/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackingAPI", "OrderAPI", "$modal", "$ugDialog", "$q", "DictionaryAPI", function($scope, PackingAPI, OrderAPI, $modal, $ugDialog, $q, DictionaryAPI){
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
        $scope.ugSelect2Config = {};
        $scope.choseOrder = {};
        $scope.choseOrderPacking = function(index){
            $scope.choseOrder = $scope.orderList[index];
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
                    $scope.orderItemPageInfoSetting.loadData = $scope.getOrderList;
                });
            }else{
                $ugDialog.warn("请先在【待装箱订单列表】中选择需要查询的订单")
            }
        };
        var getPackingList = function(){
            var defer = $q.defer();
            PackingAPI.query({
                limit:100,
                offset:1,
                orderNo:$scope.choseOrder.orderNo
            }, function(data){
                $scope.choseOrder.packingList = data.data;
                return defer.resolve();
            });
            return defer.promise;
        };
        $scope.startPacking = function(){
            if($scope.choseOrder && $scope.choseOrder.orderNo){
                getPackingList().then(function(){
                    if($scope.choseOrder.packingList && $scope.choseOrder.packingList.length == 0){
                        PackingAPI.save({
                            orderNo:$scope.choseOrder.orderNo
                        }, function(data){
                            $scope.choseOrder.currentPacking = data;
                        })
                    }else{
                        $scope.choseOrder.currentPacking = $scope.choseOrder.packingList[0];
                    }
                });
            }else{
                $ugDialog.warn("请先在【待装箱订单列表】中选择需要装箱的订单")
            }
        };
        $scope.changePacking = function(){
            PackingAPI.save({
                orderNo:$scope.choseOrder.orderNo
            }, function(data){
                $scope.choseOrder.currentPacking = data;
            })
        };
        $scope.scanItemForm = {};
        $scope.item = {};
        $scope.scanItem = function(){
            if(!$scope.choseOrder.currentPacking || !$scope.choseOrder.currentPacking.id){
                $ugDialog.warn("请点击开始装箱")
                return;
            }
            $scope.errors = null;
            if($scope.scanItemForm.validator.form()){
                PackingAPI.scanItem({
                    id:$scope.choseOrder.currentPacking.id,
                    orderNo:$scope.choseOrder.orderNo,
                    upc:$scope.item.upc
                }, function(data){
                    $scope.getPackingOrderItemList();
                    $scope.getPackingRecordList();
                    $scope.getOrderList();
                }, function(data){
                    $scope.errors = data.data;
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
                    $scope.packingRecordPageInfoSetting.loadData = $scope.packingRecordList;
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
    }];
});