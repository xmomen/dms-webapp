/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "$modal", "$ugDialog", "ReturnOrderAPI", "$filter", function ($scope, $modal, $ugDialog, ReturnOrderAPI, $filter) {
        //扫描订单编号
        $scope.saoOrderNoEvent = function (e) {
            var keycode = window.event ? e.keyCode : e.which;
            if (keycode == 13) {
                $scope.returnOrderShouhuo();
            }
        }

        //收货扫描
        $scope.returnOrderShouhuo = function () {
            if (!$scope.boxNo) {
                $ugDialog.warn("请扫描条形码");
                return;
            }
            ReturnOrderAPI.returnOrderShouhuo({
                boxNo: $scope.boxNo
            }, function (data) {
                $ugDialog.alert("扫描成功");
                $scope.returnOrderItemList = data.itemList;
                $scope.orderNo = data.orderNo;
                $("#boxNo").focus();
                $("#boxNo").select();
                $("#boxNo").val("");
            }, function (data) {
                $ugDialog.warn(data.data.message);
                $("#boxNo").focus();
                $("#boxNo").select();
                $("#boxNo").val("");
                $scope.returnOrderItemList = [];
                $scope.orderNo = null;
            })
        }

        //收货扫描
        $scope.shouhuo = function () {
            if (!$scope.orderNo) {
                $ugDialog.warn("请扫描条形码");
                return;
            }
            ReturnOrderAPI.shouhuo({
                orderNo: $scope.orderNo
            }, function (data) {
                $ugDialog.alert("收货成功");
                $scope.returnOrderItemList = [];
                $scope.orderNo = null;
                $("#boxNo").focus();
                $("#boxNo").select();
                $("#boxNo").val("");
            }, function (data) {
                $ugDialog.warn(data.data.message);
            })
        }
    }];
});