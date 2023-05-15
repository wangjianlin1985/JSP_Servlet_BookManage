<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>归还成功</title>
<link href="css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
<link href="css/print.css" rel="stylesheet" type="text/css"  media="print" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
</head>
<body>
	<div class="borrow_success">
		<div class="pageTop">
			<div class="page">
				<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;-&nbsp;
					<a href="book-return.jsp">图书归还</a>
					&nbsp;-</span>&nbsp;图书归还
			</div>
			<div class="borrowwd_book">
				<form action="bookServlet?method=clear&kind=return" method="post">
				<table  cellpadding="0" cellspacing="0" border="1px">
					<tr>
						<th colspan="7" style="border: none">本次归还图书</th>
					</tr>
					<tr>
						<td>序号</td>
						<td>图书编号</td>
						<td>图书名称</td>
						<td>作者</td>
						<td>isbn</td>
						<td>出版社</td>
						<td>超期罚款</td>
					</tr>
					<c:if test="${!empty sessionScope.BookCart.books }">
						<c:forEach items="${sessionScope.BookCart.items }" var="book" varStatus="status">
						<tr>
							<td>${status.index + 1 }</td>
							<td>${book.bookId }</td>
							<td>${book.bookName }</td>
							<td>${book.author }</td>
							<td>${book.isbn }</td>
							<td>${book.press }</td>
							<td>${overFine[book.bookId] }</td>
						</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td colspan="7" style="text-align: center;">
							<input type="submit" value="确定" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/>&nbsp;
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>