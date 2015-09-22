
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@page import="com.edaisong.core.util.PageHelper"%>     
<%@page import="com.edaisong.entity.domain.FeedbackModel"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%> 
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.enums.FeedbackType"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
 %>        
	
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%15">序号</th>
						<th width="%15">用户名称</th>
						<th width="%15">电话</th>			
						<th width="%15">城市</th>
						<th width="%15">时间</th>
						<th width="%15">反馈类型</th>
						<th style="width:300px;">反馈内容</th>
				</tr>
			</thead>
			
<tbody>                          
		<%
       PagedResponse<FeedbackModel> data = (PagedResponse<FeedbackModel>) request
                           				.getAttribute("listData");
       List<FeedbackModel> list = data.getResultList();
       if (list == null) {
       	list = new ArrayList<FeedbackModel>();
       }		
      int i=1;
     for(FeedbackModel model : list){
       %>  
			 <tr>
				<td><%=i %></td>
				<td><%=model.getName() %></td>
				<td><%=model.getPhoneNo()%></td>
				<td><%=model.getCity()%></td>					 
				<td><%=ParseHelper.ToDateString(model.getCreatetime())%></td>
				<td><%=FeedbackType.getEnum(model.getFeedbacktype()).desc()%>	</td>						
				<td><%=model.getContent()%></td>	
			</tr>
		
		 <%	i++;}
		%> 	 	
			</tbody>
		</table>
		<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>

	

	
