$(document).ready(function() {
	
	//初始化页面
	var name;
	var add;
	var phone;
	
	$('#default_table tr').find('td').each(function() {
		if ($(this).index() == "0") {
			name = $(this).text();
		}else if ($(this).index() == "1"){
			add = $(this).text();
		}else{
			phone = $(this).text();
		}
	});
	
	$('#input_name').val(name);
	$('#input_phone').val(phone);
	$('#input_address').val(add);
	$('#input_name').attr("disabled","disabled");
	$('#input_phone').attr("disabled","disabled");
	$('#input_address').attr("disabled","disabled");
	$('#name').text(name+" "+phone);
	$('#address').text(add);
	$(".account_block strong").text(getTotal());
	
	//功能函数
	$("#default_add").click(function() {
		$('#name').text(name+" "+phone);
		$('#address').text(add);
		
		$('#input_name').attr("disabled","disabled");
		$('#input_phone').attr("disabled","disabled");
		$('#input_address').attr("disabled","disabled");
	});
	
	$("#custom_add").click(function() {
		update();
		$('#input_name').removeAttr("disabled");
		$('#input_phone').removeAttr("disabled");
		$('#input_address').removeAttr("disabled");
	});
	
	$("input").keyup(function(event) {
		if($("#custom_add").is(':checked')){
			update();
		}
    });
	
	update = function(){
		$('#name').text($('#input_name').val() + " " + $('#input_phone').val());
		$('#address').text($('#input_address').val());
	};
	
	function getTotal(){
		var sum = 0.0;
		$('#order_items tr').find('td').each(function() {
			if ($(this).index() == "5") {
				sum = sum + parseFloat($(this).text());
			}
	    });
		return sum;
	};
	
	
	$("#checkout").click(function(){
		var address = $("#address").text();
		var name = $("#name").text();
		var values = "";
		var account = $(".account_block strong").text();
		var shopId = $("#shopId").text();
		var phone = "";
		
		var strs= new Array();
		strs = name.split(" ");
		name = strs[0];
		phone = strs[1];
		
		if(address=='' || address==null){
			alert("地址信息不能为空！");
			return;
		}
		if(name=='' || name==null){
			alert("用户信息不能为空！");
			return;
		}
		var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
		var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
		if(!isMobile.test(phone) && !isPhone.test(phone)){ //如果用户输入的值不同时满足手机号和座机号的正则
		    alert("请正确填写电话号码，例如:13415764179或0321-4816048");  //就弹出提示信息
		    return;         //返回一个错误，不向下执行
		}
		
		$('#order_items tr').find('td').each(function() {
			if($(this).index() == "0"){
				values += $(this).text()+":";
			}else if ($(this).index() == "4") {
				values += $(this).text()+",";
			}
	    });
		
		$.post("user/UserManage!orderConfirm.action",
			{
			address:address,
			name:name,
			message:values,
			orderId:shopId,
			telephone:phone,
			money:account
			},
			function(data)
			{
				var ms = eval('data');
				if(ms.result=="success"){
					$("#commit_value").val(ms.orderId);
					$("#commit_form").attr("action","user/UserManage!toPayOrder.action").submit();
				}
				else{
					alert("操作失败");
				}
			}
		);
	});
	
});