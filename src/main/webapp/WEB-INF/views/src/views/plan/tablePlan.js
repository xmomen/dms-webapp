/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "TablePlanAPI", "$modal", "$ugDialog", function($scope, TablePlanAPI, $modal, $ugDialog){
        $scope.tablePlanList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getTablePlanList = function(){
            TablePlanAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.tablePlanList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getTablePlanList;
            });
        };
        $scope.stop = function(index){
            TablePlanAPI.stop({
                id: $scope.tablePlanList[index].id,
                locked: $scope.tablePlanList[index].isStop == 1 ? true : false
            });
        };

        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addTablePlan.html',
                controller: ["$scope", "TablePlanAPI", "$modalInstance","currentTablePlan","BasePlanAPI", "$rootScope", "$modalMemberAdd","MemberAPI", function ($scope, TablePlanAPI, $modalInstance,currentTablePlan,BasePlanAPI,$rootScope,$modalMemberAdd,MemberAPI) {
                    $scope.ugSelect2Config = {};
                    $scope.tablePlan = {};
                    if(currentTablePlan){
                        $scope.tablePlan = currentTablePlan;
                    }
                    $scope.getBasePlanList = function(){
                        $scope.pageInfoSetting = {
                            pageSize:1000,
                            pageNum:1
                        };
                        $scope.queryParam = {};
                        $scope.basePlanList = [];
                        BasePlanAPI.query({
                            limit:$scope.pageInfoSetting.pageSize,
                            offset:$scope.pageInfoSetting.pageNum
                        }, function(data){
                            $scope.basePlanList = data.data;
                            $scope.pageInfoSetting = data.pageInfo;
                            $scope.pageInfoSetting.loadData = $scope.getCategoryList;
                            $scope.ugSelect2Config.initSelectData($scope.tablePlan.cdPlanId);
                        });
                    }
                    $scope.getBasePlanList();
                    $scope.errors = null;
                    $scope.addTablePlanForm = {};
                    $scope.saveOrUpdateTablePlan = function(){
                        $scope.errors = null;
                        if($scope.addTablePlanForm.validator.form()){
                            if($scope.tablePlan.id){
                                TablePlanAPI.update($scope.tablePlan, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                TablePlanAPI.save($scope.tablePlan, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }

                        }
                    };
                    var bindMember = function(){
                        $modalMemberAdd.open({
                            currentMember:{
                                phoneNumber:$scope.tablePlan.phone
                            }
                        }).result.then(function (data) {
                                $scope.queryMemberByPhoneNumber();
                            });
                    };
                    $scope.queryMemberByPhoneNumber = function(){
                        if($scope.tablePlan.phone){
                            MemberAPI.query({
                                limit:1,
                                offset:1,
                                phoneNumber:$scope.tablePlan.phone
                            }, function(data){
                                if(data.data && data.data.length > 0){
                                    var member = data.data[0];
                                    debugger;
                                    $scope.tablePlan.cdMemberId = member.id;
                                    $scope.tablePlan.memberCode = member.memberCode;
                                    $scope.tablePlan.consigneeAddress = member.address;
                                    $scope.tablePlan.consigneeName = member.name;
                                    $scope.tablePlan.consigneePhone = member.phoneNumber;
                                }else{
                                    $ugDialog.confirm("未找到匹配手机号的客户，是否新增客户？").then(function(){
                                        bindMember();
                                    });
                                }
                            })
                        }else{
                            $ugDialog.alert("请输入手机号查询");
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                resolve: {
                    currentTablePlan: function () {
                        return $scope.tablePlanList[index];
                    }
                }
            });
            modalInstance.result.then(function () {
                $scope.getTablePlanList();
            });
        };
        $scope.removeTablePlan = function(index){
            $ugDialog.confirm("是否删除计划？").then(function(){
                TablePlanAPI.delete({
                    id: $scope.tablePlanList[index].id
                },function(){
                    $scope.getTablePlanList();
                });
            })
        };

        $scope.getTablePlanList();
    }];
});