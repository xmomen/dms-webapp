/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "StockAPI", "$modal", "$ugDialog", "UserAPI", "$filter", function ($scope, StockAPI, $modal, $ugDialog, UserAPI, $filter) {

        $scope.stockList = [];
        $scope.pageInfoSetting = {
            pageSize: 10,
            pageNum: 1
        };
        $scope.queryParam = {};
        $scope.deleteStock = function (item) {
            if (item.stockNum === 0) {
                StockAPI.delete({
                    id: item.id
                }, function (data) {
                    $ugDialog.alert('删除成功');
                    $scope.getStockList();
                })
            } else {
                $ugDialog.alert('只能删除库存数量为0的商品');
            }
        };
        $scope.getStockList = function () {
            StockAPI.query({
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum,
                keyword: $scope.queryParam.keyword
            }, function (data) {
                $scope.stockList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getStockList;
            });
        };
        $scope.saveStock = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'saveStock.html',
                resolve: {
                    CurrentStock: function () {
                        if ($scope.stockList[index]) {
                            return angular.copy($scope.stockList[index]);
                        }
                        return null;
                    }
                },
                controller: ["$scope", "StockAPI", "CurrentStock", "$modalInstance", "ItemAPI", function ($scope, StockAPI, CurrentStock, $modalInstance, ItemAPI) {
                    $scope.stock = {};
                    $scope.ugSelect2Config = {};
                    $scope.getItemList = function () {
                        ItemAPI.query({
                            limit: 1000,
                            offset: 1,
                            excludeStock: 1
                        }, function (data) {
                            $scope.itemList = data.data;
                            $scope.ugSelect2Config.initSelectData($scope.stock.itemId);
                        });
                    };
                    $scope.stockForm = {};
                    $scope.saveBtnLoading = false;
                    $scope.save = function () {
                        if ($scope.stockForm.validator.form() && $scope.stockForm.validator.valid()) {
                            $scope.saveBtnLoading = true;
                            if ($scope.stock.id) {
                                StockAPI.update($scope.stock, function (data) {
                                    $ugDialog.alert('保存成功');
                                    $modalInstance.close();
                                }).$promise.finally(function () {
                                    $scope.saveBtnLoading = false;
                                })
                            } else {
                                StockAPI.save($scope.stock, function (data) {
                                    $ugDialog.alert('保存成功');
                                    $modalInstance.close();
                                }).$promise.finally(function () {
                                    $scope.saveBtnLoading = false;
                                })
                            }
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    if (CurrentStock) {
                        $scope.stock = CurrentStock;
                    }
                    $scope.getItemList();
                }]
            });
            modalInstance.result.then(function () {
                $scope.getStockList();
            });
        };
        $scope.changeStock = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'changeStock.html',
                resolve: {
                    CurrentStock: function () {
                        if ($scope.stockList[index]) {
                            return angular.copy($scope.stockList[index]);
                        }
                        return null;
                    }
                },
                controller: ["$scope", "StockAPI", "CurrentStock", "$modalInstance", "ItemAPI", function ($scope, StockAPI, CurrentStock, $modalInstance, ItemAPI) {
                    $scope.stock = {};
                    $scope.ugSelect2Config = {};
                    $scope.getItemList = function (categoryName) {
                        ItemAPI.query({
                            limit: 1000,
                            offset: 1
                        }, function (data) {
                            $scope.itemList = data.data;
                            $scope.ugSelect2Config.initSelectData($scope.stock.itemId);
                        });
                    };
                    $scope.stockForm = {};
                    $scope.saveBtnLoading = false;
                    $scope.save = function () {
                        if ($scope.stockForm.validator.form() && $scope.stockForm.validator.valid()) {
                            $scope.saveBtnLoading = true;
                            StockAPI.change($scope.stock, function (data) {
                                $ugDialog.alert('保存成功');
                                $modalInstance.close();
                            }).$promise.finally(function () {
                                $scope.saveBtnLoading = false;
                            });
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    if (CurrentStock) {
                        $scope.stock = CurrentStock;
                    }
                    $scope.getItemList();
                }]
            });
            modalInstance.result.then(function () {
                $scope.getStockList();
            });
        };
        $scope.getStockList();
    }];
});