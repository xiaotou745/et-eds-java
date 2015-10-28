<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.AccountInfo"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>真实姓名</th>
			<th>登录名称</th>
			<th>最后操作时间</th>
			<th>启用状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<AccountInfo> data = (PagedResponse<AccountInfo>) request
					.getAttribute("listData");
			List<AccountInfo> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<AccountInfo>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getUserName()%></td>
			<td><%=list.get(i).getLoginName()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getLastChangeTime(), "") %></td>
			<td><%=list.get(i).getStatus() == 1 ? "√" : "×"%></td>
			<td><a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId()%>)">编辑</a>
			<a href="javascript:void(0)"
				onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a>
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
    	var paramaters = {
    			"userId" :  id
    		};
    		var url = "<%=basePath%>/account/getuserinfo";
    		$.ajax({
    			type : 'POST',
    			url : url,
    			data : paramaters,
    			success : function(result) {
    				$("#txtUserName").val(result.userName);
    				$("#txtLoginName").val(result.loginName);
    				$("#txtPwd").val();
    				$("#txtConfirmPwd").val();
    				if(result.status==1){
    					$("#radyes").prop('checked','checked')
    				}
    				else{
    					$("#radno").prop('checked','checked')
    				}
    			    userid=id;
    				optype=1;
    		        $('#myModal').modal('show');
    			}
    		});

    }
    </script>