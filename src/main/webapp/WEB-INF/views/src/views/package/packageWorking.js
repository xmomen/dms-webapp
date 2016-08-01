/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackageTaskAPI", "$modal", "$ugDialog","$stateParams","JobOperationLogAPI", function($scope, PackageTaskAPI, $modal, $ugDialog,$stateParams,JobOperationLogAPI){
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

        $scope.jobOperationLogList = [];
        $scope.operationLogPageInfoSetting = {
            pageSize:50,
            pageNum:1
        };
        $scope.getJobOperationLogList = function(id){
            JobOperationLogAPI.query({
                limit:$scope.operationLogPageInfoSetting.pageSize,
                offset:$scope.operationLogPageInfoSetting.pageNum,
                jobId:id
            }, function(data){
                $scope.jobOperationLogList = data.data;
                $scope.operationLogPageInfoSetting = data.pageInfo;
                $scope.operationLogPageInfoSetting.loadData = $scope.getPackageTaskList;
            });
        };

        //作废
        $scope.cancelJobOperationLog = function(index){
           var jobOperationLog =  $scope.jobOperationLogList[index];
            JobOperationLogAPI.delete({
                id:jobOperationLog.id
            },function(data){
                $ugDialog.alert("作废成功");
                $scope.packageTask.finishValue -=1;
                $scope.packageTask.noFinishValue +=1;
                $scope.getJobOperationLogList($stateParams.id)
            })
        }
        //回车生成条码
        $scope.printBarCodeEvent = function(e){
            var keycode = window.event?e.keyCode:e.which;
            if(keycode==13){
                $scope.printBarCode();
            }
        }

        $scope.printBarCode = function(){
            if($scope.packageTask.noFinishValue == 0){
                $ugDialog.warn("全部包装完成！请换下一个任务");
                $("#weight").focus();
                $("#weight").select();
                $("#weight").value("");
                return;
            }
            if($scope.packageTask.weight == undefined){
                $ugDialog.warn("请称重！");
                $("#weight").focus();
                $("#weight").select();
                $("#weight").val("");
                return;
            }
            var barCode = $scope.packageTask.itemCode + "" + $scope.packageTask.weight + Math.floor(Math.random()*10000);
            var LODOP=getLodop();

            LODOP.PRINT_INITA(0,0,"40mm","40mm","商品条码打印");
            LODOP.ADD_PRINT_BARCODE(46,5,"37.99mm","14.71mm","128B",barCode);
            LODOP.ADD_PRINT_TEXT(128,76,75,19,"采摘人:张三");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(128,2,78,19,"检验人:李四");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(109,3,125,20,"检测结果：ub=6.5%");
            LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);

            LODOP.PRINT_DESIGN();
            PackageTaskAPI.packageWorking({
                id:$scope.packageTask.id,
                barCode:barCode
            },function(data){
                $scope.packageTask.finishValue +=1;
                $scope.packageTask.noFinishValue -=1;
                $scope.getJobOperationLogList($stateParams.id)
           })
            $("#weight").focus();
            $("#weight").select();
            $("#weight").val("");
        }
        var initialize = function(){
            $("#weight").focus();
            $("#weight").select();
            $("#weight").val("");
            $scope.getPackageTaskList($stateParams.id);
            $scope.getJobOperationLogList($stateParams.id);
        }
        initialize();
    }];
});