<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<h3>收货地址维护</h3>
<script type="text/javascript">
	function modify() {
		var name = $("#name").val();
		var address = $("#address").val();
		var telephone = $("#telephone").val();
		var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
		var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
		if (name == "") {
			alert("姓名不能为空！");
			$('#name').focus();
			return false;
		} else if (address == "") {
			alert("地址不能为空！");
			$('#address').focus();
			return false;
		} else if (!isMobile.test(telephone) && !isPhone.test(telephone)) { //如果用户输入的值不同时满足手机号和座机号的正则
			alert("请正确填写电话号码，例如:13415764179或0321-4816048"); //就弹出提示信息
			return false; //返回一个错误，不向下执行
		} else {
			$.post("user/UserManage!modifyUser.action", {
				"name" : name,
				"address" : address,
				"telephone" : telephone
			}, callback, "xml");
			function callback(data) {
				alert("提交成功！");

			}
			;
		}
	}
	$("#submit_info").click(modify);
</script>
<br>
<br>
<div class="form-horizontal col-xs-offset-2" id="modify">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">收货人</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="name" name="name" maxlength="18" value="${user.name}">
		</div>
	</div>
	<div class="form-group">
		<label for="address" class="col-sm-2 control-label">收货地址</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="address" name="address" maxlength="200" value="${user.address}">
		</div>
	</div>
	<div class="form-group">
		<label for="telephone" class="col-sm-2 control-label"> 电话 </label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="telephone"
				name="telephone" maxlength="13" value="${user.tel}">
		</div>
	</div>
	<%
		if (request.getAttribute("message") != null) {
			String message = (String) request.getAttribute("message");
	%>
	<%=message%>
	<%
		}
	%>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-6">
			<button id="submit_info"  class="btn btn-success">提交</button>
		</div>
	</div>
</div>


