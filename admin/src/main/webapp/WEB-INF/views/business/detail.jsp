<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.common.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.BusinessGroup"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.entity.domain.BusinessThirdRelationModel"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="com.edaisong.entity.BusinessOptionLog"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
String basePath =PropertyUtils.getProperty("static.admin.url");
String subsidyConfig=request.getAttribute("subsidyConfig").toString();

String finalCheckPicUrl=request.getAttribute("finalCheckPicUrl").toString();
String finalBigCheckPicUrl=request.getAttribute("finalBigCheckPicUrl").toString();

String finalBusinessPicUrl=request.getAttribute("finalBusinessPicUrl").toString();
String finalBigBusinessPicUrl=request.getAttribute("finalBigBusinessPicUrl").toString();

List<AreaModel> openCityList=(List<AreaModel>)request.getAttribute("openCityList");
List<AreaModel> openAreaList=	(List<AreaModel>)request.getAttribute("openAreaList");
BusinessDetailModel detail=	(BusinessDetailModel)request.getAttribute("detail");
List<BusinessThirdRelationModel> businessThirdRelation=	(List<BusinessThirdRelationModel>)request.getAttribute("businessThirdRelation");
List<DeliveryCompany> deliveryCompany =(List<DeliveryCompany>)request.getAttribute("deliveryCompany");
List<BusinessOptionLog> businessOpLog=(List<BusinessOptionLog>)request.getAttribute("businessOpLog");

List<GroupModel> groupListData=(List<GroupModel>)request.getAttribute("groupListData");
List<GroupModel> modifyGroupListData = new ArrayList<GroupModel>();
for (GroupModel groupModel : groupListData) {
	if (groupModel.getIsmodifybind() == 1) {
		modifyGroupListData.add(groupModel);
	}
}
List<BusinessGroup> businessGroupListData=(List<BusinessGroup>)request.getAttribute("businessGroupListData");
%>
<div>
	<div id="left" style="float: left; width: 750px; height: auto; border-right: 1px #dcdcdc solid; margin-top: -10px;">
	<form id="busForm" action="">
		<div style="width: 750px; height: auto; border-bottom: solid 0px #dcdcdc; padding-left: 10px;">
			<div class="control-group" style="margin-top: 10px">
				<label>注册账号：</label> <label><%=detail.getPhoneno()%></label>
				<div class="SearchMd"
					style="padding: 0px; border-bottom: solid 0px #dcdcdc;">
					<div style="margin-left: 630px; margin-top: -22px">
						<input type="button" value="保存" class="searchBtn"
							id="btnModifyCommit" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<label>商铺名称：</label> <input name="busiName" id="busiName"
					type="text" value="<%=detail.getName()%>"> <input
					name="busiId" id="busiId" type="hidden" value="<%=detail.getId()%>">
			</div>
			<div class="control-group">
				<label>联系电话：</label> <input name="busiPhoneNo2" id="busiPhoneNo2"
					type="text" value="<%=detail.getPhoneno2()%>">
			</div>
			<div class="control-group">
				<label>联系座机：</label> <input name="busiLandline" id="busiLandline"
					type="text"
					value="<%=detail.getLandline()==null?"":detail.getLandline()%>">
			</div>
			<div class="control-group">
				<label style="margin-left: 5px">配 送 费：</label> <input
					name="busiWaisong" id="busiWaisong" type="text"
					value="<%=detail.getDistribsubsidy()%>">
			</div>
			<div class="control-group">
				<label style="margin-left: 20px">城 市：</label>
				<%=HtmlHelper.getSelect("busiCity", openCityList, "name", "code",detail.getCityid(),"","-请选择-","width:77px")%>
				<label>区 域：</label>
				<%=HtmlHelper.getSelect("busiDistrict", openAreaList, "name", "code",detail.getDistrictid(),"","-请选择-","width:77px")%>
			</div>
			<div class="control-group">
				<label style="margin-left: 20px">地 址：</label> <input name="busiAddr"
					id="busiAddr" style="width: 200px;" type="text"
					value="<%=detail.getAddress()%>">
			</div>
			<div class="control-group" style="margin-bottom: 10px">
				<label style="margin-left: 5px">经 纬 度：</label> <input
					name="busiLongitude" id="busiLongitude" style="width: 120px;"
					type="text" value="<%=detail.getLongitude()%>" disabled="disabled">
				<input name="busiLatitude" id="busiLatitude" style="width: 120px;"
					type="text" value="<%=detail.getLatitude()%>" disabled="disabled">
				<a id="postion" style="margin-left: 15px"><b>地图定位</b></a>
			</div>
		</div>
		<hr />
		<div
			style="width: 750px; height: auto; border-bottom: solid 0px #dcdcdc; padding-left: 10px;">
			<div class="control-group" style="margin-top: 10px">
				<label style="font-size: 15px">结算比例设置(应收)</label>
			</div>
			<div class="control-group">
				<input id="rCommissionFormulaMode0" name="rCommissionFormulaMode"
					type="radio" value="1"
					<%=detail.getCommissiontype()==1?"checked" : ""%>> <label
					for="rCommissionFormulaMode0">结算比例：</label> <input
					id="rCommissionFormulaMode1" name="rCommissionFormulaMode"
					type="radio" value="2" style="margin-left: 30px"
					<%=detail.getCommissiontype()==2?"checked" : ""%>> <label
					for="rCommissionFormulaMode1">固定金额：</label> <input type="hidden"
					id="oldCommissionType" name="oldCommissionType">
			</div>
			<div class="control-group" style="margin-bottom: 10px">
				<div id="divbusCommissionText"
					<%=detail.getCommissiontype()==2?"hidden" : ""%>>
					<input name="busCommissionText" id="busCommissionText"
						style="width: 120px;" type="text"
						value="<%=detail.getBusinesscommission()%>">%
				</div>
				<div id="divCommissionFixValue"
					<%=detail.getCommissiontype()==1?"hidden" : ""%>>
					<input name="CommissionFixValue" id="CommissionFixValue"
						style="width: 120px;" type="text"
						value="<%=detail.getCommissionfixvalue()%>">元/单
				</div>
			</div>
		</div>
		<hr />
		<div
			style="width: 750px; height: auto; border-bottom: solid 1px #dcdcdc; padding-left: 10px;">
			<div class="control-group" style="margin-top: 10px">
				<label style="font-size: 15px">补贴策略设置(应付)</label>
			</div>
			<div class="control-group" style="margin-left: 27px">
				<label id="labGlobalConfig" style="margin-left: 48px"><%=subsidyConfig%></label>
			</div>
			<div class="control-group">
				<label style="margin-left: 74px">补贴策略：</label>
				<%=HtmlHelper.getSelect("businessGroupID", businessGroupListData, "name", "id",detail.getBusinessgroupid(),null,"","width:143px")%>
			</div>
			<div class="control-group" style="margin-left: 2px">
				<label style="margin-left: 48px">餐费结算方式：</label> <select
					name="busiMealsSettleMode" class="selectw" id="busiMealsSettleMode"
					style="width: 143px">
					<option <%=detail.getMealssettlemode()==0?"selected":""%> value="0">线下结算</option>
					<option <%=detail.getMealssettlemode()==1?"selected":""%> value="1">线上结算</option>
				</select>
			</div>
			<div class="control-group">
				<label style="margin-left: 73px">一键发单：</label> <input
					id="rOneKeyPubOrderY" name="rOneKeyPubOrder" type="radio" value="1"
					<%=detail.getOnekeypuborder()==1?"checked" : ""%>> <label>是</label>
				<input id="rOneKeyPubOrderN" name="rOneKeyPubOrder" type="radio"
					value="0" style="margin-left: 30px"
					<%=detail.getOnekeypuborder()==0?"checked" : ""%>> <label>否</label>
			</div>
			<div class="control-group">
				<label style="margin-left: 48px">余额可以透支：</label> <input
					id="rBbalanceAllowOverDraftY" name="rBalanceAllowOverDraft"
					type="radio" value="1"
					<%=detail.getIsallowoverdraft()==1?"checked" : ""%>> <label>是</label>
				<input id="rBbalanceAllowOverDraftN" name="rBalanceAllowOverDraft"
					type="radio" value="0" style="margin-left: 30px"
					<%=detail.getIsallowoverdraft()==0?"checked" : ""%>> <label>否</label>
			</div>
			<div class="control-group">
				<label>使用雇主任务时间限制：</label> <input id="rIsEmployerTaskY"
					name="rIsEmployerTask" type="radio" value="1"
					<%=detail.getIsemployertask()==1?"checked" : ""%>> <label>是</label>
				<input id="rIsEmployerTaskN" name="rIsEmployerTask" type="radio"
					value="0" style="margin-left: 30px"
					<%=detail.getIsemployertask()==0?"checked" : ""%>> <label>否</label>
			</div>
			<div class="control-group" style="margin-bottom: 10px">
				<label style="margin-left: 70px">第三方ID：</label> <input
					name="busiSourceId" id="busiSourceId" type="text"
					value="<%=detail.getOriginalbusiid()%>"
					<%=detail.getIsModifyBind()==0&&detail.getGroupid()>0?"disabled" : ""%> />
				<input type="hidden" id="hidGroupId" name="hidGroupId"
					value="<%=detail.getGroupid()%>" />
				<%
					if(detail.getGroupid()>0)
								                {
				%>
				<label>来源平台：<%=detail.getGroupName()%></label>
				<%
					}
								                else
								                {
				%>
				<%=HtmlHelper.getSelect("busGroupId", modifyGroupListData, "groupname", "id",detail.getBusinessgroupid(),"0","--请选择--","width:100px")%>
				<%
					}
				%>
			</div>

		</div>
    </form>
		<div
			style="width: 750px; height: auto; overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;"
			id="divDeliveryCompany">
			<div style="float: left">
				<label>绑定配送公司：</label>
			</div>
			<div id="deliveryCompanyList" style="float: left; width: 550px">
				<%
					if (deliveryCompany != null && deliveryCompany.size() > 0)
								            {
				%>
				<div style="width: 110px; height: 25px; float: left">
					<input type="checkbox" name="checkAllMenus" id="selectAll"
						onclick="checkAll()" />全部
				</div>
				<%
					for (DeliveryCompany item : deliveryCompany) {
					String check = item.getDeliverycompanycode()==detail.getRecommendphone() ? "checked='checked'" : "";
				%>
				<div style="width: 110px; height: 25px; float: left">
					<input type="checkbox" name="checkMenus" id="<%=item.getId()%>"
						value="<%=item.getId()%>" <%=check%> /> <label><%=item.getDeliverycompanyname()%></label>
				</div>
				<%
					}
								            }
				%>

			</div>
			<div class="SearchMd"
				style="padding: 0px; border-bottom: solid 0px #dcdcdc;">
				<div style="margin-left: 640px; margin-top: 0px">
					<input type="button" value="保存" class="searchBtn"
						id="btnModifyExpress" />
				</div>
			</div>
		</div>
		<div
			style="width: 750px; height: auto; border-bottom: solid 1px #dcdcdc; padding-left: 10px;">
			<label style="font-size: 15px; margin-left: 10px">商户操作记录：</label>
			<table
				class="table table-striped table-bordered table-hover dataTables-example">
				<thead>
					<tr>
						<th style="">时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						if(businessOpLog!=null&&businessOpLog.size()>0)
											            {
											            	for (BusinessOptionLog item : businessOpLog) {
					%>
					<tr>
						<td><%=ParseHelper.ToDateString(item.getInserttime(), "")%></td>
						<td><%=item.getRemark()%></td>
					</tr>
					<%
						}
											            }
					%>
				</tbody>
			</table>

		</div>
		<div style="height: 30px"></div>
		<div tabindex="-1" class="modal inmodal" id="MapShow" role="dialog"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">地图选择位置</h4>
					</div>
					<small class="font-bold">
						<div class="modal-body">
							<div id="map" style="width: 550px; height: 430px;"></div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
						</div>
					</small>
				</div>
				<small class="font-bold"> </small>
			</div>
			<small class="font-bold"> </small>
		</div>
	</div>

	<div id="right" style="float: left; width: 250px; height: auto;">
		<div id="BusiImage" style="width: 250px; margin-left: 20px">
			<div style="width: 200px; height: 200px">
				<img id="showBusiImage" src="<%=finalCheckPicUrl%>" width="200px"
					height="200px" />
			</div>
			店主手持身份证照片<a id="showBigBusiImage" href="<%=finalBigCheckPicUrl%>"
				target="_blank" style="margin-left: 15px">查看大图</a>
		</div>
		<div id="BusinessLicensePic" style="width: 250px; margin-left: 20px">
			<div style="width: 200px; height: 200px">
				<img id="showBusiImage" src="<%=finalBusinessPicUrl%>" width="200px"
					height="200px" />
			</div>
			营业执照（或卫生许可证）<a id="showBigBusinessLicensePic"
				href="<%=finalBigBusinessPicUrl%>" target="_blank">查看大图</a>
		</div>
	</div>
	<!--     <div style="clear: both"></div> -->


</div>



<script type="text/javascript">
	$(document).ready(function() {
		$('#operateTimeStart').datepicker();
		$('#operateTimeEnd').datepicker();
		funcCheckExpress();
	    oldForm=$("#busForm").serialize();
	});
	var oldForm="";
	var oldDic=new ActiveXObject("Scripting.Dictionary");

	function funcCheckExpress() {
		var businessId = $('#busiId').val();
		var paramaters = {
			"businessID" : businessId
		};
		
		var url = "<%=basePath%>/business/businessexpress";
		$.ajax({
			type : 'POST',
			async : false,
			url : url,
			data : paramaters,
			success : function(businessExpressRef) {
				if (businessExpressRef.length > 0) {
					for (var i = 0; i < businessExpressRef.length; i++) {
						oldDic.Add(businessExpressRef[i].expressid,businessExpressRef[i].isenable);
						if (businessExpressRef[i].isenable == 1) {
							$(
									":checkbox[id='"
											+ businessExpressRef[i].expressid
											+ "']").prop("checked", true);
						} else {
							$(
									":checkbox[id='"
											+ businessExpressRef[i].expressid
											+ "']").prop("checked", false);
						}
					}
				}
				//保存商家的物流公司设置
				$("input[name='checkMenus']").each(function() {
							if ($(this).is(':checked')) {
								oldDic.Add(parseInt($(this).val()),1);
							} else {
								oldDic.Add(parseInt($(this).val()),0);
							}
				});
			}
		});
	};


	//百度地图
	$("#postion").click(function() {
		$('#MapShow').modal('show');
		var longitude = $('#busiLongitude').val();
		var latitude = $('#busiLatitude').val();
		var map = new BMap.Map("map", {
			enableMapClick : false
		});
		map.centerAndZoom(new BMap.Point(longitude, latitude), 13);
		map.enableScrollWheelZoom();
		var marker = new BMap.Marker(
				new BMap.Point(longitude, latitude)); // 创建点
		map.addOverlay(marker);
		map.addEventListener("click", function(e) {
			map.clearOverlays();
			var markernew = new BMap.Marker(new BMap.Point(e.point.lng,
					e.point.lat)); // 创建新点
			map.addOverlay(markernew);
			var geoc = new BMap.Geocoder();
			var pt = e.point;
			geoc.getLocation(pt, function(rs) {
				var addComp = rs.addressComponents;
				var address = addComp.province + addComp.city
						+ addComp.district + addComp.street
						+ addComp.streetNumber;
				//TODO..城市是否开通
				addComp.city
				//TODO..城区是否存在
				addComp.district

				if (confirm("是否使用地址【" + address + "】？")) {
					$('#busiLongitude').val(e.point.lng);
					$('#busiLatitude').val(e.point.lat);
					$('#busiAddr').val(address);
					$('#MapShow').modal('hide');
				}
			});
		});
	});
	//结算比例选择
	$('input[name="rCommissionFormulaMode"]').click(function() {
		var a = $('input[name="rCommissionFormulaMode"]:checked').val();
		if (a == 1) {
			$("#divCommissionFixValue").hide();
			$("#divbusCommissionText").show();
		} else {
			$("#divbusCommissionText").hide();
			$("#divCommissionFixValue").show();
		}
	});
	//市区联动
	$('#busiCity').change(function() {
		var selDistrictCode = $("#busiDistrict");
		selDistrictCode.empty(); //清除选择的城市信息
		selDistrictCode.append('<option value="" >--区域--</option>');
		if ($(this).val() != null && $(this).val() != "") {
			var url = "<%=basePath%>/business/getCityDistrict";
			var paramaters={cityID : $(this).val()};
			$.ajax({
				type : 'POST',
				url : url,
				data : paramaters,
				success : function(data) {
					$.each(data,function(i,item) {
						selDistrictCode.append('<option  value="' + item.code + '">'
										+ item.name
										+ '</option>');
					});
				}
			});
		}
	});
	//全选
	function checkAll() {
		var checkedOfAll = $("#selectAll").prop("checked");
		$("input[name='checkMenus']").prop("checked", checkedOfAll);
	}

	//保存
	$("#btnModifyCommit").click(function() {
		 var data=$("#busForm").serialize();
		 if(oldForm==data){
			 alert("没有需要更新的信息");
			 return;
		 }
		var id = $('#busiId').val();
		var name = $('#busiName').val();
		var phoneNo2 = $('#busiPhoneNo2').val();
		var landline = $('#busiLandline').val();
		var distribSubsidy = $('#busiWaisong').val();
		var cityId = $('#busiCity').val();
		var districtId = $('#busiDistrict').val();
		var cityName = $('#busiCity option:selected').text();
		var districtName = $('#busiDistrict option:selected')
				.text();
		var adress = $('#busiAddr').val();
		var latitude = $('#busiLatitude').val();
		var longitude = $('#busiLongitude').val();
		var commissionType = $(
				'input[name="rCommissionFormulaMode"]:checked')
				.val();
		var commissionText = $('#busCommissionText').val();
		var commissionFixValue = $('#CommissionFixValue').val();
		var businessGroupId = $('#businessGroupID').val();
		var mealsSettleMode = $('#busiMealsSettleMode').val();
		var groupId = 0;
		var originalBusiId = $('#busiSourceId').val();
		var hidGroupId = $('#hidGroupId').val();
		var oneKeyPubOrder = $(
				'input[name="rOneKeyPubOrder"]:checked').val();
		var IsEmployerTask = $(
				'input[name="rIsEmployerTask"]:checked').val();

		var isAllowOverdraft = $(
				'input[name="rBalanceAllowOverDraft"]:checked')
				.val();
		if (hidGroupId == 0) {
			groupId = $('#busGroupId').val();
		}
		if (name.trim().length == 0) {
			alert("请输入商户名称！");
			return;
		}
		if (name.trim().length > 20) {
			alert("商户名称长度不超过20！");
			return;
		}
		if (originalBusiId.trim().length
				&& isNaN(originalBusiId)) {
			alert("请输入正确的第三方Id!");
			return;
		}
		var reg = /^0?1\d{10}$/;
		var regLandLine = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
		var decimalFormat = /^[0-9]*(\.[0-9]{1,2})?$/;
		if (!reg.test(phoneNo2)) {
			alert("请输入正确的手机号!");
			return;
		}
		if (!regLandLine.test(landline)
				&& landline.trim().length > 0) {
			alert("请输入正确的座机号!");
			return;
		}
		if (!decimalFormat.test(distribSubsidy)
				|| distribSubsidy == '') {
			alert("请输入正确的配送费，且不小于0元！");
			return;
		}
		if (cityId == "") {
			alert("请选择城市！");
			return;
		}

		if (districtId == "") {
			alert("请选择区域！");
			return;
		}
		if (adress.trim().length == 0) {
			alert("请输入详细地址！");
			return;
		}
		if (longitude.trim().length == 0) {
			alert("请输入经度！");
			return;
		}
		if (latitude.trim().length == 0) {
			alert("请输入纬度！");
			return;
		}
		if (commissionType == 1) {
			if (commissionText == ''
					|| Number(commissionText) < 1
					|| Number(commissionText) > 100) {
				alert("结算比例的区间为1-100!");
				return;
			}
		} else {

			if (!decimalFormat.test(commissionFixValue)
					|| commissionFixValue == '') {
				alert("请输入正确的固定金额，且不小于0元!");
				return;
			}
		}
		var paramaters = {
			"id" : id,
			"name" : name,
			"landline" : landline,
			"phoneno2" : phoneNo2,
			"distribsubsidy" : distribSubsidy,
			"cityid" : cityId,
			"districtid" : districtId,
			"city" : cityName,
			"district" : districtName,
			"address" : adress,
			"latitude" : latitude,
			"longitude" : longitude,
			"commissiontype" : commissionType,
			"businesscommission" : commissionText,
			"commissionfixvalue" : commissionFixValue,
			"businessgroupid" : businessGroupId,
			"mealssettlemode" : mealsSettleMode,
			"groupid" : groupId,
			"originalbusiid" : originalBusiId,
			"onekeypuborder" : oneKeyPubOrder,
			"isemployertask" : IsEmployerTask,
			"isallowoverdraft" : isAllowOverdraft
		};
		var url = "<%=basePath%>/business/modifybusiness";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result == 1) {
					alert("修改商户信息成功");
					window.location.href = "<%=basePath%>/business/detail?businessID=<%=detail.getId()%>";
				} else if (result == 0) {
					alert("修改商户信息失败");
				} else {
					alert("没有需要更新的信息");
				}
			}
		});

	});
	//保存物流公司配置
	$("#btnModifyExpress").click(function() {
		var busiId = $('#busiId').val();
		var deliveryCompanyList = "";
		var newDic=	new ActiveXObject("Scripting.Dictionary");//创建对象   
		$("input[name='checkMenus']").each(
				function() {
					if ($(this).is(':checked')) {
						newDic.Add(parseInt($(this).val()),1);
					} else {
						newDic.Add(parseInt($(this).val()),0);
					}
		});
		
		var keys = newDic.Keys().toArray();
		for (var i = 0; i < keys.length; i++) {//发生了改变，才需要提交db
			if (oldDic.Item(keys[i]) != newDic.Item(keys[i])) {
				deliveryCompanyList = deliveryCompanyList + keys[i]
						+ "," + newDic.Item(keys[i]) + ";";
			}
		}
		if(deliveryCompanyList==""){
			alert("物流公司数据没有发生变更，不需要保存");
			return;
		}
		if (!confirm("确定要提交保存吗？")){
			return;
		}
		var paramaters = {
			"busiId" : busiId,
			"deliveryCompanyList" : deliveryCompanyList
		};
		var url = "<%=basePath%>/business/modifyexpress";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result > 0) {
					alert("更新成功");
					window.location.href = window.location.href;
				} else {
					alert("失败");
				}
			}
		});
});
</script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=dAeaG6HwIFGlkbqtyKkyFGEC"></script>