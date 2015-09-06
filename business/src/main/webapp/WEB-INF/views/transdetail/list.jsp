<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%	
String basePath =PropertyUtils.getProperty("static.business.url");
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/dataTables.bootstrap.css">
<div class="top cb">

			<h3 class="cb">
			交易明细
				<p class="fr">
					<input type="text" class="fl" placeholder="订单号或流水号" id="customerInfo">
					<input type="button" class="fl" value="搜索按钮" id="customerSearch">
				</p>
			</h3>
				<form method="POST" action="#" class="form-horizontal" id="searchForm">
	<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
			<div class="function">
				<input type="button" class="fr" value="搜索" id="btnSearch">
				<span class="fl">交易类型</span>
				<%=HtmlHelper.getSelect("recordType", EnumHelper.GetEnumItems(BusinessBalanceRecordRecordType.class), "desc", "value",null,"-1","全部","width:155px","fl") %>
				<span class="fl">操作时间</span>
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
				<span class="intime"><input type="text"  disabled="disabled" class="dinput" id="startDate" name="startDate"><s onClick="WdatePicker({el:'startDate',dateFmt:'yyyy-MM-dd'});"></s></span>
				<span class="inblock">至</span>
				<span class="intime"><input type="text"  disabled="disabled" class="dinput" id="endDate" name="endDate"><s onClick="WdatePicker({el:'endDate',dateFmt:'yyyy-MM-dd'});"></s></span>
			</div>
			</form>
</div>
		<div class="bottom bottom2 bottom3" id="content">

		</div>
<script>
var searchType=0;
var jss = {
		search : function(currentPage) {
			if(searchType==0){//点击了查询按钮
				$("#_hiddenCurrentPage").val(currentPage);
				 var data=$("#searchForm").serialize();
					$.post("<%=basePath%>/transdetail/listdo",data, function(d) {
						$("#content").html(d);
					});
			}else{//点击了右上角的放大镜查询
				 var data={currentPage:currentPage,search:$("#customerInfo").val()};
					$.post("<%=basePath%>/transdetail/customerlistdo",data, function(d) {
						$("#content").html(d);
					});
			}
		}
	}

jss.search(1);
$("#btnSearch").click(function() {
	searchType=0;
	$("#customerInfo").val("");
	jss.search(1);
});
$("#customerSearch").click(function() {
	searchType=1;
	jss.search(1);
});
$("input[type='radio']").click(function() {
	var selected=$('input[name="timeType"]:checked').val();
	if(selected!="3"){
		$("#startDate").attr("disabled","disabled");
		$("#endDate").attr("disabled","disabled");
		$("#startDate").val("");
		$("#endDate").val("");
	}else{
		$("#startDate").removeAttr("disabled");
		$("#endDate").removeAttr("disabled");
	}
});
</script>