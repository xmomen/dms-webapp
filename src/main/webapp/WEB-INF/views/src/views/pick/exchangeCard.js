/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "CouponAPI","$modal", "$ugDialog","$stateParams", function($scope, CouponAPI,$modal, $ugDialog,$stateParams){
        $scope.readOldCard = function(){
            debugger
            if($scope.pick.couponNo && $scope.pick.password){
                //查找卡信息
                CouponAPI.readCard({
                    couponNo:$scope.pick.couponNo,
                    password:$scope.pick.password
                }, function (data) {
                    debugger;
                    if(data.userName == null || data.userName == undefined){
                        $ugDialog.warn("卡号或者密码错误!");
                        $scope.pick.couponNo = "";
                        $scope.pick.password = "";
                        $("#couponNo").focus();
                        $("#couponNo").select();
                        return;
                    };
                    $scope.pick.userName = data.userName;
                    $scope.pick.phoneNumber = data.phoneNumber;
                    $scope.pick.couponNo = data.couponNo;
                    $scope.pick.couponPrice = data.couponPrice;
                }, function (data) {
                    $ugDialog.warn(data.data.error);
                })
            }
        }
        $scope.readCard = function(){
                var strls = "";
                var errorno = "";
                var BLOCK0_EN = 0x01;//读第一块的(16个字节)
                var BLOCK1_EN = 0x02;//读第二块的(16个字节)
                var BLOCK2_EN = 0x04;//读第四块的(16个字节)

                var EXTERNKEY = 0x10;//用明码认证密码,产品开发完成后，建议把密码放到设备的只写区，然后用该区的密码后台认证，这样谁都不知道密码是多少，需要这方面支持请联系
                //指定控制字
                var myctrlword=BLOCK0_EN + BLOCK1_EN + BLOCK2_EN + EXTERNKEY;
                //指定区号
                var myareano = 8; //指定为第8区
                //批定密码模式
                var authmode = 1; //大于0表示用A密码认证，推荐用A密码认证

                //指定序列号，未知卡序列号时可指定为8个0
               var  mypiccserial="00000000";

                //指定密码，以下密码为厂家出厂密码
                var mypicckey = "ffffffffffff";
                debugger;
                strls=IcCardReader.piccreadex(myctrlword, mypiccserial,myareano,authmode,mypicckey);
                errorno = strls.substr(0,4);
                switch(errorno)
                {
                    case "ER08":
                        alert("寻不到卡");
                        break;
                    case "ER09":
                        alert("寻不到卡");
                        break;
                    case "ER10":
                        alert("寻不到卡");
                        break;

                    case "ER11":
                        $ugDialog.alert("密码认证错误");
                        break;
                    case "ER12":
                        $ugDialog.alert("密码认证错误");
                        break;
                    case "ER13":
                        $ugDialog.alert("读卡错误");
                        break;

                    case "ER14":
                        $ugDialog.alert("写卡错误");
                        break;

                    case "ER21":
                        $ugDialog.alert("没找到动态库");
                        break;
                    case "ER22":
                        $ugDialog.alert("动态库或驱动程序异常");
                        break;

                    case "ER23":
                        $ugDialog.alert("读卡器未插上或动态库或驱动程序异常");
                        break;
                    case "ER24":
                        $ugDialog.alert("操作超时，一般是动态库没有反应");
                        break;
                    case "ER25":
                        $ugDialog.alert("发送字数不够");
                        break;
                    case "ER26":
                        $ugDialog.alert("发送的CRC错");
                        break;
                    case "ER27":
                        $ugDialog.alert("接收的字数不够");
                        break;
                    case "ER28":
                        $ugDialog.alert("接收的CRC错");
                        break;
                    case "ER29":
                        $ugDialog.alert("函数输入参数格式错误,请仔细查看"	);
                        break;
                    default :
                        //读卡成功,其中ER00表示完全成功,ER01表示完全没读到卡数据，ER02表示仅读该卡的第一块成功,，ER02表示仅读该卡的第一二块成功，这是刷卡太快原因
                        var e=new RegExp("F","g");
                        $scope.pick.newCouponNo = strls.substr(14,32).replace(e,"");
                        $scope.pick.newPassword = strls.substr(46,32).replace(e,"");
                        IcCardReader.pcdbeep(200);//100表示响100毫秒
                        break;
                }
            }


        $scope.pickForm = {};
        $scope.exchangeCard = function(){
            if($scope.pick.couponNo == "" || $scope.pick.couponNo == null || $scope.pick.couponNo == undefined ||  $scope.pick.newCouponNo == "" || $scope.pick.newCouponNo == null || $scope.pick.newCouponNo == undefined){
                $ugDialog.alert("请刷卡");
                return;
            }
            CouponAPI.exchangeCard({
                oldCouponNo:$scope.pick.couponNo,
                oldPassword:$scope.pick.password,
                newCouponNo:$scope.pick.newCouponNo,
                newPassword:$scope.pick.newPassword
            },function(){
                $ugDialog.alert("换卡成功");
                $scope.pick = {}
            }, function(data){
                IcCardReader.pcdbeep(200);//100表示响100毫秒
                $ugDialog.warn(data.data.error);
            })
        }

        var initialize = function(){
            $scope.pick = {};
            $("#couponNo").focus();
            $("#couponNo").select();
        }
        initialize();
    }];
});