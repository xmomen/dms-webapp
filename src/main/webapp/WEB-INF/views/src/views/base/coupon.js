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
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCouponList = function(){
            CouponAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                couponCategoryId:$scope.queryParam.couponCategoryId
            }, function(data){
                $scope.couponList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCouponList;
            });
        };
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

        $scope.removeCoupon = function(coupon){
            $ugDialog.confirm("是否删除此卡券？").then(function(){
                CouponAPI.delete({
                    id: coupon.id
                }, function(){
                    $scope.getCouponList();
                });
            })
        };
        $scope.updateCoupon = function(coupon){
            $scope.open(coupon);
        };
        $scope.open = function (coupon) {
            var modalInstance = $modal.open({
                templateUrl: 'addCoupon.html',
                resolve: {
                    CurrentCoupon: function(){
                        return coupon;
                    }
                },
                controller: ["$scope", "CouponAPI","CouponCategoryAPI","CurrentCoupon", "$modalInstance", function ($scope, CouponAPI,CouponCategoryAPI, CurrentCoupon, $modalInstance) {
                    $scope.coupon = {
                        couponType : 1,
                        isUsed : 0,
                        isUseful : 0,
                        isGift : 0
                    };
                    $scope.ugSelect2Config = {};
                    if(CurrentCoupon){
                        $scope.coupon = CurrentCoupon;
                    }
                    $scope.getCategoryList = function(){
                        $scope.pageInfoSetting = {
                            pageSize:1000,
                            pageNum:1
                        };
                        $scope.queryParam = {};
                        $scope.categoryList = [];
                        CouponCategoryAPI.query({
                            limit:$scope.pageInfoSetting.pageSize,
                            offset:$scope.pageInfoSetting.pageNum,
                            categoryType :$scope.coupon.couponType
                        }, function(data){
                            $scope.categoryList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getCategoryList;
                            $scope.ugSelect2Config.initSelectData($scope.coupon.couponCategory);
                        });
                    }
                    $scope.getCategoryList();
                    $scope.datepickerSetting = {
                        datepickerPopupConfig:{
                            "current-text":"今天",
                            "clear-text":"清除",
                            "close-text":"关闭"
                        },
                        beginTime:{
                            opened:false
                        },
                        endTime:{
                            opened:false
                        }
                    };
                    $scope.open = function($event, index) {
                        $event.preventDefault();
                        $event.stopPropagation();
                        if(index == 0){
                            $scope.datepickerSetting.beginTime.opened = true;
                        }else if(index == 1){
                            $scope.datepickerSetting.endTime.opened = true;
                        }
                    };
                    $scope.errors = null;
                    $scope.addCouponForm = {};
                    $scope.saveCoupon = function(){
                        $scope.errors = null;
                        if($scope.addCouponForm.validator.form()){
                            if($scope.coupon.id){
                                CouponAPI.update($scope.coupon, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                CouponAPI.save($scope.coupon, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };

                    $scope.selectCategory = function(){
                        $scope.coupon.couponCategory = null;
                        if($scope.coupon.couponType == 2){
                             $scope.coupon.couponValue = 1;
                         }else{
                             $scope.coupon.couponValue = "";
                         }
                        $scope.getCategoryList();
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getCouponList();
            });
        };

        $scope.openSendCoupon = function(coupon){
            var modalInstance = $modal.open({
                templateUrl: 'sendOneCoupon.html',
                resolve: {
                    CurrentCoupon: function(){
                        return coupon;
                    }
                },
                controller: ["$scope", "CouponAPI","CurrentCoupon", "$modalInstance","CompanyAPI", function ($scope, CouponAPI, CurrentCoupon, $modalInstance,CompanyAPI) {
                    $scope.companyList = [];
                    $scope.pageInfoSetting = {
                        pageSize:1000,
                        pageNum:1
                    };
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
                    if(CurrentCoupon){
                        $scope.coupon = CurrentCoupon;
                    }
                    $scope.errors = null;
                    $scope.sendCouponForm = {};
                    $scope.sendOneCoupon = function() {
                        $scope.errors = null;
                        if ($scope.sendCouponForm.validator.form()) {
                            CouponAPI.sendOneCoupon({
                                couponNumber:coupon.couponNumber,
                                companyId:coupon.cdCompanyId,
                                customerMangerId:coupon.customerMangerId,
                                id:coupon.id
                            }, function () {
                                $modalInstance.close();
                            }, function (data) {
                                $scope.errors = data.data;
                            })
                        }
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };

                    $scope.changeCompany = function(id){
                        $scope.coupon.customerMangerId = "";
                        for(var i in $scope.companyList){
                           var company =  $scope.companyList[i]
                            if(company.id == parseInt(id)){
                                $scope.companyCustomerManagers =  company.companyCustomerManagers;
                            }
                        }
                    }
                }]
            });
            modalInstance.result.then(function () {
                $scope.getCouponList();
            });
        }

        $scope.openSendMoreCoupon = function(){
            var modalInstance = $modal.open({
                templateUrl: 'sendMoreCoupon.html',
                controller: ["$scope", "CouponAPI", "$modalInstance","CompanyAPI", function ($scope, CouponAPI, $modalInstance,CompanyAPI) {
                    $scope.ugSelect2Config = {};
                    $scope.getCategoryList = function(){
                        $scope.pageInfoSetting = {
                            pageSize:1000,
                            pageNum:1
                        };
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
                    $scope.pageCouponSetting = {
                        pageSize:30,
                        pageNum:1
                    };
                    $scope.queryParam = {};
                    $scope.getCouponList = function(){
                        CouponAPI.query({
                            limit:$scope.pageCouponSetting.pageSize,
                            offset:$scope.pageCouponSetting.pageNum,
                            keyword:$scope.queryParam.keyword,
                            isSend:0,
                            couponCategoryId:$scope.queryParam.couponCategoryId
                        }, function(data){
                            $scope.couponList = data.data;
                            $scope.pageCouponSetting = data.pageInfo;
                            $scope.pageCouponSetting.loadData = $scope.getCouponList;
                        });
                    };
                    $scope.getCouponList();
                    $scope.chooseCoupon = [];
                    $scope.chooseAllCheck = {};
                    $scope.checkedAllCoupon = function() {
                        if($scope.chooseAllCheck.isCheckCoupon == 0){
                            $scope.chooseCoupon.splice(0, $scope.chooseCoupon.length);
                            for (var i = 0; i < $scope.couponList.length; i++) {
                                var obj = $scope.couponList[i];
                                $scope.chooseCoupon.push(obj);
                            }
                        }else{
                            $scope.chooseCoupon.splice(0, $scope.chooseCoupon.length);
                        }
                        $scope.chooseCouponStr();
                    };

                    $scope.changeCouponList = function(){
                        if($scope.chooseCoupon.length == $scope.couponList.length){
                            $scope.isCheckCombine = 0;
                        }else{
                            $scope.isCheckCombine = 1;
                        }
                        $scope.chooseCouponStr();
                    };

                    //拼装卡号
                    $scope.chooseCouponStr = function(){
                        $scope.coupon.couponNumberList = "";
                        for(var i in $scope.chooseCoupon){
                            if( $scope.coupon.couponNumberList == ""){
                                $scope.coupon.couponNumberList = $scope.chooseCoupon[i].couponNumber;
                            }else{
                                $scope.coupon.couponNumberList += "," + $scope.chooseCoupon[i].couponNumber;
                            }

                        }
                    }

                    $scope.companyList = [];
                    $scope.pageInfoSetting = {
                        pageSize:1000,
                        pageNum:1
                    };
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
                    $scope.changeCompany = function(id){
                        $scope.coupon.customerMangerId = "";
                        for(var i in $scope.companyList){
                            var company =  $scope.companyList[i]
                            if(company.id == parseInt(id)){
                                $scope.companyCustomerManagers =  company.companyCustomerManagers;
                            }
                        }
                    }
                    $scope.coupon = {};
                    $scope.errors = null;
                    $scope.sendMoreCouponForm = {};
                    $scope.sendMoreCoupon = function() {
                        $scope.errors = null;
                        if ($scope.sendMoreCouponForm.validator.form()) {
                            CouponAPI.sendMoreCoupon({
                                couponNumberList:$scope.coupon.couponNumberList,
                                customerMangerId:$scope.coupon.customerMangerId,
                                companyId:$scope.coupon.cdCompanyId
                            }, function () {
                                $modalInstance.close();
                            }, function (data) {
                                $scope.errors = data.data;
                            })
                        }
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                size:"lg"
            });
            modalInstance.result.then(function () {
                $scope.getCouponList();
            });
        }

        $scope.getCouponList();
    }];
});