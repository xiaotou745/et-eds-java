<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%> 


<table class="table">
				<thead>
					<tr>
						<th>
							KeyName
						</th>
						<th>
							KeyValue
						</th>
						<th>
							KeyMark
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
			
		<%
		List<GlobalConfigModel> data=	(List<GlobalConfigModel>)request.getAttribute("DataList");
		 for (int i = 0; i < data.size(); i++) {
			 %>  
			 	<tr class="info">
						<td>
							<%=data.get(i).getKeyName() %>
						</td>
						<td>
							<%=data.get(i).getValue() %>
						</td>
						<td>
							注释
						</td>
						<td>
							<button class="btn" type="button">编辑</button>
						</td>
					</tr>
			 
		 <%}%> 
		 	</tbody>
</table>
