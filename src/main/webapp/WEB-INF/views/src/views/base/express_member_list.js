/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ExpressMemberAPI", "$modal", "$ugDialog", function($scope, ExpressMemberAPI, $modal, $ugDialog){
        $scope.expressList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getExpressMemberList = function(){
            ExpressMemberAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.expressMemberList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getExpressList;
            });
        };
        $scope.removeExpress = function(index){
            $ugDialog.confirm("是否删除该快递员？").then(function(){
                ExpressMemberAPI.delete({
                    id: $scope.expressList[index].id
                }, function(){
                    $scope.getExpressList();
                });
            })
        };
        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addExpressMember.html',
                controller: ["$scope", "ExpressAPI","ExpressMemberAPI", "$modalInstance","currentExpressMember", "$rootScope", function ($scope,ExpressAPI, ExpressMemberAPI, $modalInstance,currentExpressMember,$rootScope) {
                    $scope.expressMember = {};
                    if(currentExpressMember){
                        $scope.expressMember = currentExpressMember;
                    }

                    $scope.expressList = [];
                    $scope.pageInfoSetting = {
                        pageSize:1000,
                        pageNum:1
                    };
                    $scope.queryParam = {};
                    $scope.ugSelect2Config = {};
                    $scope.getExpressList = function(){
                        ExpressAPI.query({
                            limit:$scope.pageInfoSetting.pageSize,
                            offset:$scope.pageInfoSetting.pageNum,
                            keyword:$scope.queryParam.keyword
                        }, function(data){
                            $scope.expressList = data.data;
                            $scope.ugSelect2Config.initSelectData($scope.expressMember.cdExpressId);
                        });
                    };
                    $scope.getExpressList();


                    $scope.errors = null;
                    $scope.addExpressMemberForm = {};
                    $scope.saveOrUpdateExpressMember = function(){
                        $scope.errors = null;
                        if($scope.addExpressMemberForm.validator.form()){
                            if($scope.expressMember.id){
                                ExpressMemberAPI.update($scope.expressMember, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                ExpressMemberAPI.save($scope.expressMember, function(){
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
                currentExpressMember: function () {
                    return $scope.expressMemberList[index];
                }
            }
            });
            modalInstance.result.then(function () {
                $scope.getExpressMemberList();
            });
        };

        $scope.getExpressMemberList();
    }];
});