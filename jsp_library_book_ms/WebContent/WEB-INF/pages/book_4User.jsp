<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
<link href="css/print.css" rel="stylesheet" type="text/css"  media="print" />
<link href="css/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
</head>
<body>
	<div class="pageTop">
		<div class="page">
			<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;-&nbsp;
				<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">查询馆藏</a>
				&nbsp;-</span>&nbsp;图书信息
		</div>
	</div>
	<div class="bookMain4User">
		<table cellpadding="0" cellspacing="0" border="1px">
			<tr>
				<td style="text-align: right;">图书名称：</td>
				<td>${book.bookName }</td>
			</tr>
			<tr>
				<td style="text-align: right;">作者：</td>
				<td>${book.author }</td>
			</tr>
			<tr>
				<td style="text-align: right;">所属类别：</td>
				<td>${book.type }</td>
				<td rowspan="4">
					<img src="img/book/${book.imgPath }" width="200px" height="200px"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">出版发行：</td>
				<td>${book.press }</td>
			</tr>
			<tr>
				<td style="text-align: right;">isbn：</td>
				<td>${book.isbn }</td>
				<td rowspan="4">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">编号：</td>
				<td>${book.bookId }</td>
			</tr>
			<tr>
				<td style="text-align: right;">出版时间：</td>
				<td>${book.pubTime }</td>
			</tr>
			<tr style="border-bottom: 1px solid black">
				<td style="text-align: right;">馆藏信息：</td>
				<td>馆藏：<font class="font_bold_green">${book.allQuantity }</font>
					&nbsp;可借：
					<c:choose>
						<c:when test="${book.aviQuantity > 0}">
							<font class="font_bold_green">${book.aviQuantity }</font>
						</c:when>
						<c:otherwise>
							<font class="font_bold_red">${book.aviQuantity }</font>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<c:forEach items="${bookBorrowList }" var="borrow" varStatus="status">
				<c:if test="${borrow.relTime == null}">
					<tr>
						<td style="text-align: right;"><font color="green" style="font-size: 20px;font: bold;">归还时间： </font></td>
						<td><font color="green" style="font-size: 20px;font: bold;">${borrow.retTime }</font></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</body>
</html>