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

        $scope.createStockDaily = function () {
            $ugDialog.confirm("确认手动生成今日库存快照？").then(function () {
                StockDailyAPI.createStockDaily({}, function (data) {
                    $ugDialog.alert("今日库存快照生成成功。");
                    $scope.getStockDailyList();
                })
            });
        }
        $scope.getStockDailyList();
    }];
});