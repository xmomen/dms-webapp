/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrderAPI", "ItemAPI", "MemberAPI", "$modal", "$ugDialog", function($scope, OrderAPI, ItemAPI, MemberAPI, $modal, $ugDialog){
        $scope.order = {
            orderType:0
        };
        $scope.addOrderForm = {};
        $scope.errors = null;
        $scope.pageSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getItemList = function(){
            ItemAPI.query({
                limit:$scope.pageSetting.pageSize,
                offset:$scope.pageSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.itemList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getItemList;
            });
        };
        $scope.queryMemberByPhoneNumber = function(){
            if($scope.order.phone){
                MemberAPI.query({
                    limit:1,
                    offset:1,
                    phoneNumber:$scope.order.phone
                }, function(data){
                    if(data.data && data.data.length > 0){
                        var member = data.data[0];
                        console.log(data.data[0]);
                        $scope.order.memberId = member.id;
                        $scope.order.cdCompanyId = member.cdCompanyId;
                        $scope.order.name = member.name;
                        $scope.order.phone = member.phoneNumber;
                        $scope.order.consigneeAddress = member.address;
                        $scope.order.consigneeName = member.name;
                        $scope.order.consigneePhone = member.phoneNumber;
                        $scope.order.spareAddress = member.spareAddress;
                        $scope.order.spareName = member.spareName;
                        $scope.order.spareTel = member.spareTel;
                        $scope.order.spareAddress2 = member.spareAddress2;
                        $scope.order.spareName2 = member.spareName2;
                        $scope.order.spareTel2 = member.spareTel2;
                    }else{
                        $ugDialog.alert("未找到匹配手机号的客户");
                    }
                })
            }
        };
        $scope.saveOrder = function(){
            if($scope.addOrderForm.validator.form()){
                OrderAPI.save($scope.order, function(){
                    $ugDialog.alert("订单提交成功！");
                }, function(data){
                    $scope.errors = data.data;
                })
            }
        }
        $scope.getItemList();
    }];
});