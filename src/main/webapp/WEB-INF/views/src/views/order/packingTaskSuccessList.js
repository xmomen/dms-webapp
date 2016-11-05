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
            PackingAPI.getPackingOrderList({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                consigneeName:$scope.queryParam.consigneeName,
                managerId:$scope.queryParam.managerId,
                isShowPackingAssigns:true
            }, function(data){
                $scope.orderList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getOrderList;
            });
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
        $scope.getOrderList();

        $scope.changeOrderList = function(){
            if($scope.chooseOrder.length == $scope.orderList.length){
                $scope.isCheckCombine = 0;
            }else{
                $scope.isCheckCombine = 1;
            }
        };
    }];
});