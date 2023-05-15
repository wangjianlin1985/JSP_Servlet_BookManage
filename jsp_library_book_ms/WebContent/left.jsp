<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页左侧导航</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<head></head>

<body id="bg">
	<!-- 左边节点 -->
	<div class="container">

		<div class="leftsidebar_box">
			<a href="bookServlet?method=getBooks" target="main"><div class="line">
					<img src="img/coin01.png" />&nbsp;&nbsp;首页
				</div></a>
			<c:if test="${sessionScope.userIdentity eq 'sys' }">
			<dl class="system_log">
					<dt>
						<img class="icon1" src="img/coin10.png" /><img class="icon2"
							src="img/coin09.png" /> 用户管理<img class="icon3"
							src="img/coin19.png" /><img class="icon4"
							src="img/coin20.png" />
					</dt>
					<dd>
						<img class="coin11" src="img/coin111.png" /><img class="coin22"
							src="img/coin222.png" />
							<a class="cks" href="userServlet?method=getUsers" target="main">用户管理</a>
						<img class="icon5" src="img/coin21.png" />
					</dd>
					<dd>
						<img class="coin11" src="img/coin111.png" /><img class="coin22"
							src="img/coin222.png" />
							<a class="cks" href="userServlet?method=getAddUserPage" target="main">添加用户</a>
						<img class="icon5" src="img/coin21.png" />
					</dd>
			</dl>
			<dl class="system_log">
				<dt>
					<img class="icon1" src="img/coin03.png" /><img class="icon2"
						src="img/coin04.png" /> 图书管理<img class="icon3"
						src="img/coin19.png" /><img class="icon4"
						src="img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a class="cks" href="bookServlet?method=getBooks"
						target="main">图书管理</a><img class="icon5" src="img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a class="cks" href="bookServlet?method=getAddBookPage"
						target="main">添加图书</a><img class="icon5" src="img/coin21.png" />
				</dd>
			</dl>
			</c:if>
			<c:if test="${sessionScope.userIdentity eq 'user' || empty sessionScope.user}">
			<dl class="system_log">
				<dt>
					<img class="icon1" src="img/coin07.png" /><img class="icon2"
						src="img/coin08.png" /> 图书借还<img class="icon3"
						src="img/coin19.png" /><img class="icon4"
						src="img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a href="bookServlet?method=getBorrowPage" target="main"
						class="cks">图书借阅</a><img class="icon5" src="img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a href="bookServlet?method=getReturnPage" target="main"
						class="cks">图书归还</a><img class="icon5" src="img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a href="userServlet?method=getUser&userid=${sessionScope.user.id }" target="main"
						class="cks">借阅记录</a><img class="icon5" src="img/coin21.png" />
				</dd>
			</dl>
			</c:if>
			<c:if test="${sessionScope.userIdentity eq 'sys' }">
			<dl class="system_log">
				<dt>
					<img class="icon1" src="img/coin15.png" /><img class="icon2"
						src="img/coin16.png" />罚款缴纳<img class="icon3"
						src="img/coin19.png" /><img class="icon4"
						src="img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a class="cks" href="userServlet?method=getFinePage" target="main">罚款缴纳</a><img class="icon5"
						src="img/coin21.png" />
				</dd>
			</dl>
			</c:if>
			<c:if test="${not empty sessionScope.user }">
			<dl class="system_log">
				<dt>
					<img class="icon1" src="img/coinL1.png" /><img class="icon2"
						src="img/coinL2.png" /> 密码修改<img class="icon3"
						src="img/coin19.png" /><img class="icon4"
						src="img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a href="userServlet?method=getCgPwdPage"
						target="main" class="cks">密码修改</a><img class="icon5"
						src="img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="img/coin111.png" /><img class="coin22"
						src="img/coin222.png" /><a class="cks" href="loginServlet?method=logOut" target="_top">退出</a><img
						class="icon5" src="img/coin21.png" />
				</dd>
			</dl>
			</c:if>
		</div>
	</div>
</body>
</html>

