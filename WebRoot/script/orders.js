$(document).ready(function(){

$("#content").load("user/UserManage!orderContent.action");
	
$("li #screen").click(function(){
	var data = $(this).attr("name");
	$("#content").load("user/UserManage!orderContent.action?type="+data);
});

$("#search").click(function(){
	var content = $("#search_content").val();
	$("#content").load("user/UserManage!findOrder.action?ordernum="+content);
});

});