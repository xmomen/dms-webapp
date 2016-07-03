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
            CouponActivityAPI.query({
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
        $scope.updateValue = function(coupon){
            coupon.userPrice = coupon.couponValue;
            CouponAPI.update(coupon, function(){
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

        //填写送货地址
        $scope.openEditAddress = function(coupon){
            var modalInstance = $modal.open({
                templateUrl: 'editAddress.html',
                resolve: {
                    CurrentCoupon: function(){
                        return coupon;
                    }
                },
                controller: ["$scope", "CouponAPI","CurrentCoupon", "$modalInstance", function ($scope, CouponAPI, CurrentCoupon, $modalInstance) {
                    if(CurrentCoupon){
                        $scope.couponEdit = angular.copy(CurrentCoupon);
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
                    $scope.updateAddress = function(coupon) {
                        $scope.errors = null;
                        if ($scope.editAddressForm.validator.form()) {
                                CouponAPI.activityAddress({
                                    couponNumber:$scope.couponEdit.couponNumber,
                                    consignmentName:$scope.couponEdit.consignmentName,
                                    consignmentPhone:$scope.couponEdit.consignmentPhone,
                                    consignmentAddress:$scope.couponEdit.consignmentAddress,
                                    sendTime:$scope.couponEdit.sendTime
                                },function(){
                                    CurrentCoupon.couponNumber = $scope.couponEdit.couponNumber;
                                    CurrentCoupon.consignmentName = $scope.couponEdit.consignmentName;
                                    CurrentCoupon.consignmentPhone = $scope.couponEdit.consignmentPhone;
                                    CurrentCoupon.consignmentAddress = $scope.couponEdit.consignmentAddress;
                                    CurrentCoupon.sendTime = $scope.couponEdit.sendTime;
                                    $modalInstance.dismiss('cancel');
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                        }
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getCouponList();
            });
        }
    }];
});