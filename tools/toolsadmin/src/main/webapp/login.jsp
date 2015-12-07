<%@page import="com.edaisong.toolscore.consts.GlobalSettings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsadmin.common.UserIndentity" %>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
%>
<%
	if(UserIndentity.getIndentity(request).isLogin()){
		//如果登录,跳转到首页
		response.sendRedirect(basePath+"/welcome"); 
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="renderer" content="webkit" />
	<title>E代送开发者工具</title>
	<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet" />
	<link href="<%=basePath%>/css/font-awesome.css" rel="stylesheet" />
	<link href="<%=basePath%>/css/animate.css" rel="stylesheet" />
	<link href="<%=basePath%>/css/style.css" rel="stylesheet" />
	<link href="<%=basePath%>/css/plugins/iCheck/custom.css" rel="stylesheet" />
	<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
	<script src="<%=basePath%>/js/bootstrap.min.js"></script>
	<script src="<%=basePath %>/js/plugins/iCheck/icheck.min.js"> </script>
</head>
<body class="gray-bg">
	<div class="middle-box text-center loginscreen animated fadeInDown">
		<div style="margin-top: 100px;">
			<h2>E代送开发者工具</h2>
			<form class="m-t" role="form">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="用户名" required="" name="username">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="密码" required="" name="password">
				</div>
				<div class="form-group">
					<input type="text" name="code" class="form-control" style="width: 100px; float: left;" placeholder="验证码"
						required=""> 
					<img src="<%=basePath + "/account/code?x=" + Math.random()%>" id="imgCode" height="30px"
						style="float: left;" onclick="changeCodeImg()" />
				</div>
				<div class="form-group">
					<label> 
						<input type="checkbox" name="rememberMe" id="rememberMe" class="i-checks">记住我
					</label>
				</div>
				<button id="btnLogin" type="button" class="btn btn-primary block full-width m-b">登 录</button>
				<div class="form-group">
					<h3 id="lblErrorMsg" style="color: red;"></h3>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		function changeCodeImg(){
			var ran = Math.random();
			document.getElementById("imgCode").src = "<%=basePath %>/account/code?x="+ran;
		}
		
		$(function(){
			$('.i-checks').iCheck({
		        checkboxClass: 'icheckbox_square-green',
		        radioClass: 'iradio_square-green',
		    });
			$("#btnLogin").bind("click",function(){
				var params = {
						username:$(document).find("[name=username]").val(),
						password:$(document).find("[name=password]").val(),
						code:$(document).find("[name=code]").val(),
						rememberMe:$("#rememberMe").is(":checked")
				};
				$.ajax({
					url:"<%=basePath %>/account/login",
					method:"post",
					data:params,
					dataType:"json",
					success:function(resp){
						if(resp.iserror){
							$("#lblErrorMsg").text(resp.message);
						}else{
							location.href="<%=basePath %>/welcome";
						}
					}
				})
			})
		})
	</script>
</body>
</html>
