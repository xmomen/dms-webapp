/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackageTaskAPI", "$modal", "$ugDialog","$stateParams", function($scope, PackageTaskAPI, $modal, $ugDialog,$stateParams){
        $scope.packageTaskList = [];
        $scope.pageInfoSetting = {
            pageSize:100,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPackageTaskList = function(){
            PackageTaskAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                viewType:"view"
            }, function(data){
                $scope.packageTaskList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPackageTaskList;
            });
        };
        $scope.getPackageTaskList();
    }];
});