<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.ShanSongOrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.ShanSongOrderStatus"%>
<%	
String basePath =PropertyUtils.getProperty("java.admin.url");
%>
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
		    <th style="width: 220px;text-align:center;">收货码</th>
		    <th style="width: 220px;text-align:center;">下单人是否获取过收货码</th>
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
			String IsReceiveCodeStr="否";
			if(data.get(i).getIsreceivecode()==1)
				IsReceiveCodeStr="是";
		%>
		<tr>
			<td><%=i + 1%></td>
			<td>
			<a href="<%=basePath%>/order/shansongdetail?orderno=<%=data.get(i).getOrderNo()%>&orderid=<%=data.get(i).getId()%>"><%=data.get(i).getOrderNo()%></a>
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
			<td><%=ParseHelper.ShowString(data.get(i).getReceiveCode())%></td>
			<td><%=IsReceiveCodeStr%>
			</td>
			<td>	<%=ShanSongOrderStatus.getEnum(data.get(i).getStatus()).desc()%></td>
			<td>  
			<%if(data.get(i).getStatus()!=ShanSongOrderStatus.WaitPay.value()&&data.get(i).getStatus()!=ShanSongOrderStatus.PayClose.value()){%>
			<a href="javascript:showMapData('<%=data.get(i).getId()%>')">地图1</a>
			<%}%>
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