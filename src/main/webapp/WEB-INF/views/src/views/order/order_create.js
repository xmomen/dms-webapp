/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "ItemAPI", "MemberAPI", "ItemCategoryAPI","$modal", "$ugDialog", "$state", "CouponAPI", "$modalMemberAdd", "$rootScope",
        function($scope, OrderAPI, ItemAPI, MemberAPI,ItemCategoryAPI, $modal, $ugDialog, $state, CouponAPI, $modalMemberAdd, $rootScope){
        $scope.order = {
            orderType:0
        };
        $scope.addOrderForm = {};
        $scope.errors = null;
        $scope.saveOrder = function(){
            $scope.order.orderItemList = [];
            for (var i = 0; i < $scope.choseOrderItemList.length; i++) {
                var obj = $scope.choseOrderItemList[i];
                $scope.order.orderItemList.push({
                    orderItemId:obj.id,
                    itemQty: obj.itemQty
                });
            }
            OrderAPI.save($scope.order, function(){
                $ugDialog.alert("订单提交成功！");
                $state.go("order");
            }, function(data){
                $scope.errors = data.data;
            });
            //if($scope.addOrderForm.validator.form()){
            //}
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getItemList = function(categoryName){
            debugger
            var choseItemId = null;
            if($scope.choseOrderItemList && $scope.choseOrderItemList.length > 0){
                choseItemId = []
                for (var i = 0; i < $scope.choseOrderItemList.length; i++) {
                    var obj = $scope.choseOrderItemList[i];
                    choseItemId.push(obj.id);
                }
            }
            if(categoryName){
                $scope.queryParam.keyword = categoryName;
            }
            debugger
            ItemAPI.query({
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
                    couponNumber:$scope.card.cardNumber
                }
            }).result.then(function (data) {
                $scope.queryMemberByPhoneNumber();
            });
        };
        $scope.setting = {
            disablesSpareName2:true,
            disablesSpareName:true
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
                        $scope.order.memberId = member.id;
                        $scope.order.cdCompanyId = member.cdCompanyId;
                        $scope.order.name = member.name;
                        $scope.order.companyId = member.companyId;
                        $scope.order.companyName = member.companyName;
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
                    }else{
                        $ugDialog.confirm("未找到匹配手机号的客户，是否新增客户？").then(function(){
                            bindMember();
                        });
                    }
                })
            }
        };
        $scope.card = {};
        $scope.getCouponByCouponNo = function(){
            if($scope.card.cardNumber){
                CouponAPI.query({
                    limit:1,
                    offset:1,
                    couponNumber:$scope.card.cardNumber,
                    categoryType:1
                }, function(data){
                    if(data.data && data.data.length > 0){
                        var coupon = data.data[0];
                        $scope.card.id = coupon.id;
                        $scope.card.password = coupon.couponPassword;
                        $scope.card.amount = coupon.couponValue;
                        //存在是否绑定客户 没有则填写客户信息
                    }else{
                        $ugDialog.alert("卡号不存在！");
                    }
                })
            }
        };

        $scope.coupon ={};
        $scope.getCouponByJuanNo = function(){
            if($scope.coupon.cardNumber){
                CouponAPI.query({
                    limit:1,
                    offset:1,
                    couponNumber:$scope.coupon.cardNumber,
                    categoryType:2
                }, function(data){
                    if(data.data && data.data.length > 0){
                        var coupon = data.data[0];
                        $scope.coupon.id = coupon.id;
                        $scope.coupon.isUsed = coupon.isUsed;
                        if(coupon.isUsed==1){
                            $ugDialog.warn("劵号已使用！");
                            return
                        }
                        //存在是否绑定客户 没有则填写客户信息
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
        $scope.calTotalItem = function(){
            $scope.totalItem = {};
            var totalNumber = 0;
            var totalPrice = 0;
            for (var i = 0; i < $scope.choseOrderItemList.length; i++) {
                var obj = $scope.choseOrderItemList[i];
                totalNumber += obj.itemQty;
                totalPrice += (obj.itemQty * obj.sellPrice);
            }
            $scope.totalItem.totalNumber = totalNumber;
            $scope.totalItem.totalPrice = totalPrice;
            $scope.totalItem.totalPriceDiscount = totalPrice * $scope.order.discount / 100;
        };
//        $scope.getItemList();
        $scope.discountTotalPrice = function(){
            $scope.totalItem.totalPriceDiscount = $scope.totalItem.totalPrice * $scope.order.discount / 100;
        }
        $scope.itemCategoryList = [];
        $scope.queryCategoryParam = {};
        $scope.getItemCategoryTree = function(){
            ItemCategoryAPI.query({
                id:$scope.queryCategoryParam.id
            }, function(data){
                $scope.itemCategoryList = data;
                $rootScope.$broadcast("loadingTree");
                //loadScript("js/plugin/bootstraptree/bootstrap-tree.min.js");
            });
        };
        $scope.getItemCategoryTree()
        $scope.order = {};
        $scope.order.discount = 100;
        $scope.order.orderType = 1;

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