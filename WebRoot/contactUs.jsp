<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>关于</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>  
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript">
	   $(function(){
		   var index = <%=request.getParameter("index")%>
		   if(index != "1"){
			   $('#collapse1').collapse('toggle');
			   $('#collapse'+index).collapse('toggle');
		   }
	   });
	</script>
  </head>
  
  <body>
  	<!-- 导航条 -->
    <jsp:include page="navigation.jsp" ></jsp:include>
    
    <div class="container" style="width:1170px;height:500px;margin-top:100px;">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		  <div class="panel panel-default">
		    <div class="panel-heading" role="tab" id="headingOne">
		      <h4 class="panel-title">
		        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="true" aria-controls="collapse1">
		          <h4>关于我们</h4>
		        </a>
		      </h4>
		    </div>
		    <div id="collapse1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
		      <div class="panel-body">
		        关于我们
		      </div>
		    </div>
		  </div>
		  <div class="panel panel-default">
		    <div class="panel-heading" role="tab" id="headingTwo">
		      <h4 class="panel-title">
		        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false" aria-controls="collapse2">
		         <h4> 联系我们</h4>
		        </a>
		      </h4>
		    </div>
		    <div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
		      <div class="panel-body">
				<p>全国统一售后服务免费电话：000-000-0000（需输入总部提供的密码，只对已开业的加盟店提供）</p>
				<p>全国统一洗染咨询免费电话：111-111-1111（需输入总部提供的密码，只对已开业的加盟店提供）</p>
				<p>售后服务：000-00000000 &nbsp;&nbsp;&nbsp;&nbsp;    13800000000</p>
				<p>洗染咨询：000-51690000 &nbsp;&nbsp;&nbsp;&nbsp;    13900000000</p>
				<p>加盟商投诉：000-51690000</p>
				<p>投诉邮箱：panpan@qq.com	</p>
			  </div>
		    </div>
		  </div>
		  <div class="panel panel-default">
		    <div class="panel-heading" role="tab" id="headingThree">
		      <h4 class="panel-title">
		        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse3" aria-expanded="false" aria-controls="collapse3">
		         <h4> 市内配送</h4>
		        </a>
		      </h4>
		    </div>
		    <div id="collapse3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
		      <div class="panel-body">
				<p>1、遍布全市的物流服务网点，能够及时上门取送您的衣物。</p>
				<p>2、近百辆高配置物流车队，能够快速高效的配送。</p>
				<p>只要您下单成功，就能够享受在48小时内完成的快速洗衣体验。</p>
		      </div>
		    </div>
		  </div>
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id=""headingFour"">
              <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse4" aria-expanded="false" aria-controls="collapse4">
                 <h4> 站内支付</h4>
                </a>
              </h4>
            </div>
            <div id="collapse4" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
              <div class="panel-body">
				<p>站内充值为一次性充值，目前仅可通过官网充值！</p>
				<p>1.用户登录官网后，点击"会员中心"，跳转至个人管理中心;</p>
				<p>2.点选“用户充值”选项进行绑定充值，完成后用户方可通过官网完成下单。</p>
				<p>3.站内支付方式：在订单支付页面，选择站内支付，输入“支付密码”，点击"确认付款"即可完成订单！</p>
              </div>
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingFive">
              <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse5" aria-expanded="false" aria-controls="collapse5">
                 <h4> 第三方支付</h4>
                </a>
              </h4>
            </div>
            <div id="collapse5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
              <div class="panel-body">
                <h4>软酷洗衣目前提供易宝支付方式。</h4>
              </div>
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingSix">
              <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse6" aria-expanded="false" aria-controls="collapse6">
                  <h4>店铺加盟</h4>
                </a>
              </h4>
            </div>
            <div id="collapse6" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
              <div class="panel-body">
                <h3>店铺加盟</h3>
			               <p> 软酷洗衣店很乐意帮助您和您的店铺；</p>
			                <p> 软酷洗衣店为每一家加盟店铺开设网店，负责网店的设计和架构；</p>
			                <p>加盟店铺通过软酷洗衣店平台的强势推广，迅速建立起良好的品牌形象和市场知名度，销售收入迅速增长。在软酷洗衣店的帮助下，优秀的洗衣店能够迅速做强做大。</p>
			                <h3>店铺加盟要求</h3>
			                <p>您需要正在经营的店铺，手续齐全，符合相关卫生标准；</p>
			                <p>店铺提供配送服务；</p>
			                <p>信息真实可靠。</p>
			                <h3>网店开设流程</h3>
			                <h4>第一步： 申请开设网店</h4>
			                <p><a href="shop/shopregister.jsp">点击这里</a>填写店铺详细信息，申请加入软酷洗衣店平台。信息越详细，通过审核率越高；</p>
			                <h4>第二步： 录入衣物信息</h4>
			               <p> 通过审核后，相关工作人员会联系您录入店铺衣物信息。录入衣物时可以添加图片和详细介绍。及时更新菜单信息和生动的介绍有助于吸引客户；</p>
			                <h4>第三步： 管理订单</h4>
			                <p>使用软酷洗衣店网络管理系统处理订单，减少人工劳动，及时高效，为您店铺的发展扫清障碍。              </p>
              </div>
            </div>
          </div>
		</div>	
    </div> 
  </body>
</html>
