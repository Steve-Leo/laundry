<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>超级洗衣店-数据统计</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/membership.css">
	
	<script type="text/javascript">
		function formatOper(val,row,index){  
		    return '<a  href="javascript:;" onClick="javascript:details('+index+')" style="text-decoration:none;">详情</a>';
		} 
		$(document).ready(function(){
		    $("#dg").datagrid('getPager').pagination({pageList: [10]});
		    $("#detail_dg").datagrid('getPager').pagination({pageList: [10]});
			var date=new Date();
			var year=date.getFullYear();
			var month=date.getMonth()+1;
            $("#dlg").dialog("close");
			$("#year  option[value='"+year+"'] ").attr("selected",true);
			$("#month  option[value='"+month+"'] ").attr("selected",true);
			$("#month").change(function(){
				var year=$("#year").val();
				var month=$("#month").val();
	            $("#dg").datagrid({
	                url:"manager/manager!getStatistics",
	                queryParams:{formatMonth:getFormatMonth(year, month)}
	            });
			});
            $("#year").change(function(){
                var year=$("#year").val();
                var month=$("#month").val();
                $("#dg").datagrid({
                    url:"manager/manager!getStatistics",
                    queryParams:{formatMonth:getFormatMonth(year, month)}
                });
            });
		});
		function getFormatMonth(year,month){
            var fMonth;
            if(parseInt(month)<"10"){
                fMonth="0"+month;
            }else{
                fMonth=month;
            }
            return year+"-"+fMonth;
		}
		function details(index){
			$("#dg").datagrid("selectRow",index);
			var row = $("#dg").datagrid("getSelected");
			var year=$("#year").val();
			var month=$("#month").val();
            $("#detail_dg").datagrid("load",{shop_id:row.shop_id,formatMonth:getFormatMonth(year, month)});
			$("#dlg").dialog({
                    title:"详细信息",
                    modal:true,
                    top:50
                }).dialog("open");
		}
        function searchShop(){
            var year=$("#year").val();
            var month=$("#month").val();
            $("#dg").datagrid("load",{shopName:$("#s_shopName").val(),formatMonth:getFormatMonth(year, month)});
        }
        function formatOrderState(val,row){
            switch(val){
                case 1:return "未支付";
                case 2:return "待接单";
                case 3:return "取件中";
                case 4:return "清洗中";
                case 5:return "已送达";
                case 11:return "待评价";
                case 12:return "已评论";
                case -1:return "已取消";
            }
        }
	</script>
  </head>

  <body>
      <table id="dg" title="会员信息" class="easyui-datagrid" fitcolumns="true"
      pagination="true" rownumbers="true" toolbar="#tb" url="manager/manager!getStatistics">
          <thead>
              <tr>
                  <th field="shop_id">店铺编号</th>
                  <th field="shopname">店铺名</th>
                  <th field="num">订单量</th>
                  <th field="total_amount">交易额</th>
                  <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">
                  	操作
                  </th>
              </tr>
          </thead>
      </table>
      <div id="tb">
      	&nbsp;年：
      	<select id="year">
      		<option value="2010">2010</option>
      		<option value="2011">2011</option>
      		<option value="2012">2012</option>
      		<option value="2013">2013</option>
      		<option value="2014">2014</option>
      		<option value="2015">2015</option>
      		<option value="2016">2016</option>      		
      	</select>
      	&nbsp;月：
      	<select id="month">
      		<option value="1">1</option>
      		<option value="2">2</option>
      		<option value="3">3</option>
      		<option value="4">4</option>
      		<option value="5">5</option>
      		<option value="6">6</option>
      		<option value="7">7</option>      		
      		<option value="8">8</option>
      		<option value="9">9</option>
      		<option value="10">10</option>
      		<option value="11">11</option>
      		<option value="12">12</option>
      	</select>
        &nbsp;店铺名：
        <input type="text" name="s_shopName" id="s_shopName" style="margin-top:5px;" />&nbsp;
        <a href="javascript:searchShop()" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
      </div>
      <div id="dlg" class="easyui-dialog" style="height: 350px;width: 800px; padding: 10px;" closable="true">
	      <table id="detail_dg" title="月订单" class="easyui-datagrid" fitcolumns="true"
	      pagination="true" rownumbers="true" url="manager/manager!getDetailOrders"> 
	          <thead>
	              <tr>
	                  <th field="shopname">店名</th>
	                  <th field="ordernum">订单号</th>
                      <th field="account">下单用户</th>
	                  <th field="payTime">下单时间</th>
	                  <th field="total_amount">金额</th>
	                  <th field="state" formatter="formatOrderState">状态</th>
                      <th field="comment" width="150px;">评论</th>
	              </tr>
	          </thead>
	      </table>
      </div>
  </body>
</html>    