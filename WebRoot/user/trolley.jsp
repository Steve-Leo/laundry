<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("submit_mode", "basket");
%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>洗衣篮</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/trolley.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/trolley.js"></script>
</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container">
		<h3>
			<strong>洗衣篮</strong>
		</h3>
		<hr>
		<p id="return_message" style="color:red">${message}</p>
		<c:choose>
		<c:when test="${!empty bblist}">
		<table class="table toolbar">
			<thead>
				<tr>
					<td class="col-xs-2"><label> 
						<input type="checkbox" id="choose_all"> 全选 </label> 
						<a id="deleteChoose" style="padding-left:10px;" href="javascript:void(0)">删除已选</a>
					</td>
					<td class="col-xs-2 text-center">清洗方式</td>
					<td class="col-xs-2 text-center">单价</td>
					<td class="col-xs-2 text-center">数量</td>
					<td class="col-xs-2 text-center">金额</td>
					<td class="col-xs-2 text-center">操作</td>
				</tr>
			</thead>
		</table>
		<br>
		<c:forEach var="basketBusi" items="${bblist}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="checkbox">
					<label> <input type="checkbox" id="check_shop" type="checkbox">${basketBusi.shopName}</label>
					<a id="shopId" style="display:none">${basketBusi.shopId}</a>
				</div>
			</div>
			
			<table class="table">
				<tbody>
				<c:forEach var="item" items="${basketBusi.items}" varStatus="state">
					<tr>
						<td id="id" style="display:none">${item.id}</td>
						<td class="col-xs-2 text-center">${item.clothName}</td>
						<td class="col-xs-2 text-center">
							<c:choose>
								<c:when test="${item.washMode == 1}">干洗</c:when>
								<c:when test="${item.washMode == 2}">水洗</c:when>
							</c:choose>
						</td>
						<td class="col-xs-2 text-center">${item.price}</td>
						<td class="col-xs-2">
							<div class="center-block">
								<div class="input-group">
									<span class="input-group-btn">
										<button class="btn btn-default" id="minus">-</button> 
									</span>
									<input type="text" class="form-control" maxlength=3 value="${item.count}" id="count"
									onpaste="return false" ondragenter="return false" oncontextmenu="return false" style="ime-mode:disabled">
									<span class="input-group-btn">
										<button class="btn btn-default" id="plus">+</button> 
									</span>
								</div>
							</div>
						<td class="col-xs-2 text-center">${item.price}</td>
						<td class="col-xs-2 text-center">
							<a id="delete" href="javascript:void(0)">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</c:forEach>
		
		<br>
		<div class="toolbar">
		<div class="controlbar">
			<label> <input type="checkbox" id="choose_all"> 全选 </label>
			<a id="deleteChoose" href="javascript:void(0)">删除已选</a>
			<a id="saveChoose" href="javascript:void(0)">保存洗衣篮</a>
			<p>一次只能结算一个商店的商品</p>
		</div>
		<div class="accountbar">
			<p>已选商品 <strong id="total_count">0</strong> 件</p>
			<p>总金额 <strong id="sum">0</strong> 元</p>
			<a id="checkout" class="btn" href="javascript:void(0)" disabled="disabled">
				<span><font color=black>结算</font></span>
			</a>
		</div>
		</div>
		<form id="data_submit" action="" method="post" style="display:none;">
			<input name="message">
			<input name="ordernum">
		</form>
		</c:when>
		<c:otherwise>
			<br><br><br><h3 class="text-center">暂无记录，快去首页的店铺里看看吧~~</h3>
		</c:otherwise>
		</c:choose>
		<div style="height:150px;"></div>
		<jsp:include page="../foot.jsp"></jsp:include>
	</div>
</body>
</html>
