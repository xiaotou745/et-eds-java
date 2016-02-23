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
<div class="top cb">

			<h3 class="cb">
				门店管理
				<p class="fr">
					<input type="text" class="fl" placeholder="请输入门店名称" id="businessName">
					<input type="button" class="fl" value="搜索按钮" id="customerSearch">
				</p>
			</h3>
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
			</form>
</div>
<!-- 列表 -->
<div class="bottom bottom2 bottom3" id="content">
</div>
<!-- 列表 -->
<script>
var jss = {
	search : function(currentPage) {
	var url='<%=basePath%>/businessmanager/listdo';
	var data={"currentPage":currentPage,
			"bizName":$('#businessName').val()
			};
	$.post(url,data,function(d){
		$('#content').html(d);
	});
	}
}
jss.search(1);
$('#customerSearch').click(function(){
	jss.search(1);
});

</script>