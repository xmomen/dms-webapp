/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/user/user_list",
    "views/user/group_list",
    "views/user/permission_list",
    "views/user/organization"
],function (accountList,group_list,permission_list,organization) {
    angular.module('DMS.user', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('user_list', {
                url: '/user/list',
                templateUrl: 'views/user/user_list.html',
                //data:{
                //    permissions:{
                //        only:["admin"],
                //        redirectTo:"unauthorized"
                //    }
                //},
                controller: accountList
            })
            // 用户组
            .state('group_list', {
                url: '/user/groups',
                templateUrl: 'views/user/group_list.html',
                //data:{
                //    permissions:{
                //        only:["admin"],
                //        redirectTo:"unauthorized"
                //    }
                //},
                controller: group_list
            })
            // 权限管理
            .state('permission', {
                url: '/permission',
                templateUrl: 'views/user/permission_list.html',
                data:{
                    permissions:{
                        only:["admin"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: permission_list
            })
            // 组织管理
            .state('organization', {
                url: '/organization',
                templateUrl: 'views/user/organization.html',
                data:{
                    permissions:{
                        only:["admin"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: organization
            })
    }]);
});