<%@page import="com.edaisong.core.enums.PayStyle"%>
<%@page import="com.edaisong.core.enums.PayStatus"%>
<%@page import="com.edaisong.entity.OrderChild"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.ShanSongOrderStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.domain.ShanSongOrderListModel"%>
<%@page import="com.edaisong.entity.OrderSubsidiesLog"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.enums.PayType"%>
<%@page import="com.edaisong.core.enums.SuperPlatform"%>
<%@page import="com.edaisong.core.enums.MealsSettleMode"%>
<%@page import="java.lang.StringBuilder"%>
<%@page import="com.edaisong.core.enums.OrderFrom"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
    ShanSongOrderListModel detail=	(ShanSongOrderListModel)request.getAttribute("orderListModel");
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
				<td>任务单号：<%=detail.getOrderNo()%></td>
				<td>订单状态：<%=ShanSongOrderStatus.getEnum(detail.getStatus()).desc()%></td>
				<td>订单来源：<%=OrderFrom.getEnum(detail.getOrderFrom()).desc()%></td>
			</tr>
			<tr class="trclass">
				<td>发货账号：：<%=detail.getBusinessPhoneNo()%></td>
				<td>距离/重量： <%=ParseHelper.ShowString(detail.getKm())%>公里/<%=ParseHelper.ShowString(detail.getWeight())%>公斤</td>
				<td>配送费：<%=ParseHelper.ShowString(detail.getAmount())%>元</td>
			</tr>
			<tr class="trclass">
				<td>取货码：<%=ParseHelper.ShowString(detail.getPickupCode())%></td>
				<td>收货码：<%=ParseHelper.ShowString(detail.getReceiveCode())%></td>
				<td>小费：<%=ParseHelper.ShowString(detail.getTipAmount())%>元</td>
			</tr>
			<tr class="trclass">
				<td colspan="2">物品信息：<%=ParseHelper.ShowString(detail.getProductName())%></td>
				<td>备注:<%=ParseHelper.ShowString(detail.getRemark())%></td>
			</tr>
		</table>
	</fieldset>
	<hr />
	<fieldset>
		<legend>发货信息</legend>
		<table class="tbstyle222" border="0"
			style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
			<tr class="trclass">
				<td>发货人：<%=ParseHelper.ShowString(detail.getPubName())%></td>
				<td>发货人电话：<%=ParseHelper.ShowString(detail.getPubPhoneNo())%></td>
				<td>发货地址：<%=ParseHelper.ShowString(detail.getPickUpAddress())%></td>
			</tr>
		</table>
	</fieldset>
	<hr />
	<fieldset>
		<legend>收货信息</legend>
		<table class="tbstyle222" border="0"
			style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
			<tr class="trclass">
				<td>收货人：<%=ParseHelper.ShowString(detail.getReceviceName())%></td>
				<td>收货人电话：<%=ParseHelper.ShowString(detail.getRecevicePhoneNo())%></td>
				<td>收货地址:<%=ParseHelper.ShowString(detail.getReceviceAddress())%></td>
			</tr>
		</table>
	</fieldset>
	<hr />
	<fieldset>
		<legend>骑士信息</legend>
		<table class="tbstyle222" border="0"
			style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
			<tr class="trclass">
				<td>骑士姓名：<%=ParseHelper.ShowString(detail.getClienterName())%></td>
				<td>骑士电话：<%=ParseHelper.ShowString(detail.getClienterPhoneNo())%></td>
				<td></td>
			</tr>
		</table>
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
						<td><%=ParseHelper.ShowString(ShanSongOrderStatus.getEnum(item.getOrderstatus()).desc())%></td>
						<td><%=ParseHelper.ShowString(item.getOptname())%></td>
						<td><%=ParseHelper.ToDateString(item.getInserttime())%></td>
						<td><%=ParseHelper.ShowString(item.getRemark())%></td>
						<td><%=SuperPlatform.FastClienter.desc()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</fieldset>
	<fieldset>
	<input type="hidden"
		value="<%=ParseHelper.ShowString(detail.getOrderNo())%>"
		id="OrderNo" /> <input type="hidden"
		value="<%=ParseHelper.ShowString(detail.getId())%>"
		id="OrderId" /> <input type="hidden"
		value="<%=ParseHelper.ShowString(detail.getIsEnable())%>"
		id="IsEnable" />
		<%
		if (detail.getStatus()!=ShanSongOrderStatus.Cancel.value()&&detail.getStatus()!=ShanSongOrderStatus.PayClose.value()) 
		{
	%>
	<div class="SearchMd" style="float: left">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
<!-- 				<td><input type="button" value="取消订单" class="searchBtn" -->
<!-- 					id="btnCancel" /></td> -->
			</tr>
		</table>
	</div>
	<%
		}
	%>
		</fieldset>
		<div tabindex="-1" class="modal inmodal" id="OrderOptionShow"
		role="dialog" aria-hidden="true" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">取消订单</h4>
				</div>
				<small class="font-bold">
					<div class="modal-body">
						<fieldset>
							<br>
							<div class="control-group">
								<label>操作描述：</label>
								<div class="controls">
									<textarea cols="45" rows="5" id="orderOptionLog"></textarea>
								</div>
							</div>
						</fieldset>
					</div>
					<div class="modal-footer">
						<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" type="button" id="btnSave">保存</button>
					</div>
				</small>
			</div>
			<small class="font-bold"> </small>
		</div>
		<small class="font-bold"> </small>
	</div>
</div>
<script>
   //取消订单按钮
	$("#btnCancel").click(function() {
		var orderOptionLog = $('#orderOptionLog').val();
		if ($("#IsEnable").val().trim() == "0") {
			layer.alert('已删除的订单不能取消！', {
			    icon: 2
			});
			return false;
		}
		$('#OrderOptionShow').modal('show');
	});
	//取消订单保存按钮
	$('#btnSave').click(	function() {
		var orderNo = $('#OrderNo').val();
		var orderId = $('#OrderId').val();
		var orderOptionLog = $('#orderOptionLog').val();
		if ($("#orderOptionLog").val().trim() == "") {
			layer.alert('操作描述不能为空！', {
			    icon: 2
			});
			return false;
		}
		layer.confirm('确定要取消该订单吗？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
		//AJAX
		 $.ajax({
                type: "POST",
                url: "http://admin.edaisong.com/order/CancelOrder",
                data: {  
                	"orderId" : orderId,
					"OrderOptionLog" : orderOptionLog,
					"orderNo":orderNo },
                dataType: "jsonp",
                jsonp: "callback",
                success: function (json) {
					alert(json);
                }
            });
		//AJAX END 
		});
		return true;
	});

</script>