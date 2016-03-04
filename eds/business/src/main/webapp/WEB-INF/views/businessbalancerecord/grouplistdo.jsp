<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.BusinessBalanceRecordModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
%>
<table class="stripe" width="100%">
	<tbody>
			<tr>
			<td style="width:150px">交易类型</td>
			<td style="width:150px">订单号/流水号</td>
			<td style="width:150px">收支金额</td>
			<td style="width:150px">交易方</td>
			<td style="width:150px">余额</td>			
			<td style="width:80px">门店订单</td>
			<td style="width:80px">交易时间</td>	
			<td style="width:80px">操作</td>
		</tr>
		<%
			PagedResponse<BusinessBalanceRecordModel> responsePageList = (PagedResponse<BusinessBalanceRecordModel>) request.getAttribute("listData");
			List<BusinessBalanceRecordModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<BusinessBalanceRecordModel>();
			}		
			String jyf="";
			for (int i = 0; i < data.size(); i++) {		
				int groupid=data.get(i).getGroupid();
				if(groupid>0)
					jyf="集团";
				else
					jyf="门店";
		%>
		<tr>			     
		
			<td><%= BusinessBalanceRecordRecordType.getEnum(data.get(i).getRecordtype()).desc()%></td>
			<td><%=data.get(i).getRelationno()%></td>
			<td>￥<%=data.get(i).getAmount()%></td>
			<td><%=jyf%></td>
			<td>￥<%=data.get(i).getBalance()%></td>
			<td><%=data.get(i).getBusinessName()%></td>		
			<td><%=ParseHelper.ToDateString(data.get(i).getOperatetime())%></td>
				
			<td>
			<a class="blue2" href="<%=basePath%>/order/detail?orderno=<%=data.get(i).getRelationno()%>">详情</a>		
			</td>
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