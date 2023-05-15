<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").submit(function(e){
			//密码是否有输入
			var oldPwd = $("#pwd1").val();
			if(oldPwd != ${user.password}){
				alert("请输入旧密码！");
				return false;
			}
			//新密码位数限制
			var newPwd = $("#pwd2").val();
			if(newPwd.length < 6 || newPwd.length > 12){
				$("#msg2").text("密码长度不规范");
				return false;
			}
			
			//新密码二次校验是否相等
			var newPwd = $("#pwd2").val();
			var newPwdChecked = $("#pwd3").val();
			if(newPwd != newPwdChecked){
				$("#msg3").text('两次密码不一致');	
				return false;
			}
			
			//必须输入验证码
			var checkCode = $("#checkCode").val();
			if(checkCode == ""){
				alert("请输入 验证码");
				return false;
			}
		});
		$("#pwd1").change(function(){
			var userInput = $(this).val();
			$("#pwd3").val("");
			$("#pwd2").val("");
			if(userInput == ${user.password}){
				$("#pwd1").parent().find(".imga").show();
			    $("#pwd1").parent().find(".imgb").hide();
				$("#msg1").text("");
				return;
			}
			$("#msg1").text('密码错误！');
			$("#pwd1").parent().find(".imgb").show();
		    $("#pwd1").parent().find(".imga").hide();
		});
		$("#pwd2").change(function(){
			var userInput = $("#pwd1").val();
			var newPwd = $("#pwd2").val();
			var flag = true;
			if(userInput.length == 0){
				$("#msg2").text('请输入旧密码！');
				flag = false;
			}
			if(userInput == ${user.password}){
				if(newPwd.length < 6){
					flag = false;
					$("#msg2").text("密码长度低于6位");
				}
				if(newPwd.length > 12){
					flag = false;
					$("#msg2").text("密码长度高于12位");
				}
				if(flag){					
					$("#msg2").text("");
					$("#pwd2").parent().find(".imga").show();
				    $("#pwd2").parent().find(".imgb").hide();
				}else{
					$("#pwd2").parent().find(".imgb").show();
				    $("#pwd2").parent().find(".imga").hide();
				}
				return;
			}
		});
		$("#pwd3").change(function(){
			var userInput = $("#pwd1").val();
			var newPwd = $("#pwd2").val();
			var newPwdChecked = $("#pwd3").val();
			var flag = true;
			if(userInput.length == 0){
				flag = false;
				$("#msg3").text('请输入旧密码！');
			}
			if(newPwd.length == 0){
				flag = false;
				$("#msg3").text('请输入新密码');
			}
			if(userInput == ${user.password}){
				if(newPwd != newPwdChecked){
					flag = false;
					$("#msg3").text('两次密码不一致');					
				}
			}
			if(flag){					
				$("#msg3").text("");
				$("#pwd3").parent().find(".imga").show();
			    $("#pwd3").parent().find(".imgb").hide();
			}else{
				$("#pwd3").parent().find(".imgb").show();
			    $("#pwd3").parent().find(".imga").hide();
			}
			return;
		});
		$("#reset").click(function(){
			$("#pwd1").val("");
			$("#pwd2").val("");
			$("#pwd3").val("");
			$("#pwd1").parent().find(".imgb").hide();
		    $("#pwd1").parent().find(".imga").hide();
		    $("#pwd2").parent().find(".imgb").hide();
		    $("#pwd2").parent().find(".imga").hide();
		    $("#pwd3").parent().find(".imgb").hide();
		    $("#pwd3").parent().find(".imga").hide();
		    $("#msg3").text("");	
		    $("#msg2").text("");	
		    $("#msg1").text("");	
		});
		$(".codeChange").click(function(){
			var timeStamp = new Date().getTime();
			$(".code").attr("src","codeServlet?"+timeStamp);
			return false;
		});
	});
</script>
</head>
<body>
	<div id="pageAll">
		<div class="pageTop">
			<div class="page">
				<img src="img/coin02.png" /><span><a href="#">首页</a>&nbsp;-&nbsp;<a
					href="bookServlet?method=getBooks">查询馆藏</a>&nbsp;-</span>&nbsp;修改密码
			</div>
		</div>
		<div class="page ">
			<!-- 修改密码页面样式 -->
			<form action="userServlet?method=changePwd&userid=${user.id }" method="post">
			<div class="bacen">
				<div class="bbD">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${sessionScope.userIdentity eq 'user'}">读者</c:if>
					<c:if test="${sessionScope.userIdentity eq 'sys'}">管理员</c:if>UID：&nbsp;&nbsp;&nbsp;&nbsp;${user.id }</div>
				<div class="bbD">
					<c:if test="${sessionScope.userIdentity eq 'user'}">读者</c:if>
					<c:if test="${sessionScope.userIdentity eq 'sys'}">管理员</c:if>用户名：&nbsp;&nbsp;&nbsp;&nbsp;${user.name }</div>
				<div class="bbD">
					&nbsp;&nbsp;&nbsp;&nbsp;输入旧密码：<input type="password" class="input3"
						id="pwd1" /> <img class="imga"
						src="img/ok.png" /><img class="imgb" src="img/no.png" /><font color="red" id = "msg1"></font>
				</div>
				<div class="bbD">
					&nbsp;&nbsp;&nbsp;&nbsp;输入新密码：<input type="password" class="input3"
						id="pwd2" name="newPwd"/> <img class="imga"
						src="img/ok.png" /><img class="imgb" src="img/no.png" /><font color="red" id = "msg2"></font>
				</div>
				<div class="bbD">
					再次确认密码：<input type="password" class="input3"
						id="pwd3" name="newPwdChecked"/> <img class="imga" src="img/ok.png" /><img
						class="imgb" src="img/no.png" /><font color="red" id = "msg3"></font>
				</div>
				<div class="bbD">
					验证码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="input3"
						id="checkCode" name="code" style="width: 80px;"/> <img class="imga" src="img/ok.png" /><img
						class="imgb" src="img/no.png" /><img border = 0 class="code" src="codeServlet"/>&nbsp;<font color="red">${codeMsg}</font>
						<a class="codeChange" href="">看不清，换一张</a>
				</div>
				<c:if test="${ empty requestScope.codeMsg and not empty param.newPwd}">
					<div style="margin: 15px 0 0 15px"><font color="green">请牢记您的新密码:${param.newPwd }</font></div>
				</c:if>
				<div class="bbD">
					<p class="bbDP">
						<input type="submit" value="提交" class="subPwd"/>
						<a class="btn_ok btn_no" id = "reset" href="#">取消</a>
					</p>
				</div>
			</div>
			</form>
			<!-- 修改密码页面样式end -->
		</div>
	</div>
</body>
</html>