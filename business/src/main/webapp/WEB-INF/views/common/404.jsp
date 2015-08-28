<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("static.business.url");
    response.setStatus(HttpServletResponse.SC_OK);
%>
<img src="<%=basePath%>/images/404sz.jpg" id="img1" />
<script>window.location.href='<%=basePath%>/index';</script>