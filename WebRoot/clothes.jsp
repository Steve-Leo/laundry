<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超级洗衣店-衣服详情</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
    <link rel="shortcut icon" href="image/favicon.ico" >
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>  
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/clothes.css">
	<script type="text/javascript">
	   var id;
	   var shopId;
	   var count;
       $(function(){
            var clothes;
            id=<%=request.getParameter("id")%>;
            shopId = <%=request.getParameter("shopId")%>;
            count = 1;
            $.post("shop/shop!clothesDetail",{id:id},function(result){
                clothes = result.clothes;
                $(".clothes_pic").css({
                    background:"url("+clothes.picture+") no-repeat 50% 50%",
                    backgroundsize:"100% 100%",
                });
                var washMode = clothes.wash_mode==1?"干洗":"水洗";
                $("#washMode").text(washMode);
                $("#name").text(clothes.name);
                $("#price").text(clothes.price1);
                $("#comment").text(clothes.comment);
            },"json");
        });
        function newOrder(){
            id=<%=request.getParameter("id")%>;
            shopId = <%=request.getParameter("shopId")%>;
            count:$("#clothes_num").val();
            location.href="user/UserManage!toOrderConfirm.action?shopid="+shopId+"&message="+id+","+count;
        }
        function addToBasket(){
            $.post("user/UserManage!addClothes",{
                id:id,
                shopid:shopId,
                count:count
                },function(result){
	                if (result.result == "success") {
						alert("添加成功！");
					}else{
					    location.href="user/login.jsp";
					}
            },"json");
        }
		function minus(){
			if(count<2){
				return;
			}
			count=count-1;
			$("#clothes_num").val(count);
		}
		function plus(){
			count=count+1;
			$("#clothes_num").val(count);
		}
	</script>
  </head>

  <body>
  	<jsp:include page="navigation.jsp"></jsp:include>
  	
  	<!-- 内容主体 -->
  	<div class="container ">
  		<div class="row clothes">
  			<div class="col-md-4">
  				<p><span id="shopname"></span></p>
  				<div class="clothes_pic"></div>
  			</div>
  			<div class="col-md-5 ">
  				<div class="clothes_info">
  					<table id="clothes_info_t">
  						<tr>
  							<td>衣服名称：</td>
  							<td><span id="name"></span></td>
  						</tr>
                        <tr>
                            <td>清洗方式：</td>
                            <td><span id="washMode"></span></td>
                        </tr>
  						<tr>
  							<td>服务价格：</td>
  							<td>￥<span id="price"></span></td>
  						</tr>
  						<tr>
  							<td>衣服件数：</td>
  							<td>
  								<a id="minus" href="javascript:minus()"><span class="glyphicon glyphicon-minus"></span></a>
  								<input type="text" id="clothes_num" value="1" readonly/>
  								<a id="plus" href="javascript:plus()"><span class="glyphicon glyphicon-plus"></span></a>
  							</td>
  						</tr>
  						<tr>
  							<td>详细描述：</td>
  							<td><span id="comment"></span></td>
  						</tr>
  					</table>
					<div class="btns">
						<input type="button" onclick="javascrip:newOrder()" value="立即洗衣" class="btn btn-success btn-lg"/>
						<input type="button" onclick="javascrip:addToBasket()" value="加入洗衣篮" class="btn btn-primary btn-lg"/>
					</div>
  				</div>
  			</div>
  		</div>
  	</div>
  	
  	<jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>