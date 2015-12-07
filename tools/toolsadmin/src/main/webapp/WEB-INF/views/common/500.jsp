<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@ page import="java.io.*"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
System.out.println("test");
    //response.setStatus(HttpServletResponse.SC_OK);
%>
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<script>
$(document).ready(function() {
	$("#showErrorMessageButton").click(function() {
		$("#errorMessageDiv").toggle();
	});
});
</script>
    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold">服务器内部错误</h3>

        <div class="error-desc">
            服务器好像出错了...
            <br/>您可以返回主页看看
            <br/><a href="<%=basePath%>/account/list"  class="btn btn-primary m-t">主页</a>
            <br/><a id="showErrorMessageButton"  href="javascript:void(0)">详细错误信息</a>
        </div>
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



