<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%	
String basePath =PropertyUtils.getProperty("java.business.url");
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/map.css">
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=286c3ec71cae58cacfa75d49145ff545"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<div class="top cb">
	<h3 class="cb">配送区域管理</h3>
	<div class="function">
		<span>当前已配置配送区域3个</span>
		<a href="javascript:;" id="draw">画多边形</a>
		<a href="javascript:;" class="return">返回</a>
	</div>
	<div class="bottom bottom2 bottom3" id="content">
		<div class="map_main">
			<div class="map_center">
				<div id="map" class="map" style="min-height: 798pxhight:400px;"></div>
				<div class="map_popup" id="mapPopup">
					<p>
						区域列表<a href="javascript:;" id="closePaint"></a>
					</p>
					<div class="map_list">
						<ul id="regionlistul">
						</ul>
					</div>
				</div>

			</div>

			<div class="map_bottom">
				<dl>
					<dt>修改历史</dt>
					<dd>11.02日将华腾世纪总部公园修改为尚8国际广告园</dd>
				</dl>
			</div>

		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		var WinHeight = $(window).height();
		$("#map").css({
			"minHeight" : WinHeight - 70
		});
	});

	//地图操作
	//
	var map = new BMap.Map('map');
	var poi = new BMap.Point(116.307852, 40.057031);
	var selectOverlay;
	var NORMAL_OPACITY = 0.5, SELECT_OPACITY = 0.3;
	map.centerAndZoom(poi, 16);
	map.enableScrollWheelZoom();
	var mapPopup = $('#mapPopup');
	var overlayArray = new Array();//区域数组
	var overlayPointArray =new Array();//区域坐标数组
	var parentId=-1;
	//区域绘制完成事件
	var overlaycomplete = function(e) {
		var overlayId=overlayArray.length;
		var parliId='<li id="parent'+overlayId+'">';
		var li ='<span class="edit-box item_edit">'+
		'<a href="javascript:void(0)" onclick="showregion('+overlayId+')">'+
		'</a><input type="text" id="region'+overlayId+'"></span>'+
		'<a   class="regiona change editing"   href="javascript:void(0)">保存</a>'+
		'<a  class="regiona"   href="javascript:void(0)" onclick="deleteregion('+overlayId+')">删除</a></li>'
		if (parentId>0) {//当前绘制的是子区域
			var parentul = $('#parent'+parentId);
			var hassub=parentul.find('ul').length>0;
			if(!hassub){
				li = '<ul style="margin-left: 20px;"><li>'+li+'</ul>';
			}
			parentul.find('ul').append(li);
		}else{
			var ul = $('#regionlistul');
			li = '<li id="parent'+overlayId+'">'+li+
				'<a   class="regiona"   href="javascript:void(0)" onclick="addchild('+overlayId+')">绘制二级</a>';
			ul.append(li);
		}

		overlayArray[overlayId] = e.overlay;
		overlayPointArray[overlayId]=e.overlay.getPath();//e.overlay.getPath()为多边形各点数组
		if (selectOverlay) {
			selectOverlay.setFillOpacity(NORMAL_OPACITY);
		}
		e.overlay.setFillOpacity(SELECT_OPACITY);
		selectOverlay = e.overlay;
		e.overlay.addEventListener('click', function(e) {
			//注册新绘制的区域的点击事件
			overlayClick(overlayId, this);
		});
		mapPopup.animate({
			right : "20px"
		});
	};
	//未选中颜色
	var styleOptions = {
		strokeColor : "red", //边线颜色。
		fillColor : "red", //填充颜色。当参数为空时，圆形将没有填充效果。
		strokeWeight : 3, //边线的宽度，以像素为单位。
		strokeOpacity : 0.8, //边线透明度，取值范围0 - 1。
		fillOpacity : NORMAL_OPACITY, //填充的透明度，取值范围0 - 1。
		strokeStyle : 'solid' //边线的样式，solid或dashed。
	}
	//实例化鼠标绘制工具
	var drawingManager = new BMapLib.DrawingManager(map, {
		isOpen : false, //是否开启绘制模式
		enableDrawingTool : false, //是否显示工具栏
		drawingToolOptions : {
			anchor : BMAP_ANCHOR_TOP_RIGHT, //位置
			offset : new BMap.Size(5, 5), //偏离值
		},
		circleOptions : styleOptions, //圆的样式
		polylineOptions : styleOptions, //线的样式
		polygonOptions : styleOptions, //多边形的样式
		rectangleOptions : styleOptions
	//矩形的样式
	});
	//添加鼠标绘制工具监听事件，用于获取绘制结果
	//
	drawingManager.addEventListener('overlaycomplete', overlaycomplete);
// 	function clearAll() {
// 		for (var i = 0; i < overlays.length; i++) {
// 			map.removeOverlay(overlays[i]);
// 		}
// 		overlays.length = 0
// 	}
	//点击绘制
	$('#draw').on('click', function() {
		//删除这在绘制的区域;
		drawingManager.close();
		drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
		drawingManager.open();
	});

	//绘制存在区域
	//虚假数据 
	//字段：overlayId 区域id 
	//	   overlayPointList 区域多边各点集合集合内的坐标属性不可修改
	var tempArray = [ {
		overlayId : 1,
		overlayName : "一级区域1",
		overlayPointList:[
		               	{lat: 40.04662,lng: 116.305912},
		               	{lat: 40.045488,lng: 116.3016},
		               	{lat: 40.044825,lng: 116.309792},
		               	{lat: 40.047062,lng: 116.310511},
		               	{lat: 40.047035,lng: 116.306846}
		               	],
		subLists : [ {
			overlayId : 2,
			overlayName : "子区域1",
			overlayPointList : [ {
				lat : 40.04662,
				lng : 116.305912
			}, {
				lat : 40.045488,
				lng : 116.3016
			}, {
				lat : 40.044825,
				lng : 116.309792
			}, {
				lat : 40.047062,
				lng : 116.310511
			}, {
				lat : 40.047035,
				lng : 116.306846
			} ]
		}, {
			overlayId : 3,
			overlayName : "子区域2",
			overlayPointList : [ {
				lat : 40.04662,
				lng : 116.305912
			}, {
				lat : 40.045488,
				lng : 116.3016
			}, {
				lat : 40.044825,
				lng : 116.309792
			}, {
				lat : 40.047062,
				lng : 116.310511
			}, {
				lat : 40.047035,
				lng : 116.306846
			} ]
		} ]
	},
	{
		overlayId : 4,
		overlayName : "一级区域2",
		overlayPointList:[
			               	{lat: 40.04662,lng: 116.305912},
			               	{lat: 40.045488,lng: 116.3016},
			               	{lat: 40.044825,lng: 116.309792},
			               	{lat: 40.047062,lng: 116.310511},
			               	{lat: 40.047035,lng: 116.306846}
			               	],
		subLists : [ {
			overlayId : 5,
			overlayName : "子区域21",
			overlayPointList : [ {
				lat : 40.04662,
				lng : 116.305912
			}, {
				lat : 40.045488,
				lng : 116.3016
			}, {
				lat : 40.044825,
				lng : 116.309792
			}, {
				lat : 40.047062,
				lng : 116.310511
			}, {
				lat : 40.047035,
				lng : 116.306846
			} ]
		}, {
			overlayId : 6,
			overlayName : "子区域22",
			overlayPointList : [ {
				lat : 40.04662,
				lng : 116.305912
			}, {
				lat : 40.045488,
				lng : 116.3016
			}, {
				lat : 40.044825,
				lng : 116.309792
			}, {
				lat : 40.047062,
				lng : 116.310511
			}, {
				lat : 40.047035,
				lng : 116.306846
			} ]
		} ]
	}
	];

	//绘制一个一级区域和其拥有的二级区域
	function drawOverlay(object) {
		var tempPolygon = new BMap.Polygon(object.overlayPointList,styleOptions);
		tempPolygon.addEventListener('click', function(e) {
			overlayClick(object.overlayId, this);
		});
		overlayArray[object.overlayId]=tempPolygon;
		map.addOverlay(tempPolygon);
		for(var i=0;i<object.subLists.length;i++){
		    tempPolygon = new BMap.Polygon(object.subLists[i].overlayPointList,styleOptions);
			tempPolygon.addEventListener('click', function(e) {
				overlayClick(object.subLists[i].overlayId, this);
			});
			map.addOverlay(tempPolygon);
			overlayArray[object.subLists[i].overlayId]=tempPolygon;
		}
	}
	//多边形的选中时的点击事件，显示右侧弹出层
	function overlayClick(id, overlay) {
		//获取点击的覆盖物信息，ajax请求此区域信息
		console.log(id);
		//拿到子区域json数据，绘制子区域，并放大当前区域，修改当前区域透明度
		//infoPaint(tempSubArray, false);
		if (selectOverlay) {
			selectOverlay.setFillOpacity(NORMAL_OPACITY);
		}
		overlay.setFillOpacity(SELECT_OPACITY);
		selectOverlay = overlay;
	}
	//根据数据绘制多个多边形
	function drawOverlays(objects) {
		//页面加载时，显示右侧弹出层
		initInfoPaint(objects);
		for (var i = 0; i < objects.length; i++) {
			drawOverlay(objects[i]);
		}
	}
	//页面加载时，初始化地图上的多边形
	drawOverlays(tempArray);

	//修改区域名称或新增区域
	mapPopup.on('click', '.change', function() {
		var self = $(this);
		var eidtBox = self.parents('.edit-box');
		eidtBox=self.prev();
		//修改中状态
		if (self.hasClass('editing')) {
			//点击保存
			//修改后的名字,用来传到后台,空判断
			var name = eidtBox.find('input').val();
			name = $.trim(name);
			if (name == '') {
				malert('区域名称不能为空');
				return;
			}
			self.removeClass('editing');
			eidtBox.removeClass('item_edit');
			eidtBox.find('a').html(name);
			self.html('修改');

		} else {
			self.addClass('editing');
			eidtBox.addClass('item_edit');
			eidtBox.find('input').val(eidtBox.find('a').html());
			self.html('保存');
		}
	})
	function showregion(regionid){
		alert(regionid);
	}

	function deleteregion(regionid){
		var par=$('#parent'+regionid);
		if(par.length>0){
			if(confirm("删除一级区域时，当前区域下的所有二级区域都会被删除，确定？")){
				map.removeOverlay(overlayArray[regionid]);
				overlayArray.splice(regionid,1); //删除
				var childs=par.find('ul li');
				for(var i=0 ;i<childs.length;i++){
					var childid=childs[i].id.replace("child",'');
					map.removeOverlay(overlayArray[childid]);
					overlayArray.splice(childid,1); //删除
				}
				par.remove();
			}
		}else{
			map.removeOverlay(overlayArray[regionid]);
			overlayArray.splice(regionid,1); //删除
			$('#child'+regionid).remove();
		}
	}
	function addchild(parId){
		parentId=parId;
		$('#draw').click();
	}
	//关闭弹层
	$('#closePaint').on('click', function() {
		popupClose();

	});

	function popupClose() {
		mapPopup.animate({
			right : "-420px"
		});
		if (selectOverlay) {
			selectOverlay.setFillOpacity(NORMAL_OPACITY);
		}
	}

	//子区域管理按钮点击事件
	$('#editSub').on('click',function() {
		//再次请求
		isSubEdit = true;
		infoPaint(tempSubArray, true);
		//绘制子区域
		selectOverlay.setFillOpacity(0.3);
		for (var i = 0; i < tempSubArray.subLists.length; i++) {
			var tempPolygon = new BMap.Polygon(
					tempSubArray.subLists[i].overlayPointList,
					styleOptions);
			subOverlayArray[tempSubArray.subLists[i].subOverlayId] = tempPolygon;
			map.addOverlay(tempPolygon);
		}
		$(this).hide();
		$('.return').show();

	});
	$('.return').on('click', function() {
		isSubEdit = false;
		$('#editSub').show();
		$(this).hide();
		for ( var i in subOverlayArray) {
			map.removeOverlay(subOverlayArray[i]);
		}
		mapPopup.find('.map_list .change').remove();
		mapPopup.find('.map_list .delete').remove();
	});

	//初始化右侧区域弹出层
	function initInfoPaint(info) {
		var ul = $('#regionlistul');
		var li = '';
		for (var i = 0; i < info.length; i++) {
			li = li
			+ '<li id="parent'+info[i].overlayId+'"><span class="edit-box">'+
			'<a href="javascript:void(0)" onclick="showregion('+info[i].overlayId+')">'+
			info[i].overlayName+'</a><input type="text" id="region'+info[i].overlayId+'"></span>'+
			'<a class="regiona change"   href="javascript:void(0)">修改</a>'+
			'<a   class="regiona"   href="javascript:void(0)" onclick="deleteregion('+info[i].overlayId+')">删除</a>'+
			'<a   class="regiona"   href="javascript:void(0)" onclick="addchild('+info[i].overlayId+')">绘制二级</a>'
			if(info[i].subLists.length>0){
				li = li+'<ul style="margin-left: 20px;">';
				for (var j = 0; j < info[i].subLists.length; j++) {
					li = li
					+ '<li id="child'+info[i].subLists[j].overlayId+'"><span class="edit-box">'+
					'<a href="javascript:void(0)" onclick="showregion('+info[i].subLists[j].overlayId+')">'+
					info[i].subLists[j].overlayName+'</a><input type="text" id="region'+info[i].subLists[j].overlayId+'"></span>'+
					'<a   class="regiona change"   href="javascript:void(0)">修改</a>'+
					'<a  class="regiona"   href="javascript:void(0)" onclick="deleteregion('+info[i].subLists[j].overlayId+')">删除</a></li>'
				}
				li = li+'</ul>';
			}
			li = li+'</li>';
		}
		ul.html(li);
		mapPopup.animate({
			right : "20px"
		});
	}

	//弹出框
	function malert(message, fn) {
		var html = '<div class="popup popup5">' + '<div class="bg">蒙层</div>'
				+ '<div class="popupBox popupBox5">' + '<h1>' + message
				+ '</h1>' + '<a class="qx2" href="javascript:;">取消</a>&nbsp;'
				+ '<a class="qr2" href="javascript:;">确认</a>' + '</div>'
				+ '</div>'
		$('body').append(html);
		$('.qx2').on('click', function() {
			$(this).parents('.popup5').remove();
		});
		$('.qr2').on('click', function() {
			if (fn) {
				fn();
			} else {
				$(this).parents('.popup5').remove();
			}
		});
	}
</script>
