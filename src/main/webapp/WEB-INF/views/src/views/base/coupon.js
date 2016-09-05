/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI", "$modal", "$ugDialog","CouponCategoryAPI", function($scope, CouponAPI, $modal, $ugDialog,CouponCategoryAPI){

        //asn导入
        $scope.fileUploadConfig = {
            'buttonText' : '导入卡劵',
            'uploader'    : '/coupon/importExcel',
            onUploadStart:function(file){

            },
            'onUploadSuccess':function(file,data,response){
                $ugDialog.alert("导入成功")
            }
        };


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
            pageSize:100,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCouponList = function(){
            CouponAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                couponCategoryId:$scope.queryParam.couponCategoryId,
                batch:$scope.queryParam.batch
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
                        paymentType :1,
                        isUsed : 0,
                        isUseful : 0,
                        isGift : 0
                    };
                    $scope.ugSelect2Config = {};
                    debugger;
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
                            $scope.ugSelect2Config.initSelectData($scope.coupon.couponCategoryId);
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
                        if($scope.coupon.paymentType == 2 && ($scope.coupon.couponValue == "" || $scope.coupon.couponValue == undefined)){
                            alert("请输入面值");
                            return false;
                        }
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
//                        $scope.coupon.couponValue = "";

                        if($scope.coupon.couponType == 2){
                             $scope.coupon.paymentType = 2;
                         }else{
                            $scope.coupon.userPrice = $scope.coupon.couponValue;
                        }
                        $scope.getCategoryList();
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getCouponList();
            });
        };

        <!--余额调整-->
        $scope.openUpdateUserPrice = function (coupon) {
            var modalInstance = $modal.open({
                templateUrl: 'updateUserPrice.html',
                resolve: {
                    CurrentCoupon: function(){
                        return coupon;
                    }
                },
                controller: ["$scope", "CouponAPI","CurrentCoupon",
                    "$modalInstance", function ($scope, CouponAPI, CurrentCoupon, $modalInstance) {
                        if(CurrentCoupon){
                            $scope.coupon = CurrentCoupon;
                        }
                        $scope.errors = null;
                        $scope.updateUserPriceFrom = {};
                        $scope.updateUserPrice = function(){
                            $scope.errors = null;
                            if($scope.updateUserPriceFrom.validator.form()){
                                CouponAPI.updateUserPrice(
                                {
                                    couponNo:coupon.couponNumber,
                                    updatePrice:coupon.updatePrice,
                                    remark:coupon.remark
                                },
                                    function(){
                                        $modalInstance.close();
                                    }, function(data){
                                        $scope.errors = data.data;
                                    })
                            }
                        };
                        $scope.cancel = function () {
                            $modalInstance.dismiss('cancel');
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
                                batch:coupon.batch,
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
                        pageSize:10,
                        pageNum:1
                    };
                    $scope.queryParam = {};
                    $scope.getCouponList = function(){
                        CouponAPI.query({
                            limit:$scope.pageCouponSetting.pageSize,
                            offset:$scope.pageCouponSetting.pageNum,
                            keyword:$scope.queryParam.keyword,
                            isSend:0,
                            couponCategoryId:$scope.queryParam.couponCategoryId,
                            batch:$scope.queryParam.batch
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
                        $scope.sendMorecouponLength = $scope.chooseCoupon.length;
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
                                companyId:$scope.coupon.cdCompanyId,
                                batch:$scope.coupon.batch
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

        //更改客户经理和公司
        $scope.updateBatchCouponModel = function(){
            var modalInstance = $modal.open({
                templateUrl: 'updateBatchCouponModel.html',
                controller: ["$scope", "CouponAPI", "$modalInstance","CompanyAPI", function ($scope, CouponAPI, $modalInstance,CompanyAPI) {


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
                        $scope.couponLength = $scope.chooseCoupon.length;
                        $scope.coupon.couponNumberList = "";
                        for(var i in $scope.chooseCoupon){
                            if( $scope.coupon.couponNumberList == ""){
                                $scope.coupon.couponNumberList = $scope.chooseCoupon[i].couponNumber;
                            }else{
                                $scope.coupon.couponNumberList += "," + $scope.chooseCoupon[i].couponNumber;
                            }

                        }
                    }



                    $scope.couponList = [];
                    $scope.pageCouponSetting = {
                        pageSize:10,
                        pageNum:1
                    };
                    $scope.queryParam = {};
                    $scope.getCouponList = function(){
                        CouponAPI.query({
                            limit:$scope.pageCouponSetting.pageSize,
                            offset:$scope.pageCouponSetting.pageNum,
                            keyword:$scope.queryParam.keyword,
                            isSend:1,
                            couponCategoryId:$scope.queryParam.couponCategoryId,
                            batch:$scope.queryParam.batch
                        }, function(data){
                            $scope.couponList = data.data;
                            $scope.pageCouponSetting = data.pageInfo;
                            $scope.pageCouponSetting.loadData = $scope.getCouponList;
                        });
                    };
                    $scope.coupon = {};
                    $scope.errors = null;
                    $scope.updateBatchCouponFrom = {};
                    $scope.updateBatchCoupon = function() {
                        $scope.errors = null;
                        if ($scope.updateBatchCouponFrom.validator.form()) {
                            CouponAPI.updateBatchCoupon({
                                customerMangerId:$scope.coupon.customerMangerId,
                                companyId:$scope.coupon.cdCompanyId,
                                couponNumberList:$scope.coupon.couponNumberList
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


        $scope.updateBatchCouponTypeModel = function(){
            var modalInstance = $modal.open({
                templateUrl: 'updateBatchCouponTypeModel.html',
                controller: ["$scope", "CouponAPI", "$modalInstance","CouponCategoryAPI", function ($scope, CouponAPI, $modalInstance,CouponCategoryAPI) {
                    $scope.pageInfoSetting = {
                        pageSize:1000,
                        pageNum:1
                    };
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
                            categoryType :1
                        }, function(data){
                            $scope.categoryList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getCategoryList;
                        });
                    }
                    $scope.getCategoryList();
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
                        $scope.couponLength = $scope.chooseCoupon.length;
                        $scope.coupon.couponNumberList = "";
                        for(var i in $scope.chooseCoupon){
                            if( $scope.coupon.couponNumberList == ""){
                                $scope.coupon.couponNumberList = $scope.chooseCoupon[i].couponNumber;
                            }else{
                                $scope.coupon.couponNumberList += "," + $scope.chooseCoupon[i].couponNumber;
                            }

                        }
                    }



                    $scope.couponList = [];
                    $scope.pageCouponSetting = {
                        pageSize:10,
                        pageNum:1
                    };
                    $scope.queryParam = {};
                    $scope.getCouponList = function(){
                        CouponAPI.query({
                            limit:$scope.pageCouponSetting.pageSize,
                            offset:$scope.pageCouponSetting.pageNum,
                            keyword:$scope.queryParam.keyword,
                            batch:$scope.queryParam.batch
                        }, function(data){
                            $scope.couponList = data.data;
                            $scope.pageCouponSetting = data.pageInfo;
                            $scope.pageCouponSetting.loadData = $scope.getCouponList;
                        });
                    };
                    $scope.coupon = {};
                    $scope.errors = null;
                    $scope.updateBatchCouponTypeFrom = {};
                    $scope.updateBatchCoupon = function() {
                        $scope.errors = null;
                        if ($scope.updateBatchCouponTypeFrom.validator.form()) {
                            CouponAPI.updateBatchCouponType({
                                couponCategoryId:$scope.coupon.couponCategoryId,
                                couponNumberList:$scope.coupon.couponNumberList
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

        //写卡
        $scope.writeCard = function(coupon){
            var strls = "";
            var errorno = "";
            var BLOCK0_EN = 0x01;//读第一块的(16个字节)
            var BLOCK1_EN = 0x02;//读第二块的(16个字节)
            var BLOCK2_EN = 0x04;//读第四块的(16个字节)

            var EXTERNKEY = 0x10;//用明码认证密码,产品开发完成后，建议把密码放到设备的只写区，然后用该区的密码后台认证，这样谁都不知道密码是多少，需要这方面支持请联系
            //指定控制字
            var myctrlword=BLOCK0_EN + BLOCK1_EN + BLOCK2_EN + EXTERNKEY;
            //指定区号
            var myareano = 8; //指定为第8区
            //批定密码模式
            var authmode = 1; //大于0表示用A密码认证，推荐用A密码认证

            //指定序列号，未知卡序列号时可指定为8个0
            var  mypiccserial="00000000";

            //指定密码，以下密码为厂家出厂密码
            var mypicckey = "ffffffffffff";

            //指定写卡内容，长度为48个字节，其中每个字节以两个字符表示为十六进制数
            debugger;
            var cardNoValue = coupon.couponNumber;
            while(cardNoValue.length < 32){
                cardNoValue = cardNoValue + 'F';
            }
            var passwordValue = coupon.couponPassword;
            while(passwordValue.length < 32){
                passwordValue = passwordValue + 'F';
            }
            piccdata0_2 = cardNoValue+passwordValue+'00000000000000000000000000000000';
            strls=IcCardReader.piccwriteex(myctrlword, mypiccserial,myareano,authmode,mypicckey,piccdata0_2);
            errorno = strls.substr(0,4);
            switch(errorno)
            {
                case "ER08":
                    alert("寻不到卡");
                    break;
                case "ER09":
                    alert("寻不到卡");
                    break;
                case "ER10":
                    alert("寻不到卡");
                    break;
                case "ER11":
                    alert("密码认证错误");
                    break;
                case "ER12":
                    alert("密码认证错误");
                    break;
                case "ER13":
                    alert("读卡错误");
                    break;

                case "ER14":
                    alert("写卡错误");
                    break;

                case "ER21":
                    alert("没找到动态库");
                    break;

                case "ER22":
                    alert("动态库或驱动程序异常");
                    break;

                case "ER23":
                    alert("读卡器未插上或动态库或驱动程序异常");
                    break;
                case "ER24":
                    alert("操作超时，一般是动态库没有反应");
                    break;
                case "ER25":
                    alert("发送字数不够");
                    break;
                case "ER26":
                    alert("发送的CRC错");
                    break;
                case "ER27":
                    alert("接收的字数不够");
                    break;
                case "ER28":
                    alert("接收的CRC错");
                    break;
                case "ER29":
                    alert("函数输入参数格式错误,请仔细查看");
                    break;
                default ://写卡成功,其中ER00表示完全成功,ER01表示完全没写到卡数据，ER02表示仅写该卡的第一块成功,，ER02表示仅写该卡的第一二块成功，这是刷卡太快原因
                    IcCardReader.pcdbeep(300);//100表示响100毫秒
                    alert("写卡成功");
                    break;
            }
        }

        $scope.downImportTemplate = function(){
            window.location.href = "/coupon/downCouponImportTemplate";
        }
    }];
});
