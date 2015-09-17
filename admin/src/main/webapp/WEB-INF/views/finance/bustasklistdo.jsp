
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@page import="com.edaisong.core.util.PageHelper"%>     
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%> 
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.domain.BusTaskList"%>
<%String basePath =PropertyUtils.getProperty("static.admin.url");%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">编号</th>
						<th width="%5">门店名称</th>
						<th width="%5">注册电话</th>
						<th width="%5">任务量</th>
						<th width="%5">订单量</th>
				</tr>
			</thead>
			
			<tbody>                           
		
				<%PagedResponse<BusTaskList> data = (PagedResponse<BusTaskList>) request.getAttribute("listData");
		       	  List<BusTaskList> list = data.getResultList();
		       		if (list == null) {list = new ArrayList<BusTaskList>();}		
		         	for (int i = 0; i < list.size(); i++) {%>  
					 <tr>
						<td><%=list.get(i).getBusinessId() %></td>
						<td><a href='/'><%=list.get(i).getName()%></a></td>
						<td><%=list.get(i).getPhoneNo() %></td>
						<td><%=list.get(i).getTaskCount() %></td>
						<td><%=list.get(i).getOrderCount() %></td>
					</tr>
				 <%}%> 	 	
			</tbody>
		</table>
		<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>