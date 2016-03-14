/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "$http", function($scope, $http){
        $scope.messageList = [];
        $scope.getMessageList = function(){
            $http.get("/notification/getMessageList", {
                params:{
                    limit:10,
                    offset:1,
                    categoryCode:"SYSTEM",
                    status:0
                }
            }).then(function(data){
                $scope.messageList = data.data;
            });
        };
        $scope.getMessageList();
    }];
});