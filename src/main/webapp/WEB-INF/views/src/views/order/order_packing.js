/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackingAPI", "OrderAPI", "$modal", "$ugDialog", "$q", "DictionaryAPI", function($scope, PackingAPI, OrderAPI, $modal, $ugDialog, $q, DictionaryAPI){
        $scope.pageSetting = {
            boxNumLimit:5
        };
        $scope.packingList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.orderItemPageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.packingRecordPageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.orderList = [];
        $scope.getOrderList = function(){
            PackingAPI.getPackingOrderList({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.orderKeyword,
                packingTaskStatus:$scope.queryParam.packingTaskStatus
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.currentPackingBoxList = [];
        var changeBoxLimit = function(){
            $scope.currentPackingBoxList = [];
            for (var i = 0; i < $scope.pageSetting.boxNumLimit; i++) {
                $scope.currentPackingBoxList.push({ boxNum : i + 1});
            }
        };
        changeBoxLimit();
        $scope.choseOrder2CurrentPackingList = function(index){
            var order = $scope.orderList[index];
            for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                var obj = $scope.currentPackingBoxList[i];
                if(!obj.orderNo){
                    order.boxNum = obj.boxNum;
                    $scope.currentPackingBoxList.splice(i,1, order);
                    setPacking(i);
                    break;
                }
            }
        };
        $scope.finishOrderPacking = function(index){
            $scope.currentPackingBoxList.splice(index,1, {
                boxNum:$scope.currentPackingBoxList[index].boxNum
            });
        };
        $scope.isCurrentOrderPacking = function(index){
            for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                var obj = $scope.currentPackingBoxList[i];
                if(obj.orderNo == $scope.orderList[index].orderNo){
                    return true;
                }
            }
            return false;
        };
        $scope.choseOrder = {};
        $scope.viewOrderPacking = function(index){
            $scope.choseOrder = $scope.currentPackingBoxList[index];
            $scope.getPackingOrderItemList();
            $scope.packingRecordList = [];
            $scope.errors = null;
        };
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
        $scope.changePacking = function(index){
            PackingAPI.save({
                orderNo:$scope.currentPackingBoxList[index].orderNo
            }, function(data){
                $scope.currentPackingBoxList[index].currentPacking = data;
            })
        };
        $scope.packingHistory = [];
        $scope.scanItemForm = {};
        $scope.item = {};
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
                PackingAPI.scanItem({
                    packingInfo:packingInfo,
                    upc:$scope.item.upc
                }, function(data){
                    $scope.getPackingOrderItemList();
                    $scope.getPackingRecordList();
                    $scope.getOrderList();
                    var history = {};
                    if(!data.id){
                        history.message = "已删除商品装箱记录，UPC编号：【" + $scope.item.upc + "】";
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
                            orderNo:obj2.orderNo
                        }, function(data){
                            var oldBox = $scope.currentPackingBoxList[oldBoxIndex];
                            $scope.currentPackingBoxList[oldBoxIndex] = data.data[0];
                            $scope.currentPackingBoxList[oldBoxIndex].boxNum = oldBox.boxNum;
                            $scope.currentPackingBoxList[oldBoxIndex].currentPacking = oldBox.currentPacking;
                        });
                    }else{
                        var oldBoxIndex = null;
                        for (var i = 0; i < $scope.currentPackingBoxList.length; i++) {
                            var obj2 = $scope.currentPackingBoxList[i];
                            if(obj2.currentPacking && obj2.currentPacking.id == data.packingId){
                                history.message ="商品已放入【" + obj2.boxNum + "】号箱位。";
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
                            orderNo:obj2.orderNo
                        }, function(data){
                            var oldBox = $scope.currentPackingBoxList[oldBoxIndex];
                            $scope.currentPackingBoxList[oldBoxIndex] = data.data[0];
                            $scope.currentPackingBoxList[oldBoxIndex].boxNum = oldBox.boxNum;
                            $scope.currentPackingBoxList[oldBoxIndex].currentPacking = oldBox.currentPacking;
                        });
                    }
                }, function(data){
                    $ugDialog.warn(data.data.message);
                })
            }
        };
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
        $scope.removePacking = function(index){
            $ugDialog.confirm("是否删除此装箱记录？").then(function(){
                PackingAPI.removePackingRecord({
                    id: $scope.choseOrder.currentPacking.id,
                    recordId: $scope.packingRecordList[index].id
                }, function(){
                    $scope.getPackingRecordList();
                    $scope.getPackingOrderItemList();
                    $scope.getOrderList();
                });
            })
        };
        $scope.getOrderList();



        /***************打印*******************/
        $scope.printOrder = function(orderNo){
            var LODOP=getLodop();

            LODOP.PRINT_INITA(0,0,"102.58mm","78.11mm","打印订单");
            LODOP.ADD_PRINT_BARCODE(73,2,82,96,"QRCode","http://fygl.ehoyuan.cn:8088/bind/auth?url=/wx/receipt&param="+orderNo);
            LODOP.ADD_PRINT_BARCODE(71,293,147,98,"QRCode","http://fygl.ehoyuan.cn:8088/bind/auth?url=/wx/scanning&param="+orderNo);
            LODOP.ADD_PRINT_BARCODE(75,80,"57.57mm","18.15mm","128B","20160508225346498");
            LODOP.ADD_PRINT_TEXT(157,13,150,21,"收货人:张三");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
            LODOP.ADD_PRINT_TEXT(155,215,121,25,"电话:13162323157");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
            LODOP.ADD_PRINT_TEXT(182,12,326,24,"地址:XXXXXXXXXXXXXXXX");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
            LODOP.ADD_PRINT_TEXT(209,12,329,24,"备注:XXXXXXXXXXXXXX");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
            LODOP.ADD_PRINT_TEXT(313,152,100,20,"");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(307,35,100,28,"");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(309,76,272,20,"");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(426,63,308,20,"");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_ELLIPSE(454,486,47,48,0,1);
            LODOP.ADD_PRINT_TEXT(462,498,13,20,"1");
            LODOP.SET_PRINT_STYLEA(0,"Bold",1);
            LODOP.ADD_PRINT_TEXT(483,511,16,20,"1");
            LODOP.SET_PRINT_STYLEA(0,"Bold",1);
            LODOP.ADD_PRINT_ELLIPSE(332,97,64,40,0,1);
            LODOP.ADD_PRINT_RECT(2,307,51,44,0,1);


            LODOP.PRINT_DESIGN();
        }

    }];
});