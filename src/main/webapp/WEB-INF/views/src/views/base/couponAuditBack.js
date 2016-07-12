/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI", "$modal", "$ugDialog","CouponCategoryAPI","CompanyAPI","UserAPI", function($scope, CouponAPI, $modal, $ugDialog,CouponCategoryAPI,CompanyAPI,UserAPI){

        $scope.ugSelect2Config = {};
        $scope.getCategoryList = function(){
            $scope.pageInfoSetting = {
                pageSize:1000,
                pageNum:1
            };
            $scope.queryParam = {};
            $scope.categoryList = [];
            CouponCategoryAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.categoryList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCategoryList;
            });
        }
        $scope.getCategoryList();

        $scope.companyList = [];
        $scope.getCompanyList = function(){
            CompanyAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.companyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCompanyList;
            });
        };
        $scope.getCompanyList();

        $scope.companyCustomerManagers = [];
        $scope.getCustomerManagersList = function(){
            UserAPI.getCustomerManagerList({
                userType:"customer_manager"
            },function(data){
                $scope.companyCustomerManagers = data;
            });
        }
        $scope.getCustomerManagersList();
        $scope.changeCompany = function(id){
//            $scope.queryParam.customerMangerId = "";
//            for(var i in $scope.companyList){
//                var company =  $scope.companyList[i]
//                if(company.id == parseInt(id)){
//                    $scope.companyCustomerManagers =  company.companyCustomerManagers;
//                }
//            }
        }

        $scope.couponList = [];
        $scope.pageInfoNoAuditSetting = {
            pageSize:50,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.chooseCoupon = [];
        $scope.checkedAllCoupon = function() {
            if($scope.isCheckCoupon == 0){
                $scope.chooseCoupon.splice(0, $scope.chooseCoupon.length);
                for (var i = 0; i < $scope.couponList.length; i++) {
                    var obj = $scope.couponList[i];
                    $scope.chooseCoupon.push(obj);
                }
            }else{
                $scope.chooseCoupon.splice(0, $scope.chooseCoupon.length);
            }
        };

        $scope.changeCouponList = function(){
            if($scope.chooseCoupon.length == $scope.couponList.length){
                $scope.isCheckCombine = 0;
            }else{
                $scope.isCheckCombine = 1;
            }
        };

        $scope.getCouponList = function(){
            CouponAPI.query({
                limit:$scope.pageInfoNoAuditSetting.pageSize,
                offset:$scope.pageInfoNoAuditSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                isUseful:4,
                isSend:1,
                cdCompanyId:$scope.queryParam.cdCompanyId,
                customerMangerId:$scope.queryParam.customerMangerId,
                couponCategoryId:$scope.queryParam.couponCategoryId,
                batch:$scope.queryParam.batch
            }, function(data){
                $scope.couponList = data.data;
                $scope.pageInfoNoAuditSetting = data.pageInfo;
                $scope.pageInfoNoAuditSetting.loadData = $scope.getCouponList;
            });
        };
        $scope.getCouponList();
        $scope.audit = function(index){
            CouponAPI.audit({
                id: $scope.couponList[index].id,
                locked: $scope.couponList[index].isUseful == 1 ? true : false
            },function(data){
                $scope.getCouponList();
                $scope.getCouponAuditList();
            });
        };
        //已审核列表
        $scope.queryParamAudit = {}
        $scope.pageInfoAuditSetting = [];
        $scope.pageInfoAuditSetting = {
            pageSize:50,
            pageNum:1
        };
        $scope.getCouponAuditList = function(){
            CouponAPI.query({
                limit:$scope.pageInfoAuditSetting.pageSize,
                offset:$scope.pageInfoAuditSetting.pageNum,
                keyword:$scope.queryParamAudit.keyword,
                isUseful:1,
                isSend:1,
                isOver:0,
                cdCompanyId:$scope.queryParamAudit.cdCompanyId,
                customerMangerId:$scope.queryParamAudit.customerMangerId,
                couponCategoryId:$scope.queryParamAudit.couponCategoryId,
                batch:$scope.queryParamAudit.batch
            }, function(data){
                $scope.couponAuditList = data.data;
                $scope.pageInfoAuditSetting = data.pageInfo;
                $scope.pageInfoAuditSetting.loadData = $scope.getCouponAuditList;
            });
        };
        $scope.getCouponAuditList();

        $scope.noAudit = function(index){
            CouponAPI.audit({
                id: $scope.couponAuditList[index].id,
                locked: $scope.couponAuditList[index].isUseful == 1 ? true : false
            },function(data){
                $scope.getCouponList();
                $scope.getCouponAuditList();
            });
        };
        //退卡
        $scope.returnCoupon = function(coupon){
            $ugDialog.confirm("是否退卡/券？").then(function(){
                CouponAPI.returnCoupon({
                    id: coupon.id
                }, function(){
                    $ugDialog.alert("退卡成功");
                    $scope.getCouponList();
                });
            })
        };

        $scope.overCoupon = function(coupon){
            $ugDialog.confirm("确定卡完结，完结之后不能做任何修改？").then(function(){
                CouponAPI.overCoupon({
                    id: coupon.id,
                    isOver: 1
                },function(data){
                    $scope.getCouponAuditList();
                });
            })
        };

        $scope.receivedPrice = function(coupon){
            CouponAPI.receivedPrice({
                couponId : coupon.id,
                couponNumber : coupon.couponNumber,
                receivedPrice : coupon.receivedPrice,
                isAutoAudit :1
            },function(){

            }, function(data){
                $scope.errors = data.data;
            })
        }
    }];
});