$(document).ready(function(){
	
$("li #screen").click(function(){
	
	var data = $(this).attr("name");
	$("#content").load("shop/shop!queryShopStatistics.action?data="+data);

});

});