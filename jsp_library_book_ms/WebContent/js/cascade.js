/**
 *  实现级联列表
 */
$("#college").ready(function(){
	var url = "userServlet";  
	var args = {"method":"getColleges","time":new Date()}
	$.post(url,args,function(data){
		var arr = data.college.split(',');
		var colleges;
		for(var i = 0;i<arr.length;i++)
			$("#college").append("<option>"+arr[i]+"</option>");
	},"JSON");
});
	
$("#college").change(function(){
	var url = "userServlet";
	var college = $("#college option:selected").text();
	var idVal = $.trim(this.name);
	var args = {"method":"getMajors","college":college,"time":new Date()}
	$.post(url,args,function(data){
		var arr = data.major.split(',');
		$("#major").html("");
		$("#major").append("<option>-请选择-</option>");
		for(var i = 0;i<arr.length;i++)
			$("#major").append("<option>"+arr[i]+"</option>");
	},"JSON");
});

$("#major").change(function(){
	var url = "userServlet";
	var major = $("#major option:selected").text();
	var idVal = $.trim(this.name);
	var args = {"method":"getClasses","major":major,"time":new Date()}
	$.post(url,args,function(data){
		var arr = data.classes.split(',');
		$("#classes").html("");
		$("#classes").append("<option>-请选择-</option>");
		for(var i = 0;i<arr.length;i++)
			$("#classes").append("<option>"+arr[i]+"</option>");
	},"JSON");
});