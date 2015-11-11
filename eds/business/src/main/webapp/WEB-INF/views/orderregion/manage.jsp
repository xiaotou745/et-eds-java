<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%	
String basePath =PropertyUtils.getProperty("java.business.url");
String regionjson = (String) request.getAttribute("regionjson");
String businessLat = (String) request.getAttribute("businessLat");
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/map.css">
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=286c3ec71cae58cacfa75d49145ff545"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<script src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js" type="text/javascript"></script>
<div class="top cb">
	<h3 class="cb">配送区域管理</h3>
	<div class="map_title">
		<span>画图文字说明<br/>
1、绘图说明：点击“画多边形”→在地图中单击鼠标左键定点→移动+单击鼠标左键绘制点线面→双击鼠标结束画图<br/>
2、一级区域必须设定9个方可生效；每个一级区域里最多可设定9个二级区域</span>
<a href="<%=basePath%>/orderregion/loglist">查看操作记录</a>
	</div>
	<div class="bottom bottom2 bottom3" id="content2">
		<div class="map_main">
			<div class="map_center">
			<div class="map_top"><span id="parentNum">当前已配置一级区域3个</span>
			<a href="javascript:;" id="draw">画多边形</a>
			</div>
			
				<div id="map" class="map" style="min-height: 798pxhight:400px;"></div>
				<div class="map_popup" id="mapPopup">
					<p>
						区域列表
<!-- 						<a href="javascript:;" id="closePaint"></a> -->
					</p>

					<div class="map_list" style="overflow: auto; margin-top: 10px;">
						<ul id="regionlistul">
						</ul>
					</div>	
<!-- 					<div style="margin:0px auto ;"> -->
<!-- 						<input type="button" class="fr" value="绘制一级" id="draw">  -->
<!--  					<input type="button" class="fr" value="保存设置" id="saveall">  -->
<!-- 					</div>				 -->
				</div>

			</div>
		</div>
	</div>
</div>
<script>

var NORMAL_OPACITY = 0.3, SELECT_OPACITY = 0.7;
var map;
var drawingManager;
var overlayArray = {};//区域数组
var overlayPointArray ={};//区域坐标数组
var newoverlayArray = {};//新增区域数组
var parentId=-1;
var oldParam="";
$(document).ready(function() {
	var WinHeight = $(window).height();
	$("#map").css({
		"minHeight" : WinHeight - 70
	});
	

	var t=setTimeout(function(){
		var divs=$("#map a").hide();
	},1000);
	init();
});
function init(){
	var centerLongitude = 0;
	var centerLatitude = 0;
	var businessLat="<%=businessLat%>";
	//businessLat="0;0";
	var busdata=businessLat.split(";");
	//没有商家经纬度时，地图中心点设置为天安门
	var tempLat=parseFloat(busdata[0]);
	var templng=parseFloat(busdata[1]);
	if(tempLat>0&&templng>0){
	    if(18.286316<=tempLat&&tempLat<=53.571364&&
	       73.502922<=templng&&templng<=135.070626){
	        centerLongitude = templng;
	        centerLatitude = tempLat;
	   }
	}
	if(centerLongitude==0&&centerLatitude==0){
		if($("#content2").is(":visible")){
			$("#content2").hide();
			alert("当前门店地址坐标有误，请通过商户版APP重新提交审核或联系客服");
		}
	}else{
		var poi = new BMap.Point(centerLongitude, centerLatitude);
		map = new BMap.Map('map');
		map.centerAndZoom(poi, 16);
		map.enableScrollWheelZoom();
		//实例化鼠标绘制工具
		drawingManager = new BMapLib.DrawingManager(map, {
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
		//页面加载时，初始化地图上的多边形
		var tempArray=eval('<%=regionjson%>');
		drawOverlays(tempArray);
		oldParam=getparam();
	}
}
//区域绘制完成事件
var overlaycomplete = function(e) {
	checksame(e.overlay);
	var overlayId=0;
	for (var key in overlayArray){
		if(overlayId<parseInt(key)){
			overlayId=parseInt(key);
		}
	}
	overlayId=overlayId+1;
	var parId=0;
	var parclass="";
	if(parentId>0){
		parId=parentId;
		parclass='style="width:127px;"';
	}
	var li ='<span '+parclass+' class="edit-box item_edit">'+
	'<a id="regiontitle'+overlayId+'_'+parId+'" href="javascript:void(0)" onclick="showregion('+overlayId+')">'+
	'</a><input type="text" id="region'+overlayId+'"></span>'+
	'<a   class="regiona change editing"   href="javascript:void(0)">保存</a>'+
	'<a  class="regiona"   href="javascript:void(0)" onclick="deleteregion('+overlayId+')">删除</a>'
	if (parentId>0) {//当前绘制的是子区域
		var parentul = $('#parent'+parentId);
		var hassub=parentul.find('ul').length>0;
		if(!hassub){
			li = '<ul style="margin-left: 20px;"><li id="child'+overlayId+'">'+li+'</li></ul>';
			parentul.append(li);
		}else{
			parentul.find('ul').append('<li id="child'+overlayId+'">'+li+'</li>');
		}
		setParentNum(1,true);
	}else{
		var ul = $('#regionlistul');
		li = '<li id="parent'+overlayId+'">'+li+
			'<a   class="regiona"   href="javascript:void(0)" onclick="addchild('+overlayId+')">绘制二级</a></li>';
		ul.append(li);
	}
	$("#region"+overlayId).focus();
	
	overlayArray[overlayId] = e.overlay;
	overlayPointArray[overlayId]=e.overlay.getPath();//e.overlay.getPath()为多边形各点数组
	newoverlayArray[overlayId]=overlayId;
	showregion(overlayId);
	if(parentId>0){
		$('#regionlistul a[id^="regiontitle'+parentId+'_"]').addClass("selected_a");
	}

	e.overlay.addEventListener('click', function(e) {
		//注册新绘制的区域的点击事件
		overlayClick(overlayId, this);
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
//绘制一个一级区域和其拥有的二级区域
function drawOverlay(object) {
	var tempPolygon = new BMap.Polygon(object.overlayPointList,styleOptions);
	tempPolygon.addEventListener('click', function(e) {
		overlayClick(object.overlayId, this);
	});
	
	overlayArray[object.overlayId]=tempPolygon;
	overlayPointArray[object.overlayId]=tempPolygon.getPath();
	map.addOverlay(tempPolygon);
	addlable(object.overlayId,object.overlayName);
	var tempid=0;
	for(var i=0;i<object.subLists.length;i++){
		tempid=object.subLists[i].overlayId;
	    var childPolygon = new BMap.Polygon(object.subLists[i].overlayPointList,styleOptions);
	    childPolygon.addEventListener('click', function(e) {
			overlayClick(tempid, this);
		});
		map.addOverlay(childPolygon);
		addlable(tempid,object.subLists[i].overlayName);
		overlayArray[tempid]=childPolygon;
		overlayPointArray[tempid]=childPolygon.getPath();
	}
}
function addlable(overlayId,title){
	if(overlayPointArray[overlayId].length<3){
		return;
	}
	var p= overlayPointArray[overlayId][overlayPointArray[overlayId].length-1];
	var point = new BMap.Point(p.lng,p.lat);
	map.centerAndZoom(point, 15);
	var opts = {
	  position : point,    // 指定文本标注所在的地理位置
	  offset   : new BMap.Size(30, -30)    //设置文本偏移量
	}
	var label = new BMap.Label(title, opts);  // 创建文本标注对象
		label.setStyle({
			 color : "blue",
			 fontSize : "12px",
			 height : "20px",
			 width:"0px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 display:"inline-block",
			 border:"0px",
		 });
	//map.addOverlay(label);   
}
//多边形的选中时的点击事件，显示右侧弹出层
function overlayClick(id, overlay) {
	$('#regionlistul a[id^="regiontitle'+id+'"]').click();
}
//根据数据绘制多个多边形
function drawOverlays(objects) {
	//页面加载时，显示右侧弹出层
	initInfoPaint(objects);
	
	for (var i = 0; i < objects.length; i++) {
		drawOverlay(objects[i]);
	}
}
//修改区域名称或新增区域
$('#mapPopup').on('click', '.change', function() {
	var self = $(this);
	var eidtBox = self.prev();
	var regionid=parseInt(eidtBox.find('input').attr('id').replace("region",""));
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
		if (name.length>5) {
			malert('区域名称不能超过5个字符');
			return;
		}
		 var paramaters=getparam();
		 var parJson=eval(paramaters);
		 var pass=true;
		 for(var i=0;i<parJson.length;i++){
			 if(regionid!=parJson[i].id&&$.trim(parJson[i].name)==name){
				 alert("区域名称不能和已有区域名称重复");
				 pass=false;
				 break;
			 }
		 }
		 if(pass){
			self.removeClass('editing');
			eidtBox.removeClass('item_edit');
			eidtBox.find('a').html(name);
			self.html('修改'); 
			saveall();
		 }
	} else {
		if(newoverlayArray[regionid]==regionid){
   			self.addClass('editing');
   			eidtBox.addClass('item_edit');
   			eidtBox.find('input').val(eidtBox.find('a').html());
   			self.html('保存');	
		}
		else{
			var url = "<%=basePath%>/orderregion/checkorder";
	        $.ajax({
	            type: 'POST',
	            url: url,
	            data: {"regionId":regionid},
	            success: function (result) {   		
	            	if(result>0){
	            		alert("当前区域中今日还存在未完成的订单,暂时不能修改");  
	            	}
	            	else{
	        			self.addClass('editing');
	        			eidtBox.addClass('item_edit');
	        			eidtBox.find('input').val(eidtBox.find('a').html());
	        			self.html('保存');
	            	}
	            }
	        });
		}
	}
})
function showregion(regionid){
	for (var key in overlayArray){
		if(regionid==parseInt(key)){
			overlayArray[key].setFillOpacity(SELECT_OPACITY);
		}else{
			overlayArray[key].setFillOpacity(NORMAL_OPACITY);
		}
	}
	
	var nas=$('#regionlistul a[id^="regiontitle"]');
	for(var i=0;i<nas.length;i++){
		if($(nas[i]).attr("id").indexOf("regiontitle"+regionid+"_")<0){
			$(nas[i]).removeClass("selected_a");
		}
	}
	$('#regionlistul a[id^="regiontitle'+regionid+'_"]').addClass("selected_a");
}
function da(po){
	var pts = [];
	for(var i=0;i<po.length;i++){
		 var pt1 = new BMap.Point(po.lng, po.lat);
		   pts.push(pt1);
	}
	return  new BMap.Polygon(pts);
}
function checksame(overlay){
	return;
var same=false;
	//for (var key in overlayArray){
		for(i=0;i<overlay.getPath().length;i++){
			//var pppoint = new BMap.Point(overlay.getPath()[i].lng,overlay.getPath()[i].lat);
			var pppoint = new BMap.Point(overlay.getPath()[i].lng,overlay.getPath()[i].lat);
			//var ply = new BMap.Polygon(overlayArray[38].getPath());
			var result = BMapLib.GeoUtils.isPointInPolygon(pppoint, da(overlayArray[38].getPath()));
			alert(result);
			if(result){
				var p=$('#regionlistul a[id^="regiontitle'+key+'_"]');
				alert("和"+$(p).html()+"有重叠");
				same=true;
				break;
			}
		}
// 		if(same){
// 			break;
// 		}
// 	}
}
function deleteregion(regionid){
	if(!confirm("确定要删除当前区域？")){
		return;
	}
	if(newoverlayArray[regionid]==regionid){
		dodelete(regionid,false);
	}else{
		var url = "<%=basePath%>/orderregion/checkorder";
        $.ajax({
            type: 'POST',
            url: url,
            data: {"regionId":regionid},
            success: function (result) {   		
            	if(result>0){
            		alert("当前区域中今日还存在未完成的订单，暂时不能删除");  
            	}
            	else{
            		dodelete(regionid,true);
            	}
            }
        });
	}
}
function dodelete(regionid,delDb){
	var par=$('#parent'+regionid);
	if(par.length>0){
		var childs=par.find('ul li');
		if(childs.length>0){
			if(!confirm("删除一级区域时，当前区域下的所有二级区域都会被删除，确定删除？")){
				return;
			}
			for(var i=0 ;i<childs.length;i++){
				var childid=parseInt(childs[i].id.replace("child",''));
				map.removeOverlay(overlayArray[childid]);
				delete overlayPointArray[childid];
				delete overlayArray[childid];
			}
			map.removeOverlay(overlayArray[regionid]);
			delete overlayPointArray[regionid];
			delete overlayArray[regionid];
			par.remove();
		}else{
			map.removeOverlay(overlayArray[regionid]);
			delete overlayPointArray[regionid];
			delete overlayArray[regionid];
			par.remove();
		}
		setParentNum(-1,true);
	}else{
		map.removeOverlay(overlayArray[regionid]);
		delete overlayPointArray[regionid];
		delete overlayArray[regionid];
		$('#child'+regionid).remove();
	}
	parentId=-1;
	if(delDb){
		saveall();
	}
}
function getpoints(pointObj){
	var result="";
	for(var i=0;i<pointObj.length;i++){
		if(i<pointObj.length-1){
			result=result+(pointObj[i].lat+","+pointObj[i].lng+";");
		}else{
			result=result+(pointObj[i].lat+","+pointObj[i].lng);
		}
	}
	return result;
}
function getparam(){
	var tempKey;
	var temptitle="";
	var temppoints="";
	var paramaters ="[";
	var regions = $('#regionlistul a[id^="regiontitle"]');
	for(var i=0;i<regions.length;i++){
		tempKey=regions[i].id.replace("regiontitle","").split("_");
		temptitle=$(regions[i]).html();
		temppoints=getpoints(overlayPointArray[tempKey[0]]);
		if(i==regions.length-1){
			paramaters=paramaters+'{"id":'+tempKey[0]+',"parentid":'+tempKey[1]+',"name":"'+temptitle+'","coordinate":"'+temppoints+'"}';
		}else{
			paramaters=paramaters+'{"id":'+tempKey[0]+',"parentid":'+tempKey[1]+',"name":"'+temptitle+'","coordinate":"'+temppoints+'"},';
		}
	
	}  
	return paramaters+"]";
}
function checkEmpty(){
	 var paramaters=getparam();
	 var parJson=eval(paramaters);
	 var pass=true;
	 for(var i=0;i<parJson.length;i++){
		 if(parJson[i].name==""){
			 alert("区域名称不能为空");
			 pass=false;
			 break;
		 }
	 }
	 if(pass){
		var regions = $('#regionlistul a');
		for(var i=0;i<regions.length;i++){
			if ($(regions[i]).hasClass('editing')) {
				 alert("还有未保存的区域名称，请先保存");
				 pass=false;
				 break;
			}
		 } 
	 }

	 return pass;
}
//保存
$('#saveall').on('click', function() {
	var parLength = $('#regionlistul li[id^="parent"]').length;
	if(parLength!=9){
		alert("一级区域必须有9个");
		return;
	}
	saveall();
});
function saveall(){
       var url = "<%=basePath%>/orderregion/saveall";
        var paramaters=getparam();
		if(paramaters==oldParam){
			//alert("没有任何修改，不需要保存");
			return;
		}
		if(!checkEmpty()){
			return;
		}
        $.ajax({
            type: 'POST',
            url: url,
            data: {"regions":paramaters},
            success: function (result) {   		
           		alert(result);  
           		window.location.href= window.location.href;
            }
        });
	}
	//点击绘制一级
	$('#draw').on('click', function() {
		if(!checkEmpty()){
			return;
		}
		var parLength = $('#regionlistul li[id^="parent"]').length;
		if(parLength<9){
			parentId=-1;
			beginDraw();
		}else{
			alert("一级区域只能有9个");
		}

	});
	function beginDraw(){
		drawingManager.close();
		drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
		drawingManager.open();
	}
	function addchild(parId){
		if(!checkEmpty()){
			return;
		}
		var childLength = $('#parent'+parId+' li[id^="child"]').length;
		if(childLength<9){
			showregion(parId);
			parentId=parId;
			beginDraw();
		}else{
			alert("二级区域最多只能有9个");
		}
	}
// 	function setBounds(parId){
// 		var b = new BMap.Bounds(overlayPointArray[parId]);
// 		try {	
// 			BMapLib.AreaRestriction.setBounds(map, b);
// 		} catch (e) {
// 			alert(e);
// 		}
// 	}
// 	function clearBounds(){
// 		try {
// 			var b = new BMap.Bounds(new BMap.Point(116.027143, 39.772348),new BMap.Point(116.832025, 40.126349));
// 			BMapLib.AreaRestriction.setBounds(map, b);
// 		} catch (e) {
// 			alert(e);
// 		}
// 	}
	//关闭弹层
	$('#closePaint').on('click', function() {
		popupClose();

	});

	function popupClose() {
		$('#mapPopup').animate({
			right : "-420px"
		});
	}
function setParentNum(num,add){
	if(add){
		var oldnum=$("#parentNum").html().replace("当前已配置一级区域","").replace("个","");
		$("#parentNum").html("当前已配置一级区域"+(parseInt(oldnum)+num)+"个");
	}else{
		$("#parentNum").html("当前已配置一级区域"+num+"个");
	}
}
	//初始化右侧区域弹出层
	function initInfoPaint(info) {
		var ul = $('#regionlistul');
		var li = '';
		setParentNum(info.length,false);
		info.sort(function(a,b){return a.subLists.length-b.subLists.length});
		for (var i = 0; i < info.length; i++) {
			li = li
			+ '<li id="parent'+info[i].overlayId+'"><span class="edit-box">'+
			'<a id="regiontitle'+info[i].overlayId+'_0" href="javascript:void(0)" onclick="showregion('+info[i].overlayId+')">'+
			info[i].overlayName+'</a><input type="text" id="region'+info[i].overlayId+'"></span>'+
			'<a class="regiona change"   href="javascript:void(0)">修改</a>'+
			'<a   class="regiona"   href="javascript:void(0)" onclick="deleteregion('+info[i].overlayId+')">删除</a>'+
			'<a   class="regiona"   href="javascript:void(0)" onclick="addchild('+info[i].overlayId+')">绘制二级</a>'
			if(info[i].subLists.length>0){
				li = li+'<ul style="margin-left: 20px;">';
				for (var j = 0; j < info[i].subLists.length; j++) {
					li = li
					+ '<li id="child'+info[i].subLists[j].overlayId+'"><span style="width:127px;" class="edit-box">'+
					'<a id="regiontitle'+info[i].subLists[j].overlayId+'_'+info[i].overlayId+'" href="javascript:void(0)" onclick="showregion('+info[i].subLists[j].overlayId+')">'+
					info[i].subLists[j].overlayName+'</a><input type="text" id="region'+info[i].subLists[j].overlayId+'"></span>'+
					'<a   class="regiona change"   href="javascript:void(0)">修改</a>'+
					'<a  class="regiona"   href="javascript:void(0)" onclick="deleteregion('+info[i].subLists[j].overlayId+')">删除</a></li>'
				}
				li = li+'</ul>';
			}
			li = li+'</li>';
		}
		ul.html(li);
		$('#mapPopup').animate({
			right : "0px"
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
