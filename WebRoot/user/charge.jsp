<%@ page language="java" import="java.util.*,com.ruanku.yeepay.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<script type="text/javascript">
$(document).ready(function(){

	$(".bt").click(function(){
		$("#money").val($(this).val());
	});
	
	
	$("#submit").click(function(){
		var money = $("#money").val();
		if(money == ""){
			alert("金额不能为空");
			return;
		}
		var re = /^[0-9]*$/;
		if (!re.test(money)){
			alert("充值金额应为整数");
			return;
		}
		if(parseInt(money) < 10 || parseInt(money) > 10000){
			alert("充值金额需在10元到10000元之间");
			return;
		}
		else{
			$("#submit_val").val($("#money").val());
			$("#rechargeform").submit();
		}
	});	
});

</script>

<h3>充值</h3>
<br>
<ul class="list-inline">
	<li>
		<h4>
			<strong> 当前账户余额: </strong>
		</h4>
	</li>
	<li>
		<h4>
			<strong><fmt:formatNumber type="number" value="${user.money} " pattern="0.00" maxFractionDigits="2"/>元</strong>
		</h4>
	</li>
</ul>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">请选择充值金额：（元）</h3>
	</div>
	<div class="panel-body">
		<div class="col-xs-4">
			<input class="btn bt" style="color:gray-light;" type="button" value=50>
			<input class="btn bt" style="color:gray-light;" type="button" value=100>
			<input class="btn bt" style="color:gray-light;" type="button" value=200>
			<input class="btn bt" style="color:gray-light;" type="button" value=500>
		</div>
		<div class="col-xs-3">
			<input id="money" type="text" class="form-control" placeholder="请选择充值金额">
		</div>
		<div class="col-xs-5">
			<h5 class="text-center">充值金额应为10到10000之间的整数</h5>
		</div>
	</div>
	<div class="panel-footer">
		<input id="submit" class="btn btn-success" type="button" value="立即充值"/>
	</div>
	<form id="rechargeform" action="user/recharge_to_pay.jsp" method="post" style="display:none">
		<input id="submit_val" name="total" type="text" class="form-control">
	</form>
</div>

