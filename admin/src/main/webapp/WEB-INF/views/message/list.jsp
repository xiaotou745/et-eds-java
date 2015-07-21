<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Message"%> 	
<form method="get" class="form-horizontal">
	<table width="100%" border="1" cellspacing="0" cellpadding="0" class="tbstyle">  
				<thead>
		            <tr class="tdbg">
		                <th style="width: 100px;">编码</th>
		                <th style="width: 100px;">推送方式</th>
		                <th style="width: 100px;">消息类型</th>
		                <th style="width: 100px;">发布类型</th>
		                <th style="width: 100px;">消息内容</th>
		                <th style="width: 100px;">添加时间</th>
		                <th style="width: 100px;">发布时间</th>
		                <th style="width: 100px;">消息状态</th>
		                <th style="width: 100px;">操作人</th>
		                <th style="width: 100px;">操作</th>
		            </tr>
		        </thead>
		<%List<Message> data=	(List<Message>)request.getAttribute("DataList");
			 for (int i = 0; i < data.size(); i++) {
				 %> 
		<tr id="<%=data.get(i).getId() %>">
            <td><%=i%></td>
            <td>
            	<%=data.get(i).getPushway() %> 
            </td>
            <td>
            	<%=data.get(i).getMessagetype() %>
            </td>
            <td>
                <%=data.get(i).getSendtype() %>
            </td>
            <td>
            	<%=data.get(i).getContent() %>
            </td>
            <td>
            	<%=data.get(i).getCreatetime() %>
			</td>
            <td>
            	<%=data.get(i).getSendtime() %>
			</td>
            <td>
                <%=data.get(i).getSentstatus() %>
            </td>
            <td>
            	<%=data.get(i).getUpdateby() %>
            <td>
                <a >编辑</a>
                <a >取消发布</a>
            </td>
        </tr>
			 <%}%> 
	</table>
</form>