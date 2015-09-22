<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.enums.OrderAuditStatus"%>


<%	
String basePath =PropertyUtils.getProperty("static.admin.url");
%>
<style type="text/css">
#map_contain {
    height: 90%;
    width: 100%;
    max-width: none;
}
label {
    max-width: none;
}

#control {
width: 100%;
}
</style>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
		<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">年份:</label>
							<div class="col-sm-8">
								<select name="settlementYear" class="form-control m-b" id="settlementYear" style="width: 143px">
<!-- 										<option value="-1" selected="selected">全部</option> -->
										<%
										Calendar cal = Calendar.getInstance();
										int nowYear = cal.get(Calendar.YEAR);
										for (int i = nowYear; i > nowYear-5; i--) {
											%>
											<option value="<%=i %>"><%=i %></option>
											<%
										}
										%>
								</select>
						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">月份:</label>
							<div class="col-sm-8">
								<select name="settlementMonth" class="form-control m-b" id="settlementMonth" style="width: 143px">
										<option value="" selected="selected">全部</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">物流公司名称:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control"  name="deliveryName"  id="deliveryName"/>
							</div>
						</div>
					</div>
					
				</div>
		</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
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
			$.post("<%=basePath%>/deliverycompany/statisticslistdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
</script>