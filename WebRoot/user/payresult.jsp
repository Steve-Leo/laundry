<%@ page language="java" import="java.util.*,com.ruanku.yeepay.PaymentForOnlineService,com.ruanku.yeepay.Configuration" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%!	String formatString(String text){if(text == null) {return "";} return text;}%>
<%	
	String keyValue   = formatString(Configuration.getInstance().getValue("keyValue"));   // �̼���Կ
	String r0_Cmd 	  = formatString(request.getParameter("r0_Cmd")); // ҵ������
	String p1_MerId   = formatString(Configuration.getInstance().getValue("p1_MerId"));   // �̻����
	String r1_Code    = formatString(request.getParameter("r1_Code"));// ֧�����
	String r2_TrxId   = formatString(request.getParameter("r2_TrxId"));// �ױ�֧��������ˮ��
	String r3_Amt     = formatString(request.getParameter("r3_Amt"));// ֧�����
	String r4_Cur     = formatString(request.getParameter("r4_Cur"));// ���ױ���
	String r5_Pid     = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"),"gbk");// ��Ʒ����
	String r6_Order   = formatString(request.getParameter("r6_Order"));// �̻�������
	String r7_Uid     = formatString(request.getParameter("r7_Uid"));// �ױ�֧����ԱID
	String r8_MP      = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"),"gbk");// �̻���չ��Ϣ
	String r9_BType   = formatString(request.getParameter("r9_BType"));// ���׽����������
	String hmac       = formatString(request.getParameter("hmac"));// ǩ������
	boolean result = false;
	boolean isOK = false;
	// У�鷵�����ݰ�
	isOK = PaymentForOnlineService.verifyCallback(hmac,p1_MerId,r0_Cmd,r1_Code, 
			r2_TrxId,r3_Amt,r4_Cur,r5_Pid,r6_Order,r7_Uid,r8_MP,r9_BType,keyValue);
	if(isOK) {
		//�ڽ��յ�֧�����֪ͨ���ж��Ƿ���й�ҵ���߼�������Ҫ�ظ�����ҵ���߼�����
		if(r1_Code.equals("1")) {
			// ��Ʒͨ�ýӿ�֧���ɹ�����-������ض���
			if(r9_BType.equals("1")) {
				//out.println("callback��ʽ:��Ʒͨ�ýӿ�֧���ɹ�����-������ض���");
				// ��Ʒͨ�ýӿ�֧���ɹ�����-��������Ե�ͨѶ
			} else if(r9_BType.equals("2")) {
				// ����ڷ���������ʱ	����ʹ��Ӧ�����ʱ������Ӧ����"success"��ͷ���ַ�������Сд������
				//out.println("SUCCESS");
			  // ��Ʒͨ�ýӿ�֧���ɹ�����-�绰֧������		
			}
			// ����ҳ������ǲ���ʱ�۲���ʹ��
			/* out.println("<br>���׳ɹ�!<br>�̼Ҷ�����:" + r6_Order + "<br>֧�����:" + r3_Amt + "<br>�ױ�֧��������ˮ��:"
			 + r2_TrxId + "<br>"); */
	 		result = true;
		}
	} 
	/* else {
		out.println("����ǩ�����۸�!");
	}   */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>֧������</title>
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
						���ѳɹ�֧��&nbsp;<strong id="total"><%=r3_Amt%></strong>&nbsp;Ԫ
					</p>
					<p class="account_info">��ȴ��̼ҽӵ�������������ɺ���ȷ�϶�������ɱ��ν��ס�</p>
					<div class="address_info">
						�̼Ҷ�����&nbsp;��<a id="ordernum"><%=r6_Order%></a> <br>�ױ�֧��������ˮ��&nbsp;��<a><%=r2_TrxId%></a>
					</div>
					<p id="rs" style="display:none">success</p>
				</div>
			</div>
			<div class="pay_block">
				<div class="box">
					<p>ҳ�潫��5���ִ����ת���������ҳ�����</p>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="head_block2">
				<div class="info_block">
					<p class="title2">֧��ʧ��</p>
					<p class="account_info">��������֧����Ϣ�Ƿ���ȷ��������֧����</p>
					<div class="address_info">
						�̼Ҷ�����&nbsp;��<a id="ordernum"><%=r6_Order%></a> <br>�ױ�֧��������ˮ��&nbsp;��<a><%=r2_TrxId%></a>
					</div>
					<p id="rs" style="display:none">failed</p>
				</div>
			</div>
			<div class="pay_block">
				<div class="box">
					<p>������ѡ������֧����&nbsp;<a href="user/UserManage!myOrders.action">����˴�</a>&nbsp;���ض���ҳ����֧����ʽ��</p>
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
