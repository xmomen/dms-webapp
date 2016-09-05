/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI", "$modal", "$ugDialog","CouponCategoryAPI","CouponActivityAPI",function($scope, CouponAPI, $modal, $ugDialog,CouponCategoryAPI,CouponActivityAPI){

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
            CouponActivityAPI.getCouponAddressList({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                isUseful:0,
                isSend:1,
                couponCategoryId:$scope.queryParam.couponCategoryId,
                batch:$scope.queryParam.batch,
                couponType:1 //只有卡回款
            }, function(data){
                $scope.couponList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCouponList;
            });
        };
        $scope.getCouponList();

        //填写送货地址
        $scope.openEditAddress = function(coupon){
            var modalInstance = $modal.open({
                templateUrl: 'editAddress.html',
                resolve: {
                    CurrentCoupon: function(){
                        return coupon;
                    }
                },
                controller: ["$scope", "CouponAPI","CurrentCoupon", "$modalInstance","CouponActivityAPI", function ($scope, CouponAPI, CurrentCoupon, $modalInstance,CouponActivityAPI) {

                    if(CurrentCoupon){
                        $scope.couponEdit = angular.copy(CurrentCoupon);
                    }
                    $scope.couponEditList = [];
                    //查询活动送货地址
//                    CouponActivityAPI.getCouponActivityAddress({
//                        couponNumber : CurrentCoupon.couponNumber
//                    }, function(data){
//                        $scope.couponEditList.push(data.data);
//                    })
                    var userPrice = $scope.couponEdit.userPrice;
                    var lowestPrice = $scope.couponEdit.lowestPrice;
                    $scope.count = userPrice / lowestPrice;
                    if($scope.count == 1){
                        $scope.couponEdit.sendCount = 1;
                    }
                    $scope.couponEditList.push($scope.couponEdit);

                    $scope.addAddressList = function(){
                        $scope.couponEditList.push({});
                    }

                    $scope.datepickerSetting = {
                        datepickerPopupConfig:{
                            "current-text":"今天",
                            "clear-text":"清除",
                            "close-text":"关闭"
                        },
                        beginTime:{
                            opened:false
                        }
                    };
                    $scope.open = function($event, index) {
                        $event.preventDefault();
                        $event.stopPropagation();
                        if(index == 0){
                            $scope.datepickerSetting.beginTime.opened = true;
                        }
                    };
                    $scope.errors = null;
                    $scope.editAddressForm = {};
                    $scope.updateAddress = function() {
                        var sendCount = 0;
                        for(var index in $scope.couponEditList){

                            var couponEdit = $scope.couponEditList[index];
                            sendCount += parseInt(couponEdit.sendCount);
                        }
                       if(sendCount > parseInt($scope.count)){
                           $ugDialog.warn("不能超过限定的份数");
                           return ;
                       }
                        $scope.errors = null;
                        if ($scope.editAddressForm.validator.form()) {
                                CouponAPI.activityAddress({
                                    couponNumber : CurrentCoupon.couponNumber,
                                    couponActivityAddressList : $scope.couponEditList
                                },function(){
                                    $modalInstance.dismiss('cancel');
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                        }
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                size : 'lg'
            });
            modalInstance.result.then(function () {
                $scope.getCouponList();
            });
        }
    }];
});