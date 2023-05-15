<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>添加用户信息</title>
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
	<script type="text/javascript">
		$(function(){
			$(":submit").click(function(){
				//检查用户输入信息是否完整
				var id = $("#id").val();
				var name = $("#name").val();
				var college = $("#college option:selected").val();;
				var major = $("#major option:selected").val();
				var classes = $("#classes option:selected").val();;
				if(id!="" && name != "" && college != "" && major != "" && classes != "")
					return;
				alert("信息不完整！");
				return false;
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
						<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;-&nbsp;
							<a href="userServlet?method=getUsers&pageNo=${param.pageNo }">用户管理</a>
							&nbsp;-</span>&nbsp;添加用户信息
					</div>
				</div>
				<div class="userUpdate">
					<form action="userServlet?method=addUser&kind=user" enctype="multipart/form-data" method="post">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th width="100">相片 :</th>
							<td style="text-align: center;">
								<div>
									<input type="file" name="file"><br/>
								</div>
							</td>
						</tr>
						<tr>
							<th width="100">学号/工号 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<input name="id" id="id" type="text" size="20" />
								</div>
							</td>
						</tr>
						<tr>
							<th>姓名 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<input name="name" id="name" type="text" size="20" />
								</div>
							</td>
						</tr>
						<tr>
							<th>所属学院 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<select name="college" id="college">
										<option  selected="selected"></option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th>专业 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<select name="major" id="major">
										<option  selected="selected"></option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th>班级 :</th>
							<td>
								<div class="txtbox floatL" style="width:215px;">
									<select name="classes" id="classes">
										<option  selected="selected"></option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td  colspan="3" style="text-align: center;">
								<input type="submit" value="确认添加" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align: center;">
								<font color="red">${msg }</font>
							</td>
						</tr>
					</table>
					</form>
			</div>
		</div>
	</div>
</div>
<!-- /Popup -->
<%-- 因为html和js的执行顺序关系，因此级联列表相关的js文件放置在此处  --%>
<script src="js/cascade.js" type="text/javascript"></script>
</body>
</html>