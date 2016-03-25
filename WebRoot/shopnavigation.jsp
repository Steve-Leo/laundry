<!DOCTYPE html>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ruanku.model.Shop"%>
 <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
     <div class="container navigation">
         <div class="navbar-header">
             <a class="navbar-brand" >商家店铺管理</a>
         </div>
  
			<%
    		Shop shop = (Shop)session.getAttribute("shop");
    		if(shop == null){
    		%>
              <ul class="nav navbar-nav navbar-right">
                  <li>
                      <a href="shop/shoplogin.jsp">登录</a>
                  </li>
                  <li>
                      <a href="shop/shopregister.jsp">注册</a>
                  </li>
              </ul>
            <%
            	}else{
             %>
              <ul class="nav navbar-nav navbar-right">
              	
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
		          aria-haspopup="true" aria-expanded="false">${shop.shopname} <span class="caret"></span></a>
		          <ul class="dropdown-menu pull-right"  role="menu" aria-labelledby="dropdownMenu1">
		            <li><a href="shop/shop!logout.action">安全退出</a></li>
		          </ul>
		        </li>
              </ul>
            <%
            	}
             %>
         </div>
     </div>
 </nav>
