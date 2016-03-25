<%@ page language="java" import="java.util.*,com.ruanku.yeepay.*" pageEncoding="UTF-8"%>
<%!	String formatString(String text){if(text == null) {return "";} return text;}%>
<%	
	String keyValue   		     	= formatString(Configuration.getInstance().getValue("keyValue"));   			// 商家密钥
	String nodeAuthorizationURL  	= formatString(Configuration.getInstance().getValue("yeepayCommonReqURL")); 	// 交易请求地址
	// 商家设置用户购买商品的支付信息
	String    p0_Cmd 		     	= formatString("Buy");                               							// 在线支付请求，固定值 ”Buy”
	String    p1_MerId 		    	= formatString(Configuration.getInstance().getValue("p1_MerId")); 				// 商户编号
	String    p2_Order           	= formatString("");           													// 商户订单号
	String	  p3_Amt           	 	= formatString("0.01");      	   				// 支付金额(String)(request.getParameter("total"))
	String	  p4_Cur    		 	= formatString("CNY");	   		   												// 交易币种
 	String	  p5_Pid 		     	= formatString("recharge");	    												// 商品名称  
	String	  p6_Pcat  		     	= formatString("producttype");	       	   										// 商品种类
	String 	  p7_Pdesc   		 	= formatString("productdesc");		   											// 商品描述
	String 	  p8_Url 	         	= formatString("http://127.0.0.1:8080/laundry/user/recharge_result.jsp"); 		// 商户接收支付成功数据的地址
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
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#topay").submit();
});
</script>


<form id="topay" name="yeepay" action='<%=nodeAuthorizationURL%>' method='POST'>
	<input type='hidden' name='p0_Cmd' value='<%=p0_Cmd%>'> <input
		type='hidden' name='p1_MerId' value='<%=p1_MerId%>'> <input
		type='hidden' name='p2_Order' value='<%=p2_Order%>'> <input
		type='hidden' name='p3_Amt' value='<%=p3_Amt%>'> <input
		type='hidden' name='p4_Cur' value='<%=p4_Cur%>'> <input
		type='hidden' name='p5_Pid' value='<%=p5_Pid%>'> <input
		type='hidden' name='p6_Pcat' value='<%=p6_Pcat%>'> <input
		type='hidden' name='p7_Pdesc' value='<%=p7_Pdesc%>'> <input
		type='hidden' name='p8_Url' value='<%=p8_Url%>'> <input
		type='hidden' name='p9_SAF' value='<%=p9_SAF%>'> <input
		type='hidden' name='pa_MP' value='<%=pa_MP%>'> <input
		type='hidden' name='pd_FrpId' value='<%=pd_FrpId%>'> <input
		type="hidden" name="pr_NeedResponse" value="<%=pr_NeedResponse%>">
	<input type='hidden' name='hmac' value='<%=hmac%>'>
</form>


