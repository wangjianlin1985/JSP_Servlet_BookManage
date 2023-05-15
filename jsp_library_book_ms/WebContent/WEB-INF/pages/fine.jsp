<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link href="css/base.css" rel="stylesheet" type="text/css"/>
	<script src="js/jquery-1.10.1.min.js"></script>
	<script src="js/side.js" type="text/javascript"></script>
	
	<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
	<![endif]-->
	<%@ include file="/commons/userQueryCondition.jsp" %>
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
					$("body").click(function(i){ 
						!$(i.target).parents(".select").first().is(s) ? _hide():"";
					});
				});
				$(document).ready(function(){
					var fineMoney = '${fineMoney}';
					if(fineMoney == "")
						return;
					if(fineMoney <= 0)
						alert("该用户无罚款需缴纳！");
				});
			})
			</script>
</head>
<body>
	<!-- Popup -->
	<div id="Popup">
		<!-- SubPopup -->
		<div id="SubPopup">
			<div class="form_boxC">
				<div class="pageTop">
					<div class="page">
						<img src="img/coin02.png" /><span><a href="index.jsp?" target="_parent">首页</a>&nbsp;-&nbsp;
							<a href="userServlet?method=getUsers&pageNo=${param.pageNo }">用户管理</a>
							&nbsp;-</span>&nbsp;缴纳罚款
					</div>
				</div>
				<div class="userDisp">
					<div class="userMain">
					<form action="userServlet?method=getUser&type=fine" method = "post">
						学号/工号：<input type="text" name="userid"/>
						<input type="submit" value="确定" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out">
					</form>
					<br/>
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
									<input name="" type="text" size="20" value="${requestScope.user.id }" readonly="readonly"/>
								</div>
							</td>
						</tr>
						<tr>
							<th>姓名 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<input name="" type="text" size="20" value="${requestScope.user.name }" readonly="readonly"/>
								</div>
							</td>
						</tr>
						<tr>
							<th>所属学院 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<input name="" type="text" size="20" value="${requestScope.user.college }" readonly="readonly"/>
								</div>
							</td>
						</tr>
						<tr>
							<th>专业 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<input name="" type="text" size="20" value="${requestScope.user.major }" readonly="readonly"/>
								</div>
							</td>
						</tr>
						<tr>
							<th>班级 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<input name="" type="text" size="20" value="${requestScope.user.classes }" readonly="readonly">
								</div>
							</td>
						</tr>
					</table>
					</div>
					<div class="userBro">
					<form action="bookServlet?method=fine&userid=${param.userid }" method="post">
						<table cellpadding="0" cellspacing="0">
						<thead>超期罚款 ${fineMoney } 元</thead>
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
						<c:forEach items="${fineList }" var="borrow" varStatus="status">
							<tr>
								<td>${status.index + 1 }</td>
								<td>${borrow.id }</td>
								<td>${borrow.bookId }</td>
								<td>${borrow.bookName }</td>
								<td>${borrow.borTime }</td>
								<td>${borrow.retTime }</td>
								<td>${borrow.relTime }</td>
								<td>
									<c:choose>
										<c:when test="${borrow.overFine > 0}">
											<font color="red">${borrow.overFine }</font>
										</c:when>
										<c:otherwise>
											${borrow.overFine }
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="8" style="text-align: center;">
								<input type="submit" value="缴纳" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out">
							</td>
						</tr>						
						</table>
						</form>						
						<font color="green">${msg }</font>
					</div>
				</div>
			</div>
			</div>
		</div>
	<!-- /Popup -->
	</body>
</html>