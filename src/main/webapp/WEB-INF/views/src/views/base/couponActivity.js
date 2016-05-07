/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI", "$modal", "$ugDialog", function($scope, CouponAPI, $modal, $ugDialog){
        $scope.couponList = [];
        $scope.pageInfoSetting = {
            pageSize:25,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCouponList = function(){
            CouponAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.couponList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCouponList;
            });
        };
        $scope.getCouponList();
        $scope.updateValue = function(coupon){
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
                consignmentAddress:coupon.consignmentAddress
            },function(){

            }, function(data){
                $scope.errors = data.data;
            })
        }
    }];
});