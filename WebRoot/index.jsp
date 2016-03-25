<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ruanku.model.*"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="shortcut icon" href="image/favicon.ico" >
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
    <link rel="shortcut icon" href="image/favicon.ico" >
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>  
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vPbz2rIyB6jlHRXpNmCyKpNU"></script>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<!-- 新增 -->
	<script src="script/index.js"></script>
	<script src="script/cookie.min.js"></script>
  </head>
  
  <body>
  	<!-- 导航条 -->
    <jsp:include page="navigation.jsp" ></jsp:include>
    
    <div class="container mainBody">
    	<!-- 图片轮播 -->
    	<jsp:include page="slider.jsp" ></jsp:include>
		<!-- <div class="banner" id="banner">
			<div class="lunz" id="lunz">
				<div class="images" id="images">
					<ul>
						<li class="no0" id="no0">
							<img src="image/laundry_x.jpg" />
						</li>
						<li class="no1" id="no1">
							<img src="image/laundry_d2.jpg" />
						</li>
						<li class="no2" id="no2">
							<img src="image/laundry_gs.jpg" />
						</li>
						<li class="no3" id="no3">
							<img src="image/laundry_p.jpg" />
						</li>
					</ul>
				</div>
				<div class="btnbox" id="btnbox"> 
					<a href="javascript:void(0);" onclick="moveLeft()" class="leftbut" id="leftbut"></a> 
					<a href="javascript:void(0);" onclick="moveRight()" class="rightbut" id="rightbut"></a> 
				</div>
				<div class="dotlz" id="dotlz">
					<ul>
						<li  ></li><li  ></li><li  ></li><li  ></li><li  ></li>			
					</ul>
				</div>
			</div>
		</div> -->

		<!-- 位置和搜索栏 -->
		<div class="loc" id="loc">
			<div class="loc_d" id="loc_d">
				<span>当前地址：</span><span id="address"></span><a href="map.jsp" class="switch_addr" >[切换地址]</a>
			</div>
			<div class="loc_s" id="loc_s">
        	    <!-- <div class="input-group">
			      <input type="text" class="form-control" placeholder="输入店铺信息..." id="sr_text"/>
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="button" title="搜索店铺" onclick="javascript:searchShops()">搜索</button>
			      </span>
			    </div> -->
			</div>
		</div>
		
		<!-- 工具条 -->
		<div class="toolbar">
			<nav class="navbar navbar-default">
			  <div class="container-fluid">
			    <!-- Brand -->
			    <div class="navbar-header">
			      <a class="navbar-brand" href="#">附近商家</a>
			    </div>
			  </div><!-- /.container-fluid -->
			</nav>
		</div>

		<!-- 店铺显示 -->
		<div class="shops" id="shops">
			<%
				List<Shop> list = (List<Shop>) request.getAttribute("list");
				String flag = (String) request.getAttribute("f");
				int i = 0;
				System.out.println(list==null);
				if (list != null) {
				int length = list.size();
				for (Shop shop : list) {
					if(i%4==0)
					{
			%>
			<div class="text-center">
				<div class="col-md-3">
				<div>
					<a class="s_shop" href="javascript:void(0)" style="background-image:url(<%=shop.getPicture()%>)"> <span
						style="display:none;"><%=shop.getId()%></span> </a>
				</div>
				
				<p><%=shop.getShopname()%></p>
				<% if(flag.equals("1")) {%>
				<p>距您<%=(int)shop.getDistant()%>米</p>
				<% } %>
				</div>
				<% }
				else if((i%4==1)||(i%4==2))
				{
				 %>
				 <div class="col-md-3" >
				 <div>
					<a class="s_shop" href="javascript:void(0)" style="background-image:url(<%=shop.getPicture()%>)"> <span
						style="display:none;"><%=shop.getId()%></span> </a>
				</div>
				<p><%=shop.getShopname()%></p>
				<% if(flag.equals("1")) {%>
				<p>距您<%=(int)shop.getDistant()%>米</p>
				<% } %>
				</div>
				<%} 
				else if((i%4==3)||(i==length-1))
				{
				%>
				<div class="col-md-3">
				<div>
					<a class="s_shop" href="javascript:void(0)" style="background-image:url(<%=shop.getPicture()%>)"> <span
						style="display:none;"><%=shop.getId()%></span> </a>
				</div>
				<p><%=shop.getShopname()%></p>
				<% if(flag.equals("1")) {%>
				<p>距您<%=(int)shop.getDistant()%>米</p>
				<% } %>
				</div>
				
				<%} %>
			<%
			i++;
				}
				}
			%>
			</div>
			</div>
		</div>
				
		<!-- 页脚区分线 -->				
		<div class="content">
		</div>
    <!-- 页脚 -->
    <jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>
