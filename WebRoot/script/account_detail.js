$(document).ready(function(){

$("#consume-table").hide();

$("#nav-charge-table").click(function(){
	$("#nav-consume-table").removeClass();
	$("#nav-charge-table").addClass("active");
	$("#charge-table").show();
	$("#consume-table").hide();
});

$("#nav-consume-table").click(function(){
	$("#nav-charge-table").removeClass();
	$("#nav-consume-table").addClass("active");
	$("#consume-table").show();
	$("#charge-table").hide();
});

});