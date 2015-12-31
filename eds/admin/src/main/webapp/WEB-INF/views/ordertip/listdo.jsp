
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@page import="com.edaisong.core.util.PageHelper"%>     
<%@page import="com.edaisong.entity.OrderTip"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%> 
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
 %>        
	
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%15">序号</th>
						<th width="%15">金额</th>				
				</tr>
			</thead>
			
<tbody>                          
		<%
       PagedResponse<OrderTip> data = (PagedResponse<OrderTip>) request
                           				.getAttribute("listData");
       List<OrderTip> list = data.getResultList();
       if (list == null) {
       	list = new ArrayList<OrderTip>();
       }		
      int i=1;
     for(OrderTip model : list){
       %>  
			 <tr>
				<td><%=i %></td>
				<td><%=model.getAmount() %></td>				
			</tr>
		
		 <%	i++;}
		%> 	 	
			</tbody>
		</table>
		<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>

	

	
