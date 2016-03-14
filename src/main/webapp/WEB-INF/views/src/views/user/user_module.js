/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/user/user_list",
    "views/user/group_list"
],function (accountList,group_list) {
    angular.module('SPS.user', []).config(["$stateProvider", function ($stateProvider) {

        $stateProvider

            .state('user_list', {
                url: '/user/list',
                templateUrl: 'views/user/user_list.html',
                data:{
                    permissions:{
                        only:["admin"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: accountList
            })
            // 用户组
            .state('group_list', {
                url: '/user/groups',
                templateUrl: 'views/user/group_list.html',
                data:{
                    permissions:{
                        only:["admin"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: group_list
            })

    }]);
});