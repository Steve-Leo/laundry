<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>

<h3>修改登录密码</h3>
<script>
    $(function(){
        $("#new-pass").blur(function(){
            var text = $("#new-pass").val();
            if (text.length<6 || text.length>18) {
				document.getElementById("warn_new").innerHTML = "<font color='red'>密码长度在6-18位之间。</font>";
			}else{
			    document.getElementById("warn_new").innerHTML = "";
			}
        });
        $("#confirm").blur(function(){
            var text = $("#new-pass").val();
            var confirm = $("#confirm").val();
            if (text!=confirm) {
                document.getElementById("msg").innerHTML = "<font color='red'>密码不一致，请确认密码。</font>";
            }else{
                document.getElementById("msg").innerHTML = "";
            }
        });
        $("#old-pass").blur(function(){
            var text = $("#old-pass").val();
            if (text.length==0) {
                document.getElementById("warn_psw").innerHTML = "<font color='red'>密码不能为空。</font>";
            }else{
                document.getElementById("warn_psw").innerHTML = "";
            }
        });
    });
	function verify() {
		var password = $("#old-pass").val();
        var text = $("#new-pass").val();
        var confirm = $("#confirm").val();
        if (password.length==0 || text!=confirm || text.length==0 ||confirm.length==0) {
			return;
		}
		$.post("user/UserManage!verifyUser.action",{password:password},function(result){
            if (result.verified == false) {
				document.getElementById("warn_psw").innerHTML = "<font color='red'>密码错误，请重新输入。</font>";
			    return;
			}else {
		        $.post("user/UserManage!modifyPassword.action",{password:confirm},function(result){
		            if (result.verified==true) {
		                alert("修改密码成功，请重新登录");
		                location.href="user/login.jsp";
		            }else{
		                alert("修改密码失败");
		            }
		        },"json");
			}
		},"json");
	}
</script>
<div>

	<!-- <form class="form-horizontal col-sm-offset-2" method="post"
		name="registerform" action="user/UserManage!modifyPassword.action"> -->
		<br> <br>
	<div class="form-horizontal">
		<div class="form-group">
			<label for="old-pass" class="col-sm-2 control-label"> 旧密码 </label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="old-pass" name="old_pas"
					placeholder="请输入之前的密码"><span id="warn_psw"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="new-pass" class="col-sm-2 control-label"> 新密码 </label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="new-pass"
					name="new_pas" maxlength="18" placeholder="请输入新的密码"><span id="warn_new"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="text" class="col-sm-2 control-label"> 密码确认 </label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="confirm"
					name="confirm" placeholder="请确认密码"><span id="msg"></span>
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
				<ul class="list-inline">
					<li><button type="button" class="btn btn-success"
							onclick="verify()" id="submit">提交</button></li>
					<li><a href="user/forget_pass.jsp">忘记密码？</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- </form> -->
</div>

