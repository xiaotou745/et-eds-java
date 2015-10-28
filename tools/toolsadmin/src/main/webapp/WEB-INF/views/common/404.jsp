<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
    response.setStatus(HttpServletResponse.SC_OK);
%>
<img src="<%=basePath%>/img/404sz.jpg" id="img1" />
	<input id="indexbtn" type="button" value="返回首页">
	<p>
		<span id="timespan">5</span>秒后自动返回管理后台首页
	</p>
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<script>
$(function(){ 
	var intervalId=setInterval(function(){
			var timeSection=document.getElementById('timespan');
			var second=parseInt(timeSection.innerHTML);
			if(second<=0){
				clearInterval(intervalId);
				window.location.href = "<%=basePath%>/account/list";
			}else{
				timeSection.innerHTML=second-1;
			}
		},1000);

	$("#indexbtn").click(function() {
		window.location.href = "<%=basePath%>/account/list";
	});
});
</script>