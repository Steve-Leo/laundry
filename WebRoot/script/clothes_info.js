$(document).ready(function(){

$("#add_clothes_show").hide();

$("#add_clothes").click(function(){
	$("#myclothes").removeClass();
	$("#add_clothes").addClass("active");
	$("#add_clothes_show").show();
	$("#myclothes_show").hide();
});

$("#myclothes").click(function(){
	$("#add_clothes").removeClass();
	$("#myclothes").addClass("active");
	$("#myclothes_show").show();
	$("#add_clothes_show").hide();
});

});