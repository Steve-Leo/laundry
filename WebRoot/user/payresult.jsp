<%@ page language="java" import="java.util.*,com.ruanku.yeepay.PaymentForOnlineService,com.ruanku.yeepay.Configuration" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%!	String formatString(String text){if(text == null) {return "";} return text;}%>
<%	
	String keyValue   = formatString(Configuration.getInstance().getValue("keyValue"));   // 商家密钥
	String r0_Cmd 	  = formatString(request.getParameter("r0_Cmd")); // 业务类型
	String p1_MerId   = formatString(Configuration.getInstance().getValue("p1_MerId"));   // 商户编号
	String r1_Code    = formatString(request.getParameter("r1_Code"));// 支付结果
	String r2_TrxId   = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
	String r3_Amt     = formatString(request.getParameter("r3_Amt"));// 支付金额
	String r4_Cur     = formatString(request.getParameter("r4_Cur"));// 交易币种
	String r5_Pid     = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"),"gbk");// 商品名称
	String r6_Order   = formatString(request.getParameter("r6_Order"));// 商户订单号
	String r7_Uid     = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
	String r8_MP      = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"),"gbk");// 商户扩展信息
	String r9_BType   = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
	String hmac       = formatString(request.getParameter("hmac"));// 签名数据
	boolean result = false;
	boolean isOK = false;
	// 校验返回数据包
	isOK = PaymentForOnlineService.verifyCallback(hmac,p1_MerId,r0_Cmd,r1_Code, 
			r2_TrxId,r3_Amt,r4_Cur,r5_Pid,r6_Order,r7_Uid,r8_MP,r9_BType,keyValue);
	if(isOK) {
		//在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
		if(r1_Code.equals("1")) {
			// 产品通用接口支付成功返回-浏览器重定向
			if(r9_BType.equals("1")) {
				//out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
				// 产品通用接口支付成功返回-服务器点对点通讯
			} else if(r9_BType.equals("2")) {
				// 如果在发起交易请求时	设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
				//out.println("SUCCESS");
			  // 产品通用接口支付成功返回-电话支付返回		
			}
			// 下面页面输出是测试时观察结果使用
			/* out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>易宝支付交易流水号:"
			 + r2_TrxId + "<br>"); */
	 		result = true;
		}
	} 
	/* else {
		out.println("交易签名被篡改!");
	}   */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>支付订单</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/trolley.css">
<link rel="stylesheet" href="css/payresult.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="script/payresult.js"></script>
</head>

<body style="padding-top:80px">
	<div class="container">
		<div class="order_block">
			<%
				if (result) {
			%>
			<div class="head_block">
				<div class="info_block">
					<p class="title">
						您已成功支付&nbsp;<strong id="total"><%=r3_Amt%></strong>&nbsp;元
					</p>
					<p class="account_info">请等待商家接单。订单配送完成后，请确认订单以完成本次交易。</p>
					<div class="address_info">
						商家订单号&nbsp;：<a id="ordernum"><%=r6_Order%></a> <br>易宝支付交易流水号&nbsp;：<a><%=r2_TrxId%></a>
					</div>
					<p id="rs" style="display:none">success</p>
				</div>
			</div>
			<div class="pay_block">
				<div class="box">
					<p>页面将在5秒后执行跳转，请勿进行页面操作</p>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="head_block2">
				<div class="info_block">
					<p class="title2">支付失败</p>
					<p class="account_info">请检查您的支付信息是否正确，并尽快支付！</p>
					<div class="address_info">
						商家订单号&nbsp;：<a id="ordernum"><%=r6_Order%></a> <br>易宝支付交易流水号&nbsp;：<a><%=r2_TrxId%></a>
					</div>
					<p id="rs" style="display:none">failed</p>
				</div>
			</div>
			<div class="pay_block">
				<div class="box">
					<p>您可以选择重新支付或&nbsp;<a href="user/UserManage!myOrders.action">点击此处</a>&nbsp;返回订单页更换支付方式。</p>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
<form id="shit" action="user/UserManage!myOrders.action" method="post">
</form>
</body>
</html>
