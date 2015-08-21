<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>

<%	
String basePath =PropertyUtils.getProperty("static.business.url");
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/dataTables.bootstrap.css">
<div class="top cb">

		<form method="POST" action="#" class="form-horizontal" id="searchForm">
			<input type="hidden" name="CurrentPage" id="_hiddenCurrentPage" value="1"/>
		</form>
		</div>
		<div class="bottom bottom2 bottom3" id="content">
		</div>
<script>
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/message/listdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	$("#customerSearch").click(function() {
		if($("#customerInfo").val()!=""){
			var data={"superManPhone":$("#customerInfo").val()};
			var data={"superManName":$("#customerInfo").val()};
			var data={"originalOrderNo":$("#customerInfo").val()};
			$.post("<%=basePath%>/message/listdo",data, function(d) {
				$("#content").html(d);
			});
		}

	});
</script>