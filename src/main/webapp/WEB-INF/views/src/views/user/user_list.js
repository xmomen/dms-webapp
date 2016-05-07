/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "UserAPI", "$modal", "$ugDialog", function($scope, UserAPI, $modal, $ugDialog){
        $scope.userList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getUserList = function(){
            UserAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.userList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getUserList;
            });
        };
        $scope.locked = function(index){
            UserAPI.lock({
                id: $scope.userList[index].id,
                locked: $scope.userList[index].locked == 1 ? true : false
            });
        };
        $scope.removeUser = function(index){
            $ugDialog.confirm("是否删除用户？").then(function(){
                UserAPI.delete({
                    id: $scope.userList[index].id
                }, function(){
                    $scope.getUserList();
                });
            })
        };
        $scope.updateUser = function(index){
            $scope.open(angular.copy($scope.userList[index]));
        };
        $scope.open = function (user) {
            var modalInstance = $modal.open({
                templateUrl: 'addUser.html',
                resolve: {
                    CurrentUser: function(){
                        return user;
                    }
                },
                controller: ["$scope", "UserAPI", "CurrentUser", "$modalInstance", function ($scope, UserAPI, CurrentUser, $modalInstance) {
                    $scope.user = {};
                    if(CurrentUser){
                        $scope.user = CurrentUser;
                    }
                    $scope.errors = null;
                    $scope.addUserForm = {};
                    $scope.saveUser = function(){
                        $scope.errors = null;
                        if($scope.addUserForm.validator.form()){
                            if($scope.user.id){
                                UserAPI.update($scope.user, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                UserAPI.save($scope.user, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getUserList();
            });
        };

        $scope.getUserList();
    }];
});