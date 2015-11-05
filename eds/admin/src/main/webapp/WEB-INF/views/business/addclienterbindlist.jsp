<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
	BusinessDetailModel detail = (BusinessDetailModel) request
			.getAttribute("detail");
%>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="currentPage" id="_hiddenCurrentPage"
					value="1" /> <input type="hidden" name="businessId"
					id="businessId" value="<%=detail.getId()%>" />
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户名称:</label>
							<label class="col-sm-4 control-label">
								<%=ParseHelper.ShowString(detail.getName())%>
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">骑士姓名:</label>
							<div class="col-sm-8">
								<input id="clienterName" class="form-control" type="text"
									name="clienterName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">骑士电话:</label>
							<div class="col-sm-8">
								<input id="clienterPhone" class="form-control" type="text"
									name="clienterPhone" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary"
							id="btnSearch" style="margin-left: 3px; height: 30px;">查询</button>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-8">
								<a onclick="window.location.href='<%=basePath%>/business/addclienterbindlist?businessId=<%=detail.getId()%>'">手动绑定</a>
								<a  onclick="window.location.href='<%=basePath%>/business/clienterbatchbind?businessId=<%=detail.getId()%>'">批量绑定</a>
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
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
		 $.post("<%=basePath%>/business/addclienterbindlistdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	function funAddClienterBind(clienterId){
		var businessId = $("#businessId").val();
        if (!window.confirm("是否添加绑定？")) {
            return;
        }
        var paramaters = {"businessId":businessId,"clienterId":clienterId};
        var url = "<%=basePath%>/business/addclienterbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	alert(result.message);
                if (result.responseCode > 0) {
                    window.location.href = "<%=basePath%>/business/clienterbindlist?businessId=" + businessId;
                }
            }
        });
	}
</script>

