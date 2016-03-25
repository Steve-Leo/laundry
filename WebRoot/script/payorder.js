$(document).ready(function(){

$("#block_yi").hide();

if (parseFloat($("#total").text()) > parseFloat($("#balance").text())) {
	$("#payWithMoney").attr("disabled", "disabled");
	$("#password").attr("disabled", "disabled");
	$("#hint").text("不足以完成此次支付，请选择其他支付方式！");
}

var inputcount = 0;

$("#payWithMoney").click(function() {
	inputcount += 1;
	if (inputcount > 3) {
		alert("密码输入错误次数过多，请稍后再试！");
		return;
	}
	
	var id = $("#id").text();
	var pass = $("#password").val();
	$.post("user/UserManage!payOrder.action", {
		orderId : id,
		old_pas : pass,
		type : 1
	}, function(data) {
		var ms = eval('data');
		if (ms.result == "success") {
			window.location = "user/paysuccess.jsp";
		} else if (ms.result == "wrongpass") {
			alert("密码有误，请重新输入！");
			$("#password").val("");
		} else {
			alert("操作失败");
		}
	});
});

$("#pay_by_balance").click(function(){
	$("#pay_by_yi").removeClass();
	$("#pay_by_balance").addClass("active");
	$("#block_balance").show();
	$("#block_yi").hide();
});

$("#pay_by_yi").click(function(){
	$("#pay_by_balance").removeClass();
	$("#pay_by_yi").addClass("active");
	$("#block_yi").show();
	$("#block_balance").hide();
});

});