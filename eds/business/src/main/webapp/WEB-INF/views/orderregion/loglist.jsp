
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>


<%	
String basePath =PropertyUtils.getProperty("java.business.url");
%>
<div class="top cb">

			<h3 class="cb">
				区域操作记录
			</h3>
</div>
		<div class="bottom bottom2 bottom3" id="content">
		</div>
<script>
var jss = {
		search : function(currentPage) {
			 var data={"currentPage":currentPage};
				$.post("<%=basePath%>/orderregion/listdo",data, function(d) {
					$("#content").html(d);
				});
		}
	}
jss.search(1);
</script>