/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackageTaskAPI", "$modal", "$ugDialog","$stateParams","$filter", function($scope, PackageTaskAPI, $modal, $ugDialog,$stateParams,$filter){

        $scope.datepickerSetting = {
            datepickerPopupConfig:{
                "current-text":"今天",
                "clear-text":"清除",
                "close-text":"关闭"
            },
            packageTaskCreateTimeStart:{
                opened:false
            },
            packageTaskCreateTimeEnd:{
                opened:false
            }
        };
        $scope.open = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            if(index == 0){
                $scope.datepickerSetting.packageTaskCreateTimeStart.opened = true;
            }else if(index == 1){
                $scope.datepickerSetting.packageTaskCreateTimeEnd.opened = true;
            }
        };

        $scope.packageTaskList = [];
        $scope.pageInfoSetting = {
            pageSize:100,
            pageNum:1
        };
        $scope.queryParam = {
            packageTaskCreateTimeStart :$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd'),
            packageTaskCreateTimeEnd:$filter('date')(new Date(new Date().getTime()), 'yyyy-MM-dd')
        };

        $scope.getPackageTaskList = function(){
            PackageTaskAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword,
                packageTaskCreateTimeStart:$scope.queryParam.packageTaskCreateTimeStart,
                packageTaskCreateTimeEnd:$scope.queryParam.packageTaskCreateTimeEnd
            }, function(data){
                $scope.packageTaskList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPackageTaskList;
            });
        };
        $scope.getPackageTaskList();
    }];
});