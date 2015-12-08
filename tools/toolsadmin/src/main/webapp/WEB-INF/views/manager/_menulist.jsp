<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.edaisong.toolsentity.domain.Menu"%>
<%@page import="com.edaisong.toolsadmin.common.MenuUtils" %>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");

	List<Menu> rootMenus = MenuUtils.getTopMenus();
	List<Menu> lstMenus = MenuUtils.getAllMenus();
%>

<table class="table table-condensed table-bordered">
	<thead>
		<tr>
			<th></th>
			<th>菜单名称</th>
			<th>Url</th>
			<th>Icon</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%if (rootMenus != null && rootMenus.size() > 0) {
			for (Menu rootMenu : rootMenus) {%>
			<tr data-id="<%=rootMenu.getId()%>" data-opennewwindow="<%=rootMenu.getOpenNewWindow() %>"
				data-orderby="<%=rootMenu.getOrderBy()%>"
				data-parentid="<%=rootMenu.getParentId()%>">
				<td width="10px"><a class="J_Toggle" data-show="true"><i class="fa fa-minus"></i></a></td>
				<td><a data-toggle="modal" data-type="2" href="#modalMenus"><strong class="text-navy" name="name"><%=rootMenu.getName()%></strong></a></td>
				<td name="url"><%=rootMenu.getUrl()%></td>
				<td><i class="<%=rootMenu.getIcon()%>"></i> <code name="icon"><%=rootMenu.getIcon()%></code></td>
				<td><a class="btn btn-sm btn-white J_Remove">删除</a></td>
			</tr>
			<%List<Menu> childs = MenuUtils.getChildMenus(rootMenu.getId());
				for (Menu child : childs) {%>
				<tr parentid="<%=child.getParentId()%>" data-id="<%=child.getId()%>"
					data-opennewwindow="<%=child.getOpenNewWindow() %>"
					data-orderby="<%=child.getOrderBy()%>" 
					data-parentid="<%=child.getParentId()%>">
					<td></td>
					<td><a data-toggle="modal" href="#modalMenus" data-type="2" name="name"><%=child.getName()%></a></td>
					<td><code name="url"><%=child.getUrl()%></code></td>
					<td name="icon"><%=child.getIcon()%></td>
					<td><a class="btn btn-sm btn-white J_Remove">删除</a></td>
				</tr>
				<%}
			}
		} else {%>
		<tr>
			<td colspan="5">暂时没有菜单明细</td>
		</tr>
		<%}%>
	</tbody>
</table>