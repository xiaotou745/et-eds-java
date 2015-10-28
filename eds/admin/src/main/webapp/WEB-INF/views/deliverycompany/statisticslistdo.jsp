<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.DeliveryStatistics"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%	
String basePath =PropertyUtils.getProperty("java.admin.url");
String adminPath =PropertyUtils.getProperty("net.admin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 60px;">编号</th>
			<th style="width: 150px;">物流公司名称</th>
			<th style="width: 200px;">物流公司Code</th>
			<th style="width: 115px;">骑士结算金额</th>
			<th style="width: 150px;">物流公司结算金额</th>
			<th style="width: 150px;">任务量</th>
			<th style="width: 100px;">订单量</th>
			<th style="width: 150px;">订单金额</th>
		    <th style="width: 150px;">结算周期</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<DeliveryStatistics> responsePageList = (PagedResponse<DeliveryStatistics>) request
					.getAttribute("listData");
			List<DeliveryStatistics> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<DeliveryStatistics>();
			}
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=data.get(i).getDeliveryCompanyName()%></td>
			<td><%=data.get(i).getDeliveryCompanyCode()%> </td>
			<td><%=data.get(i).getClienterTotalSettleMoney()%></td>
			<td><%=data.get(i).getCompanyTotalSettleMoney()%></td>
			<td><a class="blue2" href="<%=adminPath%>/DeliveryManager/OrderManager?deliverID=<%=data.get(i).getDeliveryCompanyID()%>"><%=data.get(i).getTaskTotalNum()%></a></td>
			<td><%=data.get(i).getOrderTotalNum()%></td>
			<td><%=data.get(i).getAmountTotalNum()%></td>
			<td><%=data.get(i).getAuditDate()%></td>
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