<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessBindOptionLogModel"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
PagedResponse<GroupBusinessBindOptionLogModel> responsePageList=	(PagedResponse<GroupBusinessBindOptionLogModel>)request.getAttribute("listData");
List<GroupBusinessBindOptionLogModel> data = responsePageList.getResultList();
String basePath =PropertyUtils.getProperty("static.admin.url");
if(data == null){
	data = new ArrayList<GroupBusinessBindOptionLogModel>();
}%>
<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>门店名称</th>
								<th>电话</th>
								<th>门店余额</th>
								<th>操作类型</th>
								<th>操作日期</th>
								<th>操作人</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr class="info">
								<td><%=data.get(i).getId() %></td>
								<td><%=ParseHelper.ShowString(data.get(i).getBusinessName())%></td>
								<td><%=data.get(i).getPhoneNo()%></td>
								<td><%=data.get(i).getBalancePrice()%></td>
								<td><%=data.get(i).getOpttype()==1?"绑定":"解除绑定"%></td>
								<td><%=ParseHelper.ToDateString(data.get(i).getInserttime())%></td>
		                        <td><%=data.get(i).getOptname()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>