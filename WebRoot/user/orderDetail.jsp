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

<title>订单详情</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function(){
        var orderId = <%=request.getParameter("orderId")%>;
        $.post("user/UserManage!orderDetail",{orderId:orderId},function(result){
            var myOrder = result.myOrder;
            var myClothes = myOrder.clothes;
            $("#orderDate").text(myOrder.paytime);
            $("#orderNum").text(myOrder.ordernum);
            $("#shopName").text(myOrder.shopName);
            $("#receiver").text(myOrder.receiver);
            $("#tel").text(myOrder.tel);
            $("#total_amount").text(myOrder.totalAmount);
            $("#address").text(myOrder.address);
            switch(myOrder.state){
                case 1:$("#orderState").text("未支付");break;
                case 2:$("#orderState").text("待接单");break;
                case 3:$("#orderState").text("取件中");break;
                case 4:$("#orderState").text("清洗中");break;
                case 5:$("#orderState").text("已送达");break;
                case 11:$("#orderState").text("待评价");break;
                case 12:$("#orderState").text("已评论");break;
                case -1:$("#orderState").text("已取消");break;
            }
            var panel = document.getElementById("orderPanel");
            for(var i = 0; i < myClothes.length;i++){
                var row = document.createElement('div');
                row.setAttribute('class','row');
                var col1 = document.createElement('div');
                col1.setAttribute('class','col-md-2');
                var col2 = document.createElement('div');
                col2.setAttribute('class','col-md-2');
                var col3 = document.createElement('div');
                col3.setAttribute('class','col-md-2');
                var col4 = document.createElement('div');
                col4.setAttribute('class','col-md-2');
                var col5 = document.createElement('div');
                col5.setAttribute('class','col-md-2');
                row.appendChild(col1);
                row.appendChild(col2);
                row.appendChild(col3);
                row.appendChild(col4);
                row.appendChild(col5);
                col1.innerHTML="衣物名称："+myClothes[i].clothName;
                col2.innerHTML="清洗方式："+myClothes[i].washMode==1?"干洗":"水洗";
                col3.innerHTML="单价："+myClothes[i].price;
                col4.innerHTML="数量："+myClothes[i].amount;
                col5.innerHTML="金额："+myClothes[i].price*myClothes[i].amount;
                panel.appendChild(row);
            }
        },"json");
    });
</script>
</head>

<body style="padding-top:70px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container">
		<h3>
			<strong>订单详情</strong>
		</h3><hr>
		<div class="panel panel-default">
			<div class="panel-heading">
			    <div class="row">
                    <div class="col-md-3"><strong id="orderDate">2016-02-16</strong></div>
                    <div class="col-md-3">订单号：<span id="orderNum"></span></div>
                    <div class="col-md-3">洗衣店：<span id="shopName"></span></div>
                    <div class="col-md-3">订单状态：<span id="orderState"></span></div>
			    </div>
			</div>
        </div>
		<div class="panel-body" id="orderPanel">
		</div>
        <div class="panel-footer">
            <div class="row">
                <div class="col-md-3">收货人：<span id="receiver"></span></div>
                <div class="col-md-3">联系方式：<strong id="tel"></strong></div>
                <div class="col-md-3 col-md-offset-3">订单总额：<span id="total_amount"></span></div>
            </div>
	        <ul class="list-inline" style="margin-top:5px;">
	           <li>地址：<span id="address"></span></li>
	        </ul>
        </div>
    </div>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
