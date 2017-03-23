/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "${domainObjectClassName}API", "$dialog", function($scope, $modal, ${domainObjectClassName}API, $dialog){
        $scope.pageSetting = {
            checkAll : false,
            queryBtnLoading : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        // 重置
        $scope.reset = function(){
            $scope.queryParam={};
            $scope.get${domainObjectClassName}List();
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.get${domainObjectClassName}List = function(){
            $scope.pageSetting.queryBtnLoading = true;
            ${domainObjectClassName}API.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.${domainObjectName}List = data.data;
                $scope.pageInfoSetting = data.pageInfo;
            }).$promise.finally(function(){
                $scope.pageSetting.queryBtnLoading = false;
            });;
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.${domainObjectName}List){
                return;
            }
            for (var i = 0; i < $scope.${domainObjectName}List.length; i++) {
                $scope.${domainObjectName}List[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.${domainObjectName}List){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.${domainObjectName}List.length; i++) {
                if($scope.${domainObjectName}List[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.${domainObjectName}List.length){
                $scope.pageSetting.checkAll = true;
            }else{
                $scope.pageSetting.checkAll = false;
            }
        };
        // 新增
        $scope.add = function(index){
            $scope.openModal("ADD", index);
        };
        // 查看
        $scope.view = function(index){
            $scope.openModal("VIEW", index);
        };
        // 修改
        $scope.update = function(index){
            $scope.openModal("UPDATE", index);
        };
        // 弹出
        $scope.openModal = function(action, index){
            $modal.open({
                templateUrl: '${domainObjectName}_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if(index >= 0 && $scope.${domainObjectName}List[index] && $scope.${domainObjectName}List[index].id){
                            params.id = $scope.${domainObjectName}List[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "${domainObjectClassName}API", "Params", "$dialog", function($scope, $modalInstance, $modal, ${domainObjectClassName}API, Params, $dialog){
                    //$scope.${domainObjectName} = null;
                    $scope.pageSetting = {
                        formDisabled : true,
                        saveBtnLoading : false
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.${domainObjectName} = ${domainObjectClassName}API.get({
                            id: Params.id
                        });
                    }
                    $scope.${domainObjectName}DetailForm = {};
                    $scope.save${domainObjectClassName} = function(){
                        if($scope.${domainObjectName}DetailForm.validator.form()){
                            $dialog.confirm("是否保存数据？").then(function(){
                                $scope.pageSetting.saveBtnLoading = true;
                                if ( !$scope.${domainObjectName}.id ) {
                                    ${domainObjectClassName}API.create($scope.${domainObjectName}, function(data,headers){
                                        $dialog.success("新增成功");
                                        $modalInstance.close();
                                    }).$promise.finally(function(){
                                        $scope.pageSetting.saveBtnLoading = false;
                                    });
                                }else {
                                    ${domainObjectClassName}API.update($scope.${domainObjectName}, function(data,headers){
                                        $dialog.success("更新成功");
                                        $modalInstance.close();
                                    }).$promise.finally(function(){
                                        $scope.pageSetting.saveBtnLoading = false;
                                    });
                                }
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.get${domainObjectClassName}List();
            });
        };
        // 删除
        $scope.delete = function(index){
            $dialog.confirm("请确认是否删除").then(function(){
                ${domainObjectClassName}API.delete({id:$scope.${domainObjectName}List[index].id}, function(){
                    $scope.get${domainObjectClassName}List();
                });
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.${domainObjectName}List.length; i++) {
                var obj = $scope.${domainObjectName}List[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    ${domainObjectClassName}API.delete({ids:choiceItems}, function(){
                        $scope.get${domainObjectClassName}List();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            ${domainObjectClassName}API.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        var init = function(){
            $scope.get${domainObjectClassName}List();
        };
        init();
    }]
});