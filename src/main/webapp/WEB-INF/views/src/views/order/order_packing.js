/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackingAPI", "OrderAPI", "$modal", "$ugDialog", function($scope, PackingAPI, OrderAPI, $modal, $ugDialog){
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
            OrderAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                orderStatus:1,
                keyword:$scope.queryParam.orderKeyword
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.choseOrder = {};
        $scope.choseOrderPacking = function(index){
            $scope.choseOrder = $scope.orderList[index];
            $scope.getPackingOrderItemList();
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
        $scope.currentPacking = {};
        $scope.startPacking = function(){
            if(!$scope.currentPacking.packingNo && $scope.choseOrder){
                PackingAPI.save({
                    orderNo:$scope.choseOrder.orderNo
                }, function(data){
                    $scope.currentPacking = data;
                })
            }else{
                $ugDialog.warn("请先在【待装箱订单列表】中选择需要装箱的订单")
            }
        };
        $scope.changePacking = function(){

        };
        $scope.scanItem = function(){

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
            }else{
                $ugDialog.warn("请先在【订单商品装箱汇总表】中选择需要查询的商品")
            }
        };
        $scope.removePacking = function(index){
            $ugDialog.confirm("是否删除此装箱记录？").then(function(){
                PackingAPI.delete({
                    id: $scope.packingList[index].id
                }, function(){
                    $scope.getPackingList();
                });
            })
        };
        $scope.getOrderList();
    }];
});