/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "MemberAPI", "$modalInstance","currentMember","CompanyAPI", function ($scope, MemberAPI, $modalInstance,currentMember,CompanyAPI) {
        $scope.companyList = [];
        CompanyAPI.getCompanyList({},function(data){
            $scope.companyList = data;
        });
        $scope.member = {};
        if(currentMember){
            if(currentMember.id && !currentMember.name){
                MemberAPI.get({
                    id:currentMember.id
                }, function(data){
                    if(data){
                        $scope.member = data;
                    }
                })
            }else{
                $scope.member = currentMember;
            }
        }
        $scope.errors = null;
        $scope.addMemberForm = {};
        $scope.saveOrUpdateMember = function(){
            $scope.errors = null;
            if($scope.addMemberForm.validator.form()){
                if($scope.member.id){
                    MemberAPI.update($scope.member, function(){
                        $modalInstance.close(angular.copy($scope.member));
                    }, function(data){
                        $scope.errors = data.data;
                    })
                }else{
                    MemberAPI.save($scope.member, function(){
                        $modalInstance.close(angular.copy($scope.member));
                    }, function(data){
                        $scope.errors = data.data;
                    })
                }
            }
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }];
});