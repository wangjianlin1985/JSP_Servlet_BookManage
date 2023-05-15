<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title></title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="generator" content="" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
	<link href="css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
	<link href="css/print.css" rel="stylesheet" type="text/css"  media="print" />
	<link rel="stylesheet" type="text/css" href="css/css.css" />
	<script src="js/jquery-1.10.1.min.js"></script>
	<script src="js/side.js" type="text/javascript"></script>
	
	<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
	<![endif]-->
	<%@ include file="/commons/userQueryCondition.jsp" %>
</head>
<body>
	<!-- Popup -->
	<div id="Popup">
		<!-- SubPopup -->
		<div id="SubPopup">
			<script type="text/javascript">
			$(function(){
			$(".select").each(function(){
			var s=$(this);
			var z=parseInt(s.css("z-index"));
			var dt=$(this).children("dt");
			var dd=$(this).children("dd");
			var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};
			var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};
			dt.click(function(){dd.is(":hidden")?_show():_hide();});
			dd.find("a").click(function(){dt.html($(this).html());_hide();});
			$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});})})
			</script>
			<div class="form_boxC">
				<div class="pageTop">
					<div class="page">
						<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;
							<c:choose>
								<c:when test="${sessionScope.userIdentity eq 'sys' }">
									-&nbsp; <a href="userServlet?method=getUsers&pageNo=${param.pageNo }">用户管理</a>
								</c:when>
								<c:otherwise>
									- <a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">查询馆藏</a>
								</c:otherwise>
							</c:choose>
							&nbsp;-</span>&nbsp;用户信息
					</div>
				</div>
				<div class="userMain">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<th width="100">相片 :</th>
						<td>
							<div style="text-align: center;">
								<c:set var="imgPath" scope="request" value="${requestScope.user.imgPath }"/>
								<c:if test="${ empty imgPath }">
									<c:set var="imgPath" scope="page" value="default.jpg"/>
								</c:if>
								<img src="img/user/${imgPath }" width="150" heigth="200"/>
							</div>
						</td>
					</tr>
					<tr>
						<th width="100">学号/工号 :</th>
						<td>
							<div class="txtbox floatL" style="width:215px;">
								<input name="" type="text" size="22" value="${user.id }" readonly="readonly"/>
							</div>
						</td>
					</tr>
					<tr>
						<th>姓名 :</th>
						<td>
							<div class="txtbox floatL" style="width:215px;">
								<input name="" type="text" size="22" value="${user.name }" readonly="readonly"/>
							</div>
						</td>
					</tr>
					<tr>
						<th>所属学院 :</th>
						<td>
							<div class="txtbox floatL" style="width:215px;">
								<input name="" type="text" size="22" value="${user.college }" readonly="readonly"/>
							</div>
						</td>
					</tr>
					<tr>
						<th>专业 :</th>
						<td>
							<div class="txtbox floatL" style="width:215px;">
								<input name="" type="text" size="22" value="${user.major }" readonly="readonly"/>
							</div>
						</td>
					</tr>
					<tr>
						<th>班级 :</th>
						<td>
							<div class="txtbox floatL" style="width:215px;">
								<input name="" type="text" size="22" value="${user.classes }" readonly="readonly">
							</div>
						</td>
					</tr>
				</table>
				</div>
				<div class="userBro">
					<table cellpadding="0" cellspacing="0">
					<thead><font size="10">借阅记录-借阅次数: ${fn:length(borrowList) } 次</font></thead>
					<tr>
						<th>序号</th>
						<th>记录编号</th>
						<th>图书编号</th>
						<th>书名</th>
						<th>借书时间</th>
						<th>应还时间</th>
						<th>实还时间</th>
						<th>超期罚款</th>
					</tr>
					<c:forEach items="${borrowList }" var="borrow" varStatus="status">
						<%-- 设置隔行变色 --%>
							<c:set var="rows" scope="page" value="${status.index + 1 }"/>
							<c:choose>
								<c:when  test="${rows % 2 == 0 }">
									<c:set var="className" value="bgcB"/>
								</c:when>
								<c:otherwise>
									<c:set var="className" value="none"/>
								</c:otherwise>
							</c:choose>
							<tr class="${className }">
							<td>${status.index + 1 }</td>
							<td>${borrow.id }</td>
							<td>${borrow.bookId }</td>
							<td class="bookName">${borrow.bookName }</td>
							<td class="date">${borrow.borTime }</td>
							<td class="date">${borrow.retTime }</td>
							<td class="date">${borrow.relTime }</td>
							<td>
								<c:choose>
									<c:when test="${borrow.overFine > 0}">
										<font color="red">${borrow.overFine }</font>
									</c:when>
									<c:when test="${borrow.overFine < 0}">
										<font color="green">${-borrow.overFine }元，已缴纳</font>
									</c:when>
									<c:otherwise>
										${borrow.overFine }
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
			</div>
		</div>
	<!-- /Popup -->
	</body>
</html>