/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CompanyAPI", "$modal", "$ugDialog", function($scope, CompanyAPI, $modal, $ugDialog){
        $scope.companyList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCompanyList = function(){
            CompanyAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.companyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCompanyList;
            });
        };
        $scope.removeCompany = function(index){
            $ugDialog.confirm("是否删除该单位/公司？").then(function(){
                CompanyAPI.delete({
                    id: $scope.companyList[index].id
                }, function(){
                    $scope.getCompanyList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addCompany.html',
                controller: ["$scope", "CompanyAPI", "$modalInstance","currentCompany","UserAPI", "$rootScope", function ($scope, CompanyAPI, $modalInstance,currentCompany,UserAPI,$rootScope) {
                    $scope.customerManagerList = [];
                    $scope.ugSelect2Config = {};
                    UserAPI.getCustomerManagerList({
                        userType:"customer_manager"
                    },function(data){
                        $scope.customerManagerList = data;
                        $scope.ugSelect2Config.initSelectData($scope.company.customerManagerIds);
                    });
                    $scope.company = {};
                    if(currentCompany){
                        $scope.company = currentCompany;
                        if($scope.company.companyCustomerManagers && $scope.company.companyCustomerManagers.length > 0){
                            $scope.company.customerManagerIds = [];
                            for (var i = 0; i < $scope.company.companyCustomerManagers.length; i++) {
                                var obj = $scope.company.companyCustomerManagers[i];
                                $scope.company.customerManagerIds.push(obj.customerMangerId);
                            }
                        }
                    }
                    $scope.errors = null;
                    $scope.addCompanyForm = {};
                    $scope.saveOrUpdateCompany = function(){
                        $scope.errors = null;
                        if($scope.addCompanyForm.validator.form()){
                            if($scope.company.id){
                                CompanyAPI.update($scope.company, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                CompanyAPI.save($scope.company, function(){
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
                currentCompany: function () {
                    return $scope.companyList[index];
                }
            }
            });
            modalInstance.result.then(function () {
                $scope.getCompanyList();
            });
        };

        $scope.getCompanyList();
    }];
});