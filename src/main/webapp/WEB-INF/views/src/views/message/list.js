/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "$baseHttp", "MessageAPI", "$modal", "$ugDialog", function($scope, $baseHttp, MessageAPI, $modal, $ugDialog){
        $scope.messageList = [];
        $scope.pageInfoSetting = {
            pageSize:50,
            pageNum:1
        };
        $scope.queryParam = {};
        $scope.getMessageList = function(){
            MessageAPI.query({
                limit:$scope.pageInfoSetting.pageSize,
                offset:$scope.pageInfoSetting.pageNum,
                messageId:$scope.queryParam.messageId,
                dataStatus:$scope.queryParam.dataStatus,
                pushStatus:$scope.queryParam.pushStatus,
                body:$scope.queryParam.body,
                queue:$scope.queryParam.queue
            }, function(data){
                $scope.messageList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getMessageList;
            });
        };
        $scope.retry = function(messageId){
            MessageAPI.retry({
                messageId: messageId
            }, function(data){
                $ugDialog.alert("重发成功");
            });
        };
        $scope.retryAll = function(){
            var successCount = [];
            var failCount = [];
            for (var i = 0; i < $scope.length; i++) {
                var obj = $scope[i];
                MessageAPI.retry({
                    messageId: messageId
                }, function(data){
                    $ugDialog.alert("重发成功");
                }, function(){

                });
            }
        };
        $scope.getLog = function(index){
            MessageAPI.log({
                id:$scope.messageList[index].messageId
            })
        };
        $scope.open = function (index) {
            $modal.open({
                templateUrl: 'myModalContent.html',
                controller: ["$scope", "$modalInstance", "message", "MessageAPI", function ($scope, $modalInstance, message, MessageAPI) {
                    $scope.message = message;
                    $scope.getMessageLogList = function(){
                        MessageAPI.log({
                            id:$scope.message.messageId
                        }, function(data){
                            $scope.messageLogList = data;
                        })
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                size: "lg",
                resolve: {
                    message: function () {
                        return $scope.messageList[index];
                    }
                }
            });
        };
        $scope.openMessage = function (index) {
            $modal.open({
                templateUrl: 'updateMessage.html',
                controller: ["$scope", "$modalInstance", "message", "MessageAPI", function ($scope, $modalInstance, message, MessageAPI) {
                    $scope.message = message;
                    $scope.updateMessageForm = {};
                    $scope.updateMessage = function(){
                        if($scope.updateMessageForm.validator.form()){
                            MessageAPI.update({
                                id:$scope.message.messageId,
                                messageBody :$scope.message.messageBody
                            }, function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                size: "lg",
                resolve: {
                    message: function () {
                        return $scope.messageList[index];
                    }
                }
            });
        };
        $scope.getMessageList();
    }];
});