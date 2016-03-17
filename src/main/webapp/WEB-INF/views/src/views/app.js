define([
    "views/user/user_module",
    "views/schedule/schedule_module",
    "views/dashboard"
],function (user_module,schedule_module, dashboard) {
    angular.module('DMS', [
        "smartApp", "ui.router", "DMS.schedule", "DMS.user", "ug.pagination", "EnvModule", "permission", "ug.validate","ug.dialog",
        "DMS.REST"
    ]).factory({
        HttpInterceptor:["$q", function($q){
           return {
               responseError:function(response){
                   if(response.status == 401){
                       //未找到用户
                       window.location.reload();
                   }
                   return $q.reject(response);
               }
           }
        }],
        $baseHttp:["$http", "$q", "ApiEndpoint", function($http, $q, ApiEndpoint){
            var urlEndpoint = "";
            if(ApiEndpoint && ApiEndpoint.url){
                urlEndpoint = ApiEndpoint.url;
            }
            var httpGet = function(url, options){
                var defer = $q.defer();
                $http.get(urlEndpoint + url, options).then(function(response){
                    return defer.resolve(response.data, response);
                },function(response){
                    return defer.reject(response.data, response);
                });
                return defer.promise;
            };

            var httpPost = function(url, data, options){
                var defer = $q.defer();
                $http.post(urlEndpoint + url, data, options).then(function(response){
                    return defer.resolve(response.data, response);
                },function(response){
                    return defer.reject(response.data, response);
                });
                return defer.promise;
            };
            return {
                get:httpGet,
                post:httpPost
            };
        }]
    }).controller("LeftPanelController",["$scope", "$rootScope", "$http", function($scope, $rootScope, $http){
        $http.get("/account/setting").then(function(data){
            if(data.data){
                $rootScope.account = data.data;
            }
        })
    }]).config(["$httpProvider", function($httpProvider){
        $httpProvider.interceptors.push('HttpInterceptor');
    }]).run(["$rootScope", "Permission", "$q", "$baseHttp", function($rootScope, Permission, $q, $baseHttp){
        var roles = ["anonymous", "user", "admin", "superAdmin"];
        Permission.defineManyRoles(roles, function(stateParams, roleName){
            var deferred = $q.defer();
            $baseHttp.get("/user/permissions").then(function (data) {
                var hasRole = false;
                for (var i = 0; i < data.roles.length; i++) {
                    var obj = data.roles[i];
                    if(obj === roleName){
                        hasRole = true;
                        break;
                    }
                }
                if (hasRole) {
                    deferred.resolve();
                } else {
                    deferred.reject();
                }
            }, function () {
                // Error with request
                deferred.reject();
            });
            return deferred.promise;
        });
        $rootScope.$on('$viewContentLoaded', function (event, next,  nextParams, fromState) {
            // 初始化全局控件
//           pageSetUp();
        });
    }]).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $urlRouterProvider.otherwise('/dashboard');

        $stateProvider
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'views/dashboard.html',
                controller: dashboard
            })

            .state('unauthorized', {
                url: '/unauthorized',
                templateUrl: 'views/error/error403.html'
            })

            .state('blank', {
                url: '/blank',
                templateUrl: 'views/blank.html',
                controller: ["$scope", function ($scope) {
                    console.log("blank");
                }]
            })

    }]);
});
