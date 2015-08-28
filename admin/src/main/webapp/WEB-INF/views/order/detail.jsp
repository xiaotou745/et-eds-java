<%@page import="com.edaisong.core.enums.PayStyle"%>
<%@page import="com.edaisong.core.enums.PayStatus"%>
<%@page import="com.edaisong.entity.OrderChild"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.entity.OrderSubsidiesLog"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.enums.PayType"%>
<%@page import="com.edaisong.core.enums.OrderPlatform"%>
<%@page import="java.lang.StringBuilder"%>
<%
	String basePath =PropertyUtils.getProperty("static.admin.url");
    OrderListModel orderListModel=	(OrderListModel)request.getAttribute("orderListModel");
	List<OrderSubsidiesLog> orderSubsidiesLogs=	(List<OrderSubsidiesLog>)request.getAttribute("orderSubsidiesLogs");
%>
<style  type="text/css">
 .trclass {
       text-align:left;
       line-height:35px;
       padding:15px;
       width:auto;
       min-width:800px;
    }

    .trclass  td {
        padding-left:15px;
        width:225px;
    }
    .tbstyle .tdbg th{background:#f6f6f6;height:40px;border:1px solid #dcdcdc;color:#333;
    text-align: center}
</style>
<div style="width: 1000px">
	<%
		String subsidy = "";
		        if (orderListModel.getStatus() == OrderStatus.Complite.value() || orderListModel.getStatus()  == OrderStatus.Delivery.value() 
		        		|| orderListModel.getStatus()  == OrderStatus.Cancel.value())
		        {
		            subsidy = "订单佣金:" + orderListModel.getOrderCommission() + "," + "外送费:" + orderListModel.getDistribSubsidy() 
		            		+ "," + "网站补贴:" + orderListModel.getWebsiteSubsidy();
		        }
		        String payStatus = "";
		        if (orderListModel.getIsPay())
		        {
		            payStatus = "顾客已付款";
		        }
		        else
		        {
		            payStatus = "顾客未付款";
		        }
	%>
	<table class="tbstyle222" border="0"
		style="font-size: 13px; font-weight: bold; line-height: 300%; width: 1000px;">
		<tr class="trclass">
			<td>任务单号：<%=orderListModel.getOrderNo()%>
			</td>
			<td>是否已付款:<%=payStatus%></td>
			<td>订单状态：<%=OrderStatus.getEnum(orderListModel.getStatus()).desc()%></td>
			<td>订单来源：<%=orderListModel.getGroupName()%></td>
		</tr>
		<tr class="trclass">
			<td>发布时间：<%=ParseHelper.ToDateString(orderListModel.getPubDate())%></td>
			<td>商户信息：<%=ParseHelper.ShowString(orderListModel.getBusinessName())%></td>
			<td>发布人电话：<%=ParseHelper.ShowString(orderListModel.getBusinessPhoneNo())%></td>
			<td>发布人地址：<%=ParseHelper.ShowString(orderListModel.getBusinessAddress())%></td>
		</tr>
		<tr class="trclass">
			<td>订单金额：<%=ParseHelper.ShowString(orderListModel.getAmount())%>
			</td>
			<td>订单佣金： <%=ParseHelper.ShowString(orderListModel.getOrderCommission())%></td>
			<td>外送费：<%=ParseHelper.ShowString(orderListModel.getDistribSubsidy())%></td>
			<td>网站补贴： <%=ParseHelper.ShowString(orderListModel.getWebsiteSubsidy())%></td>
		</tr>
		<tr class="trclass">
			<td>收货人电话:<%=ParseHelper.ShowString(orderListModel.getRecevicePhoneNo())%></td>
			<td>收货人地址：<%=ParseHelper.ShowString(orderListModel.getReceviceAddress())%></td>
			<%
				if (orderListModel.getStatus() == OrderStatus.Complite.value() || orderListModel.getStatus()  == OrderStatus.Delivery.value() 
						        		|| orderListModel.getStatus()  == OrderStatus.Cancel.value())
						            {
			%>
			<td>骑士姓名：<%=ParseHelper.ShowString(orderListModel.getClienterTrueName())%></td>
			<td>骑士手机：<%=ParseHelper.ShowString(orderListModel.getClienterPhoneNo())%></td>
			<%
				}
			%>
		</tr>
		<tr class="trclass">
			<td>备注：<%=ParseHelper.ShowString(orderListModel.getRemark())%></td>
			<td>订单数：<%=orderListModel.getOrderCount()%></td>
			<td>审核推荐处理： <%=ParseHelper.ShowString(orderListModel.getDeductCommissionReason())%></td>
			<td>订单是否需要审核：<%=orderListModel.getIsOrderChecked()==1? "是":"否"%></td>
		</tr>
	</table>
	<hr />
	<div style="float: left; width: 1000px;">
		<%
			StringBuilder caiPin = new StringBuilder("");
				            if (orderListModel.getOrderDetailList() != null && orderListModel.getOrderDetailList().size() > 0)
				            {
				                for (int i = 0; i < orderListModel.getOrderDetailList().size(); i++)
				                {
				                    caiPin.append(orderListModel.getOrderDetailList().get(i).getProductname() + "*" + orderListModel.getOrderDetailList().get(i).getQuantity());
				                    if (i > 0 && i != orderListModel.getOrderDetailList().size() )
				                    {
				                        caiPin.append(",");
				                    }
				                }
				            }
		%>
		<lable style="font-size:13px;font-weight:bold;">菜品明细：<%=caiPin.toString()%></lable>
		<table border="0" cellspacing="0" cellpadding="0" class="tbstyle"
			width="1000">
			<thead>
				<tr class="tdbg">
					<th>订单号</th>
					<th>订单金额</th>
					<th>配送费</th>
					<th>支付状态</th>
					<th>支付方式</th>
					<th>支付来源</th>
					<th>交易流水号</th>
					<th>发票</th>
					<th>小票</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<OrderChild> orderChildList =orderListModel.getOrderChildList();
										                    String curPuth = "";
										                    String curPhost ="";
										   for (OrderChild curOrderChild :orderChildList){
											   String bigFileName = "";
											   if (curOrderChild.getTicketurl()!=null&&!curOrderChild.getTicketurl().isEmpty())
									                    {
									                        int fileLastDot = curOrderChild.getTicketurl().lastIndexOf('.');
									                        String fileHandHouZhui = curOrderChild.getTicketurl().substring(fileLastDot, curOrderChild.getTicketurl().length());
									                        bigFileName = curPhost + curPuth + curOrderChild.getTicketurl().substring(0, fileLastDot) + "_0_0" + fileHandHouZhui;
									                    }
				%>
				<tr>
					<td><%=ParseHelper.ShowString(curOrderChild.getChildid())%></td>
					<td><%=ParseHelper.ShowString(curOrderChild.getGoodprice())%></td>
					<td><%=ParseHelper.ShowString(curOrderChild.getDeliveryprice())%></td>
					<td><%=ParseHelper.ShowString(PayStatus.getEnum(curOrderChild.getPaystatus()).desc())%>
					</td>
					<td>
						<%
							if (curOrderChild.getPaytype()!=null && curOrderChild.getPaytype() > 0)
														                       {
						%> <%=ParseHelper.ShowString(PayType.getEnum(curOrderChild.getPaytype()).desc())%>
						<%
							}
						%>
					</td>
					<td>
						<%
							if (curOrderChild.getPaystyle()!=null && curOrderChild.getPaystyle() > 0)
														                   {
						%> <%=ParseHelper.ShowString(PayStyle.getEnum(curOrderChild.getPaystyle()).desc())%>
						<%
							}
						%>
					</td>
					<td></td>
					<td></td>
					<td>
						<%
							if (bigFileName!=null&&!bigFileName.isEmpty())
														                   {
						%> <a href="<%=bigFileName%>">查看</a> <%
 	}
 %>

					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<input type="hidden"
		value="<%=ParseHelper.ShowString(orderListModel.getOrderNo())%>"
		id="OrderNo" /> <input type="hidden"
		value="<%=ParseHelper.ShowString(orderListModel.getId())%>"
		id="OrderId" /> <input type="hidden"
		value="<%=ParseHelper.ShowString(orderListModel.getIsEnable())%>"
		id="IsEnable" /> <input type="hidden"
		value="<%=ParseHelper.ShowString(orderListModel.getDeductCommissionReason())%>"
		id="hidDeductCommissionReason" />
	<hr />
	<div style="float: left; width: 1000px; padding-top: 30px">
		<lable style="font-size:13px;font-weight:bold;">订单流转记录：</lable>
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
					int k = 0; 
						String orderstatus = "";
						String strplatform = ""; 
										 for(OrderSubsidiesLog item :orderSubsidiesLogs){
											 strplatform=OrderPlatform.getEnum(item.getPlatform()).desc();
				%>
				<tr id="<%=item.getId()%>">
					<td><%=ParseHelper.ShowString(OrderStatus.getEnum(item.getOrderstatus()).desc())%></td>
					<td><%=ParseHelper.ShowString(item.getOptname())%></td>
					<td><%=ParseHelper.ToDateString(item.getInserttime())%></td>
					<td><%=ParseHelper.ShowString(item.getRemark())%></td>
					<td><%=ParseHelper.ShowString(strplatform)%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<%
		if (orderListModel.getStatus()!=OrderStatus.Cancel.value()) 
			{
	%>
	<div class="SearchMd" style="float: left">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><input type="button" value="取消订单" class="searchBtn"
					id="btnCancel" /></td>
				<td><input type="button" value="审核通过" class="searchBtn"
					id="btnAuditOk" style="margin-left: 10px;" /> <input type="button"
					value="审核拒绝" class="searchBtn" id="btnAuditCancel"
					style="margin-left: 10px;" /></td>
			</tr>
		</table>
	</div>
	<%
		}
	%>
	<div class="selectSupplierDish">
		<div class="add-openbox add-form" id="OrderOptionShow"
			style="width: 500px">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="input01">操作描述</label>
					<div class="controls">
						<textarea cols="45" rows="5" id="orderOptionLog"></textarea>
						<p class="help-block"></p>
					</div>
				</div>
			</fieldset>
			<p class="btnbox">
				<input value="确认" type="button" id="btnSave" class="yesBtn" /> <input
					value="关闭" type="button" class="J_closebox qxBtn" />
			</p>
		</div>
	</div>

	<div class="selectSupplierDish">
		<div class="add-openbox add-form" id="deductWebSubsidyShow"
			style="width: 500px">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="deductWebSubsidyReason">扣除网站补贴原因</label>
					<div class="controls">
						<textarea cols="45" rows="5" id="deductWebSubsidyReason"></textarea>
						<p class="help-block"></p>
					</div>
				</div>
			</fieldset>
			<p class="btnbox">
				<input value="确认" type="button" id="btnSaveDeductWebSubsidy"
					class="yesBtn" /> <input value="关闭" type="button"
					class="J_closebox qxBtn" />
			</p>
		</div>
	</div>
</div>
<script>
	var adminjs = new adminglass(); //实例化后台类

	$("#btnCancel").click(function() {
		var orderOptionLog = $('#orderOptionLog').val();
		if ($("#IsEnable").val().trim() == "0") {
			alert("已删除的订单不能取消！");
			return false;
		}

		adminjs.openwinbox('#OrderOptionShow');

	});
	//扣除网站补贴
	$("#btnAuditCancel").click(
			function() {
				if ($("#IsEnable").val().trim() == "0") {
					alert("已删除的订单不取扣除网站补贴！");
					return false;
				}

				$("#deductWebSubsidyReason").text(
						$("#hidDeductCommissionReason").val());
				//$("#deductWebSubsidyReason").text(hidDeductCommissionReason.val());
				adminjs.openwinbox('#deductWebSubsidyShow');
			});
	$('.J_closebox').click(function() {
		adminjs.closewinbox('.add-openbox');
		return false;
	});
	$('#btnSave')
			.bind(
					'click',
					function() {
						var orderNo = $('#OrderNo').val();
						var orderId = $('#OrderId').val();
						var orderOptionLog = $('#orderOptionLog').val();
						if ($("#orderOptionLog").val().trim() == "") {
							alert("操作描述不能为空！");
							return false;
						}
						if (confirm("确定要取消该订单吗？")) {
							var paramaters = {
								"orderId" : orderId,
								"OrderOptionLog" : orderOptionLog
							};
							var url = "/Order/CancelOrder";
							$
									.ajax({
										type : 'POST',
										url : url,
										data : paramaters,
										success : function(result) {
											if (result.IsSuccess) {
												alert(result.Message);
												adminjs
														.closewinbox('.add-openbox');
												window.location.href = "/Order/OrderDetail?orderNo="
														+ orderNo
														+ "&orderId="
														+ orderId;
											} else {
												alert(result.Message);
											}
										}
									});
						}
						return true;
					});

	$('#btnSaveDeductWebSubsidy')
			.bind(
					'click',
					function() {
						var orderNo = $('#OrderNo').val();
						var orderId = $('#OrderId').val();
						var orderOptionLog = $('#deductWebSubsidyReason').val();
						if (orderOptionLog.trim() == "") {
							alert("扣除网站补贴原因不能为空！");
							return false;
						}
						if (confirm("确定要扣除该订单网站补贴吗？")) {
							var paramaters = {
								"orderId" : orderId,
								"OrderOptionLog" : orderOptionLog
							};
							var url = "/Order/AuditRefuse";
							$
									.ajax({
										type : 'POST',
										url : url,
										data : paramaters,
										success : function(result) {
											if (result.IsSuccess) {
												alert(result.Message);
												adminjs
														.closewinbox('.add-openbox');
												window.location.href = "/Order/OrderDetail?orderNo="
														+ orderNo
														+ "&orderId="
														+ orderId;
											} else {
												alert(result.Message);
											}
										}
									});
						}
						return true;
					});

	$('#btnAuditOk')
			.bind(
					'click',
					function() {
						if (!window.confirm("是否审核通过？")) {
							return;
						}
						var orderNo = $('#OrderNo').val();
						var orderId = $('#OrderId').val();

						var paramaters = {
							"orderId" : orderId
						};
						var url = "/Order/AuditOK";
						$
								.ajax({
									type : 'POST',
									url : url,
									data : paramaters,
									success : function(result) {
										if (result.IsSuccess) {
											alert("审核成功！");
											window.location.href = "/Order/OrderDetail?orderNo="
													+ orderNo
													+ "&orderId="
													+ orderId;
										} else {
											alert(result.Message);
										}
									}
								});
					});
</script>