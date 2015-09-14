<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessBalanceRecord"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordStatus"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%
	PagedResponse<GroupBusinessBalanceRecord> responsePageList=	(PagedResponse<GroupBusinessBalanceRecord>)request.getAttribute("listData");
List<GroupBusinessBalanceRecord> data = responsePageList.getResultList();
String basePath =PropertyUtils.getProperty("static.admin.url");
if(data == null){
	data = new ArrayList<GroupBusinessBalanceRecord>();
}
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>交易类型</th>
			<th>任务单号/交易流水号</th>
			<th>门店名称</th>
			<th>收支金额</th>
			<th>集团余额</th>
			<th>状态</th>
			<th>时间</th>
			<th>操作人</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr class="info">
			<td><%=BusinessBalanceRecordRecordType.getEnum(data.get(i).getRecordtype()).desc()%></td>
			<td>
				<%
					if(data.get(i).getRecordtype()==BusinessBalanceRecordRecordType.Recharge.value()){
				%>
				<a
				href="javascript:funRechargeDetail('<%=data.get(i).getRelationno()%>')"><%=data.get(i).getRelationno()%></a>
				<%
					} else{
				%> <a
				href="<%=basePath%>/order/detail?orderno=<%=data.get(i).getRelationno()%>&orderid=<%=data.get(i).getWithwardid()%> "><%=data.get(i).getRelationno()%></a>
				<%
					}
				%>
			</td>
			<td><%=data.get(i).getBusinessname()%></td>
			<td>￥<%=data.get(i).getAmount()%></td>
			<td>￥<%=data.get(i).getGroupafterbalance()%></td>
			<td><%=data.get(i).getStatus()==BusinessBalanceRecordStatus.Success.value()? "交易成功" : "交易中"%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getOperatetime())%></td>
			<td><%=data.get(i).getOperator()==null?"":data.get(i).getOperator()%></td>
			<td><%=data.get(i).getRemark()==null?"":data.get(i).getRemark()%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
<script>
	
</script>