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
                keyword:$scope.queryParam.keyword,
                isUseful:0,
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