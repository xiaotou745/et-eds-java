<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.domain.BusinessClienterRelationModel"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.core.consts.AuthCode"%>
<%@page import="com.edaisong.core.enums.BusinessClienterRelationAuditStatus"%>
<%@page import="java.util.List"%>

<%
PagedResponse<BusinessClienterRelationModel> responsePageList=	(PagedResponse<BusinessClienterRelationModel>)request.getAttribute("listData");
List<BusinessClienterRelationModel> data = responsePageList.getResultList();
String basePath =PropertyUtils.getProperty("java.admin.url");
UserContext context=UserContext.getCurrentContext(request);
boolean business_setSettleMoneyRate=context.isHasAuth(AuthCode.Business_SetSettleMoneyRate);
boolean business_Recharge=context.isHasAuth(AuthCode.Business_Recharge);
if(data == null){
	data = new ArrayList<BusinessClienterRelationModel>();
}%>

<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>骑士姓名</th>
								<th>骑士电话</th>
								<th>类型</th>
								<th>状态</th>
								<th>操作时间</th>
								<th>审核状态</th>
								<th>操作人</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) {
								%>
							<tr>
								<td><%=(i+1)%></td>
								<td><%=ParseHelper.ShowString(data.get(i).getTrueName())%></td>
								<td><%=data.get(i).getPhoneNo()%></td>
		                        <td><%=ParseHelper.ToDateString(data.get(i).getUpdatetime()) %></td>
		                        
		                        <td>
		                        <select id="selCoop" onchange="funUpdateCooperation(<%=data.get(i).getId()%>)">
		                        <option value="0" <%=data.get(i).getIsCooperation()==0?"selected=‘selected’":"" %>>合作骑士</option>
		                        <option value="1" <%=data.get(i).getIsCooperation()==1?"selected=‘selected’":"" %>>店内骑士</option>
		                        </select>
		                        </td>
		                        
		                        <td><%=data.get(i).getIsbind()==(short)0? "未绑定":"已绑定"%></td>
		                        <td><%=data.get(i).getUpdateby() %></td>
		                         <td><%=BusinessClienterRelationAuditStatus.getEnum(data.get(i).getAuditStatus()).desc()%></td>
		                        <td>
		                        <% if((int)data.get(i).getAuditStatus()!=BusinessClienterRelationAuditStatus.Wait.value()){
		                        	if(business_setSettleMoneyRate){
		                        		if(data.get(i).getIsbind() == 0){
		                        		%>
		                        		<a href="javascript:funModifyClienterBind(<%=data.get(i).getBusinessid() %>,<%=data.get(i).getClienterid() %>,1,'是否绑定？')">绑定</a>
		                        		<%}else{
		                        		%>
		                        		<a href="javascript:funModifyClienterBind(<%=data.get(i).getBusinessid() %>,<%=data.get(i).getClienterid() %>,0,'是否解除绑定？')">解除绑定</a>
		                        		<%}
		                        	}
									if(business_Recharge){
										if(data.get(i).getIsbind()> 0){%>
		                        			<a href="javascript:void(0)" style="color:gray">删除</a>
		                        		<%}else{%>
		                        			<a href="javascript:funRemoveClienterBind(<%=data.get(i).getBusinessid() %>,<%=data.get(i).getClienterid() %>)">删除</a>
		                        		<%}
		                        	}
		                        }%>
		                        <a href="javascript:bindRecord('<%=data.get(i).getBusinessid() %>','<%=data.get(i).getClienterid() %>')">操作记录</a>
		                        </td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					
<script>

	function funUpdateCooperation(id) {
		var type=$("#selCoop").val();
		if(!window.confirm("确认修改骑士状态？")) {
				type==0?$("#selCoop").val(1):$("#selCoop").val(0);
				return;
			}
		 var url ="<%=basePath%>/business/updateClienterBindRelationCooperation";
		 
		$.post(url,{id:id,type:type,m:Math.random()},function(d){
			alert("修改成功了!");
		});
	}
</script>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>