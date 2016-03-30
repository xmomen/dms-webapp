/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CompanyAPI", "$modal", "$ugDialog", function($scope, CompanyAPI, $modal, $ugDialog){
        $scope.memberList = [];
        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCompanyList = function(){
            CompanyAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.companyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCompanyList;
            });
        };
        $scope.locked = function(index){
            UserAPI.lock({
                userId: $scope.companyList[index].userId,
                locked: $scope.companyList[index].locked == 1 ? true : false
            });
        };
        $scope.removeUser = function(index){
            $ugDialog.confirm("是否删除用户？").then(function(){
                UserAPI.delete({
                    userId: $scope.companyList[index].userId
                }, function(){
                    $scope.getCompanyList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addCompany.html',
                controller: ["$scope", "CompanyAPI", "$modalInstance", function ($scope, CompanyAPI, $modalInstance) {
                    $scope.company = {};
                    $scope.errors = null;
                    $scope.addCompanyForm = {};
                    $scope.saveOrUpdateCompany = function(){
                        $scope.errors = null;
                        if($scope.addCompanyForm.validator.form()){
                            CompanyAPI.save($scope.company, function(){
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
                $scope.getCompanyList();
            });
        };

        $scope.getCompanyList();
    }];
});