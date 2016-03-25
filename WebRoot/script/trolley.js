$(document).ready(function() {
	
	$(":checkbox").prop("checked", false);
	
	$("input#count").each(function() { 
		var num = parseInt($(this).val());
        var td = $(this).parent().parent().parent();
		td.next().text(parseFloat(td.prev().text()) * num);
    });
	
	$("input#choose_all").click(function() {
		if ($(this).is(":checked")) {
			$(":checkbox").prop("checked", true);
			$(".panel-default").removeClass("panel-default").addClass("panel-primary");
		} else {
			$(":checkbox").prop("checked", false);
			$(".panel-primary").removeClass("panel-primary").addClass("panel-default");
		}
		checkout();
	});

	$("button#minus").click(function() {
		var input = $(this).parent().next();
		var num = parseInt(input.val());
		if(num > 1){
			num = num - 1;
			input.val(num);
		}
		
		var td = $(this).parent().parent().parent().parent();
		td.next().text(parseFloat(td.prev().text()) * num);
		if($("#total_count").val()!="0"){
			checkout();
		}
	});

	$("button#plus").click(function() {
		var input = $(this).parent().prev();
		var num = parseInt(input.val());
		if(num < 999){
			num = num + 1;
			input.val(num);
		}
		
		var td = $(this).parent().parent().parent().parent();
		td.next().text(parseFloat(td.prev().text()) * num);
		if(!$("#total_count").text()=="0"){
			checkout();
		}
	});

	$("input#count").keypress(function(event) { 
        var keyCode = event.which; 
        if (keyCode >= 48 && keyCode <=57) {
        	return true;
        }
        else 
            return false; 
    }); 
	
	$("input#count").keyup(function(event) { 
		$(this).val($(this).val().replace(/[^\d]/g, ''));
		var num = parseInt($(this).val());
		if(num < 1 || $(this).val()=="")
			return;
        var td = $(this).parent().parent().parent();
        var total = parseFloat(td.prev().text()) * num;
		td.next().text(total);
		checkout();
    });
	
	$("input#count").blur(function(){
		var num = parseInt($(this).val());
        if(num < 1 || $(this).val()==""){
        	num = 1;
        	$(this).val(1);
        }
        var td = $(this).parent().parent().parent();
        var total = parseFloat(td.prev().text()) * num;
		td.next().text(total);
		checkout();
	}); 
	
	$("input#check_shop").click(function(){
		var panel = $(this).parent().parent().parent().parent();
		if($(this).is(":checked")){
			panel.removeClass("panel-default").addClass("panel-primary");
		}
		else{
			panel.removeClass("panel-primary").addClass("panel-default");
			$("input#choose_all").prop("checked", false);
		}
		checkout();
    });
	
	checkout = function(){
		var row = 0;
		var sum = 0.0;
		$('.table tr').find('td').each(function() {
			if ($(this).index() == "5") {
				var panel = $(this).parent().parent().parent().parent();
				if(panel.hasClass("panel-primary")){
					sum = sum + parseFloat($(this).text());
				}
			}
	    });
		
		$('.panel-primary').each(function() {
			row += 1;
	    });
		
		if(row != 1){
			$("#checkout").attr("disabled","disabled");
			$("#checkout").css("background","#eee");
			$("font").attr("color","black");
			$("#total_count").text(0);
			$("#sum").text(0);
		}
		else{
			row = 0;
			$('.panel-primary').each(function() {
				$(this).find('tr').each(function(){
					row += 1;
				});
		    });
			$("#total_count").text(row);
			$("#sum").text(sum);
			$("#checkout").removeAttr("disabled");
			$("#checkout").css("background","#337ab7");
			$("font").attr("color","white");
		}
	};
	
	$("#saveChoose").click(function(){
		var value = "";
		var val = "";
		$("td#id").each(function(){
			val = $(this).next().next().next().next().find("input").val();
			value += $(this).text() + ":" + val + ",";
		});
		$.post("user/UserManage!updateBasket.action",{message:value}, function(data){
			var ms = eval('data');
			if (ms.result == "success") {
				alert("保存成功！");
			} else if(ms.result == "changed"){
				alert("操作失败！洗衣篮信息已更改");
				location.reload();
			} else {
				alert("操作失败！");
			}
		});
	});
	
	$("a#deleteChoose").click(function(){
		var r=confirm("确认删除已选项吗？");
		if (r==true){
			var value = "";
			$(".panel-primary").each(function(){
				$(this).find("td#id").each(function(){
					value += $(this).text() + ",";
				});
			});
			if(value == ""){
				alert("无有效数据！");
				return;
			}
			$.post("user/UserManage!deleteBasket.action",{message:value}, function(data){
				var ms = eval('data');
				if (ms.result == "success") {
					alert("操作成功！");
					location.reload();
				} else {
					alert("操作失败！");
					location.reload();
				}
			});
	 	}
	});
	
	$("a#delete").click(function(){
		var r=confirm("确认删除已选项吗？");
		if (r==true){
			var value = "";
			$(this).parent().parent().find("td#id").each(function(){
				value += $(this).text() + ",";
			});
			if(value == ""){
				alert("无有效数据！");
				return;
			}
			$.post("user/UserManage!deleteBasket.action",{message:value}, function(data){
				var ms = eval('data');
				if (ms.result == "success") {
					alert("操作成功！");
					location.reload();
				} else {
					alert("操作失败！");
					location.reload();
				}
			});
	 	}
	});
	
	$("#checkout").click(function(){
		if($(this).attr("disabled") == "disabled"){
			return;
		}
		var ids = "";
		var values = "";
		$(".panel-primary").each(function(){
			ids += $(this).find("#shopId").text()+",";
		});
		$(".panel-primary").each(function(){
			$(this).find("td").each(function(){
				if($(this).index() == "0"){
					values += $(this).text() + ":";
				}else if($(this).index() == "4"){
					values += $(this).find("input").val() + ",";
				}
			});
		});
		$("#data_submit input").first().val(ids);
		$("#data_submit input").last().val(values);
		$("#data_submit").attr("action","user/UserManage!toOrderConfirm.action").submit();
	});
});