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
<link href="css/base.css" rel="stylesheet" type="text/css"  media="print" />
<!-- 下拉框样式 -->
<link rel="stylesheet" type="text/css" href="css/selectFilter.css" />
<!-- 下拉框jq -->
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/side.js" type="text/javascript"></script>
</script>
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<%@ include file="/commons/bookQueryCondition.jsp" %>
</head>
<script type="text/javascript">
	$(function(){
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			//1.校验 val 是否为数字
			var reg = /^\d+$/g;
			var flag = false;
			if(reg.test(val)){
				var pageNo = parseInt(val);
				if(val >= 1 && val <= parseInt("${bookPage.totalPageNumber}")){
					flag = true;
				}
			}
			
			if(!flag){
				alert('输入的不是合法的页码！');
				$(this).val("");
				return;
			}
			//2.校验 val 是否在一个合法范围内，即1~totalPageNumber
			
			//3.页面跳转
			var href = "bookServlet?method=getBooks&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
	})
</script>
<body>
	<!-- MainForm -->
	<div id="MainForm">
		<div>
			<h2>图书信息管理</h2>
			<div class="queryBookForm">
			<form action="bookServlet?method=getBooks" method="post">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<select name="searchWay">
							<option value="bookName">图书名称</option>
							<option value="bookId">图书编号</option>
							<option value="isbn">ISBN</option>
							<option value="type">图书类型</option>
						</select>
					</td>
					<td>
						<input type="text" name="searchValue"/>
					</td>
					<td>
						<input type="submit" value="检索" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/>
					</td>
				</tr>
			</table>
			</form>
			</div>
			<div class="form_books">
			<table cellpadding="0" cellspacing="0">
				<c:forEach items="${bookPage.list }" var="book" varStatus="status">
					<tr>
						<th>${status.index + 1 }</td>
						<th>
						<font size="4">
							<a href="bookServlet?method=getBook&pageNo=${bookPage.pageNo}&bookId=${book.bookId}">${book.bookName }</a>
						</font>
						</th>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td rowspan="4" style="border-bottom:2px dotted #ccc">
							<a href="bookServlet?method=getBook&pageNo=${bookPage.pageNo}&bookId=${book.bookId}">
								<img src="img/book/${book.imgPath }" width="150" height="150"/>
							</a>
						</td>
						<td>编号：${book.bookId }</td>
						<td>作者：${book.author }</td>
						<c:choose>
							<c:when test="${sessionScope.userIdentity eq 'sys'}">
								<td rowspan="4" style="border-bottom:2px dotted #ccc"><a href="bookServlet?method=getBook&opType=update&pageNo=${bookPage.pageNo }&bookId=${book.bookId}" target="_parent"">修改</a></td>
							</c:when>
							<c:otherwise>
								<td rowspan="4"></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td>出版社：${book.press }</td>
						<td>出版时间：${book.pubTime }</td>
					</tr>
					<tr>						
						<td>所属类别：${book.type }</td>
						<td>isbn：${book.isbn }&nbsp;&nbsp;</td>
					</tr>
					<tr style="border-bottom:2px dotted #ccc">
						<td>馆藏：<font class="font_bold_green">${book.allQuantity }</font></td>
						<td>可借：
						<c:choose>
							<c:when test="${book.aviQuantity > 0}">
								<font class="font_bold_green">${book.aviQuantity }</font>
							</c:when>
							<c:otherwise>
								<font class="font_bold_red">${book.aviQuantity }</font>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<p class="msg"><!-- 用于显示操作消息 --></p>
			<ul id="PageNum">
				共 <font color="green" style="font-size: 15px;font:bold;">${bookPage.totalPageNumber } </font>页&nbsp;&nbsp;当前第<font color="green" style="font-size: 15px;font:bold;"> ${bookPage.pageNo }</font> 页
				<li>
						<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
				</li>
				<li>
					<c:if test="${bookPage.hasPrev }">
						<a href="bookServlet?method=getBooks&pageNo=${bookPage.prevPage }">上一页</a>
					</c:if>
				</li>
				<c:forEach var="pageNum" begin="1" end = "${bookPage.totalPageNumber }"
							step = "1">
					<c:if test="${pageNum <= 6}">
						<li>
							<a href="bookServlet?method=getBooks&pageNo=${pageNum}">${pageNum}</a>
						</li>
					</c:if>
				</c:forEach>
				<li>
					<c:if test="${bookPage.hasNext }">
						<a href="bookServlet?method=getBooks&pageNo=${bookPage.nextPage }">下一页</a>
					</c:if>
				</li>
				<li>
					<a href="bookServlet?method=getBooks&pageNo=${bookPage.totalPageNumber }">末页</a>
				</li>
				<font>转到&nbsp;&nbsp;</font><input type="text" id="pageNo" style="width: 25px"/>
			</ul>
		</div>
	</div>
	<!-- /MainForm -->
</body>
</html>