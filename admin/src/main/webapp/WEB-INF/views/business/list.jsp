<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.BusinessModel"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>
<%@page import="com.edaisong.core.common.PageHelper"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%
	String basePath =new PropertyUtils().getProperty("static.admin.url");
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
				href="/BusinessManager/BusinessDetail?businessId=<%=data.get(i).getId()%> ">￥<%=data.get(i).getBalanceprice()%></a><br>
				可提现余额:<%=data.get(i).getAllowwithdrawprice()%></td>
			<td><%=data.get(i).getCommissiontype()==1?"结算比例:"+data.get(i).getBusinesscommission():"结算金额:￥"+data.get(i).getCommissionfixvalue()%><br>
				结算类型:<%=data.get(i).getCommissiontype()==1?"结算比例":"固定金额"%><br>
				餐费结算方式:<%=data.get(i).getMealssettlemode()==0?"线下结算":"线上结算"%>
			</td>
			<td><a href="javascript:void(0)" <%=statusStyle%>
				onclick="businessOk(<%=checkAddress%>,<%=data.get(i).getId()%>,<%=data.get(i).getBusinesscommission()%>,<%=checkImage%>,<%=data.get(i).getCommissiontype()%>,<%=data.get(i).getLatitude()%>,<%=data.get(i).getLongitude()%>)"
				businessid="<%=data.get(i).getId()%>" class="businessOk">审核通过</a> <a
				href="javascript:void(0)" <%=statusStyle2%>
				businessid="<%=data.get(i).getId()%>" class="businessCel">取消资格</a> <a
				href="<%=basePath%>/business/detail?businessID=<%=data.get(i).getId()%>">修改信息</a>
				<a href="javascript:void(0)" data-toggle="modal"
				data-target="#BusinessRechargeShow"
				onclick="funcBusinessRecharge(<%=data.get(i).getId()%>,'<%=data.get(i).getName()%>', '<%=data.get(i).getPhoneno()%>')">充值</a>
				<a
				href="/BusinessManager/ClienterBindManage?businessId=<%=data.get(i).getId()%>">骑士绑定</a>
				<a href="javascript:void(0)" data-toggle="modal"
				data-target="#BusinessWithdraw"
				onclick="funcBusinessWithdraw(<%=data.get(i).getId()%>,'<%=data.get(i).getName()%>', '<%=data.get(i).getPhoneno()%>')">提款申请</a>
			</td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>
<%=PageHelper.GetPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
<script type="text/javascript">
    var currentId;
    $(document).ready(function () {
        $(".businessCel").bind("click", function () {
            if (!window.confirm("是否取消审核？")) {
                return;
            }
            currentId = $(this).attr("businessId");
            var paramaters = { "id": currentId };
            var url = "/BusinessManager/AuditCel";
            $.ajax({
                type: 'POST',
                url: url,
                data: paramaters,
                success: function (result) {
                    if (result.IsSuccess) {
                        window.location.href = "/BusinessManager/BusinessManager";
                    } else {
                        alert(result.Message);
                    }
                }
            });
        });
    });
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
        //currentId = $(this).attr("businessId");
        var paramaters = { "id": businessId };
        var url = "/BusinessManager/AuditOK";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result.IsSuccess) {
                    window.location.href = "/BusinessManager/BusinessManager";
                } else {
                    alert(result.Message);
                }
            }
        });
    }
    function funcPicView(puth, CheckPicUrl) {
        $('#showBusiImage').attr('src', puth + CheckPicUrl);
        var originSize = '_0_0';
        var fileLastDot = CheckPicUrl.lastIndexOf('.');
        var fileHandHouZhui = CheckPicUrl.substr(fileLastDot, CheckPicUrl.length - fileLastDot);
        var bigFileName = CheckPicUrl.substring(0, fileLastDot) + originSize + fileHandHouZhui;
        $('#showBigBusiImage').attr('href', puth + bigFileName);
        adminjs.openwinbox('#BusiPicShow');
        if (CheckPicUrl.length == 0) {
            $('#showBigBusiImage').hide();
        } else {
            $('#showBigBusiImage').show();
        }
    }

    //弹出结算比例-外送费层
    function funcComView(id, name, distribSubsidy, phone, businessCommission, commissionType, commissionFixValue, businessGroupId) {
        if (businessCommission == null || businessCommission == "") {
            businessCommission = 0;
        }
        var strreg = "";
        var paramaters = { "GroupId": businessGroupId };
        var url = "/SubsidyFormulaMode/GlobalConfigInfo";
        $.ajax({
            type: 'POST',
            async: false,
            url: url,
            data: paramaters,
            success: function (result) {
                strreg = result;
            }
        });

        var isStarTimeSubsidies = strreg.split(",")[0];
        var isStartOverStoreSubsidies = strreg.split(",")[1];
        var subsidyConfig;
        if (isStarTimeSubsidies == "1" || isStartOverStoreSubsidies == "1") {
            if (isStartOverStoreSubsidies == "1") {
                subsidyConfig = "全局补贴：跨店抢单奖励";
            }
            if (isStarTimeSubsidies == "1") {
                if (subsidyConfig == null || subsidyConfig.length == 0) {
                    subsidyConfig = "全局补贴：动态时间奖励";
                }
                else {
                    subsidyConfig = "全局补贴：跨店抢单奖励和动态时间奖励";
                }
            }
            $('#labGlobalConfig').text(subsidyConfig);
        }
        $('#busCommissionHid').val(id);
        $('#busCommissionName').val(name);
        $('#busCommissionWaiSong').val(distribSubsidy);
        $('#oldBusCommissionWaiSong').val(distribSubsidy);
        $('#busCommissionPhone').val(phone);
        $('#busCommissionText').val(businessCommission);
        $('#oldBusCommissionText').val(businessCommission);
        //$('#statusFin').text("设置[" + name + "]结算比例-外送费");
        $('#CommissionFixValue').val(commissionFixValue);
        $('#oldCommissionFixValue').val(commissionFixValue);
        $('#oldStrategyID').val(businessGroupId);
        $('#oldCommissionType').val(commissionType);
        if (commissionType == 2) {
            $("#rCommissionFormulaMode1").attr("checked", "checked");
        }
        else {
            $("#rCommissionFormulaMode0").attr("checked", "checked");
        }

        if (businessGroupId > 0) {

            $('#businessGroupID').val(businessGroupId);
        }
        else {
            $('select#businessGroupID option:first').attr('selected', 'true');
        }
        var a = $('input[name="rCommissionFormulaMode"]:checked').val();
        if (a == 1) {
            $("#divCommissionFixValue").hide();
            $("#divbusCommissionText").show();
        }
        else {
            $("#divbusCommissionText").hide();
            $("#divCommissionFixValue").show();
        }
        adminjs.openwinbox('#BusinessCommissionDiv');
    }
    //修改商家信息
    function funcUpdateBusinessInfo(id, name, phone, originalbusiId, groupid, mealsSettleMode) {
        $('#busiId').val(id);
        $('#busiName').val(name);
        $('#busiPhone').val(phone);
        $('#busiSourceId').val(originalbusiId);
        $('#busiMealsSettleMode').val(mealsSettleMode);
        if (groupid > 0) {
            $('#busGroupId').val(groupid);
        }
        else {
            $('select#busGroupId option:first').attr('selected', 'true');
        }
        $('#oldBusiSourceId').val(originalbusiId);
        $('#oldBusGroupId').val(groupid);
        adminjs.openwinbox('#BusinessInfoUpdateDiv');
    }
    //商户充值
    function funcBusinessRecharge(id, name, phone) {
        $('#busId').val(0);
        $('#busName').val('');
        $('#busPhone').val('');
        $('#busRechargeAmount').val('');
        $('#rechargeLog').val('');
        $('#busId').val(id);
        $('#busName').val(name);
        $('#busPhone').val(phone);
        //adminjs.openwinbox('#BusinessRechargeShow');
    }
    
    function funcBusinessWithdraw(id, name, phone) {
        GetBusiFinanceAccount(id);
        $('#withdrawId').val(0);
        $('#withdrawName').val('');
        $('#withdrawPhone').val('');
        $('#withdrawAmount').val('');
        $('#withdrawLog').val('');
        $('#withdrawId').val(id);
        $('#withdrawName').val(name);
        $('#withdrawPhone').val(phone);
        //alert("haha");
        //adminjs.openwinbox('#BusinessWithdraw');
    }
    function GetBusiFinanceAccount(busiId) { 
        var paramaters = {
            "busiId": busiId
        };
        $.ajax({
            type: 'POST',
            async:true,
            url: '/BusinessManager/GetBusiFinanceAccount',
            data: paramaters,
            success: function (result) {
                if (result != null) {
                    var bfa = $.parseJSON(result);
                    $("#openProvince").val(bfa.OpenProvince);
                    $("#openProvinceCode").val(bfa.OpenProvinceCode);
                    $("#openCity").val(bfa.OpenCity);
                    $("#openCityCode").val(bfa.OpenCityCode);
                    $("#idCard").val(bfa.IDCard);
                    if (bfa.BelongType == 0) {
                        $("#accountType").val('个人账户');
                    } else if (bfa.BelongType == 1) {
                        $("#accountType").val('公司账户');
                    } else {
                        $("#accountType").val('未知');
                    }
                }
            }
        });
    }
</script>


