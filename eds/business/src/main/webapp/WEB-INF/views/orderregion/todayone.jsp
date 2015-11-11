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
		var map = new BMap.Map('map');
		 var centerLongitude = 116.3972282409668;
		 var centerLatitude = 39.90960456049752;
		var businessLat="<%=businessLat%>";
		var busdata=businessLat.split(";");
		    //没有商家经纬度时，地图中心点设置为天安门
//		     if(parseFloat(busdata[0])>0&&parseFloat(busdata[1])>0){
//		          centerLongitude = busdata[1];
//		          centerLatitude = busdata[0];
//		     }
		var poi = new BMap.Point(centerLongitude, centerLatitude);
		var selectOverlay;
		var NORMAL_OPACITY = 0.5,SELECT_OPACITY = 0.3;
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
		var tempArray=eval('<%=regionjson%>');
		var detailJson=eval('<%=detailJson%>');
		var totalJson=eval('<%=totalJson%>');
		drawOverlays(tempArray);
		//绘制一个多边形
		function drawOverlay(object){
			var tempPolygon = new BMap.Polygon(object.overlayPointList,styleOptions);
			tempPolygon.addEventListener('click',function(e){
				overlayClick(object.overlayId, this);
			});
			map.addOverlay(tempPolygon);
		}
		function overlayClick(id,overlay){
			for(var i=0;i<totalJson.length;i++){
				if(totalJson[i].id==id){
					$("#regiontitle").html(totalJson[i].name+"("+totalJson[i].num+"单)");
					break;
				}
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
					if(detailJson[i].orderRegionOneId==id||
					   detailJson[i].orderRegionTwoId==id){
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
			
			//获取点击的覆盖物信息，ajax请求此区域信息
			console.log(id);
			//拿到子区域json数据，绘制子区域，并放大当前区域，修改当前区域透明度
			//infoPaint(tempSubArray,false);
			if(selectOverlay){
				selectOverlay.setFillOpacity(NORMAL_OPACITY);
			}
			overlay.setFillOpacity(SELECT_OPACITY);
			selectOverlay = overlay;
		}
		//绘制多个多边形

		function drawOverlays(objects){
			for(var i=0;i<objects.length;i++){
				drawOverlay(objects[i]);
			}
		}
	});
	var businessid='<%=businessid%>';
	function getdetail(status,target){
		var regionid=$(target).attr('regionid'); 
		alert(regionid);
	}
	</script>
</body>
</html>