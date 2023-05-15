<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>头部</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<style type="text/css">
	html { overflow-y:hidden; }
</style>
</head>

<body>
	<!-- 头部 -->
	<div class="head">
		<div class="headL">
			<img class="headLogo" src="img/top.gif" height="100px"/>
		</div>
		<div class="headR">
			<c:choose>
				<c:when test="${! empty sessionScope.user}">
					<span style="color:#FFF">欢迎：${sessionScope.user.name }</span> |
					<a href="loginServlet?method=logOut" rel="external" style="color:#FFF" target="_parent">[退出]</a>
				</c:when>
				<c:otherwise>
					<span style="color:#FFF">欢迎您，游客！</span> |
					<a href="login.jsp" rel="external" style="color:#FFF" target="_parent">[登录]</a>
				</c:otherwise>
			</c:choose> 
		</div>
	</div>
</body>
</html>