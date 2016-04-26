/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrganizationAPI", "$modal", "$ugDialog", "$rootScope", function($scope, OrganizationAPI, $modal, $ugDialog, $rootScope){
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
        $scope.removeNode = function(item){
            var msg = "是否删除此组织?";
            if(item.nodes && item.nodes.length > 0){
                msg = "此组织存在下级组织，确认将同步删除此组织所有下级组织，是否删除？"
            }
            $ugDialog.confirm(msg).then(function(){
                OrganizationAPI.delete({
                    id: item.id
                }, function(){
                    $scope.getOrganizationTree();
                });
            })
        };
        $scope.addChildOrganization = function(item){
            $scope.openAddModel({
                parentId:item.id,
                parentName:item.name
            }, 'ADD_CHILD_NODE');
        };
        $scope.updateChildOrganization = function(item){
            $scope.openAddModel(angular.copy(item));
        };
        $scope.openAddModel = function (organization, action) {
            var modalInstance = $modal.open({
                templateUrl: 'addOrganization.html',
                resolve:{
                    CurrentOrganization : function(){
                        return organization;
                    },
                    Action: function(){
                        return action;
                    }
                },
                controller: ["$scope", "OrganizationAPI", "$modalInstance", "CurrentOrganization", "Action", function ($scope, OrganizationAPI, $modalInstance, CurrentOrganization, Action) {
                    $scope.organization = {};
                    if(CurrentOrganization){
                        if(Action == 'ADD_CHILD_NODE'){
                            $scope.organization.parentId = CurrentOrganization.parentId;
                            $scope.organization.parentName = CurrentOrganization.parentName;
                        }else{
                            $scope.organization = {
                                id:CurrentOrganization.id,
                                name:CurrentOrganization.name,
                                description:CurrentOrganization.description,
                                parentId:CurrentOrganization.parentId,
                                parentName:CurrentOrganization.parentName
                            };
                        }
                    }
                    $scope.errors = null;
                    $scope.addOrganizationForm = {};
                    $scope.saveOrganization = function(){
                        $scope.errors = null;
                        if($scope.addOrganizationForm.validator.form()){
                            if($scope.organization.id){
                                OrganizationAPI.update($scope.organization, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                OrganizationAPI.save($scope.organization, function(){
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
                $scope.getOrganizationTree();
            });
        };

        $scope.openUserModel = function (organization) {
            var modalInstance = $modal.open({
                templateUrl: 'organizationUser.html',
                resolve:{
                    CurrentOrganization : function(){
                        return organization;
                    }
                },
                controller: ["$scope", "OrganizationAPI", "UserAPI", "$modalInstance", "CurrentOrganization", function ($scope, OrganizationAPI, UserAPI, $modalInstance, CurrentOrganization) {
                    $scope.organization = {};
                    if(CurrentOrganization){
                        $scope.organization = {
                            id:CurrentOrganization.id,
                            name:CurrentOrganization.name,
                            description:CurrentOrganization.description,
                            parentId:CurrentOrganization.parentId,
                            parentName:CurrentOrganization.parentName
                        };
                    }
                    UserAPI.query({
                        limit:10,
                        offset:1
                    }, function(data){
                        $scope.userList = data.data;
                    });
                    $scope.choseUser = {};
                    $scope.saveOrganizationUser = function(){
                        OrganizationAPI.bindUser({
                            id:$scope.organization.id,
                            userIds:$scope.choseUser.items
                        })
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getOrganizationTree();
            });
        };

        $scope.getOrganizationTree();
    }];
});