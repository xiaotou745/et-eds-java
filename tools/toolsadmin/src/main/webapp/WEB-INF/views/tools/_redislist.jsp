<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Set" %>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.domain.Menu" %>
<%@page import="com.edaisong.toolsentity.domain.Role" %>
<%
	Set<String> keys = (Set<String>) request.getAttribute("dataOfKeys");
	String suffxKey = request.getAttribute("dataOfSuffxKey").toString();
%>
<table class="table table-borderd">
	<thead>
		<tr>
			<th>Key</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%if(keys!=null && keys.size()>0){
			for(String key: keys){%>
				<tr>
					<td><a data-toggle="modal" href="#modalRedisValue"><%=key.replaceFirst(suffxKey, "") %></a></td>
					<td><a class="J_Remove">删除</a></td>
				</tr>
			<%}
		}else{%>
			<tr><td colspan="2">缓存中没有数据</td></tr>
		<%}%>
	</tbody>
</table>
