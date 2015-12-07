<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
%>
<link href="<%=basePath%>/css/plugins/jsTree/default/style.css" rel="stylesheet" />
<script src="<%=basePath%>/js/plugins/jsTree/jstree.js"></script>
<script src="<%=basePath%>/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=basePath%>/js/plugins/validate/messages_zh.js"></script>
<script src="<%=basePath%>/js/manager/role.js"></script>