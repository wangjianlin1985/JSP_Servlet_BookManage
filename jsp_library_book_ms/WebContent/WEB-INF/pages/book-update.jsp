<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.bookName }</title>
<link href="css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
<link href="css/print.css" rel="stylesheet" type="text/css"  media="print" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/side.js" type="text/javascript"></script>
<%@ include file="/commons/bookQueryCondition.jsp" %>
</head>
<body>
	<div class="form_book">
		<div class="pageTop">
			<div class="page">
				<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;-&nbsp;
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">图书管理</a>
					&nbsp;-</span>&nbsp;修改图书信息
			</div>
		</div>
		<div class="bookDisp">
			<div class="bookMain">
			<form action="bookServlet?method=update&pageNo=${param.pageNo }" method="post">
			<table cellpadding="0" cellspacing="0" border="1px">
				<tr>
					<td>编号：</td>
					<td><input type="text" name="bookId" value="${book.bookId }" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>图书名称：</td>
					<td><input type="text" name="bookName" value="${book.bookName }"/></td>
					<td rowspan="4">
						<img src="img/book/${book.imgPath }" width="150" height="150"/>
						<input type="hidden" name="imgPath" value="${book.imgPath }"/>
					</td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" name="author" value="${book.author }"/></td>
				</tr>
				<tr>
					<td>所属类别：</td>
					<td><input type="text" name="type" value="${book.type }"/></td>
				</tr>
				<tr>
					<td>出版发行：</td>
					<td><input type="text" name="press" value="${book.press }"/></td>
				</tr>
				<tr>
					<td>isbn:</td>
					<td><input type="text" name="isbn" value="${book.isbn }"/></td>
					<td><input type="button" value="封面"></td>
				</tr>
				<tr>
					<td>出版时间：</td>
					<td><input type="text" name="pubTime" value="${book.pubTime }"/></td>
				</tr>
				<tr>
					<td>馆藏信息：</td>
					<td>馆藏：<input type="text" size="1" name="allQuantity" value="${book.allQuantity }"/>&nbsp;
					可借：<input type="text" size="1" name="aviQuantity" value="${book.aviQuantity }"/></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<input type="submit" value="修改" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/>&nbsp;
					</td>
				</tr>
			</table>
			</form>
			</div>
		</div>
	</div>
</body>
</html>