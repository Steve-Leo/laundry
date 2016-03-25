<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>超级洗衣店-管理中心</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
    <link rel="shortcut icon" href="image/favicon.ico" >
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>  
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
	
	<%
	    if(session.getAttribute("manager")==null){
	        response.sendRedirect("login.jsp");
	        return; 
	    }
	%>
	<script type="text/javascript">
		$(document).ready(function(){
			$.post("manager/manager!userAndShopTotal",function(result){
			    $("#userTotal").text(result.userTotal);
			    $("#shopTotal").text(result.shopTotal);
			},"json");
            $.post("manager/manager!getApplyTotal",function(result){
                if (result.applicationTotal!=0) {
	                $("#applyTotal").text(result.applicationTotal);
				}
            },"json");
		});
		function userInfos(){
			if(jQuery("#tabs").tabs("exists","会员管理")){
				jQuery("#tabs").tabs("select","会员管理");
			}else {
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;margin:-15px;'"+
				"src='admin/membership.jsp'></iframe>";
				jQuery("#tabs").tabs("add",{
					title:"会员管理",
					closable:true,
					content:content
				});
			}
		}
		function shopApply(){
			if(jQuery("#tabs").tabs("exists","店铺审核")){
				jQuery("#tabs").tabs("select","店铺审核");
			}else {
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;margin:-15px;'"+
				"src='admin/shop_apply.jsp'></iframe>";
				jQuery("#tabs").tabs("add",{
					title:"店铺审核",
					closable:true,
					content:content
				});
			}
		}
		function shopInfos(){
			if(jQuery("#tabs").tabs("exists","店铺管理")){
				jQuery("#tabs").tabs("select","店铺管理");
			}else {
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;margin:-15px;'"+
				"src='admin/shop_info.jsp'></iframe>";
				jQuery("#tabs").tabs("add",{
					title:"店铺管理",
					closable:true,
					content:content
				});
			}
		}
		function statistics(){
			if(jQuery("#tabs").tabs("exists","数据统计")){
				jQuery("#tabs").tabs("select","数据统计");
			}else {
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;margin:-15px;'"+
				"src='admin/statistics.jsp'></iframe>";
				jQuery("#tabs").tabs("add",{
					title:"数据统计",
					closable:true,
					content:content
				});
			}
		}
	</script>
  </head>

  <body class="body">
  	<jsp:include page="adminHead.jsp"></jsp:include>
  	
  	<!-- 内容主体 -->
	<div class="easyui-layout content container">
		<div region="north" class="north"></div>
		<div region="west" collapsible="false" class="west" title="管理中心" style="width:200px;" >
			<div><span id="sys_center">统计中心</span></div>
			<div><a href="javascript:userInfos()" class="membership">会员管理</a></div>
			<div><a href="javascript:shopApply()" class="apply">店铺审核<span class="badge" id="applyTotal"></span></a></div>
			<div><a href="javascript:shopInfos()" class="com">店铺管理</a></div>
			<div><a href="javascript:statistics()" class="com">数据统计</a></div>
		</div>
		<div region="center" class="center">
			<div id="tabs" class="easyui-tabs" border="false" style="margin-left:-20px;margin-top:15px;"
			fit="true";>
			    <div title="系统统计中心" align="center">
			        <p id="welcome">
			        	<font color="red" size="16">欢迎进入系统管理中心</font>
			        </p>
			        <p id="menbership">
			        	当前用户数：<font id="userTotal"></font>
			        </p>
			        <p id="shops">
			        	当前店铺数：<font id="shopTotal"></font>
			        </p>
			    </div>
			</div>
		</div>
	</div>
	
  	<jsp:include page="../foot.jsp"></jsp:include>
  </body>
</html>