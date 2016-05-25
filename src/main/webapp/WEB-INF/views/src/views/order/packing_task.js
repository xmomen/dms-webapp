/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog", "UserAPI", function($scope, OrderAPI, $modal, $ugDialog, UserAPI){
        $scope.orderList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getOrderList = function(){
            OrderAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                orderStatus:1,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.getCustomerManagersList = function(){
            UserAPI.getCustomerManagerList({
                userType:"zhuangxiangzu"
            },function(data){
                $scope.companyCustomerManagers = data;
            });
        }
        $scope.removePacking = function(index){
            $ugDialog.confirm("是否删除此装箱记录？").then(function(){
                PackingAPI.delete({
                    id: $scope.packingList[index].id
                }, function(){
                    $scope.getPackingList();
                });
            })
        };
        $scope.updatePacking = function(index){
            $scope.open(angular.copy($scope.packingList[index]));
        };
        $scope.dispatchTask = function (order) {
            var modalInstance = $modal.open({
                templateUrl: 'dispatch_task.html',
                resolve: {
                    CurrentOrder: function(){
                        return order;
                    }
                },
                controller: ["$scope", "PackingAPI", "CurrentOrder", "$modalInstance", function ($scope, PackingAPI, CurrentOrder, $modalInstance) {
                    $scope.packing = {};
                    if(CurrentOrder){
                        $scope.packing = CurrentOrder;
                    }
                    $scope.errors = null;
                    $scope.addPackingForm = {};
                    $scope.savePacking = function(){
                        $scope.errors = null;
                        if($scope.addPackingForm.validator.form()){
                            if($scope.packing.id){
                                PackingAPI.update($scope.packing, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                PackingAPI.save($scope.packing, function(){
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
                }]
            });
            modalInstance.result.then(function () {
                $scope.getOrderList();
            });
        };

        $scope.getCustomerManagersList();
        $scope.getOrderList();
    }];
});