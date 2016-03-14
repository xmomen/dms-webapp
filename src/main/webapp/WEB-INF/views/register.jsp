<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en-us">
<head>
		<meta charset="utf-8">
		<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

		<title> SmartAdmin </title>
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
	<body id="animated fadeInDown">
		<!-- possible classes: minified, no-right-panel, fixed-ribbon, fixed-header, fixed-width-->
		<header id="header">
			<!--<span id="logo"></span>-->

			<div id="logo-group">
				<span id="logo"> <img src="./img/logo.png" alt="后台管理系统"> </span>

				<!-- END AJAX-DROPDOWN -->
			</div>
			<span id="login-header-space"> <span class="hidden-mobile">已有账号?</span> <a href="${pageContext.request.contextPath}/login" class="btn btn-danger">登录</a> </span>

		</header>

		<div id="main" role="main" style="margin-left:0">

			<!-- MAIN CONTENT -->
			<div id="content" class="container" style="margin:0 auto;width:457px">

				<div class="row">
					<div class="">
						<div class="well no-padding">

							<form action="/register.do" method="post" id="smart-form-register" class="smart-form client-form">
								<header>
									注册
								</header>
								<div class="error">
									${error}
								</div>
								<fieldset>
									<section>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="username" placeholder="用户名">
											<b class="tooltip tooltip-bottom-right">Needed to enter the website</b> </label>
									</section>

									<%--<section>--%>
										<%--<label class="input"> <i class="icon-append fa fa-envelope"></i>--%>
											<%--<input type="email" name="email" placeholder="邮箱">--%>
											<%--<b class="tooltip tooltip-bottom-right">Needed to verify your account</b> </label>--%>
									<%--</section>--%>

									<section>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="password" placeholder="密码" id="password">
											<b class="tooltip tooltip-bottom-right">Don't forget your password</b> </label>
									</section>

									<section>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="passwordConfirm" placeholder="确认密码">
											<b class="tooltip tooltip-bottom-right">Don't forget your password</b> </label>
									</section>
								</fieldset>

								<fieldset>

									<section>
										<label class="checkbox">
											<input type="checkbox" name="terms" id="terms">
											<i></i>同意 <a href="" data-toggle="modal" data-target="#myModal"> 《服务条款》 </a></label>
									</section>
								</fieldset>
								<footer>
									<button type="submit" class="btn btn-primary">
										注册
									</button>
								</footer>

								<div class="message">
									<i class="fa fa-check"></i>
									<p>
										感谢你的注册
									</p>
								</div>
							</form>

						</div>
						<p class="note text-center">*FREE Registration ends on October 2015.</p>
						<h5 class="text-center">- Or sign in using -</h5>
						<ul class="list-inline text-center">
							<li>
								<a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
							</li>
						</ul>
					</div>
				</div>
			</div>

		</div>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">服务条款</h4>
					</div>
					<div class="modal-body custom-scroll terms-body">

 <div id="left">



            <h1>SMARTADMIN TERMS & CONDITIONS TEMPLATE</h1>



            <h2>Introduction</h2>

            <p>These terms and conditions govern your use of this website; by using this website, you accept these terms and conditions in full.   If you disagree with these terms and conditions or any part of these terms and conditions, you must not use this website.</p>

            <p>[You must be at least [18] years of age to use this website.  By using this website [and by agreeing to these terms and conditions] you warrant and represent that you are at least [18] years of age.]</p>


            <h2>License to use website</h2>
            <p>Unless otherwise stated, [NAME] and/or its licensors own the intellectual property rights in the website and material on the website.  Subject to the license below, all these intellectual property rights are reserved.</p>

            <p>You may view, download for caching purposes only, and print pages [or [OTHER CONTENT]] from the website for your own personal use, subject to the restrictions set out below and elsewhere in these terms and conditions.</p>

            <p>You must not:</p>
            <ul>
                <li>republish material from this website (including republication on another website);</li>
                <li>sell, rent or sub-license material from the website;</li>
                <li>show any material from the website in public;</li>
                <li>reproduce, duplicate, copy or otherwise exploit material on this website for a commercial purpose;]</li>
                <li>[edit or otherwise modify any material on the website; or]</li>
                <li>[redistribute material from this website [except for content specifically and expressly made available for redistribution].]</li>
            </ul>
            <p>[Where content is specifically made available for redistribution, it may only be redistributed [within your organisation].]</p>

            <h2>Acceptable use</h2>

            <p>You must not use this website in any way that causes, or may cause, damage to the website or impairment of the availability or accessibility of the website; or in any way which is unlawful, illegal, fraudulent or harmful, or in connection with any unlawful, illegal, fraudulent or harmful purpose or activity.</p>

            <p>You must not use this website to copy, store, host, transmit, send, use, publish or distribute any material which consists of (or is linked to) any spyware, computer virus, Trojan horse, worm, keystroke logger, rootkit or other malicious computer software.</p>

            <p>You must not conduct any systematic or automated data collection activities (including without limitation scraping, data mining, data extraction and data harvesting) on or in relation to this website without [NAME'S] express written consent.</p>

            <p>[You must not use this website to transmit or send unsolicited commercial communications.]</p>

            <p>[You must not use this website for any purposes related to marketing without [NAME'S] express written consent.]</p>

            <h2>[Restricted access</h2>

            <p>[Access to certain areas of this website is restricted.]  [NAME] reserves the right to restrict access to [other] areas of this website, or indeed this entire website, at [NAME'S] discretion.</p>

            <p>If [NAME] provides you with a user ID and password to enable you to access restricted areas of this website or other content or services, you must ensure that the user ID and password are kept confidential.</p>

            <p>[[NAME] may disable your user ID and password in [NAME'S] sole discretion without notice or explanation.]</p>

            <h2>[User content</h2>

            <p>In these terms and conditions, “your user content” means material (including without limitation text, images, audio material, video material and audio-visual material) that you submit to this website, for whatever purpose.</p>

            <p>You grant to [NAME] a worldwide, irrevocable, non-exclusive, royalty-free license to use, reproduce, adapt, publish, translate and distribute your user content in any existing or future media.  You also grant to [NAME] the right to sub-license these rights, and the right to bring an action for infringement of these rights.</p>

            <p>Your user content must not be illegal or unlawful, must not infringe any third party's legal rights, and must not be capable of giving rise to legal action whether against you or [NAME] or a third party (in each case under any applicable law).</p>

            <p>You must not submit any user content to the website that is or has ever been the subject of any threatened or actual legal proceedings or other similar complaint.</p>

            <p>[NAME] reserves the right to edit or remove any material submitted to this website, or stored on [NAME'S] servers, or hosted or published upon this website.</p>

            <p>[Notwithstanding [NAME'S] rights under these terms and conditions in relation to user content, [NAME] does not undertake to monitor the submission of such content to, or the publication of such content on, this website.]</p>

            <h2>No warranties</h2>

            <p>This website is provided “as is” without any representations or warranties, express or implied.  [NAME] makes no representations or warranties in relation to this website or the information and materials provided on this website.</p>

            <p>Without prejudice to the generality of the foregoing paragraph, [NAME] does not warrant that:</p>
            <ul>
                <li>this website will be constantly available, or available at all; or</li>
                <li>the information on this website is complete, true, accurate or non-misleading.</li>
            </ul>
            <p>Nothing on this website constitutes, or is meant to constitute, advice of any kind.  [If you require advice in relation to any [legal, financial or medical] matter you should consult an appropriate professional.]</p>

            <h2>Limitations of liability</h2>

            <p>[NAME] will not be liable to you (whether under the law of contact, the law of torts or otherwise) in relation to the contents of, or use of, or otherwise in connection with, this website:</p>
            <ul>
                <li>[to the extent that the website is provided free-of-charge, for any direct loss;]</li>
                <li>for any indirect, special or consequential loss; or</li>
                <li>for any business losses, loss of revenue, income, profits or anticipated savings, loss of contracts or business relationships, loss of reputation or goodwill, or loss or corruption of information or data.</li>
            </ul>
            <p>These limitations of liability apply even if [NAME] has been expressly advised of the potential loss.</p>

            <h2>Exceptions</h2>

            <p>Nothing in this website disclaimer will exclude or limit any warranty implied by law that it would be unlawful to exclude or limit; and nothing in this website disclaimer will exclude or limit [NAME'S] liability in respect of any:</p>
            <ul>
                <li>death or personal injury caused by [NAME'S] negligence;</li>
                <li>fraud or fraudulent misrepresentation on the part of [NAME]; or</li>
                <li>matter which it would be illegal or unlawful for [NAME] to exclude or limit, or to attempt or purport to exclude or limit, its liability.</li>
            </ul>
            <h2>Reasonableness</h2>

            <p>By using this website, you agree that the exclusions and limitations of liability set out in this website disclaimer are reasonable.</p>

            <p>If you do not think they are reasonable, you must not use this website.</p>

            <h2>Other parties</h2>

            <p>[You accept that, as a limited liability entity, [NAME] has an interest in limiting the personal liability of its officers and employees.  You agree that you will not bring any claim personally against [NAME'S] officers or employees in respect of any losses you suffer in connection with the website.]</p>

            <p>[Without prejudice to the foregoing paragraph,] you agree that the limitations of warranties and liability set out in this website disclaimer will protect [NAME'S] officers, employees, agents, subsidiaries, successors, assigns and sub-contractors as well as [NAME].</p>

            <h2>Unenforceable provisions</h2>

            <p>If any provision of this website disclaimer is, or is found to be, unenforceable under applicable law, that will not affect the enforceability of the other provisions of this website disclaimer.</p>

            <h2>Indemnity</h2>

            <p>You hereby indemnify [NAME] and undertake to keep [NAME] indemnified against any losses, damages, costs, liabilities and expenses (including without limitation legal expenses and any amounts paid by [NAME] to a third party in settlement of a claim or dispute on the advice of [NAME'S] legal advisers) incurred or suffered by [NAME] arising out of any breach by you of any provision of these terms and conditions[, or arising out of any claim that you have breached any provision of these terms and conditions].</p>

            <h2>Breaches of these terms and conditions</h2>

            <p>Without prejudice to [NAME'S] other rights under these terms and conditions, if you breach these terms and conditions in any way, [NAME] may take such action as [NAME] deems appropriate to deal with the breach, including suspending your access to the website, prohibiting you from accessing the website, blocking computers using your IP address from accessing the website, contacting your internet service provider to request that they block your access to the website and/or bringing court proceedings against you.</p>

            <h2>Variation</h2>

            <p>[NAME] may revise these terms and conditions from time-to-time.  Revised terms and conditions will apply to the use of this website from the date of the publication of the revised terms and conditions on this website.  Please check this page regularly to ensure you are familiar with the current version.</p>

            <h2>Assignment</h2>

            <p>[NAME] may transfer, sub-contract or otherwise deal with [NAME'S] rights and/or obligations under these terms and conditions without notifying you or obtaining your consent.</p>

            <p>You may not transfer, sub-contract or otherwise deal with your rights and/or obligations under these terms and conditions.</p>

            <h2>Severability</h2>

            <p>If a provision of these terms and conditions is determined by any court or other competent authority to be unlawful and/or unenforceable, the other provisions will continue in effect.  If any unlawful and/or unenforceable provision would be lawful or enforceable if part of it were deleted, that part will be deemed to be deleted, and the rest of the provision will continue in effect.</p>

            <h2>Entire agreement</h2>

            <p>These terms and conditions [, together with [DOCUMENTS],] constitute the entire agreement between you and [NAME] in relation to your use of this website, and supersede all previous agreements in respect of your use of this website.</p>

            <h2>Law and jurisdiction</h2>

            <p>These terms and conditions will be governed by and construed in accordance with [GOVERNING LAW], and any disputes relating to these terms and conditions will be subject to the [non-]exclusive jurisdiction of the courts of [JURISDICTION].</p>

<h2>About these website terms and conditions</h2><p>We created these website terms and conditions with the help of a free website terms and conditions form developed by Contractology and available at <a href="http://www.SmartAdmin.com">www.SmartAdmin.com</a>.
Contractology supply a wide variety of commercial legal documents, such as <a href="">template data protection statements</a>.
</p>
            <h2>[Registrations and authorisations</h2>

            <p>[[NAME] is registered with [TRADE REGISTER].  You can find the online version of the register at [URL].  [NAME'S] registration number is [NUMBER].]</p>

            <p>[[NAME] is subject to [AUTHORISATION SCHEME], which is supervised by [SUPERVISORY AUTHORITY].]</p>

            <p>[[NAME] is registered with [PROFESSIONAL BODY].  [NAME'S] professional title is [TITLE] and it has been granted in [JURISDICTION].  [NAME] is subject to the [RULES] which can be found at [URL].]</p>

            <p>[[NAME] subscribes to the following code[s] of conduct: [CODE(S) OF CONDUCT].  [These codes/this code] can be consulted electronically at [URL(S)].</p>

            <p>[[NAME'S] [TAX] number is [NUMBER].]]</p>

            <h2>[NAME'S] details</h2>

            <p>The full name of [NAME] is [FULL NAME].</p>

            <p>[[NAME] is registered in [JURISDICTION] under registration number [NUMBER].]</p>

            <p>[NAME'S] [registered] address is [ADDRESS].</p>

            <p>You can contact [NAME] by email to [EMAIL].</p>



            </div>

			<br><br>

            <p><strong>By using this  WEBSITE TERMS AND CONDITIONS template document, you agree to the
	 <a href="">terms and conditions</a> set out on
	 <a href="">SmartAdmin.com</a>.  You must retain the credit
	 set out in the section headed "ABOUT THESE WEBSITE TERMS AND CONDITIONS".  Subject to the licensing restrictions, you should
	 edit the document, adapting it to the requirements of your jurisdiction, your business and your
	 website.  If you are not a lawyer, we recommend that you take professional legal advice in relation to the editing and
	 use of the template.</strong></p>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							取消
						</button>
						<button type="button" class="btn btn-primary" id="i-agree">
							<i class="fa fa-check"></i> 同意
						</button>

						<button type="button" class="btn btn-danger pull-left" id="print">
							<i class="fa fa-print"></i> 打印
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->

		<!--================================================== -->

	    <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	    <%--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>--%>
		<script> if (!window.jQuery) { document.write('<script src="./js/libs/jquery-2.0.2.min.js"><\/script>');} </script>

	    <%--<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>--%>
		<script> if (!window.jQuery.ui) { document.write('<script src="./js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->		
		<script src="./js/bootstrap/bootstrap.min.js"></script>

		<!-- CUSTOM NOTIFICATION -->
		<script src="./js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="./js/smartwidgets/jarvis.widget.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="./js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
		<!--[if IE 7]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->
		
		<!-- MAIN APP JS FILE -->
		<script src="./js/app.min.js"></script>

		<script type="text/javascript">
			runAllForms();
			
			// Model i agree button
			$("#i-agree").click(function(){
				$this=$("#terms");
				if($this.checked) {
					$('#myModal').modal('toggle');
				} else {
					$this.prop('checked', true);
					$('#myModal').modal('toggle');
				}
			});
			
			// Validation
			$(function() {
				// Validation
				$("#smart-form-register").validate({

					// Rules for form validation
					rules : {
						username : {
							required : true
						},
//						email : {
//							required : true,
//							email : true
//						},
						password : {
							required : true,
							minlength : 3,
							maxlength : 20
						},
						passwordConfirm : {
							required : true,
							minlength : 3,
							maxlength : 20,
							equalTo : '#password'
						},
						terms : {
							required : true
						}
					},

					// Messages for form validation
					messages : {
						username : {
							required : '请输入用户名'
						},
//						email : {
//							required : '请输入邮箱',
//							email : '请输入有效格式的邮箱'
//						},
						password : {
							required : '请输入密码'
						},
						passwordConfirm : {
							required : '请再次输入密码',
							equalTo : '两次输入的密码不一致'
						},
						terms : {
							required : '请勾选同意服务条款'
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