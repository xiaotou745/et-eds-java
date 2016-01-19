
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="com.edaisong.entity.domain.GroupApiConfigModel"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.Group"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">序号</th>
			<th width="%5">第三方平台名称</th>
			<th width="%5">第三方平台创建时间</th>
			<th width="%5">第三方平台AppKey</th>
			<th width="%5">第三方平台AppSecrect</th>
			<th width="%5">APP版本</th>
			<th width="%5">状态</th>
			<th width="%5">操作人</th>
			<th width="%5">操作</th>
		</tr>
	</thead>

	<tbody>

		<%
		PagedResponse<GroupApiConfigModel> responsePageList=(PagedResponse<GroupApiConfigModel>)request.getAttribute("listData");
		List<GroupApiConfigModel> data=	((PagedResponse<GroupApiConfigModel>)request.getAttribute("listData")).getResultList();
		 for (int i = 0; i < data.size(); i++) {
			 %>
		<tr>
			<td><%=data.get(i).getGroupId()%></td>
			<td><%=data.get(i).getGroupName() %></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getCreateTime()) %></td>
			<td><%=ParseHelper.ShowString(data.get(i).getAppKey()) %></td>
			<td><%=ParseHelper.ShowString(data.get(i).getAppSecret()) %></td>
			<td><%=ParseHelper.ShowString(data.get(i).getAppVersion()) %></td>
			<%if (data.get(i).getIsValid() == 1)
                    { %>
			<td width="5%"><a href="javascript:void(0);"
				onclick="SetGourpStatus(<%=data.get(i).getGroupId()%>,0)">启用</a></td>
			<% }
                    else
                    {%>
			<td width="5%"><a href="javascript:void(0);"
				onclick="SetGourpStatus(<%=data.get(i).getGroupId()%>,1)">禁用</a></td>
			<% } %>
			<td><%=data.get(i).getCreateName() %></td>
			<td><a href="javascript:void(0)"
				onclick="funcGShowView('<%=data.get(i).getId() %>','<%=data.get(i).getGroupName() %>')">修改</a>
			</td>
		</tr>
		<%}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>
<script type="text/javascript">
    //显示修改集团弹出层
    function funcGShowView(gid, gname) {    
    	   $("#hiduGroupID").val(gid);
           $('#txtuGroupName').val(gname);
           adminjs.openwinbox('#GroupUpdateDivShow');
    }
    
    //修改第三方集团启用状态
    function SetGourpStatus(id, status) {
        if (confirm("确定要更新此状态吗？")) {
            var pars = { "id": id, "isvalid": status };
            console.log(pars)
            var url = "<%=basePath%>/group/updatestatus";
            $.ajax({
                type: 'POST',
                url: url,
                data: pars,
                success: function(result) {
                	console.log(result)
                	if (result.responseCode==0) {		
						layer.alert("设置成功", {
						    icon: 1
						},function(){
							window.location.reload();
						});
					}else
					{
						layer.alert(result.message, {
						    icon: 2
				    	});
					}
                }
        });
        }
        return false;
    }
    </script>



