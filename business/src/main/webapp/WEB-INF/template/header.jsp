<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("static.business.url");
%>
<div class="header">
	<a class="logo fl" href="javascript:;"><img src="<%=basePath %>/images/logo.png" width="175" height="25" alt=""></a>
	<p class="fr">
		<a class="fr" href="javascript:;">退出</a>
		<em class="fr">|</em>
		<span class="fr">
			您好，
			<a href="javascript:;">Angel</a>
		</span>
	</p>
</div>