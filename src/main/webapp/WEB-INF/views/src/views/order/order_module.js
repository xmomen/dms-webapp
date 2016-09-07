/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/order/order",
    "views/order/order_create",
    "views/order/order_update",
    "views/order/purchase",
    "views/order/packing",
    "views/order/order_packing",
    "views/order/packing_task",
    "views/order/order_report"
],function (order, order_create,order_update, purchase, packing, order_packing, packing_task, order_report) {
    angular.module('DMS.order', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('order', {
                url: '/order',
                templateUrl: 'views/order/order.html',
                data:{
                    permissions:{
                        only:["ORDER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: order
            })

            .state('order_report', {
                url: '/order/report',
                templateUrl: 'views/order/order_report.html',
                data:{
                    permissions:{
                        only:["ORDER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: order_report
            })

            .state('order_create', {
                url: '/order/create',
                templateUrl: 'views/order/order_create.html',
                controller:order_create,
                data:{
                    permissions:{
                        only:["ORDER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                }
            })

            .state('order_update', {
                url: '/order/:id/update/:orderNo',
                templateUrl: 'views/order/order_update.html',
                controller:order_update,
                //params:{order:{}},
                data:{
                    permissions:{
                        only:["ORDER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                }
            })

            .state('purchase', {
                url: '/purchase',
                templateUrl: 'views/order/purchase.html',
                data:{
                    permissions:{
                        only:["PURCHASE_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: purchase
            })

            .state('packing', {
                url: '/packing',
                templateUrl: 'views/order/packing.html',
                data:{
                    permissions:{
                        only:["PACKING_LIST_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: packing
            })

            .state('packing_task', {
                url: '/packing/task',
                templateUrl: 'views/order/packing_task.html',
                data:{
                    permissions:{
                        only:["PACKING_TASK"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: packing_task
            })

            .state('order_packing', {
                url: '/order/packing',
                templateUrl: 'views/order/order_packing.html',
                data:{
                    permissions:{
                        only:["PACKING_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: order_packing
            })
    }]);
});