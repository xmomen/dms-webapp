/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog","ExpressAPI", function($scope, OrderAPI, $modal, $ugDialog,ExpressAPI){
        $scope.orderList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getOrderList = function(){
            //查询待配送的订单
            OrderAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                hasNoShowCancel:true
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };

        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        //查询快递商
        $scope.expressList = [];
        $scope.getExpressList = function(){
            ExpressAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            },function(data){
                $scope.expressList = data.data;
            });
        }
        //选择快递商
        $scope.choseExpressUser = function(index){
            $scope.currentCustomer = $scope.expressList[index];
        };
        $scope.getOrderList();
        $scope.getExpressList();

        $scope.bindExpress = function(index){
            if(!$scope.currentCustomer){
                $ugDialog.warn("请选择运输的快递商");
                return;
            }
            var orderNos = [];
            orderNos.push($scope.orderList[index].orderNo);
            ExpressAPI.bindExpress({
                expressId:$scope.currentCustomer.id,
                orderNos:orderNos
            }, function(){
                $scope.getOrderList();
                $scope.getExpressList();
            })
        };
        $scope.unbindExpress = function(index){
            var orderNos = [];
            orderNos.push($scope.orderList[index].orderNo);
            ExpressAPI.unbindExpress({
                orderNos:orderNos
            }, function(){
                $scope.getOrderList();
                $scope.getExpressList();
            })
        }

        //批量分配
        $scope.chooseOrder = [];
        $scope.chooseAllCheck = {};
        $scope.checkedAllOrder = function() {
            if($scope.chooseAllCheck.isCheckOrder == 0){
                $scope.chooseOrder.splice(0, $scope.chooseOrder.length);
                for (var i = 0; i < $scope.orderList.length; i++) {
                    var obj = $scope.orderList[i];
                    if(obj.expressName == null){
                        $scope.chooseOrder.push(obj);
                    }
                }
            }else{
                $scope.chooseOrder.splice(0, $scope.chooseOrder.length);
            }
        };

        $scope.changeOrderList = function(){
            if($scope.chooseOrder.length == $scope.orderList.length){
                $scope.isCheckCombine = 0;
            }else{
                $scope.isCheckCombine = 1;
            }
        };

        $scope.batchBindExpress = function(){
            if(!$scope.currentCustomer){
                $ugDialog.warn("请选择运输的快递商");
                return;
            }
            var orderNos = [];
            for(var i in $scope.chooseOrder){
                orderNos.push($scope.chooseOrder[i].orderNo);
            }
            ExpressAPI.bindExpress({
                expressId:$scope.currentCustomer.id,
                orderNos:orderNos
            }, function(){
                $scope.getOrderList();
                $scope.getExpressList();
            })
        };
    }];
});