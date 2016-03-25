<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>超级洗衣店-店铺审核</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/membership.css">
	
	<script type="text/javascript">
		var url;
		$(function(){
		  $("#dg").datagrid('getPager').pagination({pageList: [10]});
		});
		function formatOper(val,row,index){  
		    return '<a href="javascript:;" onClick="javascript:approve('+index+')" style="text-decoration:none;">同意</a>&nbsp;|&nbsp;'+
		    	'<a href="javascript:;" onClick="javascript:refuse('+index+')" style="text-decoration:none;">拒绝</a>';  
		} 
		function approve(index){
			$("#dg").datagrid("selectRow",index);
			var row=$("#dg").datagrid("getSelected");
			if(row){
	            $.post("manager/manager!shopApprove",{shopId:row.id,approve:2},function(result){
	                if(result.result!="error"){
	                    $.messager.alert("系统提示","审批成功！");
	                    $("#dg").datagrid("reload");
	                }else{
	                    $.messager.alert("系统提示","审批失败！");
	                }
	            },"json");
		    }
		}
		function refuse(index){
			$("#dg").datagrid("selectRow",index);
			var row=$("#dg").datagrid("getSelected");
			$.messager.confirm("系统提示","确定要拒绝该申请吗？",function(r){
				if(r){
					$.post("manager/manager!shopApprove",{shopId:row.id,approve:3},function(result){
						if(result.result!="error"){
							$.messager.alert("系统提示","审批成功！");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示","审批失败！");
						}
					},"json");
				}
			});
		}
		
	</script>
  </head>

  <body>
      <table id="dg" title="会员信息" class="easyui-datagrid" fitcolumns="true"
      pagination="true" rownumbers="true" url="manager/manager!getShopApplies">
          <thead>
              <tr>
                  <th field="id" width="20px">店铺编号</th>
                  <th field="shopname" width="50px">店铺名</th>
                  <th field="address" width="100px">地址</th>
                  <th field="tel">电话</th>
                  <th field="comment" width="100px">描述</th>
                  <th field="state">状态</th>
                  <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">
                  	操作
                  </th>
              </tr>
          </thead>
      </table>
  </body>
</html>    