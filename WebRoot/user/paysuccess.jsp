<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta charset="utf-8">
<link href="css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js">
	
</script>
<script src="js/bootstrap.js">
	
</script>
<link href="css/bootstrap-nonresponsive.css" rel="stylesheet" />

</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container text-center">
		<br><br><br>
           <h1>恭喜您!</h1>
		<h3><p style="color:#777">付款成功. 您可以选择访问其他洗衣店或查询您的订单.</p></h3>
		<hr>
		<div class="col-xs-offset-3 col-xs-6">
			<p style="color:#777">请留意您的订单状态</p><br><br>
			<div>
				<div class="col-sm-3"></div>
					<a class="btn btn-primary col-sm-2" href="user/toIndex.action">返回主页</a>
				<div class="col-sm-2"></div>
					<a class="btn btn-primary col-sm-2" href="user/UserManage!myOrders.action">查看订单</a>
				<div class="col-sm-3"></div>
			</div>
		</div>
    </div>
    <div style="height:150px"></div>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>

</html>
