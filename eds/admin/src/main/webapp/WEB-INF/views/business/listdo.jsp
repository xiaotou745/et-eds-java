<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.BusinessModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.consts.AuthCode"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
UserContext context=UserContext.getCurrentContext(request);
boolean business_AuditPass=context.isHasAuth(AuthCode.Business_AuditPass);
boolean business_Modify=context.isHasAuth(AuthCode.Business_Modify);
boolean business_Recharge=context.isHasAuth(AuthCode.Business_Recharge);
boolean business_ClienterBind=context.isHasAuth(AuthCode.Business_ClienterBind);
boolean business_WithDraw=context.isHasAuth(AuthCode.Business_WithDraw);
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="10%">门店名称</th>
			<th width="100px">电话</th>
			<th width="10%">地址</th>
			<th width="11%">申请时间</th>
			<th width="%5">推荐人手机号</th>
			<th width="70px">所属集团</th>
			<th width="%5">分组</th>
			<th width="70px">审核状态</th>
			<th width="120px">余额</th>
			<th width="150px">结算</th>
			<th>操作</th>
		</tr>
	</thead>

	<tbody>

		<%
			PagedResponse<BusinessModel> responsePageList = (PagedResponse<BusinessModel>)request.getAttribute("listData");
			List<BusinessModel> data=responsePageList.getResultList();
			for (int i = 0; i < data.size(); i++) {
			    String status=""; 
			    String statusStyle="";
			    String statusStyle2="style=\"color:gray\"";
				 switch(data.get(i).getStatus())
				 {
				 case 0:status="未审核";
				 break;
				 case 1:status="已通过";
				 statusStyle="style=\"color:gray\"";
				 statusStyle2="";
				 break;
				 case 2:status="未审核且未添加地址";
				 break;
				 case 3:status="审核中";
				 break;
				 case 4:status="审核被拒绝";
				 break;
				 }
                 int checkAddress = data.get(i).getAddress()==null||data.get(i).getAddress().isEmpty()?0:1;
                 int checkImage = data.get(i).getCheckpicurl()==null||data.get(i).getCheckpicurl().isEmpty()?0:1;
		%>
		<tr>
			<td><%=data.get(i).getId()%></td>
			<td><%=data.get(i).getName()%></td>
			<td><%=data.get(i).getPhoneno()%></td>
			<td><%=data.get(i).getAddress()%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getInserttime(), "")%></td>
			<td><%=data.get(i).getRecommendphone()%></td>
			<td><%=data.get(i).getGroupname()%></td>
			<td><%=data.get(i).getBusinessgroupName()%></td>
			<td><%=status%></td>
			<td>外送费:<%=data.get(i).getDistribsubsidy()%><br> 账户余额:<a
				href="<%=basePath%>/business/balancedetail?businessId=<%=data.get(i).getId()%> ">￥<%=data.get(i).getBalanceprice()%></a><br>
				可提现余额:<%=data.get(i).getAllowwithdrawprice()%></td>
			<td><%=data.get(i).getCommissiontype()==1?"结算比例:"+data.get(i).getBusinesscommission():"结算金额:￥"+data.get(i).getCommissionfixvalue()%><br>
				结算类型:<%=data.get(i).getCommissiontype()==1?"结算比例":"固定金额"%><br>
				餐费结算方式:<%=data.get(i).getMealssettlemode()==0?"线下结算":"线上结算"%></td>
			<td>
			<% if(business_AuditPass){
				%>
			    <a href="javascript:void(0)" <%=statusStyle%> onclick="businessOk(<%=checkAddress%>,<%=data.get(i).getId()%>,<%=data.get(i).getBusinesscommission()%>,<%=checkImage%>,<%=data.get(i).getCommissiontype()%>,<%=data.get(i).getLatitude()%>,<%=data.get(i).getLongitude()%>,<%=data.get(i).getReceivableType()%>)">审核通过</a>
				<a href="javascript:void(0)" onclick="businessCancel(<%=data.get(i).getId()%>)" <%=statusStyle2%>>取消资格</a>
				<%}
				if(business_Modify)
				{%>
				<a href="<%=basePath%>/business/detail?businessID=<%=data.get(i).getId()%>">修改信息</a>
				<%}
				if(business_Recharge)
				{%>
				<a href="javascript:void(0)" onclick="businessRecharge(<%=data.get(i).getId()%>,'<%=data.get(i).getName()%>', '<%=data.get(i).getPhoneno()%>')">充值</a>
				<%}
				if(business_ClienterBind)
				{%>
				<a href="<%=basePath%>/business/clienterbindlist?businessId=<%=data.get(i).getId()%>">骑士绑定</a>
				<%}
				if(business_WithDraw)
				{%>
				<a href="javascript:void(0)" onclick="businessWithdraw(<%=data.get(i).getId()%>,'<%=data.get(i).getName()%>', '<%=data.get(i).getPhoneno()%>')">提款申请</a>
				<%}%>
			</td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
<script type="text/javascript">

</script>


