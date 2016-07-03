/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/despatch/despatchJob"
],function (despatchJob) {
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
    }]);
});