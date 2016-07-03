/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope","ItemAPI", "CompanyAPI", "ItemCategoryAPI","$modal", "$ugDialog", "$state", "ContractAPI", "$modalMemberAdd", "$rootScope","$stateParams",
        function($scope, ItemAPI, CompanyAPI,ItemCategoryAPI, $modal, $ugDialog, $state, ContractAPI, $modalMemberAdd, $rootScope,$stateParams){
            //优惠的产品
            $scope.choseItemList = [];
            $scope.ugSelect2Config = {};
            var initData = function(){
                var id = $stateParams.id;
                ContractAPI.get({
                    id: id
                },function(data){
                    $scope.contract = data;
                    $scope.contract.id = id;
                    $scope.choseItemList = data.contractItemList;
                    $scope.ugSelect2Config.initSelectData($scope.contract.cdCompanyId);
                });
            }

            $scope.init = function(){
                $scope.addContractItem = {
                    contractType : 1
                };
                initData();
            }
            $scope.init();

            //单位
            $scope.companyList = [];
            $scope.ugSelect2Config = {};
            CompanyAPI.getCompanyList({},function(data){
                $scope.companyList = data;
            });
            //日期控件
            $scope.datepickerSetting = {
                datepickerPopupConfig:{
                    "current-text":"今天",
                    "clear-text":"清除",
                    "close-text":"关闭"
                },
                beginTime:{
                    opened:false
                },
                endTime:{
                    opened:false
                }
            };
            $scope.open = function($event, index) {
                $event.preventDefault();
                $event.stopPropagation();
                if(index == 0){
                    $scope.datepickerSetting.beginTime.opened = true;
                }else if(index == 1){
                    $scope.datepickerSetting.endTime.opened = true;
                }
            };

            //产品分类
            $scope.itemCategoryList = [];
            $scope.queryParam = {};
            ItemCategoryAPI.query({
                id:$scope.queryParam.id
            }, function(data){
                $scope.itemCategoryList = data;
                $rootScope.$broadcast("loadingTree");
            });
            //优惠方式改变 清空金额
            $scope.changeContractType = function(type){
                $scope.addContractItem.contractValue = "";
            }
            //产品
            $scope.pageInfoSetting = {
                pageSize:10,
                pageNum:1
            };
            $scope.queryParam = {};
            $scope.getItemList = function(categoryName){
                var choseItemId = null;
                if($scope.choseItemList && $scope.choseItemList.length > 0){
                    choseItemId = [];
                    for (var i = 0; i < $scope.choseItemList.length; i++) {
                        var obj = $scope.choseItemList[i];
                        choseItemId.push(obj.id);
                    }
                }
                if(categoryName){
                    $scope.queryParam.keyword = categoryName;
                }
                ItemAPI.query({
                    limit:$scope.pageInfoSetting.pageSize,
                    offset:$scope.pageInfoSetting.pageNum,
                    keyword:$scope.queryParam.keyword,
                    sellStatus:1,
                    exclude_ids:choseItemId
                }, function(data){
                    $scope.itemList = data.data;
                    $scope.pageInfoSetting = data.pageInfo;
                    $scope.pageInfoSetting.loadData = $scope.getItemList;
                });
            };
            //选择产品
            $scope.chooseItem = function(index,item) {
                if ($scope.addContractForm.validator.form()) {
                    if ($scope.addContractItem.contractType == 1) {
                        item.contractType = 1;
                        item.discountText = '无';
                        item.discount = 0;
                        item.contractValue = $scope.addContractItem.contractValue;
                        item.contractTypeName = "按固定金额";
                    } else {
                        item.contractTypeName = "按折扣";
                        item.discountText = $scope.addContractItem.contractValue;
                        item.discount = $scope.addContractItem.contractValue;
                        item.contractValue = parseFloat($scope.addContractItem.contractValue) * parseFloat(item.sellPrice);
                        item.contractType = 2;
                    }
                    item.cdItemId = item.id;
                    $scope.choseItemList.push(item);
                    $scope.itemList.splice(index, 1);
                }
            }
            //移除选择的产品
            $scope.removeChoseItem = function(index,item){
                $scope.choseItemList.splice(index,1);
                $scope.itemList.push(item);
            }
            //保存合同
            $scope.addContractForm = {};
            $scope.saveContract = function(){
                if($scope.choseItemList.length <=  0){
                    $ugDialog.alert("请选择产品");
                    return false;
                }
                if($scope.addContractForm.validator.form()){
                    $ugDialog.confirm("確定修改該合同？").then(function(){
                        $scope.contract.contractItemList = $scope.choseItemList;
                        ContractAPI.update(
                            $scope.contract
                        , function(){
                            $ugDialog.alert("修改成功");
                            $state.go('contract');
                        });
                    })
                }
            }
    }];
});