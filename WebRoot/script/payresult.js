$(document).ready(function(){
//$("#rs").text()=="success"
	setTimeout(function(){
		var ordernum = $("#ordernum").text();
		if($("#rs").text()=="success"){
			$.post("user/UserManage!payOrder.action",{ordernum:ordernum,type:2},function(data){
				var ms = eval('data');
				if(ms.result=="success"){
					$("#shit").submit();
				}
				else{
					alert("支付信息保存失败！请将支付成功页面截图并及与向管理员取得联系！");
				}
			});
		}
    },5000);
});

