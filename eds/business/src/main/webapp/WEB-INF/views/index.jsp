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
	BusinessOrderSummaryModel bos = (BusinessOrderSummaryModel)request.getAttribute("bos");
	BusinessMessage message = (BusinessMessage)request.getAttribute("message");
	List<BusiPubOrderTimeStatisticsModel> pubOrderTimestatistics = (List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("pubOrderTimestatistics");
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
	<div class="top cb">
		<div class="left">
			<div class="l-top">
				<h2>
					<%=bos.getName()%>
					<!-- <a href="javascript:;">详情</a> --> <span class="fr"> 最近登录：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bos.getLastLoginTime())%>
						<%=bos.getCity()%></span>
				</h2>
				<p>
					当前余额 <i>￥</i> <span><%=bos.getBalancePrice()%></span>
				</p>
			</div>
			<div class="l-bottom">
				<p class="fl">
					<img src="images/icon-7.png" width="47" height="47" alt="待抢单任务"
						class="fl"> <span class="fl"> <em class="blue"><%=bos.getUnReceive()%></em>
						<b>待抢单任务</b>
					</span>
				</p>
				<p class="fl">
					<img src="images/icon-8.png" width="47" height="47" alt="待取货任务"
						class="fl"> <span class="fl"> <em class="purple"><%=bos.getReceived()%></em>
						<b>待取货任务</b>
					</span>
				</p>
				<p class="fl">
					<img src="images/icon-9.png" width="47" height="47" alt="配送中任务"
						class="fl"> <span class="fl"> <em class="green"><%=bos.getPickUp()%></em>
						<b>配送中任务</b>
					</span>
				</p>
				<p class="fl">
					<img src="images/icon-10.png" width="47" height="47" alt="已取消任务"
						class="fl"> <span class="fl"> <em class="red"><%=bos.getCancel()%></em>
						<b>已取消任务</b>
					</span>
				</p>
			</div>
		</div>
		<div class="right">
			<div class="r-top" style="position:relative">
				<iframe allowtransparency="true" frameborder="0" width="290" stype="z-index:-1;position:absolutely;left:0;top:0"
					height="96" scrolling="no"
					src="http://tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=0&d=2&bd=0&k=&f=&q=1&e=0&a=0&c=54511&w=290&h=96&align=center"></iframe>
				<!-- 遮罩层 -->
				<div style="position:absolute;width:100%;height:100%;z-index:1;left:0;top:0"></div>
			</div>
			<div class="r-bottom">
				<h2>
					公告
					<a class="fr" href="<%=basePath%>/message/list">更多>></a> 
				</h2>
				<p class="cb">
					<em class="fl"></em>
					<%
						if(message != null){
					%>
					<a class="fr" href="<%=basePath%>/message/list"><span class="fl" id="notice" > <%=message.getContent()%>
					</span>
					</a>
					<%
						}else{
					%>
					当前没有公告
					<%
						}
					%>
				</p>
			</div>
		</div>
	</div>
	<div class="bottom">
		<!-- <h2>
			今日订单统计 <a href="javascript:;">查看更多</a>
		</h2> -->
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