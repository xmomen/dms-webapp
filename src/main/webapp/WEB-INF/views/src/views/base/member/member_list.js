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
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addMember.html',
                controller: ["$scope", "MemberAPI", "$modalInstance","currentMember", function ($scope, MemberAPI, $modalInstance,currentMember) {
                    $scope.companyList = [];
                    MemberAPI.getCompanyList({},function(data){
                        $scope.companyList = data;
                    })
                    $scope.member = {};
                    if(currentMember){
                        $scope.member = currentMember;
                    }
                    $scope.errors = null;
                    $scope.addMemberForm = {};
                    $scope.saveOrUpdateMember = function(){
                        $scope.errors = null;
                        if($scope.addMemberForm.validator.form()){
                            if($scope.member.id){
                                MemberAPI.update($scope.member, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                MemberAPI.save($scope.member, function(){
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
                }],
                resolve: {
                    currentMember: function () {
                        return $scope.memberList[index];
                    }
                }
            });
            modalInstance.result.then(function () {
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