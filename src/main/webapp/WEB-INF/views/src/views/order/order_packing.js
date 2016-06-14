/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackingAPI", "OrderAPI", "$modal", "$ugDialog", "$q", function($scope, PackingAPI, OrderAPI, $modal, $ugDialog, $q){
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
            $scope.packingRecordList = [];
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
        $scope.scanItem = function(){
            if(!$scope.choseOrder.currentPacking || !$scope.choseOrder.currentPacking.id){
                $ugDialog.warn("请点击开始装箱")
                return;
            }
            var modalInstance = $modal.open({
                templateUrl: 'scanItem.html',
                resolve: {
                    CurrentItem: function(){
                        return {
                            id:$scope.choseOrder.currentPacking.id,
                            orderNo:$scope.choseOrder.orderNo
                        };
                    }
                },
                controller: ["$scope", "PackingAPI", "CurrentItem", "$modalInstance", function ($scope, PackingAPI, CurrentItem, $modalInstance) {
                    $scope.item = {};
                    if(CurrentItem){
                        $scope.item.id = CurrentItem.id;
                        $scope.item.orderNo = CurrentItem.orderNo;
                    }
                    $scope.errors = null;
                    $scope.scanItemForm = {};
                    $scope.saveScanItem = function(){
                        $scope.errors = null;
                        if($scope.scanItemForm.validator.form()){
                            PackingAPI.scanItem($scope.item, function(){
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
                $scope.getPackingOrderItemList();
                $scope.getPackingRecordList();
                $scope.getOrderList();
            });
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