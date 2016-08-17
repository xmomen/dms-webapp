/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "$modal", "$ugDialog","ExpressAPI","OrderAPI", function($scope,$modal, $ugDialog,ExpressAPI,OrderAPI){

        $scope.pageInfoExpressSetting = {
            pageSize:100,
            pageNum:1
        };
        $scope.getExpressList = function(){
            ExpressAPI.query({
                limit:$scope.pageInfoExpressSetting.pageSize,
                offset:$scope.pageInfoExpressSetting.pageNum
            }, function(data){
                $scope.expressList = data.data;
                $scope.pageInfoExpressSetting = data.pageInfo;
                $scope.pageInfoExpressSetting.loadData = $scope.getExpressList;
            });
        };
        $scope.getExpressList();

        $scope.orderList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getOrderList = function(){
            //查询收货的订单
            OrderAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                hasNoShowCancel:true,
                orderStatus:5,
                despatchExpressId:$scope.queryParam.despatchExpressId,
                consigneeName:$scope.queryParam.consigneeName
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };

        $scope.getOrderList();

        $scope.reviewDelivery = function(orderNo){
            ExpressAPI.reviewDelivery({
                orderNo:orderNo
            }, function(){
                $ugDialog.alert("审核成功");
                $scope.getOrderList();
            },function(data){
                $ugDialog.warn(data.data.message)
            })
        }
    }];
});