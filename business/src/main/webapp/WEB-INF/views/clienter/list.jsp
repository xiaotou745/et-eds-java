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
				骑士管理
				<p class="fr">
					<input type="text" class="fl" id="customerInfo" placeholder="骑士姓名，手机号">
					<input type="button" class="fl" value="搜索按钮" id="customerSearch">
				</p>
			</h3>
			

		<form method="POST" action="#" class="form-horizontal" id="searchForm">
			<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
			<div class="function">
				<span class="fl">状态</span>
				<label class="fl">
					<input type="radio" name="workStatus" value="2" checked="checked" onchange="change(this.value)">
					全部
				</label>
				<label class="fl">
					<input type="radio" name="workStatus" value="0" onchange="change(this.value)">
					上班
				</label>
				<label class="fl">
					<input type="radio" name="workStatus" value="1" onchange="change(this.value)">
					下班
				</label>
			</div>
		</form>
		</div>
		<div class="bottom bottom2 bottom3" id="content">
		</div>
<script type="text/javascript">
var searchType=0;
var jss = {
	search : function(currentPage) {
		if(searchType==0){//点击了查询按钮
			$("#_hiddenCurrentPage").val(currentPage);
			 var data=$("#searchForm").serialize();
				$.post("<%=basePath%>/clienter/listdo",data, function(d) {
					$("#content").html(d);
				});
		}else{//点击了右上角的放大镜查询
			 var data={currentPage:currentPage,search:$("#customerInfo").val()};
				$.post("<%=basePath%>/clienter/customerlistdo",data, function(d) {
					$("#content").html(d);
				});
		}
	}
}
jss.search(1);
$("#btnSearch").click(function() {
	searchType=0;
	$("#customerInfo").val("");
	jss.search(1);
});
$("#customerSearch").click(function() {
	searchType=1;
	jss.search(1);
});

function change(val){
	$("#customerInfo").val("");
	 var data={currentPage:1,workStatus:val};
		$.post("<%=basePath%>/clienter/listdo",data, function(d) {
			$("#content").html(d);
		});
}
</script>