/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI","$modal", "$ugDialog","$stateParams", function($scope, CouponAPI,$modal, $ugDialog,$stateParams){

        $scope.readCard = function(){
                if($scope.searchCouponNo){
                    //查找卡信息
                    CouponAPI.readCard({
                        couponNo:$scope.searchCouponNo,
                        password:""
                    }, function (data) {
                        if(data.userName == null || data.userName == undefined){
                            $ugDialog.warn("卡不能识别!");
                            $scope.pick.couponNo = "";
                            $scope.pick.password = "";
                            return;
                        };
                        $scope.pick.userName = data.userName;
                        $scope.pick.phoneNumber = data.phoneNumber;
                        $scope.pick.couponNo = data.couponNo;
                        $scope.pick.couponPrice = data.couponPrice;
                    }, function (data) {
                        $ugDialog.warn(data.data.error);
                    })
                }else{
                    $ugDialog.alert("请输入卡号/密码")
                }
            }


        $scope.pickForm = {};
        $scope.cardRecharge = function(){
            debugger;
            if($scope.pick.couponNo == "" || $scope.pick.couponNo == null || $scope.pick.couponNo == undefined  ){
                $ugDialog.alert("请输入卡号");
                return;
            }
            CouponAPI.cardRecharge({
                couponNo : $scope.pick.couponNo,
                rechargePrice : $scope.pick.rechargePrice
            },function(){
                $ugDialog.alert("充值成功");
                $scope.pick = {}
            }, function(data){
                $ugDialog.warn(data.data.error);
            })
        }

        var initialize = function(){
            $scope.pick = {};
        }
        initialize();
    }];
});