<!DOCTYPE html>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ruanku.model.Admin"%>
 <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
     <div class="container navigation">
         <div class="navbar-header">
             <a class="navbar-brand adminLogo" href="user/toIndex.action" ></a>
         </div>
         <div id="navbar">
             <!-- <ul class="nav navbar-nav">
                 <li>
                     <a href="user/toIndex.action">首页</a>
                 </li>
             </ul> -->
              <ul class="nav navbar-nav navbar-right">
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
		          aria-haspopup="true" aria-expanded="false">管理员 <span class="caret"></span></a>
		          <ul class="dropdown-menu pull-right"  role="menu" aria-labelledby="dropdownMenu1">
		            <li><a href="manager/manager!logout.action">安全退出</a></li>
		          </ul>
		        </li>
              </ul>
         </div>
     </div>
 </nav>
