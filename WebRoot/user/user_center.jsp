<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.ruanku.model.User"%>
<%@ page import="com.ruanku.model.OrderBusiUser"%>
<%@ page import="com.ruanku.model.ClothesOfOrder"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户中心</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/user_center.js"></script>
<style>
.table th,.table td {
	text-align: center;
}
</style>
</head>

<body style="padding-top:100px;">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container">
		<div class="col-xs-2">
			<h3>用户中心</h3>
			<hr />
			<ul class="nav nav-pills nav-stacked">
				<li id="modify_info"><a href="javascript:void(0)"
					onclick="$('#content').load('user/modify_info.jsp')"> 修改收货地址</a></li>
				<li id="change_pass"><a href="javascript:void(0)"
					onclick="$('#content').load('user/change_pass.jsp')"> 修改登录密码 </a></li>
				<li id="change_pay_pass"><a href="javascript:void(0)"
					onclick="$('#content').load('user/change_pay_pass.jsp')">
						修改支付密码 </a></li>
				<li id="charge"><a href="javascript:void(0)"
					onclick="$('#content').load('user/charge.jsp')"> 用户充值 </a></li>
				<li id="account_detail"><a href="javascript:void(0)"
					onclick="$('#content').load('user/UserManage!showTransactionRecord.action')">
						交易记录 </a></li>
			</ul>
		</div>
		<div class="col-xs-10" id="content">
			<h2>
				<strong>欢迎！</strong><small>用户${user.account}</small>
			</h2>
			<br>
			<h4>我的收货地址</h4>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="col-xs-1">收货人</th>
						<th class="col-xs-5">收货地址</th>
						<th class="col-xs-3">电话</th>
						<th class="col-xs-3">邮箱</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${user.name}</td>
						<td>${user.address}</td>
						<td>${user.tel}</td>
						<td>${user.mail}</td>
					</tr>
				</tbody>
			</table>
			<br>
			<h4>我上次的订单</h4>

			<c:choose>
			<c:when test="${recentorder.id != -1}">
			<div class="panel panel-default">
				<div class="panel-heading text-success">
					<a href="user/orderDetail.jsp?orderId=${recentorder.id}">
						<ul class="list-inline">
							<li><strong>${fn:substring(recentorder.paytime, 0,
									19)}</strong></li>
							<li>订单号：${recentorder.ordernum }</li>
							<li>${recentorder.shopName }</li>
							<li>总金额：<fmt:formatNumber type="number"
									value="${recentorder.totalAmount }" pattern="0.00"
									maxFractionDigits="2" />
							</li>
							<li>状态： <c:choose>
									<c:when test="${recentorder.state == 1}">未支付</c:when>
									<c:when test="${recentorder.state == 2}">待接单</c:when>
									<c:when test="${recentorder.state == 3}">取件中</c:when>
									<c:when test="${recentorder.state == 4}">清洗中</c:when>
									<c:when test="${recentorder.state == 5}">已送达</c:when>
									<c:when test="${recentorder.state == 11}">待评价</c:when>
									<c:when test="${recentorder.state == 12}">已评论</c:when>
									<c:when test="${recentorder.state == -1}">已取消</c:when>
								</c:choose>
							</li>
						</ul> 
					</a>
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th class="col-xs-2">衣服名</th>
							<th class="col-xs-2">清洗方式</th>
							<th class="col-xs-2">单价</th>
							<th class="col-xs-2">数量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${recentorder.orderItems}">
							<tr>
								<td class="col-xs-2">${item.clothName}</td>
								<td class="col-xs-1"><c:choose>
										<c:when test="${item.washMode == 1}">干洗</c:when>
										<c:when test="${item.washMode == 2}">水洗</c:when>
									</c:choose>
								</td>
								<td class="col-xs-2">${item.price}</td>
								<td class="col-xs-1">${item.amount}</td>
								<c:if test="${state.count==1}">
									<td class="col-xs-2"><strong>${recentorder.totalAmount}</strong>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="panel-footer"></div>
			</div>
		</div>
		</c:when>
		<c:otherwise>
			<br><h2 class="text-center">您暂时还没有订单哦~</h2>
			<div style="height:200px;"></div>
		</c:otherwise>
		</c:choose>
		</div>
		<div style="height:200px;"></div>
		<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
