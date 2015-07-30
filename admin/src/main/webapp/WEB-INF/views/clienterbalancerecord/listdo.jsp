<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@page import="com.edaisong.core.common.PageHelper"%>     
<%@page import="com.edaisong.entity.ClienterBalanceRecord"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.common.ParseHelper"%> 

<%
	String basePath = request.getContextPath();
%>       
	
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">序号</th>				
				</tr>
			</thead>			
			<tbody>                           

		<%				
		PagedResponse<ClienterBalanceRecord> data = (PagedResponse<ClienterBalanceRecord>) request
				.getAttribute("listData");
		List<ClienterBalanceRecord> list = data.getResultList();
		if (list == null) {
			list = new ArrayList<ClienterBalanceRecord>();
		}		
		 for (int i = 0; i < list.size(); i++) {
			 %>  
			 <tr>
				<td><%=list.get(i).getId() %></td>			
			
			</tr>
		 <%}
		%> 	 	
			</tbody>
		</table>
		<%=PageHelper.GetPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>


	
