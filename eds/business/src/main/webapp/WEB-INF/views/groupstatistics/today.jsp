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
	GroupTodayStatistics g = (GroupTodayStatistics)request.getAttribute("g");
	String BusList=(String)request.getAttribute("BusList");
%>
<style type="text/css">
* {
	-webkit-box-sizing: initial;
	-moz-box-sizing: initial;
	box-sizing: content-box;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>/js/highcharts/js/highcharts.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/highcharts/js/modules/exporting.js"></script>
	<div class="content-wrap">
		<div class="master">
			<div class="m-item i1">
				<div class="m-title">当前余额</div>
				<div class="m-info">
					<div class="icon">
						<img src="<%=basePath%>/images/b_home/m1.jpg" alt="">
					</div>
					<div class="mi-ctx">
						<span class="num"><%=g.getBalancePrice()%> </span>元 <a href="<%=basePath%>/group/recharge">充值</a>
					</div>
				</div>
			</div>
			<div class="m-item i2">
				<div class="m-title">今日消费</div>
				<div class="m-info">
					<div class="icon">
						<img src="<%=basePath%>/images/b_home/m2.jpg" alt="">
					</div>
					<div class="mi-ctx">
						<span class="num"><%=g.getTodayPay()%></span>元
					</div>
				</div>
			</div>
			<div class="m-item i3">
				<div class="m-title">今日营业额</div>
				<div class="m-info">
					<div class="icon">
						<img src="<%=basePath%>/images/b_home/m3.jpg" alt="">
					</div>
					<div class="mi-ctx">
						<span class="num"> <%=g.getTodayDishTotal()%></span>元
					</div>
				</div>
			</div>
		</div>
		<div class="report">
			<div class="title">
				<div class="t-wrap">
					<h3>今日订单交易</h3>
				</div>
				<div class="store_filter">
					<input type="text"  placeholder="门店名称" id="businessName">
					<input type="hidden"  id="businessIdValue">
					<div class="icon-dropdown"></div>
				</div>
			</div>
			<div id="content"></div>
		</div>
	</div>

 
     <script type="text/javascript" src="<%=basePath%>/js/jquery.autocomplete.js"></script>
<script>
$(function(){
	
	// <!-- 下拉框 START-->
	//1.文本框ID
	var businessNameDate=<%=BusList%>
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
	        	jss.search();
	        },
	        createItemHandler: function(t, i) {
	            var s = $("<div " + (i.unlink ? 'class="disabled"': "") + ">" + i.label + (i.unlink ? "[已解绑]": "") + "</div>");
	            return s
	        }
	    })
	   // <!-- 下拉框 END-->

	})
	
var jss = {
	search : function() {
		var data={"businessID":$('#businessIdValue').val(),"currentPage":1};
	    $.post("<%=basePath%>/groupstatistics/todaydo", data, function(d) {
			$("#content").html(d);
		});
	}
}
jss.search();
</script>