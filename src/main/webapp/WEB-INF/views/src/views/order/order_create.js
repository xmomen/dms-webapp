/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog", function($scope, OrderAPI, $modal, $ugDialog){
        $scope.order = {
            orderType:0
        };
        $scope.addOrderForm = {};
        $scope.saveOrder = function(){
            console.log($scope.order);
        }
    }];
});