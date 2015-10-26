<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>账号名称</th>
			<th>登录名称</th>
			<th>启用状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<Account> data = (PagedResponse<Account>) request
					.getAttribute("listData");
			List<Account> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Account>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getUsername()%></td>
			<td><%=list.get(i).getLoginname()%></td>
			<td><%=list.get(i).getStatus() == 1 ? "√" : "×"%></td>
			<td><a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId()%>)">编辑</a>
			<a href="javascript:void(0)" onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a>
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
<script type="text/javascript">				
    function modify(id) {
        $('#myModal').modal('show');
    }
    </script>