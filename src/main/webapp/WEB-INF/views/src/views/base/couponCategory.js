/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponCategoryAPI", "$modal", "$ugDialog", function($scope, CouponCategoryAPI, $modal, $ugDialog){
        $scope.couponCategoryList = [];
        $scope.couponCategory = {};
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCouponCategoryList = function(){
            CouponCategoryAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                categoryType:$scope.queryParam.categoryType
            }, function(data){
                $scope.couponCategoryList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCouponCategoryList;
            });
        };
        $scope.removeCouponCategory = function(couponCategory){
            $ugDialog.confirm("是否删除该产品？").then(function(){
                CouponCategoryAPI.delete({
                    id: couponCategory.id
                }, function(){
                    $scope.getCouponCategoryList();
                });
            })
        };
        $scope.open = function (couponCategory) {
            var modalInstance = $modal.open({
                templateUrl: 'addCouponCategory.html',
                controller: ["$scope", "CouponCategoryAPI", "ItemAPI","$modalInstance","currentCouponCategory", function ($scope, CouponCategoryAPI,ItemAPI,$modalInstance,currentCouponCategory) {
                    $scope.couponCategory = {categoryType : 1};
                    if(currentCouponCategory){
                        $scope.couponCategory = currentCouponCategory;
                        if($scope.couponCategory.categoryType == 2){
                            CouponCategoryAPI.getChoseItemList({
                                parentId:$scope.couponCategory.id
                            },function(data){
                                $scope.choseItemList = data;
                            })
                        }
                    }
                    $scope.errors = null;
                    $scope.addCouponCategoryForm = {};
                    $scope.saveOrUpdateCouponCategory = function(){
                        $scope.errors = null;
                        if($scope.addCouponCategoryForm.validator.form()){
                            if($scope.couponCategory.id){
                                $scope.couponCategory.categoryRefs = [];
                                for (var i = 0; i < $scope.choseItemList.length; i++) {
                                    var obj = $scope.choseItemList[i];
                                    $scope.couponCategory.categoryRefs.push({
                                        cdItemId:obj.id,
                                        count :obj.count
                                    });
                                }
                                CouponCategoryAPI.update($scope.couponCategory, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                $scope.couponCategory.categoryRefs = [];
                                for (var i = 0; i < $scope.choseItemList.length; i++) {
                                    var obj = $scope.choseItemList[i];
                                    $scope.couponCategory.categoryRefs.push({
                                        cdItemId:obj.id,
                                        count :obj.count
                                    });
                                }
                                CouponCategoryAPI.save($scope.couponCategory, function(){
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
                currentCouponCategory: function () {
                    return couponCategory;
                }
            },
             size : 'lg'
            });
            modalInstance.result.then(function () {
                $scope.getCouponCategoryList();
            });
        };

        $scope.getCouponCategoryList();
    }];
});