/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "StockDailyAPI", "$modal", "$ugDialog", "UserAPI", "$filter", function ($scope, StockDailyAPI, $modal, $ugDialog, UserAPI, $filter) {

        $scope.stockDailyList = [];
        $scope.pageInfoSetting = {
            pageSize: 10,
            pageNum: 1
        };
        $scope.queryParam = {};

        $scope.datepickerSetting = {
            datepickerPopupConfig: {
                "current-text": "今天",
                "clear-text": "清除",
                "close-text": "关闭"
            },
            dailyDateStart: {
                opened: false
            },
            dailyDateEnd: {
                opened: false
            }
        };
        $scope.openDate = function ($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if (index == 0) {
                $scope.datepickerSetting.dailyDateStart.opened = true;
            } else if (index == 1) {
                $scope.datepickerSetting.dailyDateEnd.opened = true;
            }
        };

        $scope.getStockDailyList = function () {
            StockDailyAPI.query({
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum,
                keyword: $scope.queryParam.keyword,
                dailyDateStart: $scope.queryParam.dailyDateStart,
                dailyDateEnd: $scope.queryParam.dailyDateEnd
            }, function (data) {
                $scope.stockDailyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getStockDailyList;
            });
        };

        /**
         * 查看变更明细
         */
        $scope.openStockRecordDetail = function (dailyDate, stockId, changeType) {
            var modalInstance = $modal.open({
                templateUrl: 'stockRecordList.html',
                resolve: {
                    QueryParams: function () {
                        var queryParams = {
                            dailyDate: $filter('date')(new Date(dailyDate), 'yyyy-MM-dd'),
                            stockId: stockId,
                            changeType: changeType
                        }
                        return queryParams;
                    }
                },
                controller: ["$scope", "StockRecordAPI", "QueryParams", "$modalInstance", function ($scope, StockRecordAPI, QueryParams, $modalInstance) {
                    $scope.stockRecordList = [];
                    $scope.pageInfoRecordSetting = {
                        pageSize: 10,
                        pageNum: 1
                    };
                    $scope.getStockRecordList = function (categoryName) {
                        StockRecordAPI.query({
                            limit: $scope.pageInfoRecordSetting.pageSize,
                            offset: $scope.pageInfoRecordSetting.pageNum,
                            stockId: $scope.queryParams.stockId,
                            changeType: $scope.queryParams.changeType,
                            dailyDate: $scope.queryParams.dailyDate
                        }, function (data) {
                            $scope.stockRecordList = data.data;
                            $scope.pageInfoRecordSetting = data.pageInfo;
                            $scope.pageInfoRecordSetting.loadData = $scope.getStockRecordList;
                        });
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    if (QueryParams) {
                        $scope.queryParams = QueryParams;
                    }
                    $scope.getStockRecordList();
                }],
                size: "lg"
            });
            modalInstance.result.then(function () {
                $scope.getStockDailyList();
            });
        };

        /**
         * 生成库存快照
         */
        $scope.createStockDaily = function () {
            $ugDialog.confirm("确认手动生成今日库存快照？").then(function () {
                StockDailyAPI.createStockDaily({}, function (data) {
                    $ugDialog.alert("今日库存快照生成成功。");
                    $scope.getStockDailyList();
                })
            });
        }

        $scope.exportStockDaily = function () {
            if (!$scope.queryParam.dailyDateStart) {
                $ugDialog.warn("请输入开始时间");
                return;
            }
            if (!$scope.queryParam.dailyDateEnd) {
                $ugDialog.warn("请输入结束时间");
                return;
            }
            var url = "/report/stockDaily?beginTime=" + $scope.queryParam.dailyDateStart + "&endTime=" + $scope.queryParam.dailyDateEnd;
            window.location.href = url;
        }

        $scope.getStockDailyList();
    }];
});