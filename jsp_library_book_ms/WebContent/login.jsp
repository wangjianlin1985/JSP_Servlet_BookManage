<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
</head>
<body>

	<!-- 登录body -->
	<div class="logDiv">
		<div class="logGet">
			<!-- 头部提示信息 -->
			<div class="loginForm">
			<div class="logD logDtip">
				<p class="p1">登录</p>
			</div>
			<!-- 登录表单 -->
			<form action="loginServlet?method=doLogin" method="post" >
			<div class="lgD">
				<img class="img1" src="img/logName.png" />
				<input type="text" name="id" autocomplete="new-password" placeholder="输入学号" />
			</div>
			<div class="lgD">
				<img class="img1" src="img/logPwd.png" /><input type="password" name="password"
					placeholder="输入用户密码" />
			</div>
			<div class="lgRadio">
				<input type="radio" name="identity" id="user" value="user" checked="checked"/><label for="user">学生</label>
				<input type="radio" name="identity" id="sys" value="sys"/><label for="sys">管理员</label>
				<font color="red">${requestScope.msg }</font>
			</div>
			<div class="logC">
				<input type="submit" value="登录"/>
			</div>
			<div class="logE">
				<a href="index.jsp">游客</a>
			</div>
			</form>
			</div>
		</div>
		<!-- 登录body  end -->

		<!-- 登录页面底部 -->
		<div class="logFoot">
		 
		</div>
		<!-- 登录页面底部end -->
   
	</div>
</body>
</html>