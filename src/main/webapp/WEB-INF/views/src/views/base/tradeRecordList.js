/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "TradeRecordAPI", "$modal", "$ugDialog",function($scope, TradeRecordAPI, $modal, $ugDialog){

        $scope.tradeRecordList = [];
        $scope.pageInfoSetting = {
            pageSize:50,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getTradeRecordList = function(){
            TradeRecordAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.tradeRecordList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getTradeRecordList;
            });
        };
        $scope.getTradeRecordList();
    }];
});