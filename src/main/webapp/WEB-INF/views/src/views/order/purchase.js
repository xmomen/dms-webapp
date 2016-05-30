/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PurchaseAPI", "$modal", "$ugDialog", function($scope, PurchaseAPI, $modal, $ugDialog){
        $scope.purchaseList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPurchaseList = function(){
            PurchaseAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.purchaseList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPurchaseList;
            });
        };
        $scope.removePurchase = function(index){
            $ugDialog.confirm("是否删除此订单？").then(function(){
                PurchaseAPI.delete({
                    id: $scope.purchaseList[index].id
                }, function(){
                    $scope.getPurchaseList();
                });
            })
        };
        $scope.createPlan = function(){
            PurchaseAPI.save({
                orderDate:new Date()
            }, function(data){
                $scope.getPurchaseList();
            });
        }
        $scope.updatePurchase = function(index){
            $scope.open(angular.copy($scope.purchaseList[index]));
        };
        $scope.open = function (purchase) {
            var modalInstance = $modal.open({
                templateUrl: 'addPurchase.html',
                resolve: {
                    CurrentPurchase: function(){
                        return purchase;
                    }
                },
                controller: ["$scope", "PurchaseAPI", "CurrentPurchase", "$modalInstance", function ($scope, PurchaseAPI, CurrentPurchase, $modalInstance) {
                    $scope.purchase = {};
                    if(CurrentPurchase){
                        $scope.purchase = CurrentPurchase;
                    }
                    $scope.errors = null;
                    $scope.addPurchaseForm = {};
                    $scope.savePurchase = function(){
                        $scope.errors = null;
                        if($scope.addPurchaseForm.validator.form()){
                            if($scope.purchase.id){
                                PurchaseAPI.update($scope.purchase, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                PurchaseAPI.save($scope.purchase, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getPurchaseList();
            });
        };

        $scope.getPurchaseList();
    }];
});