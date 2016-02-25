<%@page import="com.edaisong.entity.domain.GroupTodayStatistics"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.edaisong.entity.BusinessMessage"%>
<%@page import="com.edaisong.entity.domain.BusinessOrderSummaryModel"%>
<%@page
	import="com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
	String basePath = PropertyUtils.getProperty("java.business.url");
%>
<style type="text/css">
* {
	-webkit-box-sizing: initial;
	-moz-box-sizing: initial;
	box-sizing: content-box;
}
</style>

<div class="top cb">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" name="currentPage" id="_hiddenCurrentPage"
			value="1" />
		<div class="function">
			<input type="button" class="fr" value="导出报表" id="btnExport"
				style="line-height: 30px;"> 
			<input type="button" class="fr"
				value="搜索" id="btnSearch" style="line-height: 30px;"> 
			<span class="intime">
				<input type="text"  class="dinput" id="orderPubStart" name="orderPubStart" />
				<s onClick="WdatePicker({el:'orderPubStart',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'orderPubEnd\')||\'new Date()\'}'});"></s></span>
				<span class="inblock">至</span>
				<span class="intime"><input type="text" class="dinput"  id="orderPubEnd" name="orderPubEnd">
				<s onClick="WdatePicker({el:'orderPubEnd',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'orderPubStart\')}',maxDate:new Date()});"></s></span>
		</div>
	</form>
</div>
<div class="bottom bottom2 bottom3" id="content"></div>

<script>
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
	}
}
jss.search(1);
$("#btnSearch").click(function() {
	$("#businessID").val("");
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

//导出功能
$("#btnExport").click(function() {
	    var orderPubStart = $("#orderPubStart").val();
	    var orderPubEnd = $("#orderPubEnd").val();
        var url = "<%=basePath%>/groupstatistics/exportstatistics?" 
        	    + "orderPubStart=" + orderPubStart
        		+ "&orderPubEnd=" + orderPubEnd;
        window.location.href = url;
        return true;
});
</script>
