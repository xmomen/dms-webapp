/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackingAPI", "$modal", "$ugDialog", function($scope, PackingAPI, $modal, $ugDialog){
        $scope.packingList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPackingList = function(){
            PackingAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.packingList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPackingList;
            });
        };
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
        $scope.open = function (packing) {
            var modalInstance = $modal.open({
                templateUrl: 'addPacking.html',
                resolve: {
                    CurrentPacking: function(){
                        return packing;
                    }
                },
                controller: ["$scope", "PackingAPI", "CurrentPacking", "$modalInstance", function ($scope, PackingAPI, CurrentPacking, $modalInstance) {
                    $scope.packing = {};
                    if(CurrentPacking){
                        $scope.packing = CurrentPacking;
                    }
                    $scope.errors = null;
                    $scope.addPackingForm = {};
                    $scope.savePacking = function(){
                        $scope.errors = null;
                        if($scope.addPackingForm.validator.form()){
                            if($scope.packing.id){
                                PackingAPI.update($scope.packing, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                PackingAPI.save($scope.packing, function(){
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
                $scope.getPackingList();
            });
        };

        $scope.getPackingList();
    }];
});