/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "MemberAPI", "$modal", "$ugDialog", function($scope, MemberAPI, $modal, $ugDialog){
        $scope.memberList = [];
        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getMemberList = function(){
            MemberAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.memberList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getMemberList;
            });
        };
        $scope.locked = function(index){
            UserAPI.lock({
                userId: $scope.memberList[index].userId,
                locked: $scope.memberList[index].locked == 1 ? true : false
            });
        };
        $scope.removeUser = function(index){
            $ugDialog.confirm("是否删除用户？").then(function(){
                UserAPI.delete({
                    userId: $scope.memberList[index].userId
                }, function(){
                    $scope.getMemberList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addMember.html',
                controller: ["$scope", "MemberAPI", "$modalInstance", function ($scope, MemberAPI, $modalInstance) {
                    $scope.member = {};
                    $scope.errors = null;
                    $scope.addMemberForm = {};
                    $scope.saveOrUpdateMember = function(){
                        $scope.errors = null;
                        if($scope.addMemberForm.validator.form()){
                            MemberAPI.save($scope.member, function(){
                                $modalInstance.close();
                            }, function(data){
                                $scope.errors = data.data;
                            })
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getMemberList();
            });
        };

        $scope.getMemberList();
    }];
});