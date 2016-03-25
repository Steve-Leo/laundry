<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<script type="text/javascript">
	 function modify() {
		var oldpas = $("#old-pass").val();
		var newpas = $("#new-pass").val();
		var confirm = $("#confirm").val();
		if (newpas.length < 3) {
			alert("支付密码长度不能小于3位！");
			$('#new-pass').focus();
			return false;
		} else if (newpas == confirm) {
			$.post(
			"user/UserManage!modifyPayPassword.action",
			{
			"old_pas":oldpas,
			"new_pas":newpas,
			"confirm":confirm
			},
			callback,
			"xml"
		);
		function callback(data){
			//alert("修改成功！");
			$("#content").load("user/change_pay_pass.jsp");
		};
		} else {
			document.getElementById("msg").innerHTML = "<font color='red'>两次密码不相同</font>";
			
		}
		
	}
	$("#submit_info").click(modify);
	
</script>
<h3>修改支付密码</h3>
<div>
	<div class="form-horizontal col-sm-offset-2">
		<br> <br>
		<div class="form-group">
			<label for="old-pass" class="col-sm-2 control-label">
				旧密码 </label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="old-pass"
					placeholder="初始密码为123">
			</div>
		</div>
		<div class="form-group">
			<label for="new-pass" class="col-sm-2 control-label">
				新密码 </label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="new-pass"
					maxlength="18" placeholder="请输入新的密码，3-18位">
			</div>
		</div>
		<div class="form-group">
			<label for="text" class="col-sm-2 control-label"> 密码确认 </label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="confirm"
					placeholder="请确认密码"><span id="msg"></span>
			</div>
		</div>
		<%
			//String message = (String) request.getAttribute("message");
			String msg = (String)session.getAttribute("msg");
			session.removeAttribute("msg");
			if (msg != null) {
		%>
		<%=msg%>
		<%
			}
		%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
				<ul class="list-inline">
					<li><button id="submit_info" class="btn btn-success">
							提交</button>
					</li>
					<li><a href="javascript:void(0)">忘记密码？</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

