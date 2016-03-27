define([
    "views/user/user_module",
    "views/schedule/schedule_module",
    "views/dashboard",
    "views/base/base_module"
],function (user_module,schedule_module, dashboard,base_module) {
    angular.module('DMS', [
        "smartApp", "ui.router", "DMS.schedule", "DMS.user","DMS.base", "ug.pagination", "EnvModule", "permission", "ug.validate","ug.dialog",
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
        User : ["UserAPI","PermissionStore", function(UserAPI, PermissionStore){
            return {
                fetchPermission: function(){
                    UserAPI.getPermissions(function(data){
                        PermissionStore.clearStore();
                        for (var i = 0; i < data.permissions.length; i++) {
                            var obj = data.permissions[i];
                            PermissionStore
                                .definePermission(obj, function (stateParams) {
                                    return true;
                                });
                        }
                    });
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
    }]).config(["$stateProvider", "$urlRouterProvider", "$httpProvider", function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $httpProvider.interceptors.push('HttpInterceptor');
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

    }]).run(["$rootScope", "User", function($rootScope, User){
        User.fetchPermission();
        $rootScope.$on('$viewContentLoaded', function (event, next,  nextParams, fromState) {
            // 初始化全局控件
//           pageSetUp();
        });
    }]);
});
