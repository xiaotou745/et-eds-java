<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.AppDbConfig"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.enums.ServerType"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.edaisong.toolsentity.AuthorityMenuClass"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
	List<AuthorityMenuClass> data = (List<AuthorityMenuClass>) request.getAttribute("listData");
	int parid = (int) request.getAttribute("ParId");
%>
<script>
var parid=<%=parid%>;
</script>
<table class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>编号</th>
			<th>菜单名称</th>
			<th>AuthCode</th>
			<th>URL</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<% 
		if(data!=null)
		{

		for(int i=0;i<data.size();i++)
		{
			%>
			<tr>
			<td><%=data.get(i).getId()%></td>
			<td><%=data.get(i).getMenuname()%></td>
			<td><%=data.get(i).getAuthCode()%></td>
			<td><%=data.get(i).getUrl()%></td>
			<td><a href="javascript:void(0)">修改</a><a href="javascript:void(0)" onclick="getList(<%=data.get(i).getId()%>)">查看子菜单</a></td>
			</tr>
			<%
		}
	}
	%>
	</tbody>
</table>
<script>
if(parid==0)
{
	$('#cdname').html('一级菜单');
}
else
{
	$('#cdname').html('二级菜单');
}
</script>
