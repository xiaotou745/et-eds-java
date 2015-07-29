<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.ResponsePageList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.edaisong.core.common.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 60px;">编号</th>
			<th style="width: 150px;">订单号</th>
			<th style="width: 200px;">商户信息</th>
			<th style="width: 115px;">超人信息</th>
			<th style="width: 100px;">发布时间</th>
			<th style="width: 150px;">送货地址</th>
			<th style="width: 100px;">完成时间</th>
			<th style="width: 150px;">订单明细</th>
		    <th style="width: 150px;">补贴</th>
			<th style="width: 110px;">扣除补贴</th>
			<th style="width: 110px;">商家结算</th>
			<th style="width: 110px;">订单状态</th>
			<th style="width: 110px;">已传/总计</th>
			<th style="width: 110px;">抢单-完成</th>
			<th style="width: 60px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			ResponsePageList<OrderListModel> responsePageList = (ResponsePageList<OrderListModel>) request
					.getAttribute("listData");
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
			<%=data.get(i).getOrderNo()%> <br /> 
				来源:<%=ParseHelper.ShowString(data.get(i).getGroupName())%><br/>原单号:<%=ParseHelper.ShowString(data.get(i).getOriginalOrderNo())%>
		    </td>
			<td><%=ParseHelper.ShowString(data.get(i).getBusinessName())%> <br /> <%=data.get(i).getBusinessPhoneNo()%>
				<br /><%=ParseHelper.ShowString(data.get(i).getPickUpAddress())%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getClienterName())%> <br /> <%=ParseHelper.ShowString(data.get(i).getClienterPhoneNo())%>
			</td>
			<td><%=ParseHelper.ToDateString(data.get(i).getPubDate())%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getReceviceAddress())%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getActualDoneDate())%></td>
			<td>数量：<%=data.get(i).getOrderCount()%><br /> 金额： <font
				style="color: red; font-weight: 600"><%=data.get(i).getAmount()%></font>
				<br /> 佣金：<%=data.get(i).getOrderCommission()%>
			</td>
			<td>
			外送费：<%=data.get(i).getDistribSubsidy()%><br/>
			每单补贴:<%=data.get(i).getWebsiteSubsidy()%><br/>
			任务补贴:<%=data.get(i).getAdjustment()%>
			</td>			
			<%
				BigDecimal butie = BigDecimal.valueOf(0.00);
					if (1 == data.get(i).getIsNotRealOrder()) {
						butie = data.get(i).getOrderCommission()
								.subtract(data.get(i).getRealOrderCommission());
					}

					if (butie.compareTo(BigDecimal.valueOf(0.00)) == 1) // BigDecimal 比较 返回1  代表大于
					{
			%>
			<td style="color: red; font-weight: 600"><%=butie%></td>
			<%
				} else {
			%>
			<td><%=butie%></td>
			<%
				}
			%>
			<td><%=data.get(i).getBusinessCommission()%></td>
			<td><%=data.get(i).getGroupName()%></td>
			<td><%=data.get(i).getHadUploadCount()%>/<%=data.get(i).getOrderCount()%></td>
			<td style="<%=grabToCompleteStyle%>"><%=grabToCompleteStr%></td>
			<td><a href="javascript:showMapData('@item.Id')">地图</a></td>
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