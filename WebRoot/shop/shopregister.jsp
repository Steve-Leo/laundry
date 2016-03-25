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

<title>新店加盟</title>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vPbz2rIyB6jlHRXpNmCyKpNU"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=68bbd621f7820fb647dbb1d56ee0fe61&plugin=AMap.Geocoder"></script>
<script src="script/area.js" type="text/javascript"></script>
<style type="text/css">
	.myMap{
		width:900px;
		background:#fff;
		margin:30px auto;
		border-radius:5px;box-shadow: 2px 2px 15px #333;
		padding:10px 20px 20px 20px;
	} 
		
	.showMap {
		height: 300px;
		background: #cc66ff;
		/* margin-top: 30px; */
		border-radius: 5px;
		font-size: 14px;
	}
</style>

<script type="text/javascript">
$(document).ready(
			function() {
			//username password1 password2 
			//shopname telephone comment
			//s_province s_city s_country address
			var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
			var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
			$("#s_county").change(function() {
				var cityName = $(this).children('option:selected').val();
	            var province = $("#s_province").val();
	            var city = $("#s_city").val();
	            var county = $("#s_county").val();
				theLocationByName(city,county);
				
			});
				$('#shopRegister').submit(
						function() {
							if ($('#account').val().length < 3) {
								alert("登录名长度不能小于3个字符！");
								$('#account').focus();
								return false;
							}
							if ($('#password1').val().length < 6) {
								alert("密码长度不能小于6位！");
								$('#password1').focus();
								return false;
							}
							if ($('#password2').val() != $('#password1').val()) {
								alert("用户密码和确认密码不相同！");
								$('#password1').focus();
								$('#password2').focus();
								return false;
							}
							if ($('#shopname').val() == "") {
								alert("店铺名不能为空！");
								$('#shopname').focus();
								return false;
							}
							/*if ($('#telephone').val() == "") {
								alert("商铺电话不能为空！");
								$('#telephone').focus();
								return false;
							}*/
							//如果用户输入的值不同时满足手机号和座机号的正则
							if (!isMobile.test($('#telephone').val()) && !isPhone.test($('#telephone').val())) { 
								alert("请正确填写电话号码，例如:13415764179或0321-4816048"); //就弹出提示信息
								$('#telephone').focus();
								return false; //返回一个错误，不向下执行
							}
							if ($('#comment').val().length < 20) {
								alert("详细描述不能少于20个字！");
								$('#comment').focus();
								return false;
							}							
							if ($('#address').val()=="") {
								alert("详细地址不能为空！");
								$('#address').focus();
								return false;
							}
							
							if ($('#longitude').val()== 0) {
								alert("请点击地图上店铺的位置！");
								$('#longitude').focus();
								return false;
							}
							
							if ($('#s_county').val()== "地区、县") {
								alert("请点击选择省市区！");
								$('#s_county').focus();
								return false;
							}
							
							//调用each方法，循环获取上传图片数
							var file = 0;
							var errFile = 0;
							$('input[type="file"]').each(
									function() {
										var fileName = $(this).val();
										if (fileName == "") {//判断是否上传了图片
											file += 1;
										} else {//判断上传图片格式是否正确，需要jpg,png,bmp和gif
											var index1 = fileName
													.lastIndexOf(".");
											var index2 = fileName.length;
											var ext = fileName.substring(
													index1, index2);//后缀名
											ext = ext.toLowerCase();
											if (ext != ".jpg" && ext != ".jpeg"
													&& ext != ".bmp"
													&& ext != ".gif"
													&& ext != ".png") {
												errFile += 1;
											}
										}
									});
							//如果未上传的图片数大于2，即3张都未上传
							if (file > 0) {
								alert("请您上传一张图片");
								return false;
							}
							if (errFile > 0) {
								alert("请上传jpg,jpeg,png,bmp,gif格式的图片！");
								return false;
							}
						});

			});

	</script>
	<script> 
	//图片上传预览功能
   function PreviewImage(imgFile) 
   { 
    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
    if(!pattern.test(imgFile.value)) 
    { 
     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
     imgFile.focus(); 
    } 
    else 
    { 
     var path; 
     if(document.all)//IE 
     { 
      imgFile.select(); 
      path = document.selection.createRange().text; 
      document.getElementById("imgPreview").innerHTML=""; 
      document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
     } 
     else//FF 
     { 
      path = URL.createObjectURL(imgFile.files[0]);
      document.getElementById("imgPreview").innerHTML = "<img width = '300px' height = '200px' src = \"" + path + "\"></img>"; 
     } 
    } 
   } 
  </script> 
</head>



<body style="padding-top:100px">
	<!-- 页头 -->
	<jsp:include page="../shopnavigation.jsp"></jsp:include>
	<div class="container">
		<div class="col-xs-6">
			<h3>新店加盟</h3>
			<%
							String message = (String) request.getAttribute("message");
							if (message != null) {
						%>
						<font color='#CE0000'><%=message%></font>
						<%
							}
						%>
			<br>
		</div>
		<div class="col-xs-6">
			<br>
			<h4>
				已有店铺？直接点击 
				<span style="color:blue;font-weight:bold">
				<a href="shop/shoplogin.jsp">登录我的店铺</a>
				</span>
			</h4>
		</div>
	</div>
	<form class="form-horizontal"  method="post" name="registerform"
				action="shop/shop!register.action" enctype="multipart/form-data"
				id="shopRegister">
	<div class="container">
		<div class="col-xs-5">
			<input type="text" name="longitude" style="display:none;"
						type="hidden" id="longitude" value="0"/>
			<input type="text" name="latitude" style="display:none;"
					type="hidden" id="latitude" value="0"/>
				<div class="form-group">
					<label for="account" class="col-sm-3 control-label"> 店铺用户名:
					</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="account" name="username" 
							maxlength="18" placeholder="3-18个字符">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-3 control-label">
						请设置密码: </label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="password1" name="password"
							placeholder="密码长度6-18位" maxlength="18">
					</div>
				</div>
				<div class="form-group">
					<label for="passagain" class="col-sm-3 control-label">
						请确认密码: </label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="password2" name="comfirm"
							placeholder="确认密码">
					</div>
				</div>
				<div class="form-group">
					<label for="picture" class="col-sm-3 control-label"> 店铺图片:
					</label>
					<div class="col-sm-9">
						<div id="imgPreview">
						<img src="image/laundry_x.jpg" width="300" height="200">
						</div>
						<input
							type="file" id="inputfile" name="pic" onchange='PreviewImage(this)' >
					</div>
				</div>
			
		</div>

		<div class="col-xs-5">
				<div class="form-group">
					<label for="shopname" class="col-sm-3 control-label"> 店铺名称: </label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="shopname" name="shopname"
							placeholder="不能超过30字" maxlength="30">
					</div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-3 control-label">
						联系电话: </label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="telephone"
							name="telephone" placeholder="输入座机（格式：区号-电话号）或手机号" maxlength="13">
					</div>
				</div>

				<div class="form-group">
					<label for="description" class="col-sm-3 control-label">
						详细描述:</label>
					<div class="col-sm-9">
						<textarea class="form-control" rows="12" placeholder="详细描述，至少20个字" id="comment" name="comment"
						maxlength="800" style="resize:none"></textarea>
					</div>
				</div>

			
		</div>
	</div>
	
	<div class="container">
		<div class="col-xs-9">
			
				<div class="form-group">
					<label for="name" class="col-sm-3 control-label"> 店铺位置: </label>
					<div class="col-sm-9">
						<div class="row_search_all">
							<!-- <input type="text" name="city" id="addr_searchbar"
								placeholder="输入城市" /> <span>&nbsp;市 -->
							<select style='width:120px;height:30px;font-size:18px;'	id="s_province" name="s_province">
							</select >&nbsp;&nbsp; 
							<select style='width:120px;height:30px;font-size:18px;' id="s_city" name="s_city">
							</select>&nbsp;&nbsp; 
							<select style='width:120px;height:30px;font-size:18px;' id="s_county" name="s_county"></select>
							<script type="text/javascript">
							_init_area();
										
							</script>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-3 control-label"> 详细地址:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="address" name="address"
							placeholder="详细地址" maxlength="100">
					</div>
				</div>
				<div class="myMap"> 
				<div style='font-size:10px;'>请在地图点上您的位置</div>
					<div class="showMap" id="southeast"></div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-4">
						<button type="submit" class="btn btn-primary btn-block">
							提交注册信息</button>
					</div>
				</div>
			
		</div>
	</div>
</form>

<!-- 页脚 -->
<jsp:include page="../foot.jsp"></jsp:include>
<script type="text/javascript">
		//创建和初始化地图函数：
		function initMap() {
			createMap();//创建地图
			
			setMapEvent();//设置地图事件
			addMapControl();//向地图添加控件
			addMapOverlay();//向地图添加覆盖物
			
		}
		function createMap() {
			map = new BMap.Map("southeast");
			map.setDefaultCursor("crosshair");
			map.centerAndZoom(new BMap.Point(120.751427,31.275758), 18);
			/* analyseLocation(); */
			
			map.addEventListener("click",function(e){
				$('#latitude').val(e.point.lat);
				$('#longitude').val(e.point.lng);
				/* marker.setPosition(new BMap.Point(e.point.lng, e.point.lat)); */
				if(marker != null){
					map.removeOverlay(marker);
				}
				lng = e.point.lng;
				lat = e.point.lat;
				var pt = new BMap.Point(e.point.lng, e.point.lat);
				marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
				map.addOverlay(marker);
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				marker.enableDragging();
				marker.addEventListener("dragend",function(e2){
					/* alert(e2.point.lng + "," + e2.point.lat); */
					lng = e2.point.lng;
					lat = e2.point.lat;
				});
			});
		}
		function setMapEvent() {
			map.enableScrollWheelZoom();
			map.enableKeyboard();
			map.enableDragging();
			map.enableDoubleClickZoom();
		}
		
		function addClickHandler(target, window) {
			target.addEventListener("click", function() {
				target.openInfoWindow(window);
			});
		}
		
		//向地图添加控件
		function addMapControl() {
			var scaleControl = new BMap.ScaleControl({
				anchor : BMAP_ANCHOR_BOTTOM_RIGHT
			});
			scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
			map.addControl(scaleControl);
			var navControl = new BMap.NavigationControl({
				anchor : BMAP_ANCHOR_TOP_LEFT,
				type : 1
			});
			map.addControl(navControl);
			var overviewControl = new BMap.OverviewMapControl({
				anchor : BMAP_ANCHOR_BOTTOM_LEFT,
				isOpen : false
			});
			map.addControl(overviewControl);
		}
		var map;
		var marker;
		var lng;
		var lat;
		
		var myIcon = new BMap.Icon("image/store.png", new BMap.Size(32,32));
		initMap();
		
		function theLocationByName(cityName,county){
			if(county != ""){
				map.centerAndZoom(county,11);      // 用城市名设置地图中心点
				geocoder(cityName,county);
			}
		}
		// 创建地址解析器实例
		function geocoder(cityName,county) {
        var geocoder = new AMap.Geocoder({
            								city: "000", //城市，默认：“全国”
            								radius: 1000 //范围，默认：500
       							});
        //地理编码,返回地理编码结果
        	var addStr = cityName+""+county;
        	geocoder.getLocation(addStr, function(status, result) {
            	if (status === 'complete' && result.info === 'OK') {
                	var geocode = result.geocodes;
                if(marker != null){
					map.removeOverlay(marker);
				}
				var pt = new BMap.Point(geocode[0].location.getLng(), geocode[0].location.getLat());
				marker = new BMap.Marker(pt,{icon:myIcon}); // 创建标注
				map.addOverlay(marker);
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            	}
        	});
    	}
		// 将地址解析结果显示在地图上,并调整地图视野
		
		
		
		
	</script>
</body>
</html>
