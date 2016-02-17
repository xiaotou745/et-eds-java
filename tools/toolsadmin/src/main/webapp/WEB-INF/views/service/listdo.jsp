<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolsentity.QuartzServiceModel"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.toolscore.enums.AppSource"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
PagedResponse<QuartzServiceModel>  responsePageList = (PagedResponse<QuartzServiceModel>) request.getAttribute("listData");
List<QuartzServiceModel> data = responsePageList.getResultList();
%>


<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 60px;">编号</th>
			<th style="width: 150px;">系统名称</th>
			<th style="width: 150px;">服务名称</th>
			<th style="width: 150px;">请求地址</th>
			<th style="width: 150px;">cron表达式</th>
			<th style="width: 150px;">备注</th>
			<th style="width: 200px;">状态</th>
			<th style="width: 150px;">创建人</th>
			<th style="width: 150px;">创建时间</th>
			<th style="width: 150px;">修改人</th>
			<th style="width: 150px;">修改时间</th>
			<th style="width: 200px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<%
		String name="";
		String cron="";
		String remark="";
		for (int i = 0; i < data.size(); i++) {
			name=new String(Base64.encodeBase64(data.get(i).getName().getBytes("UTF-8"))); 
			cron=new String(Base64.encodeBase64(data.get(i).getExecTime().getBytes("UTF-8"))); 
			remark=new String(Base64.encodeBase64(data.get(i).getRemark().getBytes("UTF-8"))); 
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=AppSource.getEnum(data.get(i).getAppSource()).desc()%></td>
			<td><%=data.get(i).getName()%></td>
			<td><%=data.get(i).getReqUrl()%></td>
			<td><%=data.get(i).getExecTime()%></td>
			<td><%=data.get(i).getRemark()%></td>
			<td><%=data.get(i).getIsStart()==1?"启动":"停止"%></td>
			<td><%=data.get(i).getCreateName()%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getCreateTime())%></td>
			<td><%=data.get(i).getUpdateName()%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getUpdateTime())%></td>
			<td><a href="javascript:void(0)" onclick="updateStatus('<%=data.get(i).getId()%>',<%=data.get(i).getIsStart()%>)">
			<%=data.get(i).getIsStart()==1?"停止":"启动"%></a>
			<%if(data.get(i).getIsStart()==0){%>
				<br/>
				<a href="javascript:void(0)" onclick="modify('<%=data.get(i).getId()%>','<%=name%>','<%=data.get(i).getReqUrl()%>','<%=cron%>','<%=remark%>')">修改</a>	
			<%} %>
			</td>
		</tr>
		<%}%>
		<tr>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>