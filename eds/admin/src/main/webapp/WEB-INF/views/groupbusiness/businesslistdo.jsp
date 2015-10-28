<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessRelationModel"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
PagedResponse<GroupBusinessRelationModel> responsePageList=	(PagedResponse<GroupBusinessRelationModel>)request.getAttribute("listData");
List<GroupBusinessRelationModel> data = responsePageList.getResultList();
String basePath =PropertyUtils.getProperty("java.admin.url");
if(data == null){
	data = new ArrayList<GroupBusinessRelationModel>();
}%>
<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>门店名称</th>
								<th>电话</th>
								<th>门店余额</th>
								<th>状态</th>
								<th>所属集团</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr class="info">
								<td><%=data.get(i).getBusinessid() %></td>
								<td><%=ParseHelper.ShowString(data.get(i).getName())%></td>
								<td><%=data.get(i).getPhoneNo()%></td>
								<td><%=data.get(i).getBalancePrice()%></td>
								<td><%=data.get(i).getIsbind() == null || data.get(i).getIsenable()==0 ? "未绑定" : "已绑定"  %></td>
								<td><%=data.get(i).getIsbind() == null || data.get(i).getIsenable()==0 ? "" : ParseHelper.ShowString(data.get(i).getGroupName())%></td>
		                        <td>
		                        	<%if(data.get(i).getIsbind() == null || data.get(i).getIsenable()==0){%>
		                        		<button type="button" class="btn btn-primary btn-sm" onclick="funAddBusinessBind(<%=data.get(i).getBusinessid()%>)">绑定</button>
		                        	<% }%>
		                        </td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>