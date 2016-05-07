/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "UserGroupAPI", "$modal", "$ugDialog", function($scope, UserGroupAPI, $modal, $ugDialog){
        $scope.groupList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
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
            });
        };
        $scope.removeGroup = function(index){
            $ugDialog.confirm("是否删除用户组？").then(function(){
                UserGroupAPI.delete({
                    id: $scope.groupList[index].id
                }, function(){
                    $scope.getGroupList();
                });
            })
        };
        $scope.locked = function(index){
            UserGroupAPI.save({
                id: $scope.groupList[index].id,
                available: $scope.groupList[index].available == 1 ? true : false
            });
        };
        // 新增用户组
        $scope.open = function (index, size) {
            var modalInstance = $modal.open({
                templateUrl: 'addGroup.html',
                controller: ["$scope", "$modalInstance", function ($scope, $modalInstance) {
                    $scope.userGroup = {};
                    $scope.addGroupForm = {};
                    $scope.saveGroup = function(){
                        if($scope.addGroupForm.validator.form()){
                            UserGroupAPI.save($scope.userGroup, function(){
                                $modalInstance.close();
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
                $scope.getGroupList();
            });
        };
        // 新增用户到用户组
        $scope.openAddUserToGroup = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addUserToGroup.html',
                controller: ["$scope", "$modalInstance", "UserGroupRelationAPI", "UserGroupModel", function ($scope, $modalInstance, UserGroupRelationAPI, UserGroupModel) {
                    $scope.userGroup = UserGroupModel;
                    $scope.choseUsers = [];
                    $scope.queryParam = {};
                    $scope.choseUsersPageInfo = {
                        pageSize:50,
                        pageNum:1
                    };
                    $scope.addGroupForm = {};
                    $scope.getChoseUser = function(){
                        UserGroupRelationAPI.query({
                            id:$scope.userGroup.id,
                            chose:true,
                            limit:$scope.choseUsersPageInfo.pageSize,
                            offset:$scope.choseUsersPageInfo.pageNum
                        },function(data){
                            $scope.choseUsers = data.data;
                            $scope.choseUsersPageInfo = data.pageInfo;
                            $scope.choseUsersPageInfo.loadData = $scope.getChoseUser;
                        })
                    };
                    $scope.unChoseUsersPageInfo = {
                        pageSize:50,
                        pageNum:1
                    };
                    $scope.unChoseUsers = [];
                    $scope.getUnChoseUser = function(){
                        UserGroupRelationAPI.query({
                            id:$scope.userGroup.id,
                            chose:false,
                            keyword:$scope.queryParam.keyword,
                            limit:$scope.unChoseUsersPageInfo.pageSize,
                            offset:$scope.unChoseUsersPageInfo.pageNum
                        },function(data){
                            $scope.unChoseUsers = data.data;
                            $scope.unChoseUsersPageInfo = data.pageInfo;
                            $scope.unChoseUsersPageInfo.loadData = $scope.getUnChoseUser;
                        })
                    };
                    $scope.getChoseUser();
                    $scope.getUnChoseUser();
                    $scope.removeUser = function(index){
                        UserGroupRelationAPI.save({
                            id:$scope.userGroup.id,
                            userId:$scope.choseUsers[index].userId,
                            chose:false
                        }, function(){
                            $scope.unChoseUsers.push($scope.choseUsers[index]);
                            $scope.choseUsers.splice(index, 1);
                        })
                    };
                    $scope.addUser = function(index){
                        UserGroupRelationAPI.save({
                            id:$scope.userGroup.id,
                            userId:$scope.unChoseUsers[index].userId,
                            chose:true
                        }, function(){
                            $scope.choseUsers.push($scope.unChoseUsers[index]);
                            $scope.unChoseUsers.splice(index, 1);
                        })
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                resolve:{
                    UserGroupModel:function(){
                        return $scope.groupList[index]
                    }
                },
                size: "lg"
            });
            modalInstance.result.then(function () {
                $scope.getGroupList();
            });
        };
        // 修改用户组权限
        $scope.openUpdateGroupPermission = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'updateGroupPermission.html',
                controller: ["$scope", "$modalInstance","User", "GroupPermissionRelationAPI", "UserGroupModel", function ($scope, $modalInstance,User, GroupPermissionRelationAPI, UserGroupModel) {
                    $scope.userGroup = UserGroupModel;
                    $scope.chosePermissions = [];
                    $scope.queryParam = {};
                    $scope.chosePermissionsPageInfo = {
                        pageSize:50,
                        pageNum:1
                    };
                    $scope.addGroupForm = {};
                    $scope.getChosePermissions = function(){
                        GroupPermissionRelationAPI.query({
                            id:$scope.userGroup.id,
                            chose:true,
                            limit:$scope.chosePermissionsPageInfo.pageSize,
                            offset:$scope.chosePermissionsPageInfo.pageNum
                        },function(data){
                            $scope.chosePermissions = data.data;
                            $scope.chosePermissionsPageInfo = data.pageInfo;
                            $scope.chosePermissionsPageInfo.loadData = $scope.getChosePermissions;
                        })
                    };
                    $scope.unChosePermissionPageInfo = {
                        pageSize:50,
                        pageNum:1
                    };
                    $scope.unChosePermissions = [];
                    $scope.getUnChosePermissions = function(){
                        GroupPermissionRelationAPI.query({
                            id:$scope.userGroup.id,
                            chose:false,
                            keyword:$scope.queryParam.keyword,
                            limit:$scope.unChosePermissionPageInfo.pageSize,
                            offset:$scope.unChosePermissionPageInfo.pageNum
                        },function(data){
                            $scope.unChosePermissions = data.data;
                            $scope.unChosePermissionPageInfo = data.pageInfo;
                            $scope.unChosePermissionPageInfo.loadData = $scope.getUnChosePermissions;
                        })
                    };
                    $scope.getChosePermissions();
                    $scope.getUnChosePermissions();
                    $scope.unCorrelationPermission = function(index){
                        GroupPermissionRelationAPI.save({
                            id:$scope.userGroup.id,
                            permissionId:$scope.chosePermissions[index].permissionId,
                            chose:false
                        }, function(){
                            $scope.unChosePermissions.push($scope.chosePermissions[index]);
                            $scope.chosePermissions.splice(index, 1);
                            User.fetchPermission();
                        })
                    };
                    $scope.correlationUser = function(index){
                        GroupPermissionRelationAPI.save({
                            id:$scope.userGroup.id,
                            permissionId:$scope.unChosePermissions[index].permissionId,
                            chose:true
                        }, function(){
                            $scope.chosePermissions.push($scope.unChosePermissions[index]);
                            $scope.unChosePermissions.splice(index, 1);
                            User.fetchPermission();
                        })
                    };
                    $scope.cancel = function () {
//                        window.location.reload();
                        $modalInstance.dismiss('cancel');
                    };
                }],
                resolve:{
                    UserGroupModel:function(){
                        return $scope.groupList[index]
                    }
                },
                size: "lg"
            });
            modalInstance.result.then(function () {
                $scope.getGroupList();
            });
        };

        $scope.getGroupList();
    }];
});