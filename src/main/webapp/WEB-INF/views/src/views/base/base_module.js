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
    "views/base/couponAuditBack",
    "views/base/express_list",
    "views/base/express_member_list",
    "views/base/onlineRecharge",
    "views/base/tradeRecordList",
    "views/base/couponActivityAddress"
],function (memberList,companyList,couponCategory,coupon,dictionary,itemCategory,item,contract,contract_create,contract_update,activity,couponActivity,couponAudit,couponAuditBack,express_list,express_member_list,onlineRecharge,tradeRecordList,couponActivityAddress) {
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
            .state('couponActivityAddress', {
                url: '/couponActivityAddress',
                templateUrl: 'views/base/couponActivityAddress.html',
                data:{
                    permissions:{
                        only:["COUPON_ACTIVITY_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: couponActivityAddress
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
                controller: couponAuditBack
            })
            .state('expressList', {
                url: '/expressList',
                templateUrl: 'views/base/express_list.html',
                data:{
                    permissions:{
                        only:["EXPRESS_LIST"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: express_list
            })

            .state('expressMemberList', {
                url: '/expressMemberList',
                templateUrl: 'views/base/express_member_list.html',
                data:{
                    permissions:{
                        only:["EXPRESS_MEMBER_LIST"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: express_member_list
            })

            .state('onlineRecharge', {
                url: '/onlineRecharge',
                templateUrl: 'views/base/onlineRecharge.html',
                data:{
                    permissions:{
                        only:["ONLINE_RECHARGE"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: onlineRecharge
            })

            .state('tradeRecordList', {
                url: '/tradeRecordList',
                templateUrl: 'views/base/tradeRecordList.html',
                data:{
                    permissions:{
                        only:["TRADE_RECORD_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: tradeRecordList
            })
    }]);
});