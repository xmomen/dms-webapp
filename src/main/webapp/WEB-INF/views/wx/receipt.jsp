<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="mytags.jsp"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page" data-ng-app="smartApp">
<head>
<meta charset="utf-8">
<title>微信页面</title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${webRoot}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${webRoot}/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${webRoot}/css/smartadmin-production.min.css">
<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->

<!-- Angular Related CSS -->
</head>
<body class="animated fadeInDown">
	<div id="main">
		<!-- MAIN CONTENT -->
		<div id="content" class="container">
			<div class="row">
				<div>
					<div class="well no-padding">
						<form action="#" method="get" id="login-form"
							class="smart-form client-form">
							<fieldset>
								<div class="accordion" id="accordion2">
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												data-parent="#accordion2" href="#collapseOne"> <strong>订单信息</strong>
											</a>
										</div>
										<div id="collapseOne" class="accordion-body collapse in">
											<div class="accordion-inner">
												<div class="well well-large">
													<span>订购人姓名:<strong>${orderInfo.consigneeName}</strong></span><br>
													<span>电话:<strong>${orderInfo.consigneePhone}</strong></span><br>
													<span>地址:<strong>${orderInfo.consigneeAddress}</strong></span><br>
													<span>订单要求:<strong>${orderInfo.remark}</strong></span><br>
												</div>
											</div>
										</div>
									</div>
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												data-parent="#accordion2" href="#collapseTwo"> <strong>商品明细</strong>
											</a>
										</div>
										<div id="collapseTwo" class="accordion-body collapse">
											<div class="accordion-inner">
												<div class="well well-large">
													<div class="table-responsive">
														<table class="table">
															<thead>
																<tr>
																	<th width="80%">产品名称</th>
																	<th width="20%">份数</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="orderItem" items="${orderItemInfo}"
																	varStatus="status">
																	<tr>
																		<td><c:out value="${orderItem.itemName}" /></td>
																		<td><c:out value="${orderItem.itemQty}" /></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</fieldset>
							<c:if test="${express == 0}">
								<fieldset>
									<section>
										<label class="label">收货码</label> <label class="input">
										 <input type="text" name="shouhuoNo" id="shouhuoNo"> 
										</label>
									</section>
								</fieldset>
							</c:if>

							<input type="hidden" id="openId" name="openId" value="${openId}">
							<div class="error">${message}</div>
							<footer>
								<button type="button" onclick="shouhuoEvent();" class="btn btn-primary">确认收货</button>
							</footer>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		if (!window.jQuery) {
			document
					.write('<script src="${webRoot}/js/libs/jquery-2.0.2.min.js"><\/script>');
		}
	</script>
	<script>
		if (!window.jQuery.ui) {
			document
					.write('<script src="${webRoot}/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
		}
	</script>

	<!-- BOOTSTRAP JS -->
	<script src="${webRoot}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		function shouhuoEvent(){
			var express = ${express};
			var shouhuoNo = $("#shouhuoNo").val();
			var openId = $("#openId").val();
			var orderNo = ${orderInfo.orderNo};
			if(express == 0 && !shouhuoNo){
				alert("请输入收货码");
				return ;
			}
			$.ajax({
			    url:"/wx/shouhuo?openId="+openId+"shouhuoNo="+shouhuoNo+"&orderNo="+orderNo,
				type:"get",
			    dataType:"json",
			    success:function(data){
			    	if(!data){
			    		alert("收货码错误");
			    	}
			  }
			});
		}
	</script>
</body>
</html>
