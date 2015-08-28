<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@ page import="java.io.*"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
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

<div></div>
<table width="100%">
	<tr>
		<td style="border-bottom: dotted 1px Gray;" colspan="2"><img
			src="<%=basePath%>/img/500.jpg" id="img1" /></td>
		<td></td>
	</tr>
	<tr>

		<td>尊敬的用户：<br />系统出现了异常，请重试。 <br />如果问题重复出现，请向系统管理员反馈。<br />
		<br /> <a id="showErrorMessageButton" href="javascript:void">详细错误信息</a>
		</td>
				<td style="width: 130px"></td>
	</tr>
</table>
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



