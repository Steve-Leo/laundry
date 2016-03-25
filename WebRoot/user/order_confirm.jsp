<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";  
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单确认</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/order_confirm.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/order_confirm.js"></script>
</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container">
		<h3>
			<strong>收货地址确认</strong>
		</h3>
		<hr>
		<div class="radio">
			<label> <input type="radio" name="optionsRadios"
				id="default_add" value="default" checked> 默认收货地址 </label>
		</div>
		<table class="table table-bordered table-striped" id="default_table">
			<thead>
				<tr>
					<th class="col-xs-2">收货人姓名</th>
					<th class="col-xs-6">收货人地址</th>
					<th class="col-xs-4">联系电话</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${name}</td>
					<td>${address}</td>
					<td>${telephone}</td>
				</tr>
			</tbody>
		</table>
		<div class="radio">
			<label> <input type="radio" name="optionsRadios"
				id="custom_add" value="custom"> 自定收货地址 </label>
		</div>
		<div class="addr_back">
			<div class="col-xs-2">
				<input class="form-control" id="input_name" placeholder="收货人姓名" maxlength="18">
			</div>
			<div class="col-xs-6">
				<input class="form-control" id="input_address" placeholder="收货人地址" style="width:500px;" maxlength="200">
			</div>
			<div class="col-xs-4">
				<input class="form-control" id="input_phone" placeholder="联系电话" maxlength="13">
			</div>
		</div>
		<br><br><br>
		<h3>
			<strong>商品信息确认</strong>
		</h3>
		<hr>
		
		<c:if test="${!empty bblist}">
		<c:forEach var="basketBusi" items="${bblist}">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<ul class="list-inline">
					<li>${basketBusi.shopName}</li>
					<li id="shopId" style="display:none;">${basketBusi.shopId}</li>
				</ul>
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="col-xs-4 text-center">商品信息</th>
						<th class="col-xs-2 text-center">清洗方式</th>
						<th class="col-xs-2 text-center">单价</th>
						<th class="col-xs-2 text-center">数量</th>
						<th class="col-xs-2 text-center">金额</th>
					</tr>
				</thead>
				<tbody id="order_items">
				<c:forEach var="item" items="${basketBusi.items}">
					<tr>
						<td style="display:none">${item.clothId}</td>
						<td class="col-xs-4 text-center">${item.clothName}</td>
						<td class="col-xs-2 text-center">
							<c:choose>
								<c:when test="${item.washMode == 1}">干洗</c:when>
								<c:when test="${item.washMode == 2}">水洗</c:when>
							</c:choose>
						</td>
						<td class="col-xs-2 text-center">${item.price}</td>
						<td class="col-xs-2 text-center">${item.count}</td>
						<td class="col-xs-2 text-center">${item.count*item.price}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</c:forEach>
		
		<div class="submit_block">
			<div class="addr_info_block">
				<div class="account_block">
					<p>总金额 ：<strong></strong> 元</p>
				</div>
				<div class="addr_block">
					<div>
						<span><strong>寄送至：</strong><span id="address"></span></span>
					</div>
					<div style="margin-bottom:10px;">
						<span><strong>收件人：</strong><span id="name"></span></span>
					</div>
				</div>
			</div>
			<div class="submit_button_block">
				<a id="checkout" class="btn" href="javascript:void(0)">
					<span><font color=white>提交订单</font></span>
				</a>
			</div>
		</div>
		<form id="commit_form" action="" method="post" style="display:none">
			<input id="commit_value" name="orderId">
		</form>
		</c:if>
		
		<jsp:include page="../foot.jsp"></jsp:include>
	</div>
</body>
</html>
