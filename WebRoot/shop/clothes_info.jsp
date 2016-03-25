<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ruanku.model.*"%>
<%@ page import="java.util.List"%>
<!-- <%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";%> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="script/clothes_info.js"></script>
<script> 
	//图片上传预览功能 修改衣服信息
   function PreviewImage1(imgFile) 
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
      document.getElementById("imgPreview1").innerHTML=""; 
      document.getElementById("imgPreview1").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
     } 
     else//FF 
     { 
      path = URL.createObjectURL(imgFile.files[0]);
      document.getElementById("imgPreview1").innerHTML = "<img width = '370px' height = '300px' class = 'img-rounded' src = \"" + path + "\"></img>"; 
     } 
    } 
   }
   //图片上传预览 添加衣服
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
      document.getElementById("imgPreview").innerHTML = "<img width = '370px' height = '370px' class = 'img-rounded' src = \"" + path + "\"></img>"; 
     } 
    } 
   }  
  </script> 
<script type="text/javascript">
function seltAll(){  
        var chckBoxSign = document.getElementById("ckb");       //ckb 全选/反选的选择框id  
        var chckBox = document.getElementsByName("chckBox");    //所有的选择框其那么都是chckBox  
        var num = chckBox.length;  
        if(chckBoxSign.checked){  
            for(var index =0 ; index<num ; index++){  
                chckBox[index].checked = true;  
                }  
        }else{  
            for(var index =0 ; index<num ; index++){  
                chckBox[index].checked = false;  
                }  
            }  
        }  
function deleSeltedRecords(){  
        var chckBox = document.getElementsByName("chckBox");  
        var num = chckBox.length;  
        var ids = "";  
        for(var index =0 ; index<num ; index++){  
            if(chckBox[index].checked){  
                ids += chckBox[index].value + ",";                
            }  
        }  
        if(ids!=""){  
            if(window.confirm("确定删除所选记录？")){  
                $.ajax( {  
                    type : "post",  
                    url : "shop/shop!delMultiClothes?ids=" + ids, //要自行删除的action  
                    dataType : 'json',
                    success:function (data) {  
                        alert("删除成功");  
                        //window.location.reload();
                        $("#content").load("shop/shop!clothesList.action");
                    },
                    error : function(data) {
                    alert("删除成功");  
                        //window.location.reload();
                        $("#content").load("shop/shop!clothesList.action");
                    }  
                });  
            }  
        }else{  
            alert("请选择要删除的记录");  
            }  
        }  

function modify(var1,var2,var3,var4,var5,var6,var7) {
	 	$("#id1").val(var1);
		$("#name1").val(var2);
		$("#type1").val(var3);
		var mode = "干洗";
		if(var4 == "2"){
			mode = "水洗";
		}
		$("#modify_washmode").html(mode+"&nbsp;<span class='caret'></span>");
		$("#washMode1").val(var4);
		$("#modifyPriceDry").val(var5);
		$("#comment1").val(var6);
		$("#old_pic").attr("src",var7);
	}

	$(document).ready(
			function() {
				$('#modifyCloth').submit(
						function() {
							if ($('#name1').val() == "") {
								alert("衣服名称不能为空！");
								$('#name1').focus();
								return false;
							}
							if (isNaN($('#modifyPriceDry').val())) {
								alert("洗衣价格必须为数字！");
								$('#modifyPriceDry').focus();
								return false;
							}
							
							if ($('#comment1').val() == "") {
								alert("商品描述不能为空！");
								$('#comment1').focus();
								return false;
							}

							//调用each方法，循环获取上传图片数
							var file = 0;
							var errFile = 0;
							$('input[id="pic1"]').each(
									function() {
										var fileName = $('input[id="pic1"]').val();
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
							/* if (file > 0) {
								alert("请您上传一张图片");
								return false;
							} */
							if (errFile > 0) {
								alert("请上传jpg,jpeg,png,bmp,gif格式的图片！");
								return false;
							}
						});

			});
	
	$(document).ready(
			function() {
				$('#createCloth').submit(
						function() {
							if ($('#clothesname').val() == "") {
								alert("衣服名称不能为空！");
								$('#clothesname').focus();
								return false;
							}
							if (isNaN($('#addPriceDry').val())) {
								alert("洗衣价格必须为数字！");
								$('#addPriceDry').focus();
								return false;
							}

							if ($('#comment').val() == "") {
								alert("商品描述不能为空！");
								$('#comment').focus();
								return false;
							}
							
							if ($('#priceDry').val() == ""){
								$('#priceDry').focus();
							}
							//调用each方法，循环获取上传图片数
							var file = 0;
							var errFile = 0;
							$('input[id="pic"]').each(
									function() {
										var fileName = $('input[id="pic"]').val();
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
			
			function deleteCloth(var1) {
		$.post(
			"shop/shop!deleteCloth.action?id="+var1,
			callback,
			"xml"
		);
		function callback(data){
			//
			//
			alert("删除成功！");
			//window.location.reload();
			$("#content").load("shop/shop!clothesList.action");
			
		};
		
	}
	
	$("#add_mode_dry").click(function(){
		$("#add_washmode").html("干洗&nbsp;<span class='caret'></span>");
		$("#washMode").val(1);
	});
		
	$("#add_mode_water").click(function(){
		$("#add_washmode").html("水洗&nbsp;<span class='caret'></span>");
		$("#washMode").val(2);
	});
	
	$("#modify_mode_dry").click(function(){
		$("#modify_washmode").html("干洗&nbsp;<span class='caret'></span>");
		$("#washMode1").val(1);
	});
		
	$("#modify_mode_water").click(function(){
		$("#modify_washmode").html("水洗&nbsp;<span class='caret'></span>");
		$("#washMode1").val(2);
	});
</script>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h3>修改</h3>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" method="post"
					id="modifyCloth" enctype="multipart/form-data"
					action="shop/shop!modifyCloth.action">
					<input type="text" name="id" style="display:none;" id="id1" /> <input
						type="text" name="washMode" style="display:none;" type="hidden"
						id="washMode1" value="1"/>
					<div class="form-group">
						<label for="clothesname" class="col-sm-3 control-label">商品名称:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="name1"
								name="clothesname" maxlength="18">
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-3 control-label"> 商品类型:</label>
						<div class="col-sm-8">
							<select class="form-control" id="type1" name="type">
								<option value="1">上衣</option>
								<option value="2">裤子</option>
								<option value="3">羽绒服</option>
								<option value="4">大衣</option>
								<option value="5">其他</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="wash_mode" class="col-sm-3 control-label">清洗方式:</label>
						<div class="col-sm-8">
							<div class="input-group">
								<div class="input-group-btn">
									<button id="modify_washmode" type="button"
										class="btn btn-default dropdown-toggle" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										干洗 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a id="modify_mode_dry" href="javascript:void(0)">干洗</a>
										</li>
										<li><a id="modify_mode_water" href="javascript:void(0)">水洗</a>
										</li>
									</ul>
								</div>
								<span class="input-group-addon">价格¥</span> <input type="text"
									id="modifyPriceDry" name="priceDry" class="form-control" maxlength="10">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-offset-4 control-label text-warning">注：选择一种方式后输入价格</label>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-3 control-label">
							商品描述:</label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="6" placeholder="商品描述"
								id="comment1" name="comment" maxlength="500" style="resize:none"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="picture" class="col-sm-3 control-label"> 商品图片:
						</label>
						<div class="col-sm-8">
							<div id="imgPreview1">
							<img id="old_pic" src="image/shortTr.jpg" class="img-rounded" style="width:370px;height:300px">
							</div>
							<input
								type="file" id="pic1" name="pic" onchange='PreviewImage1(this)'>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-success">确认修改</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="col-xs-2">
	<ul class="nav nav-pills nav-stacked">
		<li class="active" id="myclothes"><a href="javascript:void(0)">
				我的商品 </a>
		</li>
		<li id="add_clothes"><a href="javascript:void(0)"> 添加新商品 </a>
		</li>
	</ul>
</div>
<div class="col-xs-10" id="content">
	<!-- 显示我的商品 -->
	<div id="myclothes_show">
		<div class="checkbox col-sm-2">
			<label> <input type="checkbox" id="ckb" onclick="seltAll()">全选/取消</label>
		</div>
		<div class="col-sm-1">
			<button type="submit" class="btn  btn-danger btn-sm"
				onclick="deleSeltedRecords()">删除商品</button>
		</div>
		<table id="clothes_table" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th class="col-xs-1"></th>
					<!-- <th class="col-xs-1">编号</th> -->
					<th class="col-xs-1">商品名称</th>
					<th class="col-xs-1">类型</th>
					<th class="col-xs-1">图片</th>
					<th class="col-xs-2">洗衣方式/价格</th>
					<th class="col-xs-2">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${!empty clist}">
					<c:forEach var="cloth" items="${clist}">
						<c:if test="${cloth.del == 0}">
							<tr>

								<td>
									<div class="checkbox">
										<label> <input type="checkbox" name="chckBox"
											value="${cloth.id}" > </label>
									</div>
								</td>
								<!-- <th>${cloth.id}</th> -->
								<td>${cloth.name}</td>
								<td><c:choose>
										<c:when test="${cloth.type == 1}">上衣</c:when>
										<c:when test="${cloth.type == 2}">裤子</c:when>
										<c:when test="${cloth.type == 3}">羽绒服</c:when>
										<c:when test="${cloth.type == 4}">大衣</c:when>
										<c:when test="${cloth.type == 5}">其他</c:when>
									</c:choose>
								</td>

								<td><img src="${cloth.picture}" class="img-thumbnail"
									style="width:60px;height:60px" ></td>

								<td><c:choose>
										<c:when test="${cloth.mode == 1}">
							干洗/${cloth.price1}
						</c:when>
										<c:when test="${cloth.mode == 2}">
						水洗/${cloth.price1}
						</c:when>
										<%-- <c:when test="${cloth.mode == 3}">干洗/${cloth.price1}
						<p>水洗/${cloth.price2}</p>
										</c:when> --%>
									</c:choose>
								</td>
								<td><a href="javascript:void(0)"
									onclick="modify(${cloth.id},'${cloth.name}',${cloth.type},${cloth.mode},${cloth.price1},'${cloth.comment}','${cloth.picture}')"
									data-toggle="modal" data-target="#myModal1">修改</a> / <a
									href="javascript:void(0)" onclick="deleteCloth(${cloth.id})">删除</a>
								</td>

							</tr>
						</c:if>
					</c:forEach>
				</c:if>

			</tbody>
		</table>
	</div>

	<!-- 添加新商品 -->
	<div id="add_clothes_show">
		<h3>添加新商品</h3>
		<form class="form-horizontal col-xs-offset-2" method="post"
			id="createCloth" enctype="multipart/form-data"
			action="shop/shop!addCloth.action">
			<input type="text" name="washMode" style="display:none;"
				type="hidden" id="washMode" value="1"/>
			<div class="form-group">
				<label for="clothesname" class="col-sm-2 control-label">商品名称:</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="clothesname"
						name="clothesname" maxlength="18">
				</div>
			</div>
			<div class="form-group">
				<label for="type" class="col-sm-2 control-label"> 商品类型:</label>
				<div class="col-sm-8">
					<select class="form-control" id="type" name="type">
						<option value="1">上衣</option>
						<option value="2">裤子</option>
						<option value="3">羽绒服</option>
						<option value="4">大衣</option>
						<option value="5">其他</option>
					</select>
				</div>
			</div>
			<!-- <div class="form-group">
				<label for="price_dry" class="col-sm-2 control-label">干洗价格:</label>
				<div class="col-sm-3">
					<div class="input-group">
						<span class="input-group-addon">¥</span> <input type="text"
							class="form-control" id="priceDry" name="priceDry">
					</div>
				</div>
				<label for="price_water" class="col-sm-2 control-label">水洗价格:</label>
				<div class="col-sm-3">
					<div class="input-group">
						<span class="input-group-addon">¥</span> <input type="text"
							class="form-control" id="priceWater" name="priceWater">
					</div>
				</div>
			</div> -->
			<div class="form-group">
				<label for="wash_mode" class="col-sm-2 control-label">清洗方式:</label>
				<div class="col-sm-8">
					<div class="input-group">
						<div class="input-group-btn">
							<button id="add_washmode" type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								干洗 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a id="add_mode_dry" href="javascript:void(0)">干洗</a>
								</li>
								<li><a id="add_mode_water" href="javascript:void(0)">水洗</a>
								</li>
							</ul>
						</div>
						<span class="input-group-addon">价格¥</span>
						<input type="text" id="addPriceDry" name="priceDry" class="form-control" maxlength="10">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-8 control-label text-warning">注：选择一种方式后输入价格</label>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label">
					商品描述:</label>
				<div class="col-sm-8">
					<textarea class="form-control" rows="6" placeholder="商品描述"
						id="comment" name="comment" style="resize:none" maxlength="500"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="picture" class="col-sm-2 control-label"> 商品图片: </label>
				<div class="col-sm-8">
					<div id="imgPreview">
					<img src="image/shortTr.jpg" class="img-rounded" width="370" height="370">
					</div>
					<input type="file" id="pic" name="pic" onchange='PreviewImage(this)' >
				</div>
			</div>
			<br>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-8">
					<button type="submit" class="btn btn-success">确认添加</button>
				</div>
			</div>
		</form>
	</div>
</div>