/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/stock/stock"
],function (stock) {
    angular.module('DMS.stock', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('stock', {
                url: '/stock',
                templateUrl: 'views/stock/stock.html',
                //data:{
                //    permissions:{
                //        only:["USER_VIEW"],
                //        redirectTo:"unauthorized"
                //    }
                //},
                controller: stock
            })
    }]);
});