/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/report/reportOrder"
],function (reportOrder) {
    angular.module('DMS.report', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('reportOrder', {
                url: '/reportOrder',
                templateUrl: 'views/report/reportOrder.html',
                data:{
                    permissions:{
                        only:["REPORT_ORDER"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: reportOrder
            })
    }]);
});