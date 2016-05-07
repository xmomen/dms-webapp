/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "DictionaryGroupAPI", "DictionaryAPI", "$modal", "$ugDialog", function($scope, DictionaryGroupAPI, DictionaryAPI, $modal, $ugDialog){
        $scope.dictionaryGroupList = [];
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.currentDictionaryGroup;
        $scope.choiceGroup = function(index){
            $scope.currentDictionaryGroup = $scope.dictionaryGroupList[index];
            $scope.getDictionaryList();
        }
        $scope.getDictionaryGroupList = function(){
            DictionaryGroupAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                keyword:$scope.queryParam.keyword
            }, function(data){
                $scope.dictionaryGroupList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getDictionaryGroupList;
            });
        };
        $scope.removeDictionaryGroup = function(index){
            $ugDialog.confirm("是否删除此数据字典？").then(function(){
                DictionaryGroupAPI.delete({
                    id: $scope.dictionaryGroupList[index].id
                }, function(){
                    $scope.getDictionaryGroupList();
                });
            })
        };
        $scope.updateDictionaryGroup = function(index){
            $scope.open(angular.copy($scope.dictionaryGroupList[index]));
        };
        $scope.open = function (dictionaryGroup) {
            var modalInstance = $modal.open({
                templateUrl: 'addDictionaryGroup.html',
                resolve: {
                    CurrentDictionaryGroup: function(){
                        return dictionaryGroup;
                    }
                },
                controller: ["$scope", "DictionaryGroupAPI", "CurrentDictionaryGroup", "$modalInstance", function ($scope, DictionaryGroupAPI, CurrentDictionaryGroup, $modalInstance) {
                    $scope.dictionaryGroup = {};
                    if(CurrentDictionaryGroup){
                        $scope.dictionaryGroup = CurrentDictionaryGroup;
                    }
                    $scope.errors = null;
                    $scope.addDictionaryGroupForm = {};
                    $scope.saveDictionaryGroup = function(){
                        $scope.errors = null;
                        if($scope.addDictionaryGroupForm.validator.form()){
                            if($scope.dictionaryGroup.id){
                                DictionaryGroupAPI.update($scope.dictionaryGroup, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                DictionaryGroupAPI.save($scope.dictionaryGroup, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getDictionaryGroupList();
            });
        };

        $scope.getDictionaryGroupList();


        $scope.dictionaryList = [];
        $scope.childPageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.dictionaryQueryParam = {};
        $scope.getDictionaryList = function(){
            DictionaryAPI.query({
                group_id:$scope.currentDictionaryGroup.id,
                limit:$scope.childPageInfoSetting.pageSize,
                offset:$scope.childPageInfoSetting.pageNum,
                keyword:$scope.dictionaryQueryParam.keyword
            }, function(data){
                $scope.dictionaryList = data.data;
                $scope.childPageInfoSetting = data.pageInfo;
                $scope.childPageInfoSetting.loadData = $scope.getDictionaryList;
            });
        };
        $scope.removeDictionary = function(index){
            $ugDialog.confirm("是否删除此数据字典？").then(function(){
                DictionaryAPI.delete({
                    group_id:$scope.currentDictionaryGroup.id,
                    id: $scope.dictionaryList[index].id
                }, function(){
                    $scope.getDictionaryList();
                });
            })
        };
        $scope.updateDictionary = function(index){
            $scope.openChild(angular.copy($scope.dictionaryList[index]));
        };
        $scope.openChild = function (dictionary) {
            if(!$scope.currentDictionaryGroup){
                $ugDialog.alert("请选择一项数据字典组");
                return;
            }
            var modalInstance = $modal.open({
                templateUrl: 'addDictionary.html',
                resolve: {
                    CurrentDictionary: function(){
                        if(dictionary){
                            dictionary.sysDictionaryId = $scope.currentDictionaryGroup.id;
                            dictionary.groupDesc = $scope.currentDictionaryGroup.dictionaryDesc;
                        }else{
                            dictionary = {
                                sysDictionaryId : $scope.currentDictionaryGroup.id,
                                groupDesc : $scope.currentDictionaryGroup.dictionaryDesc
                            };
                        }
                        return dictionary;
                    }
                },
                controller: ["$scope", "DictionaryAPI", "CurrentDictionary", "$modalInstance", function ($scope, DictionaryAPI, CurrentDictionary, $modalInstance) {
                    $scope.dictionary = {};
                    if(CurrentDictionary){
                        $scope.dictionary = CurrentDictionary;
                    }
                    $scope.errors = null;
                    $scope.addDictionaryForm = {};
                    $scope.saveDictionary = function(){
                        $scope.errors = null;
                        if($scope.addDictionaryForm.validator.form()){
                            if($scope.dictionary.id){
                                DictionaryAPI.update($scope.dictionary, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }else{
                                DictionaryAPI.save($scope.dictionary, function(){
                                    $modalInstance.close();
                                }, function(data){
                                    $scope.errors = data.data;
                                })
                            }
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getDictionaryList();
            });
        };


    }];
});