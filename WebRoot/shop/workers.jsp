<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ruanku.model.Worker"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<script type="text/javascript">
$('#birthday').datebox({
	onSelect: function(date){
		var y = date.getFullYear();  //年
    	var m = date.getMonth() + 1;  //月
    	m = m < 10 ? '0' + m : m;  
    	var d = date.getDate();    //日
    	d = d < 10 ? ('0' + d) : d;
    	var datestr = y + '-' + m + '-' + d;
    	$('#birthday').datebox('setValue', datestr);
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		//alert(datestr);
	}
});
$('#birthday2').datebox({
	onSelect: function(date){
		var y = date.getFullYear();  //年
    	var m = date.getMonth() + 1;  //月
    	m = m < 10 ? '0' + m : m;  
    	var d = date.getDate();    //日
    	d = d < 10 ? ('0' + d) : d;
    	var datestr2 = y + '-' + m + '-' + d;
    	$('#birthday2').datebox('setValue', datestr2);
	}
});
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
                    url : "shop/shop!delMultiWorker?ids=" + ids, //要自行删除的action  
                    dataType : 'json',
                    success:function (data) {  
                        alert("删除成功");  
                        //window.location.reload();
                        $("#content").load("shop/shop!workerList.action");  
                    },
                    error : function(data) {  
                        //window.location.reload(); 
                         $("#content").load("shop/shop!workerList.action");  
                    }  
                });  
            }  
        }else{  
            alert("请选择要删除的记录");  
            }  
        }  

	 function deleteWorker(var1) {
		$.post(
			"shop/shop!delWorker.action?id="+var1,
			callback,
			"xml"
		);
		function callback(data){
			//
			
			alert("删除成功，刷新界面！");
			$("#content").load("shop/shop!workerList.action");  
			
			
		};
	}
	$("#submit_info").click(deleteWorker);
		
	function add() {
		var name = $("#name").val();
		var sex = $("#sex").val();
		//var birthday = $("#birthday").val();
		var birthday = $("#birthday").datebox('getValue');
		var telephone = $("#telephone").val();
		var origin = $("#origin").val();
		var reg = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
		var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
		var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
		if (name.length < 2) {
			alert("姓名长度不能少于2个字符！");
			$('#name').focus();
			return false;
		} 
		else if (!reg.test(birthday)) {
			alert("出生年月日输入格式不正确！");
			$('#birthday').focus();
			return false;
		}
		/*else if (!reg1.test(telephone)) {
			alert("电话输入有非法字符！");
			$('#telephone').focus();
			return false;
		}*/
		else if (!isMobile.test(telephone) && !isPhone.test(telephone)) { //如果用户输入的值不同时满足手机号和座机号的正则
			alert("请正确填写电话号码，例如:13415764179或0321-4816048"); //就弹出提示信息
			return false; //返回一个错误，不向下执行
			}
		$.post(
			"shop/shop!addworker.action",
			{
			"name":name,
			"sex":sex,
			"birthday":birthday,
			"telephone":telephone,
			"origin":origin
			},
			callback,
			"xml"
		);
		
		function callback(data){
			alert("增加成功，刷新界面！");
			$("#content").load("shop/shop!workerList.action");  
			
		};
	}
	 function modify(var1,var2,var3,var4,var5,var6) {
	 	$("#id2").val(var1);
		$("#name2").val(var2);
		$("#sex2").val(var3);
		//$("#birthday2").val(var4);
		$("#birthday2").datebox('setValue', var4);
		$("#telephone2").val(var5);
		$("#origin2").val(var6);
	}
	
	function modifyWorker() {
		var id = $("#id2").val();
		var name = $("#name2").val();
		var sex = $("#sex2").val();
		//var birthday = $("#birthday2").val();
		var birthday = $("#birthday2").datebox('getValue');
		var telephone = $("#telephone2").val();
		var origin = $("#origin2").val();
		var reg = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
		var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
		var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
		//$("#myModal2").modal("hide");
		if (name.length < 2) {
			alert("姓名长度不能少于2个字符！");
			$('#name').focus();
			return false;
		} 
		else if (!reg.test(birthday)) {
			alert("出生年月日输入格式不正确！");
			$('#birthday').focus();
			return false;
		}
		else if (!isMobile.test(telephone) && !isPhone.test(telephone)) { //如果用户输入的值不同时满足手机号和座机号的正则
			alert("请正确填写电话号码，例如:13415764179或0321-4816048"); //就弹出提示信息
			return false; //返回一个错误，不向下执行
			}
		$.post(
			"shop/shop!modifyWorker.action",
			{
			"id":id,
			"name":name,
			"sex":sex,
			"birthday":birthday,
			"telephone":telephone,
			"origin":origin
			},
			callback,
			"xml"
		);
		
		function callback(data){
			alert("修改成功，刷新界面！");
			//window.location.reload();
			$("#content").load("shop/shop!workerList.action");  
			
		};
	}
</script>




<div class="checkbox col-sm-2">
	<label> <input type="checkbox" id="ckb" onclick="seltAll()">全选/取消</label>
</div>
<div class="col-sm-1">
	<button type="submit" class="btn  btn-danger btn-sm" onclick="deleSeltedRecords()">-删除</button>
</div>
<div class="col-sm-1">
	<button type="submit" class="btn  btn-success btn-sm easyui-linkbutton"
	onclick="clear()"	data-toggle="modal" data-target="#myModal1" >+添加员工</button>
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">

					<h3>新增</h3>
				</div>
				<div class="modal-body">
					<div name="registerform">

						<table>
							<tr>
								<td><label>姓名：</label> <input type="text" name="name"
									id="name" maxlength="18" /></td>
							</tr>
							<tr>
								<td><label>性别：</label> <select name=sex id="sex">
										<option value="1">男</option>
										<option value="2">女</option>
								</select> <!-- <input type="text" name="sex" /> --></td>
							</tr>
							<tr>
								<td><label>生日：</label> <input type="text" id="birthday" class="easyui-datebox" required="required" placeholder="XXXX-XX-XX"
									name="birthday" /></td>
							</tr>
							<tr>
								<td><label>电话：</label> <input type="text" id="telephone"
									name="telephone" /></td>
							</tr>
							<tr>
								<td><label>籍贯：</label> <input type="text" id="origin"
									name="origin" maxlength="50"/></td>
							</tr>
							<tr>
								<td><label></label> <button type="button" class="btn btn-default" 
									onclick="add()" data-dismiss="modal">增加</button></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">

					<h3>修改</h3>
				</div>
				<div class="modal-body">
					<div name="registerform">

						<table>
							<tr>
								<td><label></label> <input type="text" name="id" style="display:none;"
									id="id2" /></td>
							</tr>
							<tr>
								<td><label>姓名：</label> <input type="text" name="name"
									id="name2" maxlength="18"/></td>
							</tr>
							<tr>
								<td><label>性别：</label> <select name=sex id="sex2">
										<option value="1">男</option>
										<option value="2">女</option>
								</select> <!-- <input type="text" name="sex" /> --></td>
							</tr>
							<tr>
								<td><label>生日：</label> <input type="text" id="birthday2" class="easyui-datebox" required="required" placeholder="XXXX-XX-XX"
									name="birthday" /></td>
							</tr>
							<tr>
								<td><label>电话：</label> <input type="text" id="telephone2"
									name="telephone" /></td>
							</tr>
							<tr>
								<td><label>籍贯：</label> <input type="text" id="origin2"
									name="origin" maxlength="50"/></td>
							</tr>
							<tr>
								<td><label></label> 
								<button type="button" class="btn btn-default" 
									onclick="modifyWorker()" data-dismiss="modal">修改
									</button></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <div id="dom_var_window"></div>-->
</div>
<table id="workers_table" class="table table-bordered table-striped">
	<thead>
		<tr>
			<th class="col-xs-1"></th>
			<!-- <th class="col-xs-1">编号</th> -->
			<th class="col-xs-1">姓名</th>
			<th class="col-xs-1">性别</th>
			<th class="col-xs-2">出生年月</th>
			<th class="col-xs-2">电话</th>
			<th class="col-xs-1">籍贯</th>
			<th class="col-xs-2">操作</th>
		</tr>
	</thead>



	<tbody>

		<c:if test="${!empty workerList}">
			<c:forEach var="worker" items="${workerList}">
				<tr>
					<td>
						<div class="checkbox">
							<label> <input type="checkbox" name="chckBox" value="${worker.id}"> </label>
						</div>
					</td>
					<!--<th>${worker.id}</th> -->
					<td>${worker.name}</td>
					<td><c:choose>
							<c:when test="${worker.sex == 1}">男</c:when>
							<c:when test="${worker.sex == 2}">女</c:when>
						</c:choose></td>
					<td>${worker.birthday}</td>
					<td>${worker.tel}</td>
					<td>${worker.origin}</td>
					<td><a href="javascript:void(0)"
						onclick="modify(${worker.id},'${worker.name}',${worker.sex},'${worker.birthday}','${worker.tel}','${worker.origin}')"
						 data-toggle="modal" data-target="#myModal2">修改</a>/
						<a href="javascript:void(0)" onclick="deleteWorker(${worker.id})">删除</a>
						<!--  <a href="shop/shop!delWorker.action?id=${worker.id}">删除</a>-->
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>