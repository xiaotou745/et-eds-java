<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%	
String basePath =PropertyUtils.getProperty("java.business.url");
basePath="http://10.8.7.253:8091/business";
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
			<p class="todaya">请点击区域查看订单</p>
			<span id="regiontitle">尚8一级>尚8二级（50单）</span>
			<ul class="m-bx m-bx-l">
				<li class="m-fx-1">
					<dl>
						<dt>待接单</dt>
						<dd><a id="waiting" href="#" regionid="-1" onclick="getdetail(0,this)">0单接单</a></dd>
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
	var businessid='<%=businessid%>';
	var tempArray=eval('<%=regionjson%>');
	var detailJson=eval('<%=detailJson%>');
	var totalJson=eval('<%=totalJson%>');
	var overlayArray = {};//区域数组
	var NORMAL_OPACITY = 0.3,SELECT_OPACITY = 0.7;

	//未选中颜色
	var styleOptions = {
	    strokeColor:"red",    //边线颜色。
	    fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
	    strokeWeight: 3,       //边线的宽度，以像素为单位。
	    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
	    fillOpacity: NORMAL_OPACITY,      //填充的透明度，取值范围0 - 1。
	    strokeStyle: 'solid' //边线的样式，solid或dashed。
	}
	var map =new BMap.Map('map', {minZoom :12, maxZoom :16, enableMapClick :false});
	tmpfun = map.onclick;
	map.onclick = null;
	function initMap(){
		var centerLongitude = 116.3972282409668;
		var centerLatitude = 39.90960456049752;
		//如果商家有区域，则定位到订单数量最多的一个一级区域
		var maxid=getmaxParentid();
		if(maxid>=0){
			for(var i=0;i<tempArray.length;i++){
				if(tempArray[i].overlayId==maxid){
					centerLatitude =tempArray[i].overlayPointList[0].lat;
					centerLongitude =tempArray[i].overlayPointList[0].lng;
					break;
				}
			}
		}else{
			var businessLat="<%=businessLat%>";
			//businessLat="0;0";
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
		}

		var poi = new BMap.Point(centerLongitude, centerLatitude);
		map.centerAndZoom(poi, 14);
	}
	function addevent(tempPolygon,overlayId){
		tempPolygon.addEventListener('click',function(e){
			overlayClick(overlayId);
		});
		//app上触摸，不会触发click事件，网上给的解决方法如下：
		//地址：http://www.lofter.com/postentry?from=search&permalink=1ce8cc_7dc4e1
		tempPolygon.addEventListener('touchstart',function(e){
			map.onclick = tmpfun;
			overlayClick(overlayId);
		});
	}
	//绘制一个多边形
	function drawOverlay(object){
		var tempPolygon = new BMap.Polygon(object.overlayPointList,styleOptions);
		var overlayId=object.overlayId;
		addevent(tempPolygon,overlayId);
		overlayArray[overlayId]=tempPolygon;
		map.addOverlay(tempPolygon);
		addlable(overlayId,object.overlayName);
		var tempid=0;
		for(var i=0;i<object.subLists.length;i++){
			tempid=object.subLists[i].overlayId;
		    var childPolygon = new BMap.Polygon(object.subLists[i].overlayPointList,styleOptions);
		    addevent(childPolygon,tempid);
			map.addOverlay(childPolygon);
			overlayArray[tempid]=childPolygon;
			addlable(tempid,object.subLists[i].overlayName);
		}
	}
	function getcenter(overlayId){
		var p= overlayArray[overlayId].getPath()[0];
		return new BMap.Point(p.lng,p.lat);
	}
	function addlable(overlayId,title){
		if(overlayArray[overlayId].getPath().length<3){
			return;
		}
		for(var i=0;i<totalJson.length;i++){
			if(totalJson[i].id==overlayId){
				title=title+"("+totalJson[i].num+")";
				break;
			}
		}
		var point = getcenter(overlayId);
		map.centerAndZoom(point, 15);
		var label = new BMap.Label(title, {position :point, offset :new BMap.Size(-20, -10)});
		label.setStyle({color :"blue", fontWeight :'700', fontSize :"12px", fontFamily :"Microsoft Yahei", backgroundColor :'none', border :0, cursor :"pointer"});
		//map.addOverlay(label);  
	}
	function overlayClick(id){
		if(id<0){
			return;
		}
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
			$("#regiontitle").html(parentregion.name+"("+parentregion.num+"单)"+">"+region.name+"("+region.num+"单)");
		}else{
			$("#regiontitle").html(region.name+"("+region.num+"单)");
		    zoomIn(id);
		}

		$("#waiting").attr("regionid",id);
		$("#picking").attr("regionid",id);
		$("#sending").attr("regionid",id);
		$("#done").attr("regionid",id);
		
		//默认都是0单（如果有详情，则显示详情中的数量）
		$("#waiting").html("0单接待");
		$("#picking").html("0单取货中");
		$("#sending").html("0单配送中");
		$("#done").html("0单已完成");
		
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

		for (var key in overlayArray){
			if(id==parseInt(key)){
				overlayArray[key].setFillOpacity(SELECT_OPACITY);
			}else{
				overlayArray[key].setFillOpacity(NORMAL_OPACITY);
			}
		}
	}
	//页面第一次打开时，显示一级区域中订单数量最多的区域的详情
	function init(){
		initMap();
		for(var i=0;i<tempArray.length;i++){
			drawOverlay(tempArray[i]);
		}

		overlayClick(getmaxParentid());
	}
	function getdetail(status,target){
		if(status==0){
			return;
		}
		var regionid=$(target).attr('regionid'); 
		if(parseInt(regionid)<0){
			return;
		}
		var regionName="";
		for(var i=0;i<totalJson.length;i++){
			if(totalJson[i].id==regionid){
				regionName=totalJson[i].name;
				break;
			}
		}
		try{
		//和app交互
		    window.todayOrder.orderList(businessid,regionid,status,regionName);
		}catch(e){
		}
		try{
			//和app交互
			orderList(businessid,regionid,status,regionName);
		}catch(e){
		}
	}
	function getmaxParentid(){
		var maxparentid=-1;
		var maxnum=0;
		for(var i=0;i<totalJson.length;i++){
			if(totalJson[i].parentId==0){
				if(maxnum<=totalJson[i].num){
					maxparentid=totalJson[i].id;
				}
			}
		}
		return maxparentid;
	}
	//点击区域放大，地图居中
	function zoomIn(overlayId) {
		var isparent=true;
		for(var i=0;i<totalJson.length;i++){
			if(totalJson[i].id==overlayId){
				if(totalJson[i].parentId>=0){
					isparent=false;
					break;
				}
			}
		}
	    //$(window).scrollTop(200);
	    if(isparent){
		    map.zoomTo(16);
	    }
		var point = getcenter(overlayId);
	    map.panTo(point);
	  }
	$(function(){
		init();
		var t=setTimeout(function(){
			var divs=$("#map a").hide();
		},1000);
	});
	</script>
</body>
</html>