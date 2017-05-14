/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "AdviceAPI", "$modal", "$ugDialog", function ($scope, AdviceAPI, $modal, $ugDialog) {
        $scope.adviceList = [];
        $scope.pageInfoSetting = {
            pageSize: 10,
            pageNum: 1
        };
        $scope.queryParam = {};
        $scope.getAdviceList = function () {
            AdviceAPI.query({
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum,
                keyword: $scope.queryParam.keyword
            }, function (data) {
                $scope.adviceList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getAdviceList;
            });
        };
        $scope.removeAdvice = function (index) {
            $ugDialog.confirm("是否删除该快报？").then(function () {
                AdviceAPI.delete({
                    id: $scope.adviceList[index].id
                }, function () {
                    $scope.getAdviceList();
                });
            })
        };

        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addAdvice.html',
                controller: ["$scope", "AdviceAPI", "$modalInstance", "currentAdvice", function ($scope, AdviceAPI, $modalInstance, currentAdvice) {

                    $scope.advice = {};
                    if (currentAdvice) {
                        $scope.advice = currentAdvice;
                    }
                    $scope.errors = null;
                    $scope.addAdviceForm = {};
                    $scope.saveOrUpdateAdvice = function () {
                        $scope.errors = null;
                        if ($scope.addAdviceForm.validator.form()) {
                            if ($scope.advice.id) {
                                AdviceAPI.update($scope.advice, function () {
                                    $modalInstance.close();
                                }, function (data) {
                                    $scope.errors = data.data;
                                })
                            } else {
                                AdviceAPI.save($scope.advice, function () {
                                    $modalInstance.close();
                                }, function (data) {
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
                    currentAdvice: function () {
                        return $scope.adviceList[index];
                    }
                },
                size: 'lg'
            });
            modalInstance.result.then(function () {
                $scope.getAdviceList();
            });
        };

        $scope.getAdviceList();
    }];
});