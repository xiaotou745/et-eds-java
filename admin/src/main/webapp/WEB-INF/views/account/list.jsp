<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <% %>
<link href="/admin/css/admin.css" rel="stylesheet" />
<!-- <script type="text/javascript" src="/admin/js/jquery-1.11.1.min.js"></script> -->
 <script type="text/javascript" src="/admin/js/admin.js"></script> 

账号名称: 
<input type="text" />
<input type="button" value="查询"  class="searchBtn" id="btnSearch"  />
<input type="button" value="添加用户" class="searchBtn" id="btnAdd" />
<div id="content">
	
</div>
<!--添加用户开始-->
<div class="selectSupplierDish" style="width:540px;height: 500px">
    <div class="add-openbox add-form" id="AddAccountShow" style="min-width:540px;overflow-y:auto;max-height:500px;">
        <h2>
            <p id="statusFin">添加帐号</p>
            <a class="J_closebox x_close"></a>
        </h2>
        <form class="AddAccountfrom" id="AddAccountfrom1">
            <fieldset>
                <input type="hidden" name="optionType" id="optionType" value="0">
                <input type="hidden" name="accountId" id="accountId">
                <div class="control-group">
                    <label class="control-label">帐号名称</label>
                    <div class="controls">
                        <input placeholder="帐号名称" name="accountName" id="accountName" type="text">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">登录名</label>
                    <div class="controls">
                        <input placeholder="登录名" name="loginName" id="loginName" type="text">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">密  码</label>
                    <div class="controls">
                        <input placeholder="密码" name="password" id="password" type="password">
                        <label id="modifyRemind" style="font-size: 10px;color: red">不填写，密码保持不变!</label>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">确认密码</label>
                    <div class="controls">
                        <input placeholder="确认密码" name="confirmPassword" id="confirmPassword" type="password">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">城市权限类型</label>
                    <div class="controls">
                        <select id="selAcountType">
                            <option value="1">全部城市权限</option>
                            <option value="2">部分城市权限</option>
                        </select>
                    </div>
                </div>

                @if (ViewBag.txtGroupId == null)
                {
                    <div class="control-group">
                        <label class="control-label">集团</label>
                        <div class="controls">
                            @Html.DropDownList("AddGroupId", new SelectList(new BusinessProvider().GetGroups(), "Id", "GroupName"), "--无--", new { @class = "selectw", style = "width:143px" })
                        </div>
                    </div>
                }
                else
                {
                    <input name="AddGroupId" id="AddGroupId" type="hidden" value="@ViewBag.txtGroupId" />
                }

                <div style="width:500px;" id="divCity">
                    <label class="control-label">城市管理</label>
                    <a href="javascript:funControlCityList();"><div id="cityNameList">请选择</div></a>
                    <div id="cityList" style="float: left">
                        @{
                            var openCityList = ViewBag.openCityList.Result.AreaModels as List<AreaModel>;
                            if (openCityList != null && openCityList.Count > 0)
                            {
                                <div style="width: 100px;float: left">
                                    <input type="checkbox" name="All" id="selectAll" onclick="checkAll()" />全选/取消
                                </div>
                                foreach (var item in openCityList)
                                {
                                    <div style="width: 100px;float: left">
                                        <input type="checkbox" name="checkMenus" id="@item.Code" value="@item.Code" />
                                        <label>@item.Name</label>
                                    </div>
                                }
                            }
                        }
                    </div>
                </div>
                <div style="clear:both;"></div>
                <div style="width:500px;" id="divDeliveryCompany">
                    <label class="control-label">物流公司管理</label>
                    <a href="javascript:funDcChangeList();"><div id="dcChange">请选择</div></a>
                    <div id="DcItemList" style="float: left;display:none;">
                        @{
                            var openDC = ViewBag.openDcList as IList<DeliveryCompanyModel>;
                            if (openDC != null && openDC.Count > 0)
                            {
                                foreach (var item in openDC)
                                {
                                    <div style="width: 100px;float: left">
                                        <input type="checkbox" name="checkDeliveryCompany" id="@item.Id" value="@item.Id" />
                                        <label>@item.DeliveryCompanyName</label>
                                    </div>
                                }
                            }
                        }
                    </div>
                </div>
                <div style="float: left;width:500px">
                    <label class="control-label" for="input01">是否启用</label>
                    <input name="isEnable" id="isEnableY" type="radio" value="1" checked="checked" /> 启用
                    <input name="isEnable" id="isEnableN" type="radio" value="0" /> 禁用
                </div>
            </fieldset>
            <p class="btnbox">
                <input value="确定" type="button" id="btnAddAccount" class="yesBtn" />
                <input value="关闭" type="button" class="J_closebox qxBtn" />
            </p>
        </form>
    </div>
</div>
<div class="selectSupplierDish" id="_AuthorityDiv">
    <div class="=J_closebox"></div>
</div>
<!--添加用户结束-->
<script>

$(function(){
	$.post("/admin/account/listdo",{m:Math.random()},function(d){
		$("#content").html(d);
	});
});

    var adminjs = new adminglass(); //实例化后台类
    $(function() {
        $("#selAcountType").change(fn);

        function fn() {
            $("#divCity").toggle($("#selAcountType").val() != 1);

        }

        //添加帐户
        $("#btnAdd").on('click', function() {
            $("#selAcountType").val('1');
            $('#accountId').val('0');
            $('#optionType').val('0');
            $("#accountName").removeAttr("disabled");
            $("#loginName").removeAttr("disabled");
            $("#modifyRemind").hide();
            $('#accountName').val('');
            $('#loginName').val('');
            $('#password').val('');
            $('#confirmPassword').val('');
            $("#isEnableY").attr("checked", "checked");
            $(":checkbox[name='checkMenus']").prop("checked", false);
            $("input[name='checkDeliveryCompany']").each(function () {
                $(this).prop("checked", false);
            });
            $('#statusFin').text("添加账号");
            $('#cityNameList').text("请选择");
            adminjs.openwinbox('#AddAccountShow');
            $("#divCity").hide();
            $("#DcItemList").hide();
        });
        window.location.hash = '';
    });
    function checkAll() {
        var checkedOfAll = $("#selectAll").prop("checked");
        $("input[name='checkMenus']").prop("checked", checkedOfAll);
    }
    //物流公司绑定显示隐藏
    function funControlCityList() {
        if ($("#cityList").is(":visible")) {
            $("#cityList").hide();
        } else {
            $("#cityList").show();
        }
    }
    function funDcChangeList() {
        if ($("#DcItemList").is(":visible")) {
            $("#DcItemList").hide();
        } else {
            $("#DcItemList").show();
        }
    }
    $("#btnAddAccount").click(function () {
        var cityCodeList = "";
        $("input[name='checkMenus']").each(function () {
            if ($("#selAcountType").val() == 1) {
                cityCodeList = cityCodeList + $(this).val() + "," + "1" + "|";
            }
            else {
                if ($(this).is(':checked')) {
                    cityCodeList = cityCodeList + $(this).val() + ",1" + "|";
                } else {
                    cityCodeList = cityCodeList + $(this).val() + ",0" + "|";
                }
            }
        });
        var DCidList = "";
        $("input[name='checkDeliveryCompany']").each(function () {
            if ($(this).is(':checked')) {
                DCidList = DCidList + $(this).val() + "," + "1" + "|";
            }
            else {
                DCidList = DCidList + $(this).val() + "," + "0" + "|";
            }
        });
        if (cityCodeList.length > 0)
            cityCodeList = cityCodeList.substring(0, cityCodeList.length - 1);
        if (DCidList.length > 0)
            DCidList = DCidList.substring(0, DCidList.length - 1);
        var id = $("#accountId").val();//用户Id
        var accountName = $("#accountName").val();//用户名称
        var loginName = $("#loginName").val();//登陆名称
        var password = $("#password").val();//密码
        var confirmPassword = $("#confirmPassword").val();//确认密码
        var groupId = $("#AddGroupId").val();//登陆名称
        var status = $("input[name='isEnable']:checked").val();//是否启用
        var optionType = $("#optionType").val();//操作类型
        var accountType = $("#selAcountType").val();
        if (accountName == "") {
            alert("请输入用户名称!");
            return;
        }
        if (loginName == "") {
            alert("请输入登陆名称!");
            return;
        }
        if (password == "" && optionType == "0") {
            alert("请输入登陆密码!");
            return;
        }
        if (confirmPassword == "" && optionType == "0") {
            alert("请输入确认密码!");
            return;
        }
        if (password != "" && password.length < 6) {
            alert("密码长度必须为六位及以上!");
            return;
        }
        if (password != confirmPassword) {
            alert("两次密码输入不一致!");
            return;
        }
        if (confirm("确定要提交用户信息吗？")) {
            var paramaters = {
                "Id": id, "userName": accountName, "loginName": loginName, "password": password, "groupId": groupId, "status": status, "cityCodeList": cityCodeList, "optionType": optionType,
                "accountType": accountType, "dcidlist": DCidList
            };
            var url = "/AuthorityManager/Add";
            $.ajax({
                type: 'POST',
                url: url,
                data: paramaters,
                success: function (result) {
                    if (result.IsSuccess) {
                        alert(result.Message);
                        window.location.href = "/AuthorityManager/AuthorityManager";
                    } else {
                        alert(result.Message);
                    }

                }
            });
        }
    });

</script>