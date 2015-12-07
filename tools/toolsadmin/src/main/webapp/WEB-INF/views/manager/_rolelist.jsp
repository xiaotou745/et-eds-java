<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.edaisong.toolsentity.domain.Role"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");

	List<Role> allRoles = (List<Role>) request.getAttribute("dataOfRoles");
	Role currentRole = (Role)request.getAttribute("dataOfCurrentRole");
%>

<%if(allRoles.size()>0){%>
	<ul>
	<%for(Role role : allRoles){
		String roleId = "role"+role.getId();
		boolean selected = role.getId()==currentRole.getId();%>
		<li id="<%=role.getId() %>" data-id="<%=role.getId() %>" data-jstree='{"opened":true,"selected":<%=selected%>}'><%=role.getName() %></li>
	<%} %>
	</ul>
<%}else{%>
    <p>还没有角色，赶快添加吧.</p>
<%}%>