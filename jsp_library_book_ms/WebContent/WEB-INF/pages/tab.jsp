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
<link href="css/base.css" rel="stylesheet" type="text/css" />
<!-- 下拉框样式 -->
<!-- 下拉框jq -->
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/side.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<%@ include file="/commons/userQueryCondition.jsp" %>
</head>
<script type="text/javascript">
	//为了在翻页过程中保存查询条件
	$(function(){
		
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			//1.校验 val 是否为数字
			var reg = /^\d+$/g;
			var flag = false;
			if(reg.test(val)){
				var pageNo = parseInt(val);
				if(val >= 1 && val <= parseInt('${userPage.totalPageNumber}')){
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
			var href = "userServlet?method=getUsers&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
	})
</script>
<body>
	<!-- MainForm -->
	<div id="MainForm">
		<div class="form_boxA">
			<h2>用户信息管理</h2>
			<div class="queryForm">
			<table cellpadding="0" cellspacing="0">
				<form action="userServlet?method=getUsers" method="post">
					<tr>
						<td>所属学院:</td>
						<td>
							<select name="college" id="college">
								<option>-请选择-</option>
							</select>
						</td>
						<td>专业:</td>
						<td>
							<select name="major" id="major">
								<option>-请选择-</option>
							</select>
						</td>
						<td>班级:</td>
						<td>
							<select name="classes" id="classes">
								<option>-请选择-</option>
							</select>
						</td>
						<td>学号:</td>
						<td>
							<input type=""text" name="id"/>
						</td>
						<td><input type="submit" value="查询" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/></td>
					</tr>
				</form>
			</table>
			</div>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<th>序号</th>
					<th>学号/工号</th>
					<th>姓名</th>
					<th>所属学院</th>
					<th>专业</th>
					<th>班级</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${userPage.list }" var="user" varStatus="status">
					<%-- 设置隔行变色 --%>
					<c:set var="rows" scope="page" value="${status.index + 1 }"/>
					<c:choose>
						<c:when  test="${rows % 4 == 0 }">
							<c:set var="className" value="bgcA"/>
						</c:when>
						<c:when  test="${rows % 4 == 1 }">
							<c:set var="className" value="bgcB"/>
						</c:when>
						<c:when  test="${rows % 4 == 2 }">
							<c:set var="className" value="bgcC"/>
						</c:when>
						<c:otherwise>
							<c:set var="className" value="bgcD"/>
						</c:otherwise>
					</c:choose>
					<tr class="${className }">
						<td>${status.index + 1 }</td>
						<td>${user.id }</td>
						<td>
							<a href="userServlet?method=getUser&pageNo=${userPage.pageNo }&userid=${user.id}" target="_self">${user.name }</a>
						</td>
						<td>${user.college }</td>
						<td>${user.major }</td>
						<td>${user.classes }</td>
						<td><a href="userServlet?method=getUser&type=update&pageNo=${userPage.pageNo }&userid=${user.id}" target="_parent">修改</a>&nbsp;</td>
					</tr>
				</c:forEach>
				</table>
			<p class="msg"><!-- 用于显示操作消息 --></p>
			<ul id="PageNum">
				共<font color="green" style="font-size: 15px;font:bold;"> ${userPage.totalPageNumber }</font> 页&nbsp;&nbsp;当前第<font color="green" style="font-size: 15px;font:bold;"> ${userPage.pageNo }</font> 页
				<li>
						<a href="userServlet?method=getUsers&pageNo=1">首页</a>
				</li>
				<li>
					<c:if test="${userPage.hasPrev }">
						<a href="userServlet?method=getUsers&pageNo=${userPage.prevPage }">上一页</a>
					</c:if>
				</li>
				<c:forEach var="pageNum" begin="1" end = "${userPage.totalPageNumber }"
							step = "1">
					<c:if test="${pageNum <= 6 }">
						<li>
							<a href="userServlet?method=getUsers&pageNo=${pageNum }">${pageNum }</a>
						</li>
					</c:if>
				</c:forEach>
				<li>
					<c:if test="${userPage.hasNext }">
						<a href="userServlet?method=getUsers&pageNo=${userPage.nextPage }">下一页</a>
					</c:if>
				</li>
				<li>
					<a href="userServlet?method=getUsers&pageNo=${userPage.totalPageNumber }">末页</a>
				</li>
				<font>转到&nbsp;&nbsp;</font><input type="text" id="pageNo" style="width: 25px;"/>
			</ul>
		</div>
	</div>
	<!-- /MainForm -->
	<%-- 因为html和js的执行顺序关系，因此级联列表相关的js文件放置在此处  --%>
	<script src="js/cascade.js" type="text/javascript"></script>
</body>
</html>