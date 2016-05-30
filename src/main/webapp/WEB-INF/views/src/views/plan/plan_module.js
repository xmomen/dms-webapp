/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/plan/basePlan",
    "views/plan/tablePlan"
],function (basePlan,tablePlan) {
    angular.module('DMS.plan', ["permission"]).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $stateProvider
            .state('basePlan', {
                url: '/basePlan',
                templateUrl: 'views/plan/basePlan.html',
                data:{
                    permissions:{
                        only:["BASE_PLAN"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: basePlan
            })
            .state('tablePlan', {
                url: '/tablePlan',
                templateUrl: 'views/plan/tablePlan.html',
                data:{
                    permissions:{
                        only:["TABLE_PLAN"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: tablePlan
            })
    }]);
});