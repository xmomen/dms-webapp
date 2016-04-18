/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/base/member_list",
    "views/base/company_list",
    "views/base/couponCategory",
    "views/base/coupon",
    "views/base/dictionary",
    "views/base/itemCategory",
    "views/base/item",
    "views/base/contract"
],function (memberList,companyList,couponCategory,coupon,dictionary,itemCategory,item,contract) {
    angular.module('DMS.base', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('member_list', {
                url: '/base/member',
                templateUrl: 'views/base/member_list.html',
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
                templateUrl: 'views/base/company_list.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: companyList
            })
            .state('couponCategory', {
                url: '/couponCategory',
                templateUrl: 'views/base/couponCategory.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: couponCategory
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
            .state('dictionary', {
                url: '/dictionary',
                templateUrl: 'views/base/dictionary.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: dictionary
            })
            .state('itemCategory', {
                url: '/itemCategory',
                templateUrl: 'views/base/itemCategory.html',
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
                templateUrl: 'views/base/item.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: item
            })
            .state('contract', {
                url: '/contract',
                templateUrl: 'views/base/contract.html',
                data:{
                    permissions:{
                        only:["USER_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: contract
            })
    }]);
});