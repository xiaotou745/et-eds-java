<%@page import="com.edaisong.toolscore.consts.GlobalSettings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsadmin.common.LoginUtil" %>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsadmin.common.LoginUtil"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
%>
<%
	boolean isLogin =false;// LoginUtil.checkIsLogin(request,response);
	if(isLogin){
		//如果登录,跳转到首页
		response.sendRedirect(basePath+"/account/list"); 
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="renderer" content="webkit" />
<title>易代送管理工具后台</title>
<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=basePath%>/css/font-awesome.css" rel="stylesheet" />
<link href="<%=basePath%>/css/animate.css" rel="stylesheet" />
<link href="<%=basePath%>/css/style.css" rel="stylesheet" />
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
</head>
<body class="gray-bg">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div style="margin-top: 100px;">
			<!-- 			<div>
				<h1 class="logo-name">H+</h1>
			</div> -->
			<h2>易代送管理工具后台</h2>
			<form class="m-t" role="form" action="<%=basePath%>/account/login"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="用户名"
						required="" name="username">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="密码"
						required="" name="password">
				</div>
				<div class="form-group">
					<input type="text" name="code" class="form-control"
						style="width: 100px; float: left;" placeholder="验证码"
						required=""> <img src="<%=basePath+"/account/code?x="+Math.random()%>" id="imgCode"
						height="30px" style="float: left;" onclick="changeCodeImg()"/>
				</div>
				<div class="form-group">
					<label> <input type="checkbox" name="rememberMe" id="rememberMe" value="1" class="i-checks">自动登录
					</label>
				</div>
				<button type="submit" class="btn btn-primary block full-width m-b">登
					录</button>
				<div class="form-group">
					<h3 style="color:red;"><%=request.getAttribute("error")==null?"": request.getAttribute("error")%></h3>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
	function changeCodeImg(){
		var ran = Math.random();
		document.getElementById("imgCode").src = "<%=basePath %>/account/code?x="+ran;
	}
	</script>
	<!-- Mainly scripts -->
</body>
</html>
