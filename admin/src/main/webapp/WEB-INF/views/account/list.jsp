<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
		集团记录列表：
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr class="tdbg">
					<th width="%5">id</th>
						<th width="%5">集团名称</th>
						<th width="%5">创建时间</th>
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
	</div>
</body>
</html>