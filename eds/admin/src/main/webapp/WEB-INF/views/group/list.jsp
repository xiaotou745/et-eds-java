<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.Group"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">第三方平台名称:</label>
							<div class="col-sm-8">
							  <input type="text" class="form-control" value="" name="groupName"  id="txtGroupName"/>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">第三方平台AppKey:</label>
							<div class="col-sm-8">
							  <input type="text" class="form-control" value="" name="appKey"  id="txtAppkey"/>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
					   <button type="button" class="btn btn-w-m btn-primary" id=btnAdd
							style="margin-left: 3px;height:30px;">新增第三方平台</button>
					</div>
					<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
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
			$.post("<%=basePath%>/group/selectlist",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
</script>
