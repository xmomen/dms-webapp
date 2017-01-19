/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ReportOrderAPI", "$modal", "$ugDialog", "$filter", "CouponCategoryAPI", "CompanyAPI", "UserAPI", function ($scope, ReportOrderAPI, $modal, $ugDialog, $filter, CouponCategoryAPI, CompanyAPI, UserAPI) {

        $scope.queryParam = {};

        $scope.pageInfoSetting = {
            pageSize: 1000,
            pageNum: 1
        };

        $scope.companyList = [];
        $scope.getCompanyList = function () {
            CompanyAPI.query({
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function (data) {
                $scope.companyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCompanyList;
            });
        };
        $scope.getCompanyList();

        $scope.companyCustomerManagers = [];
        $scope.getCustomerManagersList = function () {
            UserAPI.getCustomerManagerList({
                userType: "customer_manager"
            }, function (data) {
                $scope.companyCustomerManagers = data;
            });
        }
        $scope.getCustomerManagersList();

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
            } else if (index == 3) {
                $scope.datepickerSetting.startTimeExpress.opened = true;
            } else if (index == 4) {
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
            var url = "/report/order?beginTime=" + $("#startTimeId").val() + "&endTime=" + $("#endTimeId").val();
            if ($scope.queryParam.cdCompanyId_order != undefined) {
                url += "&companyId=" + $scope.queryParam.cdCompanyId_order;
            }
            alert($scope.queryParam.cdCompanyId_order);
            if ($scope.queryParam.customerMangerId_order != undefined) {
                url += "&managerId=" + $scope.queryParam.customerMangerId_order;
            }
            window.location.href = url
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
            var url = "/report/express?beginTime=" + $("#startTimeId_express").val() + "&endTime=" + $("#endTimeId_express").val();
            if ($scope.queryParam.cdCompanyId_express != 'undefined') {
                url += "&companyId=" + $scope.queryParam.cdCompanyId_express;
            }
            if ($scope.queryParam.customerMangerId_express != 'undefined') {
                url += "&managerId=" + $scope.queryParam.customerMangerId_express;
            }
            window.location.href = url;
        }
    }];
});