<%@page import="java.sql.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">超人电话:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="superManPhone" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">超人姓名:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control"  name="superManName"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户电话:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="businessPhone"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="businessName" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">订单状态:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">发布时间:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="OrderPubStart"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="OrderPubEnd" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选城市:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control">
							</div>
						</div>
					</div>
				</div>
					<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选集团: </label>
							<div class="col-sm-8">
								<input type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">原订单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">订单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;">查询</button>
						<button type="button" class="btn btn-w-m btn-primary" id="btnSave"
							style="margin-left: 3px;">保存修改</button>
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
		 var data=$("#searchForm").serialize();
// 			data.CurrentPage=currentPage;
			$.post("<%=basePath%>/order/listdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
</script>