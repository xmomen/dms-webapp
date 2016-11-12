/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "TablePlanAPI", "$modal", "$ugDialog", function($scope, TablePlanAPI, $modal, $ugDialog){
        $scope.isCouponNumberExist = false;
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

        $scope.createPlanOrder = function(){
            TablePlanAPI.createPlanOrder({

            }, function(data){
                $ugDialog.alert("执行成功");
                $scope.getTablePlanList();
            });
        };

        $scope.open = function (index) {
            var modalInstance = $modal.open({
                templateUrl: 'addTablePlan.html',
                controller: ["$scope", "TablePlanAPI", "$modalInstance","currentTablePlan","BasePlanAPI","CouponAPI" ,"$rootScope", "$modalMemberAdd","MemberAPI", function ($scope, TablePlanAPI, $modalInstance,currentTablePlan,BasePlanAPI,CouponAPI,$rootScope,$modalMemberAdd,MemberAPI) {
                    $scope.chooseTablePlans = [];
                    $scope.ugSelect2Config = {};
                    $scope.tablePlan = {
                    };

                    $scope.datepickerSetting = {
                        datepickerPopupConfig:{
                            "current-text":"今天",
                            "clear-text":"清除",
                            "close-text":"关闭"
                        },
                        beginTime:{
                            opened:false
                        }
                    };
                    $scope.open = function($event, index) {
                        $event.preventDefault();
                        $event.stopPropagation();
                        if(index == 0){
                            $scope.datepickerSetting.beginTime.opened = true;
                        }
                    };

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
                            if(currentTablePlan){
                                $scope.ugSelect2Config.initSelectData(currentTablePlan.cdPlanId);
                            }
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
                                    if($scope.isCouponNumberExist){
                                        $scope.tablePlan.tablePlans = $scope.chooseTablePlans;
                                        TablePlanAPI.save($scope.tablePlan, function(){
                                            $modalInstance.close();
                                        }, function(data){
                                            $scope.errors = data.data;
                                        });
                                    }else{
                                        $ugDialog.alert("请先查询卡号信息");
                                    }
                                }
                                $scope.isCouponNumberExist = false;
                            }
                    };
                    var bindMember = function(){
                        $modalMemberAdd.open({
                            currentMember:{
                                couponNumber:$scope.tablePlan.couponNumber
                            }
                        }).result.then(function (data) {
                                $scope.queryMemberByCouponNumber();
                            });
                    };
                    $scope.queryMemberByCouponNumber = function(){
                        if($scope.tablePlan.couponNumber){
                            CouponAPI.query({
                                limit:1,
                                offset:1,
                                couponNumber:$scope.tablePlan.couponNumber,
                                couponType:1
                            }, function(data){
                                if(data.data && data.data.length > 0){
                                    $scope.isCouponNumberExist = true;
                                    var coupon = data.data[0];
                                    if(coupon.memberId){
                                        MemberAPI.get({
                                            id:coupon.memberId
                                        },function(data){
                                            var member = data;
                                                $scope.tablePlan.cdMemberId = member.id;
                                                $scope.tablePlan.memberCode = member.memberCode;
                                                $scope.tablePlan.consigneeAddress = member.address;
                                                $scope.tablePlan.consigneeName = member.name;
                                                $scope.tablePlan.consigneePhone = member.phoneNumber;
                                        })
                                    }
                                    else{
                                        $ugDialog.confirm("未找到客户信息，是否新增客户？").then(function(){
                                            bindMember();
                                        });
                                    }
                                }else{
                                    $ugDialog.alert("卡号不存在！");
                                }
                            })
                        }else{
                            $ugDialog.alert("请输入卡号");
                        }
                    };

                    //添加计划生效时间
                    $scope.changeSelect =function(){
                        if($scope.tablePlan.id == null){
                            $scope.chooseTablePlans=[];
                            var cdPlanIdList = $scope.tablePlan.cdPlanIds;
                            for(var i = 0;i<cdPlanIdList.length;i++){
                                //获取计划名称
                                var planName = "";
                                for(var j=0; j<$scope.basePlanList.length;j++){
                                    if($scope.basePlanList[j].id == cdPlanIdList[i]){
                                        planName = $scope.basePlanList[j].planName;
                                        break;
                                    }
                                }
                                var chooseTablePlan ={
                                    cdPlanId:cdPlanIdList[i],
                                    planName:planName,
                                    isStop:0
                                }
                                $scope.chooseTablePlans.push(chooseTablePlan);
                        }
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
                },
                size : 'lg'
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