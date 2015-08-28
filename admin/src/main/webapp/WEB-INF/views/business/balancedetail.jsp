<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%
String basePath =PropertyUtils.getProperty("static.admin.url");
BusinessDetailModel detail=	(BusinessDetailModel)request.getAttribute("detail");
double chargeTotalAmount = (double)request.getAttribute("chargeTotalAmount");
%>
<table class="tbstyle222" border="0" style="font-size:14px;font-weight:bold;line-height:300%;width:900px ">
        <tr class="trclass">
            <td>商户名称：<%=ParseHelper.ShowString(detail.getName())%></td>
            <td>电话：<%=ParseHelper.ShowString(detail.getPhoneno()) %></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="trclass">
            <td>当前余额：￥<%=detail.getBalanceprice() %></td>
            <td>累计提款：￥<%=detail.getHaswithdrawprice() %></td>
            <td>累计充值：￥<%=chargeTotalAmount %></td>
            <td></td>
        </tr>
        <tr class="trclass">
            <td>开户行:<%=ParseHelper.ShowString(detail.getOpenBank()) %></td>
            <td>开户支行：<%=ParseHelper.ShowString(detail.getOpenSubBank()) %></td>
            <td>账户名:<%=ParseHelper.ShowString(detail.getTrueName()) %></td>
            <td>银行账号:<%=ParseHelper.toDecrypt(detail.getAccountNo()) %></td>
        </tr>
</table>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">交易类型:</label>
							<div class="col-sm-8">
							<%=HtmlHelper.getSelect("recordType", EnumHelper.GetEnumItems(BusinessBalanceRecordRecordType.class), "desc", "value",null,"-1","全部","","form-control m-b") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">提款单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control"  name="relationNo"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">提款日期:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="orderPubStart" />
                                    </div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
							     <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="orderPubEnd" />
                                    </div>
   						</div>
						</div>
					</div>
				</div>
				<div class="row">
					<input type="hidden" name="CurrentPage" id="_hiddenCurrentPage" value="1"/>
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
					</div>
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnExport
							style="margin-left: 3px;height:30px;">导出</button>
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
			$.post("<%=basePath%>/business/balancedetaillistdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
</script>

