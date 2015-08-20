<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>

<%	
String basePath =PropertyUtils.getProperty("static.business.url");
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/dataTables.bootstrap.css">
<div class="top cb">

			<h3 class="cb">
				全部订单
				<p class="fr">
					<input type="text" class="fl" placeholder="订单号，骑士姓名或手机号" id="customerInfo">
					<input type="button" class="fl" value="搜索按钮" id="customerSearch">
				</p>
			</h3>
				<form method="POST" action="#" class="form-horizontal" id="searchForm">
	<input type="hidden" name="CurrentPage" id="_hiddenCurrentPage" value="1"/>
			<div class="function">
				<input type="button" class="fr" value="搜索" id="btnSearch">
				<span class="fl">订单状态</span>
					<%=HtmlHelper.getSelect("orderStatus", EnumHelper.GetEnumItems(OrderStatus.class), "desc", "value",null,"-1","全部","width:155px","fl") %>
				<span class="fl">发单时间</span>
				<label class="fl">
					<input type="radio" name="timeType" value="0" checked="checked">
					今日
				</label>
				<label class="fl">
					<input type="radio" name="timeType"  value="1">
					7天
				</label>
				<label class="fl">
					<input type="radio" name="timeType" value="2">
					30天
				</label>
				<label class="fl">
					<input type="radio" name="timeType" value="3">
					区间
				</label>
				<span class="intime"><input type="text" class="dinput" id="orderPubStart" name="orderPubStart"><s onClick="WdatePicker({el:'orderPubStart',dateFmt:'yyyy-MM-dd'});"></s></span>
				<span class="inblock">至</span>
				<span class="intime"><input type="text" class="dinput" id="orderPubEnd" name="orderPubEnd"><s onClick="WdatePicker({el:'orderPubEnd',dateFmt:'yyyy-MM-dd'});"></s></span>
			</div>
			</form>
		</div>
		<div class="bottom bottom2 bottom3" id="content">
		</div>
<script>
$(document).ready(function () {  
//     $('#orderPubStart').datepicker();
//     $('#orderPubEnd').datepicker();
}); 
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/order/listdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	$("#customerSearch").click(function() {
		if($("#customerInfo").val()!=""){
			var data={"superManPhone":$("#customerInfo").val()};
			var data={"superManName":$("#customerInfo").val()};
			var data={"originalOrderNo":$("#customerInfo").val()};
			$.post("<%=basePath%>/order/listdo",data, function(d) {
				$("#content").html(d);
			});
		}

	});
</script>