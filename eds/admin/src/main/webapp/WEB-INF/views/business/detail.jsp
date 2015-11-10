<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.BusinessGroup"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.entity.domain.BusinessThirdRelationModel"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="com.edaisong.entity.BusinessOptionLog"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");
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
 <ul class="nav nav-tabs" id="myTab">
      <li class="active"><a href="#tabjbxx">基本信息</a></li>
      <li><a href="#tabqtsz">其他设置</a></li>
      <li><a href="#tabqdgl">渠道管理</a></li>
      <li><a href="#tabwlgs">物流公司</a></li>
      <li><a href="#tabbq">标签</a></li>
      <li><a href="#tabczjl">操作记录</a></li>
    </ul>
       
    <div class="tab-content">
      <div class="tab-pane active" id="tabjbxx">
      <!-- 基本信息开始 -->
      <form id="formjbxx">
	  	<div id="left" style="float: left; width: 750px; height: auto; border-right: 1px #dcdcdc solid; margin-top: -10px;">
	  	  <div style="width: 750px; height: auto; border-bottom: solid 0px #dcdcdc; padding-left: 10px;">
			<div class="control-group" style="margin-top: 10px">
				<label>门店账号：</label> <label><%=detail.getPhoneno()%></label>
			</div>
			<div class="control-group">
				<label>商铺名称：</label> <input name="busiName" id="busiName"
					type="text" value="<%=detail.getName()%>"> <input
					name="busiId" id="busiId" type="hidden" value="<%=detail.getId()%>">
			</div>
			<div class="control-group" style="margin-top: 10px">
                    <label>推荐人账号：</label>
                    <input name="RecommendPhone" id="recomPhone" type="text" value="<%=detail.getRecommendphone()%>" />
            </div>
			<div class="control-group">
				<label>联系电话：</label> <input name="busiPhoneNo2" id="busiPhoneNo2" type="text" value="<%=detail.getPhoneno2()%>">
			</div>
			<div class="control-group">
				<label>联系座机：</label> <input name="busiLandline" id="busiLandline" type="text" value="<%=detail.getLandline()==null?"":detail.getLandline()%>">
			</div>
			<div class="control-group">
				<label style="margin-left: 20px">城 市：</label>
				<%=HtmlHelper.getSelect("busiCity", openCityList, "name", "code",detail.getCityid(),"","-请选择-")%>
				<label>区 域：</label>
				<%=HtmlHelper.getSelect("busiDistrict", openAreaList, "name", "code",detail.getDistrictid(),"","-请选择-")%>
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
			<div class="SearchMd" style="padding: 0px; border-bottom: solid 0px #dcdcdc;">
					<div >
						<input type="button" value="保存" class="searchBtn"
							id="btnModifyCommit" />
					</div>
			</div>
			
	  	  </div>
	  	
	  	 </div>
	  	 </form>
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
		
	  <!-- 基本信息结束 -->
	  </div>
		

      <div class="tab-pane" id="tabqtsz">
      <!-- 其他设置开始 -->
	      <form action="" id="formqtsz">
	      		<div class="control-group">
					<label style="margin-left: 73px">发单模式：</label> 
					<input id="rPushOrderTypeN" name="rPushOrderType" type="radio" value="0" style="margin-left: 30px" <%=detail.getPushOrderType()==0?"checked" : ""%>> 
					<label>普通模式</label>
					<input id="rPushOrderTypeY" name="rPushOrderType" type="radio" value="1" <%=detail.getPushOrderType()==1?"checked" : ""%>> 
					<label>快单模式</label>
				</div>
				<div class="control-group">
	                    <label style="margin-left: 73px">应收门店：</label>
	                    <label title="应收商家配送费比例">商配比例</label><input id="busCommissionText" name="busCommissionText" style="width:100px" type="text" value="<%=detail.getBusinesscommission()%>" />
	                    %&nbsp;+&nbsp;<label title="应收商家配送费定额">商配定额</label><input id="CommissionFixValue" name="CommissionFixValue" style="width:100px" value="<%=detail.getCommissionfixvalue()%>" />&nbsp;+&nbsp;
	                    <label title="代收客户配送费">代收客配</label><input id="busiWaisong" id="busiWaisong" style="width:100px" value="<%=detail.getDistribsubsidy()%>" />
	            </div>
	            <div class="control-group" style="margin-left: 27px">
					<label id="labGlobalConfig" style="margin-left: 48px"><%=subsidyConfig%></label><!-- 全局补贴 -->
				</div>
				<div class="control-group">
					<label style="margin-left: 74px">补贴策略：</label>
					<%=HtmlHelper.getSelect("businessGroupID", businessGroupListData, "name", "id",detail.getBusinessgroupid(),null,"")%>
				</div>
				<div class="control-group" style="margin-left: 2px">
					<label style="margin-left: 48px">餐费结算方式：</label> 
					<select name="busiMealsSettleMode"  class="form-control m-b" id="busiMealsSettleMode" >
						<option <%=detail.getMealssettlemode()==0?"selected":""%> value="0">线下结算</option>
						<option <%=detail.getMealssettlemode()==1?"selected":""%> value="1">线上结算</option>
					</select>
				</div>
	      		<div class="control-group">
					<label style="margin-left: 73px">一键发单：</label> 
					<input id="rOneKeyPubOrderY" name="rOneKeyPubOrder" type="radio" value="1" <%=detail.getOnekeypuborder()==1?"checked" : ""%>>
					<label>是</label>
					<input id="rOneKeyPubOrderN" name="rOneKeyPubOrder" type="radio" value="0" style="margin-left: 30px" <%=detail.getOnekeypuborder()==0?"checked" : ""%>> 
					<label>否</label>
				</div>
				<div class="control-group">
					<label style="margin-left: 48px">余额可以透支：</label> 
					<input id="rBbalanceAllowOverDraftY" name="rBalanceAllowOverDraft" type="radio" value="1" <%=detail.getIsallowoverdraft()==1?"checked" : ""%>>
					<label>是</label>
					<input id="rBbalanceAllowOverDraftN" name="rBalanceAllowOverDraft" type="radio" value="0" style="margin-left: 30px" <%=detail.getIsallowoverdraft()==0?"checked" : ""%>> 
					<label>否</label>
				</div>
				<div class="control-group">
	                    <label style="margin-left: 48px">是否可以现金支付：</label>
	                    <input id="rIsAllowCashPayY" name="rIsAllowCashPay" type="radio" value="1" <%=detail.getIsAllowCashPay()==1?"checked" : ""%>>
	                    <label>是</label>
	                    <input id="rIsAllowCashPayN" name="rIsAllowCashPay" type="radio" value="0" style="margin-left: 30px" <%=detail.getIsAllowCashPay()==0?"checked" : ""%>>
	                    <label>否</label>
	            </div>
				<div class="control-group">
					<label>使用雇主任务时间限制：</label> <input id="rIsEmployerTaskY"
						name="rIsEmployerTask" type="radio" value="1"
						<%=detail.getIsemployertask()==1?"checked" : ""%>> <label>是</label>
					<input id="rIsEmployerTaskN" name="rIsEmployerTask" type="radio"
						value="0" style="margin-left: 30px"
						<%=detail.getIsemployertask()==0?"checked" : ""%>> <label>否</label>
				</div>
				<div class="control-group" style="margin-bottom: 10px" id="OrderChecked">
	                <label style="margin-left: 5px">订单是否需要审核：</label>
	                <label><input name="IsCheckOrder" type="radio" value="1" <%=detail.getIsOrderChecked()==1?"checked" : ""%> />是 </label>
	                <label><input name="IsCheckOrder" type="radio" value="0" <%=detail.getIsOrderChecked()==0?"checked" : ""%> />否 </label> 
	            </div>	
				
	            </form>
	            <div class="SearchMd" style="padding: 0px; border-bottom: solid 0px #dcdcdc;">
						<div >
							<input type="button" value="保存" class="searchBtn"	id="btnsaveqtsz" />
						</div>
				</div>
	      <!-- 其他设置结束 -->
      </div>
      <div class="tab-pane" id="tabqdgl">
      <!-- 渠道管理开始 -->
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
					<%=HtmlHelper.getSelect("busGroupId", modifyGroupListData, "groupname", "id",detail.getBusinessgroupid(),"0","--请选择--")%>
					<%
						}
					%>
			</div>
	  <!-- 渠道管理结束-->
	  </div>
      <div class="tab-pane" id="tabwlgs">
	      <!-- 物流公司开始 -->
	      <form id="formwlgs">
			<div id="deliveryCompanyList" style="float: left; width: 550px">
				<%
					if (deliveryCompany != null && deliveryCompany.size() > 0)
								            {
				%>
				<div style="width: 180px; height: 25px; float: left">
					<input type="checkbox" name="checkAllMenus" id="selectAll"	onclick="checkAll()" />
					<label>全部</label>
				</div>
				<%
					for (DeliveryCompany item : deliveryCompany) {
					String check = item.getDeliverycompanycode()==detail.getRecommendphone() ? "checked='checked'" : "";
				%>
				<div style="width: 180px; height: 25px; float: left">
					<input type="checkbox" name="checkMenus" id="<%=item.getId()%>"
						value="<%=item.getId()%>" <%=check%> /> <label><%=item.getDeliverycompanyname()%></label>
				</div>
				<%
					}
								            }
				%>
				 <div class="SearchMd" style="padding: 0px; border-bottom: solid 0px #dcdcdc;">
						<div >
							<input type="button" value="保存" class="searchBtn"	id="btnModifyExpress" />
						</div>
				</div>
			</div>
		
		  </form>
	      <!-- 物流公司结束 -->
      </div>
      <div class="tab-pane" id="tabbq">标签</div>
      <div class="tab-pane" id="tabczjl">
      <!-- 操作记录开始 -->
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
      <!-- 操作记录结束 -->
      </div>
    </div>   
<script type="text/javascript">

	var formjbxx="";//基本信息表单
	var formqtsz="";//其他设置表单
	var reg = /^0?1\d{10}$/;//手机号正则
	var regLandLine = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;//座机正则
	var decimalFormat = /^[0-9]*(\.[0-9]{1,2})?$/;//金额数字正则
	var oldbusinessgroupidname="";
	$(document).ready(function() {
		//初始化显示哪个tab
		 $('#myTab a:first').tab('show');
	     $('#myTab a').click(function (e) {
	          e.preventDefault();//阻止a链接的跳转行为
	          $(this).tab('show');//显示当前选中的链接及关联的content
	        })
	    //初始化时间控件
		$('#operateTimeStart').datepicker();
		$('#operateTimeEnd').datepicker();
		//初始化物流公司信息选择框
		funcCheckExpress();
		//初始化各个页签下的表单记录
	    formjbxx=$("#formjbxx").serialize();
		formqtsz=$("#formqtsz").serialize();
		oldbusinessgroupidname=$('#businessGroupID option:selected').text();//保存旧的补贴策略名字
		console.log(formjbxx);
	});
    var oldValues= new Array();  
    var businessId = $('#busiId').val();//商户ID
	//初始化物流信息数据
	function funcCheckExpress() {
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
                $("input[name='checkMenus']").each(function () {
                    if ($(this).is(':checked')) {
                        oldValues.push(1);
                    } else {
                        oldValues.push(0);
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

	//基本信息保存
	$("#btnModifyCommit").click(function() {
		 var data=$("#formjbxx").serialize();;
		 if(formjbxx==data){
			 alert("没有需要更新的信息");
			 return;
		 }
		var id = $('#busiId').val();//BID
		var name = $('#busiName').val();//商家名称
		var phoneNo2 = $('#busiPhoneNo2').val();//联系电话
		var recommendPhone=$('#recomPhone').val();//推荐人电话
		var landline = $('#busiLandline').val();//联系座机
		var cityId = $('#busiCity').val();//城市ID
		var districtId = $('#busiDistrict').val();//区域ID
		var cityName = $('#busiCity option:selected').text();//城市名称
		var districtName = $('#busiDistrict option:selected').text();//区域名称
		var adress = $('#busiAddr').val();//地址
		var latitude = $('#busiLatitude').val();//坐标
		var longitude = $('#busiLongitude').val();//坐标

		if (name.trim().length == 0) {
			alert("请输入商户名称！");
			return;
		}
		if (name.trim().length > 20) {
			alert("商户名称长度不超过20！");
			return;
		}
		if (!reg.test(phoneNo2)) {
			alert("请输入正确的手机号!");
			return;
		}
		if (!regLandLine.test(landline)&& landline.trim().length > 0) {
			alert("请输入正确的座机号!");
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
		var paramaters = {
			"id" : id,
			"name" : name,
			"landline" : landline,
			"phoneno2" : phoneNo2,
			"cityid" : cityId,
			"districtid" : districtId,
			"city" : cityName,
			"district" : districtName,
			"address" : adress,
			"latitude" : latitude,
			"longitude" : longitude,
			"recommendphone":recommendPhone
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
	
	//其他设置保存
	$('#btnsaveqtsz').click(function(){
		var data=$("#formqtsz").serialize();;
		 if(formqtsz==data){
			 alert("没有需要更新的信息");
			 return;
		 }
		 var id = $('#busiId').val();//BID
		 var pushOrderType=$('input[name="rPushOrderType"]:checked').val();//发单模式
		 var commissionText = $('#busCommissionText').val();//商配比例
		 var commissionFixValue = $('#CommissionFixValue').val();//商配定额
		 var distribSubsidy = $('#busiWaisong').val();//代收客配
		 var businessGroupId = $('#businessGroupID').val();//补贴策略分组ID
		 var businessGroupIdtext = $('#businessGroupID option:selected').text();//补贴策略分组名字
		 var mealsSettleMode = $('#busiMealsSettleMode').val();//餐费结算方式
		 var oneKeyPubOrder = $('input[name="rOneKeyPubOrder"]:checked').val();//一键发单
		 var IsEmployerTask = $('input[name="rIsEmployerTask"]:checked').val();//雇主任务时间限制
		 var isAllowOverdraft = $('input[name="rBalanceAllowOverDraft"]:checked').val();//余额是否可以透支
		 var isAllowCashPay = $('input[name="rIsAllowCashPay"]:checked').val();//现金支付 
		 if (isNaN(parseFloat(distribSubsidy)) || parseFloat(distribSubsidy) < 0 || parseFloat(distribSubsidy) >100) {
	            alert("代收客配必须在0-100之间");
	            return;
	        }
	 	 if (isNaN(parseFloat(commissionText)) || parseFloat(commissionText) < 0 || parseFloat(commissionText) > 100) {
	            alert("商配比例数值必须在0-100之间");
	            return;
	        }
	 	if (isNaN(parseFloat(commissionFixValue)) || parseFloat(commissionFixValue) < 0 || parseFloat(commissionFixValue)>100) {
            alert("商配定额必须在0-100之间");
            return;
        }
		var paramaters = {
				"id" : id,
				"pushOrderType":pushOrderType,//发单模式
	 			"distribsubsidy" : distribSubsidy,//代收客配
	 			"businesscommission" : commissionText,//商配比例
	 			"commissionfixvalue" : commissionFixValue,//商配定额
	 			"businessgroupid" : businessGroupId,//补贴策略组ID
	 			"mealssettlemode" : mealsSettleMode,//餐费结算方式
	 			"onekeypuborder" : oneKeyPubOrder,//一键发单
	 			"isemployertask" : IsEmployerTask,//雇主任务
	 			"isallowoverdraft" : isAllowOverdraft,//允许透支
	 			"isOrderChecked":$('#OrderChecked input[name="IsCheckOrder"]:checked ').val(),//订单审核
	 			"isAllowCashPay":isAllowCashPay,
	 			"businessgroupidname" : businessGroupIdtext,//补贴策略组ID
	 			"oldbusinessgroupidname":oldbusinessgroupidname//旧的补贴策略名字
			};
		console.log(paramaters);
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
		 
	});//其他保存事件结束
	
	//保存物流公司配置
	$("#btnModifyExpress").click(function() {
		var busiId = $('#busiId').val();
		var deliveryCompanyList = "";
		var newKeys = new Array();
        var newValues = new Array();
        $("input[name='checkMenus']").each(
                function () {
                    newKeys.push($(this).val());
                    if ($(this).is(':checked')) {
                        newValues.push(1);
                    } else {
                        newValues.push(0);
                    }
                });


        for (var i = 0; i < newKeys.length; i++) {//发生了改变，才需要提交db
            if (oldValues[i] != newValues[i]) {
                deliveryCompanyList = deliveryCompanyList + newKeys[i]
                        + "," + newValues[i] + ";";
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