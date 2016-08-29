/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "BasePlanAPI", "$modal", "$ugDialog", "$rootScope",function($scope, BasePlanAPI, $modal, $ugDialog,$rootScope){
        $scope.basePlanList = [];
        $scope.basePlan = {};
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getBasePlanList = function(){
            BasePlanAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                categoryType:$scope.queryParam.categoryType
            }, function(data){
                $scope.basePlanList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getBasePlanList;
            });
        };
        $scope.removeBasePlan = function(basePlan){
            $ugDialog.confirm("是否删除该计划？").then(function(){
                BasePlanAPI.delete({
                    id: basePlan.id
                }, function(){
                    $scope.getBasePlanList();
                });
            })
        };
        $scope.open = function (basePlan) {
            var modalInstance = $modal.open({
                templateUrl: 'addBasePlan.html',
                controller: ["$scope", "BasePlanAPI", "ItemAPI","$modalInstance","currentBasePlan", function ($scope, BasePlanAPI,ItemAPI,$modalInstance,currentBasePlan) {

                    $scope.chooseCategoryModel = function(){
                        var modalInstance = $modal.open({
                            templateUrl: 'chooseCategory.html',
                            controller: ["$scope", "ItemCategoryAPI", "$modalInstance", function ($scope, ItemCategoryAPI, $modalInstance) {
                                $scope.itemCategoryList = [];
                                $scope.queryParam = {};
                                ItemCategoryAPI.query({
                                    id:$scope.queryParam.id
                                }, function(data){
                                    $scope.itemCategoryList = data;
                                    $rootScope.$broadcast("loadingTree");
                                });
                                $scope.cancel = function () {
                                    $modalInstance.dismiss('cancel');
                                };
                                $scope.chooseCategory = function(category){
                                    $modalInstance.close(category);
                                }
                            }]
                        });
                        modalInstance.result.then(function (category) {
                            $scope.basePlan.categoryName = category.name;
                            $scope.basePlan.cdCategoryId = category.id;
                        });
                    },

                    $scope.basePlan = {categoryType : 1,isRandom:0};
                    if(currentBasePlan){
                        $scope.basePlan = currentBasePlan;
                            BasePlanAPI.getChoseItemList({
                                parentId:$scope.basePlan.id
                            },function(data){
                                $scope.choseItemList = data;
                            })
                    }
                    $scope.errors = null;
                    $scope.addBasePlanForm = {};
                    $scope.saveOrUpdateBasePlan = function(){
                        $scope.errors = null;
                        if($scope.addBasePlanForm.validator.form()){
                            if($scope.basePlan.id){
                                $scope.basePlan.planItems = [];
                                for (var i = 0; i < $scope.choseItemList.length; i++) {
                                    var obj = $scope.choseItemList[i];
                                    $scope.basePlan.planItems.push({
                                        cdItemId:obj.id,
                                        count :obj.count
                                    });
                                }
                                BasePlanAPI.update($scope.basePlan, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                $scope.basePlan.planItems = [];
                                for (var i = 0; i < $scope.choseItemList.length; i++) {
                                    var obj = $scope.choseItemList[i];
                                    $scope.basePlan.planItems.push({
                                        cdItemId:obj.id,
                                        count :obj.count
                                    });
                                }
                                BasePlanAPI.save($scope.basePlan, function(){
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
                    $scope.choseItemList = [];
                    $scope.pageInfoSetting = {
                        pageSize:1000,
                        pageNum:1
                    };
                    $scope.queryParam = {};

                    $scope.getItemList = function(){
                        var choseItemId = null;
                        if($scope.choseItemList && $scope.choseItemList.length > 0){
                            choseItemId = []
                            for (var i = 0; i < $scope.choseItemList.length; i++) {
                                var obj = $scope.choseItemList[i];
                                choseItemId.push(obj.id);
                            }
                        }
                        ItemAPI.query({
                            limit:$scope.pageInfoSetting.pageSize,
                            offset:$scope.pageInfoSetting.pageNum,
                            keyword:$scope.queryParam.keyword,
                            exclude_ids:choseItemId,
                            sellStatus : 1
                        }, function(data){
                            $scope.itemList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getItemList;
                        });
                    };

                    $scope.openItemNumber = function (index) {
                        var modalInstance = $modal.open({
                            templateUrl: 'addItemNumber.html',
                            resolve: {
                                CurrentOrderItem: function(){
                                    return $scope.itemList[index];
                                }
                            },
                            controller: ["$scope", "CurrentOrderItem", "$modalInstance", function ($scope, CurrentOrderItem, $modalInstance) {
                                $scope.orderItem = {};
                                if(CurrentOrderItem){
                                    $scope.orderItem = CurrentOrderItem;
                                }
                                $scope.addItemNumberForm = {};
                                $scope.saveItemNumber = function(){
                                    if($scope.addItemNumberForm.validator.form()){
                                        $modalInstance.close($scope.orderItem);
                                    }
                                };
                                $scope.cancel = function () {
                                    $modalInstance.dismiss('cancel');
                                };
                            }]
                        });
                        modalInstance.result.then(function (data) {
                            $scope.choseItem(index, parseFloat(data.number));
                        });
                    };

                    $scope.choseItem = function(index,count){
                        var item = $scope.itemList[index];
                        item.cdItemId = item.id;
                        item.count = count;
                        $scope.choseItemList.push(item);
                        $scope.getItemList();
                    };

                    $scope.removeItem = function(index){
                        $scope.choseItemList.splice(index,1);
                        $scope.getItemList();
                    };
                }],
                resolve: {
                currentBasePlan: function () {
                    return basePlan;
                }
            },
             size : 'lg'
            });
            modalInstance.result.then(function () {
                $scope.getBasePlanList();
            });
        };

        $scope.getBasePlanList();
    }];
});