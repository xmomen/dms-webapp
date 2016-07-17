<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="mytags.jsp"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page" data-ng-app="smartApp">
<head>
    <meta charset="utf-8">
    <title>微信页面</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Basic Styles -->
    <link rel="stylesheet" type="text/css" media="screen" href="${webRoot}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="${webRoot}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="${webRoot}/css/smartadmin-production.min.css">
    <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->

    <!-- Angular Related CSS -->
</head>
<body class="animated fadeInDown">
<div id="main">
    <!-- MAIN CONTENT -->
    <div id="content" class="container">
        <div class="row">
            <div>
                <div class="well">
                	恭喜绑定手机号：<<${phone}>>成功，以后您将可以直接通过微信扫一扫进行扫码收货，同时可以通过微信公众号接收订单信息！
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    if (!window.jQuery) {
        document.write('<script src="${webRoot}/js/libs/jquery-2.0.2.min.js"><\/script>');
    }
</script>
<script>
    if (!window.jQuery.ui) {
        document.write('<script src="${webRoot}/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
    }
</script>

<!-- BOOTSTRAP JS -->
<script src="${webRoot}/js/bootstrap/bootstrap.min.js"></script>

</body>
</html>
