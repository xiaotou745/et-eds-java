<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.domain.GroupModel"%> 
<%
	String basePath = request.getContextPath();
%>


    <link href="<%=basePath%>/css/admin.css" rel="stylesheet" /> 

    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/admin.js""></script>

        
<div class="ibox-content">		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table">
			<thead>
				<tr class="tdbg">
					<th width="%5">序号</th>
						<th width="%5">集团名称</th>
						<th width="%5">创建时间</th>
						<th width="%5">集团AppKey</th>
						<th width="%5">集团AppSecre</th>
						<th width="%5">APP版本</th>
						<th width="%5">状态</th>
						<th width="%5">操作人</th>
						<th width="%5">操作</th>
				</tr>
			</thead>
			
			<tbody>
	
		<%
		List<GroupModel> data=	(List<GroupModel>)request.getAttribute("listData");
		 for (int i = 0; i < data.size(); i++) {
			 %>  
			 <tr>
				<td><%=data.get(i).getId() %></td>
				<td><%=data.get(i).getGroupname() %></td>
				<td><%=data.get(i).getCreatetime() %></td>
				<td><%=data.get(i).getAppkey() %></td>
				<td><%=data.get(i).getAppsecret() %></td>					
				<td><%=data.get(i).getAppversion() %></td>				
	            
				<%
				if (data.get(i).getIsvalid().toString().equals("1"))
				{
				%>		
				<td ><a href="javascript:void(0)" onclick="SetGourpStatus('<%=data.get(i).getId() %>','<%=data.get(i).getIsvalid() %>')">启用</a></td>
				<%
				}
				else
				{
				%>
				<td ><a href="javascript:void(0);" onclick="SetGourpStatus('<%=data.get(i).getId() %>','<%=data.get(i).getIsvalid() %>')">未启用</a></td>
				<%
				}
				%>			
					
				<td><%=data.get(i).getCreatename() %></td>	
				<td>
				<a href="javascript:void(0)" onclick="funcGShowView('<%=data.get(i).getId() %>','<%=data.get(i).getGroupname() %>')">修改</a>
				<% 
				if (data.get(i).getAppkey()==null || data.get(i).getAppkey().equals(""))
				{
				%>
				<a href="javascript:void(0)" onclick="funcAShowView('<%=data.get(i).getId() %>','<%=data.get(i).getGroupname() %>')">设置AppKey</a>
				<%
				}
				%>
				</td>				
			</tr>
		 <%}
		%> 	 
	
			</tbody>
		</table>
	</div>
	
<script type="text/javascript">
    //显示修改集团弹出层
    function funcGShowView(gid, gname) {    
    	   $("#hiduGroupID").val(gid);
           $('#txtuGroupName').val(gname);
           adminjs.openwinbox('#GroupUpdateDivShow');
    }
    //显示设置config弹出层
    function funcAShowView(gid,gname) {
        $("#HidGroupID").val(gid); 
        $("#statusFinApp").text("配置-" + gname+"-AppConfig");
        adminjs.openwinbox('#GroupAppConfig');
    }
    
    function SetGourpStatus(id, status) {
        if (confirm("确定要更新此状态吗？")) {
            var pars = { "id": id, "status": status };
            var url = "<%=basePath%>/group/updatestatus";
            $.ajax({
                type: 'POST',
                url: url,
                data: pars,
                success: function(result) {
                	if(result=="ok")
                    	window.location.href = "<%=basePath%>/group/list";   
                }
        });
        }
        return false;
    }
    </script>
	
	
	
