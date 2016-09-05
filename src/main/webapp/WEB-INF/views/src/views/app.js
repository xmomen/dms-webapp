define([
    "views/user/user_module",
    "views/order/order_module",
    "views/schedule/schedule_module",
    "views/dashboard",
    "views/base/base_module",
    "views/templates/template_module",
    "views/checklist-model/checklist-model",
    "views/plan/plan_module",
    "views/package/package_module",
    "views/pick/pick_module",
    "views/despatch/despatch_module"
],function (user_module,order_module,schedule_module, dashboard, base_module, template_module,checklist_model,plan_module,package_module,despatch_module) {
    angular.module('DMS', [
        "smartApp", "ui.router", "DMS.schedule", "DMS.order", "DMS.tpls", "DMS.user","DMS.base", "ug.pagination", "EnvModule", "permission", "ug.validate","ug.dialog",
        "DMS.REST","checklist-model","DMS.plan","DMS.package","DMS.pick","DMS.despatch"
    ]).filter(
        'to_trusted', ['$sce', function ($sce) {
            return function (text) {
                return $sce.trustAsHtml(text);
            }
        }]
    ).factory({
        HttpInterceptor:["$q", function($q){
           return {
               request: function (config) {
                   if(config.method=='GET'){
                       if(config.params){
                           config.params._noCache = new Date().getTime();
                       }
                   }
                   return config;
               },
               responseError:function(response){
                   if(response.status == 401){
                       //未找到用户
                       window.location.reload();
                   }
                   return $q.reject(response);
               }
           }
        }],
        User : ["UserAPI","PermissionStore", "$q", function(UserAPI, PermissionStore, $q){
            return {
                fetchPermission: function(){
                    var defered = $q.defer();
                    UserAPI.getPermissions(function(data){
                        PermissionStore.clearStore();
                        for (var i = 0; i < data.permissions.length; i++) {
                            var obj = data.permissions[i];
                            PermissionStore
                                .definePermission(obj, function (stateParams) {
                                    return true;
                                });
                        }
                        defered.resolve();
                    }, function(){
                        defered.reject();
                    });
                    return defered.promise;
                },
                resetPermission: function(data){
                    PermissionStore.clearStore();
                    for (var i = 0; i < data.permissions.length; i++) {
                        var obj = data.permissions[i];
                        PermissionStore
                            .definePermission(obj, function (stateParams) {
                                return true;
                            });
                    }
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
    }).directive('datepickerLocaldate', ['$filter', function ($filter) {
        /**
         * change model to iso format (yyyy-MM-dd)
         */
        return {
            require: 'ngModel',
            link:  function link(scope, element, attr, ngModel) {

                ngModel.$parsers.push(function toModel(date) {
                    if (!date) {
                        return undefined;
                    }
                    return $filter('date')(date, 'yyyy-MM-dd');
                });

                var converted = false;
                scope.$watch(function(){
                        return ngModel.$modelValue;
                    },
                    function(modelValue){

                        // convert to localDate (remove timezone if necessary), this is for datepicker to synchronize with the good date for negative timezone (https://github.com/angular-ui/bootstrap/issues/2072)
                        if(!converted && modelValue){

                            var dt = new Date(modelValue);
                            if(dt.getTimezoneOffset() > 0)
                                dt.setMinutes(dt.getMinutes() + dt.getTimezoneOffset());

                            ngModel.$modelValue = dt;

                            ngModel.$render();

                            converted=true;

                        }
                    });
            }
        };
    }]).directive("ugSelect2",["CompanyAPI", "$rootScope", function(CompanyAPI, $rootScope){
        return {
            restrict:"A",
            require:"select",
            scope:{ ugSelect2Config: '='},
            link: function(scope, element, attr, crtl){
                var config = scope.ugSelect2Config || {};
                 config = angular.extend(config, {
                    initSelectData : function(data){
                        setTimeout(function(){
                            $select2.val(data).trigger("change");
                        }, 100);
                    }
                });
                var $select2 = $(element).select2(config);
            }
        }
    }]).directive({
        /**
         * 文件上传
         */
        uxUpload:["$timeout","$ugDialog",function($timeout,Dialog){
            var fileType = "*.xlsx;*.xls;*.pdf;*.doc;*.docx;*.*";
            return {
                scope:{
                    uxUploadConfig : '='
                },
                link:function(scope,elem,attr,ctrl){
                    debugger
                    var config = scope.uxUploadConfig;
                    var defaultConfig = {
                        buttonClass     : "btn btn-outline btn-primary",
                        buttonText      : '上传身份证图片',
                        swf             : '/js/uploadify/uploadify.swf',
                        uploader        : '/wms-webapp/',
                        fileSizeLimit   : "10MB",
                        fileObjName     : "files",//对应后台参数名，请勿修改
                        fileTypeDesc    : "请选择 " + fileType + " 类型的文件",
                        fileTypeExts    : fileType,
                        overrideEvents  : [ 'onDialogClose', 'onSelectError' ],
                        onFallback      : function() {
                            //Dialog.alert('Flash was not detected or flash version is not supported.');
                            Dialog.alert('未发现Flash插件或Flash版本不支持（请确保已下载Flash插件且已启用Flash插件）。');
                        },
                        onSelectError   : function(file, errorCode, errorMsg){
                            switch (errorCode) {
                                case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                                    if (this.settings.queueSizeLimit > errorMsg) {
                                        this.queueData.errorMsg = '选择文件的数量超过了剩余的上传限制 (' + errorMsg + ').';
                                    } else {
                                        this.queueData.errorMsg = '选择文件的数量超过了队列大小限制 (' + this.settings.queueSizeLimit + ').';
                                    }
                                    break;
                                case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                                    this.queueData.errorMsg = "文件大小超出限制( " + this.settings.fileSizeLimit + " 以内)";
                                    break;
                                case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                                    this.queueData.errorMsg = "文件大小为0，请检查文件是否正确";
                                    break;
                                case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                                    this.queueData.errorMsg = "无效的文件类型，上传文件类型限制为：" + this.settings.fileTypeExts;
                                    break;
                                default:
                                    this.queueData.errorMsg = "文件上传失败，" + errorMsg;
                            }
                        },
                        onDialogClose   : function(queueData){
                            if (queueData.filesErrored > 0) {
                                Dialog.alert(this.queueData.errorMsg);
                            }
                        }
                    };
                    angular.extend(defaultConfig,config);
                    $(elem).uploadify(defaultConfig);
                }
            };
//            使用DEMO
//            <input ux-upload ux-upload-config="fileUploadConfig" id="file" ng-model="fileUrl" type="file" multiple="true">
//            $scope.fileUploadConfig = {
//                'buttonText' : '上传身份证图片',
//                'onUploadSuccess':function(file,data,response){
//                    var result = $scope.$eval(data);
//                    if(result.status == "1"){
//                        Dialog.alert(result.msg);
//                    }else if(result.status == "0"){
//                        if(result.result[0]){
//                            //xxxxxxxxxxx
//                        }
//                    }
//                }
//            };
        }]
    }).controller("LeftPanelController",["$scope", "$rootScope", "$http", function($scope, $rootScope, $http){
        $http.get("/account/setting").then(function(data){
            if(data.data){
                $rootScope.account = data.data;
            }
        })
    }]).run(["$rootScope", "User", function($rootScope, User){
        User.resetPermission(permissionList);

        $rootScope.$on('loadingTree', function(){
            setTimeout(function(){
                $(".tree").find("li:has(ul)").addClass("parent_li").attr("role", "treeitem").find(" > span").attr("title", "收缩").on("click", function (a) {
                    var b = $(this).parent("li.parent_li").find(" > ul");
                    if(b.is(":visible")){
                        b.hide("fast");
                        $(this).attr("title", "展开").find(" > i").addClass("fa-plus-circle").removeClass("fa-minus-circle");
                    }else{
                        b.show("fast");
                        $(this).attr("title", "收缩").find(" > i").addClass("fa-minus-circle").removeClass("fa-plus-circle");
                    }
                    a.stopPropagation();
                });
            }, 1500);
        });

        $rootScope.$on('$viewContentLoaded', function (event, next,  nextParams, fromState) {
            // 初始化全局控件
//           pageSetUp();
        });
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
                }]
            })

    }]);
    angular.element(document).ready(function() {
        $.get('/user/permissions', function(data) {
            permissionList = data;
            angular.bootstrap(document, ['DMS']);
        });
    });
});
