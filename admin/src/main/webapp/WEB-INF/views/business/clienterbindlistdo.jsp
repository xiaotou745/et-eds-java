<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.domain.BusinessClienterRelationModel"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
PagedResponse<BusinessClienterRelationModel> responsePageList=	(PagedResponse<BusinessClienterRelationModel>)request.getAttribute("listData");
List<BusinessClienterRelationModel> data = responsePageList.getResultList();
String basePath =PropertyUtils.getProperty("static.admin.url");
if(data == null){
	data = new ArrayList<BusinessClienterRelationModel>();
}%>
<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>骑士姓名</th>
								<th>骑士电话</th>
								<th>时间</th>
								<th>状态</th>
								<th>操作人</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr class="info">
								<td><%=data.get(i).getId().toString()%></td>
								<td><%=ParseHelper.ShowString(data.get(i).getTrueName())%></td>
								<td><%=data.get(i).getPhoneNo()%></td>
		                        <td><%=ParseHelper.ToDateString(data.get(i).getUpdatetime()) %></td>
		                        <td><%=data.get(i).getIsbind()==(short)0? "未绑定":"已绑定"%></td>
		                        <td><%=data.get(i).getUpdateby() %></td>
		                        <td>
		                        <%if(UserContext.getCurrentContext(request).isHasAuth(24)){
	                        		if(data.get(i).getIsbind() == 0){
	                        		%>
	                        		<a href="javascript:funModifyClienterBind(<%=data.get(i).getBusinessid() %>,<%=data.get(i).getClienterid() %>,1,'是否绑定？')">绑定</a>
	                        		<%}else{
	                        		%>
	                        		<a href="javascript:funModifyClienterBind(<%=data.get(i).getBusinessid() %>,<%=data.get(i).getClienterid() %>,0,'是否解除绑定？')">解除绑定</a>
	                        		<%}
	                        	}else if(UserContext.getCurrentContext(request).isHasAuth(52)){
									if(data.get(i).getIsbind() == 0){%>
	                        			<a href="javascript:void(0)" style="color:gray">删除</a>
	                        		<%}else{%>
	                        			<a href="javascript:funRemoveClienterBind(<%=data.get(i).getBusinessid() %>,<%=data.get(i).getClienterid() %>)">删除</a>
	                        		<%}
	                        	}
		                        %>
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