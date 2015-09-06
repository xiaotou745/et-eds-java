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
%>

<div class="SearchMd">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
	<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
	<input type="hidden" name="businessId" id="businessId" value="<%=detail.getId()%>"/>
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>商户名称:<%=ParseHelper.ShowString(detail.getName())%></td>
		</tr>
		<tr>
			<td>
				<button type="button" class="btn btn-default btn-lg" onclick="window.location.href='<%=basePath%>/business/addclienterbindlist?businessId=<%=detail.getId()%>'">手动绑定</button>
				<%-- <button type="button" class="btn btn-primary btn-lg" onclick="window.location.href='<%=basePath%>/business/clienterbathbindlist?businessId=<%=detail.getId()%>'">批量绑定</button> --%>
			</td>
		</tr>
		<tr>
			<td><span class="">骑士姓名: </span> <input id="clienterName"
				type="text" name="clienterName" />
				<span class="">骑士电话: </span> <input id="clienterPhone"
				type="text" name="clienterPhone" /> <input type="button"
				value="查询" class="searchBtn" id="btnSearch" /> </td>
		</tr>
	</table>
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
		 $.post("<%=basePath%>/business/addclienterbindlistdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	function funAddClienterBind(clienterId){
		var businessId = $("#businessId").val();
        if (!window.confirm("是否添加绑定？")) {
            return;
        }
        var paramaters = {"businessId":businessId,"clienterId":clienterId};
        var url = "<%=basePath%>/business/addclienterbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	alert(result.message);
                if (result.responseCode > 0) {
                    window.location.href = "<%=basePath%>/business/clienterbindlist?businessId=" + businessId;
                }
            }
        });
	}
</script>

