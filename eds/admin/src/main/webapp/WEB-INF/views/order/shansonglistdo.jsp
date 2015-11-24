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
<%@page import="com.edaisong.core.enums.OrderAuditStatus"%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 60px;">编号</th>
			<th style="width: 150px;">订单号</th>
			<th style="width: 200px;">发货账号</th>
			<th style="width: 200px;">发布时间</th>
			<th style="width: 200px;">发货地址</th>
			<th style="width: 200px;">收货地址</th>
			<th style="width: 200px;">费用信息</th>
			<th style="width: 150px;">骑士信息</th>
		    <th style="width: 150px;">取货码</th>
		    <th style="width: 150px;">订单状态</th>
			<th style="width: 60px;">操作</th>
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
				double distance = data.get(i).getGrabToCompleteDistance();
				String grabToCompleteStyle = "";
				String grabToCompleteStr = "";
				if (distance == -1)//抢单和完成的坐标有一个未知时
				{
					grabToCompleteStyle = "color:black";
					grabToCompleteStr = "未知";
				} else if (distance == 0.0)//抢单和完成的坐标重合
				{
					grabToCompleteStyle = "color:red;font-weight:900";
					grabToCompleteStr = "重合";
				} else //抢单和完成的坐标不重合
				{
					grabToCompleteStyle = "color:green";
					grabToCompleteStr = "不重合";
				}
				int diffHour = 0;
				String val = diffHour > 10 && data.get(i).getStatus() == 0 ? "red"
						: diffHour > 8 && data.get(i).getStatus() == 0 ? "blue"
								: diffHour > 5 && data.get(i).getStatus() == 0 ? "yellow"
										: "none";
		%>
		<tr>
			<td><%=i + 1%></td>
			<td>
			<%=data.get(i).getOrderNo()%>
		    </td>
			<td><%=ParseHelper.ShowString(data.get(i).getBusinessPhoneNo())%> 
			<td><%=ParseHelper.ToDateString(data.get(i).getPubDate())%>
			</td>
			<td><%=ParseHelper.ShowString(data.get(i).getPickUpAddress())%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getReceviceAddress())%></td>
			<td></td>
			<td><%=ParseHelper.ShowString(data.get(i).getClienterName())%>,
			<%=ParseHelper.ShowString(data.get(i).getClienterPhoneNo())%></td>
			<td></td>
			<td>	<%=OrderStatus.getEnum(data.get(i).getStatus()).desc()%></td>
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