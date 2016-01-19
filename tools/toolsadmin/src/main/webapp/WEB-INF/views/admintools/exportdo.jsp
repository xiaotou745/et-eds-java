
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.ExportSqlManage"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
            <th style="width:70px;">编码</th>
            <th>名称</th>
            <th>执行时间</th>
            <th>是否启用</th>
            <th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<ExportSqlManage> data = (PagedResponse<ExportSqlManage>) request.getAttribute("listData");
			List<ExportSqlManage> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<ExportSqlManage>();
			}
			String name="";
			String executetime="";
			String receiveemail="";
			String sqltext="";

			for (int i = 0; i < list.size(); i++) { 
				name=new String(Base64.encodeBase64(list.get(i).getName().getBytes("UTF-8")));  
				executetime=new String(Base64.encodeBase64(list.get(i).getExecutetime().getBytes("UTF-8")));  
				receiveemail=new String(Base64.encodeBase64(list.get(i).getReceiveemail().getBytes("UTF-8")));  
				sqltext=new String(Base64.encodeBase64(list.get(i).getSqltext().getBytes("UTF-8")));  
		%>
		<tr>
			<td>
			<input type="hidden" id="<%="name"+list.get(i).getId()%>" value="<%=name%>"/>
			<input type="hidden" id="<%="executetime"+list.get(i).getId()%>" value="<%=executetime%>"/>
			<input type="hidden" id="<%="receiveemail"+list.get(i).getId()%>" value="<%=receiveemail%>"/>
			<input type="hidden" id="<%="sqltext"+list.get(i).getId()%>" value="<%=sqltext%>"/>
			<%=list.get(i).getId()%></td>
			<td><%=list.get(i).getName()%></td>
			<td><%=list.get(i).getExecutetime()%></td>
		    <td><%=(list.get(i).getIsenable()==0?"有效":"无效")%></td>
			<td>			
			<a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId()%>,<%=list.get(i).getIsenable()%>)">修改</a>
			<a href="javascript:void(0)" onclick="deleterecord(<%=list.get(i).getId()%>)">删除</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>