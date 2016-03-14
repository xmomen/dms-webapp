/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "ScheduleAPI", "$modal", "$ugDialog", function($scope, ScheduleAPI, $modal, $ugDialog){
        $scope.scheduleList = [];
        $scope.pageInfoSetting = {
            pageSize:50,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getScheduleList = function(){
            ScheduleAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                if(data.data && data.data.length > 0){
                    $scope.scheduleList = data.data;
                    $scope.pageInfoSetting = data.pageInfo;
                    $scope.pageInfoSetting.loadData = $scope.getScheduleList;
                }
            });
        };
        $scope.restart = function(index){
            ScheduleAPI.update({
                id: $scope.scheduleList[index].id,
                action:"restart"
            }, function(data){
                $ugDialog.alert("任务已重启");
                $scope.getScheduleList();
            })
        };
        $scope.remove = function(index){
            var schedule = $scope.scheduleList[index];
            $ugDialog.confirm("是否删除定时任务：" + schedule.jobName + "？").then(function(data){
                ScheduleAPI.remove({
                    id:schedule.id
                }, function(){
                    $scope.getScheduleList();
                    $ugDialog.alert("删除成功！")
                }, function(data){
                    $ugDialog.warn({
                        title:"删除失败",
                        content:"错误信息：" + data.data.message
                    })
                });
            });
        };
        $scope.pause = function(index){
            ScheduleAPI.update({
                id: $scope.scheduleList[index].id,
                action:"pause"
            }, function(data){
                $ugDialog.alert("任务已暂停");
                $scope.getScheduleList();
            })
        };
        $scope.open = function (index) {
            $modal.open({
                templateUrl: 'addScheduleJob.html',
                controller: ["$scope", "$modalInstance", "ScheduleAPI", function ($scope, $modalInstance, ScheduleAPI) {
                    $scope.addScheduleForm = {};
                    $scope.schedule = {};
                    $scope.errors = null;
                    $scope.addSchedule = function(){
                        if($scope.addScheduleForm.validator.form()){
                            $scope.errors = null;
                            ScheduleAPI.save($scope.schedule, function(){
                                $modalInstance.close();
                            }, function(data){
                                if(data.data){
                                    $scope.errors = data.data.message;
                                }
                            });
                        }
                    };
                    $scope.scheduleTemplates = [];
                    $scope.getScheduleTemplate = function(){
                        ScheduleAPI.getTemplates(function(data){
                            $scope.scheduleTemplates = data;
                        });
                    };
                    $scope.getScheduleTemplate();
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                size: "lg"
            }).result.then(function(){
                $scope.getScheduleList();
            });
        };
        $scope.openView = function(index){
            openUpdate(index, "view");
        };
        $scope.openUpdate = function(index){
            openUpdate(index, "update");
        };
        var openUpdate = function (index, action) {
            $modal.open({
                templateUrl: 'updateScheduleJob.html',
                controller: ["$scope", "$modalInstance", "schedule", "action", "ScheduleAPI", function ($scope, $modalInstance, schedule, action, ScheduleAPI) {
                    $scope.schedule = schedule;
                    $scope.setting = {
                        disabled: true
                    };
                    $scope.updateScheduleForm = {};
                    $scope.updateSchedule = function(){
                        if($scope.updateScheduleForm.validator.form()){
                            ScheduleAPI.update({
                                id:$scope.schedule.id,
                                jobName:$scope.schedule.jobName,
                                jobGroup:$scope.schedule.jobGroup,
                                description:$scope.schedule.jobDescription,
                                cronExpression:$scope.schedule.cronExpression,
                                jobTriggerId:$scope.schedule.triggerId
                            }, function(){
                                $modalInstance.close();
                            }, function(data){
                                $scope.errors = data.data.message;
                            });
                        }
                    };
                    $scope.getScheduleTemplate = function(){
                        ScheduleAPI.getTemplates(function(data){
                            $scope.scheduleTemplates = data;
                        });
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    $scope.getScheduleTemplate();
                    if(action == "update"){
                        $scope.setting.disabled = false;
                    }
                }],
                size: "lg",
                resolve: {
                    action: function(){
                        return action;
                    },
                    schedule: function () {
                        return $scope.scheduleList[index];
                    }
                }
            });
        };
        $scope.getScheduleList();
    }];
});