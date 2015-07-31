<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%
	String basePath =new PropertyUtils().getProperty("static.admin.url");
%>        
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
		List<Group> data=	(List<Group>)request.getAttribute("listData");
		 for (int i = 0; i < data.size(); i++) {
			 %>  
			 <tr>
				<td><%=data.get(i).getId() %></td>
				<td><%=data.get(i).getGroupname() %></td>
				<td><%=data.get(i).getCreatetime() %></td>
				<td><%=data.get(i).getCreatetime() %></td>	
				<td><%=data.get(i).getIsvalid() %></td>
				<td><%=data.get(i).getIsvalid() %></td>
				<td><%=data.get(i).getIsvalid() %></td>					
	
				<%--<%
				if(data.get(i).getIsvalid()==1)
				%>		
				<td ><a href="javascript:void(0);">启用</a></td>
				<%
				else
				%>
				<td ><a href="javascript:void(0);">未启用</a></td>
				 
				<td><%=data.get(i).getCreatetime() %></td>
					
				--%>			
				<td><%=data.get(i).getIsvalid() %></td>	
				<td>
				<a href="javascript:void(0)" onclick="funcGShowView('<%=data.get(i).getId() %>','<%=data.get(i).getGroupname() %>')">修改</a>
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
    </script>
	
	
	
