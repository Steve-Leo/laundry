<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ruanku.model.RechargeRecord"%>
<%@ page import="com.ruanku.model.Order"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<script src="script/account_detail.js"></script>
<base href="<%=basePath%>">
<h3>交易记录</h3>
<br>
<ul class="nav nav-tabs">
	<li id="nav-charge-table" class="active">
		<a href="javascript:void(0)">充值记录 </a>
	</li>
	<li id="nav-consume-table">
		<a href="javascript:void(0)"> 消费记录 </a>
	</li>
</ul>
<br>

	
	<table id="charge-table" class="table table-bordered table-hover">
	<thead>
		<tr class="active">
			<th class="col-xs-4">流水号</th>
			<th class="col-xs-2">充值金额</th>
			<th class="col-xs-3">日期</th>
			<th class="col-xs-3">描述</th>
		</tr>
	</thead>
	<c:if test="${!empty rlist}">
	<c:forEach var="recharge" items="${rlist}">
	
		<tbody>
		<tr>
			<td>${recharge.serialNum }</td>
			<td><fmt:formatNumber type="number" value="${recharge.money }" pattern="0.00" maxFractionDigits="2"/></td>
			<td>${fn:substring(recharge.time, 0, 19)}</td>
			<td>
				<c:choose>
				<c:when test="${recharge.status == 1}">充值</c:when>
				<c:when test="${recharge.status == 2}">退款</c:when>
				<c:when test="${recharge.status == 3}">充值失败</c:when>
				</c:choose> 
			</td>
		</tr>
	</tbody>
	
	</c:forEach>
	</c:if>
	</table>
	
	<table id="consume-table" class="table table-bordered table-hover">
	<thead>
		<tr class="active">
			<th class="col-xs-3">订单号</th>
			<th class="col-xs-2">消费金额</th>
			<th class="col-xs-3">付款日期</th>
		</tr>
	</thead>
	<c:if test="${!empty clist}">
	<c:forEach var="order" items="${clist}">
	
		<tbody>
		<tr>
			<td><a href="user/orderDetail.jsp?orderId=${order.id}">${order.ordernum }</a></td>
			<td><fmt:formatNumber type="number" value="${order.totalAmount}" pattern="0.00" maxFractionDigits="2"/></td>
			<td>${fn:substring(order.chargeTime, 0, 19) }</td>
		</tr>
	</tbody>
	
	</c:forEach>
	</c:if>
	</table>
	
	




