/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog", function($scope, OrderAPI, $modal, $ugDialog){
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
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.removeOrder = function(index){
            $ugDialog.confirm("是否删除此订单？").then(function(){
                OrderAPI.delete({
                    id: $scope.orderList[index].id
                }, function(){
                    $scope.getOrderList();
                });
            })
        };
        $scope.updateOrder = function(index){
            $scope.open(angular.copy($scope.orderList[index]));
        };
        $scope.open = function (order) {
            var modalInstance = $modal.open({
                templateUrl: 'addOrder.html',
                resolve: {
                    CurrentOrder: function(){
                        return order;
                    }
                },
                controller: ["$scope", "OrderAPI", "CurrentOrder", "$modalInstance", function ($scope, OrderAPI, CurrentOrder, $modalInstance) {
                    $scope.order = {};
                    if(CurrentOrder){
                        $scope.order = CurrentOrder;
                    }
                    $scope.errors = null;
                    $scope.addOrderForm = {};
                    $scope.saveOrder = function(){
                        $scope.errors = null;
                        if($scope.addOrderForm.validator.form()){
                            if($scope.order.id){
                                OrderAPI.update($scope.order, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                OrderAPI.save($scope.order, function(){
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

        $scope.getOrderList();
    }];
});