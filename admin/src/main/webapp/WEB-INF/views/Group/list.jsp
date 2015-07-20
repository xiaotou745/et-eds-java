<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style="height:500%"> this is test page.

-1this is test page</div>
1111111111111111111111111111111111111111111111
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
		List<Group> data=	(List<Group>)request.getAttribute("listData");
		 for (int i = 0; i < data.size(); i++) {
			 %>  
			 <tr>
				<td><%=data.get(i).getId() %></td>
				<td><%=data.get(i).getGroupname() %></td>
				<td><%=data.get(i).getCreatetime() %></td>					
			</tr>
		 <%}
		%> 
			</tbody>
		</table>
	</div>
</body>
</html>