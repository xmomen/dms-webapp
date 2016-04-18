/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/schedule/list"
],function (scheduleList) {
    angular.module('DMS.schedule', ["permission"]).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $stateProvider

            .state('schedule', {
                url: '/schedule',
                templateUrl: 'views/schedule/list.html',
                data:{
                    permissions:{
                        only:["SCHEDULE_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: scheduleList
            })

    }]);
});