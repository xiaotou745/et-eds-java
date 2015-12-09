<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
%>
<link href="<%=basePath %>/css/plugins/iCheck/custom.css" rel="stylesheet">
<script src="<%=basePath %>/js/plugins/iCheck/icheck.min.js"> </script>
<script src="<%=basePath %>/js/plugins/validate/jquery.validate.min.js"> </script>
<script src="<%=basePath %>/js/plugins/validate/messages_zh.js"> </script>
<script src="<%=basePath %>/js/tools/shortcutKeys.js"> </script>
