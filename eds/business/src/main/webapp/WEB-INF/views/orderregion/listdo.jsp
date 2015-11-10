
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.OrderRegionLog"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.enums.OrderRegionLogOptType"%>
<table class="stripe" width="100%">
	<tbody>
			<tr>
			<td style="width:50px">编号</td>
			<td style="width:60px">操作类型</td>
			<td style="width:150px">操作时间</td>
			<td style="width:80px">操作人</td>
			<td style="width:80px">备注</td>
		</tr>
		
		<%
		PagedResponse<OrderRegionLog> responsePageList = (PagedResponse<OrderRegionLog>) request.getAttribute("listData");
		List<OrderRegionLog> data = responsePageList.getResultList();
		for (int i = 0; i < data.size(); i++) {%>
		<tr>
			<td><%=i + 1%></td>
			
			<td><%=OrderRegionLogOptType.getEnum(data.get(i).getOpttype()).desc()%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getOpttime())%></td>
			<td><%=data.get(i).getOptname()%></td>
			<td><%=data.get(i).getRemark()%></td>
		</tr>
		<%}%>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>