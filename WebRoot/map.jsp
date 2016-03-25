<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="image/favicon.ico" >
	<title>选择您所在的位置</title>
	<link rel="stylesheet" type="text/css" href="css/map.css">
	<meta name="keywords" content="java,javaweb,地图">
	<meta name="descripetion" content="地图定位">
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=68bbd621f7820fb647dbb1d56ee0fe61&plugin=AMap.Geocoder"></script>
	<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="script/area.js" type="text/javascript"></script>
	<script src="script/provincesdata.js" type="text/javascript"></script>
	<script src="script/cookie.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vPbz2rIyB6jlHRXpNmCyKpNU"></script>
	<style type="text/css">
	.myMap{font-size:20px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			if(cookie.get("lng")!=null&&cookie.get("lat")!=null){
				theLocationByLnLa();
			}
			else{
				map.setCenter(decodeURI(cookie.get("cityName")));
			}
			
			$("#s_county").change(function() {
				var cityName = $(this).children('option:selected').val();
	            var province = $("#s_province").val();
	            var city = $("#s_city").val();
	            var county = $("#s_county").val();
				theLocationByName(city,county);
				loadStores(province,city);
				
			});
		});
	</script>
</head>
<body>
	<div class="top">
		<div class="header">
			<!-- 导航条 -->
			<jsp:include page="navigation.jsp"></jsp:include>
		</div>
	</div>

	<div class="myMap">
		<div>
			<select style='width:120px;height:30px;font-size:18px;'	id="s_province" name="s_province">
			</select >&nbsp;&nbsp; 
			<select style='width:120px;height:30px;font-size:18px;' id="s_city" name="s_city">
			</select>&nbsp;&nbsp; 
			<select style='width:120px;height:30px;font-size:18px;' id="s_county" name="s_county"></select>
			<input type="text" id="suggestId" value="" placeholder="请输入您的具体位置" style="width:320px;height:30px;font-size:18px;margin-left:13px;" />
			
			<a href="user/toIndex.action"> <button type="button" class="btn btn-success" style='width:100px;font-size:16px;float: right;'>我已选好</button></a>
			<script type="text/javascript">
			_init_area();
			
			</script>
		</div>
		<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
		<div class="showMap" id="southeast"></div>
		<div style='font-size:10px;'>温馨提示：如果没有搜索到您的位置，可直接在地图中点击确认。</div>
	</div>
	
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
			map.centerAndZoom(new BMap.Point(cookie.get('lng'), cookie.get('lat')), 14);
			map.setDefaultCursor("crosshair");
			map.addEventListener("click",function(e){
				if(marker != null){
					map.removeOverlay(marker);
				}
				var pt = new BMap.Point(e.point.lng, e.point.lat);
				setCookies(pt);
				marker = new BMap.Marker(pt); // 创建标注
				map.addOverlay(marker);
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			});
		}
		function setMapEvent() {
			map.enableScrollWheelZoom();
			map.enableKeyboard();
			map.enableDragging();
			map.enableDoubleClickZoom();
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
		var shops;
		var myValue;
		var myIcon = new BMap.Icon("image/store.png", new BMap.Size(32,32));
		var myDate = new Date();
		var geoc = new BMap.Geocoder();
		var ac;
		createAC();
		initMap();
		function createAC() {
			var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "suggestId",
				 "location" : map
			});
			
			ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
				var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
			
				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
				G("searchResultPanel").innerHTML = str;
			});
			
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
				var _value = e.item.value;
				myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
			
				setPlace();
			});
		}
		
		function setPlace(){
			function myFun(){
				var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
				setCookies(pp);
				map.centerAndZoom(pp, 18);
				if(marker != null){
					map.removeOverlay(marker);
				}
				marker = new BMap.Marker(pp);
				map.addOverlay(marker);    //添加标注
				marker.setAnimation(BMAP_ANIMATION_BOUNCE);
			}
			var local = new BMap.LocalSearch(map, { //智能搜索
			  	onSearchComplete: myFun
			});
		
			local.search(myValue);
		}
		
		function theLocationByName(cityName,county){
			if(county != ""){
				map.centerAndZoom(county,11);      // 用城市名设置地图中心点
				geocoder(county);
				geocoder(county);
				/* var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint(county, function(point){
				alert(point.lng+","+point.lat);
					if (point) {
						if(marker != null){
							map.removeOverlay(marker);
						}
						marker = new BMap.Marker(point);
						map.addOverlay(marker);    //添加标注
						marker.setAnimation(BMAP_ANIMATION_BOUNCE);
						setCookies (point);
					}
					else{
						alert("您选择地址没有解析到结果!");
					}
				}); */
				geocoder(cityName,county);
			}
		}
		
		function theLocationByLnLa(){	
			map.clearOverlays();
			var new_point = new BMap.Point(cookie.get("lng"), cookie.get("lat"));
			marker = new BMap.Marker(new_point);
			
			marker.setAnimation(BMAP_ANIMATION_BOUNCE);
			map.addOverlay(marker); // 将标注添加到地图中
			map.panTo(new_point);
		}
		
		function addClickHandler(contentTemp,marker){
			marker.addEventListener("mouseover",function(e){
				openInfo(contentTemp,e);});
		}
		
		function openInfo(contentTemp,e){
			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			var content ='地址：'+ contentTemp.contentM;
			var opts = contentTemp.optsM;
			var sContent = 
				"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+opts.title+"</h4>" + 
				"<img style='float:right;margin:4px' id='imgDemo' src='"+opts.image+"' width='139' height='104' title='image'/>" + 
				"<p style='margin:0;line-height:2;font-size:14px;text-indent:2em'>"+opts.message+"<br>"+content+"</p>" +
				"<a class=button style='display: block;margin-top: 10px;margin-right: 0px;padding-top: 5px;"+
   			 	"margin-bottom: 0px;background: #ff6000; width:100px;height:30px;color:#fff;text-align:center;border-radius:3px;' href=shopdetail.jsp?id="+opts.shopid+">进入店铺</a>"+	
					"</div>";
			var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象 
			map.openInfoWindow(infoWindow,point); //开启信息窗口
		}
		
		function closeInfo(contentTemp,e){
			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			map.closeInfoWindow(point); //开启信息窗口
		}
		
		function loadStores(province,city){
			$.post("map/map!getShopByLocation",{province:province,city:city},
				function(result){
				   var shops = result.shopArray;
				   for(var i = 0; i < shops.length; i++){
				   		var store_pt = new BMap.Point(shops[i].longitude,shops[i].latitude);
				   		var store_mk = new BMap.Marker(store_pt,{icon:myIcon});
				   		map.addOverlay(store_mk);
				   		var opts = {
							  width : 200,     // 信息窗口宽度
							  height : 100,     // 信息窗口高度
							  title : shops[i].shopname, // 信息窗口标题
							  enableMessage :true,//设置允许信息窗发送短息
							  message :shops[i].comment,
							  image: shops[i].picture,
							  shopid: shops[i].id
						};
						
						var content = shops[i].address;
						var contentTemp = {
							optsM:opts,
							contentM:content
						};
						addClickHandler(contentTemp,store_mk);
					}
				},"json");
		}
		
		function G(id) {
			return document.getElementById(id);
		}
		function setCookies (point) {
			cookie.set('lng', point.lng);
			cookie.set('lat', point.lat);
			cookie.set('lltime',myDate.getTime());
			geoc.getLocation(point, function(rs){
				var addComp = rs.addressComponents;
				loadStores(addComp.province,addComp.city);
	//			alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
				cookie.set('address', encodeURI(addComp.province+ ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber, "utf-8"), {expires : 30}); 
				document.getElementById("address").innerHTML = decodeURI(cookie.get("address"));
			});
		}
		
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
				setCookies(pt);
				marker = new BMap.Marker(pt); // 创建标注
				map.addOverlay(marker);
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            	}
        	});
    	}
	</script>
</body>
</html>