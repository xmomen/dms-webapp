/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/message/list"
],function (messsageList) {
    angular.module('SPS.message', []).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $stateProvider

            .state('message_list', {
                url: '/message/list',
                templateUrl: 'views/message/list.html',
                data:{
                    permissions:{
                        only:["user"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: messsageList
            })

    }]);
});