<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.ShanSongOrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.ShanSongOrderStatus"%>

<table class="table table-striped table-bordered table-hover dataTables-example" style="text-align:center;">
	<thead>
		<tr>
			<th style="width: 60px;text-align:center" >编号</th>
			<th style="width: 150px;text-align:center;">订单号</th>
			<th style="width: 200px;text-align:center;">发货账号</th>
			<th style="width: 200px;text-align:center;">发布时间</th>
			<th style="width: 200px;text-align:center;">发货地址</th>
			<th style="width: 200px;text-align:center;">收货地址</th>
			<th style="width: 200px;text-align:center;">费用信息</th>
			<th style="width: 150px;text-align:center;">骑士信息</th>
		    <th style="width: 220px;text-align:center;">取货码</th>
		    <th style="width: 150px;text-align:center;">订单状态</th>
			<th style="width: 60px;text-align:center;">操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<ShanSongOrderListModel> responsePageList = (PagedResponse<ShanSongOrderListModel>) request.getAttribute("listData");
			List<ShanSongOrderListModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<ShanSongOrderListModel>();
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
			<td> <%=ParseHelper.ShowString(data.get(i).getKm())%>公里/<%=ParseHelper.ShowString(data.get(i).getWeight())%>公斤<br/>
			配送费: <%=ParseHelper.ShowString(data.get(i).getAmount())%>元</td>
			<td><%=ParseHelper.ShowString(data.get(i).getClienterName())%> <br/>
			<%=ParseHelper.ShowString(data.get(i).getClienterPhoneNo())%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getPickupCode())%></td>
			<td>	<%=ShanSongOrderStatus.getEnum(data.get(i).getStatus()).desc()%></td>
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