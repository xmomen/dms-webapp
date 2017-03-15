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
            }
        };

        $scope.openDatepicker = function ($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if (index == 1) {
                $scope.datepickerSetting.startTime.opened = true;
            } else if (index == 2) {
                $scope.datepickerSetting.endTime.opened = true;
            }
        };


        $scope.exportFinanceExcel = function () {
            // if (!$("#startTimeId").val()) {
            //     $ugDialog.warn("请输入开始时间");
            //     return;
            // }
            // if (!$("#endTimeId").val()) {
            //     $ugDialog.warn("请输入结束时间");
            //     return;
            // }
            var url = "/report/finance"
            if ($scope.queryParam.cdCompanyId != undefined) {
                url += "?companyId=" + $scope.queryParam.cdCompanyId;
            }
            if ($scope.queryParam.customerMangerId != undefined) {
                if(url.indexOf("?") > 0){
                    url += "&managerId=" + $scope.queryParam.customerMangerId;
                }else{
                    url += "?managerId=" + $scope.queryParam.customerMangerId;
                }
            }
            window.location.href = url;
        }
    }];
});