/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/base/member/member_list",
    "views/base/company/company_list",
    "views/base/coupon",
    "views/base/itemCategory/itemCategory",
    "views/base/item/item"
],function (memberList,companyList,coupon,itemCategory,item) {
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
            .state('itemCategory', {
                url: '/itemCategory',
                templateUrl: 'views/base/itemCategory/itemCategory.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: itemCategory
            })
            .state('item', {
                url: '/item',
                templateUrl: 'views/base/item/item.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: item
            })
    }]);
});