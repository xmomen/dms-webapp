/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog","UserAPI", "CompanyAPI", "$window", function($scope, OrderAPI, $modal, $ugDialog,UserAPI, CompanyAPI, $window){
        $scope.ugSelect2Config = {};
        $scope.pageSetting = {
            pageSize:1000,
            pageNum:1
        };
        $scope.getCompanyList = function(){
            CompanyAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum
            }, function(data){
                $scope.companyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCompanyList;
            });
        };
        $scope.getCompanyList();
        $scope.managers = [];
        $scope.getCustomerManagersList = function(){
            UserAPI.getCustomerManagerList({
                userType:"customer_manager"
            },function(data){
                $scope.managers = data;
            });
        };
        $scope.getCustomerManagersList();
        $scope.orderList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };

        $scope.datepickerSetting = {
            datepickerPopupConfig:{
                "current-text":"今天",
                "clear-text":"清除",
                "close-text":"关闭"
            },
            orderCreateTimeStart:{
                opened:false
            },
            orderCreateTimeEnd:{
                opened:false
            }
        };
        $scope.openDate = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 0){
                $scope.datepickerSetting.orderCreateTimeStart.opened = true;
            }else if(index == 1){
                $scope.datepickerSetting.orderCreateTimeEnd.opened = true;
            }
        };

        $scope.queryParam = {};
        $scope.exportOrder = function(){
            var data = {
                managerId:$scope.queryParam.managerId,
                companyId:$scope.queryParam.companyId,
                orderCreateTimeStart:$scope.queryParam.orderCreateTimeStart.getTime(),
                orderCreateTimeEnd: $scope.queryParam.orderCreateTimeEnd.getTime()
            };
            var params = "";
            for(var p in data){
                if(data[p]){
                    params += p + "=" + data[p] + "&";
                }
            }
            var anchor = angular.element('<a/>');
            anchor.attr({
                href: '/order/report?' + params,
                target: '_blank'
            })[0].click();
        };
        $scope.getOrderList = function(){
            OrderAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                managerId:$scope.queryParam.managerId,
                companyId:$scope.queryParam.companyId,
                orderCreateTimeStart:$scope.queryParam.orderCreateTimeStart,
                orderCreateTimeEnd: $scope.queryParam.orderCreateTimeEnd
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.getOrderList();
    }];
});