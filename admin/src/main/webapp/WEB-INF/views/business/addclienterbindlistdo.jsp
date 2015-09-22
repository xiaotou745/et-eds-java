<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.domain.ClienterBindInfoModel"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
PagedResponse<ClienterBindInfoModel> responsePageList=	(PagedResponse<ClienterBindInfoModel>)request.getAttribute("listData");
List<ClienterBindInfoModel> data = responsePageList.getResultList();
String basePath =PropertyUtils.getProperty("java.admin.url");
if(data == null){
	data = new ArrayList<ClienterBindInfoModel>();
}%>
<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>骑士姓名</th>
								<th>骑士电话</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr class="info">
								<td><%=data.get(i).getId() %></td>
								<td><%=ParseHelper.ShowString(data.get(i).getTrueName())%></td>
								<td><%=data.get(i).getPhoneNo()%></td>
		                        <td>
		                        	<%if(data.get(i).getIsBind() == 1){%>
		                        		<button type="button" class="btn btn-default btn-sm">已绑定</button>
		                        	<% }else{%>
		                        		<button type="button" class="btn btn-primary btn-sm" onclick="funAddClienterBind(<%=data.get(i).getId()%>)">绑定</button>
		                        	<%}%>
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