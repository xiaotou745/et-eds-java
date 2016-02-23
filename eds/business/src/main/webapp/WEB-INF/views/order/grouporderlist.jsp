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
String basePath =PropertyUtils.getProperty("java.business.url");
%>
<div class="top cb">

			<h3 class="cb">
				外卖订单
				<p class="fr">
					<input type="text" class="fl" placeholder="订单号，骑士姓名或手机号" id="customerInfo">
					<input type="button" class="fl" value="搜索按钮" id="customerSearch">
				</p>
			</h3>
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
	        <input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
			<div class="function">
				<input type="button" class="fr" value="导出报表" id="btnExport" style="line-height:30px;">
				<input type="button" class="fr" value="搜索" id="btnSearch" style="line-height:30px;">
				<span class="fl">订单状态</span>
				<select class="fl"  name="orderStatus"  id="orderStatus">
					<option value="-1">全部</option>
					<option value="0">待接单</option>
					<option value="2">取货中</option>
					<option value="4">送货中</option>
					<option value="1">已完成</option>
					<option value="3">已取消</option>
				</select>
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
				<span class="intime">
				<input type="text" disabled="disabled" class="dinput" id="orderPubStart" name="orderPubStart" />
				<s onClick="WdatePicker({el:'orderPubStart',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'orderPubEnd\')||\'new Date()\'}'});"></s></span>
				<span class="inblock">至</span>
				<span class="intime"><input type="text" class="dinput" disabled="disabled" id="orderPubEnd" name="orderPubEnd">
				<s onClick="WdatePicker({el:'orderPubEnd',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'orderPubStart\')}',maxDate:new Date()});"></s></span>
			</div>
			</form>
</div>
		<div class="bottom bottom2 bottom3" id="content">
		</div>
<script>

var searchType=0;
var jss = {
	search : function(currentPage) {
		
		var date1=$("#orderPubStart").val();
		var date2=$("#orderPubEnd").val();
		if(date1!=null&&date2!=null&&date1!=""&&date2!=""){
		    var date11=new Date(date1);
		 	var date22=new Date(date2);
		 	var date1Seconds=new Date(date11.getFullYear(),date11.getMonth()+2,date11.getDate());
		 	var date2Seconds=new Date(date22.getFullYear(),date22.getMonth(),date22.getDate());
		    if(date1Seconds<date2Seconds)
		    {
		    	alert("查询开始日期与结束日期最大间隔为两个月！")
		    	return;
		    }
		} 
		if(searchType==0){//点击了查询按钮
			$("#_hiddenCurrentPage").val(currentPage);
			 var data=$("#searchForm").serialize();
				$.post("<%=basePath%>/order/grouporderlistdo",data, function(d) {
					$("#content").html(d);
				});
		}else{//点击了右上角的放大镜查询
			 var data={currentPage:currentPage,search:$("#customerInfo").val()};
				$.post("<%=basePath%>/order/customerlistdo",data, function(d) {
					$("#content").html(d);
				});
		}

	}
}
jss.search(1);
$("#btnSearch").click(function() {
	searchType=0;
	$("#customerInfo").val("");
	var startDate = $('#orderPubStart').val();
    var endDate = $('#orderPubEnd').val();
    if (startDate != "" && endDate != "") {
        var intStartDate = startDate.replace(/-/g, "");
        var intEndDate = endDate.replace(/-/g, "");
        if (intStartDate > intEndDate) {
            alert('开始日期不能大于结束日期');
            $('#orderPubStart').val("");
            return;
        }
    }
	jss.search(1);
});
$("#customerSearch").click(function() {
	searchType=1;
	jss.search(1);
});
$("input[type='radio']").click(function() {
	var selected=$('input[name="timeType"]:checked').val();
	if(selected!="3"){
		$("#orderPubStart").attr("disabled","disabled");
		$("#orderPubEnd").attr("disabled","disabled");
		$("#orderPubStart").val("");
		$("#orderPubEnd").val("");
	}else{
		$("#orderPubStart").removeAttr("disabled");
		$("#orderPubEnd").removeAttr("disabled");
	}
});

//导出功能
$("#btnExport").click(function() {
	    var orderStatus = $("#orderStatus").val();
	    var timeType=$('input[name="timeType"]:checked').val();
	    var orderPubStart = $("#orderPubStart").val();
	    var orderPubEnd = $("#orderPubEnd").val();
        var url = "<%=basePath%>/order/exportgrouporders?orderStatus=" + orderStatus 
        		+ "&timeType=" + timeType + "&orderPubStart=" + orderPubStart
        		+ "&orderPubEnd=" + orderPubEnd;
        window.location.href = url;
        return true;
});
</script>