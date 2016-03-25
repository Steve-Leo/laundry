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

<title>店铺登录</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/cookie.min.js"></script>
<script src="script/login.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if (cookie.get("account") != null&&cookie.get("account") != ""){
			showCookie();
		}
	});
</script>
</head>

<body>
<body style="padding-top:100px">
<!-- 页头 -->
	<jsp:include page="../shopnavigation.jsp"></jsp:include>
	<div style="height:60px"></div>
	<div class="container">
		<div class="col-xs-6">
			<br />
			<img src="image/login.jpg" class="img-rounded center-block" style="width:480">
		</div>
		<div class="col-xs-5">
			<form class="form-horizontal " method="post" name="registerform"
				action="shop/shop!login.action">
				<br /> <br /> <br />
				<div class="form-group">
					<label for="account" class="col-sm-4 control-label"> 用户名 </label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="account" name="username"
							placeholder="用户名">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label"> 密码 </label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<div class="checkbox">
							<label> <input type="checkbox" id="checkbox"> 记住我 </label>
						</div>
						<%
							String message = (String) request.getAttribute("message");
							if (message != null) {
						%>
						<font color='#600000'><%=message%></font>
						<%
							}
						%>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<button type="submit" class="btn btn-primary btn-block" onclick="checkCookie()">
							登录</button>
					</div>
				</div>
			</form>
			<div>
				<div class="col-sm-offset-7 ">
					<a href="shop/shopregister.jsp" class="text-center"> 立即注册加盟 </a>
				</div>
			</div>
		</div>
		<div class="col-xs-1"></div>
	</div>
</body>
</html>
