<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.AppVersion"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%
	UserContext context=UserContext.getCurrentContext(request);
boolean canEdit=context.isHasAuth(73);
boolean canCancel=context.isHasAuth(74);
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
            <th>编号</th>
            <th>客户端类型</th>
            <th>用户类型</th>
            <th>版本号</th>
            <th>是否强制更新</th>
            <th>更新内容</th>
            <th>发布类型</th>
            <th>发布状态</th>
            <th>所属平台</th>
            <th>发布时间</th>
            <th>操作人</th>
            <th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<AppVersion> responsePageList = (PagedResponse<AppVersion>) request.getAttribute("listData");
			List<AppVersion> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<AppVersion>();
			}
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=data.get(i).getPlatform()==1?"Android":"IOS"%></td>
			<td><%=data.get(i).getUsertype()==1?"骑士":"门店"%></td>
			<td><a href="javascript:void;" onclick="ViewDetail('<%=data.get(i).getId()%>',0)"><%=data.get(i).getVersion() %></a></td>
			<td><%=data.get(i).getIsmust()==1?"是":"否"%></td>
			<td title='<%=data.get(i).getMessage()%>'><%=data.get(i).getMessage().length()>15?(data.get(i).getMessage().substring(0, 15)+"..."):data.get(i).getMessage()%></td>
			<td><%=data.get(i).getIstiming()==1?"定时发布":"实时发布"%></td>
			<td><%=data.get(i).getPubstatus()==0?"待发布":(data.get(i).getPubstatus()==1?"已发布":"取消发布")%></td>			
			<td><%=data.get(i).getAppsource()==0?"易代送商户版":"智能调度平台"%></td>		
			<td><%=ParseHelper.ToDateString(data.get(i).getTimingdate())%></td>		
			<td><%=data.get(i).getUpdateby()%></td>		
			<td>
				<% if(data.get(i).getIstiming()==1&&data.get(i).getPubstatus()==0){
				if(canEdit){%>
					  <a href="javascript:void(0)" onclick="ViewDetail('<%=data.get(i).getId()%>',1)">编辑</a>
				<%}
				if(canCancel){%>
					  <a href="javascript:void(0)" onclick="ViewDetail('<%=data.get(i).getId()%>',2)">取消发布</a>
				<%}}%>
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