<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.TaskDistribution"%>
<%@page import="java.util.List"%>
<%
PagedResponse<TaskDistribution> responsePageList=	(PagedResponse<TaskDistribution>)request.getAttribute("listData");
List<TaskDistribution> data = responsePageList.getResultList();
if(data == null){
	data = new ArrayList<TaskDistribution>();
}
%>
<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr>
								<td><%=data.get(i).getId()%></td>
								<td><%=data.get(i).getName()%></td>
								<td><%=data.get(i).getRemark()%></td>
								<td><a href="javascript:void(0)" onclick="LichengChoosed(<%=data.get(i).getId()%>,'<%=data.get(i).getName()%>')">选择</a></td>
							</tr>

							<%
								}
							%>
						</tbody>
					</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage()).replace("jss.search", "jsslicheng.search")%>
		
<script>
$(function() {

});
</script>