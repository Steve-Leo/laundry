<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>超级洗衣店-会员管理</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/membership.css">
	
	<script type="text/javascript">
		var url;
		function formatOper(val,row,index){  
		    return '<a  href="javascript:;" onClick="javascript:editUser('+index+')" style="text-decoration:none;">编辑</a>&nbsp;|&nbsp;'+
		    	'<a  href="javascript:;" onClick="javascript:deleteUser('+index+')" style="text-decoration:none;">删除</a>';  
		} 
		$(function(){
		  closeDialog();
		  $("#dg").datagrid('getPager').pagination({pageList: [10]});
		});
		function editUser(index){
			$("#dg").datagrid("selectRow",index);
			var row=$("#dg").datagrid("getSelected");
			if(row){
				$("#dlg").dialog({
				    title:"修改用户信息",
                    modal:true,
                    top:100
				}).dialog("open");
				$("#fm").form("load",row);
				url="manager/manager!updateUser?id="+row.id;
			}
		}
		function deleteUser(index){
			$("#dg").datagrid("selectRow",index);
			var row=$("#dg").datagrid("getSelected");
			$.messager.confirm("系统提示","确定要删除该用户吗？",function(r){
				if(r){
					$.post("manager/manager!deleteUser",{id:row.id},function(result){
			            if(result.result != "error"){
			                $.messager.alert("系统提示","删除成功");
			                $("#dg").datagrid("reload");
			            }else{
			                $.messager.alert("系统提示","删除失败！");
			            }
					},"json");
				}
			});
		}
		function deleteUsers(){
			var users=$("#dg").datagrid("getSelections");
			if(users){
				if (users.length<1) {
					$.messager.alert("系统提示","没有选择数据！");
					return;
				}
				$.messager.confirm("系统提示","确定要删除这些用户吗？",function(r){
					if(r){
						var idArray=[];
						for(var i=0;i<users.length;i++){
							idArray.push(users[i].id);
						}
						var userIds=idArray.join(",");
						$.post("manager/manager!deleteUsers",{userIds:userIds},function(result){
							if(result.result != "error"){
								$.messager.alert("系统提示","删除成功！");
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示","删除失败！");
							}
						},"json");
					}
				});
			}else{
				$.messager.alert("系统提示","没有选择数据！");
				return;
			}
		}
		
		function resetVal(){
			$("#account").val("");
			$("#name").val("");
			$("#money").val("");
			$("#tel").val("");
			$("#mail").val("");
			$("#address").val("");
		}
		
		function saveUser(){
			$("#fm").form("submit",{
				url:url,
				onSubmit:function(){
					return $(this).form("validate");
				},
				success:function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						return;
					}else{
						$.messager.alert("系统提示","保存成功！");
					}
					closeDialog();
					$("#dg").datagrid("reload");
				}
			});
		}
		function closeDialog(){
			$("#dlg").dialog("close");
			resetVal();
		}
		function searchUser(){
            $("#dg").datagrid("load",{account:$("#s_userName").val()});
		}
	</script>
  </head>

  <body>
      <table id="dg" title="会员信息" class="easyui-datagrid" fitcolumns="true"
      pagination="true" rownumbers="true" url="manager/manager!getUsersInfo" toolbar="#tb">
          <thead>
              <tr>
                  <th id="cb" checkbox="true"></th>
                  <th field="id">会员编号</th>
                  <th field="account">用户名</th>
                  <th field="name">姓名</th>
                  <th field="money">余额</th>
                  <th field="tel">电话</th>
                  <th field="mail">邮箱</th>
                  <th field="address">地址</th>
                  <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">
                  	<a href="javascript:deleteUsers()" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
                  </th>
              </tr>
          </thead>
      </table>
      <div id="tb">
          &nbsp;用户名：
          <input type="text" name="s_userName" id="s_userName" style="margin-top:5px;" />&nbsp;
          <a href="javascript:searchUser()" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
      </div>
      <div id="dlg" class="easyui-dialog" style="height:320px;width:350px;padding:10px 20px;" buttons="#btns">
          <form id="fm" method="post">
              <table>
                  <tr>
                      <td>用户名：</td>
                      <td><input type="text" name="account" id="account" readonly/></td>
                  </tr>
                  <tr>
                      <td>姓名:</td>
                      <td><input type="text" name="name" id="name" /></td>
                  </tr>
                  <tr>
                      <td>余额:</td>
                      <td><input type="text" name="money" id="money" class="easyui-numberbox" /></td>
                  </tr>
                  <tr>
                      <td>电话:</td>
                      <td><input type="text" name="tel" id="tel" class="easyui-validatebox easyui-numberbox" 
                      data-options="required:true,validType:'length[11,11]',invalidMessage:'电话号码必须11位'"/></td>
                  </tr>
                  <tr>
                      <td>邮箱:</td>
                      <td><input type="text" name="mail" id="mail"  class="easyui-validatebox" data-options="required:true,validType:'email'"/></td>
                  </tr>
                  <tr>
                      <td>地址:</td>
                      <td><textarea rows="3" cols="21" name="address" id="address"  class="easyui-validatebox" data-options="required:true" style="resize:none;"></textarea></td>
                  </tr>
              </table>
          </form>
      </div>
      <div id="btns">
          <a href="javascript:saveUser()" class="easyui-linkbutton" iconcls="icon-ok">保存</a>
          <a href="javascript:closeDialog()" class="easyui-linkbutton" iconcls="icon-cancel">关闭</a>
      </div>
  </body>
</html>    