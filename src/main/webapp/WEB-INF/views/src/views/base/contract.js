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
            $ugDialog.confirm("是否作废该合同？").then(function(){
                ContractAPI.delete({
                    id: $scope.contractList[index].id
                }, function(){
                    $scope.getContractList();
                });
            })
        };
        $scope.viewContract = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'viewContractDetail.html',
                resolve: {
                    CurrentContract: function(){
                        return angular.copy($scope.contractList[index]);
                    }
                },
                controller: ["$scope", "OrderAPI", "CurrentContract", "$modalInstance", function ($scope, OrderAPI, CurrentContract, $modalInstance) {
                    $scope.setting = {
                        pageInfo : {
                            pageSize:1000,
                            pageNum:1
                        }
                    };
                    ContractAPI.get({
                        id: CurrentContract.id
                    },function(data){
                        $scope.contract = data;
                        $scope.choseItemList = data.contractItemList;
                    });

                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                size:"lg"
            });
            modalInstance.result.then(function () {
                $scope.getOrderList();
            });
        };
        $scope.getContractList();
    }];
});