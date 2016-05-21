/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackageTaskAPI", "$modal", "$ugDialog","$stateParams", function($scope, PackageTaskAPI, $modal, $ugDialog,$stateParams){
        $scope.packageTaskList = [];
        $scope.pageInfoSetting = {
            pageSize:1,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getPackageTaskList = function(id){
            PackageTaskAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                packageTaskId:id
            }, function(data){
                $scope.packageTaskList = data.data;
                $scope.packageTask = $scope.packageTaskList[0];
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPackageTaskList;
            });
        };

        $scope.printBarCode = function(){
            if($scope.packageTask.noFinishValue == 0){
                $ugDialog.warn("全部包装完成！请换下一个任务");
                $("#weight").focus();
                $("#weight").select();
                return;
            }
            if($scope.packageTask.weight == undefined){
                $ugDialog.warn("请称重！");
                $("#weight").focus();
                $("#weight").select();
                return;
            }
            var barCode = $scope.packageTask.itemCode + "" + $scope.packageTask.weight + Math.floor(Math.random()*10000);
            var LODOP=getLodop();
            LODOP.PRINT_INITA(0,0,"2.25in","1.25in","包裹号打印");
            LODOP.SET_PRINT_PAGESIZE(1,"2.25in","1.25in","");
            LODOP.ADD_PRINT_BARCODE(4,14,"1.9479in","0.9063in","128B",barCode);
            LODOP.PREVIEW();
            PackageTaskAPI.packageWorking({
                id:$scope.packageTask.id,
                barCode:barCode
            },function(data){
                $scope.packageTask.finishValue +=1;
                $scope.packageTask.noFinishValue -=1;
           })
            $("#weight").focus();
            $("#weight").select();
        }
        var initialize = function(){
            $("#weight").focus();
            $("#weight").select();
            $scope.getPackageTaskList($stateParams.id);
        }
        initialize();
    }];
});