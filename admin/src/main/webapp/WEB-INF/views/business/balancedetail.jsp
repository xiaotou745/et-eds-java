<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
	BusinessDetailModel detail = (BusinessDetailModel) request
			.getAttribute("detail");
	double chargeTotalAmount = (double) request
			.getAttribute("chargeTotalAmount");
%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script
	src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<table class="tbstyle222" border="0"
	style="font-size: 14px; font-weight: bold; line-height: 300%; width: 900px">
	<tr class="trclass">
		<td>商户名称：<%=ParseHelper.ShowString(detail.getName())%></td>
		<td>电话：<%=ParseHelper.ShowString(detail.getPhoneno())%></td>
		<td></td>
		<td></td>
	</tr>
	<tr class="trclass">
		<td>当前余额：￥<%=detail.getBalanceprice()%></td>
		<td>累计提款：￥<%=detail.getHaswithdrawprice()%></td>
		<td>累计充值：￥<%=chargeTotalAmount%></td>
		<td></td>
	</tr>
	<tr class="trclass">
		<td>开户行:<%=ParseHelper.ShowString(detail.getOpenBank())%></td>
		<td>开户支行：<%=ParseHelper.ShowString(detail.getOpenSubBank())%></td>
		<td>账户名:<%=ParseHelper.ShowString(detail.getTrueName())%></td>
		<td>银行账号:<%=ParseHelper.toDecrypt(detail.getAccountNo())%></td>
	</tr>
</table>

<input type="hidden" id="businessName" value="<%=ParseHelper.ShowString(detail.getName())%>"/>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">交易类型:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("recordType", EnumHelper
					.GetEnumItems(BusinessBalanceRecordRecordType.class),
					"desc", "value", null, "-1", "全部", "", "form-control m-b")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">提款单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="relationNo" id="relationNo" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">提款日期:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> <input type="text"
										class="form-control" value="" name="startDate" id="startDate"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> <input type="text"
										class="form-control" value="" name="endDate" id="endDate"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<input type="hidden" name="CurrentPage" id="_hiddenCurrentPage"
						value="1" /> <input type="hidden" name="businessID"
						id="businessID" value="<%=detail.getId()%>" />
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
							style="margin-left: 3px; height: 30px;">查询</button>
					</div>
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnExport"
							style="margin-left: 3px; height: 30px;">导出</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>


<div tabindex="-1" class="modal inmodal" id="WithdrawFormShow"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">商户提款详情</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							提现日期:<span id="labWithdrawTime"></span>
						</div>
						<div class="control-group">
							提现状态:<span id="labWithdrawStatus"></span>
						</div>
						<div class="control-group">
							交易流水号:<span id="labWithdrawNo"></span>
						</div>
						<div class="control-group">
							提现金额: ￥<span id="labAmount"></span>
						</div>
						<div class="control-group">
							提款商铺:<span id="labBusinessName"></span>
						</div>
						<div class="control-group">
							商铺电话:<span id="labBusinessPhoneNo"></span>
						</div>
						<div class="control-group">
							开户银行:<span id="labBusinessOpenBank"></span>
						</div>
						<div class="control-group">
							开户支行: <span id="labBusinessOpenSubBank"></span>
						</div>
						<div class="control-group">
							账户名:<span id="labTrueName"></span>
						</div>
						<div class="control-group">
							银行卡号:<span id="labAccountNo"></span>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>

<div tabindex="-1" class="modal inmodal" id="RechargeDetail"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">商户充值详情</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							商铺:<span id="labRechargeBussinessName"></span>
						</div>
						<div class="control-group">
							充值日期: <span id="labRechargeTime"></span>
						</div>
						<div class="control-group">
							充值金额:￥<span id="labRechargeAmount"></span>
						</div>
						<div class="control-group">
							充值后余额: ￥<span id="labRechargeBalance"></span>
						</div>
						<div class="control-group">
							交易流水号: <span id="labRechargeWithwardNO"></span>
						</div>
						<div class="control-group">
							支付方式: <span id="labRechargePayType"></span>
						</div>
						<div class="control-group">
							交易状态:<span id="labRechargeStatus"></span>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>


<script>
 $(function(){
	  $(' .input-group.date').datepicker({
          todayBtn: "linked",
          keyboardNavigation: false,
          forceParse: false,
          calendarWeeks: true,
          autoclose: true
      });
 });
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/business/balancedetaillistdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	//导出
	$("#btnExport").click(function(){
        var businessId = $("#businessID").val();
        var recordType = $("#recordType").val();
        var relationNo = $("#relationNo").val();
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if (startDate == "" || endDate == "") {
            alert("请输入时间范围!");
            return;
        }
        if((Date.parse(endDate) - Date.parse(startDate)) < 0){
        	alert("结束时间必须大于开始时间!");
            return;
        }
        var url = "<%=basePath%>/business/exportbusinessbalancerecord?businessId=" + businessId + "&recordType=" + recordType + "&relationNo=" + relationNo + "&startDate=" + startDate + "&endDate=" + endDate;
        window.location.href = url;
        return;
	});	

	function funLookDetail(withwardId) {
		 var paramaters = { "withwardId": withwardId };
	     var url = "<%=basePath%>/businesswithdrawform/businesswithdrawdetail";
	     $.ajax({
            type: 'POST',
            async:true,
            url: url,
            data: paramaters,
            success: function (jsonstr) {
                if (jsonstr != null) {
                	$('#WithdrawFormShow').modal('show');
                    var withdrawTime = jsonstr.withdrawtime;
                    var strstatus = "";
                    if (jsonstr.status == 1) {
                        strstatus = "待审核";
                    }
                    else if (jsonstr.status == 2) {
                        strstatus = "审核通过";
                    }
                    else if (jsonstr.status == 3) {
                        strstatus = "打款完成";
                    }
                    else if (jsonstr.status == -1) {
                        strstatus = "审核拒绝";
                    }
                    else if (jsonstr.status == -2) {
                        strstatus = "打款失败";
                    }
                    $('#labWithdrawTime').html(withdrawTime);
                    $('#labWithdrawStatus').html(strstatus);
                    $('#labWithdrawNo').html(jsonstr.withwardno);
                    $('#labAmount').html(jsonstr.amount);
                    $('#labBusinessName').html(jsonstr.businessName);
                    $('#labBusinessPhoneNo').html(jsonstr.businessPhoneNo);
                    $('#labBusinessOpenBank').html(jsonstr.openbank);
                    $('#labBusinessOpenSubBank').html(jsonstr.opensubbank);
                    $('#labTrueName').html(jsonstr.truename);
                    $('#labAccountNo').html(jsonstr.accountno);
                }else{
                	alert("请求失败");
                }
            }
        });
	}
	
	function funRechargeDetail(relationNo) {
        var paramaters = { "orderNo": relationNo };
        var url = "<%=basePath%>/business/rechargedetail";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (jsonstr) {
                if (jsonstr != null) {
                	$('#RechargeDetail').modal('show');
                    var strPayType = "";
                    if (jsonstr.paytype==1) {
                        strPayType = "支付宝";
                    } else if (jsonstr.paytype == 2) {
                        strPayType = "微信";
                    }
                    var strStatus = "";
                    if (jsonstr.paytype == 0) {
                        strStatus = "待支付";
                    }
                    else if (jsonstr.paytype == 1) {
                        strStatus = "已支付";
                    }
                    $('#labRechargeBussinessName').html(jsonstr.name);
                    $('#labRechargeTime').html(jsonstr.paytime);
                    $('#labRechargeAmount').html(jsonstr.amount);
                    $('#labRechargeBalance').html(jsonstr.balance);
                    $('#labRechargeWithwardNO').html(jsonstr.orderno);
                    $('#labRechargePayType').html(strPayType);
                    $('#labRechargeStatus').html(strStatus);
                }else{
                	alert("请求失败");
                }
            }
        });
    }
</script>

