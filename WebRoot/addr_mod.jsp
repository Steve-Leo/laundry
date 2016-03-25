<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超级洗衣店-切换地址</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link rel="shortcut icon" href="image/favicon.ico" >
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>  
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/addr_mod.css">
  </head>
 
  <body class="addr_content">
  	<div class="container addr_mod_bg">
  		<div class="row text-center">
		  <div class="col-md-12">
		  	<div class="row row_logo">
		  		<div class="col-md-2 row_col">
		  			<a href="#" class="logo"></a>
		  		</div>
		  		<div class="col-md-2 col-md-offset-8 row_col">
		  			<a href="" name="a_login" id="a_login">
		  				<font id="t_login">登录</font>
		  			</a>|
		  			<a href="" name="a_login" id="a_login">
		  				<font id="t_login">注册</font>
		  			</a>
		  		</div>
		  	</div>
		  	<div class="row row_ad row_col">
		  		<div class="row_ad_pos"></div>
		  	</div>
		  	<div class="row row_search row_col">
		  		<div class="row_search_all">
		  			<input type="text" name="addr_searchbar" id="addr_searchbar" placeholder="您的城市"/>
		  			<span>&nbsp;市</span>
		  			<input type="text" name="addr_searchbtn" id="addr_searchbtn" placeholder="请输入您的地址"/>
		  			<button type="button" class="btn btn-success">搜索</button>
		  		</div>
		  	</div>
		  </div>
		</div>
  	</div>
  	
  	<!-- 页脚 -->
  	<jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>