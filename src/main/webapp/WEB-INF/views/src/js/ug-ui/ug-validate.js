/**
 * Created by Jeng on 2015/12/17.
 */
/*jshint globalstrict:true*/
/*global angular:false*/
(function(){
    // Create global xmg obj and its namespaces
    // build processes may have already created an xmg obj
    window.ug = window.ug || {};
    window.ug.version = '1.0.0';
(function(angular){
    'use strict';
/**
 * 校验规则表达式
 */
var Regex_Rules = {
    // 正数字
    positiveIntegerRegex: /^[0-9]+$/,
    // 整数
    integerRegex : /^\-?[0-9]+$/,
    // 正浮点数字
    positiveDecimalRegex : /^[0-9]*\.?[0-9]+$/,
    // 浮点数字
    decimalRegex : /^\-?[0-9]*\.?[0-9]+$/,
    // email
    emailRegex : /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
    // 大小写字母或数字
    notSpecialCharacterRegex : /^[A-Za-z0-9]+$/i,
    // 非汉字
    notChineseRegex : /^[\u4E00-\u9FA5]+$/i,
    // 中国身份证
    chinaIdRegex : /^[1-9]\d{5}[1-9]\d{3}(((0[13578]|1[02])(0[1-9]|[12]\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\d|30))|(02(0[1-9]|[12]\d)))(\d{4}|\d{3}[xX])$/,
    // 中国邮政编码
    chinaZipRegex : /^\d{6}$/,
    // 手机号码
    telephoneRegex : /^(1)[0-9]{10}$/,
    // IP
    ipRegex : /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/
};

// 校验框架
var $ugValidate = ["$ugValidateDefault", function( $ugValidateDefault) {
    return {
        restrict: 'A',
        scope:{
            ugValidate:"="
        },
        require:"form",
        link: function(scope, element, attr) {
            var option = angular.extend($ugValidateDefault, scope.ugValidate);
            scope.ugValidate.validator = $(element).validate(option);
            angular.extend(scope.ugValidate, option);
        }
    };
}];
    /**
     * 校验框架
     */
angular.module('ug.validate', ["ng"])
    .constant("Regex_Rules", Regex_Rules)
    .constant("$ugValidateDefault", {
        errorElement: "div",
        errorClass:"error",
        errorPlacement: function(error, element) { //指定错误信息位置
            if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                var eid = element.attr('name'); //获取元素的name属性
                var ele = $(element).parent().parent();
                error.appendTo(ele); //将错误信息添加当前元素的父结点后面
            } else {
                error.insertAfter(element);
            }
        }
    })
    .factory("$ugValidateProvider", function () {
        return {
            setDefaults: function (options) {
                $.validator.setDefaults(options);
            },
            addMethod: function (name, func, errorText) {
                $.validator.addMethod(name, func, errorText);

            }
        }
    })
    .directive('ugValidate', $ugValidate);

}(angular));
})();