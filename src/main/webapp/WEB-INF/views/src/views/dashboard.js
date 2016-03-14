/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "UserAPI", "$modal", function($scope, UserAPI, $modal){
        $scope.open = function (index, size) {
            var modalInstance = $modal.open({
                templateUrl: 'updatePassword.html',
                controller: ["$scope", "$modalInstance", "UserAPI", function ($scope, $modalInstance, UserAPI) {
                    $scope.user = {};
                    $scope.updatePasswordForm = {};
                    $scope.updatePassword = function(){
                        if($scope.updatePasswordForm.validator.form()){
                            UserAPI.resetPassword({
                                current_password:$scope.user.oldPassword,
                                password:$scope.user.password
                            }, function(){
                                $modalInstance.close();
                            })
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }],
                size: size
            });
            modalInstance.result.then(function () {
                window.location = "/logout";
            });
        };
    }];
});