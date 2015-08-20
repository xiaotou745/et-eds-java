<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>

<%	
String basePath =PropertyUtils.getProperty("static.business.url");
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/dataTables.bootstrap.css">
<div class="center">
		<div class="top cb">
			<h3 class="cb">
				交易明细
			</h3>
			<div class="function">
				<input type="button" class="fr" value="搜索" id="btnSerach">
					<span class="fl">时间</span>
				<span class="intime"><input type="text" name="d" class="dinput" id="startDate" ><s onClick="WdatePicker({el:'startDate',dateFmt:'yyyy-MM-dd'});"></s></span>
				<span class="inblock">至</span>
				<span class="intime"><input type="text" name="d2" class="dinput" id="endDate" ><s onClick="WdatePicker({el:'endDate',dateFmt:'yyyy-MM-dd'});"></s></span>
				<span class="fl">交易类型</span>
				<select class="fl" id="transTypeSelect">
					<option value="0">全部</option>
					<option value="1">发布订单</option>
					<option value="2">取消订单</option>
					<option value="8">订单菜品费</option>
					<option value="9">充值</option>
					<option value="11">手续费</option>
				</select>
				<select class="fl" id="numTypeSelect">
				<option value="0">单号类型</option>
				<option value="1">订单号</option>
				<option value="2">流水号</option>
				</select>
				<span class="fl">单号/流水</span>
				<input type="text" placeholder="订单号，流水号" class="dinput"  id="numString" >
			</div>
		</div>
		<div class="bottom bottom2 bottom3" id="content">

		</div>
	</div>
<script>
var jss = {
		search : function(currentPage) {
			var paramaters={
					startDate:$('#startDate').val(),
					endDate:$('#endDate').val(),
					transType:$('#transTypeSelect option:selected').val(),
					numType:$('#numTypeSelect option:selected').val(),
					numString:$('#numString').val(),
					currentPage:currentPage
					}
			$.post("<%=basePath%>/transdetail/listdo",paramaters, function(d) {
				$("#content").html(d);
			});
		}
	}
$(function(){
	//alert(<%=basePath%>);
	//设置文本框不可用
	$('#numString').attr('disabled','disabled');
	//单号类型改变事件
	$('#numTypeSelect').change(function(){
		var numtype=$('#numTypeSelect option:selected').val();
		if(numtype==0){
			$('#numString').attr('disabled','disabled');
		}
		else{
			$('#numString').removeAttr('disabled');
		}
	});
	$('#btnSerach').click(function(){
		jss.search(1);
	});
	jss.search(1);;
});

</script>