<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.BusinessGroup"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");
List<AreaModel> openCityList=	(List<AreaModel>)request.getAttribute("openCityList");
List<BusinessGroup> businessGroupListData=	(List<BusinessGroup>)request.getAttribute("businessGroupListData");
List<GroupModel> groupListData=	(List<GroupModel>)request.getAttribute("groupListData");
int groupId=(int)request.getAttribute("groupId");
String busname=ParseHelper.ToString(request.getAttribute("businessName"),"");
%>
<!-- 下拉框的样式以及JS -->
<link href="<%=basePath%>/css/plugins/chosen/chosen.css"  rel="stylesheet">
<script src="<%=basePath%>/js/plugins/chosen/chosen.jquery.js" ></script>
<!-- 下拉框的样式以及JS -->
<style type="text/css">
#map_contain {
    height: 90%;
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
			<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店名称: </label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="businessName"  id="businessName" />
							</div>
						</div>
					</div>	
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">审核状态: </label>
							<div class="col-sm-8">
								<select
				name="status"  class="form-control m-b" id="status">
					<option value="-1" selected="selected">全部</option>
					<option value="1">审核通过</option>
					<option value="0">未审核</option>
					<option value="2">未审核且未添加地址</option>
					<option value="3">审核中</option>
					<option value="4">审核被拒绝</option>
			</select>
							</div>
						</div>
					</div>						
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店电话: </label>
							<div class="col-sm-8">
							 <input  class="form-control" id="businessPhone"				type="text" name="businessPhone" />
							</div>
						</div>
					</div>	
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">结算比例: </label>
							<div class="col-sm-8">
							 <input class="form-control"	id="businessSettlementRatio" type="text" name="businessSettlementRatio" />
							</div>
						</div>
					</div>	
				</div>
				<div class="row">					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选城市: </label>
							<div class="col-sm-8">
							 <%=HtmlHelper.getSelectAuto("businessCity", openCityList, "name", "name",null,"","全部")%>
							</div>
						</div>
					</div>	
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店分组: </label>
							<div class="col-sm-8">
							<%=HtmlHelper.getSelect("businessGroupId", businessGroupListData, "name", "id",0,"0","全部")%>
							</div>
						</div>
					</div>	
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">结算类型: </label>
							<div class="col-sm-8">
						 <select name="commissionType"
				 class="form-control m-b" id="commissionType" >
					<option value="-1" selected="selected">全部</option>
					<option value="1">固定比例</option>
					<option value="2">固定金额</option>
			</select> 
							</div>
						</div>
					</div>	
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">餐费结算方式: </label>
							<div class="col-sm-8">
							<select name="mealsSettleMode"
				 class="form-control m-b" id="mealsSettleMode">
					<option value="-1" selected="selected">全部</option>
					<option value="0">线下结算</option>
					<option value="1">线上结算</option>
			</select>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">	
				<div class="col-lg-3">				
						<div class="form-group">
							<label class="col-sm-4 control-label">推荐人电话: </label>
							<div class="col-sm-8">
							<input class="form-control" id="recommendPhone"type="text" name="recommendPhone" />
							</div>
						</div>
					</div>
				</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>				
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


<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content" id="content">
			
				</div>
			</div>
		</div>
	</div>

<div tabindex="-1" class="modal inmodal" id="BusinessRechargeShow"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">门店充值</h4>
				<!-- 				<small class="font-bold">这里可以显示副标题。 </small> -->
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">
                <label>商家名称：</label>
                <input name="busName" id="busName" disabled="disabled" type="text">
                <input name="busId" id="busId" type="hidden">
            </div>
            <div class="control-group">
                <label>商家电话：</label>
                <input name="busPhone" id="busPhone" disabled="disabled" type="text">
            </div>
            <div class="control-group">
                <label>充值类型：</label> <select id="RechargeType"  class="form-control m-b">
                    <option value="1">充值</option>
                    <option value="2">赠送</option>
                    <option value="3">充值+赠送</option>
                </select>
            </div>
            <div class="control-group">
                <div id="busRechargeAmountDiv">
                    <label>充值金额：</label>
                    <input name="busRechargeAmount" id="busRechargeAmount" type="text">元
                    <label style="font-size: 10px;color: red">（充值金额范围:1.00-50000.00元）</label><br />
                </div>
                <div id="busRechargeAmountFreeDiv">
                    <label>赠送金额：</label>
                    <input name="busRechargeAmountFree" id="busRechargeAmountFree" type="text">元
                    <label style="font-size: 10px; color: red">（赠送金额范围:1.00-50000.00元）</label>
                </div>
            </div>
            <div class="control-group">
                <label>操作描述：</label>
                <div class="controls">
                    <textarea cols="45" rows="5" id="rechargeLog"></textarea>
                </div>
            </div>
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnRechargeCommit">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>

<div tabindex="-1" class="modal inmodal" id="BusinessWithdraw"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">发起门店提款申请</h4>
				<!-- 				<small class="font-bold">这里可以显示副标题。 </small> -->
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>门店名称：</label> <input name="withdrawName" id="withdrawName"
								disabled="disabled" type="text"> <input
								name="withdrawId" id="withdrawId" type="hidden">
						</div>
						<div class="control-group">
							<label>登录账号：</label> <input name="withdrawPhone"
								id="withdrawPhone" disabled="disabled" type="text">
						</div>
						<div class="control-group">
							<label>提款金额：</label> <input name="withdrawAmount"
								id="withdrawAmount" type="text">元 <label
								style="font-size: 10px; color: red">（提款金额范围:1-1,000,000元）</label>
						</div>
						<div class="control-group">
							<label>省：</label> <input type="text" disabled="disabled"
								id="openProvince" /> <input type="hidden" id="openProvinceCode" />
						</div>
						<div class="control-group">
							<label>市区：</label> <input type="text" disabled="disabled"
								id="openCity" /> <input type="hidden" id="openCityCode" /> 
						</div>
						<div class="control-group">
							<label>身份证号/营业执照：</label> <input type="text" id="idCard"
								disabled="disabled" />
						</div>
						<div class="control-group">
							<label>账户类型：</label> <input type="text" id="accountType"
								disabled="disabled" />
						</div>

						<div class="control-group">
							<label>备注：</label>
							<div class="controls">
								<textarea cols="45" rows="5" id="withdrawLog"></textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnWithdrawCommit">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<script>
var jss={
		search:function(currentPage){
			$("#_hiddenCurrentPage").val(currentPage);
			 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/business/selectlist",data,function(d){
				$("#content").html(d);
			});
		}
	}
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>
