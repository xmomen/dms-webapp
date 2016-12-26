/**
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog","UserAPI","$filter", function($scope, OrderAPI, $modal, $ugDialog,UserAPI,$filter){
        $scope.managers = [];
        $scope.getCustomerManagersList = function(){
            UserAPI.getCustomerManagerList({
                userType:"customer_manager"
            },function(data){
                $scope.managers = data;
            });
        }
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

        $scope.queryParam = {
            orderCreateTimeStart :$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd'),
            orderCreateTimeEnd:$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd')
        };

        $scope.getOrderList = function(){
            OrderAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                orderCreateTimeStart:$scope.queryParam.orderCreateTimeStart,
                orderCreateTimeEnd: $scope.queryParam.orderCreateTimeEnd,
                couponNumber:$scope.queryParam.couponNumber,
                hasNoShowCancel:false
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.getOrderList();
    }];
});