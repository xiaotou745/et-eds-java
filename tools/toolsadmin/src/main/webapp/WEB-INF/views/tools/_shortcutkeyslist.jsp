<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="com.edaisong.toolsadmin.common.ViewModel" %>
<%@page import="com.edaisong.toolsentity.domain.ShortcutKeys" %>
<%@page import="com.edaisong.toolsentity.view.ShortcutKeysModel" %>
<%
	ShortcutKeysModel model = (ShortcutKeysModel)request.getAttribute(ViewModel.KEY_VIEW_DATA);
%>
<table class="table table-borderd table-condensed table-striped">
	<thead>
		<tr>
			<th>Key</th>
			<th>Group</th>
			<th>Value</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%if(model.getKeys()!=null && model.getKeys().size()>0){
			for(ShortcutKeys key: model.getKeys()){%>
				<tr data-id="<%=key.getId()%>">
					<td><code><a data-toggle="modal" data-type="2" href="#modalKey"><%=key.getKey() %></a></code></td>
					<td><code><%=key.getToolsName() %></code></td>
					<td><%=key.getDesc() %></td>
					<td><a class="J_Remove">删除</a></td>
				</tr>
			<%}
		}else{%>
			<tr><td colspan="2">没有数据</td></tr>
		<%}%>
	</tbody>
</table>