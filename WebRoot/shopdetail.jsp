<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("submit_mode", "direct");
%>
<%@ page import="com.ruanku.model.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>超级洗衣店-店铺详情</title>
	<meta http-equiv="keywords" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<meta http-equiv="description" content="洗衣店,干洗,水洗,上门取货,市内配送">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link rel="shortcut icon" href="image/favicon.ico" >
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>  
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/shopdetail.css">
	<script type="text/javascript" src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
	<script src="script/jquery.fly.min.js"></script>
	<script src="script/requestAnimationFrame.js"></script>
	<script type="text/javascript">
       var shop;
       var pageNum;
       var maxLength = 10;
       var pageCount = 0;
       var searched = false;
       var searchResult = null;
       var showAllClothes = true;
       var cPageCount = 0;
       var showAllComments = true;
       var cPageNum;
       var cMaxLength = 100;
       var allAomments;
       var showSearch = true;
       var bInit = true;
	   $(function(){
		    var id=<%=request.getParameter("id")%>;
		    $("#searchBtn").val("");
		            /* 加载商品 */
            $.post("shop/shop!shopDetail",{id:id},function(result){
                shop = result.shop;
                $(".shop_pic").css({
                    background:"url("+shop.picture+") no-repeat 50% 50%",
                    backgroundsize:"100% 100%",
                });
                $("#shopname").text(shop.shopname);
                $("#address").text(shop.address);
                $("#tel").text(shop.tel);
                $("#comment").text(shop.comment);
                setPage(shop.clothes);
                pagination(shop.clothes,0, 8);
                var page = parseInt(pageNum);
                setLastPage(shop.clothes.length,page);
            },"json");
            /*加载店铺评论*/
            $.post("shop/shop!shopComment",{id:id},function(result){
                allAomments = result;
                setComPage(allAomments);
                cPagination(allAomments,0, 100);
                var page = parseInt(cPageNum);
                setLastComPage(allAomments.length,page);
            },"json");
            $('#presentation a').click(function (e) {
              if (showSearch) {
				 $(".searchbar").css("display","none");
				 showSearch = false;
			  }else {
				 $(".searchbar").css("display","block");
				 showSearch = true;
			  }
			  e.preventDefault();
			  $(this).tab('show');
			});
            /* 跳转商品详情页 */
		    $(".shop_gs_p").click(function(){
		        var id = $(this).children("span").text();
		        var shopId=<%=request.getParameter("id")%>;
		        location.href="clothes.jsp?id="+id+"&shopId="+shopId;
		    });
		    /* 加入购物车 */
		    /* 购物车动画效果 */
			var offset = {top:-10,left:(window.innerWidth)*0.3};
			$(".shoppingcart").click(function(event){
                var id=$(this).children(".cId").text();
                var shopid=<%=request.getParameter("id")%>;
                $.post("user/UserManage!addClothes.action",{id:id,shopid:shopid},function(result){
                    if (result.result=="failed") {
						location.href="user/login.jsp";
					}
                },"json");
			    var addcar = $(this);
			    var id = addcar.children('.index').text();
			    var img;
			    if(searched){
			        img = searchResult[parseInt(id)].picture;
			    }else{
			        img = shop.clothes[parseInt(id)].picture;
			    }
			    var flyer = $('<img class="u-flyer" src="'+img+'">');
			    flyer.fly({
			        start: {
			            left: event.pageX,
			            top: event.pageY
			        },
			        end: {
			            left: offset.left+10,
			            top: offset.top+10,
			            width: 0,
			            height: 0
			        },
			        onEnd: function(){}
			    });
			});
	   });
       function setPage(clothes){
           var ul = document.getElementById("pagination");
           if(searched){
               for(var i = 1; i<=pageCount; i++){
		           ul.removeChild(ul.childNodes[2]);
               }
           }
           if (clothes.length==0) {
               pageCount=0;
	           pageNum = 0;
	           $("#pageNum").text(pageNum);
		       return;
		   }
           pageCount=parseInt(clothes.length/8);
           if(clothes.length%8 > 0){
               pageCount++;
           }
           for ( var i = 1; i<=pageCount; i++) {
               var li = document.createElement('li');
               var a = document.createElement('a');
               a.setAttribute('onclick','javascript:freshPage('+i+')');
               a.setAttribute('href','javascript:void(0)');
               a.innerHTML=i;
               li.setAttribute('id','aPage');
               if(i == 1){
                   li.setAttribute('class','active');
               }
               li.appendChild(a);
               ul.insertBefore(li,ul.childNodes[i+1]);
           }
           ul.childNodes[1].setAttribute('class','disabled');
           pageNum = 1;
           $("#pageNum").text(pageNum);
       }
       function pagination(clothesList,start,num){
           var count = num;
           if (clothesList.length==0) {
		       return;
		   }
           if (pageCount == parseInt(pageNum) && clothesList.length%8 != 0) {
			   count = clothesList.length%8;
		   }
           for(var i = 1; i<=count; i++ ){
               var picture = document.getElementById("shop_gs_p"+i);
               picture.setAttribute("style",
                   "background:url("+clothesList[start+i-1].picture+") no-repeat;"+
                   "background-size:100% 100%;");
               $("#p"+i).text(clothesList[start+i-1].price1);
               $("#n"+i).text(clothesList[start+i-1].name);
               $("#id"+i).text(clothesList[start+i-1].id);
               $("#index"+i).text(String(start+i-1));
               $("#p_id"+i).text(clothesList[start+i-1].id);
           }
       }
       function freshPage(param){
           pageNum = $("#pageNum").text();
           var step = param - pageNum;
           movePage(step);
       }
       function movePage(step){
           var check =parseInt(pageNum) + parseInt(step);
           if (check < 1 || check > pageCount) {
               return;
           }
           var ul = document.getElementById("pagination");
           ul.childNodes[parseInt(pageNum)+1].setAttribute('class','');        
           pageNum =parseInt(pageNum) + parseInt(step);
           $("#pageNum").text(pageNum);
           var pre = document.getElementById("pre");
           var next = document.getElementById("next");
           ul.childNodes[parseInt(pageNum)+1].setAttribute('class','active');      
           if(pageNum == 1){
               pre.setAttribute('class','disabled');
           }else{
               pre.setAttribute('class','');
           }
           if (pageNum == pageCount) {
			   next.setAttribute('class','disabled');
		   }else{
		       next.setAttribute('class','');
		   }
		   var page = parseInt(pageNum);
		   if (!searched) {
	           pagination(shop.clothes,(page-1)*8,8);              
	           setLastPage(shop.clothes.length,page);
		   }else{
		       pagination(searchResult,(page-1)*8,8);
		       setLastPage(searchResult.length,page);
		   };
		   
       }
       function setLastPage(total,page){
           if (page == pageCount) {
               if(total%8 != 0 || pageCount==0){
	               var lastPageClothesCount = total%8; 
	               for(var i = 8; i > lastPageClothesCount; i--){
	                   $("#clothes"+i).css('display','none');
                   }
                   for(var i = lastPageClothesCount; i > 0; i--){
                       $("#clothes"+i).css('display','block');
                   }
	               showAllClothes = false;
               };
           }else{
               if(!showAllClothes){
                   showAllClothes = true;
                   for(var i = 8; i > 0; i--){
                       $("#clothes"+i).css('display','block');
                   };
               };
           };
       }
       function search() {
	       var searchText = $("#searchBtn").val();
	       var shopId = '<%=request.getParameter("id")%>';
	       searched = true;
           $.post("shop/shop!searchClothes",{searchText:searchText,shopId:shopId},function(result){
               searchResult = result.clist;
               setPage(searchResult);
               pagination(searchResult,0, 8);
	           var page = parseInt(pageNum);
               setLastPage(searchResult.length,page);
           },"json");
	   }
       function setComPage(comments){
           var ul = document.getElementById("c_pagination");
           for(var i = 1; i<=cPageCount; i++){
               ul.removeChild(ul.childNodes[2]);
           }
           cPageCount=parseInt(comments.length/100);
           if(comments.length%100 > 0){
               cPageCount++;
           }
           for ( var i = 1; i<=cPageCount; i++) {
               var li = document.createElement('li');
               var a = document.createElement('a');
               a.setAttribute('onclick','javascript:freshComPage('+i+')');
               a.setAttribute('href','javascript:void(0)');
               a.innerHTML=i;
               li.setAttribute('id','cPage');
               if(i == 1){
                   li.setAttribute('class','active');
               }
               li.appendChild(a);
               ul.insertBefore(li,ul.childNodes[i+1]);
           }
           ul.childNodes[1].setAttribute('class','disabled');
           cPageNum = 1;
           $("#cPageNum").text(cPageNum);
       }
       
       /*评论分页*/
       function cPagination(comments,start,num){
           var count = num;
           if (cPageCount == parseInt(cPageNum) && comments.length%num != 0) {
               count = comments.length%num;
           }
           if (bInit) {
	           for(var i = 1; i<=count; i++ ){
	               var s_comments = document.getElementById('s_comments');
	               var row = document.createElement('div');
	               var br = document.createElement('br');
	               row.setAttribute('class','row');
	               row.setAttribute('id','row'+i);
	               br.setAttribute('id','br'+i);
	               var col1 = document.createElement('div');
	               col1.setAttribute('class','col-md-3');
	               col1.innerHTML=comments[start+i-1].name;
	               var col2 = document.createElement('div');
	               col2.setAttribute('class','col-md-6');
	               var text = document.createElement('textarea');
	               text.setAttribute('cols','65');
	               text.setAttribute('rows','2');
	               text.setAttribute('readonly','true');
	               text.setAttribute('style','max-width:420px;max-height:80px;overflow:auto;border:0px;resize:none;');
	               text.innerHTML=comments[start+i-1].comment;
	               col2.appendChild(text);
	               //col2.innerHTML=comments[start+i-1].comment;
	               var col3 = document.createElement('div');
	               col3.setAttribute('class','col-md-3');
	               col3.innerHTML=comments[start+i-1].comment_time;
	               row.appendChild(col1);
	               row.appendChild(col2);
	               row.appendChild(col3);
	               s_comments.appendChild(br);
	               s_comments.appendChild(row);
	           }
	           bInit = false;
           }else{
               for(var i = 1; i<=count; i++ ){
                   var row = document.getElementById('row'+i);
                   row.childNodes[0].innerHTML = comments[start+i-1].name;
                   row.childNodes[1].innerHTML = comments[start+i-1].comment;
                   row.childNodes[2].innerHTML = comments[start+i-1].comment_time;
               };
           };
       }
       function freshComPage(param){
           cPageNum = $("#cPageNum").text();
           var step = param - cPageNum;
           showComment(step);
       }
       function showComment(step){
           var check =parseInt(cPageNum) + parseInt(step);
           if (check < 1 || check > cPageCount) {
		       return;
		   }
           var ul = document.getElementById("c_pagination");
           ul.childNodes[parseInt(cPageNum)+1].setAttribute('class','');        
           cPageNum =parseInt(cPageNum) + parseInt(step);
           $("#cPageNum").text(cPageNum);
           var pre = document.getElementById("cPre");
           var next = document.getElementById("cNext");
           ul.childNodes[parseInt(cPageNum)+1].setAttribute('class','active');      
           if(cPageNum == 1){
               pre.setAttribute('class','disabled');
           }else{
               pre.setAttribute('class','');
           }
           if (cPageNum == cPageCount) {
               next.setAttribute('class','disabled');
           }else{
               next.setAttribute('class','');
           }
           var page = parseInt(cPageNum);
           cPagination(allAomments,(page-1)*100,100);              
           setLastComPage(allAomments.length,page);
          
       }
       function setLastComPage(total,page){
           if (page == cPageCount) {
               if(total%100 != 0){
                   var lastPageClothesCount = total%100; 
                   for(var i = 100; i > lastPageClothesCount; i--){
                       $("#row"+i).css('display','none');
                       $("#br"+i).css('display','none');
                   }
                   showAllComments = false;
               };
           }else{
               if(!showAllComments){
                   showAllComments = true;
                   for(var i = 100; i > 0; i--){
                       $("#row"+i).css('display','block');
                       $("#br"+i).css('display','inline');
                   };
               };
           };
       }
	</script>
  </head>

  <body>
  	<jsp:include page="navigation.jsp"></jsp:include>
  	
  	<!-- 内容主题 -->
  	<div class="container">
	  	<!-- 店铺信息 -->
	  	<div class="shop_dt">
	  		<div class="row shop_dt_info">
			  <div class="col-md-4 shop_pic"></div>
			  <div class="col-md-4">
			    <h3 style="margin-left:50px;"><span id="shopname"></span></h3>
			  	<table class="shop_info">
			  		<tr>
			  		    <td>地址：</td>
			  			<td><span id="address"></span></td>
			  		</tr>
			  		<tr>
			  		    <td>电话：</td>
			  			<td><span id="tel"></span></td>
			  		</tr>
			  		<tr>
			  		    <td valign="top">描述：</td>
			  			<td valign="top"><textarea rows="4" cols="50" id="comment" readonly></textarea></td>
			  		</tr>
			  	</table>
			  </div>
	  		</div>
	  	</div>
	  	
		<!-- 工具条 -->
		<div class="toolbar">
		<div>
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active" id="presentation"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">全部商品</a></li>
		    <li role="presentation" id="presentation"><a href="#shopCom" aria-controls="shopCom" role="tab" data-toggle="tab">店铺评价</a></li>
            <li class="searchbar">
                <div class="input-group" style="margin-left:660px;bottom:5px;">
                  <input type="text" id="searchBtn" class="form-control" placeholder="输入衣服信息...">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="javascript:search()">搜索</button>
                  </span>
                </div>
            </li>
		  </ul>
			
		  <!-- Tab panes -->
		  <div class="tab-content" style="padding-top:30px;">
		    <div role="tabpanel" class="tab-pane fade in active" id="home">
		  	   <div class="shop_gs text-center">
		  	    <div style="display:none;"><span id="pageNum"></span></div>
		  		<div class="row row1">
		  			<div class="col-md-3 gs_infos " id="clothes1">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p1" id="shop_gs_p1" href="javascript:void(0)" title="查看详细信息">
			  				    <span id="p_id1" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p1"></span></div>
		  					<div class="col-md-5"><span id="n1"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id1" style="display:none;"></span>
		  						   <span class="index" id="index1" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  			<div class="col-md-3 gs_infos" id="clothes2">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p2" id="shop_gs_p2" href="javascript:void(0)" title="查看详细信息">
			  				    <span id="p_id2" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p2"></span></div>
		  					<div class="col-md-5"><span id="n2"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id2" style="display:none;"></span>
		  						   <span class="index" id="index2" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  			<div class="col-md-3 gs_infos" id="clothes3">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p3" id="shop_gs_p3" href="javascript:void(0)" title="查看详细信息">
			  				    <span id="p_id3" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p3"></span></div>
		  					<div class="col-md-5"><span id="n3"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id3" style="display:none;"></span>
		  						   <span class="index" id="index3" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  			<div class="col-md-3 gs_infos" id="clothes4">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p4" id="shop_gs_p4" href="javascript:void(0)" title="查看详细信息">
			  				    <span id="p_id4" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p4"></span></div>
		  					<div class="col-md-5"><span id="n4"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id4" style="display:none;"></span>
		  						   <span class="index" id="index4" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  		</div><!-- end row1 -->
		  		<div class="row row2">
		  			<div class="col-md-3 gs_infos"  id="clothes5">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p5" id="shop_gs_p5" href="javascript:void(0)"  title="查看详细信息">
			  				    <span id="p_id5" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p5"></span></div>
		  					<div class="col-md-5"><span id="n5"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id5" style="display:none;"></span>
		  						   <span class="index" id="index5" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  			<div class="col-md-3 gs_infos" id="clothes6">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p6" id="shop_gs_p6" href="javascript:void(0)" title="查看详细信息">
			  				    <span id="p_id6" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p6"></span></div>
		  					<div class="col-md-5"><span id="n6"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id6" style="display:none;"></span>
		  						   <span class="index" id="index6" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  			<div class="col-md-3 gs_infos" id="clothes7">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p7" id="shop_gs_p7" href="javascript:void(0)" title="查看详细信息">
			  				    <span id="p_id7" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p7"></span></div>
		  					<div class="col-md-5"><span id="n7"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id7" style="display:none;"></span>
		  						   <span class="index" id="index7" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  			<div class="col-md-3 gs_infos" id="clothes8">
		  				<div>
			  				<a class="shop_gs_p shop_gs_p8" id="shop_gs_p8" href="javascript:void(0)" title="查看详细信息">
			  				    <span id="p_id8" style="display:none;"></span>
			  				</a>
		  				</div>
		  				<div class="row gs_price">
		  					<div class="col-md-4">￥<span id="p8"></span></div>
		  					<div class="col-md-5"><span id="n8"></span></div>
		  					<div class="col-md-3">
		  						<a href="javascript:void(0)" title="加入洗衣篮" class="shoppingcart">
		  						   <span class="cId" id="id8" style="display:none;"></span>
		  						   <span class="index" id="index8" style="display:none;"></span>
		  						</a>
		  					</div>
		  				</div>
		  			</div>
		  		</div><!-- end row2 -->
		  	</div> <!-- end shop_gs -->
		  	
		  	<!-- 分页导航 -->
			<div class="s_pages">
			    <div style="display:none;"><span id="pageNum"></span></div>
				<nav>
				  <ul class="pagination" id="pagination">
				    <li id="pre">
				      <a href="javascript:movePage(-1)" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <li id="next">
				      <a href="javascript:movePage(1)" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
			</div>
          </div><!-- 商品页结束 -->
          
          <!-- 评论页开始 -->
          <div role="tabpanel" class="tab-pane fade" id="shopCom">
            
            <!-- 店铺评论 -->
            <div class="s_comments" id="s_comments">
                <div class="row">
                    <div class="col-md-3"><strong>用户</strong></div>
                    <div class="col-md-6"><strong>评论</strong></div>
                    <div class="col-md-3"><strong>评论时间</strong></div>
                </div>
            </div>
            
            <!-- 分页导航 -->
            <div class="s_cPages" style="padding-left:43%">
                <div style="display:none;"><span id="cPageNum"></span></div>
                <nav>
                  <ul class="pagination" id="c_pagination">
                    <li id="cPre">
                      <a href="javascript:showComment(-1)" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                      </a>
                    </li>
                    <li id="cNext">
                      <a href="javascript:showComment(1)" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                      </a>
                    </li>
                  </ul>
                </nav>
            </div>
          </div><!-- 评论页结束-->
        </div>
      
      </div>          
  	</div><!-- end container -->
  	
    <!-- <div id="msg">已成功加入购物车！</div> -->
  	<jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>