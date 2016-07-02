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
    "views/base/contract",
    "views/base/contract_create",
    "views/base/contract_update",
    "views/base/activity",
    "views/base/couponActivity",
    "views/base/couponAudit",
    "views/base/couponAuditBack"
],function (memberList,companyList,couponCategory,coupon,dictionary,itemCategory,item,contract,contract_create,contract_update,activity,couponActivity,couponAudit,couponAuditBack) {
    angular.module('DMS.base', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('member_list', {
                url: '/base/member',
                templateUrl: 'views/base/member_list.html',
                data:{
                    permissions:{
                        only:["MEMBER_VIEW"],
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
                        only:["COMPANY_VIEW"],
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
                        only:["COUPON_CATEGORY_VIEW"],
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
                        only:["COUPON_VIEW"],
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
                        only:["DICTIONARY_VIEW"],
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
                        only:["PRODUCT_CATEGORY_VIEW"],
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
                        only:["PRODUCT_VIEW"],
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
                        only:["CONTRACT_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: contract
            })
            .state('contract_create', {
                url: '/contract/create',
                templateUrl: 'views/base/contract_create.html',
                data:{
                    permissions:{
                        only:["CONTRACT_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: contract_create
            })

            .state('contract_update', {
                url: '/contract/:id/update/',
                templateUrl: 'views/base/contract_update.html',
                controller:contract_update,
                data:{
                    permissions:{
                        only:["CONTRACT_VIEW"],
                        redirectTo:"unauthorized"
                    }
                }
            })

            .state('activity', {
                url: '/activity',
                templateUrl: 'views/base/activity.html',
                data:{
                    permissions:{
                        only:["ACTIVITY_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: activity
            })
            .state('couponActivity', {
                url: '/couponActivity',
                templateUrl: 'views/base/couponActivity.html',
                data:{
                    permissions:{
                        only:["COUPON_ACTIVITY_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: couponActivity
            })
            .state('couponAudit', {
                url: '/couponAudit',
                templateUrl: 'views/base/couponAudit.html',
                data:{
                    permissions:{
                        only:["COUPON_AUDIT_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: couponAudit
            })
            .state('couponAuditBack', {
                url: '/couponAuditBack',
                templateUrl: 'views/base/couponAuditBack.html',
                data:{
                    permissions:{
                        only:["COUPON_AUDIT_BACK_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: couponAudit
            })
    }]);
});