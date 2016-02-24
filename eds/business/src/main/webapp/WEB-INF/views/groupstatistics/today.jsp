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
	List<BusiPubOrderTimeStatisticsModel> bdaysNew = 
			(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysNew");
	List<BusiPubOrderTimeStatisticsModel> bdaysDelivery = 
			(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysDelivery");
	List<BusiPubOrderTimeStatisticsModel> bdaysTaking = 
			(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysTaking");
	List<BusiPubOrderTimeStatisticsModel> bdaysComplite = 
			(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysComplite");
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
	var bdaysNew = new Array();
	var bdaysDelivery = new Array();
	var bdaysTaking = new Array();
	var bdaysComplite = new Array();
<%if(bdaysNew != null){
		StringBuilder sbbdaysNew = new StringBuilder();
		
		for(BusiPubOrderTimeStatisticsModel model : bdaysNew){
			sbbdaysNew.append(String.format("bdaysNew[%d]=%d;", model.getHour(),model.getPubCount()));
		}
		%>
<%=sbbdaysNew.toString()%>
<%}%>

<%if(bdaysDelivery != null){
	StringBuilder sbbdaysDelivery = new StringBuilder();
	for(BusiPubOrderTimeStatisticsModel model : bdaysDelivery){
		sbbdaysDelivery.append(String.format("bdaysDelivery[%d]=%d;", model.getHour(),model.getPubCount()));
	}
	%>
<%=sbbdaysDelivery.toString()%>
<%}%>

<%if(bdaysTaking != null){
	StringBuilder sbbdaysTaking = new StringBuilder();
	for(BusiPubOrderTimeStatisticsModel model : bdaysTaking){
		sbbdaysTaking.append(String.format("bdaysTaking[%d]=%d;", model.getHour(),model.getPubCount()));
	}
	%>
<%=sbbdaysTaking.toString()%>
<%}%>

<%if(bdaysComplite != null){
	StringBuilder sbbdaysComplite = new StringBuilder();
	for(BusiPubOrderTimeStatisticsModel model : bdaysComplite){
		sbbdaysComplite.append(String.format("bdaysComplite[%d]=%d;", model.getHour(),model.getPubCount()));
	}
	%>
<%=sbbdaysComplite.toString()%>
<%}%>

	var hours = [];
	var counts1 = [];
	var counts2 = [];
	var counts3 = [];
	var counts4 = [];
	for (i = 0; i < 24; i++) {
		var c = i + 1;
		hours[i] = (c).toString();
		if (!bdaysNew[c]) {
			counts1[i] = 0;
		} else {
			counts1[i] = bdaysNew[c];
		}
		if (!bdaysDelivery[c]) {
			counts2[i] = 0;
		} else {
			counts2[i] = bdaysDelivery[c];
		}
		if (!bdaysTaking[c]) {
			counts3[i] = 0;
		} else {
			counts3[i] = bdaysTaking[c];
		}
		if (!bdaysComplite[c]) {
			counts4[i] = 0;
		} else {
			counts4[i] = bdaysComplite[c];
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
				allowDecimals : false
			},
			tooltip : {
				crosshairs : true,
				shared : true
			},
		   legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
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
				name : '待接单',
				data : counts1
			}, {
				type : 'area',
				name : '取货中',
				data : counts2
			}, {
				type : 'area',
				name : '配送中',
				data : counts3
			}, {
				type : 'area',
				name : '已完成',
				data : counts4
			} ]
		});
	});
</script>