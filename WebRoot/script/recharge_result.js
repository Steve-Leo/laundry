$(document).ready(function(){

	if($("#rs").text()=="success"){
		setTimeout(updateInfo,5000); 
	}
	
	function updateInfo(){
		var total = $("#total").text();
		$.post("user/UserManage!recharge.action",{money:total},function(data){
			var ms = eval('data');
   			if(ms.result=="success"){
   				$("#shit").submit();
   			}
   			else{
   				alert("支付信息保存失败！请将支付成功页面截图并及与向管理员取得联系！");
   			}
		});
	}
});