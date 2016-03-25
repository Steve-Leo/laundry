$(document).ready(function(){
	if (cookie.get("address") != null&&cookie.get("address") != ""){
		document.getElementById("address").innerHTML = decodeURI(cookie.get("address"));
	} else {
		loadLocation();
		var myCity = new BMap.LocalCity();
		myCity.get(myFun);
		}
	$(".s_shop").click(function(){
		var id = $(this).children("span").text();
		location.href="shopdetail.jsp?id="+id;
	});
});

/* 图片轮播开始 */
/*var oIco;
var oUl;
var aBtn;
var images;
var oUlImage;
var aLi;
var oBtnLeft;
var oBtnRight;
var iNow;

$(function(){    
	$("#dotlz li").click(function(){        
		oIco = document.getElementById("dotlz");
		oUl = oIco.getElementsByTagName("ul");
		aBtn = oUl[0].getElementsByTagName("li");
		var iIndex=$(this).index();
		move(iIndex-iNow);
	});
});
function moveLeft(){
	move(-1);
}
function moveRight(){
	move(1);
}

function getIndex(iNum){
	if(iNum>=0){
		return iNum%aLi.length;
	}else if (iNum<0) {
		return aLi.length+iNum;
	}
}

function setSide(i,flag){
	$("#no"+getIndex(i)+"").animate({width:"725px",height:"321px",top:"50px"});
	$("#no"+getIndex(i)+"").css("z-index","1");
	$("#no"+getIndex(i+2)+"").animate({width:"725px",height:"321px",top:"50px"});
	$("#no"+getIndex(i+2)+"").css("z-index","1");
	if(flag==true){
		$("#no"+getIndex(i)+"").animate({left:"0px"});
		$("#no"+getIndex(i+2)+"").animate({left:"476px"});
	}
	if(flag==false){
		$("#no"+getIndex(i)+"").animate({left:"476px"});
		$("#no"+getIndex(i+2)+"").animate({left:"0px"});
	}
}

function setCenter(i,flag){
	if (flag==true) {
		$("#no"+getIndex(i+1)+"").animate({width:"874px",height:"382px",top:"20px",left:"100px"});
		$("#no"+getIndex(i+1)+"").css("z-index","2");
		$("#no"+getIndex(i-1)+"").animate({width:"874px",height:"382px",top:"20px",left:"100px"});
		$("#no"+getIndex(i-1)+"").css("z-index","0");
	}
	if(flag==false){
		$("#no"+getIndex(i-1)+"").animate({width:"874px",height:"382px",top:"20px",left:"100px"});
		$("#no"+getIndex(i-1)+"").css("z-index","2");
		$("#no"+getIndex(i+1)+"").animate({width:"874px",height:"382px",top:"20px",left:"100px"});
		$("#no"+getIndex(i+1)+"").css("z-index","0");
	}
}

function move(step){
	images = document.getElementById("images");
	oUlImage = images.getElementsByTagName("ul");
	aLi = oUlImage[0].getElementsByTagName("li");
	oBtnLeft = document.getElementById("leftbut");
	oBtnRight = document.getElementById("rightbut");
	for(var j=0;j<aLi.length;j++){
		if($("#no"+getIndex(j)+"").css("z-index")==2){
			iNow=j;
			break;
		}
	}
	var i=iNow;
	if (step>0) {
		for(i;i<iNow+step;i++){
			setSide(i,true);
			setCenter(i,true);
		}
	}else if (step<0) {
		for(i;i>iNow+step;i--){
			setSide(i,false);
			setCenter(i,false);
		}
	}
	iNow = getIndex(i);
}*/
/* 图片轮播结束*/

$(function() {
	var address;
	var marker;
	var myCity;
	var map;
});

/*function searchShops() {
	var searchText = $("#sr_text").val();
	$.post("http://127.0.0.1:8080/laundry/user/toIndex.action?searchText="+searchText);
}*/

function loadLocation() {
	var map = new BMap.Map("allmap");
	//map.centerAndZoom(point,12);
	var point = new BMap.Point(116.331398,39.897445);
	
	var geolocation = new BMap.Geolocation();
	var geoc = new BMap.Geocoder();
	geolocation.getCurrentPosition(function(r){
		var pt = r.point;
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(pt);
			var myDate = new Date();
			
			if(cookie.get('lltime')==null||(cookie.get('lltime') - myDate.getTime()) < 21600000){

				cookie.set('lng', r.point.lng,{path:'/laundry'});
				cookie.set('lat', r.point.lat,{path:'/laundry'});
				cookie.set('lltime',myDate.getTime());
			}		
		}
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
//			alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
//			document.getElementById("address").innerHTML = addComp.province+ ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
			cookie.set('address', encodeURI(addComp.province+ ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber, "utf-8"), {expires : 30}); 
			document.getElementById("address").innerHTML = decodeURI(cookie.get("address"));
		}); 
	},{enableHighAccuracy: true});
}

function myFun(result){
	
	var cityName = result.name;
	cookie.set('cityName',encodeURI( result.name),{path:'/laundry'});
}


/*function checkCookie()
{
	//alert('您的位置ddddd：'+cookie.get("lng")+',   ' +cookie.get("lat")+', ' + decodeURI(cookie.get("cityName")));
}*/

