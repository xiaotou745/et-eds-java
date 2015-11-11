<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%	
String basePath =PropertyUtils.getProperty("java.business.url");
String regionjson = (String) request.getAttribute("regionjson");
String totalJson = (String) request.getAttribute("totalJson");
String detailJson = (String) request.getAttribute("detailJson");
String businessLat = (String) request.getAttribute("businessLat");
String businessid=request.getAttribute("businessid").toString();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="textml; charset=utf-8">
		<meta charset="utf-8">
		<title>今日订单</title>
		<meta name="description" content="">
		<meta name="Keywords" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<link href="<%=basePath%>/css/c3.css" rel="stylesheet">
		<link href="<%=basePath%>/css/normalize.css" rel="stylesheet">
		<link href="<%=basePath%>/css/todaymap.css" rel="stylesheet">
		<script type="text/javascript" src="<%=basePath%>/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=286c3ec71cae58cacfa75d49145ff545"></script>
</head>
<body>
	<div class="map_main">
		<div class="map_top" id="map"></div>
		<div class="map_bottom">
			<p><a href="#">请点击区域查看订单</a></p>
			<span id="regiontitle">尚8一级>尚8二级（50单）</span>
			<ul class="m-bx m-bx-l">
				<li class="m-fx-1">
					<dl>
						<dt>待接待</dt>
						<dd><a id="waiting" href="#" regionid="-1" onclick="getdetail(0,this)">0单接待</a></dd>
					</dl>
				</li>
				<li class="m-fx-1">
					<dl class="quh">
						<dt>取货中>></dt>
						<dd><a id="picking" href="#" regionid="-1"  onclick="getdetail(2,this)">0单取货中</a></dd>
					</dl>
				</li>
			</ul>
			<ul class="m-bx m-bx-l">
				<li class="m-fx-1">
					<dl class="peis">
						<dt>配送中>></dt>
						<dd><a id="sending" href="#" regionid="-1" onclick="getdetail(4,this)">0单配送中</a></dd>
					</dl>
				</li>
				<li class="m-fx-1">
					<dl class="wanc">
						<dt>已完成>></dt>
						<dd><a id="done" href="#" regionid="-1" onclick="getdetail(1,this)">0单已完成</a></dd>
					</dl>
				</li>
			</ul>
		</div>

	</div>
	
	<script>
	$(function(){
		//绘制一个多边形
		function drawOverlay(object){
			var tempPolygon = new BMap.Polygon(object.overlayPointList,styleOptions);
			var overlayId=object.overlayId;
			tempPolygon.addEventListener('click',function(e){
				overlayClick(overlayId);
			});
			overlayArray[overlayId]=tempPolygon;
			map.addOverlay(tempPolygon);
		}
		function overlayClick(id){
			var region;
			for(var i=0;i<totalJson.length;i++){
				if(totalJson[i].id==id){
					region=totalJson[i];
					break;
				}
			}
			if(region.parentId>0){
				var parentregion;
				for(var i=0;i<totalJson.length;i++){
					if(totalJson[i].id==region.parentId){
						parentregion=totalJson[i];
						break;
					}
				}
				$("#regiontitle").html(parentregion.name+">"+region.name+"("+region.num+"单)");
			}else{
				$("#regiontitle").html(region.name+"("+region.num+"单)");
			}

			$("#waiting").attr("regionid",id);
			$("#picking").attr("regionid",id);
			$("#sending").attr("regionid",id);
			$("#done").attr("regionid",id);
			if(detailJson.length==0){
				$("#waiting").html("0单接待");
				$("#picking").html("0单取货中");
				$("#sending").html("0单配送中");
				$("#done").html("0单已完成");
			}else{
				for(var i=0;i<detailJson.length;i++){
					if((region.parentId==0&&
						detailJson[i].orderRegionOneId==id&&
						detailJson[i].orderRegionTwoId==0)||
						(region.parentId>0&&
						 detailJson[i].orderRegionOneId==region.parentId&&
						 detailJson[i].orderRegionTwoId==id)){
							switch(detailJson[i].status){
							case 0:
								$("#waiting").html(detailJson[i].num+"单接待");
								break;
							case 2:
								$("#picking").html(detailJson[i].num+"单取货中");
								break;
							case 4:
								$("#sending").html(detailJson[i].num+"单配送中");
								break;
							case 1:
								$("#done").html(detailJson[i].num+"单已完成");
								break;
							}
					}
				}
			}
			for (var key in overlayArray){
				if(id==parseInt(key)){
					overlayArray[key].setFillOpacity(SELECT_OPACITY);
				}else{
					overlayArray[key].setFillOpacity(NORMAL_OPACITY);
				}
			}
		}

	});
		//页面第一次打开时，显示一级区域中订单数量最多的区域的详情
		function init(){
			for(var i=0;i<tempArray.length;i++){
				drawOverlay(tempArray[i]);
			}
			var maxparentid;
			var maxnum;
			for(var i=0;i<totalJson.length;i++){
				if(totalJson[i].parentId==0){
					if(maxnum<totalJson[i].num){
						maxparentid=totalJson[i].id;
					}
				}
			}
			overlayClick(maxparentid);
		}
		function getdetail(status,target){
			var regionid=$(target).attr('regionid'); 
			alert(businessid);
			alert(regionid);
			alert(status);
		}
		var map = new BMap.Map('map');
		var centerLongitude = 116.3972282409668;
		var centerLatitude = 39.90960456049752;
		var businessLat="<%=businessLat%>";
		var busdata=businessLat.split(";");
		var tempLat=parseFloat(busdata[0]);
		var templng=parseFloat(busdata[1]);
		if(tempLat>0&&templng>0){
		    if(18.286316<=tempLat&&tempLat<=53.571364&&
		       73.502922<=templng&&templng<=135.070626){
		        centerLongitude = templng;
		        centerLatitude = tempLat;
		   }
		}
		var poi = new BMap.Point(centerLongitude, centerLatitude);
		var overlayArray = {};//区域数组
		var NORMAL_OPACITY = 0.3,SELECT_OPACITY = 0.7;
		map.centerAndZoom(poi, 16);
		//未选中颜色
		var styleOptions = {
		    strokeColor:"red",    //边线颜色。
		    fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
		    strokeWeight: 3,       //边线的宽度，以像素为单位。
		    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
		    fillOpacity: NORMAL_OPACITY,      //填充的透明度，取值范围0 - 1。
		    strokeStyle: 'solid' //边线的样式，solid或dashed。
		}
		var businessid='<%=businessid%>';
		var tempArray=eval('<%=regionjson%>');
		var detailJson=eval('<%=detailJson%>');
		var totalJson=eval('<%=totalJson%>');
		init();
	</script>
</body>
</html>