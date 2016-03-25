<%@ page language="java" import="java.util.*,com.ruanku.yeepay.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>	
<%!	String formatString(String text){if(text == null) {return "";} return text;}%>
<%	
	String keyValue   		     	= formatString(Configuration.getInstance().getValue("keyValue"));   			// 商家密钥
	String nodeAuthorizationURL  	= formatString(Configuration.getInstance().getValue("yeepayCommonReqURL")); 	// 交易请求地址
	// 商家设置用户购买商品的支付信息
	String    p0_Cmd 		     	= formatString("Buy");                               							// 在线支付请求，固定值 ”Buy”
	String    p1_MerId 		    	= formatString(Configuration.getInstance().getValue("p1_MerId")); 				// 商户编号
	String    p2_Order           	= formatString((String)(request.getAttribute("ordernum")));           			// 商户订单号
	String	  p3_Amt           	 	= formatString((String)(request.getAttribute("total")));      	   				// 支付金额
	String	  p4_Cur    		 	= formatString("CNY");	   		   												// 交易币种
 	String	  p5_Pid 		     	= formatString("wash service");	    											// 商品名称  
	String	  p6_Pcat  		     	= formatString("producttype");	       	   										// 商品种类
	String 	  p7_Pdesc   		 	= formatString("productdesc");		   											// 商品描述
	String 	  p8_Url 	         	= formatString("http://127.0.0.1:8080/laundry/user/payresult.jsp"); 			// 商户接收支付成功数据的地址
	String 	  p9_SAF 		     	= formatString("0"); 			   												// 需要填写送货信息 0：不需要  1:需要
	String 	  pa_MP 			 	= formatString("");         	   												// 商户扩展信息
	String    pd_FrpId           	= formatString("");       	   													// 支付通道编码 */
	// 银行编号必须大写
	pd_FrpId = pd_FrpId.toUpperCase();
	String 	  pr_NeedResponse    	= formatString("1");    // 默认为"1"，需要应答机制
  	String 	  hmac 			     	= formatString("");		// 交易签名串
    
    // 获得MD5-HMAC签名
    hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
			p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,
			p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,keyValue);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>支付订单</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/payorder.css">
<script src="script/payorder.js"></script>
</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	<div class="container">
		<div class="order_block">
			<div class="head_block">
				<div class="info_block">
					<p class="title">订单已提交，请您尽快支付!</p>
					<p class="account_info">订单号：${ordernum}&nbsp;&nbsp;|&nbsp;&nbsp;应付金额：<strong id="total">${message}</strong>&nbsp;元</p>
					<div class="address_info">
						<p>店家名称为：<strong>${address}</strong>，请认真核对信息，并尽快完成支付。使用站内支付时请保管好您的支付密码。</p>
					</div>
				</div>
			</div>
			<div class="pay_block">
				<ul class="nav nav-tabs">
					<li id="pay_by_balance" class="active"><a href="javascript:void(0)">站内支付</a>
					</li>
					<li id="pay_by_yi"><a href="javascript:void(0)">第三方支付</a></li>
				</ul>
				<div class="box">
					<a id="id" hidden>${orderId}</a>
					<div id="block_balance">
						<p class="balance_block">您的余额：<strong><span id="balance">
							<fmt:formatNumber type="number" value="${money}" pattern="0.00" maxFractionDigits="2"/>
						</span></strong>&nbsp;元</p>
						<div id="hint"></div>
						<div class="control_block">
							<input id="password" class="form-control" type="password"
								placeholder="请输入支付密码，初始为：123" maxlength=20></input>
							<button class="btn btn-success" id="payWithMoney">立即支付</button>
						</div>
					</div>
					<div id="block_yi">
						<form name="yeepay" action='<%=nodeAuthorizationURL%>'
							method='POST'>
							<input type='hidden' name='p0_Cmd' value='<%=p0_Cmd%>'> 
							<input type='hidden' name='p1_MerId' value='<%=p1_MerId%>'> 
							<input type='hidden' name='p2_Order' value='<%=p2_Order%>'> 
							<input type='hidden' name='p3_Amt' value='<%=p3_Amt%>'> 
							<input type='hidden' name='p4_Cur' value='<%=p4_Cur%>'> 
							<input type='hidden' name='p5_Pid' value='<%=p5_Pid%>'> 
							<input type='hidden' name='p6_Pcat' value='<%=p6_Pcat%>'> 
							<input type='hidden' name='p7_Pdesc' value='<%=p7_Pdesc%>'> 
							<input type='hidden' name='p8_Url' value='<%=p8_Url%>'> 
							<input type='hidden' name='p9_SAF' value='<%=p9_SAF%>'> 
							<input type='hidden' name='pa_MP' value='<%=pa_MP%>'> 
							<input type='hidden' name='pd_FrpId' value='<%=pd_FrpId%>'> 
							<input type="hidden" name="pr_NeedResponse" value="<%=pr_NeedResponse%>"> 
							<input type='hidden' name='hmac' value='<%=hmac%>'> 
							<img src="image/yiLogo.jpg" style="margin-top:40"/><br>
							<input class= "btn btn-success" type='submit' value="使用易宝支付"/>
						</form>
					</div>
				</div>
			</div>
		<div style="height:100px;"></div>
		<jsp:include page="../foot.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
