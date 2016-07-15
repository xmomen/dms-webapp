/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "UserAPI", "$modal", "$ugDialog","OrganizationAPI","$rootScope",function($scope, UserAPI, $modal, $ugDialog,OrganizationAPI,$rootScope){

        $scope.organizationList = [];
        $scope.queryParam = {};
        $scope.getOrganizationTree = function(){
            OrganizationAPI.query({
                id:$scope.queryParam.id
            }, function(data){
                $scope.organizationList = data;
                $rootScope.$broadcast("loadingTree");
            });
        };
        $scope.getOrganizationTree();
        $scope.userList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getUserList = function(organizationId){
            UserAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                organizationId : organizationId
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

        //重置默认密码
        $scope.resetDefaultPassword = function(index){
            UserAPI.resetDefaultPassword({
                id: $scope.userList[index].id
            }, function(){
                $ugDialog.alert("重置密码成功，新密码：123456")
            })
        }

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
                controller: ["$scope", "UserAPI", "CurrentUser", "$modalInstance","UserGroupAPI", function ($scope, UserAPI, CurrentUser, $modalInstance,UserGroupAPI) {
                    $scope.user = {};
                    $scope.ugSelect2Config = {};
                    $scope.groupList = [];
                    $scope.pageInfoSetting = {
                        pageSize:1000,
                        pageNum:1
                    };
                    $scope.queryParam = {};
                    $scope.getGroupList = function(){
                        UserGroupAPI.query({
                            limit:$scope.pageInfoSetting.pageSize,
                            offset:$scope.pageInfoSetting.pageNum,
                            keyword:$scope.queryParam.keyword
                        }, function(data){
                            $scope.groupList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getGroupList;
                            $scope.ugSelect2Config.initSelectData($scope.user.userGroupIds);
                        });
                    };
                    $scope.getGroupList();
                    if(CurrentUser){
                        $scope.user = CurrentUser;
                        if($scope.user.userGroups && $scope.user.userGroups.length > 0){
                            $scope.user.userGroupIds = [];
                            for (var i = 0; i < $scope.user.userGroups.length; i++) {
                                var obj = $scope.user.userGroups[i];
                                $scope.user.userGroupIds.push(obj.userGroupId);
                            }
                        }
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
                    $scope.chooseCategoryModel = function(){
                        var modalInstance = $modal.open({
                            templateUrl: 'chooseCategory.html',
                            controller: ["$scope", "OrganizationAPI", "$modalInstance","$rootScope", function ($scope, OrganizationAPI, $modalInstance, $rootScope) {
                                $scope.innerOrganizationList = [];
                                $scope.queryParam = {};
                                OrganizationAPI.query({
                                    id:$scope.queryParam.id
                                }, function(data){
                                    $scope.innerOrganizationList = data;
                                    $rootScope.$broadcast("loadingTree");
                                });
                                $scope.cancel = function () {
                                    $modalInstance.dismiss('cancel');
                                };
                                $scope.chooseCategory = function(category){
                                    $modalInstance.close(category);
                                }
                            }]
                        });
                        modalInstance.result.then(function (category) {
                            $scope.user.organization = category.name;
                            $scope.user.organizationId = category.id;
                        });
                    }
                }]
            });
            modalInstance.result.then(function () {
                $scope.getUserList();
            });
        };

        $scope.getUserList();
    }];
});