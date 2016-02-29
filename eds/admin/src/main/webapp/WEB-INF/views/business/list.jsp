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
			<div  id="content"></div>
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
                <label>充值类型：</label> <select id="RechargeType"  class="form-control m-b" style="width: 251px;">
                    <option value="1">充值</option>
                    <option value="2">赠送</option>
                    <option value="3" selected="selected">充值+赠送</option>
                </select>
            </div>
            <div class="control-group">
                <div id="busRechargeAmountDiv" >
                    <label>充值金额：</label>
                    <input name="busRechargeAmount" id="busRechargeAmount" type="text" value="0">元
                    <label style="font-size: 10px;color: red">（充值金额范围:1.00-50000.00元）</label><br />
                </div>
                <div id="busRechargeAmountFreeDiv">
                    <label>赠送金额：</label>
                    <input name="busRechargeAmountFree" id="busRechargeAmountFree" type="text" value="0">元
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

function businessCancel(businessId)
{
	if (!window.confirm("是否取消审核？")) {
        return;
    }
    var paramaters = { "businessID": businessId,"status":4 };
    var url = "<%=basePath%>/business/audit";
    $.ajax({
        type: 'POST',
        url: url,
        data: paramaters,
        success: function (result) {
        	if (result>0) {
                window.location.href = "<%=basePath%>/business/list";
            } else {
                alert("操作失败");
            }
        }
    });
}
///操作审核验证
function businessOk(checkAddress, businessId, Proportion, checkImage, commissionType, Latitude, Longitude,ReceivableType) {
    if (!window.confirm("是否审核通过？")) {
        return;
    }
    if (checkAddress == 0) {
        alert("该商家未填写配送地址，不能通过审核。")
        return;
    }
    if (Proportion < 10 && commissionType == 1&&ReceivableType==1) {
        alert("该商家结算比例小于10%，不能通过审核。")
        return;
    }
    if (checkImage == 0) {
        alert("该商家未上传图片，不能通过审核。")
        return;
    }
    //debugger
    var lng = parseFloat(Longitude);
    var lat = parseFloat(Latitude);
    if (lng<73.40||lng>135.230||lat<3.52||lat>53.33) {
        alert("该商家地址有误，不能通过审核。")
        return;
    }
    var paramaters = { "businessID": businessId,"status":1 };
    var url = "<%=basePath%>/business/audit";
    $.ajax({
        type: 'POST',
        url: url,
        data: paramaters,
        success: function (result) {
            if (result>0) {
                window.location.href = "<%=basePath%>/business/list";
            } else {
                alert("操作失败");
            }
        }
    });
}

//注册充值下拉框改变事件
$('#RechargeType').change(function () {
    $('#busRechargeAmount').val('');
    $('#busRechargeAmountFree').val('');
    var tempvalue = $('#RechargeType').val();
    if (tempvalue == '1') {
        //充值显示 赠送隐藏
        $('#busRechargeAmountDiv').show();
        $('#busRechargeAmountFreeDiv').hide();
    }
    if (tempvalue == '2') {
        //充值隐藏 赠送显示
        $('#busRechargeAmountDiv').hide();
        $('#busRechargeAmountFreeDiv').show();
    }
    if (tempvalue == '3') {
        //充值显示 赠送隐藏
        $('#busRechargeAmountDiv').show();
        $('#busRechargeAmountFreeDiv').show();
    }
});
//门店充值
function businessRecharge(id, name, phone) {
    $('#busId').val(0);
    $('#busName').val('');
    $('#busPhone').val('');
    $('#busRechargeAmount').val('0');
    $('#busRechargeAmountFree').val('0');
    $('#rechargeLog').val('');
    $('#busId').val(id);
    $('#busName').val(name);
    $('#busPhone').val(phone);
    $('#BusinessRechargeShow').modal('show');
}
//门店充值
$("#btnRechargeCommit").on('click', function () {
    var busiId = $("#busId").val(); //门店id
    var busiName = $("#busName").val(); //门店电话
    var busRechargeType = $('#RechargeType').val();//充值类型
    var busiRechargeAmount = $("#busRechargeAmount").val(); //门店充值金额
    var busiRechargeAmountFree = $("#busRechargeAmountFree").val(); //门店赠送金额
    var rechargeLog = $("#rechargeLog").val(); //充值描述
    if (rechargeLog.trim().length == 0) {
        alert("请输入充值操作描述!");
        return;
    }
    var decimalFormat = /^[0-9]*(\.[0-9]{1,2})?$/;
    if ((busRechargeType == '1' || busRechargeType == '3') && !decimalFormat.test(busiRechargeAmount)) {
        alert("请输入正确的充值金额！");
        return;
    }
    if ((busRechargeType == '1' || busRechargeType == '3') && (busiRechargeAmount < 1 || busiRechargeAmount > 50000)) {
        alert("充值金额须在1-50000元之间！");
        return;
    }
    if ((busRechargeType == '2' || busRechargeType == '3') && !decimalFormat.test(busiRechargeAmountFree)) {
        alert("请输入正确的赠送金额！");
        return;
    }
    if ((busRechargeType == '2' || busRechargeType == '3') && (busiRechargeAmountFree < 1 || busiRechargeAmountFree > 50000)) {
        alert("赠送金额须在1-50000元之间！");
        return;
    }
    var tipstr = "";
    switch (busRechargeType) {
        case "1":
            tipstr = "确定要为门店：" + busiName + "  充值：" + busiRechargeAmount + "元？"; break;
        case "2":
            tipstr = "确定要为门店：" + busiName + "  赠送：" + busiRechargeAmountFree + "元？"; break;
        case "3":
            tipstr = "确定要为门店：" + busiName + "  充值：" + busiRechargeAmount + " 元,赠送:" + busiRechargeAmountFree + "元？"; break;

    }
    if (confirm(tipstr)) {
        var paramaters = {
            "businessId": busiId,
            "rechargeAmount": busiRechargeAmount,
            "remark": rechargeLog,
            "rechargeAmountFree": busiRechargeAmountFree,
            "rechargeType": busRechargeType
        };
        var url = "<%=basePath%>/business/recharge";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result==1) {
                    alert('操作成功!');
                    window.location.href = "<%=basePath%>/business/list";
                } else {
                    alert('操作失败!');
                }
            }
        });
    }
});
function businessWithdraw(busiId, name, phone) { 
    var paramaters = {
        "businessID": busiId
    };
    $.ajax({
        type: 'POST',
        async:true,
        url: '<%=basePath%>/business/getfinanceaccount',
        data: paramaters,
        success: function (bfa) {
            if (bfa != null&&bfa!="") {
                $("#openProvince").val(bfa.openProvince);
                $("#openProvinceCode").val(bfa.openProvinceCode);
                $("#openCity").val(bfa.openCity);
                $("#openCityCode").val(bfa.openCityCode);
                $("#idCard").val(bfa.idcard);
                if (bfa.belongtype == 0) {
                    $("#accountType").val('个人账户');
                } else if (bfa.belongtype == 1) {
                    $("#accountType").val('公司账户');
                } else {
                    $("#accountType").val('未知');
                }
            }
            ClearFinanceAccount(busiId, name, phone);
        }
    });
}
function ClearFinanceAccount(busiId, name, phone) {
    $('#withdrawId').val(0);
    $('#withdrawName').val('');
    $('#withdrawPhone').val('');
    $('#withdrawAmount').val('');
    $('#withdrawLog').val('');
    $('#withdrawId').val(busiId);
    $('#withdrawName').val(name);
    $('#withdrawPhone').val(phone);
    $('#BusinessWithdraw').modal('show');
}
//门店提现
$("#btnWithdrawCommit").on('click', function () {
    var withdrawId = $("#withdrawId").val(); //门店id
    var busiName = $("#withdrawPhone").val(); //门店电话
    var withdrawAmount = $("#withdrawAmount").val();
    var withdrawLog = $("#withdrawLog").val();
    var selectProvinceName = $("#openProvince").val();
    var selectProvinceCode = $("#openProvinceCode").val();
    var selectCityName = $("#openCity").val();
    var selectCityCode = $("#openCityCode").val();
    var idCard = $("#idCard").val();
	if(idCard==""){
	    alert("身份证号/营业执照为空，不能提现！");
	    return;
	} 
    var decimalFormat = /^[0-9]*(\.[0-9]{1,2})?$/;
    if (!decimalFormat.test(withdrawAmount)) {
        alert("请输入正确的金额！");
        return;
    }
    if (withdrawAmount < 1 || withdrawAmount > 1000000) {
        alert("请输入正确的提款金额，大于1元小于1,000,000！");
        return;
    }

    if (confirm("是否确认提款?")) {
        var paramaters = {
            "BusinessId": withdrawId,
            "WithdrawPrice": withdrawAmount,
            "Remarks": withdrawLog,
            "OpenProvinceCode": selectProvinceCode,
            "OpenProvince": selectProvinceName,
            "selectCityCode": selectCityCode,
            "OpenCity": selectCityName,
            "idCard": idCard
        };
        var url = "<%=basePath%>/business/withdraw";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {

                if (result.Status == "1") {
                    alert(result.Message);
                    window.location.href = "<%=basePath%>/business/list";
                } else {
                    alert(result.Message);
                }
            }
        });
    }
});
</script>
