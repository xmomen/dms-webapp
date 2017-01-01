/**
 */
define(function () {
    return ["$scope", "ReceivingCodeRequestAPI", "$modal", "$ugDialog","UserAPI","$filter", function($scope, ReceivingCodeRequestAPI, $modal, $ugDialog,UserAPI,$filter){

        $scope.receivingCodeRequestList = [];
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
            requestTimeStart:{
                opened:false
            },
            requestTimeEnd:{
                opened:false
            }
        };
        $scope.openDate = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 0){
                $scope.datepickerSetting.requestTimeStart.opened = true;
            }else if(index == 1){
                $scope.datepickerSetting.requestTimeEnd.opened = true;
            }
        };

        $scope.queryParam = {
            requestTimeStart :$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd'),
            requestTimeEnd:$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd')
        };

        $scope.getReceivingCodeRequestList = function(){
            ReceivingCodeRequestAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                requestTimeStart:$scope.queryParam.requestTimeStart,
                requestTimeEnd: $scope.queryParam.requestTimeEnd,
                orderNo:$scope.queryParam.orderNo
            }, function(data){
                $scope.receivingCodeRequestList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getReceivingCodeRequestList;
            });
        };
        $scope.getReceivingCodeRequestList();
    }];
});