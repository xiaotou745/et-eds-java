
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.business.common.UserContext"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
    String viewPath =request.getAttribute("viewPath").toString();
    UserContext context = UserContext.getCurrentContext(request);
    String groupName=context.getBusinessName();
    if(groupName.length()>10){
    	groupName=groupName.substring(0,10);
    }
%>
	<div class="nav">
		<img src="<%=basePath%>/images/dun.png" width="55" height="74" alt="">
		<%if(context!=null){
			if(context.getBusinessType()==0){%>
				<a href="<%=basePath%>/order/publish">发布任务</a>
				<span <%=viewPath=="index"?"class='on'":""%>><a class="one" href="<%=basePath%>/index">商户主页</a></span>
				<span <%=viewPath=="order/list"?"class='on'":""%>><a class="two" href="<%=basePath%>/order/list">全部订单</a></span>
				<span <%=viewPath=="orderregion/list"?"class='on'":""%>><a class="two" href="<%=basePath%>/orderregion/regionmanage">区域管理</a></span>
<%-- 				<span <%=viewPath=="order/todaylist"?"class='on'":""%>><a class="two" href="<%=basePath%>/orderregion/todayone?businessid=<%=context.getBusinessID() %>">今日订单</a></span> --%>
		<!-- 	<span <%=viewPath==""?"class='on'":""%>><a class="three" href="javascript:;">订单统计</a></span> -->
				<span <%=viewPath=="transdetail/list"?"class='on'":""%>><a class="four" href="<%=basePath%>/transdetail/list">交易明细</a></span>
				<span <%=viewPath=="clienter/list"?"class='on'":""%>><a class="five" href="<%=basePath%>/clienter/list">骑士管理</a></span>
				<span <%=viewPath=="message/list"?"class='on'":""%>><a class="six" href="<%=basePath%>/message/list">消息中心</a></span>
			<%}else{%>
				<a href="javascript:void(0)"><%=groupName%></a>
				<span <%=viewPath=="groupstatistics/today"?"class='on'":""%>><a class="one" href="<%=basePath%>/groupstatistics/today">集团主页</a></span>
				<span <%=viewPath=="order/grouporderlist"?"class='on'":""%>><a class="two" href="<%=basePath%>/order/grouporderlist">外卖订单</a></span>
				<span <%=viewPath=="groupstatistics/statistics"?"class='on'":""%>><a class="three" href="<%=basePath%>/groupstatistics/statistics">订单统计</a></span>
				<span <%=viewPath=="group/recharge"?"class='on'":""%>><a class="six" href="<%=basePath%>/group/recharge">充值</a></span>
				
				<span <%=viewPath=="businessmanager/list"?"class='on'":""%>><a class="six" href="<%=basePath%>/businessmanager/list">门店管理</a></span>

			<%}
		}%>
	</div>