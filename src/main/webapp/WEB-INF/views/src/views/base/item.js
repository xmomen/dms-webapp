/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ItemAPI", "ItemCategoryAPI","$modal", "$ugDialog","$rootScope", function($scope, ItemAPI,ItemCategoryAPI, $modal, $ugDialog, $rootScope){

        $scope.itemCategoryList = [];
        $scope.queryCategoryParam = {};
        $scope.getItemCategoryTree = function(){
            ItemCategoryAPI.query({
                id:$scope.queryCategoryParam.id
            }, function(data){
                $scope.itemCategoryList = data;
                $rootScope.$broadcast("loadingTree");
            });
        };
        $scope.getItemCategoryTree();
        $scope.itemList = [];
        $scope.item = {};
        $scope.pageInfoSetting = {
            pageSize:25,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getItemList = function(categoryName){
            ItemAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.itemList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getItemList;
            });
        };

        $scope.getItemListFormCategory = function(categoryName){
            $scope.queryParam.keyword = categoryName;
            $scope.getItemList();
        }

        $scope.removeItem = function(index){
            $ugDialog.confirm("是否删除该产品？").then(function(){
                ItemAPI.delete({
                    id: $scope.itemList[index].id
                }, function(){
                    $scope.getItemList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addItem.html',
                controller: ["$scope", "ItemAPI", "$modalInstance","currentItem", function ($scope, ItemAPI, $modalInstance,currentItem) {
                    $scope.choseChildItemList = [];
                    $scope.item = {
                        itemType : 1,
                        sellStatus : 1
                    };
                    if(currentItem){
                        $scope.item = currentItem;
                        if($scope.item.itemType == 2){
                            ItemAPI.getChildItemList({
                                parentId:$scope.item.id
                            },function(data){
                                $scope.choseChildItemList = data;
                            })
                        }
                    }
                    $scope.errors = null;
                    $scope.addItemForm = {};
                    $scope.saveOrUpdateItem = function(){
                        $scope.errors = null;
                        if($scope.addItemForm.validator.form()){
                            if($scope.item.id){
                                $scope.item.childItems = [];
                                for (var i = 0; i < $scope.choseChildItemList.length; i++) {
                                    var obj = $scope.choseChildItemList[i];
                                    $scope.item.childItems.push({
                                        id:obj.id,
                                        count:obj.count
                                    });
                                }
                                ItemAPI.update($scope.item, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                $scope.item.childItems = [];
                                for (var i = 0; i < $scope.choseChildItemList.length; i++) {
                                    var obj = $scope.choseChildItemList[i];
                                    $scope.item.childItems.push({
                                        id:obj.id,
                                        count:obj.count
                                    });
                                }
                                ItemAPI.save($scope.item, function(){
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
                                $scope.item.categoryName = category.name;
                                $scope.item.cdCategoryId = category.id;
                        });
                    },
                    $scope.pageChildSetting = {
                        pageSize:1000,
                        pageNum:1
                    };
                    $scope.queryChildParam = {};

                    $scope.getChildItemList = function(){
                        var choseItemId = null;
                        if($scope.choseChildItemList && $scope.choseChildItemList.length > 0){
                            choseItemId = []
                            for (var i = 0; i < $scope.choseChildItemList.length; i++) {
                                var obj = $scope.choseChildItemList[i];
                                choseItemId.push(obj.id);
                            }
                        }
                        ItemAPI.query({
                            limit:$scope.pageChildSetting.pageSize,
                            offset:$scope.pageChildSetting.pageNum,
                            keyword:$scope.queryChildParam.keyword,
                            exclude_ids:choseItemId,
                            itemType : 1,
                            sellStatus : 1
                        }, function(data){
                            $scope.childItemList = data.data;
                            $scope.pageChildSetting = data.pageInfo;
                            $scope.pageChildSetting.loadData = $scope.getChildItemList;
                        });
                    };

                    $scope.openItemNumber = function (index) {
                        var modalInstance = $modal.open({
                            templateUrl: 'addItemNumber.html',
                            resolve: {
                                CurrentOrderItem: function(){
                                    return $scope.childItemList[index];
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
                            $scope.choseChildItem(index, parseFloat(data.number));
                        });
                    };

                    $scope.choseChildItem = function(index,count){
                        var item = $scope.childItemList[index];
                        item.childItemId = item.id;
                        item.count = count;
                        $scope.choseChildItemList.push(item);
                        $scope.getChildItemList();
                    };

                    $scope.removeChildItem = function(index){
                        $scope.choseChildItemList.splice(index,1);
                        $scope.getChildItemList();
                    };

                }],
                resolve: {
                currentItem: function () {
                    return $scope.itemList[index];
                }
            },
             size : 'lg'
            });
            modalInstance.result.then(function () {
                $scope.getItemList();
            });
        };

        $scope.getItemList();
    }];
});