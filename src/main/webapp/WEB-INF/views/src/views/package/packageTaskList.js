/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackageTaskAPI", "$modal", "$ugDialog", function($scope, PackageTaskAPI, $modal, $ugDialog){
        $scope.packageTaskList = [];
        $scope.pageInfoSetting = {
            pageSize:100,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPackageTaskList = function(){
            PackageTaskAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.packageTaskList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPackageTaskList;
            });
        };

        $scope.openDistributeModal = function (currentPackageTask) {
            var modalInstance = $modal.open({
                templateUrl: 'addPackageTask.html',
                controller: ["$scope", "PackageTaskAPI", "$modalInstance","currentPackageTask","UserAPI", "$rootScope", function ($scope, PackageTaskAPI, $modalInstance,currentPackageTask,UserAPI,$rootScope) {
                    $scope.packageUserList = [];
                    $scope.ugSelect2Config = {};
                    UserAPI.getCustomerManagerList({
                        userType:"baozhuangzu"
                    },function(data){
                        $scope.packageUserList = data;
                    });
                    $scope.company = {};
                    if(currentPackageTask){
                        $scope.packageTask = currentPackageTask;
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
                    currentPackageTask: function () {
                        return currentPackageTask;
                    }
                }
            });
            modalInstance.result.then(function () {
                $scope.getPackageTaskList();
            });
        };

        $scope.getPackageTaskList();
    }];
});