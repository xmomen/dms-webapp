/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/pick/pick"
],function (pick) {
    angular.module('DMS.pick', [
        "permission"
    ]).config(["$stateProvider", function($stateProvider){
        $stateProvider
            .state('pick', {
                url: '/pick',
                templateUrl: 'views/pick/pick.html',
                data:{
                    permissions:{
                        only:["PICK"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: pick
            })
    }]);
});