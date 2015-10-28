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
	List<GroupModel> groupListData=	(List<GroupModel>)request.getAttribute("groupListData");
%>
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
							<label class="col-sm-4 control-label">超人电话:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="superManPhone"  id="txtSuperManPhone" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">超人姓名:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control"  name="superManName"  id="txtSuperManName"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户电话:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="businessPhone"  id="txtBusinessPhone"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="businessName"  id="txtBusinessName"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">订单状态:</label>
							<div class="col-sm-8">
							<%=HtmlHelper.getSelect("orderStatus", EnumHelper.GetEnumItems(OrderStatus.class), "desc", "value",null,"-1","全部") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">发布时间:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="OrderPubStart"  id="txtOrderPubStart"/>
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
                                        <input type="text" class="form-control" value="" name="OrderPubEnd"  id="txtOrderPubEnd"/>
                                    </div>
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选城市:</label>
							<div class="col-sm-8">
							  <%=HtmlHelper.getSelect("businessCity", areaListData, "name", "name","-1","-1","全部") %>
							</div>
						</div>
					</div>
				</div>
					<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选集团: </label>
							<div class="col-sm-8">
								  <%=HtmlHelper.getSelect("groupId", groupListData, "groupname", "id","-1","-1","全部") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">原订单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="originalOrderNo" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">订单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="orderId" />
							</div>
						</div>
					</div>
					<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
					<div class="col-lg-3">
					<div class="form-group">
							<label class="col-sm-4 control-label">审核状态:</label>
							<div class="col-sm-8">
									<%=HtmlHelper.getSelect("auditStatus", EnumHelper.GetEnumItems(OrderAuditStatus.class), "desc", "value",null,"-1","全部") %>
							</div>
						</div>
					</div>
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
// 			data.currentPage=currentPage;
			$.post("<%=basePath%>/order/listdo",data, function(d) {
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
		    var superManPhone = $("#txtSuperManPhone").val();
	        var txtSuperManName = $("#txtSuperManName").val();
	        var txtBusinessPhone = $("#txtBusinessPhone").val();
	        var txtBusinessName = $("#txtBusinessName").val();
	        var txtOrderPubStart = $("#txtOrderPubStart").val();
	        var txtOrderPubEnd = $("#txtOrderPubEnd").val();
	        var businessCity = $("#businessCity").val();
	        var orderStatus = $("#orderStatus").val();
	        var groupId = $("#groupId").val();
	        if (txtOrderPubStart == "" || txtOrderPubEnd == "") {
	        	layer.alert('请输入时间范围!', {
				    icon: 2
				});
	            return;
	        }
	        var url = "<%=basePath%>/order/exportorder?superManPhone=" + superManPhone 
	        		+ "&superManName=" + txtSuperManName + "&businessPhone=" + txtBusinessPhone
	        		+ "&businessName=" + txtBusinessName + "&orderStatus=" + orderStatus 
	        		+ "&businessCity=" + businessCity + "&orderPubStart=" + txtOrderPubStart 
	        		+ "&orderPubEnd=" + txtOrderPubEnd + "&groupId=" + groupId;
	        window.location.href = url;
	        return true;
	});
	
	
	 function showMapData(orderid) {
	        //弹出地图时，禁用滚动条
	        document.documentElement.style.overflow = "hidden";
	        document.body.style.overflow = "hidden";
	        var url = "<%=basePath%>/order/ordermap?orderid="+orderid;
	        $.ajax({
	            type: 'POST',
	            url: url,
	            data: {},
	            success: function (result) {
	            	$('#orderMapShow').modal('show');
	                showMap(result);
	            }
	        });
	    }
	 
	    function showMap(jsonstr) {
	        if (jsonstr == null) {
	            alert("没取到订单的地图数据！");
	        } else {
	            // 百度地图API功能
	            //发单的坐标作为地图的中心点
	            var map = new BMap.Map("map_contain");
	            //没有发单经纬度时，地图中心点设置为天安门
	            //var centerLongitude = 116.3972282409668;
	            //var centerLatitude = 39.90960456049752;
	            var centerLongitude = jsonstr.pubLongitude;
	            var centerLatitude = jsonstr.pubLatitude;
	            if (jsonstr.pubLongitude != 0 && jsonstr.pubLatitude!=0) {
	                //也许需要重新计算中心点
	            }
	            map.centerAndZoom(new BMap.Point(centerLongitude, centerLatitude), 16);
	            map.enableScrollWheelZoom();

	            var points = [];
	            for (var i = 0; i < jsonstr.locations.length; i++) {
	                points.push(new BMap.Point(jsonstr.locations[i].longitude, jsonstr.locations[i].latitude));
	            }
	            var polyline = new BMap.Polyline(points, { strokeColor: "red", strokeWeight: 2, strokeOpacity: 0.5 });   //创建折线
	            map.addOverlay(polyline);   //增加折线
	            var isPubDateTimely = jsonstr.isPubDateTimely != 0;
	            var isGrabTimely = jsonstr.isGrabTimely != 0;
	            var isTakeTimely = jsonstr.isTakeTimely != 0;
	            var isCompleteTimely = jsonstr.isCompleteTimely != 0;
	            //发单
	            var marker = new BMap.Marker(new BMap.Point(jsonstr.pubLongitude, jsonstr.pubLatitude)); // 创建点
	            map.addOverlay(marker);
	            var label = new BMap.Label("发单时间-" + jsonstr.pubDate + (!isPubDateTimely?",非实时":""), { offset: new BMap.Size(-200, -10) });
	            marker.setLabel(label);
	            //抢单
	            var marker1 = new BMap.Marker(new BMap.Point(jsonstr.grabLongitude, jsonstr.grabLatitude)); // 创建点
	            map.addOverlay(marker1);
	            var label = new BMap.Label("抢单时间-" + jsonstr.grabTime + (!isGrabTimely ? ",非实时" : ""), { offset: new BMap.Size(20, -10) });
	            marker1.setLabel(label);
	            //取货
	            var marker2 = new BMap.Marker(new BMap.Point(jsonstr.takeLongitude, jsonstr.takeLatitude)); // 创建点
	            map.addOverlay(marker2);
	            var label = new BMap.Label("取货时间-" + jsonstr.takeTime + (!isTakeTimely ? ",非实时" : ""), { offset: new BMap.Size(20, 20) });
	            marker2.setLabel(label);
	            //完成
	            var marker3 = new BMap.Marker(new BMap.Point(jsonstr.completeLongitude, jsonstr.completeLatitude)); // 创建点
	            map.addOverlay(marker3);
	            var label = new BMap.Label("完成时间-" + jsonstr.actualDoneDate + (!isCompleteTimely ? ",非实时" : ""), { offset: new BMap.Size(-200, 20) });
	            marker3.setLabel(label);
	        }
	    }
</script>