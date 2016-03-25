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

<title>忘记密码</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container">
		<div>
			<h3>忘记密码</h3>
			<br>
			<br>
			<form class="form-horizontal col-xs-offset-2" method="post" name="registerform"
				action="user/forgot.action">
				<div class="form-group">
					<label for="account" class="col-sm-2 control-label">
						邮箱 </label>
					<div class="col-sm-6">
						<input type="email" class="form-control" id="mail" name="mail"
							placeholder="请输入您的邮箱，我们将给您重置密码">
					</div>
				</div>
				<%
					String message = (String) request.getAttribute("msg");
					if (message != null) {
				%>
				<%=message%>
				<%
					}
				%>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<button type="submit" class="btn btn-success">重置</button>
					</div>
				</div>
			</form>
			<div></div>
		</div>
		<div style="height:100px"></div>
		<jsp:include page="../foot.jsp"></jsp:include>
	</div>
</body>
</html>
