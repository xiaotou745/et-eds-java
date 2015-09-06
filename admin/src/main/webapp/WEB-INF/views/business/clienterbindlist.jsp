<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
	BusinessDetailModel detail = (BusinessDetailModel) request
			.getAttribute("detail");
	int bindClienterQty = (int) request
			.getAttribute("bindClienterQty");
%>
<div class="SearchMd">
<form method="POST" action="#" class="form-horizontal" id="searchForm">
<table class="tbstyle222" border="0"
	style="font-size: 14px; font-weight: bold; line-height: 300%; width: 900px">
	<tr class="trclass">
		<td>商户名称:<%=ParseHelper.ShowString(detail.getName())%></td>
		<td>商铺电话:<%=ParseHelper.ShowString(detail.getPhoneno())%></td>
		<td>商铺地址:<%=ParseHelper.ShowString(detail.getAddress())%> </td>
		<td>已绑定骑士:<%=bindClienterQty%></td>
	</tr>
	<tr>
		<td><button type="button" class="btn btn-w-m btn-primary" id=btnExport style="margin-left: 3px; height: 30px;" 
			onclick="window.location.href='<%=basePath%>/business/addclienterbindlist?businessId=<%=detail.getId()%>'">
			添加骑士绑定</button></td>
	</tr>
</table>
	<input type="hidden" name="CurrentPage" id="_hiddenCurrentPage" value="1"/>
	<input type="hidden" name="businessId" id="businessId" value="<%=detail.getId()%>"/>
</form>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>

<script>
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/business/clienterbindlistdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	function funModifyClienterBind(businessId,clienterId,isBind,confirmMsg){
        if (!window.confirm(confirmMsg)) {
            return;
        }
        var paramaters = { "businessId": businessId,"clienterId": clienterId,"isBind":isBind };
        var url = "<%=basePath%>/business/modifyclienterbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result>0) {
                	alert("操作成功");
                	window.location.href = "/business/clienterbindlist?businessId=" + businessId;
                } else {
                    alert("操作失败");
                }
            }
        });
	}
	
	function funRemoveClienterBind(businessId,clienterId){
		if (!window.confirm("是否删除绑定？")) {
            return;
        }
		var paramaters = { "businessId": businessId,"clienterId": clienterId};
        var url = "<%=basePath%>/business/removeclienterbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result>0) {
                	alert("操作成功");
                	window.location.href = "<%=basePath%>/business/clienterbindlist?businessId=" + businessId;
                } else {
                    alert("操作失败");
                }
            }
        });
	}
</script>

