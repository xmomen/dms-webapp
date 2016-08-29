/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "ItemAPI", "MemberAPI", "ItemCategoryAPI","$modal", "$ugDialog", "$state", "CouponAPI", "$modalMemberAdd", "$rootScope",
        function($scope, OrderAPI, ItemAPI, MemberAPI,ItemCategoryAPI, $modal, $ugDialog, $state, CouponAPI, $modalMemberAdd, $rootScope){
            $scope.setting = {
                disablesSpareName2:true,
                disablesSpareName:true,
                disablesChoseItems:false,
                disablesUpdateChoseItems:false,
                disablesChosePayMode:true
            };
            var resetOrder = function(){
                $scope.setting = {
                    disablesSpareName2:true,
                    disablesSpareName:true,
                    disablesChoseItems:false,
                    disablesUpdateChoseItems:false,
                    disablesChosePayMode:false
                };
                $scope.card = {};
                $scope.choseOrderItemList = [];
                $scope.totalItem = {};
                $scope.order = {
                    discount : 100,
                    discountPrice : 0,
                    orderSource:3,
                    appointmentTime : $scope.showTime(1)
                };
            };
            $scope.order = {
                orderType:1,
                discount:100,
                discountPrice : 0,
                paymentMode:5,
                orderSource:3
            };
            $scope.addOrderForm = {};
            $scope.errors = null;
            $scope.saveOrder = function(type){
                $scope.errors = null;
                $scope.order.orderItemList = [];
                for (var i = 0; i < $scope.choseOrderItemList.length; i++) {
                    var obj = $scope.choseOrderItemList[i];
                    $scope.order.orderItemList.push({
                        orderItemId:obj.id,
                        itemQty: obj.itemQty
                    });
                }
                if($scope.addOrderForm.validator.form()){
                    $scope.order.totalPrice = $scope.totalItem.totalPrice;
                    $ugDialog.confirm("是否提交订单").then(function(){
                        if($scope.order.batchNumber && type == 1){
                            OrderAPI.batch($scope.order, function(){
                                $ugDialog.alert("订单提交成功！");
                                $state.go("order");
                            }, function(data){
                                $scope.errors = data.data;
                            });
                        }else{
                            OrderAPI.save($scope.order, function(){
                                $ugDialog.alert("订单提交成功！");
                                $state.go("order");
                            }, function(data){
                                $scope.errors = data.data;
                            });
                        }
                    });
                }
            };
            $scope.saveBatchOrder = function(){
                var modalInstance = $modal.open({
                    templateUrl: 'batchOrder.html',
                    controller: ["$scope", "$modalInstance", function ($scope, $modalInstance) {
                        $scope.batchOrder = {};
                        $scope.batchCreateOrderForm = {};
                        $scope.saveBatchCreateOrderNumber = function(){
                            if($scope.batchCreateOrderForm.validator.form()){
                                $modalInstance.close($scope.batchOrder);
                            }
                        };
                        $scope.cancel = function () {
                            $modalInstance.dismiss('cancel');
                        }
                    }]
                });
                modalInstance.result.then(function (data) {
                    $scope.order.batchNumber = data.batchNumber;
                    $scope.saveOrder(1);
                });
            };
            $scope.changeOrderType = function(){
                if($scope.order.orderType == 1){
                    $scope.setting.disablesChosePayMode = true;
                    $scope.setting.disablesChoseItems = false;
                    $scope.setting.disablesUpdateChoseItems = false;
                    $scope.order.paymentMode = 5;
                }else if($scope.order.orderType == 2){
                    $scope.setting.disablesChosePayMode = true;
                    $scope.setting.disablesChoseItems = false;//劵的也要支持可以改产品
                    $scope.setting.disablesUpdateChoseItems = false;//劵的也要支持可以改产品
                    $scope.order.paymentMode = 7;
                }else{
                    $scope.setting.disablesChosePayMode = false;
                }
            };
            $scope.$watch('order.orderType', function(newVal, oldVal){
                if(oldVal != newVal){
                    resetOrder();
                    $scope.order.orderType = newVal;
                    $scope.changeOrderType(newVal);
                }
            });
            $scope.pageInfoSetting = {
                pageSize:10,
                pageNum:1
            };
            $scope.queryParam = {};
            $scope.getItemList = function(categoryName){
                var choseItemId = null;
                if($scope.choseOrderItemList && $scope.choseOrderItemList.length > 0){
                    choseItemId = [];
                    for (var i = 0; i < $scope.choseOrderItemList.length; i++) {
                        var obj = $scope.choseOrderItemList[i];
                        choseItemId.push(obj.id);
                    }
                }
                if(categoryName){
                    $scope.queryParam.keyword = categoryName;
                }
                ItemAPI.query({
                    companyId:$scope.order.companyId,
                    limit:$scope.pageInfoSetting.pageSize,
                    offset:$scope.pageInfoSetting.pageNum,
                    keyword:$scope.queryParam.keyword,
                    sellStatus:1,
                    exclude_ids:choseItemId
                }, function(data){
                    $scope.itemList = data.data;
                    $scope.pageInfoSetting = data.pageInfo;
                    $scope.pageInfoSetting.loadData = $scope.getItemList;
                });
            };
            var bindMember = function(){
                $modalMemberAdd.open({
                    currentMember:{
                        phoneNumber:$scope.order.phone
                    }
                }).result.then(function (data) {
                    $scope.queryMemberByPhoneNumber();
                });
            };
            $scope.editMember = function(){
                $modalMemberAdd.open({
                    currentMember:{
                        id: $scope.order.memberId,
                        couponNumber:$scope.card.cardNumber,
                        cdUserId:$scope.order.managerId,
                        cdCompanyId:$scope.order.companyId
                    }
                }).result.then(function (data) {
                    $scope.queryMemberByPhoneNumber();
                });
            };
            $scope.queryMemberByPhoneNumber = function(){
                if($scope.order.phone){
                    MemberAPI.query({
                        limit:1,
                        offset:1,
                        phoneNumber:$scope.order.phone
                    }, function(data){
                        if(data.data && data.data.length > 0){
                            var member = data.data[0];
                            setMemberInfo(member);
                        }else{
                            $ugDialog.confirm("未找到匹配手机号的客户，是否新增客户？").then(function(){
                                bindMember();
                            });
                        }
                    })
                }
            };
            var setMemberInfo = function(member){
                $scope.order.memberId = member.id;
                $scope.order.companyId = member.cdCompanyId;
                $scope.order.name = member.name;
                $scope.order.companyName = member.companyName;
                $scope.order.managerId = member.cdUserId;
                $scope.order.managerName = member.managerName;
                $scope.order.phone = member.phoneNumber;
                $scope.order.addressChose = 1;
                $scope.order.consigneeAddress = member.address;
                $scope.order.consigneeName = member.name;
                $scope.order.consigneePhone = member.phoneNumber;
                $scope.order.spareAddress = member.spareAddress;
                $scope.order.spareName = member.spareName;
                $scope.order.spareTel = member.spareTel;
                $scope.order.spareAddress2 = member.spareAddress2;
                $scope.order.spareName2 = member.spareName2;
                $scope.order.spareTel2 = member.spareTel2;
                if($scope.order.spareName){
                    $scope.setting.disablesSpareName = false;
                }
                if($scope.order.spareName2){
                    $scope.setting.disablesSpareName2 = false;
                }
            };
            $scope.card = {};
            $scope.getCouponByCouponNo = function(){
                if($scope.card.cardNumber){
                    CouponAPI.query({
                        limit:1,
                        offset:1,
                        couponNumber:$scope.card.cardNumber,
                        couponType:1
                    }, function(data){
                        if(data.data && data.data.length > 0){
                            var coupon = data.data[0];
                            $scope.card.id = coupon.id;
                            $scope.card.password = coupon.couponPassword;
                            $scope.card.amount = coupon.userPrice;
                            $scope.order.paymentRelationNo = coupon.couponNumber;
                            $scope.order.companyName = coupon.companyName;
                            $scope.order.companyId = coupon.companyId;
                            $scope.order.managerId = coupon.managerId;
                            $scope.order.managerName = coupon.managerName;
                            if(coupon.memberId){
                                MemberAPI.get({
                                    id:coupon.memberId
                                },function(data){
                                    setMemberInfo(data);
                                })
                            }
                            //存在是否绑定客户 没有则填写客户信息
                        }else{
                            $ugDialog.alert("卡号不存在！");
                        }
                    })
                }
            };
            $scope.choseOrderItem = [];
            $scope.coupon ={};
            $scope.getCouponByJuanNo = function(){
                if($scope.coupon.cardNumber){
                    CouponAPI.query({
                        limit:1,
                        offset:1,
                        couponNumber:$scope.coupon.cardNumber,
                        couponType:2
                    }, function(data){
                        if(data.data && data.data.length > 0){
                            var coupon = data.data[0];
                            $scope.coupon.id = coupon.id;
                            $scope.coupon.isUsed = coupon.isUsed;
                            $scope.order.companyName = coupon.companyName;
                            $scope.order.companyId = coupon.companyId;
                            $scope.order.managerId = coupon.managerId;
                            $scope.order.managerName = coupon.managerName;
                            $scope.order.paymentRelationNo = coupon.couponNumber;
                            $scope.order.juanValue = coupon.couponValue;//劵的话订单金额显示劵的面值
                            if(coupon.memberId){
                                MemberAPI.get({
                                    id:coupon.memberId
                                },function(data){
                                    setMemberInfo(data);
                                })
                            }
                            if(coupon.relationItemList){
                                var ids = [];
                                for (var i = 0; i < coupon.relationItemList.length; i++) {
                                    var obj = coupon.relationItemList[i];
                                    ids.push(obj.itemId);
                                }
                                //  固定产品
                                ItemAPI.query({
                                    companyId:$scope.order.companyId,
                                    limit:1000,
                                    offset:1,
                                    sellStatus:1,
                                    ids:ids
                                }, function(data){
                                    var itemList = data.data;
                                    for (var i = 0; i < itemList.length; i++) {
                                        var obj1 = itemList[i];
                                        for (var j = 0; j < coupon.relationItemList.length; j++) {
                                            var obj2 = coupon.relationItemList[j];
                                            if(obj1.id == obj2.itemId){
                                                var item = itemList[i];
                                                item.itemQty = obj2.itemNumber;
                                                item.orderItemId = item.id;
                                                $scope.choseOrderItemList.push(item);
                                                $scope.calTotalItem();
                                            }
                                        }
                                    }
                                });
                                if(coupon.isUsed==1){
                                    $ugDialog.warn("劵号已使用！");
                                    return;
                                }
                            }
                        }else{
                            $ugDialog.warn("劵号不存在！");
                        }
                    })
                }
            }


            $scope.choseOrderItemList = [];
            $scope.choseItem = function(index, number){
                var item = $scope.itemList[index];
                item.itemQty = number;
                item.orderItemId = item.id;
                $scope.choseOrderItemList.push(item);
                $scope.itemList.splice(index,1);
                $scope.calTotalItem();
                $scope.getItemList();
            };
            $scope.changeItemNumber = function(index, action){
                if(action == 1){
                    $scope.choseOrderItemList[index].itemQty++;
                }else if(action == 0){
                    $scope.choseOrderItemList[index].itemQty--;
                }
                $scope.calTotalItem();
            };
            $scope.removeItem = function(index){
                $scope.choseOrderItemList.splice(index,1);
                $scope.calTotalItem();
                $scope.getItemList();
            };
            $scope.removeAllItem = function(){
                $ugDialog.confirm("确认移除所有已选订购产品？").then(function(){
                    $scope.choseOrderItemList = [];
                    $scope.calTotalItem();
                    $scope.getItemList();
                });
            };
            $scope.openItemNumber = function (index) {
                var modalInstance = $modal.open({
                    templateUrl: 'addItemNumber.html',
                    resolve: {
                        CurrentOrderItem: function(){
                            return $scope.itemList[index];
                        }
                    },
                    controller: ["$scope", "CurrentOrderItem", "$modalInstance", function ($scope, CurrentOrderItem, $modalInstance) {
                        $scope.orderItem = {};
                        if(CurrentOrderItem){
                            $scope.orderItem = CurrentOrderItem;
                        }
                        $scope.addItemNumberForm = {};
                        $scope.saveItemNumber = function(){
                            if($scope.addItemNumberForm.validator.form()){
                                $modalInstance.close($scope.orderItem);
                            }
                        };
                        $scope.cancel = function () {
                            $modalInstance.dismiss('cancel');
                        };
                    }]
                });
                modalInstance.result.then(function (data) {
                    $scope.choseItem(index, parseFloat(data.number));
                });
            };
            $scope.totalItemPrice = function(obj){
                if(obj.discountPrice){
                    return obj.itemQty * obj.discountPrice;
                }
                return obj.itemQty * obj.sellPrice;
            };
            $scope.calTotalItem = function(){
                $scope.totalItem = {};
                var totalNumber = 0;
                var totalPrice = 0;
                for (var i = 0; i < $scope.choseOrderItemList.length; i++) {
                    var obj = $scope.choseOrderItemList[i];
                    totalNumber += obj.itemQty;
                    if(obj.discountPrice){
                        totalPrice += (obj.itemQty * obj.discountPrice);
                    }else{
                        totalPrice += (obj.itemQty * obj.sellPrice);
                    }
                }
                $scope.totalItem.totalNumber = totalNumber;
                //如果是劵的话 订单总金额就是劵面金额
                if($scope.order.orderType == 2){
                    $scope.totalItem.totalPrice = $scope.order.juanValue;
                    $scope.totalItem.totalPriceDiscount = $scope.order.juanValue;
                }
                else{
                    $scope.totalItem.totalPrice = totalPrice;
                    $scope.totalItem.totalPriceDiscount = totalPrice * $scope.order.discount / 100;
                }
            };
            $scope.discountTotalPrice = function(type){
                //如果是劵的话 不会打折
                if($scope.order.orderType != 2){
                    if(type == 1){
                        $scope.totalItem.totalPriceDiscount = $scope.totalItem.totalPrice * $scope.order.discount / 100;
                        $scope.order.discountPrice = $scope.totalItem.totalPrice - $scope.totalItem.totalPriceDiscount;
                    }else if(type == 2){
                        $scope.totalItem.totalPriceDiscount = $scope.totalItem.totalPrice - $scope.order.discountPrice;
                        $scope.order.discount = (1 - ($scope.order.discountPrice / $scope.totalItem.totalPrice).toFixed(2)) * 100;
                    }
                }
            }

            $scope.itemCategoryList = [];
            $scope.queryCategoryParam = {};
            $scope.getItemCategoryTree = function(){
                ItemCategoryAPI.query({
                    id:$scope.queryCategoryParam.id
                }, function(data){
                    $scope.itemCategoryList = data;
                    $rootScope.$broadcast("loadingTree");
                });
            };
            $scope.getItemCategoryTree();

            $scope.datepickerSetting = {
                datepickerPopupConfig:{
                    "current-text":"今天",
                    "clear-text":"清除",
                    "close-text":"关闭"
                },
                appointmentTime:{
                    opened:false
                }
            };
            $scope.open = function($event, index) {
                $event.preventDefault();
                $event.stopPropagation();
                if(index == 1){
                    $scope.datepickerSetting.appointmentTime.opened = true;
                }
            };
            $scope.addByTransDate = function(dateParameter, num) {
                var translateDate = "", dateString = "", monthString = "", dayString = "";
                translateDate = dateParameter.replace("-", "/").replace("-", "/");
                var newDate = new Date(translateDate);
                newDate = newDate.valueOf();
                newDate = newDate + num * 24 * 60 * 60 * 1000;
                newDate = new Date(newDate);
                //如果月份长度少于2，则前加 0 补位
                if ((newDate.getMonth() + 1).toString().length == 1) {
                    monthString = 0 + "" + (newDate.getMonth() + 1).toString();
                } else {
                    monthString = (newDate.getMonth() + 1).toString();
                }
                //如果天数长度少于2，则前加 0 补位
                if (newDate.getDate().toString().length == 1) {
                    dayString = 0 + "" + newDate.getDate().toString();
                } else {
                    dayString = newDate.getDate().toString();
                }
                dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;
                return dateString;
            }

            $scope.reduceByTransDate = function(dateParameter, num) {
                var translateDate = "", dateString = "", monthString = "", dayString = "";
                translateDate = dateParameter.replace("-", "/").replace("-", "/");
                var newDate = new Date(translateDate);
                newDate = newDate.valueOf();
                newDate = newDate - num * 24 * 60 * 60 * 1000;
                newDate = new Date(newDate);
                //如果月份长度少于2，则前加 0 补位
                if ((newDate.getMonth() + 1).toString().length == 1) {
                    monthString = 0 + "" + (newDate.getMonth() + 1).toString();
                } else {
                    monthString = (newDate.getMonth() + 1).toString();
                }
                //如果天数长度少于2，则前加 0 补位
                if (newDate.getDate().toString().length == 1) {
                    dayString = 0 + "" + newDate.getDate().toString();
                } else {
                    dayString = newDate.getDate().toString();
                }
                dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;
                return dateString;
            }

            //得到日期  主方法
            $scope.showTime = function(pdVal) {
                var trans_day = "";
                var cur_date = new Date();
                var cur_year = new Date().getFullYear();
                var cur_month = cur_date.getMonth() + 1;
                var real_date = cur_date.getDate();
                cur_month = cur_month > 9 ? cur_month : ("0" + cur_month);
                real_date = real_date > 9 ? real_date : ("0" + real_date);
                eT = cur_year + "-" + cur_month + "-" + real_date;
                if (pdVal == 1) {
                    trans_day = $scope.addByTransDate(eT, 1);
                }
                else if (pdVal == -1) {
                    trans_day = $scope.reduceByTransDate(eT, 1);
                }
                else {
                    trans_day = eT;
                }
                //处理
                return trans_day;
            }

            $scope.order.appointmentTime = $scope.showTime(1);

    }];
});
