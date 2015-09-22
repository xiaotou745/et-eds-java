<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@ page import="java.io.*"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
    //response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html>
<html>
<head>
	<title>报错</title>
	<meta charset="utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/base.css">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/commen.css">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/noFound.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script>
$(document).ready(function() {
	function countH(){
		var WinHeight = $(window).height();
		var headerHeight = $('.header').height();
		var contentHeight = $('.content').height();
		$(".content").css({
			"minHeight":WinHeight-headerHeight
		})
	};
	countH();
	$("#showErrorMessageButton").click(function() {
		$("#errorMessageDiv").toggle();
	});
	$("#indexbtn").click(function() {
		window.location.href = "<%=basePath%>/index";
	});
});
</script>
</head>
<body>

<div class="header">
	<a class="logo fl" href="javascript:;"><img src="<%=basePath%>/images/logo.png" width="74" height="25" alt=""></a>
	<b class="fl">商家中心</b>
</div>
<div class="content cb">
	<img class="img" src="<%=basePath%>/images/500.png" width="598" height="348" alt="500图片">
	<h3>服务器内部错误</h3>
	<h2>服务器好像出错了…您可以访问首页看看</h2>
	<input type="button" id="indexbtn" value="商家中心首页">
	<a id="showErrorMessageButton"  href="javascript:void(0)">详细错误信息</a>
</div>
<div id="errorMessageDiv" style="display:none;">
	<pre>
                <%
                	try {
                		//全部内容先写到内存，然后分别从两个输出流再输出到页面
                		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                		PrintStream printStream = new PrintStream(byteArrayOutputStream);

                		printStream.println();

                		printStream.println("访问的路径: "+ request.getAttribute("javax.servlet.forward.request_uri"));
                		printStream.println();

                		Enumeration<String> e = request.getParameterNames();
                		if (e.hasMoreElements()) {
                			printStream.println("请求中的Parameter包括：");
                			while (e.hasMoreElements()) {
                				String key = e.nextElement();
                				printStream.println(key + "="+ request.getParameter(key));
                			}
                			printStream.println();
                		}
                		printStream.println("异常信息:");
                		Object msg = request.getAttribute("exception");
                		if (msg != null) {
                			printStream.println(msg);
                		} else {
                			printStream.println(exception.getClass() + " : "+ exception.getMessage());
                		}
                		printStream.println();
                		printStream.println("堆栈信息");
                		Object stackTrace = request.getAttribute("stackTrace");
                		if (stackTrace != null) {
                			printStream.println(stackTrace);
                		} else {
                			exception.printStackTrace(printStream);
                		}
                		printStream.println();
                		out.print(byteArrayOutputStream); //输出到网页
                	} catch (Exception ex) {
                		ex.printStackTrace();
                	}
                %>
            </pre>
</div>
</body>
</html>