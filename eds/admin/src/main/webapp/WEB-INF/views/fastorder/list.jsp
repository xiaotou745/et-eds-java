<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.enums.OrderAuditStatus"%>


<%	
String basePath =PropertyUtils.getProperty("java.admin.url");
	List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
%>
<!-- 下拉框的样式以及JS -->
<link href="<%=basePath%>/css/plugins/chosen/chosen.css"  rel="stylesheet">
<script src="<%=basePath%>/js/plugins/chosen/chosen.jquery.js" ></script>
<!-- 下拉框的样式以及JS -->
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dAeaG6HwIFGlkbqtyKkyFGEC"></script>
<style type="text/css">
#map_contain {
    height: 90%;
    width: 100%;
    max-width: none;
}
label {
    max-width: none;
}

#control {
width: 100%;
}
</style>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">骑士电话:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="superManPhone"  id="superManPhone" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">骑士姓名:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control"  name="superManName"  id="superManName"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店电话:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="businessPhone"  id="businessPhone"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="businessName"  id="businessName"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">订单状态:</label>
							<div class="col-sm-8">
							<select class="form-control m-b"  name="orderStatus"  id="orderStatus">
					<option value="-1">全部</option>
					<option value="2">已接单</option>
					<option value="4">已取货</option>
					<option value="1">已完成</option>
					<option value="3">已取消</option>
				</select>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">抢单时间:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="orderGrabStart"  id="orderGrabStart"/>
                                    </div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
							     <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="orderGrabEnd"  id="orderGrabEnd"/>
                                    </div>
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商家城市:</label>
							<div class="col-sm-8">
							<%=HtmlHelper.getSelectAuto("businessCity", areaListData, "name", "name","-1","-1","全部") %>
<%-- 							  <%=HtmlHelper.getSelect("businessCity", areaListData, "name", "code","-1","-1","全部") %> --%>
							</div>
						</div>
					</div>
				</div>
					<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">订单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="orderNo" id="orderNo" />
							</div>
						</div>
					</div>
					<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
					
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
					   <button type="button" class="btn btn-w-m btn-primary" id=btnExport
							style="margin-left: 3px;height:30px;">订单数据导出</button>
					</div>
			</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>
<div tabindex="-1" class="modal inmodal" id="orderMapShow"
		role="dialog" aria-hidden="true" style="display: none;">
		<div class="modal-dialog" style="width: 100%; height: 100%">
			<div class="modal-content animated bounceInRight" style="width: 60%; height: 80%;margin:auto;" >
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">查看地图</h4>
				</div>
				<small class="font-bold">
					   <div id="map_contain"  style="width: 100%; height: 100%"></div>
				</div>
				</small>
			<small class="font-bold"> </small>
		</div>
		<small class="font-bold"> </small>
	</div>
<script>
 $(function(){
	  $(' .input-group.date').datepicker({
          todayBtn: "linked",
          keyboardNavigation: false,
          forceParse: false,
          calendarWeeks: true,
          autoclose: true
      });
 });
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/fastorder/listdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	//导出功能
	$("#btnExport").click(function() {
	        if ($("#orderGrabStart").val() == "" || $("#orderGrabEnd").val() == "") {
	        	layer.alert('请输入时间范围!', {
				    icon: 2
				});
	            return;
	        }
	        var formdata=$("#searchForm").serialize();
	        var url = "<%=basePath%>/fastorder/exportorder";
// 	        $.ajax({
// 	            type: 'GET',
// 	            url: url,
// 	            data: formdata,
// 	            success: function (result) {
// 	            }
// 	        });
	        window.location.href = url+"?"+formdata;
	        return true;
	});

	 function showMapData(orderid) {
	        //弹出地图时，禁用滚动条
// 	        document.documentElement.style.overflow = "hidden";
// 	        document.body.style.overflow = "hidden";
	        var url = "<%=basePath%>/fastorder/ordermap?orderid="+orderid;
	        $.ajax({
	            type: 'POST',
	            url: url,
	            data: {},
	            success: function (result) {
	                showMap(result);
	            }
	        });
	    }
	 
	    function showMap(jsonstr) {
	        if (jsonstr == null||jsonstr.length==0) {
	            alert("没取到订单的地图数据！");
	        } else {
	        	var msg="";
	        	var nonebase=0;
	        	if(jsonstr[0].longitude=="0"&&jsonstr[0].latitude=="0"){
	        		msg="发单坐标为空\n";
	        		nonebase++;
	        	}
	        	 if(jsonstr[0].grabLongitude=="0"&&jsonstr[0].grabLatitude=="0"){
	        		 msg+="抢单坐标为空\n";
	        		 nonebase++;
	        	 }
	        	 if(jsonstr[0].pickUpLongitude=="0"&&jsonstr[0].pickUpLatitude=="0"){
	        		 msg+="取货坐标为空\n";
	        		 nonebase++;
	        	 }
	        	 var doneNum=0;
	        	 var noneNum=0;
	        	for (var i = 0; i < jsonstr.length; i++) {
	            	if(jsonstr[i].status=="1"){
	            		doneNum++;
	            		if(jsonstr[i].doneLongitude=="0"&&jsonstr[i].doneLatitude=="0"){
	            			noneNum++;
	            		}
	            	}
	            }
	        	if(noneNum>0){
	        		msg+=("共"+doneNum+"个完成坐标,其中"+noneNum+"个为空\n");
	        	}
	        	if(msg!=""){
	        		alert(msg);
	        	}
	        	if(nonebase==3&&noneNum==doneNum){
	        		return;
	        	}
	            // 百度地图API功能
	            //发单的坐标作为地图的中心点
	            $('#orderMapShow').modal('show');
	            var map = new BMap.Map("map_contain");

	
	            var centerLongitude = jsonstr[0].longitude;
	            var centerLatitude = jsonstr[0].latitude;
	            if (centerLongitude == 0 && centerLongitude==0) {
		            //没有发单经纬度时，地图中心点设置为天安门
		            centerLongitude = 116.3972282409668;
		            centerLatitude = 39.90960456049752;
	            }
	            map.centerAndZoom(new BMap.Point(centerLongitude, centerLatitude), 16);
	            map.enableScrollWheelZoom();

// 	            var points = [];
// 	            for (var i = 0; i < jsonstr.locations.length; i++) {
// 	                points.push(new BMap.Point(jsonstr.locations[i].longitude, jsonstr.locations[i].latitude));
// 	            }
// 	            var polyline = new BMap.Polyline(points, { strokeColor: "red", strokeWeight: 2, strokeOpacity: 0.5 });   //创建折线
// 	            map.addOverlay(polyline);   //增加折线

	            //发单
	            if(jsonstr[0].longitude!="0"&&jsonstr[0].latitude!="0"){
		            var marker = new BMap.Marker(new BMap.Point(jsonstr[0].longitude, jsonstr[0].latitude)); // 创建点
		            map.addOverlay(marker);
		            var label = new BMap.Label("发单时间-" + jsonstr[0].pubDate, { offset: new BMap.Size(-200, -10) });
		            marker.setLabel(label);
	            }

	            //抢单
	             if(jsonstr[0].grabLongitude!="0"&&jsonstr[0].grabLatitude!="0"){
		            var marker1 = new BMap.Marker(new BMap.Point(jsonstr[0].grabLongitude, jsonstr[0].grabLatitude)); // 创建点
		            map.addOverlay(marker1);
		            var label = new BMap.Label("抢单时间-" + jsonstr[0].grabTime, { offset: new BMap.Size(20, -10) });
		            marker1.setLabel(label);
	             }
	            //取货
	            if(jsonstr[0].pickUpLongitude!="0"&&jsonstr[0].pickUpLatitude!="0"){
		            var marker2 = new BMap.Marker(new BMap.Point(jsonstr[0].pickUpLongitude, jsonstr[0].pickUpLatitude)); // 创建点
		            map.addOverlay(marker2);
		            var label = new BMap.Label("取货时间-" + jsonstr[0].pickUpTime, { offset: new BMap.Size(20, 20) });
		            marker2.setLabel(label);
	            }
	            //完成
	            for (var i = 0; i < jsonstr.length; i++) {
	            	if(jsonstr[i].doneLongitude!="0"&&jsonstr[i].doneLatitude!="0"){
			            var marker3 = new BMap.Marker(new BMap.Point(jsonstr[i].doneLongitude, jsonstr[i].doneLatitude)); // 创建点
			            map.addOverlay(marker3);
			            var label = new BMap.Label("完成时间-" + jsonstr[i].actualDoneDate, { offset: new BMap.Size(-200, 20) });
			            marker3.setLabel(label);	
	            	}
	            }
	        }
	    }
</script>