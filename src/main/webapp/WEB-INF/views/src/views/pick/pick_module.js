/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/pick/pick",
    "views/pick/cardRecharge"
],function (pick,cardRecharge) {
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
            .state('cardRecharge', {
                url: '/cardRecharge',
                templateUrl: 'views/pick/cardRecharge.html',
                data:{
                    permissions:{
                        only:["CARD_RECHARGE"],
                        redirectTo:"unauthorized"
                    }
                },
                controller: cardRecharge
            })
    }]);
});