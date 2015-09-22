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
	GroupBusinessModel detail = (GroupBusinessModel)request.getAttribute("detail");
%>

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
							<label class="col-sm-4 control-label">集团名称：</label>
							<div class="col-sm-4 control-label">
								<label><%=ParseHelper.ToString(detail.getGroupbusiname(), "")%></label>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店名称：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="bizName" id="bizName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-8">
								<button type="button" class="btn btn-primary btn-lg" id="btnSearch">查询</button>
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
		 $.post("<%=basePath%>/groupbusiness/businessbindloglistdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
</script>

