
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.entity.OrderGrabChild"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.domain.FastOrderDetail"%>
<%@page import="com.edaisong.entity.OrderSubsidiesLog"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.enums.OrderPlatform"%>
<%@page import="com.edaisong.core.enums.OrderFrom"%>
<%@page import="java.lang.StringBuilder"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
FastOrderDetail detailModel=	(FastOrderDetail)request.getAttribute("detailModel");
List<OrderGrabChild> orderChildList = detailModel.getOrderChilds();
	List<OrderSubsidiesLog> orderSubsidiesLogs=	(List<OrderSubsidiesLog>)request.getAttribute("orderSubsidiesLogs");
%>
<style type="text/css">
.trclass {
	text-align: left;
	line-height: 35px;
	padding: 15px;
	width: auto;
	min-width: 800px;
}

.trclass  td {
	padding-left: 15px;
	width: 225px;
}

.tbstyle .tdbg th {
	background: #f6f6f6;
	height: 40px;
	border: 1px solid #dcdcdc;
	color: #333;
	text-align: center
}
</style>
<div style="width: 1000px">
	<fieldset>
		<legend>任务信息</legend>
		<table class="tbstyle222" border="0"
			style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
			<tr class="trclass">
				<td>任务单号：<%=detailModel.getGraborderno()%></td>
				<td>是否已付款:顾客已付款</td>
				<td>订单状态：<%=OrderStatus.getEnum(detailModel.getStatus()).desc()%></td>
				<td>订单来源：<%=OrderFrom.getEnum(detailModel.getOrderFrom()).desc()%></td>
			</tr>
			<tr class="trclass">
				<td>订单数量：<%=detailModel.getOrdercount()%></td>
				<td>订单总佣金： <%=ParseHelper.ShowString(detailModel.getOrderCommission())%></td>
				<td>配送费：<%=ParseHelper.ShowString(orderChildList.get(0).getDistribsubsidy())%>/单</td>
				<td>网站补贴： <%=ParseHelper.ShowString(orderChildList.get(0).getWebsiteSubsidy())%>/单</td>
			</tr>
			<tr class="trclass">
				<td>订单是否需要审核：否</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</fieldset>
	<hr />
	<fieldset>
		<legend>商户信息</legend>
		<table class="tbstyle222" border="0"
			style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
			<tr class="trclass">
				<td>商户名称：<%=ParseHelper.ShowString(detailModel.getBusinessName())%></td>
				<td>商户电话：<%=ParseHelper.ShowString(detailModel.getBusinessPhoneNo())%></td>
				<td>联系电话：<%=ParseHelper.ShowString(detailModel.getBusinessPhoneNo2())%></td>
				<td>商户地址：<%=ParseHelper.ShowString(detailModel.getBusinessAddress())%></td>
			</tr>
		</table>
	</fieldset>
	<hr />
	<fieldset>
		<legend>收货信息</legend>
		<table class="tbstyle222" border="0"
			style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
			<tr class="trclass">
				<td>收货地址：<%=ParseHelper.ShowString(detailModel.getOrderRegionOneName())+ "-"+ ParseHelper.ShowString(detailModel.getOrderRegionTwoName())%></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</fieldset>
	<hr />
	<fieldset>
		<legend>骑士信息</legend>
		<table class="tbstyle222" border="0"
			style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
			<tr class="trclass">
				<td>骑士姓名：<%=ParseHelper.ShowString(detailModel.getClienterName())%></td>
				<td>骑士电话：<%=ParseHelper.ShowString(detailModel.getClienterPhoneNo())%></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</fieldset>
	<hr />
	<fieldset>
		<legend>订单列表</legend>
		<div style="float: left; width: 1000px;">

			<table border="0" cellspacing="0" cellpadding="0" class="tbstyle"
				width="1000">
				<thead>
					<tr class="tdbg">
						<th>单号</th>
						<th>订单状态</th>
						<th>完成时间</th>
						<th>完成位置</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (OrderGrabChild curOrderChild : orderChildList) {
					%>
					<tr>
						<td><%=ParseHelper.ShowString(curOrderChild.getChildid())%></td>
						<td><%=ParseHelper.ShowString(OrderStatus.getEnum(
						curOrderChild.getStatus()).desc())%></td>
						<td><%=ParseHelper.ToDateString(curOrderChild.getActualdonedate())%></td>
						<td><%=curOrderChild.getDonelatitude() + "_"+ curOrderChild.getDonelatitude()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</fieldset>
	<hr />
	<fieldset>
		<legend>订单操作记录</legend>
		<div style="float: left; width: 1000px; padding-top: 30px">
			<table border="0" cellspacing="0" cellpadding="0" class="tbstyle"
				width="1000">
				<thead>
					<tr class="tdbg">
						<th>操作类型</th>
						<th>操作人</th>
						<th width="150">操作时间</th>
						<th width="500">操作描述</th>
						<th>操作平台</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (OrderSubsidiesLog item : orderSubsidiesLogs) {
					%>
					<tr id="<%=item.getId()%>">
						<td><%=ParseHelper.ShowString(OrderStatus.getEnum(
						item.getOrderstatus()).desc())%></td>
						<td><%=ParseHelper.ShowString(item.getOptname())%></td>
						<td><%=ParseHelper.ToDateString(item.getInserttime())%></td>
						<td><%=ParseHelper.ShowString(item.getRemark())%></td>
						<td><%=OrderPlatform.getEnum(item.getPlatform()).desc()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</fieldset>
</div>
