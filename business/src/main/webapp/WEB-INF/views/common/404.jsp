<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
%>
<!DOCTYPE html>
<html>
<head>
	<title>错误</title>
	<meta charset="utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/base.css">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/commen.css">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/noFound.css">
</head>
<body>

<div class="header">
	<a class="logo fl" href="javascript:;"><img src="<%=basePath%>/images/logo.png" width="74" height="25" alt=""></a>
	<b class="fl">商家中心</b>
</div>
<div class="content cb">
	<img src="<%=basePath%>/images/NoFound.png" width="598" height="348" alt="404图片">
	<h1>抱歉，你访问的页面地址有误，或者该页面不存在</h1>
	<input id="indexbtn" type="button" value="商家中心首页">
	<p>
		<span id="timespan">5</span>秒后自动返回E代送商家中心首页
	</p>
</div>

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script>
function countH(){
	var WinHeight = $(window).height();
	var headerHeight = $('.header').height();
	var contentHeight = $('.content').height();
	$(".content").css({
		"minHeight":WinHeight-headerHeight
	})
};
$(function(){ 
	countH();
	var intervalId=setInterval(function(){
			var timeSection=document.getElementById('timespan');
			var second=parseInt(timeSection.innerHTML);
			if(second<=0){
				clearInterval(intervalId);
				window.location.href = "<%=basePath%>/index";
			}else{
				timeSection.innerHTML=second-1;
			}
		},1000);

	$("#indexbtn").click(function() {
		window.location.href = "<%=basePath%>/index";
	});
});

</script>

</body>
</html>