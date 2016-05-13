/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ContractAPI", "$modal", "$ugDialog", function($scope, ContractAPI, $modal, $ugDialog){
        $scope.contractList = [];
        $scope.contract = {};
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getContractList = function(){
            ContractAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.contractList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getContractList;
            });
        };
        $scope.removeContract = function(index){
            $ugDialog.confirm("是否删除该合同？").then(function(){
                ContractAPI.delete({
                    id: $scope.contractList[index].id
                }, function(){
                    $scope.getContractList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addContract.html',
                controller: ["$scope", "ContractAPI", "$modalInstance","currentContract","CompanyAPI", function ($scope, ContractAPI, $modalInstance,currentContract,CompanyAPI) {
                    $scope.companyList = [];
                    $scope.ugSelect2Config = {};
                    CompanyAPI.getCompanyList({},function(data){
                        $scope.companyList = data;
                        $scope.ugSelect2Config.initSelectData($scope.contract.cdCompanyId);
                    })
                    $scope.contract = {};
                    if(currentContract){
                        $scope.contract = currentContract;
                    }

                    $scope.datepickerSetting = {
                        datepickerPopupConfig:{
                            "current-text":"今天",
                            "clear-text":"清除",
                            "close-text":"关闭"
                        },
                        beginTime:{
                            opened:false
                        },
                        endTime:{
                            opened:false
                        }
                    };
                    $scope.open = function($event, index) {
                        $event.preventDefault();
                        $event.stopPropagation();
                        if(index == 0){
                            $scope.datepickerSetting.beginTime.opened = true;
                        }else if(index == 1){
                            $scope.datepickerSetting.endTime.opened = true;
                        }
                    };
                    $scope.errors = null;
                    $scope.addContractForm = {};
                    $scope.saveOrUpdateContract = function(){
                        $scope.errors = null;
                        if($scope.addContractForm.validator.form()){
                            if($scope.contract.id){
                                ContractAPI.update($scope.contract, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                ContractAPI.save($scope.contract, function(){
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
                currentContract: function () {
                    return $scope.contractList[index];
                }
            }
            });
            modalInstance.result.then(function () {
                $scope.getContractList();
            });
        };

        $scope.openContractItemModal = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addContractItem.html',
                controller: ["$scope", "ContractItemAPI", "$modalInstance","currentContract", function ($scope, ContractItemAPI, $modalInstance,currentContract) {
                    $scope.addContractItem = {};
                    $scope.contract = {};
                    $scope.addContractItemFrom ={};
                    if(currentContract){
                        $scope.contract = currentContract;
                    }
                    $scope.errors = null;
                    $scope.getContractItemList = function(){
                        ContractItemAPI.query({
                            limit:1000,
                            offset:1,
                            keyword:currentContract.id
                        }, function(data){
                            $scope.contractItemList = data.data;
                        });
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    $scope.chooseCategoryModel = function(){
                        var modalInstance = $modal.open({
                            templateUrl: 'chooseCategory.html',
                            controller: ["$scope", "ItemCategoryAPI","$modalInstance", function ($scope, ItemCategoryAPI,$modalInstance) {
                                $scope.itemCategoryList = [];
                                $scope.queryParam = {};
                                ItemCategoryAPI.query({
                                    id:$scope.queryParam.id
                                }, function(data){
                                    $scope.itemCategoryList = data;
                                });
                                $scope.cancel = function () {
                                    $modalInstance.dismiss('cancel');
                                };
                                $scope.chooseCategory = function(category){
                                    $modalInstance.close(category);
                                }
                            }]
                        });
                        modalInstance.result.then(function (category) {
                            $scope.addContractItem.categoryName = category.name;
                            $scope.addContractItem.cdCategoryId = category.id;
                        });
                    }

                    $scope.saveOrUpdateContractItem = function(){
                        $scope.errors = null;
                        if($scope.addContractItemFrom.validator.form()){
                            $scope.addContractItem.cdContractId = currentContract.id;
                            ContractItemAPI.save($scope.addContractItem, function(){
                                $scope.getContractItemList();
                                $scope.addContractItem = {};
                            }, function(data){
                                $scope.errors = data.data;
                            })
                        }
                    }
                    $scope.getContractItemList();
                }],
                resolve: {
                    currentContract: function () {
                        return $scope.contractList[index];
                    }
                }
            });
            modalInstance.result.then(function () {
                $scope.getContractList();
            });
        };

        $scope.getContractList();
    }];
});