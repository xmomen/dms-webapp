/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/base/member/member_list",
    "views/base/company/company_list",
    "views/base/coupon",
    "views/base/dictionary"
],function (memberList,companyList,coupon,dictionary) {
    angular.module('DMS.base', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('data_dictionary', {
                url: '/dictionary',
                templateUrl: 'views/base/dictionary.html',
                //data:{
                //    permissions:{
                //        only:["USER_VIEW"],
                //        redirectTo:"unauthorized"
                //    }
                //},
                controller: dictionary
            })
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
            .state('coupon', {
                url: '/coupon',
                templateUrl: 'views/base/coupon.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: coupon
            })
    }]);
});