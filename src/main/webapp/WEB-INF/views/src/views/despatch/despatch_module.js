/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/despatch/despatchJob",
    "views/despatch/despatchSuccessList",
    "views/despatch/takeDelivery"
],function (despatchJob,despatchSuccessList,takeDelivery) {
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

            .state('despatchSuccessList', {
                url: '/despatchSuccessList',
                templateUrl: 'views/despatch/despatchSuccessList.html',
                data:{
                    permissions:{
                        only:["DESPATCH_JOB"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: despatchSuccessList
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

    }]);
});