<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.enums.AlipayBatchStatus"%>
<%@page import="com.edaisong.entity.AlipayBatch"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
	String netUrl = PropertyUtils.getProperty("net.admin.url");
	PagedResponse<AlipayBatch> data = (PagedResponse<AlipayBatch>) request.getAttribute("listData");
	
	UserContext context = UserContext.getCurrentContext(request);
	if(context == null){
		response.sendRedirect(basePath);
		return;
	}
%>
<%
	if (data.getResultList() == null || data.getResultList().size() == 0) {
%>
=====暂无数据=====
<%
	} else {
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="%5">批次号</th>
			<th width="%5">总提现金额</th>
			<th width="%5">总提现单数</th>
			<th width="%5">成功单数/失败单数</th>
			<th width="%5">打款时间</th>
			<th width="%5">支付宝回调时间</th>
			<th width="%5">状态</th>
			<th width="%5">操作人</th>
			<th width="%5">操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<AlipayBatch> list = data.getResultList();
				for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><a href="<%=basePath%>/finance/alipaybatchlistdetail?id=<%=list.get(i).getId()%>"><%=list.get(i).getBatchNo()%></a></td>
			<td><%=list.get(i).getTotalWithdraw()%></td>
			<td><%=list.get(i).getOptTimes()%></td>
			<td>
				<%
					if (list.get(i).getStatus() == AlipayBatchStatus.PlayGame.value()) {
				%> -- 
				<%
					} else  if (list.get(i).getFailTimes()==0){
				%> <%=list.get(i).getSuccessTimes()%>/<%=list.get(i).getFailTimes()%>
				<%
					} else  if (list.get(i).getFailTimes()>0){
						%><font style="color:red"> <%=list.get(i).getSuccessTimes()%>/<%=list.get(i).getFailTimes()%></font>
						<%
							}
						%>
			</td>
			<td><%=ParseHelper.ToDateString(list.get(i).getLastOptTime())%></td>
			<td>
				<%
					if (list.get(i).getStatus() == AlipayBatchStatus.PlayGame.value()) {
				%> -- <%
					} else  {
				%> <%=ParseHelper.ToDateString(list.get(i).getCallbackTime())%>
				<%
					}
				%>
			</td>
			
			<td>
			<%
					if (list.get(i).getStatus() == AlipayBatchStatus.PlayGame.value()) {
				%>  <font style="color:red"><%=AlipayBatchStatus.getEnum(list.get(i).getStatus()).desc()%> </font>
				<%
					} else {
				%> <%=AlipayBatchStatus.getEnum(list.get(i).getStatus()).desc()%>
				<%
					}
				%></td>
			<td><%=list.get(i).getLastOptUser()%></td>
			<td>
			<%
					if (list.get(i).getStatus() == AlipayBatchStatus.PlayGame.value()&&ParseHelper.plusDate(list.get(i).getLastOptTime(), 4, 10)
					.compareTo(new Date())<0&&(UserContext.getCurrentContext(request).getLoginName().trim().equals("admin")||
							UserContext.getCurrentContext(request).getLoginName().trim().equals("douhaichao"))) {
				%> <a href="javascript:doSure('<%=list.get(i).getBatchNo()%>')">打款</a> <%
					} else  {
				%>  --
				<%
					}
				%>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%
	}
%>
<%=PageHelper.getPage(data.getPageSize(), data.getCurrentPage(), data.getTotalRecord(), data.getTotalPage())%>