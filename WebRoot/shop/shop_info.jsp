<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	/* function modify() {
		var shopname = $("#shopname").val();
		var telephone = $("#telephone").val();
		var pic = $("#pic").val();
		var comment = $("#comment").val();
		$.post("shop/shop!modifyShop.action", {
			"shopname" : shopname,
			"telephone" : telephone,
			"pic" : pic,
			"comment" : comment
		}, callback, "xml");
		function callback(data) {
			alert("修改成功！");
		}
		;
	}
	$("#submit_info").click(modify); */
	
	$(document).ready(
			function() {
			//username password1 password2 
			//shopname telephone comment
			//s_province s_city s_country address
				$('#modifyShop').submit(
						function() {
						var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
						var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
							if ($('#shopname').val() == "") {
								alert("店铺名不能为空！");
								$('#shopname').focus();
								return false;
							}
							/*if ($('#telephone').val() == "") {
								alert("商铺电话不能为空！");
								$('#telephone').focus();
								return false;
							}*/
							//如果用户输入的值不同时满足手机号和座机号的正则
							if (!isMobile.test($('#telephone').val()) && !isPhone.test($('#telephone').val())) { 
								alert("请正确填写电话号码，例如:13415764179或0321-4816048"); //就弹出提示信息
								$('#telephone').focus();
								return false; //返回一个错误，不向下执行
							}	
							if ($('#comment').val().length < 20) {
								alert("详细描述不能少于20个字！");
								$('#comment').focus();
								return false;
							}
							//调用each方法，循环获取上传图片数
							var file = 0;
							var errFile = 0;
							$('input[type="file"]').each(
									function() {
										var fileName = $(this).val();
										if (fileName == "") {//判断是否上传了图片
											file += 1;
										} else {//判断上传图片格式是否正确，需要jpg,png,bmp和gif
											var index1 = fileName
													.lastIndexOf(".");
											var index2 = fileName.length;
											var ext = fileName.substring(
													index1, index2);//后缀名
											ext = ext.toLowerCase();
											if (ext != ".jpg" && ext != ".jpeg"
													&& ext != ".bmp"
													&& ext != ".gif"
													&& ext != ".png") {
												errFile += 1;
											}
										}
									});
							//如果未上传的图片数大于2，即3张都未上传
							if (file > 0) {
								alert("请您上传一张图片");
								return false;
							}
							if (errFile > 0) {
								alert("请上传jpg,jpeg,png,bmp,gif格式的图片！");
								return false;
							}
						});

			});
</script>
<script type="text/javascript">
//图片上传预览功能
   function PreviewImage(imgFile) 
   { 
    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
    if(!pattern.test(imgFile.value)) 
    { 
     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
     imgFile.focus(); 
    } 
    else 
    { 
     var path; 
     if(document.all)//IE 
     { 
      imgFile.select(); 
      path = document.selection.createRange().text; 
      document.getElementById("imgPreview").innerHTML=""; 
      document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
     } 
     else//FF 
     { 
      path = URL.createObjectURL(imgFile.files[0]);
      document.getElementById("imgPreview").innerHTML = "<img width = '320px' height = '200px' src = \"" + path + "\"></img>"; 
     } 
    } 
   } 
  </script> 
<div>
	<form class="form-horizontal"  method="post" name="modifyShop"
				action="shop/shop!modifyShop.action" enctype="multipart/form-data"
				id="modifyShop">
		<div class="container">
			<div class="col-xs-5">
				<div class="form-horizontal">
					<div class="form-group">
						<label for="shopname" class="col-sm-3 control-label">
							店铺名称: </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="shopname" name="shopname"
								placeholder="不能超过30字" maxlength="30" value="${shop.shopname}">
						</div>
					</div>
					<div class="form-group">
						<label for="telephone" class="col-sm-3 control-label">
							联系电话: </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="telephone" name="telephone"
								value="${shop.tel}" placeholder="输入座机（格式：区号-电话号）或手机号" maxlength="13">
						</div>
					</div>
					<div class="form-group">
						<label for="picture" class="col-sm-3 control-label"> 店铺图片:
						</label>
						<div class="col-sm-9">
							<div id="imgPreview">
							<img src="${shop.picture}" width="320" height="200">
							</div>
							<input
								type="file" id="pic" name="pic" onchange='PreviewImage(this)'>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label for="description" class="col-sm-3 control-label">
						详细描述:</label>
					<div class="col-sm-9">
						<textarea class="form-control" rows="14" id="comment" name="comment"
							placeholder="详细描述，至少20个字" maxlength="800" style="resize:none"></textarea>
						<script>
							document.getElementById("comment").value = "${shop.comment}";
						</script>
					</div>
				</div>
			</div>
		</div>
		<br> <br>
		<div class="container">
			<div class="col-sm-offset-4 col-sm-3">
				<button type="submit" id="submit_info"
					class="btn btn-primary btn-block">提交修改信息</button>
			</div>

		</div>
	</form>
</div>
