<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessModel"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
    GroupBusinessModel detail = (GroupBusinessModel) request.getAttribute("groupBusinessModel");
%>
<table class="tbstyle222" border="0"
	style="font-size: 14px; font-weight: bold; line-height: 300%; width: 900px">
	<tr class="trclass">
		<td>集团名称：<%=ParseHelper.ShowString(detail.getGroupbusiname())%></td>
		<td>登录账号：<%=ParseHelper.ShowString(detail.getLoginname())%></td>
		<td>当前余额：￥<%=detail.getAmount()%></td>
	</tr>  
</table>
 
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="groupBusinessId" id="groupbusinessId" value="<%=detail.getId()%>" />
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">交易类型:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("recordType", EnumHelper
					.GetEnumItems(BusinessBalanceRecordRecordType.class),
					"desc", "value", null, "-1", "全部")%>
							</div>
							
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">任务单号/交易流水号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="relationNo" id="relationNo"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="Businessname" id="businessName"/>
							</div>
						</div>
					</div> 
				</div>
				<div class="row">  
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">交易日期:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> 
										<input type="text" class="form-control" value="" name="startDate" id="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
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
										class="fa fa-calendar"></i></span> 
										<input type="text" class="form-control" value="" name="endDate" id="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<input type="hidden" name="currentPage" id="_hiddenCurrentPage"
						value="1" />  
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
   
<div tabindex="-1" class="modal inmodal" id="RechargeDetail"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">集团充值详情</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							集团名称:<span id="labRechargeBussinessName"></span>
						</div>
						<div class="control-group">
							充值日期: <span id="labRechargeTime"></span>
						</div>
						<div class="control-group">
							充值金额:￥<span id="labRechargeAmount"></span>
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
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize(); 
			$.post("<%=basePath%>/groupbusiness/balancerecordlistdo", data,
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
        var groupbusinessId = $("#groupbusinessId").val();
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
        var url = "<%=basePath%>/groupbusiness/exportgroupbusinessbalancerecord?groupbusinessId=" + groupbusinessId + "&recordType=" + recordType + "&relationNo=" + relationNo + "&startDate=" + startDate + "&endDate=" + endDate;
        window.location.href = url;
        return;
	});	 
	Date.prototype.Format = function(fmt) 
    {  
      var o = { 
        "M+" : this.getMonth()+1,                  
        "d+" : this.getDate(),                     
        "h+" : this.getHours(),                    
        "m+" : this.getMinutes(),                  
        "s+" : this.getSeconds(),                  
        "q+" : Math.floor((this.getMonth()+3)/3), 
        "S"  : this.getMilliseconds()              
      }; 
      if(/(y+)/.test(fmt)) 
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
      for(var k in o) 
        if(new RegExp("("+ k +")").test(fmt)) 
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
      return fmt; 
    }
	function funRechargeDetail(relationNo) {
       var paramaters = { "orderNo": relationNo };
       var url = "<%=basePath%>/groupbusiness/rechargedetail";
       $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (jsonstr) { 
               if (jsonstr != null) {
               	$('#RechargeDetail').modal('show');
                   var strPayType = "";
                   if (jsonstr.paytype=="alipay") {
                       strPayType = "支付宝";
                   } else {
                       strPayType = "";
                   }
                   var strStatus = "";
                   var paytimestr = "";
                   if (jsonstr.paystatus == 0) {
                       strStatus = "待支付";
                   }
                   else if (jsonstr.paystatus == 1) {
                       strStatus = "已支付";
                       paytimestr = new Date(jsonstr.paytime).Format("yyyy-MM-dd hh:mm:ss") ;
                   }else{
                	   strStatus = "异常";
                   }   
                   $('#labRechargeBussinessName').html("<%=ParseHelper.ShowString(detail.getGroupbusiname())%>");
                   $('#labRechargeTime').html(paytimestr);
                   $('#labRechargeAmount').html(jsonstr.payamount);
                   $('#labRechargeWithwardNO').html(jsonstr.originalorderno);
                   $('#labRechargePayType').html(strPayType);
                   $('#labRechargeStatus').html(strStatus);
               }else{
               	alert("请求失败");
               }
           }
       });
       
   }
</script>

