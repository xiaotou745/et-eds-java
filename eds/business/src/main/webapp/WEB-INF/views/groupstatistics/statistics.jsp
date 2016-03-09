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
//门店下拉数据
String BusList=(String)request.getAttribute("BusList");
%>
<!-- 下拉框 -->
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/autocomplete.css">
<script src="<%=basePath%>/js/jquery.autocomplete.js"></script>
<!-- 下拉框 -->

<script type="text/javascript"
	src="<%=basePath%>/js/highcharts/js/highcharts.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/highcharts/js/modules/exporting.js"></script>
	
	<div class="content-wrap">
		<div class="top cb">
			<h3 class="cb">订单统计</h3>
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<div class="function">
					<div class="store_filter">
						<input type="text" placeholder="门店名称" id="businessName" />
					<input type="hidden"  id="businessIdValue">
						<div class="icon-dropdown"></div>
					</div>
					<span class="intime"> <input type="text" class="dinput" value="<%=(String)request.getAttribute("pubStart")%>"
						id="orderPubStart" name="orderPubStart" /> <s
						onClick="WdatePicker({el:'orderPubStart',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'orderPubEnd\')||\'new Date()\'}'});"></s></span>
					<span class="inblock">至</span> <span class="intime"><input value="<%=(String)request.getAttribute("pubEnd")%>"
						type="text" class="dinput" id="orderPubEnd" name="orderPubEnd">
						<s
						onClick="WdatePicker({el:'orderPubEnd',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'orderPubStart\')}',maxDate:new Date()});"></s></span>
					<input type="button" class="fr" value="下载报表" style="line-height:30px;padding-left: 10px;padding-right: 10px;width:auto;"  id="btnExport">
					<input type="button" class="btn fr" value="搜索" id="btnSearch">
				</div>
				<div class="report" id="content">
					
				</div>
			</form>
		</div>

	</div>

<script>
$(function(){
	var businessNameDate=<%=BusList%>;
	 $("#businessName").AutoComplete({
	        data: businessNameDate,
	        ajaxDataType: "json",
	        width: 240,
	        listStyle: "custom",
	        matchHandler: function() {
	            return ! 0
	        },
	        afterSelectedHandler: function(d) {
	        	$('#businessName').val(d.label);////1.文本框ID
	        	$('#businessIdValue').val(d.value);////2.隐藏域文本框ID
	        },
	        createItemHandler: function(t, i) {
	            var s = $("<div " + (i.unlink ? 'class="disabled"': "") + ">" + i.label + (i.unlink ? "[已解绑]": "") + "</div>");
	            return s
	        }
	    })
	   // <!-- 下拉框 END-->

	})
	

var jss = {
	search : function(currentPage) {
		var date1=$("#orderPubStart").val();
		var date2=$("#orderPubEnd").val();
		var businessID=$('#businessIdValue').val();
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
		    var data={"orderPubStart":date1,"orderPubEnd":date2,"businessID":businessID};
		    $.post("<%=basePath%>/groupstatistics/statisticsdo", data, function(d) {
				$("#content").html(d);
			});
		}else{
			alert("日期不能为空")
		} 
	}
}
jss.search(1);
$("#btnSearch").click(function() {
	if($("#businessName").val()==""){
		$("#businessIdValue").val("");
	}
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
