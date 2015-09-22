<%@page import="com.edaisong.entity.domain.BusinessClientersModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.BusinessMessage"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
%>
<table class="stripe" width="100%">
	<tbody>
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>手机号</td>
			<td>状态</td>
			<td>金额</td>
			<td>可提现余额</td>
			<td>完成订单数</td>
		</tr>

		<%
			PagedResponse<BusinessClientersModel> responsePageList = (PagedResponse<BusinessClientersModel>) request.getAttribute("listData");
			List<BusinessClientersModel> data = responsePageList.getResultList();
			if(data != null && data.size() > 0){
				int count = 1;
				for(BusinessClientersModel model : data){
		%>
		<tr>
			<td style="word-break:break-word; width:50px;"><%=count%></td>
			<td style="word-break:break-word; width:200px;"><%=model.getTruename() == null ?"":model.getTruename() %></td>
			<td style="word-break:break-word; width:200px;"><%=model.getPhoneno() == null ?"":model.getPhoneno()%></td>
			<td style="word-break:break-word; width:200px;"><%=model.getWorkstatus().equals(0) ?"上班":"下班"%></td>
			<td style="word-break:break-word; width:200px;">￥<%=model.getAccountbalance()%></td>
			<td style="word-break:break-word; width:200px;">￥<%=model.getAllowwithdrawprice()%></td>
			<td style="word-break:break-word; width:200px;"><%=model.getFinishedOrderCount()%></td>
		</tr>
		<%
				count++;}
			}else{
		%>
			<td>当前没有数据</td>
		<%} %>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>
					