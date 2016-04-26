/**
 */
define(function () {
    return ["$scope", "ItemCategoryAPI", "$modal", "$ugDialog", "$rootScope", function($scope, ItemCategoryAPI, $modal, $ugDialog, $rootScope){
        $scope.itemCategoryList = [];
        $scope.queryParam = {};
        $scope.getItemCategoryTree = function(){
            ItemCategoryAPI.query({
                id:$scope.queryParam.id
            }, function(data){
                $scope.itemCategoryList = data;
                $rootScope.$broadcast("loadingTree");
            });
        };
        $scope.removeNode = function(item){
            var msg = "是否删除此分类?";
            if(item.nodes && item.nodes.length > 0){
                msg = "此分类存在下级分类，确认将同步删除此分类所有下级分类，是否删除？"
            }
            $ugDialog.confirm(msg).then(function(){
                ItemCategoryAPI.delete({
                    id: item.id
                }, function(){
                    $scope.getItemCategoryTree();
                });
            })
        };
        $scope.addChildItemCategory = function(item){
            $scope.openAddModel({
                parentId:item.id,
                parentName:item.name
            }, 'ADD_CHILD_NODE');
        };
        $scope.updateChildItemCategory = function(item){
            $scope.openAddModel(angular.copy(item));
        };
        $scope.openAddModel = function (itemCategory, action) {
            var modalInstance = $modal.open({
                templateUrl: 'addItemCategory.html',
                resolve:{
                    CurrentItemCategory : function(){
                        return itemCategory;
                    },
                    Action: function(){
                        return action;
                    }
                },
                controller: ["$scope", "ItemCategoryAPI", "$modalInstance", "CurrentItemCategory", "Action", function ($scope, ItemCategoryAPI, $modalInstance, CurrentItemCategory, Action) {
                    $scope.itemCategory = {};
                    if(CurrentItemCategory){
                        if(Action == 'ADD_CHILD_NODE'){
                            $scope.itemCategory.parentId = CurrentItemCategory.parentId;
                            $scope.itemCategory.parentName = CurrentItemCategory.parentName;
                        }else{
                            $scope.itemCategory = {
                                id:CurrentItemCategory.id,
                                name:CurrentItemCategory.name,
                                parentId:CurrentItemCategory.parentId,
                                parentName:CurrentItemCategory.parentName
                            };
                        }
                    }
                    $scope.errors = null;
                    $scope.addItemCategoryForm = {};
                    $scope.saveItemCategory = function(){
                        $scope.errors = null;
                        if($scope.addItemCategoryForm.validator.form()){
                            if($scope.itemCategory.id){
                                ItemCategoryAPI.update($scope.itemCategory, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                ItemCategoryAPI.save($scope.itemCategory, function(){
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
                $scope.getItemCategoryTree();
            });
        };

        $scope.getItemCategoryTree();

    }];
});