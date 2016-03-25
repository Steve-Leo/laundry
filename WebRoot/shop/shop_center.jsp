<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ruanku.model.Shop"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>店铺管理中心</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/shop_center.js"></script>
<style>
.table th,.table td {
	text-align: center;
}
</style>
</head>

<body style="padding-top:80px;">
	<jsp:include page="../shopnavigation.jsp"></jsp:include>
	<div class="container">
		<div class="col-xs-2">
			<h3 style="color:#000079">我的店铺</h3>
		</div>
		<div class="col-xs-9">
			<br>
			<ul class="nav nav-pills nav-justified">
				<li id="shop_info"><a href="javascript:void(0)">修改店铺资料</a>
				</li>
				<li id="clothes_info"><a href="javascript:void(0)">维护商品信息</a>
				</li>
				<li id="order_shop"><a href="javascript:void(0)">订单管理</a>
				</li>
				<li id="data"><a href="javascript:void(0)">数据统计</a>
				</li>
				<li id="workers"><a href="javascript:void(0)">员工管理</a>
				</li>
			</ul>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="col-xs-12 " id="content">
			<!-- 进入我的店铺默认显示 -->
			<div class="col-xs-5">
				<h2 style="text-align:center">
					欢迎！<span style="color:#000079">${shop.username}</span>
				</h2>
				<h3 style="text-align:center;color:#000079">
					您的店铺状态为：<br><span style="color:#930000">
					<c:choose>
						<c:when test="${shop.state == 1}">待审核</c:when>
						<c:when test="${shop.state == 2}">审核已通过</c:when>
						<c:when test="${shop.state == 3}">审核被拒绝</c:when>
					</c:choose>
					</span>
				</h3>
			</div>
			<div class="col-xs-5">
				<img src="${shop.picture}" class="img-rounded center-block" width="300" height="250">
			</div>

		</div>
	</div>
	<div style="height:200px;"></div>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
