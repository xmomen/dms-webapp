/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PurchaseAPI", "$modal", "$ugDialog","UserAPI", function($scope, PurchaseAPI, $modal, $ugDialog,UserAPI){
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
                isDistribute:1,
                sellUnit:0
            }, function(data){
                $scope.purchaseList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPurchaseList;
            });
        };
        //查询包装工
        $scope.packageUserList = [];
        UserAPI.getCustomerManagerList({
            userType:"baozhuangzu"
        },function(data){
            $scope.packageUserList = data;
        });
        //选择包装工
        $scope.chosePackageUser = function(index){
            $scope.currentCustomer = $scope.packageUserList[index];
        };

        $scope.openDistributeModal = function (currentPurchase) {
            if($scope.currentCustomer == null){
                $ugDialog.alert("请选择包装工人");
                return false;
            }
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
                        currentPurchase.jobUser = $scope.currentCustomer.customerMangerId;
                        currentPurchase.jobUserName =  $scope.currentCustomer.customerManger;
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