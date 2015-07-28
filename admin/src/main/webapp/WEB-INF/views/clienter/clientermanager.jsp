<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%
	String basePath = request.getContextPath();
%>


    <link href="<%=basePath%>/css/admin.css" rel="stylesheet" />
 

    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/admin.js""></script>

<div style="height:500%"></div>
  
   <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <span class="">骑士名称: </span>
                    <input id="txtGroupName" type="text" name="GroupName" />
                    <span class="">骑士电话: </span> 
                    <input id="txtAppkey" type="text" name="Appkey" />
                    <input type="button" value="查询" class="searchBtn" id="btnSearch"  />                    
                </td>
            </tr>
        </table>  
        
        <div id="groupList">     
        <jsp:include page="clientermanagerlist.jsp"/>
        </div>

<div>
    <div class="add-openbox add-form" id="ClienterRechargeShow" style="width:500px">
        <h2>
            <p id="statusFin">骑士余额变更</p>
        </h2>
        <fieldset>
            <br>
            <div class="control-group">
                <label>骑士名称：</label>
                <input name="clienterName" id="clienterName" disabled="disabled" type="text">
                <input name="clienterId" id="clienterId" type="hidden">
            </div>
            <div class="control-group">
                <label>骑士电话：</label>
                <input name="clienterPhone" id="clienterPhone" disabled="disabled" type="text">
            </div>
            <div class="control-group">
                <label>余额增减：</label>
                <input name="clienterRechargeAmount" id="clienterRechargeAmount" type="text">元
            </div>
            <div class="control-group">
                <label>备注：</label>
                <div class="controls">
                    <textarea cols="45" rows="5" id="rechargeLog"></textarea>
                </div>
            </div>
        </fieldset>
        <p class="btnbox">
            <input value="确认" type="button" id="btnRechargeCommit" class="yesBtn" />
            <input value="关闭" type="button" class="J_closebox qxBtn" />
        </p>
    </div>
</div>
	<script>		
	var adminjs = new adminglass(); //实例化后台类	
	 //骑士充值
    $("#btnRechargeCommit").on('click', function () {
        var clienterId = $("#clienterId").val(); //骑士id
        var clienterName = $("#clienterName").val(); //骑士电话
        var clienterRechargeAmount = $("#clienterRechargeAmount").val(); //骑士调整金额
        var rechargeLog = $("#rechargeLog").val(); //充值描述
        if (rechargeLog.trim().length == 0) {
            alert("请输入备注!");
            return;
        }

        var decimalFormat = /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
        if (!decimalFormat.test(clienterRechargeAmount)) {
            alert("请输入正确的金额！");
            return;
        }
    
        if (confirm("确定要为骑士：" + clienterName + "   调整：" + clienterRechargeAmount + "元？")) {
            var paramaters = {
                "ClienterId": clienterId, "RechargeAmount": clienterRechargeAmount, "Remark": rechargeLog
            };
            var url = "<%=basePath%>/clienter/modifymoney";
            $.ajax({
                type: 'POST',
                url: url,
                data: paramaters,
                success: function (result) {                  
                        window.location.href = "<%=basePath%>/clienter/list";                    
                }
            });
        }
    });
    //关闭弹层
    $('.J_closebox').click(function () {
        adminjs.closewinbox('.add-openbox');
        return false;
    });
	</script>		
	
