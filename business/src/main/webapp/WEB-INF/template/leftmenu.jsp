
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("static.business.url");
%>
	<div class="nav">
		<img src="<%=basePath%>/images/dun.png" width="55" height="74" alt="">
		<a  class="on" href="<%=basePath%>/order/publish">发布任务</a>
		<span><a class="one" href="<%=basePath%>/index">商户主页</a></span>
		<span><a class="two" href="<%=basePath%>/order/list">全部订单</a></span>
		<span><a class="three" href="javascript:;">订单统计</a></span>
		<span><a class="four" href="<%=basePath%>/transdetail/list">交易明细</a></span>
		<span><a class="five" href="<%=basePath%>/clienter/list">骑士管理</a></span>
		<span><a class="six" href="javascript:;">消息中心</a></span>
	</div>
	<script type="text/javascript">
// 	$(document).ready(function() {
// 		$(".nav a").click(function(){
// 			$(this).parent().siblings().removeClass("on");
// 			$(this).parent().addClass("on");
// 		});
// 	});
</script>