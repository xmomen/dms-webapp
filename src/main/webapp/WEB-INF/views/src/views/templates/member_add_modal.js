/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "MemberAPI", "$modalInstance","currentMember","CompanyAPI", "UserAPI", function ($scope, MemberAPI, $modalInstance,currentMember,CompanyAPI,UserAPI) {
        $scope.companyList = [];
        $scope.ugSelect2Config = {};
        $scope.pageSetting = {
            pageSize:1000,
            pageNum:1
        };
        $scope.getCompanyList = function(){
            CompanyAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum
            }, function(data){
                $scope.companyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCompanyList;
                $scope.ugSelect2Config.initSelectData($scope.member.cdCompanyId);
                $scope.managerUgSelect2Config.initSelectData($scope.member.cdUserId);
            });
        };
        $scope.managerUgSelect2Config = {};
        $scope.getCompanyList();
        $scope.changeCompany = function(id){
            for(var i in $scope.companyList){
                var company =  $scope.companyList[i];
                if(company.id == parseInt(id)){
                    $scope.companyCustomerManagers =  company.companyCustomerManagers;
                }
            }
        };
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