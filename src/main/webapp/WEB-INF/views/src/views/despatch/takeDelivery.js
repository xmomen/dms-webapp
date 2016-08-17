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
                $ugDialog.warn(data.data.message)
            })
        }

        $scope.unTakeDelivery = function(orderNo){
            ExpressAPI.unTakeDelivery({
                orderNo:orderNo
            }, function(){
                $ugDialog.alert("取消提货成功");
                $scope.getOrderList();
            },function(data){
                $ugDialog.warn(data.data.message)
            })
        }
    }];
});