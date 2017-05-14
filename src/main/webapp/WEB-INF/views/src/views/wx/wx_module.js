/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/wx/advice"
], function (advice) {
    angular.module('DMS.wx', ["permission"]).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider
            .state('advice', {
                url: '/advice',
                templateUrl: 'views/wx/advice.html',
                data: {
                    permissions: {
                        only: ["ADVICE_VIEW"],
                        redirectTo: "unauthorized"
                    }
                },
                controller: advice
            })
    }]);
});