/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/schedule/list"
],function (scheduleList) {
    angular.module('SPS.schedule', []).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $stateProvider

            .state('schedule', {
                url: '/schedule/list',
                templateUrl: 'views/schedule/list.html',
                data:{
                    permissions:{
                        only:["admin"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: scheduleList
            })

    }]);
});