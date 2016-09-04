/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "$modal", "$ugDialog","ExpressAPI", function($scope,$modal, $ugDialog,ExpressAPI){
        $scope.orderList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};

        $scope.datepickerSetting = {
            datepickerPopupConfig:{
                "current-text":"今天",
                "clear-text":"清除",
                "close-text":"关闭",
                "format":"yyyy-MM-dd"
            },
            startTime:{
                opened:false
            },
            endTime:{
                opened:false
            }
        };
        $scope.openDatepicker = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 1){
                $scope.datepickerSetting.startTime.opened = true;
            }else if(index == 2){
                $scope.datepickerSetting.endTime.opened = true;
            }
        };

        $scope.getOrderList = function(){
            //查询未提货的订单
            ExpressAPI.queryOrder({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                hasNoShowCancel:true
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };

        $scope.getOrderList();

        //扫描订单编号
        $scope.saoOrderNoEvent = function(e){
            var keycode = window.event?e.keyCode:e.which;
            if(keycode==13){
                $scope.takeDelivery();
            }
        }

        //
        $scope.takeDelivery = function(){
            if(!$scope.orderNo){
                $ugDialog.warn("请扫描订单号");
                return;
            }

            ExpressAPI.takeDelivery({
                orderNo:$scope.orderNo
            }, function(){
                $ugDialog.alert("提货成功");
                $scope.getOrderList();
                $("#orderNo").focus();
                $("#orderNo").select();
                $("#orderNo").val("");
            },function(data){
                $ugDialog.warn(data.data.message);
                $("#orderNo").focus();
                $("#orderNo").select();
                $("#orderNo").val("");
            })
        }

        $scope.unTakeDelivery = function(orderNo){
            ExpressAPI.unTakeDelivery({
                orderNo:orderNo
            }, function(){
                $ugDialog.alert("取消提货成功");
                $scope.getOrderList();
                $("#orderNo").focus();
                $("#orderNo").select();
                $("#orderNo").val("");
            },function(data){
                $ugDialog.warn(data.data.message)
                $("#orderNo").focus();
                $("#orderNo").select();
                $("#orderNo").val("");
            })
        }

        $scope.exportExcel = function(){
            window.location.href = "/export/exportTakeDeliveryExcel?startTime="+$scope.queryParam.startTime+"&endTime="+$scope.queryParam.endTime;
        }
    }];
});