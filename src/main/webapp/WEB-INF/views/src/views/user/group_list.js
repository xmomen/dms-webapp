/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "UserGroupAPI", "$modal", function($scope, UserGroupAPI, $modal){
        $scope.groupList = [];
        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getGroupList = function(){
            UserGroupAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.groupList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getGroupList;
            });
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

        $scope.getGroupList();
    }];
});