/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "OrganizationAPI", "$modal", "$ugDialog", function($scope, OrganizationAPI, $modal, $ugDialog){
        $scope.organizationList = [];
        $scope.queryParam = {};
        $scope.getOrganizationTree = function(){
            OrganizationAPI.query({
                id:$scope.queryParam.id
            }, function(data){
                $scope.organizationList = data;
            });
        };
        $scope.removeUser = function(index){
            $ugDialog.confirm("是否删除用户？").then(function(){
                OrganizationAPI.delete({
                    id: $scope.userList[index].userId
                }, function(){
                    $scope.getUserList();
                });
            })
        };
        $scope.openAddModel = function () {
            var modalInstance = $modal.open({
                templateUrl: 'addOrganization.html',
                controller: ["$scope", "OrganizationAPI", "$modalInstance", function ($scope, OrganizationAPI, $modalInstance) {
                    $scope.organization = {};
                    $scope.errors = null;
                    $scope.addOrganizationForm = {};
                    $scope.saveOrganization = function(){
                        $scope.errors = null;
                        if($scope.addOrganizationForm.validator.form()){
                            OrganizationAPI.save($scope.organization, function(){
                                $modalInstance.close();
                            }, function(data){
                                $scope.errors = data.data;
                            })
                        }
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }]
            });
            modalInstance.result.then(function () {
                $scope.getOrganizationTree();
            });
        };

        $scope.getOrganizationTree();

        loadScript("js/plugin/bootstraptree/bootstrap-tree.min.js");
    }];
});