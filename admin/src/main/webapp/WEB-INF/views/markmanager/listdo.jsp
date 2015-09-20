
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="com.edaisong.entity.ClienterForzen"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.Mark" %>
<%String basePath = PropertyUtils.getProperty("static.admin.url");%>
<%PagedResponse<Mark> data = (PagedResponse<Mark>) request.getAttribute("listData");%>
<% if(data.getResultList()==null||data.getResultList().size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">编号</th>
						<th width="%5">名称</th>
						<th width="%5">标签类型</th>
						<th width="%5">数量</th>
						<th width="%5">创建时间</th>
						<th width="%5">状态</th>
						<th width="%5">操作人</th>
						<th width="%5">操作时间</th>
						<th width="%5">备注</th>
						<th width="%5">操作</th>	
				</tr>
			</thead>
			<tbody>                           
		<%List<Mark> list = data.getResultList();
           for (int i = 0; i < list.size(); i++) {%>  
			 <tr> 
			    <td><%=i+1 %></td>
                <td><%=list.get(i).getTagName() %></td>
				<td><%=list.get(i).getTagType()==0?"门店":"骑士" %></td>
				<td><%=list.get(i).getBindquantity() %></td>
				<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime()) %></td>
                <td><%=list.get(i).getIsenable()==0?"禁止":"启动" %></td>
                <td><%=list.get(i).getModifyname() %></td>
				<td><%=ParseHelper.ToDateString(list.get(i).getModifytime()) %></td>
                <td><%=list.get(i).getRemark() %></td>
				<td><a href="javascript:showEditTag(<%=list.get(i).getId()%>,<%=list.get(i).getIsenable()%>)">修改</a></td>				
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script>
	function showEditTag(id,isEnable){
		$("#hdTagId").val(id); 
		$("#hdIsEnable").val(isEnable);
		$('#showEditTag').modal('show');
	}


</script>

