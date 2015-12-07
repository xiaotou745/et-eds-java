<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
%>
<link href="<%=basePath %>/css/plugins/iCheck/custom.css" rel="stylesheet">
<script src="<%=basePath %>/js/plugins/iCheck/icheck.min.js"> </script>
<script src="<%=basePath %>/js/plugins/validate/jquery.validate.min.js"> </script>
<script src="<%=basePath %>/js/plugins/validate/messages_zh.js"> </script>
<link href="<%=basePath %>/css/plugins/mobiscroll/mobiscroll.custom-2.5.2.min.css" rel="stylesheet">
<script src="<%=basePath %>/js/plugins/mobiscroll/mobiscroll.custom-2.5.2.min.js"> </script>
<script src="<%=basePath %>/js/plugins/mobiscroll/mobiscroll.datetime-2.5.1.js"> </script>
<script src="<%=basePath %>/js/plugins/mobiscroll/mobiscroll.datetime-2.5.1-zh.js"> </script>
<style type="text/css">
<!--
.dw { z-index: 5001; }
.dw-persp, .dwo { z-index: 5001; }
-->
</style>
<script src="<%=basePath %>/js/tools/tasks.js"> </script>
