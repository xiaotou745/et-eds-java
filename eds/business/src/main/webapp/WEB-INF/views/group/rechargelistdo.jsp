<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.GroupBusinessRecharge"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
%>
<table class="stripe" width="100%">
	<tbody>
			<tr>
			<td style="width:150px">流水号</td>
			<td style="width:150px">创建时间</td>
			<td style="width:150px">充值方式</td>
			<td style="width:150px">金额</td>
			<td style="width:150px">状态</td>
		</tr>
		<%
			PagedResponse<GroupBusinessRecharge> responsePageList = (PagedResponse<GroupBusinessRecharge>) request.getAttribute("listData");
			List<GroupBusinessRecharge> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<GroupBusinessRecharge>();
			}		
			
			for (int i = 0; i < data.size(); i++) {	
				String strPayType="";
				String strPayStatus="";
				String payType=data.get(i).getPaytype();
				int payStatus=data.get(i).getPaystatus();
				if(payType.equals("alipay"))
					strPayType="支付宝";
				else
					strPayType="未知";
				
				if(payStatus==1)
				{
					strPayStatus="交易成功";
				}
				else if(payStatus==0)
				{
					strPayStatus="待支付";
				}
				else
				{
					strPayStatus="交易失败";
				}
		%>
		<tr>		     
		
			<td><%=data.get(i).getOrderno()%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getRequesttime())%> </td>
			<td><%=strPayType%></td>
				<td>￥<%=data.get(i).getPayamount()%></td>
					<td><%=strPayStatus%></td>			
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