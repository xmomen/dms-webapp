/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI", "$modal", "$ugDialog", function($scope, CouponAPI, $modal, $ugDialog){
        $scope.couponList = [];
        $scope.pageInfoSetting = {
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
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                isUseful:3,
                isSend:1
            }, function(data){
                $scope.couponList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCouponList;
            });
        };
        $scope.getCouponList();
        $scope.audit = function(index){
            CouponAPI.audit({
                id: $scope.couponList[index].id,
                locked: $scope.couponList[index].isUseful == 1 ? true : false
            },function(data){
                $scope.getCouponList();
            });
        };
    }];
});