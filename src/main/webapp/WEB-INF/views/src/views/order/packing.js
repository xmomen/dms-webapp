/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackingAPI", "$modal", "$ugDialog", function($scope, PackingAPI, $modal, $ugDialog){
        $scope.packingList = [];
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
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPackingList = function(){
            PackingAPI.getPackingOrderList({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                isHasPackingTaskUserId:true,
                //packingTaskCreateTimeStart: new Date($scope.queryParam.packingTaskCreateTimeStart).getTime(),
                //packingTaskCreateTimeEnd:$scope.queryParam.packingTaskCreateTimeEnd,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.packingList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPackingList;
            });
        };
        $scope.getPackingList();
        $scope.showPackingDetail = function(index){
            var modalInstance = $modal.open({
                size:'lg',
                templateUrl: 'viewPackingDetail.html',
                resolve: {
                    choseOrder: function(){
                        return $scope.packingList[index];
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
                    $scope.viewOrderPacking = function(index){
                        $scope.choseOrder = $scope.currentPackingBoxList[index];
                        $scope.getPackingOrderItemList();
                        $scope.packingRecordList = [];
                        $scope.errors = null;
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
    }];
});