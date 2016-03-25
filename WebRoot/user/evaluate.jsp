<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单评论</title>

<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta charset="utf-8">
<script type="text/javascript">
$(document).ready(function(){

$("#evaluate").click(function(){
	var id = $("#orderId").val();
	var text = $("#text").val();
	$.post("user/UserManage!evaluate.action",{orderId:id,message:text},function(data){
		var ms = eval('data');
   		if(ms.result=="success"){
   			window.location = "user/UserManage!myOrders.action";
   			alert("评价已成功，将会返回订单页!");
   		}
   		else{
   			alert("操作失败");
   		}
	});
});

$("#text").bind("input propertychange", function() {
        if ($(this).val().length > 200) {  
            $(this).val($(this).val().substring(0, 200));  
        }  
    }) ;

});
</script>

</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	
	<div class="container text-center">
        <h1>恭喜您！</h1><br>
		<h3><p style="color:#777">您的订单已经完成，现在您可以对订单进行评价~</p></h3><br>
		<div class="col-xs-offset-3 col-xs-6">
			<input id="orderId" value="${orderId}" hidden>
			<textarea class="form-control" id="text" rows="10" style="resize:none"></textarea><br><br>
			<button id="evaluate" class="btn btn-success btn-block">立即提交</button>
		</div>
    </div>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>

</html>
