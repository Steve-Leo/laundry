$(document).ready(function(){
	
$("li #screen").click(function(){
	var data = $(this).attr("name");
	$("#content").load("shop/shop!shopOrderList.action?type="+data);
});

$("#searchOrder").click(function(){
	var content = $("#searchOrderText").val();
	$("#content").load("shop/shop!singleShopOrder.action?message="+content);
});

$("a#pagetag").click(function(){
	var page = $(this).text();
	var type = $("#ordertype").text();
	if(type == ""){
		$("#content").load("shop/shop!shopOrderList.action?page="+page);
	}else{
		$("#content").load("shop/shop!shopOrderList.action?page="+page+"&type="+type);
	}
});

$("#prevpage").click(function(){
	var page = parseInt($("li.active").last().find("#pagetag").first().text());
	var type = $("#ordertype").text();
	if(page == 1){
		return;
	}
	page = page - 1;
	$("#content").load("shop/shop!shopOrderList.action?page="+page+"&type="+type);
});

$("#nextpage").click(function(){
	var page = parseInt($("li.active").last().find("#pagetag").first().text());
	var type = $("#ordertype").text();
	var total = parseInt($("#total_amount").text());
	if(page * 10 >= total){
		return;
	}
	page = page + 1;
	$("#content").load("shop/shop!shopOrderList.action?page="+page+"&type="+type);
});

});