/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ReturnOrderAPI", "$modal", "$ugDialog", "UserAPI", "$filter", function ($scope, ReturnOrderAPI, $modal, $ugDialog, UserAPI, $filter) {

        $scope.managers = [];
        $scope.getCustomerManagersList = function () {
            UserAPI.getCustomerManagerList({
                userType: "customer_manager"
            }, function (data) {
                $scope.managers = data;
            });
        }
        $scope.getCustomerManagersList();

        $scope.orderList = [];
        $scope.pageInfoSetting = {
            pageSize: 10,
            pageNum: 1
        };
        $scope.datepickerSetting = {
            datepickerPopupConfig: {
                "current-text": "今天",
                "clear-text": "清除",
                "close-text": "关闭"
            },
            orderCreateTimeStart: {
                opened: false
            },
            orderCreateTimeEnd: {
                opened: false
            }
        };
        $scope.openDate = function ($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if (index == 0) {
                $scope.datepickerSetting.orderCreateTimeStart.opened = true;
            } else if (index == 1) {
                $scope.datepickerSetting.orderCreateTimeEnd.opened = true;
            }
        };

        $scope.queryParam = {
            orderCreateTimeStart: $filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd'),
            orderCreateTimeEnd: $filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd')
        };

        $scope.getOrderList = function () {
            ReturnOrderAPI.query({
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum,
                keyword: $scope.queryParam.keyword,
                orderCreateTimeStart: $scope.queryParam.orderCreateTimeStart,
                orderCreateTimeEnd: $scope.queryParam.orderCreateTimeEnd,
                couponNumber: $scope.queryParam.couponNumber,
                hasNoShowCancel: false
            }, function (data) {
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.viewOrder = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'viewOrderDetail.html',
                resolve: {
                    CurrentOrder: function () {
                        return angular.copy($scope.orderList[index]);
                    }
                },
                controller: ["$scope", "ReturnOrderAPI", "CurrentOrder", "$modalInstance", function ($scope, ReturnOrderAPI, CurrentOrder, $modalInstance) {
                    $scope.order = {};
                    if (CurrentOrder) {
                        $scope.order = CurrentOrder;
                    }
                    $scope.setting = {
                        pageInfo: {
                            pageSize: 30,
                            pageNum: 1
                        }
                    };
                    ReturnOrderAPI.getItemList({
                        limit: $scope.setting.pageInfo.pageSize,
                        offset: $scope.setting.pageInfo.pageNum,
                        id: $scope.order.id
                    }, function (data) {
                        $scope.order.itemList = data.data;
                        $scope.calTotalItem();
                    });
                    $scope.calTotalItem = function () {
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

        //审核退货订单
        $scope.auditYes = function (index) {
            $ugDialog.confirm("是否同意退货？").then(function () {
                var returnOrder = angular.copy($scope.orderList[index]);
                ReturnOrderAPI.auditReturnOrder({
                    id: returnOrder.returnOrderId,
                    statusCd: 1
                }, function (data) {
                    $scope.getOrderList();
                });
            });
        };

        $scope.auditNo = function (index) {
            $ugDialog.confirm("是否拒绝退货？").then(function () {
                var returnOrder = angular.copy($scope.orderList[index]);
                ReturnOrderAPI.auditReturnOrder({
                    id: returnOrder.returnOrderId,
                    statusCd: 0
                }, function (data) {
                    $scope.getOrderList();
                });
            });
        };
    }];
});