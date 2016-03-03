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
//门店下拉数据
String BusList=(String)request.getAttribute("BusList");
%>
<!-- 下拉框 -->
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/autocomplete.css">
<script src="<%=basePath%>/js/jquery.autocomplete.js"></script>
<!-- 下拉框 -->

<div class="top cb">

			<h3 class="cb">
				门店管理
				<p class="fr">
					<input type="text" class="fl" placeholder="请输入门店名称" id="businessName">
					<input type="hidden" class="fl" placeholder="请输入门店名称" id="businessIdValue">
					
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

$(function(){
	
	// <!-- 下拉框 START-->
	//1.文本框ID
	var businessNameDate=<%=BusList%>
	 $("#businessName").AutoComplete({
	        data: businessNameDate,
	        ajaxDataType: "json",
	        width: 240,
	        listStyle: "custom",
	        matchHandler: function() {
	            return ! 0
	        },
	        afterSelectedHandler: function(d) {
	        	$('#businessName').val(d.label);////1.文本框ID
	        	$('#businessIdValue').val(d.value);////2.隐藏域文本框ID
	        	jss.search(1);
	        },
	        createItemHandler: function(t, i) {
	            var s = $("<div " + (i.unlink ? 'class="disabled"': "") + ">" + i.label + (i.unlink ? "[已解绑]": "") + "</div>");
	            return s
	        }
	    })
	   // <!-- 下拉框 END-->

	})
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
//移除绑定
function RemoveBusinessBind(bid)
{
	if(!confirm('确定移除吗 ？'))
	{
		return;
	}
	var url='<%=basePath%>/businessmanager/removebind';
	var par={"bid":bid};
	$.post(url,par,function(d){
		if(d==1)
		{
			alert('操作成功!');
			jss.search(1);
		}
		else{
			alert('操作失败!');
			}
	});
}
</script>