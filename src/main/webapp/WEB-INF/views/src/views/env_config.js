/**
 * Created by Jeng on 2015/5/7.
 * 环境配置
 */
define(function() {
    'use strict';
    angular.module('EnvModule', [])
        .constant("ApiEndpoint", {
            url:""
        })
        .constant('AppConfig', {
            ImgWebsite:"http://test.img.udfex.com",
            AlipayNotifyUri:"http://test.www.udfex.com/05-udf-ecw/pub/payment/notify/alipay.json"
        });
});
