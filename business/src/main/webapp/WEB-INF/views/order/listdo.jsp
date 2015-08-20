<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<table class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>编号</th>
			<th>订单号</th>
			<th>发单时间</th>
			<th>订单金额</th>
			<th>收货人信息</th>
			<th>骑士信息</th>
			<th>完成时间</th>
			<th>订单状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<OrderListModel> responsePageList = (PagedResponse<OrderListModel>) request.getAttribute("listData");
			List<OrderListModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<OrderListModel>();
			}
			for (int i = 0; i < data.size(); i++) {
										
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=data.get(i).getOrderNo()%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getPubDate())%></td>
			<td><%=data.get(i).getAmount()%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getReceviceAddress())%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getClienterName())%> <br /> 
				<%=ParseHelper.ShowString(data.get(i).getClienterPhoneNo())%>
			</td>
			<td><%=ParseHelper.ToDateString(data.get(i).getActualDoneDate())%></td>
			<td><%=OrderStatus.getEnum(data.get(i).getStatus()).desc()%></td>
			<td><a href="javascript:showMapData('@item.Id')">取消订单</a>
			<a href="javascript:showMapData('@item.Id')">订单详情</a>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.GetPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>