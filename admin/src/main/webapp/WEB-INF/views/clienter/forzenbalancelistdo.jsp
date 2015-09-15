
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@page import="com.edaisong.core.util.PageHelper"%>     
<%@page import="com.edaisong.entity.domain.ClienterModel"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%> 
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("static.admin.url");
 %> 
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">编号</th>
						<th width="%5">姓名</th> 
						<th width="%5">电话</th>					 
						<th width="%5">余额</th>
						<th width="%5">可提现余额</th>
						<th width="%5">审核状态</th>
						<th width="%5">操作</th>	
				</tr>
			</thead> 
			<tbody>      
		<%
       PagedResponse<ClienterModel> data = (PagedResponse<ClienterModel>) request
                           				.getAttribute("listData");
                           				List<ClienterModel> list = data.getResultList();
                           				if (list == null) {
                           			list = new ArrayList<ClienterModel>();
                           				}		
                           				 for (int i = 0; i < list.size(); i++) {
                           		%>  
			 <tr>
				<td><%=list.get(i).getId() %></td>
				<td><%=list.get(i).getTrueName() %></td>
				<td><%=list.get(i).getPhoneNo() %></td>
				<td>￥<%=list.get(i).getAccountBalance() %></td>
				<td><%=list.get(i).getAllowWithdrawPrice() %></td> 
				<%
				if (list.get(i).getStatus()== 0)
				{
				%>		
				<td>审核被拒绝</td>
				<%
				}
				else if (list.get(i).getStatus()== 1)
				{
				%>
				<td>审核通过</td>
				<%
				}
				else if (list.get(i).getStatus()== 2)
				{
				%>
				<td>未审核</td>
				<%
				}
				else if (list.get(i).getStatus()== 3)
				{
				%>
				<td>审核中</td>
				<%
				}
				%>			
				  			
				<td>
				<%if (list.get(i).getStatus()== 1 && list.get(i).getAccountBalance()>=1 && list.get(i).getAllowWithdrawPrice()>=1){%>				
				<a href="javascript:void(0)"  onclick="showForzenClienterBalance(<%=list.get(i).getId() %>,'<%=list.get(i).getPhoneNo() %>','<%=list.get(i).getTrueName()%>',<%=list.get(i).getAccountBalance() %>,<%=list.get(i).getAllowWithdrawPrice() %>)" >余额冻结</a>
				<%}else{%>
				<%}%>
			</td> 
			</tr>
		 <%}
		%> 	 	
			</tbody>
		</table>
		<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%> 
<script type="text/javascript">

  function showForzenClienterBalance(clienterId,clienterPhone,clienterTrueName,clienterAmountBalance,clienterWithdrawPrice){
	  $("#clienterId").val(clienterId);
	  $("#clienterPhone").html(clienterPhone);
	  $("#clienterTrueName").html(clienterTrueName);
	  $("#clienterAmountBalance").html(clienterAmountBalance);
	  $("#clienterWithdrawPrice").html(clienterWithdrawPrice); 
	  $("#forzenClienterBalance").modal('show');
  }
    
  </script>
	
	
