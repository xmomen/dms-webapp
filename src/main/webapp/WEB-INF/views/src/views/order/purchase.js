/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PurchaseAPI", "$modal", "$ugDialog","$filter", function($scope, PurchaseAPI, $modal, $ugDialog,$filter){
        $scope.datepickerSetting = {
            datepickerPopupConfig:{
                "current-text":"今天",
                "clear-text":"清除",
                "close-text":"关闭",
                "format":"yyyy-MM-dd"
            },
            startTime:{
                opened:false
            },
            endTime:{
                opened:false
            },
            appointmentTimeStart:{
                opened:false
            },
            appointmentTimeEnd:{
                opened:false
            }
        };
        $scope.openDatepicker = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 1){
                $scope.datepickerSetting.startTime.opened = true;
            }else if(index == 2){
                $scope.datepickerSetting.endTime.opened = true;
            }else if(index == 3){
                $scope.datepickerSetting.appointmentTimeStart.opened = true;
            }else if(index == 4){
                $scope.datepickerSetting.appointmentTimeEnd.opened = true;
            }
        };
        $scope.purchasePlan = {};
        $scope.purchaseList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {
            purchaseStatus : "0",
            startTime:$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd'),
            endTime :$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd')
        };
        $scope.getPurchaseList = function(){
            PurchaseAPI.query({
                startTime: $scope.queryParam.startTime,
                endTime:$scope.queryParam.endTime,
                purchaseStatus:$scope.queryParam.purchaseStatus,
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                sellUnit:$scope.queryParam.sellUnit
            }, function(data){
                $scope.purchaseList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPurchaseList;
            });
        };

        $scope.finish = function(index){
            $ugDialog.confirm("是否已完成此产品的采购？").then(function(){
                PurchaseAPI.update({
                    id: $scope.purchaseList[index].purchaseId,
                    purchaseStatus:1
                }, function(){
                    $scope.getPurchaseList();
                });
            })
        };
        $scope.removePurchase = function(index){
            $ugDialog.confirm("是否删除此订单？").then(function(){
                PurchaseAPI.delete({
                    id: $scope.purchaseList[index].purchaseId
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
            }, function(data){
                $ugDialog.warn(data.data.message);
            });
        }

        $scope.createPlanAppointmenTime = function(){
            if($scope.purchasePlan.appointmentTimeEnd == undefined || $scope.purchasePlan.appointmentTimeStart == undefined){
                $ugDialog.warn("请输入起始结束日期");
                return false;
            }
            PurchaseAPI.save({
                appointmentTimeStart:$scope.purchasePlan.appointmentTimeStart,
                appointmentTimeEnd:$scope.purchasePlan.appointmentTimeEnd
            }, function(data){
                $scope.getPurchaseList();
            }, function(data){
                $ugDialog.warn(data.data.message);
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
        $scope.exportExcel = function(){
            window.location.href = "/export/exportPurchaseExcel";
        }
        $scope.getPurchaseList();
    }];
});