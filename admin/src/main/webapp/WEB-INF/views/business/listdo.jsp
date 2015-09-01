<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.BusinessModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.consts.AuthCode"%>
<%
	String basePath =PropertyUtils.getProperty("static.admin.url");
boolean business_AuditPass=UserContext.getCurrentContext(request).isHasAuth(AuthCode.Business_AuditPass);
boolean business_Modify=UserContext.getCurrentContext(request).isHasAuth(AuthCode.Business_Modify);
boolean business_Recharge=UserContext.getCurrentContext(request).isHasAuth(AuthCode.Business_Recharge);
boolean business_ClienterBind=UserContext.getCurrentContext(request).isHasAuth(AuthCode.Business_ClienterBind);
boolean business_WithDraw=UserContext.getCurrentContext(request).isHasAuth(AuthCode.Business_WithDraw);
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="10%">商户名称</th>
			<th width="100px">电话</th>
			<th width="10%">地址</th>
			<th width="11%">申请时间</th>
			<th width="%5">推荐人手机号</th>
			<th width="70px">所属集团</th>
			<th width="%5">分组</th>
			<th width="70px">审核状态</th>
			<th width="120px">余额</th>
			<th width="150px">结算</th>
			<th>操作</th>
		</tr>
	</thead>

	<tbody>

		<%
			PagedResponse<BusinessModel> responsePageList = (PagedResponse<BusinessModel>)request.getAttribute("listData");
			List<BusinessModel> data=responsePageList.getResultList();
			for (int i = 0; i < data.size(); i++) {
			    String status=""; 
			    String statusStyle="";
			    String statusStyle2="style=\"color:gray\"";
				 switch(data.get(i).getStatus())
				 {
				 case 0:status="未审核";
				 break;
				 case 1:status="已通过";
				 statusStyle="style=\"color:gray\"";
				 statusStyle2="";
				 break;
				 case 2:status="未审核且未添加地址";
				 break;
				 case 3:status="审核中";
				 break;
				 case 4:status="审核被拒绝";
				 break;
				 }
                 int checkAddress = data.get(i).getAddress()==null||data.get(i).getAddress().isEmpty()?0:1;
                 int checkImage = data.get(i).getCheckpicurl()==null||data.get(i).getCheckpicurl().isEmpty()?0:1;
		%>
		<tr>
			<td><%=data.get(i).getId()%></td>
			<td><%=data.get(i).getName()%></td>
			<td><%=data.get(i).getPhoneno()%></td>
			<td><%=data.get(i).getAddress()%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getInserttime(), "")%></td>
			<td><%=data.get(i).getRecommendphone()%></td>
			<td><%=data.get(i).getGroupname()%></td>
			<td><%=data.get(i).getBusinessgroupName()%></td>
			<td><%=status%></td>
			<td>外送费:<%=data.get(i).getDistribsubsidy()%><br> 账户余额:<a
				href="<%=basePath%>/business/balancedetail?businessId=<%=data.get(i).getId()%> ">￥<%=data.get(i).getBalanceprice()%></a><br>
				可提现余额:<%=data.get(i).getAllowwithdrawprice()%></td>
			<td><%=data.get(i).getCommissiontype()==1?"结算比例:"+data.get(i).getBusinesscommission():"结算金额:￥"+data.get(i).getCommissionfixvalue()%><br>
				结算类型:<%=data.get(i).getCommissiontype()==1?"结算比例":"固定金额"%><br>
				餐费结算方式:<%=data.get(i).getMealssettlemode()==0?"线下结算":"线上结算"%></td>
			<td>
			<% if(business_AuditPass){
				%>
			    <a href="javascript:void(0)" <%=statusStyle%> onclick="businessOk(<%=checkAddress%>,<%=data.get(i).getId()%>,<%=data.get(i).getBusinesscommission()%>,<%=checkImage%>,<%=data.get(i).getCommissiontype()%>,<%=data.get(i).getLatitude()%>,<%=data.get(i).getLongitude()%>)">审核通过</a>
				<a href="javascript:void(0)" onclick="businessCancel(<%=data.get(i).getId()%>)" <%=statusStyle2%>>取消资格</a>
				<%}
				if(business_Modify)
				{%>
				<a href="<%=basePath%>/business/detail?businessID=<%=data.get(i).getId()%>">修改信息</a>
				<%}
				if(business_Recharge)
				{%>
				<a href="javascript:void(0)" onclick="businessRecharge(<%=data.get(i).getId()%>,'<%=data.get(i).getName()%>', '<%=data.get(i).getPhoneno()%>')">充值</a>
				<%}
				if(business_ClienterBind)
				{%>
				<a href="/BusinessManager/ClienterBindManage?businessId=<%=data.get(i).getId()%>">骑士绑定</a>
				<%}
				if(business_WithDraw)
				{%>
				<a href="javascript:void(0)" onclick="businessWithdraw(<%=data.get(i).getId()%>,'<%=data.get(i).getName()%>', '<%=data.get(i).getPhoneno()%>')">提款申请</a>
				<%}%>
			</td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
<script type="text/javascript">
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
    function businessOk(checkAddress, businessId, Proportion, checkImage, commissionType, Latitude, Longitude) {
        if (!window.confirm("是否审核通过？")) {
            return;
        }
        if (checkAddress == 0) {
            alert("该商家未填写配送地址，不能通过审核。")
            return;
        }
        if (Proportion < 10 && commissionType == 1) {
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
    
    
    //商户充值
    function businessRecharge(id, name, phone) {
        $('#busId').val(0);
        $('#busName').val('');
        $('#busPhone').val('');
        $('#busRechargeAmount').val('');
        $('#rechargeLog').val('');
        $('#busId').val(id);
        $('#busName').val(name);
        $('#busPhone').val(phone);
        $('#BusinessRechargeShow').modal('show');
    }
  //商户充值
    $("#btnRechargeCommit").on('click', function () {
        var busiId = $("#busId").val(); //商户id
        var busiName = $("#busName").val(); //商户电话
        var busRechargeType = $('#RechargeType').val();//充值类型
        var busiRechargeAmount = $("#busRechargeAmount").val(); //商户充值金额
        var busiRechargeAmountFree = $("#busRechargeAmountFree").val(); //商户赠送金额
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
                tipstr = "确定要为商户：" + busiName + "  充值：" + busiRechargeAmount + "元？"; break;
            case "2":
                tipstr = "确定要为商户：" + busiName + "  赠送：" + busiRechargeAmountFree + "元？"; break;
            case "3":
                tipstr = "确定要为商户：" + busiName + "  充值：" + busiRechargeAmount + " 元,赠送:" + busiRechargeAmountFree + "元？"; break;

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
                    if (result.IsSuccess) {
                        alert(result.Message);
                        window.location.href = "<%=basePath%>/business/list";
                    } else {
                        alert(result.Message);
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
  //商户提现
    $("#btnWithdrawCommit").on('click', function () {
        var withdrawId = $("#withdrawId").val(); //商户id
        var busiName = $("#withdrawPhone").val(); //商户电话
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


