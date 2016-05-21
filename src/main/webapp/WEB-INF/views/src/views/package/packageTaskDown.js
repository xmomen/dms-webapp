/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PurchaseAPI", "$modal", "$ugDialog", function($scope, PurchaseAPI, $modal, $ugDialog){
        $scope.purchaseList = [];
        $scope.pageInfoSetting = {
            pageSize:100,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPurchaseList = function(){
            PurchaseAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                isDistribute:1
            }, function(data){
                $scope.purchaseList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPurchaseList;
            });
        };

        $scope.openDistributeModal = function (currentPurchase) {
            var modalInstance = $modal.open({
                templateUrl: 'addPackageTask.html',
                controller: ["$scope", "PackageTaskAPI", "$modalInstance","currentPurchase","UserAPI", "$rootScope", function ($scope, PackageTaskAPI, $modalInstance,currentPurchase,UserAPI,$rootScope) {
                    $scope.packageUserList = [];
                    $scope.ugSelect2Config = {};
                    UserAPI.getCustomerManagerList({
                        userType:"baozhuangzu"
                    },function(data){
                        $scope.packageUserList = data;
                    });
                    $scope.company = {};
                    if(currentPurchase){
                        $scope.packageTask = currentPurchase;
                        $scope.packageTask.noDistributeValue = $scope.packageTask.totalItemQty - $scope.packageTask.distributeValue ;
                    }
                    $scope.errors = {};
                    $scope.addPackageTaskForm = {};
                    $scope.savePackageTask = function(){
                        $scope.errors = {};
                        if($scope.packageTask.countValue > $scope.packageTask.noDistributeValue){
                            $scope.errors.message = "不能大于未分配数";
                            return;
                        }
                        if($scope.addPackageTaskForm.validator.form()){
                            PackageTaskAPI.save($scope.packageTask, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                resolve: {
                    currentPurchase: function () {
                        return currentPurchase;
                    }
                }
            });
            modalInstance.result.then(function () {
                $scope.getPurchaseList();
            });
        };

        $scope.getPurchaseList();
    }];
});