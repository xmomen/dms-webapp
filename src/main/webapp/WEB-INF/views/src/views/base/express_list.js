/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ExpressAPI", "$modal", "$ugDialog", function($scope, ExpressAPI, $modal, $ugDialog){
        $scope.expressList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getExpressList = function(){
            ExpressAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.expressList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getExpressList;
            });
        };
        $scope.removeExpress = function(index){
            $ugDialog.confirm("是否删除该快递公司？").then(function(){
                ExpressAPI.delete({
                    id: $scope.expressList[index].id
                }, function(){
                    $scope.getExpressList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addExpress.html',
                controller: ["$scope", "ExpressAPI", "$modalInstance","currentExpress", "$rootScope", function ($scope, ExpressAPI, $modalInstance,currentExpress,$rootScope) {
                    $scope.express = {};
                    if(currentExpress){
                        $scope.express = currentExpress;
                    }
                    $scope.errors = null;
                    $scope.addExpressForm = {};
                    $scope.saveOrUpdateExpress = function(){
                        $scope.errors = null;
                        if($scope.addExpressForm.validator.form()){
                            if($scope.express.id){
                                ExpressAPI.update($scope.express, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                ExpressAPI.save($scope.express, function(){
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
                currentExpress: function () {
                    return $scope.expressList[index];
                }
            }
            });
            modalInstance.result.then(function () {
                $scope.getExpressList();
            });
        };

        $scope.getExpressList();
    }];
});