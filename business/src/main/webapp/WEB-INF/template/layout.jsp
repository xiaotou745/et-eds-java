<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = PropertyUtils.getProperty("static.business.url");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/base.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/commen.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/index.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/newProjects.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/popup.css">


<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>/js/jquery-ui-1.8.20.js"></script> --%>
<%-- <script type="text/javascript" src="<%=basePath%>/js/jquery.ui.datepicker-zh-CN.js"></script> --%>
<script>
$(document).ready(function() {
	setTimeout("regTrEvent()",50);  
});

function regTrEvent(){  
	if($(".stripe tr").length==0){
		setTimeout("regTrEvent()",50);  
		return;
	}
	$(".stripe tr").mouseover(function() {
		//鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");
	}).mouseout(function() {
		//给这行添加class值为over，并且当鼠标一出该行时执行函数
		$(this).removeClass("over");
	})
	//移除该行的class

	$(".stripe tr:even").addClass("alt");
	//给class为stripe的表格的偶数行添加class值为alt

	//注：$(':even') 为偶数语法；
	//注：$(':odd')  为奇数语法；
	countH();
}  

	$(document).ajaxError(function(event, jqXHR, options, errorMsg) {
		var info=jqXHR.responseText;
		//alert(options.url + "调用出错了！");
		alert(info);
	});
	function countH() {
		var WinHeight = $(window).height();
		$(".nav").css({
			"min-height" : WinHeight - 70
		})
	}
</script>
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<div class="content content2  cb">
		<tiles:insertAttribute name="leftmenu"></tiles:insertAttribute>
		<div class="center">
			<tiles:insertAttribute name="breadcrumbs"></tiles:insertAttribute>
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
			<tiles:insertAttribute name="footer" ignore="true"></tiles:insertAttribute>	
		</div>	
	</div>
</body>
</html>
