<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.AppDbConfig"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.enums.ServerType"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>系统名称</th>
			<th>服务器类型</th>
			<th>连接串</th>
			<th>创建时间</th>
			<th>创建人</th>
			<th>最后修改时间</th>
			<th>修改人</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<AppDbConfig> data = (PagedResponse<AppDbConfig>) request.getAttribute("listData");
			List<AppDbConfig> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<AppDbConfig>();
			}
			String configValue="";
			String base64ConfigValue="";
			for (int i = 0; i < list.size(); i++) {
				configValue=list.get(i).getConfigvalue();
				base64ConfigValue=new String(Base64.encodeBase64(configValue.getBytes()));  
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getAppname()%></td>
			<td><%=ServerType.getEnum(list.get(i).getConfigtype()).desc()%></td>
		    <td><%=HtmlUtils.htmlEscape(configValue)%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime(), "") %></td>
			<td><%=list.get(i).getCreatename()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getUpdatetime(), "") %></td>
			<td><%=list.get(i).getUpdatename()%></td>
			<td><a href="javascript:void(0)" onclick="modifyApp(<%=list.get(i).getId()%>,'<%=list.get(i).getAppname()%>','<%=list.get(i).getConfigtype()%>','<%=base64ConfigValue%>')">修改</a>
			<a href="javascript:void(0)" onclick="deleteApp(<%=list.get(i).getId()%>)">删除</a>
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