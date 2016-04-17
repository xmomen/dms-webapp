/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponCategoryAPI", "$modal", "$ugDialog", function($scope, CouponCategoryAPI, $modal, $ugDialog){
        $scope.couponCategoryList = [];
        $scope.couponCategory = {};
        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getCouponCategoryList = function(){
            CouponCategoryAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.couponCategoryList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCouponCategoryList;
            });
        };
        $scope.removeCouponCategory = function(index){
            $ugDialog.confirm("是否删除该产品？").then(function(){
                CouponCategoryAPI.delete({
                    id: $scope.couponCategoryList[index].id
                }, function(){
                    $scope.getCouponCategoryList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addCouponCategory.html',
                controller: ["$scope", "CouponCategoryAPI", "ItemAPI","$modalInstance","currentCouponCategory", function ($scope, CouponCategoryAPI,ItemAPI,$modalInstance,currentCouponCategory) {
                    $scope.couponCategory = {categoryType : 1};
                    if(currentCouponCategory){
                        $scope.couponCategory = currentCouponCategory;
                    }
                    $scope.errors = null;
                    $scope.addCouponCategoryForm = {};
                    $scope.saveOrUpdateCouponCategory = function(){
                        $scope.errors = null;
                        if($scope.addCouponCategoryForm.validator.form()){
                            if($scope.couponCategory.id){
                                CouponCategoryAPI.update($scope.couponCategory, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
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
                    $scope.pageSetting = {
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
                            limit:$scope.pageSetting.pageSize,
                            offset:$scope.pageSetting.pageNum,
                            keyword:$scope.queryParam.keyword,
                            exclude_ids:choseItemId,
                            sellStatus : 1
                        }, function(data){
                            $scope.itemList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getItemList;
                        });
                    };
                    $scope.choseItem = function(index){
                        var item = $scope.itemList[index];
                        item.cdItemId = item.id;
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
                    return $scope.couponCategoryList[index];
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