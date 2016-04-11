/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog", function($scope, OrderAPI, $modal, $ugDialog){
        $scope.order = {
            orderType:0
        };
        $scope.addOrderForm = {};
        $scope.errors = null;
        $scope.saveOrder = function(){
            if($scope.addOrderForm.validator.form()){
                OrderAPI.save($scope.order, function(){
                    $ugDialog.alert("订单提交成功！");
                }, function(data){
                    $scope.errors = data.data;
                })
            }
        }
    }];
});