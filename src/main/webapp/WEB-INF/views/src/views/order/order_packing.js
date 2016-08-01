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
                    if(!data.id){
                        $ugDialog.alert("已删除商品装箱记录，UPC编号：【" + $scope.item.upc + "】");
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
                                $ugDialog.alert("商品已放入【" + obj2.boxNum + "】号箱位。")
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

            LODOP.PRINT_INITA(7,2,"799.99mm","600mm","打印订单");
            LODOP.ADD_PRINT_BARCODE(100,334,209,150,"QRCode","http://fygl.ehoyuan.cn:8088/bind/auth?url=/wx/receipt&param="+orderNo);
            LODOP.ADD_PRINT_BARCODE(102,69,240,142,"QRCode","http://fygl.ehoyuan.cn:8088/bind/auth?url=/wx/scanning&param="+orderNo);
            LODOP.ADD_PRINT_BARCODE(223,58,"114.46mm","19.21mm","128B",orderNo);
            LODOP.ADD_PRINT_RECT(32,26,171,60,0,1);
            LODOP.ADD_PRINT_RECT(32,217,299,60,0,1);
            LODOP.ADD_PRINT_TEXT(37,222,279,50,"客服电话：\n健康是一种生活习惯");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
            LODOP.ADD_PRINT_RECT(308,22,512,193,0,1);
            LODOP.ADD_PRINT_LINE(356,135,356,266,0,1);
            LODOP.ADD_PRINT_TEXT(333,49,69,35,"收货人");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
            LODOP.ADD_PRINT_TEXT(328,339,49,36,"电话");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
            LODOP.ADD_PRINT_TEXT(387,48,51,35,"地址");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
            LODOP.ADD_PRINT_TEXT(441,47,54,35,"备注");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
            LODOP.ADD_PRINT_LINE(352,391,352,523,0,1);
            LODOP.ADD_PRINT_TEXT(334,165,100,20,"张三");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
            LODOP.ADD_PRINT_TEXT(331,392,100,20,"11115589658");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
            LODOP.ADD_PRINT_LINE(409,98,409,451,0,1);
            LODOP.ADD_PRINT_LINE(470,104,470,437,0,1);
            LODOP.ADD_PRINT_TEXT(386,106,401,20,"XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
            LODOP.ADD_PRINT_TEXT(447,105,382,20,"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...(省略)");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
            LODOP.ADD_PRINT_ELLIPSE(454,486,47,48,0,1);
            LODOP.ADD_PRINT_LINE(494,493,467,527,0,1);
            LODOP.ADD_PRINT_TEXT(462,498,13,20,"1");
            LODOP.SET_PRINT_STYLEA(0,"Bold",1);
            LODOP.ADD_PRINT_TEXT(483,511,16,20,"1");
            LODOP.SET_PRINT_STYLEA(0,"Bold",1);


            LODOP.PRINT_DESIGN();
        }

    }];
});