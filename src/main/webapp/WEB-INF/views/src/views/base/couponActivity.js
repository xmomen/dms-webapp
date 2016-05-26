/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI", "$modal", "$ugDialog","CouponCategoryAPI", function($scope, CouponAPI, $modal, $ugDialog,CouponCategoryAPI){

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

        $scope.couponList = [];
        $scope.pageInfoSetting = {
            pageSize:50,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCouponList = function(){
            CouponAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                isUseful:0,
                isSend:1,
                couponCategoryId:$scope.queryParam.couponCategoryId
            }, function(data){
                $scope.couponList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCouponList;
            });
        };
        $scope.getCouponList();
        $scope.updateValue = function(coupon){
            coupon.userPrice = coupon.couponValue;
            CouponAPI.update(coupon, function(){
            }, function(data){
                $scope.errors = data.data;
            })
        }

        $scope.updateAddress = function(coupon){
            CouponAPI.activityAddress({
                couponNumber:coupon.couponNumber,
                consignmentName:coupon.consignmentName,
                consignmentPhone:coupon.consignmentPhone,
                consignmentAddress:coupon.consignmentAddress,
                sendTime:coupon.sendTime
            },function(){

            }, function(data){
                $scope.errors = data.data;
            })
        }

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
    }];
});