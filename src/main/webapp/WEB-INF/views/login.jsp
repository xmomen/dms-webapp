<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en-us" id="extr-page" data-ng-app="smartApp">
<head>
    <meta charset="utf-8">
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

    <title> 订单管理系统 </title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Basic Styles -->
    <link rel="stylesheet" type="text/css" media="screen" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="./css/font-awesome.min.css">

    <!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
    <link rel="stylesheet" type="text/css" media="screen" href="./css/smartadmin-production.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="./css/smartadmin-skins.min.css">

    <!-- SmartAdmin RTL Support is under construction
         This RTL CSS will be released in version 1.5
    <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> -->

    <!-- We recommend you use "your_style.css" to override SmartAdmin
         specific styles this will also ensure you retrain your customization with each SmartAdmin update.
    <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

    <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
    <link rel="stylesheet" type="text/css" media="screen" href="./css/demo.min.css">

    <!-- Angular Related CSS -->
    <link rel="stylesheet" type="text/css" media="screen" href="./css/ng.min.css">

    <!-- FAVICONS -->
    <link rel="shortcut icon" href="./img/favicon/favicon.ico" type="image/x-icon">
    <link rel="icon" href="./img/favicon/favicon.ico" type="image/x-icon">

    <!-- GOOGLE FONT -->
    <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">-->

    <!-- Specifying a Webpage Icon for Web Clip
         Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
    <link rel="apple-touch-icon" href="./img/splash/sptouch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="76x76" href="./img/splash/touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="120x120" href="./img/splash/touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="152x152" href="./img/splash/touch-icon-ipad-retina.png">

    <!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Startup image for web apps -->
    <link rel="apple-touch-startup-image" href="./img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
    <link rel="apple-touch-startup-image" href="./img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
    <link rel="apple-touch-startup-image" href="./img/splash/iphone.png" media="screen and (max-device-width: 320px)">

</head>
<body class="animated fadeInDown">
<!-- possible classes: minified, no-right-panel, fixed-ribbon, fixed-header, fixed-width-->
<header id="header">
    <!--<span id="logo"></span>-->

    <div id="logo-group">
        <span id="logo"> <img src="./img/logo.png" alt="订单管理系统"> </span>

        <!-- END AJAX-DROPDOWN -->
    </div>

    <%--<span id="extr-page-header-space"> <span class="hidden-mobile">需要新账户?</span> <a href="${pageContext.request.contextPath}/register" class="btn btn-danger">注册</a> </span>--%>

</header>

<div id="main" role="main" style="margin-left: 0;">

    <!-- MAIN CONTENT -->
    <div id="content" class="container" style="margin:0 auto;width:457px">
        <div class="row">
            <div>
                <div class="well no-padding">
                    <form  action="" method="post" id="login-form" class="smart-form client-form">
                        <header>
                            登录
                        </header>
                        <fieldset>
                            <div class="error">
                                ${error}
                            </div>
                            <section>
                                <label class="label">用户名</label>
                                <label class="input"> <i class="icon-append fa fa-user"></i>
                                    <input type="text" name="username">
                                    <b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> 请输入用户名</b></label>
                            </section>

                            <section>
                                <label class="label">密码</label>
                                <label class="input"> <i class="icon-append fa fa-lock"></i>
                                    <input type="password" name="password">
                                    <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 请输入密码</b> </label>
                                <div class="note">
                                    <a href="forgotpassword.html">忘记密码?</a>
                                </div>
                            </section>

                            <section>
                                <label class="checkbox">
                                    <input type="checkbox" name="rememberMe" checked="true">
                                    <i></i>保持登录</label>
                            </section>
                        </fieldset>
                        <footer>
                            <button type="submit" class="btn btn-primary">
                                登录
                            </button>
                        </footer>
                    </form>

                </div>

                <%--<h5 class="text-center"> - Or sign in using -</h5>--%>

                <%--<ul class="list-inline text-center">--%>
                    <%--<li>--%>
                        <%--<a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>--%>
                    <%--</li>--%>
                <%--</ul>--%>

            </div>
        </div>
    </div>

</div>

<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<!--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>-->
<script>
    if (!window.jQuery) {
        document.write('<script src="./js/libs/jquery-2.0.2.min.js"><\/script>');
    }
</script>

<!--<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>-->
<script>
    if (!window.jQuery.ui) {
        document.write('<script src="./js/libs/jquery-ui-1.10.3.min.js"><\/script>');
    }
</script>

<!-- BOOTSTRAP JS -->
<script src="./js/bootstrap/bootstrap.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="./js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<script type="text/javascript">


    $(function() {
        // Validation
        $("#login-form").validate({
            // Rules for form validation
            rules : {
                username : {
                    required : true
                },
                password : {
                    required : true,
                    minlength : 3,
                    maxlength : 20
                }
            },

            // Messages for form validation
            messages : {
                username : {
                    required : '请输入用户名'
                },
                password : {
                    required : '请输入密码'
                }
            },

            // Do not change code below
            errorPlacement : function(error, element) {
                error.insertAfter(element.parent());
            }
        });
    });
</script>

</body>
</html>
