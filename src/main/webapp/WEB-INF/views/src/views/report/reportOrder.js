/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ReportOrderAPI", "$modal", "$ugDialog", "$filter", function ($scope, ReportOrderAPI, $modal, $ugDialog, $filter) {

        $scope.datepickerSetting = {
            datepickerPopupConfig: {
                "current-text": "今天",
                "clear-text": "清除",
                "close-text": "关闭"
            },
            startTime: {
                opened: false
            },
            endTime: {
                opened: false
            },
            startTimeExpress: {
                opened: false
            },
            endTimeExpress: {
                opened: false
            }
        };
        $scope.openDatepicker = function ($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if (index == 1) {
                $scope.datepickerSetting.startTime.opened = true;
            } else if (index == 2) {
                $scope.datepickerSetting.endTime.opened = true;
            }else if (index == 3) {
                $scope.datepickerSetting.startTimeExpress.opened = true;
            }else if (index == 4) {
                $scope.datepickerSetting.endTimeExpress.opened = true;
            }
        };


        $scope.exportOrderExcel = function () {
            if (!$("#startTimeId").val()) {
                $ugDialog.warn("请输入开始时间");
                return;
            }
            if (!$("#endTimeId").val()) {
                $ugDialog.warn("请输入结束时间");
                return;
            }
            window.location.href = "/report/order?beginTime=" + $("#startTimeId").val() + "&endTime=" + $("#endTimeId").val();
        }

        $scope.exportExpressExcel = function () {
            if (!$("#startTimeId_express").val()) {
                $ugDialog.warn("请输入开始时间");
                return;
            }
            if (!$("#endTimeId_express").val()) {
                $ugDialog.warn("请输入结束时间");
                return;
            }
            window.location.href = "/report/express?beginTime=" + $("#startTimeId_express").val() + "&endTime=" + $("#endTimeId_express").val();
        }
    }];
});