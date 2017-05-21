/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/stock/stock",
    "views/stock/beforehandPackage"
],function (stock,beforehandPackage) {
    angular.module('DMS.stock', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('stock', {
                url: '/stock',
                templateUrl: 'views/stock/stock.html',
                data:{
                   permissions:{
                       only:["STOCK_LIST"],
                       redirectTo:"unauthorized"
                   }
                },
                controller: stock
            })
            .state('beforehandPackage', {
                url: '/beforehandPackage',
                templateUrl: 'views/stock/beforehandPackage.html',
                data:{
                   permissions:{
                       only:["BEFOREHAND_PACKAGE"],
                       redirectTo:"unauthorized"
                   }
                },
                controller: beforehandPackage
            })
    }]);
});