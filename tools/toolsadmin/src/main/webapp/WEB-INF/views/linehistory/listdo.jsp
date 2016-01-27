<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.LineHistory"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>项目</th>
			<th>产品</th>
			<th>版本</th>
			<th>上线时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody> 
		<%
			PagedResponse<LineHistory> data = (PagedResponse<LineHistory>) request.getAttribute("listData");
			List<LineHistory> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<LineHistory>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getDevPlatform() %></td>
			<td><%=list.get(i).getOnLineProduct() %></td>
			<td><%=list.get(i).getDevVersion() %></td>			
			<td><%=ParseHelper.ToDateString(list.get(i).getOnLineTime(), "") %></td> 
			<td><a href="javascript:void(0)" onclick="modifyLineHistory(<%=list.get(i).getId()%>,'<%=list.get(i).getDevPlatform()%>','<%=list.get(i).getOnLineProduct()%>','<%=list.get(i).getDevVersion()%>','<%=list.get(i).getOnLineTime()%>','<%=list.get(i).getOnLineContent()%>','<%=list.get(i).getRemark() %>')">修改</a>&nbsp;
			<a href="javascript:void(0)" onclick="deleteLineHistory(<%=list.get(i).getId()%>)">删除</a>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
					
<script>
//修改
function modifyLineHistory(id,devPlatform,onlineProduct,devVersion,onlineTime,onlineContent,remark) {
	$("#modifyDevPlatform").val(devPlatform);
	$("#modifyOnlineProduct").val(onlineProduct);
	$("#txtModifyDevVersion").val(devVersion);
	$("#modifyonlineDate").val(onlineTime);
	$("#txtModifyOnLineContent").val(onlineContent);
	$("#txtModifyRemark").val(remark);
}
//删除
function deleteLineHistory(id){

}
</script>