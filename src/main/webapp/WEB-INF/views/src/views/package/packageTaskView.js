/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackageTaskAPI", "$modal", "$ugDialog","$stateParams", function($scope, PackageTaskAPI, $modal, $ugDialog,$stateParams){

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
        $scope.currentDate = function(){
            var myDate = new Date();
            var fullYear = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
            var month = myDate.getMonth() + 1;       //获取当前月份(0-11,0代表1月)
            if(month < 10){
                month = '0'+month;
            }
            var date = myDate.getDate();        //获取当前日(1-31)
            if(date < 10){
                date = '0'+date;
            }
            return fullYear+"-"+month+"-"+date;
        }

        $scope.packageTaskList = [];
        $scope.pageInfoSetting = {
            pageSize:100,
            pageNum:1
        };
        $scope.queryParam = {
            packageTaskCreateTimeStart :$scope.currentDate(),
            packageTaskCreateTimeEnd:$scope.currentDate()
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