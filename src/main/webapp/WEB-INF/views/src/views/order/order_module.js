/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/order/order",
    "views/order/order_create"
],function (order, order_create) {
    angular.module('DMS.order', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('order', {
                url: '/order',
                templateUrl: 'views/order/order.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: order
            })
            .state('order_create', {
                url: '/order/create',
                templateUrl: 'views/order/order_create.html',
                controller:order_create,
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                }
            })
    }]);
});