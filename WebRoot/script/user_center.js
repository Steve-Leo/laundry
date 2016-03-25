$(document).ready(function() {

	clearNavbar = function() {
		$("#modify_info").removeClass();
		$("#change_pass").removeClass();
		$("#change_pay_pass").removeClass();
		$("#charge").removeClass();
		$("#account_detail").removeClass();
	};

	$("#modify_info").click(function() {
		clearNavbar();
		$("#modify_info").addClass("active");
		$("#content").load("user/modify_info.jsp");
	});

	$("#change_pass").click(function() {
		clearNavbar();
		$("#change_pass").addClass("active");
		$("#content").load("user/change_pass.jsp");
	});

	$("#change_pay_pass").click(function() {
		clearNavbar();
		$("#change_pay_pass").addClass("active");
		$("#content").load("user/change_pay_pass.jsp");
	});

	$("#charge").click(function() {
		clearNavbar();
		$("#charge").addClass("active");
		$("#content").load("user/charge.jsp");
	});

	$("#account_detail").click(function() {
		clearNavbar();
		$("#account_detail").addClass("active");
		$("#content").load("user/UserManage!showTransactionRecord.action");
	});

});