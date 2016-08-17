/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/despatch/despatchJob",
    "views/despatch/takeDelivery",
    "views/despatch/reviewDespatch"
],function (despatchJob,takeDelivery,reviewDespatch) {
    angular.module('DMS.despatch', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('despatch_job', {
                url: '/despatch',
                templateUrl: 'views/despatch/despatchJob.html',
                data:{
                    permissions:{
                        only:["DESPATCH_JOB"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: despatchJob
            })
            .state('despatch_operation', {
                url: '/takeDelivery',
                templateUrl: 'views/despatch/takeDelivery.html',
                data:{
                    permissions:{
                        only:["DESPATCH_OPERATION"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: takeDelivery
            })
            .state('reviewDespatch', {
                url: '/reviewDespatch',
                templateUrl: 'views/despatch/reviewDespatch.html',
                data:{
                    permissions:{
                        only:["REVIEW_DESPATCH"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: reviewDespatch
            })
    }]);
});