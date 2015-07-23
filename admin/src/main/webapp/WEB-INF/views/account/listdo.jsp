<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<thead>
			<tr class="tdbg">
				<th width="%5">id</th>
					<th width="%5">用户名称</th>
					<th width="%5">登录名称</th>
			</tr>
		</thead>
		<tbody>
	<%
	List<Account> data=(List<Account>)request.getAttribute("listData");
	 for (int i = 0; i < data.size(); i++) {
		 %>  
		 <tr>
			<td><%=data.get(i).getId() %></td>
			<td><%=data.get(i).getUsername() %></td>
			<td><%=data.get(i).getLoginname()%></td>
		</tr>
	 <%}
	%> 
		</tbody>
</table>