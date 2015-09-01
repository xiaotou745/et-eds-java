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

<%-- <div class="col-lg-6">
	<div class="panel blank-panel">
		<div class="panel-heading">
			<div class="panel-title m-b-md">
				<h4>商户名称:<%=ParseHelper.ShowString(detail.getName())%></h4>
			</div>
			<div class="panel-options">
				<ul class="nav nav-tabs">
					<li><a data-toggle="tab" href="tabs_panels.html#tab-1"
						tppabs="http://www.zi-han.net/theme/hplus/tabs_panels.html#tab-1">第一个选项卡</a>
					</li>
					<li class=""><a data-toggle="tab"
						href="tabs_panels.html#tab-2"
						tppabs="http://www.zi-han.net/theme/hplus/tabs_panels.html#tab-2">第二个选项卡</a>
					</li>
				</ul>
			</div>
		</div>

		<div class="panel-body">
			<div class="tab-content">
				<div id="tab-1" class="tab-pane active">
					<strong>HTML5 文档类型</strong>
					<p>Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5
						文档类型。在你项目中的每个页面都要参照下面的格式进行设置。</p>
				</div>
				<div id="tab-2" class="tab-pane">
					<strong>移动设备优先</strong>
					<p>在 Bootstrap 2 中，我们对框架中的某些关键部分增加了对移动设备友好的样式。而在 Bootstrap 3
						中，我们重写了整个框架，使其一开始就是对移动设备友好的。这次不是简单的增加一些可选的针对移动设备的样式，而是直接融合进了框架的内核中。也就是说，Bootstrap
						是移动设备优先的。针对移动设备的样式融合进了框架的每个角落，而不是增加一个额外的文件。</p>
				</div>
			</div>
		</div>
	</div>
</div>
 --%>

<div class="SearchMd">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
	<input type="hidden" name="CurrentPage" id="_hiddenCurrentPage" value="1"/>
	<input type="hidden" name="businessId" id="businessId" value="<%=detail.getId()%>"/>
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>商户名称:<%=ParseHelper.ShowString(detail.getName())%></td>
		</tr>
		<tr>
			<td>
				<button type="button" class="btn btn-default btn-lg" onclick="window.location.href='<%=basePath%>/business/addclienterbindlist?businessId=<%=detail.getId()%>'">手动绑定</button>
				<button type="button" class="btn btn-primary btn-lg" onclick="window.location.href='<%=basePath%>/business/bathaddclienterbindlist?businessId=<%=detail.getId()%>'">批量绑定</button>
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
	
	function funAddClienterBind(clientId){
		var businessId = $("#businessId").val();
        if (!window.confirm("是否添加绑定？")) {
            return;
        }
        var paramaters = { "businessId": businessId, "clienterId": clienterId };
        var url = "/business/addclienterbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	alert(result.message);
                if (result.responseCode > 0) {
                    window.location.href = "/business/clienterbindlist?businessId=" + businessId;
                }
            }
        });
	}
</script>

