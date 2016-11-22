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
                isUseful:3,
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
                locked: true
            },function(data){
                $scope.getCouponList();
                $scope.getCouponAuditList();
            });
        };

        $scope.batchUsefulCoupon = function(){
            var ids = [];
            for(var i in $scope.chooseCoupon){
                ids.push($scope.chooseCoupon[i].id);
            }
            if(ids.length == 0){
                $ugDialog.warn("请选择卡劵进行批量激活！");
                return false;
            }
            CouponAPI.batchAudit({
                ids:ids,
                locked:true
            },function(data){
                $scope.getCouponList();
                $scope.getCouponAuditList();
            });
        }
        //已审核列表
        $scope.pageInfoAuditSetting = [];
        $scope.pageInfoAuditSetting = {
            pageSize:50,
            pageNum:1
        };

        $scope.currentDate = function(){
            var myDate = new Date();
            var fullYear = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
            var month = myDate.getMonth() + 1;       //获取当前月份(0-11,0代表1月)
            if(month < 10){
                month = '0'+month;
            }
            var date = myDate.getDate();        //获取当前日(1-31)
            if(date < 10){
                date = '0'+date;
            }
            return fullYear+"-"+month+"-"+date;
        }

        $scope.datepickerSetting = {
            datepickerPopupConfig:{
                "current-text":"今天",
                "clear-text":"清除",
                "close-text":"关闭"
            },
            auditDateStart:{
                opened:false
            },
            auditDateEnd:{
                opened:false
            }
        };
        $scope.openDate = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 0){
                $scope.datepickerSetting.auditDateStart.opened = true;
            }else if(index == 1){
                $scope.datepickerSetting.auditDateEnd.opened = true;
            }
        };

        $scope.queryParamAudit = {
            auditDateStart :$scope.currentDate(),
            auditDateEnd:$scope.currentDate()
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
                batch:$scope.queryParamAudit.batch,
                auditDateStart:$scope.queryParamAudit.auditDateStart,
                auditDateEnd:$scope.queryParamAudit.auditDateEnd
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
                locked:  false
            },function(data){
                $scope.getCouponList();
                $scope.getCouponAuditList();
            });
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
                receivedPrice : coupon.receivedPrice
            },function(){

            }, function(data){
                $scope.errors = data.data;
            })
        }
    }];
});