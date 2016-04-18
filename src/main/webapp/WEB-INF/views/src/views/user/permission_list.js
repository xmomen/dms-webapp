/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PermissionAPI", "$modal", "$ugDialog", function($scope, PermissionAPI, $modal, $ugDialog){
        $scope.userList = [];
        $scope.pageInfoSetting = {
            pageSize:30,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPermissionList = function(){
            PermissionAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.permissionList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPermissionList;
            });
        };
        $scope.removePermission = function(index){
            $ugDialog.confirm("是否删除权限？").then(function(){
                PermissionAPI.delete({
                    id: $scope.permissionList[index].id
                }, function(){
                    $scope.getPermissionList();
                });
            })
        };
        $scope.open = function (index, size) {
            var modalInstance = $modal.open({
                templateUrl: 'addPermission.html',
                controller: ["$scope", "PermissionAPI", "$modalInstance", function ($scope, PermissionAPI, $modalInstance) {
                    $scope.permission = {};
                    $scope.errors = null;
                    $scope.addPermissionForm = {};
                    $scope.savePermission = function(){
                        $scope.errors = null;
                        if($scope.addPermissionForm.validator.form()){
                            $scope.permission.available = true;
                            PermissionAPI.save($scope.permission, function(){
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
                size: size
            });
            modalInstance.result.then(function () {
                $scope.getPermissionList();
            });
        };

        $scope.getPermissionList();
    }];
});