<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("result","success");
%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>查看订单</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/orders.js"></script>
</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container">
		<h3>
			<strong>我的订单</strong>
		</h3><hr>
		<div class="input-group col-xs-4">
			<input type="text" class="form-control" placeholder="输入订单号搜索" id="search_content">
			<div class="input-group-btn">
				<button class="btn btn-primary" id="search">搜索</button>
				<button class="btn btn-default dropdown-toggle" type="button"
					id="screen" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true">
					筛选 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu dropdown-menu-right"
					aria-labelledby="screen">
					<li><a id="screen" href="javascript:void(0)" name="0">全部</a></li>
					<li><a id="screen" href="javascript:void(0)" name="1">待付款</a></li>
					<li><a id="screen" href="javascript:void(0)" name="2">待接单</a></li>
					<li><a id="screen" href="javascript:void(0)" name="3">取件中</a></li>
					<li><a id="screen" href="javascript:void(0)" name="4">清洗中</a></li>
					<li><a id="screen" href="javascript:void(0)" name="5">已送达</a></li>
					<li><a id="screen" href="javascript:void(0)" name="11">待评价</a></li>
					<li><a id="screen" href="javascript:void(0)" name="12">已评价</a></li>
					<li><a id="screen" href="javascript:void(0)" name="-1">已取消</a></li>
				</ul>
			</div>
		</div><br>
		<table class="table">
			<thead>
				<tr>
					<th class="col-xs-2 text-center">衣服名称</th>
					<th class="col-xs-1 text-center">清洗方式</th>
					<th class="col-xs-2 text-center">单价</th>
					<th class="col-xs-1 text-center">数量</th>
					<th class="col-xs-2 text-center">总金额</th>
					<th class="col-xs-2 text-center">状态</th>
					<th class="col-xs-2 text-center">操作</th>
				</tr>
			</thead>
		</table>
		
		<div id="content">
		</div>
		
		<div style="height:200px;"></div>
		<jsp:include page="../foot.jsp"></jsp:include>
	</div>
</body>
</html>
