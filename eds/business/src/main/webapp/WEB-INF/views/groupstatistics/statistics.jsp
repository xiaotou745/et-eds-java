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
	<div class="content-wrap">
		<div class="top cb">
			<h3 class="cb">订单统计</h3>
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<div class="function">
					<div class="store_filter">
						<input type="text" placeholder="门店名称" />
						<div class="icon-dropdown"></div>
					</div>
					<span class="intime"> <input type="text" class="dinput"
						id="orderPubStart" name="orderPubStart" /> <s
						onClick="WdatePicker({el:'orderPubStart',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'orderPubEnd\')||\'new Date()\'}'});"></s></span>
					<span class="inblock">至</span> <span class="intime"><input
						type="text" class="dinput" id="orderPubEnd" name="orderPubEnd">
						<s
						onClick="WdatePicker({el:'orderPubEnd',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'orderPubStart\')}',maxDate:new Date()});"></s></span>
					<input type="button" class="fr" value="下载报表" id="btnExport">
					<input type="button" class="btn fr" value="搜索" id="btnSearch">
				</div>
				<div class="report">
					<div class="menu">
						<ul>
							<li class="active" idx="0">
								<div class="num">100</div>
								<div class="des">订单数量（单）</div>
							</li>
							<li idx="1">
								<div class="num">300</div>
								<div class="des">配送费支出（元）</div>
							</li>
							<li idx="2">
								<div class="num">48</div>
								<div class="des">菜品金额（元）</div>
							</li>
							<li idx="3">
								<div class="num">0</div>
								<div class="des">拒单数量（单）</div>
							</li>
						</ul>
					</div>
					<div class="chart"></div>
				</div>
			</form>
		</div>

	</div>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery.autocomplete.js"></script>
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
