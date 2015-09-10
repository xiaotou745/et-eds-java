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
%>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="groupBusinessId" id="groupBusinessId" value="" />
								<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">集团名称：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="bizName" id="bizName" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="bizName" id="bizName" />
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
	
	function funAddBusinessBind(businessId){
		var groupBusinessId = $("#groupBusinessId").val();
        if (!window.confirm("是否绑定？")) {
            return;
        }
        var paramaters = {"businessId":businessId,"groupBusinessId":groupBusinessId};
        var url = "<%=basePath%>/groupbusiness/addbusinessbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	alert(result.message);
                if (result.responseCode > 0) {
                    window.location.href = "<%=basePath%>/groupbusiness/businesslist?groupBusinessId=" + groupBusinessId;
                }
            }
        });
	}
</script>

