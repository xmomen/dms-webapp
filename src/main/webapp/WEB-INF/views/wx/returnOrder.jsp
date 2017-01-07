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
													<span>订单编号:<strong>${orderInfo.orderNo}</strong></span><br>
													<span>订购人姓名:<strong>${orderInfo.consigneeName}</strong></span><br>
													<span>电话:<strong>${orderInfo.consigneePhone}</strong></span><br>
													<span>地址:<strong>${orderInfo.consigneeAddress}</strong></span><br>
													<span>订单要求:<strong>${orderInfo.remark}</strong></span><br>
												</div>
											</div>
										</div>
									</div>
									
									<div class="well well-large">
										<div class="table-responsive">
											<table class="table">
												<thead>
													<tr>
														<th></th>
														<th width="80%">产品名称</th>
														<th width="20%">份数</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="orderItem" items="${orderItemInfo}"
														varStatus="status">
														<tr>
															<td><input type="checkbox" value="${orderItem.id}"></input></td>
															<td><c:out value="${orderItem.itemName}" /></td>
															<td><c:out value="${orderItem.itemQty}" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</fieldset>
							<input type="hidden" id="openId" name="openId" value="${openId}">
							<input type="hidden" id="phone" name="phone" value="${phone}">
							<input type="hidden" id="orderNo" name="orderNo" value="${orderInfo.orderNo}">
							<input type="hidden" id="express" name="express" value="${express}">
							<input type="hidden" id="expressId" name="expressId" value="${expressId}">
							<div class="error">${message}</div>
						</form>
						<footer id="footer">
								<c:if test="${express == 1}">
									<button type="button" onclick="shouhuoReturn();" class="btn btn-primary">确认收货</button>
								</c:if>
								<c:if test="${express == 0}">
									<button type="button" onclick="bufenReturn();" class="btn btn-primary">部分退货</button>
									<button type="button" onclick="allReturn();" class="btn btn-primary">全部退货</button>
								</c:if>
						</footer>
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
	<script type="text/javascript">
		//收货操作
		function shouhuoReturn(){
			var expressId = $("#expressId").val();
			var orderNo =$("#orderNo").val();
			var phone =$("#phone").val();
			var a=confirm("确定收到退货货物了吗？");
			 if(a==true)
			 {
				 $.ajax({
					    url:"/wx/shouhuoReturn?expressId="+expressId+"&orderNo="+orderNo+"&phone="+phone,
						type:"get",
					    dataType:"json",
					    success:function(data){
					    	if(data.result == 1){
					    		alert(data.message);
					    		//按钮隐藏
					    		$("#footer").hide();
					    	}
					    	else{
					    		alert(data.message);
					    	}
					  }
					});
			 }
			
		}
		
		//全部退货
		function allReturn(){
			var itemIds ="";
			var orderNo =$("#orderNo").val();
			$("input[type='checkbox']").attr("checked","true"); 
			$("input[type='checkbox']:checkbox:checked").each(function(){
			    itemIds = $(this).val()+",";
			});
			
			var a=confirm("确定要全部退货吗？");
			 if(a==true)
			 {
				 returnOrderEvent(orderNo,itemIds);
			 }
		}
		
		//部门退货
		function bufenReturn(){
			var itemIds ="";
			var orderNo =$("#orderNo").val();
			$("input[type='checkbox']:checkbox:checked").each(function(){
			    itemIds = $(this).val()+",";
			});
			if(itemIds == ""){
				alert('请选择需要退货的商品。');
				return;
			}

			var a=confirm("确定要部分退货吗？");
			 if(a==true)
			 {
				 returnOrderEvent(orderNo,itemIds);
			 }
			
		}
		
		function returnOrderEvent(orderNo,itemIds){
			$.ajax({
			    url:"/wx/returnOrderEvent",
				type:"post",
			    dataType:"json",
			    data:{
			    	orderNo:orderNo,
			    	itemIds:itemIds
			    },
			    success:function(data){
			    	if(data.result == 1){
			    		alert(data.message);
			    		//按钮隐藏
			    		$("#footer").hide();
			    	}
			    	else{
			    		alert(data.message);
			    	}
			  }
			});
		}
	</script>
</body>
</html>
