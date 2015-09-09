
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>

<%
String basePath =PropertyUtils.getProperty("static.admin.url");
	
	List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
	List<DeliveryCompany> dCListData=	(List<DeliveryCompany>)request.getAttribute("dCListData");
%>