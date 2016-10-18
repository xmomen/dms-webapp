/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackingAPI", "OrderAPI", "$modal", "$ugDialog", "$q", "DictionaryAPI","UserAPI", function($scope, PackingAPI, OrderAPI, $modal, $ugDialog, $q, DictionaryAPI,UserAPI){

        $scope.managers = [];
        $scope.getCustomerManagersList = function(){
            UserAPI.getCustomerManagerList({
                userType:"customer_manager"
            },function(data){
                $scope.managers = data;
            });
        }
        $scope.getCustomerManagersList();

        $scope.pageSetting = {
            boxNumLimit:5,
            showPackingTask: false,
            disabledScan : false
        };
        $scope.packingList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };

        $scope.currentDate = function(){
            var myDate = new Date();
            var fullYear = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
            var month = myDate.getMonth() + 1;       //获取当前月份(0-11,0代表1月)
            if(month < 10){
                month = '0'+month;
            }
            var date = myDate.getDate();        //获取当前日(1-31)
            if(date < 10){
                date = '0'+date;
            }
            return fullYear+"-"+month+"-"+date;
        }

        $scope.datepickerSetting = {
            datepickerPopupConfig:{
                "current-text":"今天",
                "clear-text":"清除",
                "close-text":"关闭"
            },
            packingTaskCreateTimeStart:{
                opened:false
            },
            packingTaskCreateTimeEnd:{
                opened:false
            }
        };
        $scope.open = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 0){
                $scope.datepickerSetting.packingTaskCreateTimeStart.opened = true;
            }else if(index == 1){
                $scope.datepickerSetting.packingTaskCreateTimeEnd.opened = true;
            }
        };

        $scope.queryParam = {
            packingTaskCreateTimeStart :$scope.currentDate(),
            packingTaskCreateTimeEnd:$scope.currentDate()
        };

        $scope.orderList = [];
        $scope.getOrderList = function(){
            PackingAPI.getPackingOrderList({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.orderKeyword,
                isHasPackingTaskUserId:true,
                packingTaskCreateTimeStart:$scope.queryParam.packingTaskCreateTimeStart,
                packingTaskCreateTimeEnd:$scope.queryParam.packingTaskCreateTimeEnd,
                packingTaskStatus:$scope.queryParam.packingTaskStatus,
                consigneeName:$scope.queryParam.consigneeName,
                managerId:$scope.queryParam.managerId
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.choosePackingOrders = [];
        $scope.checkedAll = function() {
            if($scope.isCheckOrder == 0){
                $scope.choosePackingOrders.splice(0, $scope.choosePackingOrders.length);
                for (var i = 0; i < $scope.orderList.length; i++) {
                    var obj = $scope.orderList[i];
                    if(obj.packingTaskStatus == 0 || obj.packingTaskStatus == 1) {
                        $scope.choosePackingOrders.push(obj);
                    }
                }
            }else{
                $scope.choosePackingOrders.splice(0, $scope.choosePackingOrders.length);
            }
        };

        $scope.changePackingOrderList = function(){
            var j = 0;
            for (var i = 0; i < $scope.orderList.length; i++) {
                var obj = $scope.orderList[i];
                if(obj.packingTaskStatus != 2){
                    j++;
                }
            }
            if($scope.choosePackingOrders.length == j && j > 0){
                $scope.isCheckOrder = 0;
            }else{
                $scope.isCheckOrder = 1;
            }
        };

        $scope.startPacking = function(){
            //如果没有选择 则默认一个一个装 取其中一个未完成任务进行装箱
            if($scope.choosePackingOrders.length == 0){
                for (var i = 0; i < $scope.orderList.length; i++) {
                    var obj = $scope.orderList[i];
                    if(obj.packingTaskStatus == 0 || obj.packingTaskStatus == 1){
                        $scope.choosePackingOrders.push(obj);
                        break;
                    }
                }
            }
            if($scope.choosePackingOrders.length == 0){
                $ugDialog.alert("无未完成的装箱任务！");
                return;
            }
            $scope.currentPackingBoxList = [];
            for (var i = 0; i < $scope.choosePackingOrders.length; i++) {
                var obj = angular.copy($scope.choosePackingOrders[i]);
                $scope.currentPackingBoxList.push(obj);
            }
            $scope.pageSetting.showPackingTask = true;
            $scope.getPackingOrderCountItemList();
            $scope.choseOrder2CurrentPackingList(obj);
        };

        //下一个任务
        $scope.nextPackingTask =function(){
            var currentPackingTask =  $scope.choosePackingOrders[0];
            $scope.choosePackingOrders = [];
            $scope.currentPackingBoxList = [];
            for (var i = 0; i < $scope.orderList.length; i++) {
                var obj = $scope.orderList[i];
                if((obj.packingTaskStatus == 0 || obj.packingTaskStatus == 1) && currentPackingTask.orderNo != obj.orderNo){
                    $scope.choosePackingOrders.push(obj);
                    break;
                }
            }
            for (var i = 0; i < $scope.choosePackingOrders.length; i++) {
                var obj = angular.copy($scope.choosePackingOrders[i]);
                $scope.currentPackingBoxList.push(obj);
            }
            $scope.pageSetting.showPackingTask = true;
            $scope.getPackingOrderCountItemList();
            $scope.choseOrder2CurrentPackingList(obj);
        }

        $scope.getPackingOrderCountItemList = function(){
            var orderNos = [];
            for (var i = 0; i < $scope.choosePackingOrders.length; i++) {
                var obj = $scope.choosePackingOrders[i];
                orderNos.push(obj.orderNo);
            }
            PackingAPI.getPackingOrderItemCount({
                orderNos:orderNos,
                limit: 100,
                offset: 1
            }, function(data){
                $scope.packingOrderCountItemList = data.data;
                $scope.orderItemPageInfoSetting = data.pageInfo;
                $scope.orderItemPageInfoSetting.loadData = $scope.getPackingOrderCountItemList;
            });
        };
        $scope.currentPackingBoxList = [];
        var setPacking = function(index){
            PackingAPI.query({
                limit:100,
                offset:1,
                orderNo: $scope.currentPackingBoxList[index].orderNo
            }, function(data){
                if(data.data == null || data.data.length == 0){
                    PackingAPI.save({
                        orderNo: $scope.currentPackingBoxList[index].orderNo
                    }, function(result){
                        $scope.currentPackingBoxList[index].currentPacking = result;
                    })
                }else{
                    $scope.currentPackingBoxList[index].currentPacking = data.data[data.data.length - 1];
                }
            });
        };
        $scope.choseOrder2CurrentPackingList = function(order){
            for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                setPacking(i);
            }
        };
        $scope.finishOrderPacking = function(){
            $scope.pageSetting.showPackingTask = false;
            $scope.choosePackingOrders = [];
            $scope.currentPackingBoxList = [];
        };
        $scope.isPackingOrder = function(index){
            for (var i = 0; i < $scope.choosePackingOrders.length; i++) {
                var obj = $scope.choosePackingOrders[i];
                if(obj.orderNo == $scope.orderList[index].orderNo){
                    return true;
                }
            }
            return false;
        };
        $scope.changeBox = function(index){
            PackingAPI.save({
                orderNo:$scope.currentPackingBoxList[index].orderNo
            }, function(data){
                $scope.currentPackingBoxList[index].currentPacking = data;
            })
        };
        $scope.packingHistory = [];
        $scope.scanItemForm = {};
        $scope.item = {};
        $scope.showPutBoxNum = null;

        //扫描UPC码
        $scope.scanItemEvent = function(e){
            var keycode = window.event?e.keyCode:e.which;
            if(keycode==13){
                $scope.scanItem();
                //清空UPC码
                $scope.item.upc = "";
            }
        }

        $scope.scanItem = function(){
            var ok = false;
            for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                var obj1 = $scope.currentPackingBoxList[i];
                if(obj1.orderNo){
                    ok = true;
                }
            }
            if(!ok){
                $ugDialog.warn("请选择需要装箱的订单");
                return;
            }
            $scope.errors = null;
            if($scope.scanItemForm.validator.form()){
                var packingInfo = {};
                var packing = {};
                for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                    var obj = $scope.currentPackingBoxList[i];
                    if(obj.orderNo){
                        packingInfo[obj.orderNo] = obj.currentPacking.id;
                        packing.orderNo = obj.orderNo;
                        packing.packingId = obj.currentPacking.id;
                    }
                }
                $scope.pageSetting.disabledScan = true;
                var call = PackingAPI.scanItem({
                    packingInfo:packingInfo,
                    upc:$scope.item.upc
                }, function(data){
                    $scope.getPackingOrderCountItemList();
                    $scope.getOrderList();
                    var history = {};
                    $scope.pageSetting.disabledScan = false;
                    if(!data.id){
                        // 重复扫描，删除商品扫描记录
                        history.message = "已删除商品装箱记录，UPC编号：【" + $scope.item.upc + "】";
                        $scope.showPutBoxNum = null;
                        $ugDialog.alert(history.message);
                        $scope.packingHistory.push(history);
                        var oldBoxIndex = null;
                        for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                            var obj2 = $scope.currentPackingBoxList[i];
                            if(obj2.currentPacking && obj2.currentPacking.id == packing.packingId){
                                oldBoxIndex = i;
                            }
                        }
                        if(oldBoxIndex == null){
                            return;
                        }
                        PackingAPI.getPackingOrderList({
                            limit:1,
                            offset:1,
                            orderNo:data.orderNo
                        }, function(data){
                            var oldBox = $scope.currentPackingBoxList[oldBoxIndex];
                            $scope.currentPackingBoxList[oldBoxIndex] = data.data[0];
                            $scope.currentPackingBoxList[oldBoxIndex].currentPacking = oldBox.currentPacking;
                        });
                    }else{
                        var oldBoxIndex = null;
                        for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                            var obj2 = $scope.currentPackingBoxList[i];
                            if(obj2.currentPacking && obj2.currentPacking.id == data.packingId){
                                var num = parseInt(i) + 1;
                                history.message ="商品已放入【" + num + "】号箱位。";
                                $scope.showPutBoxNum = num;
                                $ugDialog.alert(history.message);
                                $scope.packingHistory.push(history);
                                oldBoxIndex = i;
                            }
                        }
                        if(oldBoxIndex == null){
                            return;
                        }
                        PackingAPI.getPackingOrderList({
                            limit:1,
                            offset:1,
                            orderNo:data.orderNo
                        }, function(data){
                            var oldBox = $scope.currentPackingBoxList[oldBoxIndex];
                            $scope.currentPackingBoxList[oldBoxIndex] = data.data[0];
                            $scope.currentPackingBoxList[oldBoxIndex].currentPacking = oldBox.currentPacking;
                            //订单已完成
                            if($scope.currentPackingBoxList[oldBoxIndex].packingTaskStatus == 2){
                                //打印订单
                                $scope.printOrder($scope.currentPackingBoxList[oldBoxIndex]);
                                //如果是单箱装 才自动进入下一个任务
                                if($scope.choosePackingOrders.length == 1){
                                    //自动进入下一个任务
                                    $scope.nextPackingTask();
                                }
                            }
                        });
                    }
                }, function(data){
                    $ugDialog.warn(data.data.message);
                })
                console.log(call);
            }
        };
        $scope.getOrderList();

        /**
         * 显示装箱明细
         */
        $scope.showPackingDetail = function(index){
            var modalInstance = $modal.open({
                size:'lg',
                templateUrl: 'viewPackingDetail.html',
                resolve: {
                    choseOrder: function(){
                        return $scope.currentPackingBoxList[index];
                    }
                },
                controller: ["$scope", "choseOrder", "$modalInstance", function ($scope, choseOrder, $modalInstance) {
                    $scope.choseOrder = choseOrder || {};
                    $scope.orderItemPageInfoSetting = {
                        pageSize:10,
                        pageNum:1
                    };
                    $scope.packingRecordPageInfoSetting = {
                        pageSize:10,
                        pageNum:1
                    };

                    $scope.queryParam = {};
                    $scope.getPackingOrderItemList = function(){
                        if($scope.choseOrder &&
                            $scope.choseOrder.id){
                            PackingAPI.getPackingOrderItemList({
                                limit:$scope.orderItemPageInfoSetting.pageSize,
                                offset:$scope.orderItemPageInfoSetting.pageNum,
                                id:1,
                                keyword:$scope.queryParam.packingOrderKeyword,
                                orderId:$scope.choseOrder.id
                            }, function(data){
                                $scope.packingOrderItemList = data.data;
                                $scope.orderItemPageInfoSetting = data.pageInfo;
                                $scope.orderItemPageInfoSetting.loadData = $scope.getPackingOrderItemList;
                            });
                        }
                    };
                    $scope.getPackingOrderItemList();
                    $scope.choseOrderItem = function(index){
                        $scope.choseOrder.choseOrderItem = $scope.packingOrderItemList[index];
                        $scope.getPackingRecordList();
                    };
                    $scope.getPackingRecordList = function(){
                        if($scope.choseOrder &&
                            $scope.choseOrder.choseOrderItem &&
                            $scope.choseOrder.choseOrderItem.orderItemId){
                            PackingAPI.getPackingRecordList({
                                limit:$scope.packingRecordPageInfoSetting.pageSize,
                                offset:$scope.packingRecordPageInfoSetting.pageNum,
                                id:$scope.choseOrder.id,
                                keyword:$scope.queryParam.packingRecordKeyword,
                                orderItemId:$scope.choseOrder.choseOrderItem.orderItemId
                            }, function(data){
                                $scope.packingRecordList = data.data;
                                $scope.packingRecordPageInfoSetting = data.pageInfo;
                                $scope.packingRecordPageInfoSetting.loadData = $scope.getPackingRecordList;
                            });
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function (data) {
                // $scope.choseItem(index, parseFloat(data.number));
            });
        }
        /***************打印*******************/
        $scope.printOrder = function(order){
            PackingAPI.printOrder({
                orderId:order.id
            }, function(data){
                var result = data;
                var boxSize = result.packingModels.length;
                var LODOP=getLodop();
                for(i=0;i<boxSize;i++){
                    LODOP.PRINT_INITA(0,0,"100.81mm","74.61mm","打印订单");
                    LODOP.ADD_PRINT_BARCODE(23,266,43,43,"QRCode",order.orderNo);
                    LODOP.ADD_PRINT_BARCODE(23,317,40,42,"QRCode","http://fygl.ehoyuan.cn:8088/bind/auth?url=/wx/receipt&param="+order.orderNo);
                    LODOP.ADD_PRINT_BARCODE(99,23,"57.57mm","9.95mm","128Auto",order.orderNo);
                    LODOP.ADD_PRINT_TEXT(100,254,95,26,order.consigneeName);
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
                    LODOP.SET_PRINT_STYLEA(0,"Bold",1);
                    LODOP.ADD_PRINT_TEXT(128,254,113,25,order.consigneePhone);
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
                    LODOP.SET_PRINT_STYLEA(0,"Bold",1);
                    LODOP.ADD_PRINT_TEXT(160,20,340,24,"地址:"+order.consigneeAddress);
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.ADD_PRINT_TEXT(186,20,341,24,"备注:"+order.remark);
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.ADD_PRINT_ELLIPSE(454,486,47,48,0,1);
                    LODOP.ADD_PRINT_TEXT(462,498,13,20,"1");
                    LODOP.SET_PRINT_STYLEA(0,"Bold",1);
                    LODOP.ADD_PRINT_TEXT(483,511,16,20,"1");
                    LODOP.SET_PRINT_STYLEA(0,"Bold",1);
                    LODOP.ADD_PRINT_TEXT(212,19,341,25,"收款方式："+order.paymentModeDesc);
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.ADD_PRINT_TEXT(239,18,181,25,"客户经理："+order.managerName);
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.ADD_PRINT_TEXT(62,270,45,15,"物流专用");
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
                    LODOP.ADD_PRINT_TEXT(62,316,50,15,"收退货专用");
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
                    var currentBox = i +1;
                    LODOP.ADD_PRINT_TEXT(237,283,85,35,currentBox + "/" + boxSize);
                    LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                    LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
                    LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
                    LODOP.SET_PRINT_STYLEA(0,"Bold",1);
                    LODOP.ADD_PRINT_RECT(217,275,100,60,0,1);
                    LODOP.PRINT();
                }
            });
        }

    }];
});