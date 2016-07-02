/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "MemberAPI", "$modal", "$ugDialog", "$modalMemberAdd", function($scope, MemberAPI, $modal, $ugDialog, $modalMemberAdd){
        $scope.memberList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getMemberList = function(){
            MemberAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                couponNumber:$scope.queryParam.couponNumber
            }, function(data){
                $scope.memberList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getMemberList;
            });
        };
        $scope.open = function (index) {
            $modalMemberAdd.open({
                currentMember: $scope.memberList[index]
            }).result.then(function () {
                $scope.getMemberList();
            });
        };
        $scope.removeMember = function(index){
            $ugDialog.confirm("是否删除客户？").then(function(){
                MemberAPI.delete({
                    id: $scope.memberList[index].id
                },function(){
                    $scope.getMemberList();
                });
            })
        };

        $scope.getMemberList();
    }];
});