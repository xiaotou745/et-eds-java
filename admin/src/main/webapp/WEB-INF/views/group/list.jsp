<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div style="height:500%"></div>

  <div class="form-group">
   <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <span class="">集团名称: </span>
                    <input id="txtGroupName" type="text" name="GroupName" />
                    <span class="">集团AppKey: </span> 
                    <input id="txtAppkey" type="text" name="Appkey" />
                    <input type="submit" value="查询" class="searchBtn" id="btnSearch" />
                    <input type="button" value="新增集团" class="searchBtn" id="btnAdd" /> 
                </td>
            </tr>
        </table>
        
  <%--
  <table width="50%" border="0" cellspacing="0" cellpadding="0"  class="table">
			<thead>
				<tr class="tdbg">
			<th width="%5">集团名称:</th>
			<th width="%5"><input id="title" type="text" class="form-control" placeholder="请输入集团名称..." width="200px"></th>
				<th width="%5">集团AppKey:</th>
				<th width="%5"><input id="title" type="text" class="form-control" placeholder="集团AppKey..." width="200px"></th>
				</tr>
			</thead>
			</table>
	     --%>	
  </div>

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
				<td><a href="javascript:void(0)" >修改</a></td>				
			</tr>
		 <%}
		%> 
			</tbody>
		</table>
	</div>
</body>
</html>