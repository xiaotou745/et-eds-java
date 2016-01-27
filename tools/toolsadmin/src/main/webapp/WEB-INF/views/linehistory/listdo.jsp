<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.LineHistory"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
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
			<td><a href="javascript:void(0)" onclick="showLineHistory('<%=new String(Base64.encodeBase64(list.get(i).getDevPlatform().getBytes("UTF-8")))%>','<%=new String(Base64.encodeBase64(list.get(i).getOnLineProduct().getBytes("UTF-8")))%>','<%=new String(Base64.encodeBase64(list.get(i).getDevVersion().getBytes("UTF-8")))%>','<%=ParseHelper.ToDateString(list.get(i).getOnLineTime(), "")%>','<%=new String(Base64.encodeBase64(list.get(i).getOnLineContent().getBytes("UTF-8")))%>','<%=new String(Base64.encodeBase64(list.get(i).getRemark().getBytes("UTF-8"))) %>')"><%=list.get(i).getOnLineProduct() %></a></td>			
			<td><%=list.get(i).getDevVersion() %></td>			
			<td><%=ParseHelper.ToDateString(list.get(i).getOnLineTime(), "") %></td> 
			<td><a href="javascript:void(0)" onclick="modifyLineHistory(<%=list.get(i).getId()%>,'<%=new String(Base64.encodeBase64(list.get(i).getDevPlatform().getBytes("UTF-8")))%>','<%=new String(Base64.encodeBase64(list.get(i).getOnLineProduct().getBytes("UTF-8")))%>','<%=new String(Base64.encodeBase64(list.get(i).getDevVersion().getBytes("UTF-8")))%>','<%=ParseHelper.ToDateString(list.get(i).getOnLineTime(), "")%>','<%=new String(Base64.encodeBase64(list.get(i).getOnLineContent().getBytes("UTF-8")))%>','<%=new String(Base64.encodeBase64(list.get(i).getRemark().getBytes("UTF-8"))) %>')">修改</a>
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