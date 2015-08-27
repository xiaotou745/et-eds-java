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
	String basePath = PropertyUtils.getProperty("static.business.url");
%>
<table class="stripe" width="100%">
	<tbody>
			<tr>
			<td >时间</td>
			<td >消息内容</td>
		</tr>
		<%
			PagedResponse<BusinessMessage> responsePageList = (PagedResponse<BusinessMessage>) request.getAttribute("listData");
			List<BusinessMessage> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<BusinessMessage>();
			}
			for(BusinessMessage message : data){
										
		%>
		<tr>
			<td style="word-break:break-word; width:50px;"><%=ParseHelper.ToDateString(message.getPubdate())%></td>
			<td style="word-break:break-word; width:200px;"><%=message.getContent()%></td>
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
					