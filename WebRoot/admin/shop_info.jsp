<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>超级洗衣店-店铺管理</title>
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
		    return '<a href="javascript:;" onClick="javascript:editShop('+index+')" style="text-decoration:none;">编辑</a>&nbsp;|&nbsp;'+
		    	'<a href="javascript:;" onClick="javascript:deleteShop('+index+')" style="text-decoration:none;">删除</a>';  
		} 
        $(function(){
          closeDialog();
          $("#dg").datagrid('getPager').pagination({pageList: [10]});
        });
		function editShop(index){
			$("#dg").datagrid("selectRow",index);
			var row=$("#dg").datagrid("getSelected");
			if(row){
				$("#dlg").dialog({
                    title:"修改店铺信息",
                    modal:true,
                    top:100
                }).dialog("open");
				$("#fm").form("load",row);
				url="manager/manager!updateShop?id="+row.id;
			}
		}
		function deleteShop(index){
			$("#dg").datagrid("selectRow",index);
			var row=$("#dg").datagrid("getSelected");
			$.messager.confirm("系统提示","确定要删除该店铺吗？",function(r){
				if(r){
					$.post("manager/manager!deleteShop",{id:row.id},function(result){
						if(result.result != "error"){
							$.messager.alert("系统提示","删除成功！");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示","删除失败！");
						}
					});
				}
			});
		}
		function deleteShops(){
			var shops=$("#dg").datagrid("getSelections");
			if(shops){
				if (shops.length<1) {
					$.messager.alert("系统提示","没有选择数据！");
					return;
				}
				$.messager.confirm("系统提示","确定要删除这些店铺吗？",function(r){
					if(r){
						var idArray=[];
						for(var i=0;i<shops.length;i++){
							idArray.push(shops[i].id);
						}
						var shopIds=idArray.join(",");
						$.post("manager/manager!deleteShops",{shopIds:shopIds},function(result){
							if(result.result != "error"){
								$.messager.alert("系统提示","删除成功！");
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示","删除失败!");
							}
						});
					}
				});
			}else{
				$.messager.alert("系统提示","没有选择数据！");
				return;
			}
		}
		
		function resetVal(){
			$("#shopname").val("");
			$("#address").val("");
			$("#tel").val("");
			$("#state").val("");
			//$("#money").val("");
			$("#comment").val("");
		}
		
		function saveShop(){
			$("#fm").form("submit",{
				url:url,
				onSubmit:function(){
					return $(this).form("validate");
				},
				success:function(result){
					if(result.result == "error"){
						$.messager.alert("系统提示","保存用户信息失败！");
						return;
					}else{
						$.messager.alert("系统提示","保存用户信息成功！");
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
        function searchShop(){
            $("#dg").datagrid("load",{shopName:$("#s_shopName").val()});
        }
        function formatState(val,row){
            if (val==1){
                return '待审核';
            } else if(val==2) {
                return '已通过';
            } else{
                return '已拒绝';
            }
        }
	</script>
  </head>

  <body>
      <table id="dg" title="店铺信息" class="easyui-datagrid" fitcolumns="true"
      pagination="true" rownumbers="true" url="manager/manager!getShopsInfo" toolbar="#tb">
          <thead>
              <tr>
                  <th id="cb" checkbox="true"></th>
                  <th field="id" width="20px">店铺编号</th>
                  <th field="shopname" width="50px">店名</th>
                  <th field="state" formatter="formatState">状态</th>
                  <!-- <th field="money">余额</th> -->
                  <th field="tel">电话</th>
                  <th field="address" width="100px">地址</th>
                  <th field="comment" width="100px">简介</th>
                  <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">
                  	<a href="javascript:deleteShops()" class="easyui-linkbutton" iconcls="icon-remove" plain="true">删除</a>
                  </th>
              </tr>
          </thead>
      </table>
      <div id="tb">
          &nbsp;店铺名：
          <input type="text" name="s_shopName" id="s_shopName" style="margin-top:5px;" />&nbsp;
          <a href="javascript:searchShop()" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
      </div>
      <div id="dlg" class="easyui-dialog" style="height:320px;width:500px;padding:10px 20px;" buttons="#btns">
          <form id="fm" method="post">
              <table>
                  <tr>
                      <td>店名：</td>
                      <td><input type="text" name="shopname" id="shopname" class="easyui-validatebox" data-options="required:true"/></td>
                  </tr>
                  <tr>
                      <td>状态:</td>
                      <td><input type="text" name="state" id="state" class="easyui-validatebox" data-options="required:true"/></td>
                  </tr>
                  <!-- <tr>
                      <td>余额:</td>
                      <td><input type="text" name="money" id="money"  class="easyui-numberbox"/></td>
                  </tr> -->
                  <tr>
                      <td>电话:</td>
                      <td><input type="text" name="tel" id="tel" class="easyui-validatebox easyui-numberbox" 
                      data-options="required:true,validType:'length[11,11]',invalidMessage:'电话号码必须11位'"/></td>
                  </tr>
                  <tr>
                      <td>地址:</td>
                      <td><textarea rows="4" cols="52" name="address" id="address"  class="easyui-validatebox" data-options="required:true" style="resize:none;"></textarea></td>
                  </tr>
                  <tr>
                      <td>评论:</td>
                      <td><textarea rows="4" cols="52" name="comment" id="comment" style="resize:none;"></textarea></td>
                  </tr>
              </table>
          </form>
      </div>
      <div id="btns">
          <a href="javascript:saveShop()" class="easyui-linkbutton" iconcls="icon-ok">保存</a>
          <a href="javascript:closeDialog()" class="easyui-linkbutton" iconcls="icon-cancel">关闭</a>
      </div>
  </body>
</html>    