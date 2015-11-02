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
String basePath =PropertyUtils.getProperty("java.business.url");
%>
<script>
				var url = "<%=basePath%>/order/todaytotal";
				$.ajax({
					type : 'POST',
					url : url,
					data :null,
					success : function(result) {
						alert(result[0].name);
					}
				});
				var url = "<%=basePath%>/order/todaydetail";
				$.ajax({
					type : 'POST',
					url : url,
					data :null,
					success : function(result) {
						alert(result);
					}
				});
</script>