/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/package/packageTaskDown",
    "views/package/packageTaskList",
    "views/package/packageWorking",
    "views/package/packageTaskView"
],function (packageTaskDown,packageTaskList,packageWorking,packageTaskView) {
    angular.module('DMS.package', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('packageTaskDown', {
                url: '/packageTaskDown',
                templateUrl: 'views/package/packageTaskDown.html',
                data:{
                    permissions:{
                        only:["PACKAGE_TASK_DOWN"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: packageTaskDown
            })
            .state('packageTask', {
                url: '/packageTaskList',
                templateUrl: 'views/package/packageTaskList.html',
                data:{
                    permissions:{
                        only:["PACKAGE_TASK"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: packageTaskList
            })
            .state('packageWorking', {
                url: '/packageWorking?id',
                templateUrl: 'views/package/packageWorking.html',
                data:{
                    permissions:{
                        only:["PACKAGE_TASK"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: packageWorking
            })
            .state('packageTaskView', {
                url: '/packageTaskView',
                templateUrl: 'views/package/packageTaskView.html',
                data:{
                    permissions:{
                        only:["PACKAGE_TASK_VIEW"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: packageTaskView
            })
    }]);
});