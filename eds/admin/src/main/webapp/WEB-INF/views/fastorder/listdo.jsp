<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.FastOrderModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.enums.OrderFrom"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%	
String basePath =PropertyUtils.getProperty("java.admin.url");

%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 60px;">编号</th>
			<th style="width: 150px;">订单号</th>
			<th style="width: 200px;">门店信息</th>
			<th style="width: 115px;">骑士信息</th>
			<th style="width: 100px;">抢单时间</th>
			<th style="width: 150px;">送货地址</th>
			<th style="width: 100px;">完成时间</th>
			<th style="width: 150px;">订单明细</th>
			<th style="width: 110px;">订单状态</th>
			<th style="width: 60px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<FastOrderModel> responsePageList = (PagedResponse<FastOrderModel>) request
					.getAttribute("listData");
			List<FastOrderModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<FastOrderModel>();
			}
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><a href="<%=basePath%>/fastorder/detail?orderid=<%=data.get(i).getId()%>"><%=data.get(i).getOrderNo()%></a><br /> 
			来源:<%=OrderFrom.getEnum(data.get(i).getOrderFrom()).desc() %>
		    </td>
			<td><%=ParseHelper.ShowString(data.get(i).getBusinessName())%> <br /> 
			<%=data.get(i).getBusinessPhoneNo()%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getClienterName())%> <br /> 
			<%=ParseHelper.ShowString(data.get(i).getClienterPhoneNo())%>
			</td>
			<td><%=ParseHelper.ToDateString(data.get(i).getGrabTime())%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getOrderRegionOneName())+"-"+ParseHelper.ShowString(data.get(i).getOrderRegionTwoName())%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getActualDoneDate())%></td>
			<td>数量：<%=data.get(i).getOrderCount()%><br /> 
				佣金：<%=data.get(i).getOrderCommission()%>
			</td>
			<td>
			<%=OrderStatus.getEnum(data.get(i).getStatus()).desc()%>
			</td>			
			<td><a href="javascript:showMapData('<%=data.get(i).getId()%>')">地图</a></td>
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