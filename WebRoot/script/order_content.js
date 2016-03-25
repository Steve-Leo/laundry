$(document).ready(function(){

$("a#detail").click(function(){
	var content = $(this).parent().find("input").first().val();
	location.href = "user/orderDetail.jsp?orderId="+content;
});


$("a#cancel").click(function(){
	var r=confirm("确认取消该订单吗？");
	if (r==true){
		var id = $(this).next().val();
		$.post("user/UserManage!cancelOrder.action",{orderId:id},function(data){
			var ms = eval('data');
   			if(ms.result=="success"){
   				location.reload();
   				alert("操作已成功!");
   			}
   			else{
   				alert("操作失败");
   			}
		});
 	} 
});

$("a#cancelWithRefund").click(function(){
	var r=confirm("确认取消该订单吗？");
	if (r==true){
		var id = $(this).next().val();
		$.post("user/UserManage!cancelOrderWithRefund.action",{orderId:id},function(data){
			var ms = eval('data');
   			if(ms.result=="success"){
   				location.reload();
   				alert("操作已成功，订单费用已退款至您的账户!");
   			}
   			else if(ms.result=="changed"){
   				location.reload();
   				alert("操作失败，订单状态已更新");
   			}
   			else{
   				alert("操作失败");
   			}
		});
 	}
});

$("a#confirm").click(function(){
	var r=confirm("请确认您已收到清洗过的衣物，避免钱财两空！");
	if (r==true){
		var form = $(this).parent();
		var id = $(this).next().val();
		$.post("user/UserManage!confirmOrder.action",{orderId:id},function(data){
			var ms = eval('data');
   			if(ms.result=="success"){
   				form.attr("action","user/UserManage!toEvaluate.action").submit();
   			}else if(ms.result=="changed"){
   				location.reload();
   				alert("操作失败,订单信息已更改！");
   			}else{
   				alert("操作失败");
   			}
		});
 	}
});

$("a#evaluate").click(function(){
	var ms = 'evaluate';
	var id = $(this).next().val();
	var form = $(this).parent();
	$.post("user/UserManage!checkOrderOperationLegal.action",{orderId:id,message:ms},function(data){
		var ms = eval('data');
		if(ms.result=="success"){
			form.attr("action","user/UserManage!toEvaluate.action").submit();
		}
		else if(ms.result=="changed"){
			location.reload();
			alert("操作失败,订单信息已更改！");
		}else{
			alert("操作失败");
		}
	});
});

$("a#pay").click(function(){
	var ms = 'pay';
	var id = $(this).next().next().next().val();
	var form = $(this).parent();
	$.post("user/UserManage!checkOrderOperationLegal.action",{orderId:id,message:ms},function(data){
		var ms = eval('data');
		if(ms.result=="success"){
			form.attr("action","user/UserManage!toPayOrder.action").submit();
		}
		else if(ms.result=="changed"){
			location.reload();
			alert("操作失败,订单信息已更改！");
		}else{
			alert("操作失败");
		}
	});
});

$("a#pagetag").click(function(){
	var page = $(this).text();
	var type = $("#ordertype").text();
	if(type == ""){
		$("#content").load("user/UserManage!orderContent.action?page="+page);
	}else{
		$("#content").load("user/UserManage!orderContent.action?page="+page+"&type="+type);
	}
});

$("#prevpage").click(function(){
	var page = parseInt($("li.active").first().find("#pagetag").first().text());
	var type = $("#ordertype").text();
	if(page == 1){
		return;
	}
	page = page - 1;
	$("#content").load("user/UserManage!orderContent.action?page="+page+"&type="+type);
});

$("#nextpage").click(function(){
	var page = parseInt($("li.active").first().find("#pagetag").first().text());
	var type = $("#ordertype").text();
	var total = parseInt($("#total_amount").text());
	if(page * 10 >= total){
		return;
	}
	page = page + 1;
	$("#content").load("user/UserManage!orderContent.action?page="+page+"&type="+type);
});

});