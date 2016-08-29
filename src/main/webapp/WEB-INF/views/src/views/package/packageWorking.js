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

        /**
         * 下一个按钮功能
         * @param id
         */
        $scope.nextPackageTask = function(id){
            PackageTaskAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                nextPackageTaskId:id
            }, function(data){
                if(data.data.length == 0){
                    $ugDialog.alert("无下一个任务了！")
                    return;
                }
                $scope.packageTaskList = data.data;
                $scope.packageTask = $scope.packageTaskList[0];
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPackageTaskList;
                $scope.getJobOperationLogList($scope.packageTask.id);
            });
        }
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
            var max = parseFloat($scope.packageTask.spec) + parseFloat(($scope.packageTask.spec * 0.1));
            var min = parseFloat($scope.packageTask.spec);
            if($scope.packageTask.weight < min){
                $("#weight").focus();
                $("#weight").select();
                $("#weight").val("");
                return;
            }
            if($scope.packageTask.weight.length > 4){
                $("#weight").focus();
                $("#weight").select();
                $("#weight").val("");
                return;
            }
            var barCode = $scope.packageTask.itemCode + "" + $scope.packageTask.weight + Math.floor(Math.random()*10000);
            var LODOP=getLodop();
//            if($scope.packageTask.weight > max){
//                //元素设置为readonly
//                $("#weight").attr("readonly","readonly");
//                $ugDialog.confirm("超过最大重量，是否打印？").then(function(){
//                    $scope.print(barCode);
//                    //去除input元素的readonly属性
//                    $("#weight").removeAttr("readonly");
//                    PackageTaskAPI.packageWorking({
//                        id:$scope.packageTask.id,
//                        barCode:barCode
//                    },function(data){
//                        $scope.packageTask.finishValue +=1;
//                        $scope.packageTask.noFinishValue -=1;
//                        $scope.getJobOperationLogList($stateParams.id)
//                    })
//                    $("#weight").focus();
//                    $("#weight").select();
//                    $("#weight").val("");
//                },function(){
//                    //去除input元素的readonly属性
//                    $("#weight").removeAttr("readonly");
//                    $("#weight").focus();
//                    $("#weight").select();
//                    $("#weight").val("");
//                })
//            }else{
            $scope.print(barCode);
            PackageTaskAPI.packageWorking({
                id:$scope.packageTask.id,
                barCode:barCode
            },function(data){
                $scope.packageTask.finishValue +=1;
                $scope.packageTask.noFinishValue -=1;
                $scope.getJobOperationLogList($scope.packageTask.id)
                //如果完成自动跳转到下一个任务
                if($scope.packageTask.noFinishValue == 0){
                    $scope.nextPackageTask($scope.packageTask.id);
                }
            })
            $("#weight").focus();
            $("#weight").select();
            $("#weight").val("");
//            }
        }


        //回车批量生成条码
        $scope.printBatchBarCodeEvent = function(e){
            var keycode = window.event?e.keyCode:e.which;
            if(keycode==13){
                $scope.printBatchBarCode();
            }
        }

        $scope.printBatchBarCode = function(){
            if($scope.packageTask.batch == undefined){
                $ugDialog.warn("请输入批量打印数量！");
                $("#batch").focus();
                $("#batch").select();
                $("#batch").val("");
                return;
            }
            if($scope.packageTask.noFinishValue < $scope.packageTask.batch){
                $ugDialog.warn("输入的数量超过未包装数！");
                $("#batch").focus();
                $("#batch").select();
                $("#batch").val("");
                return;
            }

            var LODOP=getLodop();
            var barCodes = "";
            for(var i = 0;i<$scope.packageTask.batch;i++){
                //重量默认商品规格
                var barCode = $scope.packageTask.itemCode + "" + $scope.packageTask.spec + Math.floor(Math.random()*(9999-1000+1)+1000);
                $scope.print(barCode);
                $scope.packageTask.finishValue +=1;
                $scope.packageTask.noFinishValue -=1;
                barCodes = barCodes +barCode+",";
            }
            PackageTaskAPI.packageWorking({
                id:$scope.packageTask.id,
                barCode:barCodes
            },function(data){
                $scope.getJobOperationLogList($scope.packageTask.id)
                //如果完成自动跳转到下一个任务
                if($scope.packageTask.noFinishValue == 0){
                    $scope.nextPackageTask($scope.packageTask.id);
                }
            });
            $("#batch").focus();
            $("#batch").select();
            $("#batch").val("");
        }


        $scope.print = function(barCode){
            LODOP.PRINT_INITA(0,0,"56.3mm","60.01mm","商品条码打印");
            LODOP.ADD_PRINT_BARCODE(62,9,"50.96mm","10.21mm","128B",barCode);
            LODOP.ADD_PRINT_TEXT(134,107,75,19,"采摘人:"+$scope.packageTask.caizaiUser);
            LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(131,19,78,19,"检验人:"+$scope.packageTask.jianceUser);
            LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(104,20,158,19,"产品名称:"+$scope.packageTask.itemName);
            LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(145,19,100,20,"采摘点：吐鲁番");
            LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(160,19,137,20,"采摘时间：6:00-9:00");
            LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
            LODOP.ADD_PRINT_TEXT(118,20,119,19,"检测结果：ub="+$scope.packageTask.nongCanLv);
            LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
            LODOP.SET_PRINT_STYLEA(0,"FontSize",8);

            LODOP.PRINT();
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