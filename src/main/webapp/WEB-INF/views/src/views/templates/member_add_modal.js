/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "MemberAPI", "$modalInstance", "currentMember", "CompanyAPI", "UserAPI", "MemberAddressAPI", "$ugDialog", function ($scope, MemberAPI, $modalInstance, currentMember, CompanyAPI, UserAPI, MemberAddressAPI, $ugDialog) {
        $scope.companyList = [];
        $scope.ugSelect2Config = {};
        $scope.pageSetting = {
            pageSize: 1000,
            pageNum: 1
        };
        $scope.getCompanyList = function () {
            CompanyAPI.query({
                limit: $scope.pageSetting.pageSize,
                offset: $scope.pageSetting.pageNum
            }, function (data) {
                $scope.companyList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getCompanyList;
                $scope.ugSelect2Config.initSelectData($scope.member.cdCompanyId);
                $scope.managerUgSelect2Config.initSelectData($scope.member.cdUserId);
            });
        };

        $scope.managerUgSelect2Config = {};
        $scope.getCompanyList();
        $scope.changeCompany = function (id) {
            for (var i in $scope.companyList) {
                var company = $scope.companyList[i];
                if (company.id == parseInt(id)) {
                    $scope.companyCustomerManagers = company.companyCustomerManagers;
                }
            }
        };
        $scope.member = {};
        $scope.memberAddressList = [];
        //编辑场合
        if (currentMember != undefined && currentMember.id) {
            //查询收货地址
            MemberAddressAPI.query({
                cdMemberId: currentMember.id,
                limit: $scope.pageSetting.pageSize,
                offset: $scope.pageSetting.pageNum
            }, function (result) {
                if (result) {
                    $scope.memberAddressList = result.data;
                }
            });

            if (!currentMember.name) {
                MemberAPI.get({
                    id: currentMember.id
                }, function (data) {
                    if (data) {
                        $scope.member = data;
                    }
                });
            } else {
                $scope.member = currentMember;
            }
        }
        //新增、绑定场合
        else {
            $scope.memberAddressList.push({});
            $scope.member = currentMember;
        }

        $scope.addAddressList = function () {
            $scope.memberAddressList.push({});
        }

        $scope.deleteAddress = function (index) {
            $ugDialog.confirm("是否删除地址？").then(function () {
                //地址是否保存
                if ($scope.memberAddressList[index].id) {
                    MemberAddressAPI.delete({
                        id: $scope.memberAddressList[index].id
                    }, function () {
                        $scope.getExpressList();
                    });
                }
                $scope.memberAddressList.splice(index, 1);
            });
        }

        $scope.errors = null;
        $scope.addMemberForm = {};
        $scope.saveOrUpdateMember = function () {
            $scope.errors = null;
            if ($scope.addMemberForm.validator.form()) {
                if ($scope.member.id) {
                    $scope.member.memberAddressList = $scope.memberAddressList;
                    MemberAPI.update($scope.member, function () {
                        $modalInstance.close(angular.copy($scope.member));
                    }, function (data) {
                        $scope.errors = data.data;
                    })
                } else {
                    $scope.member.memberAddressList = $scope.memberAddressList;
                    MemberAPI.save($scope.member, function () {
                        $modalInstance.close(angular.copy($scope.member));
                    }, function (data) {
                        $scope.errors = data.data;
                    })
                }
            }
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }];
});