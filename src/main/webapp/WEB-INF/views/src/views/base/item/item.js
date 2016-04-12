/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ItemAPI", "$modal", "$ugDialog", function($scope, ItemAPI, $modal, $ugDialog){
        $scope.itemList = [];
        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getItemList = function(){
            ItemAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.itemList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getItemList;
            });
        };
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
                    $scope.item = {};
                    if(currentItem){
                        $scope.item = currentItem;
                    }
                    $scope.errors = null;
                    $scope.addItemForm = {};
                    $scope.saveOrUpdateItem = function(){
                        $scope.errors = null;
                        if($scope.addItemForm.validator.form()){
                            if($scope.item.id){
                                ItemAPI.update($scope.item, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
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
                }],
                resolve: {
                currentItem: function () {
                    return $scope.itemList[index];
                }
            }
            });
            modalInstance.result.then(function () {
                $scope.getItemList();
            });
        };

        $scope.getItemList();
    }];
});