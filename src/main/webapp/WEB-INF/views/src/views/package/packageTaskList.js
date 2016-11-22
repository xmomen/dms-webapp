/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "PackageTaskAPI", "$modal", "$ugDialog", "$filter",function($scope, PackageTaskAPI, $modal, $ugDialog,$filter){

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

        $scope.openJobOperationLogList = function (packageJob) {
            var modalInstance = $modal.open({
                templateUrl: 'jobOperationLogList.html',
                resolve: {
                    currentJob: function(){
                        return packageJob;
                    }
                },
                controller: ["$scope","JobOperationLogAPI","currentJob", "$modalInstance", function ($scope,JobOperationLogAPI, currentJob, $modalInstance) {
                    $scope.jobOperationLogList = [];
                    $scope.operationLogPageInfoSetting = {
                        pageSize:50,
                        pageNum:1
                    };
                    $scope.getJobOperationLogList = function(){
                        JobOperationLogAPI.query({
                            limit:$scope.operationLogPageInfoSetting.pageSize,
                            offset:$scope.operationLogPageInfoSetting.pageNum,
                            jobId:packageJob.id,
                            upc:$scope.operationLogPageInfoSetting.upc
                        }, function(data){
                            $scope.jobOperationLogList = data.data;
                            $scope.operationLogPageInfoSetting = data.pageInfo;
                            $scope.operationLogPageInfoSetting.loadData = $scope.getPackageTaskList;
                        });
                    };
                    $scope.getJobOperationLogList();

                    $scope.print = function(jobOperationLog){
                        LODOP.PRINT_INITA(0,0,"56.3mm","60.01mm","商品条码打印");
                        LODOP.ADD_PRINT_BARCODE(62,9,"50.96mm","10.21mm","128B",jobOperationLog.barCode);
                        LODOP.ADD_PRINT_TEXT(134,107,75,19,"采摘人:"+jobOperationLog.caizaiUser);
                        LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                        LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
                        LODOP.ADD_PRINT_TEXT(131,19,78,19,"检验人:"+jobOperationLog.jianceUser);
                        LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                        LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
                        LODOP.ADD_PRINT_TEXT(104,20,158,19,"产品名称:"+jobOperationLog.itemName);
                        LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                        LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
                        LODOP.ADD_PRINT_TEXT(145,19,100,20,"采摘点：吐鲁番");
                        LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                        LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
                        LODOP.ADD_PRINT_TEXT(160,19,137,20,"采摘时间：6:00-9:00");
                        LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                        LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
                        LODOP.ADD_PRINT_TEXT(118,20,119,19,"检测结果：μb="+jobOperationLog.nongCanLv);
                        LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
                        LODOP.SET_PRINT_STYLEA(0,"FontSize",8);

//                        LODOP.PRINT_DESIGN();
                        LODOP.PRINT();
                    }
                }]
            });
            modalInstance.result.then(function () {
            });
        };

        $scope.openDistributeModal = function (currentPackageTask) {
            var modalInstance = $modal.open({
                templateUrl: 'addPackageTask.html',
                controller: ["$scope", "PackageTaskAPI", "$modalInstance","currentPackageTask","UserAPI", "$rootScope", function ($scope, PackageTaskAPI, $modalInstance,currentPackageTask,UserAPI,$rootScope) {
                    $scope.packageUserList = [];
                    $scope.ugSelect2Config = {};
                    UserAPI.getCustomerManagerList({
                        userType:"baozhuangzu"
                    },function(data){
                        $scope.packageUserList = data;
                    });
                    $scope.company = {};
                    if(currentPackageTask){
                        $scope.packageTask = currentPackageTask;
                        $scope.packageTask.noDistributeValue = $scope.packageTask.totalItemQty - $scope.packageTask.distributeValue ;
                    }
                    $scope.errors = {};
                    $scope.addPackageTaskForm = {};
                    $scope.savePackageTask = function(){
                        $scope.errors = {};
                        if($scope.packageTask.countValue > $scope.packageTask.noDistributeValue){
                            $scope.errors.message = "不能大于未分配数";
                            return;
                        }
                        if($scope.addPackageTaskForm.validator.form()){
                            PackageTaskAPI.save($scope.packageTask, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                resolve: {
                    currentPackageTask: function () {
                        return currentPackageTask;
                    }
                }
            });
            modalInstance.result.then(function () {
                $scope.getPackageTaskList();
            });
        };

        $scope.getPackageTaskList();
    }];
});