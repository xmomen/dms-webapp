/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "UserAPI", "$modal", "$ugDialog", function($scope, UserAPI, $modal, $ugDialog){
        $scope.userList = [];
        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getUserList = function(){
            UserAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.userList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getUserList;
            });
        };
        $scope.locked = function(index){
            UserAPI.lock({
                userId: $scope.userList[index].userId,
                locked: $scope.userList[index].locked == 1 ? true : false
            });
        };
        $scope.removeUser = function(index){
            $ugDialog.confirm("是否删除用户？").then(function(){
                UserAPI.delete({
                    userId: $scope.userList[index].userId
                }, function(){
                    $scope.getUserList();
                });
            })
        };
        $scope.open = function (index, size) {
            var modalInstance = $modal.open({
                templateUrl: 'addUser.html',
                controller: ["$scope", "UserAPI", "$modalInstance", function ($scope, UserAPI, $modalInstance) {
                    $scope.user = {};
                    $scope.errors = null;
                    $scope.addAccountForm = {};
                    $scope.saveAccount = function(){
                        $scope.errors = null;
                        if($scope.addAccountForm.validator.form()){
                            UserAPI.save($scope.user, function(){
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
                $scope.getUserList();
            });
        };

        $scope.getUserList();
    }];
});