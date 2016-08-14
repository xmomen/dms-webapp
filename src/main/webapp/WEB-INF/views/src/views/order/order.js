/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog","UserAPI", function($scope, OrderAPI, $modal, $ugDialog,UserAPI){

        $scope.managers = [];
        $scope.getCustomerManagersList = function(){
            UserAPI.getCustomerManagerList({
                userType:"customer_manager"
            },function(data){
                $scope.managers = data;
            });
        }
        $scope.getCustomerManagersList();


        $scope.orderList = [];
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
            orderCreateTimeStart:{
                opened:false
            },
            orderCreateTimeEnd:{
                opened:false
            }
        };
        $scope.openDate = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 0){
                $scope.datepickerSetting.orderCreateTimeStart.opened = true;
            }else if(index == 1){
                $scope.datepickerSetting.orderCreateTimeEnd.opened = true;
            }
        };

        $scope.queryParam = {
            orderCreateTimeStart :$scope.currentDate(),
            orderCreateTimeEnd:$scope.currentDate()
        };

        $scope.getOrderList = function(){
            OrderAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                orderCreateTimeStart:$scope.queryParam.orderCreateTimeStart,
                orderCreateTimeEnd: $scope.queryParam.orderCreateTimeEnd,
                hasNoShowCancel:false
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.cancelOrder = function(index){
            $ugDialog.confirm("是否取消此订单？").then(function(){
                OrderAPI.cancel({
                    id: $scope.orderList[index].id
                }, function(){
                    $scope.getOrderList();
                });
            })
        };
        $scope.updateOrder = function(index){
            $scope.open(angular.copy($scope.orderList[index]));
        };
        $scope.viewOrder = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'viewOrderDetail.html',
                resolve: {
                    CurrentOrder: function(){
                        return angular.copy($scope.orderList[index]);
                    }
                },
                controller: ["$scope", "OrderAPI", "CurrentOrder", "$modalInstance", function ($scope, OrderAPI, CurrentOrder, $modalInstance) {
                    $scope.order = {};
                    if(CurrentOrder){
                        $scope.order = CurrentOrder;
                    }
                    $scope.setting = {
                        pageInfo : {
                            pageSize:30,
                            pageNum:1
                        }
                    };
                    OrderAPI.getItemList({
                        limit: $scope.setting.pageInfo.pageSize,
                        offset: $scope.setting.pageInfo.pageNum,
                        id:$scope.order.id,
                        orderNo:$scope.order.orderNo
                    }, function(data){
                        $scope.order.itemList = data.data;
                        $scope.calTotalItem();
                    });
                    $scope.calTotalItem = function(){
                        $scope.totalItem = {};
                        var totalNumber = 0;
                        var totalPrice = 0;
                        for (var i = 0; i < $scope.order.itemList.length; i++) {
                            var obj = $scope.order.itemList[i];
                            totalNumber += obj.itemQty;
                            totalPrice += (obj.itemQty * obj.itemPrice);
                        }
                        $scope.totalItem.totalNumber = totalNumber;
                        $scope.totalItem.totalPrice = totalPrice;
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getOrderList();
            });
        };
        $scope.getOrderList();
    }];
});