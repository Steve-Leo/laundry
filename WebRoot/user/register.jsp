<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="bootstrap-3.3.5-dist/css/bootstrap-nonresponsive.css">
<link rel="stylesheet" href="css/common.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta charset="utf-8">
<link href="css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js">
	
</script>
<script src="js/bootstrap.js">
	
</script>
<link href="css/bootstrap-nonresponsive.css" rel="stylesheet" />
<style type="text/css">
.container {
	width: 1100px;
}
</style>
<script type="text/javascript">
	
	function check() {
		var account = document.getElementById("account").value;
		var password = document.getElementById("password").value;
		var passagain = document.getElementById("passagain").value;
		var address = document.getElementById("address").value;
		var telephone = document.getElementById("telephone").value;
		var email = document.getElementById("email").value;
		var name = document.getElementById("name").value;
		var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
		var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
		if (account.length < 3) {
			alert("用户名长度不能少于3个字符！");
			$('#account').focus();
			return false;
		} else if (password.length < 6) {
			alert("密码长度不能小于6位！");
			$('#password').focus();
			return false;
		}
		else if (passagain !== password) {
			alert("两次密码不一致！");
			$('#passagain').focus();
			return false;
		}
		else if (address == "") {
			alert("地址不能为空！");
			$('#address').focus();
			return false;
		}
		else if(!isMobile.test(telephone) && !isPhone.test(telephone)){ //如果用户输入的值不同时满足手机号和座机号的正则
		    alert("请正确填写电话号码，例如:13415764179或0321-4816048");  //就弹出提示信息
		    return false;         //返回一个错误，不向下执行
		}
		else if (email == "") {
			alert("邮箱不能为空！");
			$('#email').focus();
			return false;
		}
		else if (name == "") {
			alert("姓名不能为空！");
			$('#name').focus();
			return false;
		}
		return true;
	}
</script>
</head>

<body style="padding-top:100px">
	<jsp:include page="../navigation.jsp"></jsp:include>
	
	
	<div class="container">
		<div class="col-xs-5">
			<h3>新用户注册</h3>
			<br>
			<form class="form-horizontal" method="post" name="registerform"
				action="user/register.action">
				<div class="form-group">
					<label for="account" class="col-sm-3 control-label">
						用户名： </label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="account"
							name="account" placeholder="3-18个字符" maxlength="18">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-3 control-label">
						输入密码： </label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="6-18位" maxlength="18">
					</div>
				</div>
				<div class="form-group">
					<label for="passagain" class="col-sm-3 control-label">
						确认密码： </label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="passagain"
							name="passagain" placeholder="确认密码">
					</div>
				</div>
				<div class="form-group">
					<label for="text" class="col-sm-3 control-label"> 地址： </label>
					<div class="col-sm-9">
						<input type="test" class="form-control" id="address"
							name="address" placeholder="请输入您的收货地址" maxlength="200">
					</div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-3 control-label">
						电话：</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="telephone"
							name="telephone" placeholder="输入座机（格式：区号-电话号）或手机号" maxlength="13">
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-3 control-label"> 邮箱 ：</label>
					<div class="col-sm-9">
						<input type="email" class="form-control" id="email" name="email"
							placeholder="请输入有效邮箱，登录前必须进行激活哦" maxlength="30">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-3 control-label"> 真实姓名： </label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="请输入真实姓名" maxlength="18">
					</div>
				</div>
				<%
					String message = (String) request.getAttribute("message");
					if (message != null) {
				%>
				<%=message%>
				<%
					}
				%>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-success" onclick="return check()">注册</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-xs-2">
			<div style="height:60px"></div>
			<img src="image/divider.png" class="center-block">
		</div>
		<div class="col-xs-5">
		<br><br><br>
			<h3>已有账号?</h3>
			<br>
			<p><a href="user/login.jsp">点此登录</a></p>
		
		</div>
	</div>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>

</html>
