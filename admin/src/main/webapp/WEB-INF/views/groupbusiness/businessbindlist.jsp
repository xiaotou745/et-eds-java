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
	String basePath = PropertyUtils.getProperty("static.admin.url");
	GroupBusinessModel detail = (GroupBusinessModel)request.getAttribute("detail");
%>

<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script
	src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<table class="tbstyle222" border="0"
	style="font-size: 14px; font-weight: bold; line-height: 300%; width: 900px">
	<tr class="trclass">
		<td>集团名称：<%=ParseHelper.ToString(detail.getGroupbusiname(), "")%></td>
		<td>联系电话：<%=ParseHelper.ToString(detail.getLoginname(), "")%></td>
		<td><a href="#">集团余额：<%=detail.getAmount() %></a></td>
		<td><a href="<%=basePath%>/groupbusiness/businessbindloglist?groupBusinessId=<%=detail.getId() %>">绑定记录查询</a></td>
	</tr>
</table>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="groupBusinessId" id="groupBusinessId" value="<%=detail.getId() %>" />
				<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="bizName" id="bizName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">绑定日期:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> 
										<input class="form-control" type="text" name="startDate" id="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
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
										<input class="form-control" type="text"  name="endDate" id="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-8">
								<button type="button" class="btn btn-primary btn-lg" id="btnSearch">查询</button>
								<button type="button" class="btn btn-primary btn-lg" onclick="window.location.href='<%=basePath%>/groupbusiness/businesslist?groupBusinessId=<%=detail.getId()%>'">绑定门店</button>
							</div>
						</div>
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
});

	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize(); 
		 console.log(data);
		 $.post("<%=basePath%>/groupbusiness/businessbindlistdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	function funRemoveBusinessBind(businessId){
		var groupBusinessId = $("#groupBusinessId").val();
        if (!window.confirm("是否解除绑定？")) {
            return;
        }
        var paramaters = {"businessId":businessId,"groupId":groupBusinessId};
        var url = "<%=basePath%>/groupbusiness/removebusinessbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	alert(result.message);
                if (result.responseCode > 0) {
                    window.location.href = "<%=basePath%>/groupbusiness/businessbindlist?groupBusinessId=" + groupBusinessId;
                }
            }
        });
	}
</script>

