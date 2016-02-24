<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessRelationModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
PagedResponse<GroupBusinessRelationModel> responsePageList = (PagedResponse<GroupBusinessRelationModel>) request.getAttribute("listData");
List<GroupBusinessRelationModel> data = responsePageList.getResultList();
if (data == null) {
	data = new ArrayList<GroupBusinessRelationModel>();
}
%>
<table class="stripe" width="100%">
	<thead>
		<tr>
			<td style="width:150px">门店名称</td>
			<td style="width:150px">门店账号</td>
			<td style="width:150px">联系电话</td>
			<td style="width:150px">地址</td>
			<td style="width:150px">门店余额</td>
			<td style="width:80px">消费集团金额</td>
			<td style="width:80px">操作</td>
		</tr>
	</thead>
	<tbody>
			
		<%
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr>
			<td><%=ParseHelper.ShowString(data.get(i).getName())%></td>
			<td><%=data.get(i).getPhoneNo()%></td>
			<td><%=data.get(i).getPhoneNo2()%>,<%=data.get(i).getLandline()%></td>
			<td><%=data.get(i).getAddress()%></td>
			<td><%=data.get(i).getBalancePriceString()%></td>
			<td><%=data.get(i).getUseGroupMoneyString()%></td>
			<td><a href="javascript:void(0)" onclick="RemoveBusinessBind(<%=data.get(i).getBusinessid()%>)">移除绑定</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>		