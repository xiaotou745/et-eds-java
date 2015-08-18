<%@page import="com.edaisong.entity.BusinessMessage"%>
<%@page import="com.edaisong.entity.domain.BusinessOrderSummaryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
	String basePath = PropertyUtils.getProperty("static.business.url");
	BusinessOrderSummaryModel bos = (BusinessOrderSummaryModel)request.getAttribute("bos");
	BusinessMessage message = (BusinessMessage)request.getAttribute("message");
%>

<div class="center">
	<div class="top cb">
		<div class="left">
			<div class="l-top">
				<h2>
					<%=bos.getName() %> <a href="javascript:;">详情</a> <span class="fr">
					最近登录：<%=bos.getLastLoginTime() %> <%=bos.getCity() %></span>
				</h2>
				<p>
					当前余额 <i>￥</i> <span><%=bos.getBalancePrice()%></span>
				</p>
			</div>
			<div class="l-bottom">
				<p class="fl">
					<img src="images/icon-7.png" width="47" height="47" alt="待抢单"
						class="fl"> <span class="fl"> <em class="blue"><%=bos.getUnReceive()%></em>
						<b>待抢单</b>
					</span>
				</p>
				<p class="fl">
					<img src="images/icon-8.png" width="47" height="47" alt="待取货"
						class="fl"> <span class="fl"> <em class="purple"><%=bos.getReceived()%></em>
						<b>待取货</b>
					</span>
				</p>
				<p class="fl">
					<img src="images/icon-9.png" width="47" height="47" alt="配送中"
						class="fl"> <span class="fl"> <em class="green"><%=bos.getPickUp()%></em>
						<b>配送中</b>
					</span>
				</p>
				<p class="fl">
					<img src="images/icon-10.png" width="47" height="47" alt="已取消"
						class="fl"> <span class="fl"> <em class="red"><%=bos.getCancel()%></em>
						<b>已取消</b>
					</span>
				</p>
			</div>
		</div>
		<div class="right">
			<div class="r-top">
				<iframe allowtransparency="true" frameborder="0" width="290" height="96" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=0&d=2&bd=0&k=&f=&q=1&e=1&a=1&c=54511&w=290&h=96&align=center"></iframe>
			</div>
			<div class="r-bottom">
				<h2>
					公告<!--  <a class="fr" href="javascript:;">更多>></a> -->
				</h2>
				<p class="cb">
					<em class="fl"></em>
					<%
						if(message != null){		
					%>
					<span class="fl">
						<%=message.getContent() %>
					</span>
					<% }else{ %>
					当前没有公告
					<%} %>
				</p>
			</div>
		</div>
	</div>
	<div class="bottom">
		<h2>
			今日订单统计 <a href="javascript:;">查看更多</a>
		</h2>
	</div>
</div>