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
	BusinessOrderSummaryModel b= (BusinessOrderSummaryModel)request.getAttribute("b");
	List<BusiPubOrderTimeStatisticsModel> pubOrderTimestatistics = 
			(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("pubOrderTimestatistics");
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

<div class="">
    <%=g.getBalancePrice()%>   <%=g.getTodayPay()%>   <%=g.getTodayDishTotal()%>
	    <%=b.getUnReceive()%> <%=b.getReceived()%>  <%=b.getPickUp()%>   <%=b.getPickUp()%> 
	    
	<div class="bottom">
		<div id="container" style="min-width: 500px; height: 400px"></div>
	</div>
</div>

<script type="text/javascript">
	//统计图
	var statistics = new Array();
<%if(pubOrderTimestatistics != null){
		StringBuilder sb = new StringBuilder();
		for(BusiPubOrderTimeStatisticsModel model : pubOrderTimestatistics){
			sb.append(String.format("statistics[%d]=%d;", model.getHour(),model.getPubCount()));
		}%>
	
<%=sb.toString()%>
	
<%}%>
	var hours = [];
	var counts = [];
	for (i = 0; i < 24; i++) {
		var c = i + 1;
		hours[i] = (c).toString();
		if (!statistics[c]) {
			counts[i] = 0;
		} else {
			counts[i] = statistics[c];
		}
	}
	$(function() {
 		var notice = $("#notice").text();
		if (notice.length > 30) {
			$("#notice").text(notice.substr(0, 30) + "...");
		} 

		//统计图
		$('#container').highcharts({
			chart : {
				type : 'spline'
			},
			title : {
				text : '今日订单统计'
			},
			subtitle : {
				text : ''
			},
			xAxis : {
				categories : hours,
				title : {
					text : '时间(小时)'
				},
			},
			yAxis : {
				title : {
					text : '数量'
				},
				labels : {
					formatter : function() {
						return this.value;
					}
				},
				minTickInterval : 1,
				tickAmount : 11,
				allowDecimals:false
			},
			tooltip : {
				crosshairs : true,
				shared : true
			},
			legend : {
				enabled : false
			},
			credits : {
				enabled : false
			},
			exporting : {
				enabled : false
			},
			plotOptions : {
				spline : {
					marker : {
						radius : 4,
						lineColor : '#666666',
						lineWidth : 1
					}
				}
			},
			series : [ {
				type : 'area',
				name : '该时间段订单数量',
				data : counts

			} ]
		});
	});
</script>