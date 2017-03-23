'use strict';

<#include "header.ftl">
define([
    "angularAMD",
    "./${domainObjectUnderlineName}.api",
    "./${domainObjectUnderlineName}"
],function(angularAMD, ${domainObjectClassName}Rest, ${domainObjectName}){
    angular.module('${domainObjectUnderlineName}.module',[
        "${domainObjectClassName}.REST"
    ]).config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {

            var states = [];

            states.push({
                title: "${tableComment}",
                name: 'app.${domainObjectName}',
                url: '${restMapping}',
                controller: ${domainObjectName},
                templateUrl: 'modules/${moduleName}/${domainObjectUnderlineName}.html'
            });

            angular.forEach(states, function(state){
                $stateProvider.state(state.name, angularAMD.route(state));
            });
        }
    ]);
});