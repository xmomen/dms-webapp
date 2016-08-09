/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "$modal", "$ugDialog", "UserAPI", "PackingAPI", function($scope, OrderAPI, $modal, $ugDialog, UserAPI, PackingAPI){
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
        $scope.queryParam = {};
        $scope.getOrderList = function(){
            debugger;
            PackingAPI.getPackingOrderList({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                consigneeName:$scope.queryParam.consigneeName,
                managerId:$scope.queryParam.managerId
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
        };
        $scope.currentCustomer = {};
        $scope.resetCurrentCustomer = function(){
            $scope.currentCustomer = {};
        };
        $scope.chosePackingCustomer = function(index){
            $scope.currentCustomer = $scope.companyCustomerManagers[index];
        };
        $scope.packingTaskPageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.getCustomerManagersList = function(){
            PackingAPI.getPackingTaskList({
                userType:"zhuangxiangzu",
                keyword:$scope.queryParam.customerKeyword,
                limit:$scope.packingTaskPageInfoSetting.pageSize,
                offset:$scope.packingTaskPageInfoSetting.pageNum
            },function(data){
                $scope.companyCustomerManagers = data.data;
                $scope.packingTaskPageInfoSetting = data.pageInfo;
                $scope.packingTaskPageInfoSetting.loadData = $scope.getCustomerManagersList;
            });
        };
        $scope.bindPackingTask = function(index){
            if(!$scope.currentCustomer.actorId){
                $ugDialog.warn("请选择需要分配的责任人");
                return;
            }
            var orderNos = [];
            orderNos.push($scope.orderList[index].orderNo);
            PackingAPI.bindPackingTask({
                packingTaskUserId:$scope.currentCustomer.actorId,
                orderNos:orderNos
            }, function(){
                $scope.getOrderList();
                $scope.getCustomerManagersList();
            })
        };
        $scope.unbindPackingTask = function(index){
            var orderNos = [];
            orderNos.push($scope.orderList[index].orderNo);
            PackingAPI.unbindPackingTask({
                orderNos:orderNos
            }, function(){
                $scope.getOrderList();
                $scope.getCustomerManagersList();
            })
        }
        $scope.removePacking = function(index){
            $ugDialog.confirm("是否删除此装箱记录？").then(function(){
                PackingAPI.delete({
                    id: $scope.packingList[index].id
                }, function(){
                    $scope.getPackingList();
                });
            })
        };
        $scope.updatePacking = function(index){
            $scope.open(angular.copy($scope.packingList[index]));
        };
        $scope.getCustomerManagersList();
        $scope.getOrderList();

        //批量分配
        $scope.chooseOrder = [];
        $scope.chooseAllCheck = {};
        $scope.checkedAllOrder = function() {
            if($scope.chooseAllCheck.isCheckOrder == 0){
                $scope.chooseOrder.splice(0, $scope.chooseOrder.length);
                for (var i = 0; i < $scope.orderList.length; i++) {
                    var obj = $scope.orderList[i];
                    if(obj.packingTaskStatus == null){
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

        $scope.batchBindPackingTask = function(){
            if(!$scope.currentCustomer.actorId){
                $ugDialog.warn("请选择需要分配的责任人");
                return;
            }
            var orderNos = [];
            for(var i in $scope.chooseOrder){
                orderNos.push($scope.chooseOrder[i].orderNo);
            }
            PackingAPI.bindPackingTask({
                packingTaskUserId:$scope.currentCustomer.actorId,
                orderNos:orderNos
            }, function(){
                $scope.getOrderList();
                $scope.getCustomerManagersList();
            })
        };
    }];
});