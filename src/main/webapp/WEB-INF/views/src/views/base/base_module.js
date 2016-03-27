/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/base/member/member_list",
    "views/base/company/company_list"
],function (memberList,companyList) {
    angular.module('DMS.base', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('member_list', {
                url: '/base/member',
                templateUrl: 'views/base/member/member_list.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: memberList
            })
            .state('company_list', {
                url: '/base/company',
                templateUrl: 'views/base/company/company_list.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: companyList
            })
    }]);
});