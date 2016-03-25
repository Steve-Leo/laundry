<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>超级洗衣店-管理员登录</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
    <link rel="shortcut icon" href="image/favicon.ico" >
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>  
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
  </head>

  <body>
  	<jsp:include page="../navigation.jsp"></jsp:include>
  	
  	<!-- 内容主体 -->
  	<div class="container">
  		<div class="login">
	  		<form class="admin_lg" method="post" name="registerform"
				action="manager/manager!login.action">
	  		  <div class="input-group">
				<span class="input-group-addon glyphicon glyphicon-user" id="user_pic"></span>
				<input type="text" class="form-control" name="name"
				 placeholder="用户名" aria-describedby="user_pic">
			  </div>
			  <div class="input-group admin_lg_pw">
			    <span class="input-group-addon glyphicon glyphicon-lock" id="user_pwd"></span>
			    <input type="password" class="form-control" id="password" name="password"
			    placeholder="密码" aria-describedby="user_pwd">
			  </div>
			  <button type="submit" class="btn btn-primary btn-lg">登录</button>
			</form>
	  	</div>
  	</div>

  	<jsp:include page="../foot.jsp"></jsp:include>
  </body>
</html>