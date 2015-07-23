<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
	    <div class="col-lg-12">
	        <div class="input-group" style="margin-bottom:5px;">
	            <input type="text" placeholder="请输入账号名称" class="input-sm form-control" id="InputCity" style="width:250px;height:34px;" value="<%=request.getAttribute("cityname")==null?"":request.getAttribute("cityname")%>"/>
	            <button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" id="btnSave" style="margin-left:3px;">添加用户</button>
	        </div>
	    </div>
	   
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th width="5%">编号</th>
								<th>账号名称</th>
								<th>登录名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content">
						</tbody>	
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="<%=basePath%>/js/plugins/jeditable/jquery.jeditable.js"></script>
<!-- Data Tables -->
<script src="<%=basePath%>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="<%=basePath%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=basePath%>/js/hplus.js"></script>
<!-- Page-Level Scripts -->

<div id="content">
	
</div>

<script>

$(function(){
	jss.search('');

	var jss={
		search:function(keyword){
			$.post("/admin/account/listdo",{keyword:$("#txtKeyword").val(),m:Math.random()},function(d){
				$("#content").html(d);
			});
		}
	}
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