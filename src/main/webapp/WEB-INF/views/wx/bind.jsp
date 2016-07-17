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
<div id="main" style="margin-left: 0;">

    <!-- MAIN CONTENT -->
    <div id="content" class="container">
        <div class="row">
            <div>
                <div class="well no-padding">
                    <form  action="/bind/account" method="get" id="login-form" class="smart-form client-form">
                        <header>
                         	绑定手机号
                        </header>
                        <fieldset>
                            <div class="error">
                                ${message}
                            </div>
                            <section>
                             	<input type="hidden" name="openId" value="${openId}">
                             	<input type="hidden" name="bindType" value="${bindType}">
                                <label class="label">手机号</label>
                                <label class="input"> <i class="icon-append fa fa-phone"></i>
                                    <input type="text" name="phone"  >
                                    <b class="tooltip tooltip-top-right"><i class="fa fa-phone txt-color-teal"></i> </b></label>
                            </section>
                        </fieldset>
                        <footer>
                            <button type="submit" class="btn btn-primary">
                          		绑定
                            </button>
                        </footer>
                    </form>
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

<!-- JQUERY VALIDATE -->
<script src="${webRoot}/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<script type="text/javascript">
    $(function() {
        // Validation
        $("#login-form").validate({
            // Rules for form validation
            rules : {
            	phone : {
                    required : true
                }
            },

            // Messages for form validation
            messages : {
            	phone : {
                    required : '请输入手机号'
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
