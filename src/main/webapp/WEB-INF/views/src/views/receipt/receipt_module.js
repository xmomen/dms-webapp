/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/receipt/receivingCode",
    "views/receipt/receivingCodeRequest",
    "views/receipt/returnOrder",
    "views/receipt/reviewDespatch",
    "views/receipt/twoSendOrder",
    "views/receipt/returnOrderShouHuo"
],function (receivingCode,receivingCodeRequest,returnOrder,reviewDespatch,twoSendOrder,returnOrderShouHuo) {
    angular.module('DMS.receipt', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('receivingCode', {
                url: '/receivingCode',
                templateUrl: 'views/receipt/receivingCode.html',
                data:{
                    permissions:{
                        only:["RECEIVING_CODE"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: receivingCode
            })
            .state('receivingCodeRequest', {
                url: '/receivingCodeRequest',
                templateUrl: 'views/receipt/receivingCodeRequest.html',
                data:{
                    permissions:{
                        only:["RECEIVING_CODE_REQUEST"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: receivingCodeRequest
            })
            .state('returnOrder', {
                url: '/returnOrder',
                templateUrl: 'views/receipt/returnOrder.html',
                data:{
                    permissions:{
                        only:["RETURN_AUDIT"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: returnOrder
            })
            .state('reviewDespatch', {
                url: '/reviewDespatch',
                templateUrl: 'views/receipt/reviewDespatch.html',
                data:{
                    permissions:{
                        only:["REVIEW_DESPATCH"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: reviewDespatch
            })
            .state('twoSendOrderList', {
                url: '/twoSendOrderList',
                templateUrl: 'views/receipt/twoSendOrder.html',
                data:{
                    permissions:{
                        only:["REVIEW_DESPATCH"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: twoSendOrder
            })

            .state('returnOrderShouHuo', {
                url: '/returnOrderShouHuo',
                templateUrl: 'views/receipt/returnOrderShouHuo.html',
//                data:{
//                    permissions:{
//                        only:["RETURN_ORDER_SHOUHUO"],
//                        redirectTo:"unauthorized"
//                    }
//                },
                controller: returnOrderShouHuo
            })
    }]);
});