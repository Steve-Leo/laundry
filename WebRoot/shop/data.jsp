<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ruanku.model.*"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
<!-- HTTP 1.1 -->  
<meta http-equiv="pragma" content="no-cache">  
<!-- HTTP 1.0 -->  
<meta http-equiv="cache-control" content="no-cache">  
<!-- Prevent caching at the proxy server -->  
<meta http-equiv="expires" content="0">  
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" /> 
<script src="script/data.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<!--<div class="col-sm-9">
	<label class="col-sm-3 control-label"> 请选择查询月份:</label>
	 <div class="form-group  col-sm-3">
		<select class="form-control">
			<option value="">2015年</option>
			<option value="">2016年</option>
		</select>
	</div>
	<div class="form-group  col-sm-3">
		<select class="form-control">
			<option value="">1月</option>
			<option value="">2月</option>
			<option value="">3月</option>
			<option value="">4月</option>
			<option value="">5月</option>
			<option value="">6月</option>
			<option value="">7月</option>
			<option value="">8月</option>
			<option value="">9月</option>
			<option value="">10月</option>
			<option value="">11月</option>
			<option value="">12月</option>
		</select>
	</div>
</div> -->
<br>
<br>
<div class="input-group col-xs-4">
    <table>
    <tr>
		<td><label > 请选择查询年份:</label></td>
		<td style="padding-left:15px;"><div class="input-group-btn">
			<button class="btn btn-default dropdown-toggle" type="button"
				id="screen" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="true">
				筛选 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu dropdown-menu-right"
				aria-labelledby="screen">
				<li><a id="screen" href="javascript:void(0)" name="2015">2015</a></li>
				<li><a id="screen" href="javascript:void(0)" name="2016">2016</a></li>
				<li><a id="screen" href="javascript:void(0)" name="2017">2017</a></li>
				<li><a id="screen" href="javascript:void(0)" name="2018">2018</a></li>
				<li><a id="screen" href="javascript:void(0)" name="2019">2019</a></li>
				<li><a id="screen" href="javascript:void(0)" name="2020">2020</a></li>
			</ul>
		</div></td>
		</tr>
		</table>
	</div>
	<%
	String data =(String) session.getAttribute("data");
	System.out.println("----------1111----"+data+"-------------------");
	 if(data!=null) {%>
	<div id="chart_container" style="margin-top:20px;">
	<img src="linechart?type=2<%=Calendar.getInstance().getTimeInMillis()%>"/>
	<% 
	
	} %>
	
	</div>
<!--<table id="data-table" class="table table-bordered table-striped">
	<thead>
		<tr>
			<th class="col-xs-1">年月</th>
			<th class="col-xs-2">订单数</th>
			<th class="col-xs-2">总收入（元）</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${!empty slist}">
			<c:forEach var="st" items="${slist}">
				<tr>
					<th>${st.yearMonth}</th>
					
					<td>${st.orderNum}</td>
					<td>${st.sumTotal}</td>
				</tr>
			</c:forEach>
		</c:if>
		
		
	</tbody>
</table>-->