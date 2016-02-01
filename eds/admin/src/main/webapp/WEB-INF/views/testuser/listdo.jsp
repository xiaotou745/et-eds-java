<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.domain.TestUserRecord"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
PagedResponse<TestUserRecord> responsePageList = (PagedResponse<TestUserRecord>) request.getAttribute("listData");
List<TestUserRecord> list = responsePageList.getResultList();
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>测试手机号</th>
			<th>角色</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getPhoneNo()%></td>
			<td><%=list.get(i).getIsC()? "骑士" : ""%>-<%=list.get(i).getIsB()? "门店" : ""%></td>
			<td>
			<a href="javascript:void(0)" onclick="deleteInfo(<%=list.get(i).getPhoneNo()%>,1)">删除骑士</a>
            <a href="javascript:void(0)" onclick="deleteInfo(<%=list.get(i).getPhoneNo()%>,2);">删除订单</a>
            <a href="javascript:void(0)" onclick="deleteInfo(<%=list.get(i).getPhoneNo()%>,3);">删除门店</a> 
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>