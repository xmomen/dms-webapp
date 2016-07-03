/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ActivityAPI", "$modal", "$ugDialog", function($scope, ActivityAPI, $modal, $ugDialog){
        $scope.activityList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getActivityList = function(){
            ActivityAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.activityList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getActivityList;
            });
        };
        $scope.removeActivity = function(index){
            $ugDialog.confirm("是否删除活动？").then(function(){
                ActivityAPI.delete({
                    id: $scope.activityList[index].id
                }, function(){
                    $scope.getActivityList();
                });
            })
        };
        $scope.updateActivity = function(index){
            $scope.open(angular.copy($scope.activityList[index]));
        };
        $scope.open = function (activity) {
            var modalInstance = $modal.open({
                templateUrl: 'addActivity.html',
                resolve: {
                    CurrentActivity: function(){
                        return activity;
                    }
                },
                controller: ["$scope", "ActivityAPI","CurrentActivity","ItemAPI", "CouponCategoryAPI","$modalInstance", function ($scope, ActivityAPI, CurrentActivity, ItemAPI,CouponCategoryAPI,$modalInstance) {
                    $scope.ugSelect2Config = {};
                    $scope.choseChildItemList = [];
                    $scope.activity = {
                        activityType : 1,
                        available : 1
                    };
                    if(CurrentActivity){
                        $scope.activity = CurrentActivity;
                        ActivityAPI.getChoseItemList({
                                parentId:$scope.activity.id
                            },function(data){
                                $scope.ugSelect2Config.initSelectData($scope.activity.couponCategory);
                                $scope.choseChildItemList = data;
                            })
                    }
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
                            sellStatus : 1
                        }, function(data){
                            $scope.childItemList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getChildItemList;
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
                    $scope.getCategoryList = function(){
                        $scope.pageInfoSetting = {
                            pageSize:1000,
                            pageNum:1
                        };
                        $scope.queryParam = {};
                        $scope.categoryList = [];
                        CouponCategoryAPI.query({
                            limit:$scope.pageInfoSetting.pageSize,
                            offset:$scope.pageInfoSetting.pageNum,
                            categoryType : 1
                        }, function(data){
                            $scope.categoryList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getCategoryList;
                        });
                    }
                    $scope.getCategoryList();

                    $scope.datepickerSetting = {
                        datepickerPopupConfig:{
                            "current-text":"今天",
                            "clear-text":"清除",
                            "close-text":"关闭"
                        },
                        beginTime:{
                            opened:false
                        },
                        endTime:{
                            opened:false
                        }
                    };
                    $scope.open = function($event, index) {
                        $event.preventDefault();
                        $event.stopPropagation();
                        if(index == 0){
                            $scope.datepickerSetting.beginTime.opened = true;
                        }else if(index == 1){
                            $scope.datepickerSetting.endTime.opened = true;
                        }
                    };
                    $scope.errors = null;
                    $scope.addActivityForm = {};
                    $scope.saveActivity = function(){
                        $scope.errors = null;
                        if($scope.addActivityForm.validator.form()){
                            if($scope.activity.id){
                                $scope.activity.activityRefList = [];
                                for (var i = 0; i < $scope.choseChildItemList.length; i++) {
                                    var obj = $scope.choseChildItemList[i];
                                    $scope.activity.activityRefList.push({
                                        cdItemId:obj.id,
                                        count:obj.count
                                    });
                                }
                                ActivityAPI.update($scope.activity, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                $scope.activity.activityRefList = [];
                                for (var i = 0; i < $scope.choseChildItemList.length; i++) {
                                    var obj = $scope.choseChildItemList[i];
                                    $scope.activity.activityRefList.push({
                                        cdItemId:obj.id,
                                        count:obj.count
                                    });
                                }
                                ActivityAPI.save($scope.activity, function(){
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
                size:'lg'
            });
            modalInstance.result.then(function () {
                $scope.getActivityList();
            });
        };

        $scope.getActivityList();
    }];
});