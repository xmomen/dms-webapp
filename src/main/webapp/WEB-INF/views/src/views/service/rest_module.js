/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    var ngREST = angular.module("DMS.REST", ["ngResource"]);
    ngREST.factory("UserAPI", ["$resource", function ($resource) {
        return $resource("/user/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            lock: {
                method: "PUT",
                url: "/user/:id/locked",
                params: {
                    id: "@id",
                    locked: "@locked"
                }
            },
            resetDefaultPassword: {
                method: "PUT",
                url: "/user/:id/resetPassword",
                params: {
                    id: "@id"
                }
            },
            getPermissions: {
                url: "/user/permissions"
            },
            resetPassword: {
                url: "/account/resetPassword",
                method: "POST",
                params: {
                    current_password: "@current_password",
                    password: "@password"
                }
            },
            getCustomerManagerList: {
                method: "GET", url: "/customerManagerList",
                params: {userType: "@userType", keyword: "@keyword"},
                isArray: true
            }
        });
    }]);
    ngREST.factory("MessageAPI", ["$resource", function ($resource) {
        return $resource("/message/:id", {id: '@id'}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            log: {
                isArray: true,
                method: "GET",
                url: "/message/:id/log",
                params: {id: "@id"}
            },
            retry: {
                url: "/message/retry",
                method: "POST",
                params: {messageId: "@messageId"}
            }
        });
    }]);
    ngREST.factory("UserGroupAPI", ["$resource", function ($resource) {
        return $resource("/group/:id", {id: '@id'}, {
            query: {isArray: false}
        });
    }]);
    ngREST.factory("UserGroupRelationAPI", ["$resource", function ($resource) {
        return $resource("/group/:id/user", {id: '@id', userId: '@userId'}, {
            query: {isArray: false},
            save: {
                method: "PUT",
                params: {
                    chose: "@chose",
                    userId: "@userId"
                }
            }
        });
    }]);
    ngREST.factory("GroupPermissionRelationAPI", ["$resource", function ($resource) {
        return $resource("/group/:id/permissions", {id: '@id'}, {
            query: {isArray: false},
            save: {
                method: "PUT",
                params: {
                    chose: "@chose",
                    permissionId: "@permissionId"
                }
            }
        });
    }]);
    ngREST.factory("PermissionAPI", ["$resource", function ($resource) {
        return $resource("/permission/:id", {id: '@id'}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);
    ngREST.factory("ScheduleAPI", ["$resource", function ($resource) {
        return $resource("/schedule/:id", {id: '@id'}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            getTemplates: {
                url: "/schedule/template",
                method: "GET",
                isArray: true
            }
        });
    }]);
    ngREST.factory("OrganizationAPI", ["$resource", function ($resource) {
        return $resource("/organization/:id", {id: '@id'}, {
            //  query:{ isArray:false},
            update: {method: "PUT", params: {id: "@id"}},
            bindUser: {
                url: "/organization/:id/user",
                method: "POST", params: {id: "@id", userIds: "@userIds"}
            },
            unBindUser: {
                url: "/organization/:id/user",
                method: "DELETE", params: {id: "@id", userIds: "@userIds"}
            }
        });
    }]);

    ngREST.factory("MemberAPI", ["$resource", function ($resource) {
        return $resource("/member/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("CompanyAPI", ["$resource", function ($resource) {
        return $resource("/company/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            getCompanyList: {method: "GET", url: "/companyList", isArray: true}
        });
    }]);

    ngREST.factory("ItemCategoryAPI", ["$resource", function ($resource) {
        return $resource("/itemCategory/:id", {id: "@id"}, {
            //  query:{ isArray:false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("ItemAPI", ["$resource", function ($resource) {
        return $resource("/item/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            getChildItemList: {method: "GET", url: "/getChildItem", isArray: true}
        });
    }]);

    ngREST.factory("CouponCategoryAPI", ["$resource", function ($resource) {
        return $resource("/couponCategory/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            getChoseItemList: {method: "GET", url: "/getChoseItemList", isArray: true}
        });
    }]);

    ngREST.factory("CouponAPI", ["$resource", function ($resource) {
        return $resource("/coupon/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            sendOneCoupon: {method: "GET", url: "/coupon/sendOneCoupon", params: {id: "@id", companyId: "@companyId", couponNumber: "@couponNumber", batch: "@batch", isGift: "@isGift"}},
            sendMoreCoupon: {method: "GET", url: "/coupon/sendMoreCoupon", params: {companyId: "@companyId", couponNumberList: "@couponNumberList", batch: "@batch", isGift: "@isGift"}},
            updateBatchCoupon: {method: "GET", url: "/coupon/updateBatchCoupon", params: {companyId: "@companyId", customerMangerId: "@customerMangerId", batch: "@batch"}},
            updateBatchCouponType: {method: "GET", url: "/coupon/updateBatchCouponType", params: {couponCategoryId: "@couponCategoryId", couponNumberList: "@couponNumberList"}},
            activityAddress: {method: "POST", url: "/coupon/activityAddress"},
            updateUserPrice: {
                method: "GET",
                url: "/coupon/updateBalance",
                params: {
                    couponNo: "@couponNo",
                    updatePrice: "@updatePrice",
                    couponNo: "@remark"
                }
            },
            audit: {
                method: "PUT",
                url: "/coupon/:id/audit",
                params: {
                    id: "@id",
                    locked: "@locked"
                }
            },
            batchAudit: {
                method: "PUT",
                url: "/coupon/batchAudit",
                params: {
                    ids: "@ids",
                    locked: "@locked"
                }
            },
            overCoupon: {
                method: "PUT",
                url: "/coupon/:id/overCoupon",
                params: {
                    id: "@id",
                    isOver: "@isOver"
                }
            },
            returnCoupon: {
                method: "PUT",
                url: "/coupon/:id/returnCoupon",
                params: {
                    id: "@id"
                }
            },
            receivedPrice: {
                method: "GET",
                url: "/coupon/receivedPrice",
                params: {
                    couponId: "@couponId",
                    couponNumber: "@couponNumber",
                    receivedPrice: "@receivedPrice",
                    isAutoAudit: "@isAutoAudit"
                }
            },
            readCard: {
                method: "GET",
                url: "/coupon/readCard",
                params: {
                    couponNo: "@couponNo",
                    password: "@password"
                }
            },
            cardRecharge: {
                method: "GET",
                url: "/coupon/cardRecharge",
                params: {
                    couponNo: "@couponNo",
                    rechargePrice: "@rechargePrice"
                }
            },
            exchangeCard: {
                method: "GET",
                url: "/coupon/exchangeCard",
                params: {
                    oldCouponNo: "@oldCouponNo",
                    oldPassword: "@oldPassword",
                    newCouponNo: "@newCouponNo",
                    newPassword: "@newPassword"
                }
            }
        });
    }]);

    ngREST.factory("CouponActivityAPI", ["$resource", function ($resource) {
        return $resource("/couponActivity/:id", {id: "@id"}, {
            query: {isArray: false},
            getCouponAddressList: {
                method: "GET",
                url: "/couponActivity/couponActivityAddress"
            },
            getCouponActivityAddress: {
                method: "GET",
                url: "/couponActivity/getCouponActivityAddress"
            }
        });
    }]);
    ngREST.factory("ActivityAPI", ["$resource", function ($resource) {
        return $resource("/activity/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            getChoseItemList: {method: "GET", url: "/activity/getChoseItemList", isArray: true}
        });
    }]);

    ngREST.factory("ContractAPI", ["$resource", function ($resource) {
        return $resource("/contract/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("ContractItemAPI", ["$resource", function ($resource) {
        return $resource("/contractItem/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("DictionaryGroupAPI", ["$resource", function ($resource) {
        return $resource("/dictionary/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("DictionaryAPI", ["$resource", function ($resource) {
        return $resource("/dictionary/:group_id/child/:id", {id: "@id", group_id: "@sysDictionaryId"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("OrderAPI", ["$resource", function ($resource) {
        return $resource("/order/:id", {id: "@id"}, {
            query: {isArray: false},
            batch: {
                method: "POST",
                url: "/order/batch"
            },
            update: {method: "PUT", params: {id: "@id"}},
            cancel: {method: "DELETE", url: "/order/:id"},
            getItemList: {
                method: "GET",
                url: "/order/:id/item",
                params: {id: "@id"}
            },
            updateTotalBox: {
                method: "POST",
                url: "/order/updateTotalBox",
                params: {orderNo: "@orderNo", totalBox: "@totalBox"}
            },
            auditTwoSend: {method: "POST", url: "/order/:id/twoSend", params: {id: "@id", auditStatusCd: "@auditStatusCd"}}
        });
    }]);

    ngREST.factory("ReportOrderAPI", ["$resource", function ($resource) {
        return $resource("/report/:id", {id: "@id"}, {
            report: {
                method: "GET",
                url: "/report/order",
                responseType: "blob"
            }
        });
    }]);

    ngREST.factory("ReturnOrderAPI", ["$resource", function ($resource) {
        return $resource("/returnOrder/:id", {id: "@id"}, {
            query: {isArray: false},
            getItemList: {
                method: "GET",
                url: "/returnOrder/:id/item",
                params: {id: "@id"}
            },
            auditReturnOrder: {
                method: "POST", url: "/returnOrder/:id/audit",
                params: {
                    id: "@id",
                    statusCd: "@statusCd"
                }
            },
            returnOrderShouhuo: {
                method: "PUT", url: "/returnOrder/returnOrderShouhuo", params: {
                    boxNo: "@boxNo"
                }
            },
            shouhuo: {
                method: "PUT", url: "/returnOrder/shouhuo", params: {
                    orderNo: "@orderNo"
                }
            }
        });
    }]);

    ngREST.factory("PackingAPI", ["$resource", function ($resource) {
        return $resource("/packing/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            getPackingOrderItemList: {method: "GET", url: "/packing/:id/order", isArray: false},
            getPackingOrderItemCount: {method: "GET", url: "/packing/order/item", isArray: false},
            getPackingRecordList: {method: "GET", url: "/packing/:id/record", isArray: false},
            getPackingOrderList: {method: "GET", url: "/packing/order", isArray: false},
            removePackingRecord: {method: "DELETE", url: "/packing/:id/record/:recordId"},
            getPackingTaskList: {method: "GET", url: "/packing/task", isArray: false},
            bindPackingTask: {method: "PUT", url: "/packing/task/bind"},
            unbindPackingTask: {
                method: "PUT", url: "/packing/task/unbind", params: {
                    orderNos: "@orderNos"
                }
            },
            scanItem: {method: "POST", url: "/packing/:id/record"},
            autoScan: {
                method: "POST", url: "/packing/auto",
                isArray: true,
                params: {orderNo: "@orderNo", packingId: "@packingId"}
            },
            printOrder: {
                method: "GET", url: "/packing/printOrder", params: {
                    orderId: "@orderId"
                }
            }
        });
    }]);

    ngREST.factory("PurchaseAPI", ["$resource", function ($resource) {
        return $resource("/purchase/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id", purchaseStatus: "@purchaseStatus"}}
        });
    }]);

    ngREST.factory("BasePlanAPI", ["$resource", function ($resource) {
        return $resource("/basePlan/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            getChoseItemList: {method: "GET", url: "/basePlan/getChosePlanItemList", isArray: true}
        });
    }]);

    ngREST.factory("PackageTaskAPI", ["$resource", function ($resource) {
        return $resource("/packageTask/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            packageWorking: {
                method: "PUT",
                url: "/packageTask/:id/packageWorking",
                params: {
                    id: "@id",
                    barCode: "@barCode"
                }
            }
        });
    }]);

    ngREST.factory("JobOperationLogAPI", ["$resource", function ($resource) {
        return $resource("/jobOperationLog/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("TablePlanAPI", ["$resource", function ($resource) {
        return $resource("/tablePlan/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            stop: {
                method: "PUT",
                url: "/tablePlan/:id/stop",
                params: {
                    id: "@id",
                    locked: "@locked"
                }
            },
            createPlanOrder: {
                method: "PUT",
                url: "/tablePlan/createPlanOrder"
            }
        });
    }]);

    ngREST.factory("PickAPI", ["$resource", function ($resource) {
        return $resource("/pick/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            settleAccounts: {method: "PUT", url: "/pick/settleAccounts"},
            pickCard: {method: "PUT", url: "/pick/pickCard"}
        });
    }]);

    ngREST.factory("ExpressAPI", ["$resource", function ($resource) {
        return $resource("/express/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}},
            bindExpress: {method: "PUT", url: "/express/order/bind"},
            unbindExpress: {
                method: "PUT", url: "/express/order/unbind", params: {
                    orderNos: "@orderNos"
                }
            },
            noScanOrder: {
                method: "GET", url: "/express/noScanOrder", params: {}
            },
            queryOrder: {
                method: "GET", url: "/express/order", params: {}
            },
            takeDelivery: {
                method: "PUT", url: "/express/order/takeDelivery", params: {
                    boxNo: "@boxNo"
                }
            },
            unTakeDelivery: {
                method: "PUT", url: "/express/order/untakeDelivery", params: {
                    orderNo: "@orderNo"
                }
            }
        });
    }]);

    ngREST.factory("ExpressMemberAPI", ["$resource", function ($resource) {
        return $resource("/expressMember/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("TradeRecordAPI", ["$resource", function ($resource) {
        return $resource("/tradeRecord/:id", {id: "@id"}, {
            query: {isArray: false},
            update: {method: "PUT", params: {id: "@id"}}
        });
    }]);

    ngREST.factory("ReceivingCodeRequestAPI", ["$resource", function ($resource) {
        return $resource("/receivingCodeRequest/:id", {id: "@id"}, {
            query: {isArray: false}
        });
    }]);
});
