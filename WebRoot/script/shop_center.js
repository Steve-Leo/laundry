$(document).ready(function() {

	clearNavbar = function() {
		$("#shop_info").removeClass();
		$("#clothes_info").removeClass();
		$("#order_shop").removeClass();
		$("#data").removeClass();
		$("#workers").removeClass();
	};

	$("#shop_info").click(function() {
		clearNavbar();
		$("#shop_info").addClass("active");
		$("#content").load("shop/shop_info.jsp");
	});

	$("#clothes_info").click(function() {
		clearNavbar();
		$("#clothes_info").addClass("active");
		$("#content").load("shop/shop!clothesList.action");
	});

	$("#order_shop").click(function() {
		clearNavbar();
		$("#order_shop").addClass("active");
		$("#content").load("shop/shop!shopOrderList.action");
	});

	$("#data").click(function() {
		clearNavbar();
		$("#data").addClass("active");
		$("#content").load("shop/shop!queryShopStatistics.action");
	});
	
	$("#workers").click(function() {
		clearNavbar();
		$("#workers").addClass("active");
		$("#content").load("shop/shop!workerList.action");
	});

});