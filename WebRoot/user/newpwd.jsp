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

<title>重置密码</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/user_center.js"></script>
</head>

<body style="padding-top:80px;">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<center>
		<%
			String message = (String) request.getAttribute("msg");
			if (message != null) {
		%>
		<%=message%>
		<%
			}
		%><br /> <br />

	</center>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>

</html>