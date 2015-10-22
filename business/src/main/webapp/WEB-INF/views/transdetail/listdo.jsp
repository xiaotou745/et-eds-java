<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.BusinessBalanceRecord"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordStatus"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
PagedResponse<BusinessBalanceRecord> responsePageList = (PagedResponse<BusinessBalanceRecord>) request.getAttribute("result");
 String basePath = PropertyUtils.getProperty("java.business.url");
 
%>
			<table width="100%" class="stripe">
				<tr>
					<td>交易类型</td>
					<td>订单号/流水号</td>
					<td>收支金额</td>
					<td>余额</td>
					<td>状态</td>
					<td>操作时间</td>
				</tr>
				<%for(int i=0;i<responsePageList.getResultList().size();i++)
				{%>
				<tr>
					<td><%=BusinessBalanceRecordRecordType.getEnum((int)responsePageList.getResultList().get(i).getRecordtype()).desc() %></td>
					<td><a class="blue2" href="<%=basePath%>/order/detail?orderno=<%=responsePageList.getResultList().get(i).getRelationno()%>"><%=responsePageList.getResultList().get(i).getRelationno()%></a></td>
					<td>￥<%=responsePageList.getResultList().get(i).getAmount() %></td>
					<td>￥<%=responsePageList.getResultList().get(i).getBalance() %></td>
					<td><%=BusinessBalanceRecordStatus.getEnum((int)responsePageList.getResultList().get(i).getStatus()).desc()%></td>
					<td><%= ParseHelper.ToDateString(responsePageList.getResultList().get(i).getOperatetime())  %></td>
					<!--  <td>待抢单</td>
					<td>
						<a href="javascript:;" class="red2">取消订单</a>
						<a href="javascript:;" class="blue2">订单详情</a>
					</td>-->
				</tr>
					<%}%>
			</table>
			<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>
