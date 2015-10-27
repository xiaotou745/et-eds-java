<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<div class="row wrapper border-bottom white-bg page-heading">
	<div class="col-lg-10">
		<h2>${currenttitle}</h2>
		<ol class="breadcrumb">
			<li><a href="<%=basePath%>/ordermanage/auditorder">首页</a></li>
			<li><a>${subtitle}</a></li>
			<li class="active"><strong>${currenttitle}</strong></li>
		</ol>
	</div>
	<div class="col-lg-2"></div>
</div>