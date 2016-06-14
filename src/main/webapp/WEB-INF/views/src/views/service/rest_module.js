/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    var ngREST = angular.module("DMS.REST",["ngResource"]);
    ngREST.factory("UserAPI", ["$resource", function($resource){
        return $resource("/user/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            lock:{
                method:"PUT",
                url:"/user/:id/locked" ,
                params:{
                    id:"@id",
                    locked:"@locked"
                }
            },
            getPermissions: {
                url:"/user/permissions"
            },
            resetPassword: {
                url:"/account/resetPassword" ,
                method:"POST",
                params:{
                    current_password: "@current_password",
                    password:"@password"
                }
            },
            getCustomerManagerList:{method:"GET",url:"/customerManagerList",
                params:{userType:"@userType",keyword:"@keyword"},
                isArray:true}
        });
    }]);
    ngREST.factory("MessageAPI", ["$resource", function($resource){
        return $resource("/message/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            log:{
                isArray: true,
                method:"GET",
                url:"/message/:id/log" ,
                params:{id:"@id"}
            },
            retry:{
                url: "/message/retry",
                method:"POST" ,
                params: {messageId: "@messageId"}
            }
        });
    }]);
    ngREST.factory("UserGroupAPI", ["$resource", function($resource){
        return $resource("/group/:id",{id:'@id'},{
            query:{ isArray:false}
        });
    }]);
    ngREST.factory("UserGroupRelationAPI", ["$resource", function($resource){
        return $resource("/group/:id/user",{id:'@id',userId:'@userId'},{
            query:{ isArray:false},
            save:{
                method:"PUT",
                params: {
                    chose: "@chose",
                    userId: "@userId"
                }
            }
        });
    }]);
    ngREST.factory("GroupPermissionRelationAPI", ["$resource", function($resource){
        return $resource("/group/:id/permissions",{id:'@id'},{
            query:{ isArray:false},
            save:{
                method:"PUT",
                params: {
                    chose: "@chose",
                    permissionId: "@permissionId"
                }
            }
        });
    }]);
    ngREST.factory("PermissionAPI", ["$resource", function($resource){
        return $resource("/permission/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}}
        });
    }]);
    ngREST.factory("ScheduleAPI", ["$resource", function($resource){
        return $resource("/schedule/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}},
            getTemplates:{
                url : "/schedule/template",
                method:"GET" ,
                isArray: true
            }
        });
    }]);
    ngREST.factory("OrganizationAPI", ["$resource", function($resource){
        return $resource("/organization/:id",{id:'@id'},{
          //  query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}},
            bindUser:{
                url:"/organization/:id/user",
                method:"POST", params:{id:"@id",userIds:"@userIds"}
            },
            unBindUser:{
                url:"/organization/:id/user",
                method:"DELETE", params:{id:"@id",userIds:"@userIds"}
            }
        });
    }]);

    ngREST.factory("MemberAPI", ["$resource", function($resource){
        return $resource("/member/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("CompanyAPI", ["$resource", function($resource){
        return $resource("/company/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            getCompanyList:{method:"GET",url:"/companyList",isArray:true}
        });
    }]);

    ngREST.factory("ItemCategoryAPI", ["$resource", function($resource){
        return $resource("/itemCategory/:id", { id:"@id" }, {
            //  query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("ItemAPI", ["$resource", function($resource){
        return $resource("/item/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            getChildItemList:{method:"GET",url:"/getChildItem",isArray:true}
        });
    }]);

    ngREST.factory("CouponCategoryAPI", ["$resource", function($resource){
        return $resource("/couponCategory/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            getChoseItemList:{method:"GET",url:"/getChoseItemList",isArray:true}
        });
    }]);

    ngREST.factory("CouponAPI", ["$resource", function($resource){
        return $resource("/coupon/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            sendOneCoupon:{method:"GET",url:"/coupon/sendOneCoupon",params:{id:"@id",companyId:"@companyId",couponNumber:"@couponNumber"}},
            sendMoreCoupon:{method:"GET",url:"/coupon/sendMoreCoupon",params:{companyId:"@companyId",couponNumberList:"@couponNumberList"}},
            activityAddress:{method:"GET",url:"/coupon/activityAddress",params:{couponNumber:"@couponNumber",consignmentName:"@consignmentName",consignmentPhone:"@consignmentPhone",consignmentAddress:"@consignmentAddress"}},
            audit:{
                method:"PUT",
                    url:"/coupon/:id/audit" ,
                    params:{
                    id:"@id",
                    locked:"@locked"
                  }
            },
            overCoupon:{
                method:"PUT",
                url:"/coupon/:id/overCoupon" ,
                params:{
                    id:"@id",
                    isOver:"@isOver"
                }
            },
            returnCoupon:{
                method:"PUT",
                url:"/coupon/:id/returnCoupon" ,
                params:{
                    id:"@id"
                }
            },
            receivedPrice:{
                method:"GET",
                url:"/coupon/receivedPrice",
                params:{
                    couponId:"@couponId",
                    couponNumber:"@couponNumber",
                    receivedPrice:"@receivedPrice"
                }
            },
            readCard:{
                method:"GET",
                url:"/coupon/readCard",
                params:{
                    couponNo:"@couponNo",
                    password:"@password"
                }

            }
        });
    }]);

    ngREST.factory("ActivityAPI", ["$resource", function($resource){
        return $resource("/activity/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            getChoseItemList:{method:"GET",url:"/activity/getChoseItemList",isArray:true}
        });
    }]);

    ngREST.factory("ContractAPI", ["$resource", function($resource){
        return $resource("/contract/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("ContractItemAPI", ["$resource", function($resource){
        return $resource("/contractItem/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("DictionaryGroupAPI", ["$resource", function($resource){
        return $resource("/dictionary/:id", { id:"@id" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("DictionaryAPI", ["$resource", function($resource){
        return $resource("/dictionary/:group_id/child/:id", { id:"@id",group_id:"@sysDictionaryId" }, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("OrderAPI", ["$resource", function($resource){
        return $resource("/order/:id", { id:"@id"}, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("PackingAPI", ["$resource", function($resource){
        return $resource("/packing/:id", { id:"@id"}, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            getPackingOrderItemList:{method:"GET",url:"/packing/:id/order",isArray:false},
            getPackingRecordList:{method:"GET",url:"/packing/:id/record",isArray:false},
            getPackingOrderList:{method:"GET",url:"/packing/order",isArray:false},
            removePackingRecord:{method:"DELETE",url:"/packing/:id/record/:recordId"},
            getPackingTaskList:{method:"GET",url:"/packing/task",isArray:false},
            bindPackingTask:{method:"PUT",url:"/packing/task/bind"},
            unbindPackingTask:{method:"PUT",url:"/packing/task/unbind", params:{
                orderNos:"@orderNos"
            }},
            scanItem:{method:"POST",url:"/packing/:id/record"}
        });
    }]);

    ngREST.factory("PurchaseAPI", ["$resource", function($resource){
        return $resource("/purchase/:id", { id:"@id"}, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}}
        });
    }]);

    ngREST.factory("BasePlanAPI", ["$resource", function($resource){
        return $resource("/basePlan/:id", { id:"@id"}, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            getChoseItemList:{method:"GET",url:"/basePlan/getChosePlanItemList",isArray:true}
        });
    }]);

    ngREST.factory("PackageTaskAPI", ["$resource", function($resource){
        return $resource("/packageTask/:id", { id:"@id"}, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            packageWorking:{
                method:"PUT",
                url:"/packageTask/:id/packageWorking" ,
                params:{
                    id:"@id",
                    barCode:"@barCode"
                }
            }
        });
    }]);

    ngREST.factory("TablePlanAPI", ["$resource", function($resource){
        return $resource("/tablePlan/:id", { id:"@id"}, {
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            stop:{
                method:"PUT",
                url:"/tablePlan/:id/stop" ,
                params:{
                    id:"@id",
                    locked:"@locked"
                }
            }
        });
    }]);
});
