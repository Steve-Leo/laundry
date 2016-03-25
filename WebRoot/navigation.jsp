<!DOCTYPE html>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ruanku.model.User"%>
 <nav class="navbar navbar-default navbar-fixed-top ">
     <div class="container navigation">
         <div class="navbar-header">
             <a class="navbar-brand logo" href="user/toIndex.action"></a>
         </div>
         <div id="navbar">
             <ul class="nav navbar-nav">
                 <li>
                     <a href="user/toIndex.action">首页</a>
                 </li>
			  	<li>
                     <a href="user/UserManage!myOrders.action">我的订单 <span class="badge"></span></a>
                 </li>
                 <li>
                     <a href="user/UserManage!myBasket.action" id="basket">我的洗衣篮 <span class="badge"></span></a>
                 </li>
			  <li>
                     <a href="user/toUserCenter.action">会员中心 <span class="badge"></span></a>
                 </li>
             </ul>
			<%
    		User user = (User)session.getAttribute("user");
    		if(user == null){
    		%>
              <ul class="nav navbar-nav navbar-right">
                  <li>
                      <a href="user/login.jsp">登录</a>
                  </li>
                  <li>
                      <a href="user/register.jsp">注册</a>
                  </li>
              </ul>
            <%
            	}else{
             %>
              <ul class="nav navbar-nav navbar-right">
              	<!-- 会员头像（预留） -->
              	<li><img src="" alt="" class="img-circle"></li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
		          aria-haspopup="true" aria-expanded="false">${user.account} <span class="caret"></span></a>
		          <ul class="dropdown-menu pull-right"  role="menu" aria-labelledby="dropdownMenu1">
		            <li><a href="user/toUserCenter.action">会员中心</a></li>
		            <li><a href="user/UserManage!myOrders.action">我的订单</a></li>
		            <li><a href="user/UserManage!myBasket.action">洗衣篮</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="user/logout.action">安全退出</a></li>
		          </ul>
		        </li>
              </ul>
            <%
            	}
             %>
         </div>
     </div>
 </nav>
