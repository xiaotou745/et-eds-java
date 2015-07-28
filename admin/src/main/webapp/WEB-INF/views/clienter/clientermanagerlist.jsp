<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.domain.ClienterModel"%> 
<%
	String basePath = request.getContextPath();
%>


    <link href="<%=basePath%>/css/admin.css" rel="stylesheet" /> 

    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/admin.js""></script>

        
<div class="ibox-content">		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table">
			<thead>
				<tr class="tdbg">
						<th width="%5">序号</th>
						<th width="%5">姓名</th>
						<th width="%5">工作状态</th>
						<th width="%5">电话</th>
						<th width="%5">身份证号</th>
						<th width="%5">照片</th>
						<th width="%5">申请时间</th>
						<th width="%5">帐户余额</th>
						<th width="%5">可提现余额</th>
						<th width="%5">集团名称</th>
						<th width="%5">物流公司名称</th>
						<th width="%5">审核状态</th>
						<th width="%5">推荐人</th>
						<th width="%5">操作</th>	
				</tr>
			</thead>
			
			<tbody>                           

		<%
		List<ClienterModel> data=	(List<ClienterModel>)request.getAttribute("listData");
		 for (int i = 0; i < data.size(); i++) {
			 %>  
			 <tr>
				<td><%=data.get(i).getId() %></td>
				<td><%=data.get(i).getTrueName() %></td>
				<%
				if (data.get(i).getWorkStatus()== 0)
				{
				%>		
				<td>上班</td>
				<%
				}
				else
				{
				%>
				<td>下班</td>
				<%
				}
				%>		
				<td><%=data.get(i).getPhoneNo() %></td>
				<td><%=data.get(i).getIdCard() %></td>
				<td><a href="javascript:void(0)" class="businessOk" onclick="">查看</a></td>			
				<td><%=data.get(i).getInsertTime() %></td>				
				<td style="color:red;font-weight:600"><a href="/SuperManManager/ClienterDetail?clienterId=@item.Id ">￥  <%=data.get(i).getAccountBalance() %></a></td>
				<td><%=data.get(i).getAllowWithdrawPrice() %></td>
				<td><%=data.get(i).getGroupName()%>  </td>
				<td><%=data.get(i).getDeliveryCompanyName()%>  </td>
				<%
				if (data.get(i).getStatus()== 0)
				{
				%>		
				<td>审核被拒绝</td>
				<%
				}
				else if (data.get(i).getStatus()== 1)
				{
				%>
				<td>审核通过</td>
				<%
				}
				else if (data.get(i).getStatus()== 2)
				{
				%>
				<td>未审核</td>
				<%
				}
				else if (data.get(i).getStatus()== 3)
				{
				%>
				<td>审核中</td>
				<%
				}
				%>			
				<td><%=data.get(i).getRecommendName()%>  </td>
				
				<%
				if (data.get(i).getStatus()== 1)
				{
				%>				
				<td>
				<a href="javascript:void(0)" style="color:gray"  onclick="clientOk('<%=data.get(i).getId() %>','<%=data.get(i).getIdCard() %>','<%=data.get(i).getTrueName() %>','<%=data.get(i).getPicUrl() %>','<%=data.get(i).getPicWithHandUrl() %>')">审核通过</a>
				<a href="javascript:void(0)"  onclick="clientCancel('<%=data.get(i).getId() %>')" >审核拒绝</a>
				</td>	
				<%
				}
				else
				{
				%>
				<td>				  
				  <a href="javascript:void(0)"   onclick="clientOk('<%=data.get(i).getId() %>','<%=data.get(i).getIdCard() %>','<%=data.get(i).getTrueName() %>','<%=data.get(i).getPicUrl() %>','<%=data.get(i).getPicWithHandUrl() %>')">审核通过</a>
                     <a href="javascript:void(0)" style="color:gray" onclick="clientCancel('<%=data.get(i).getId() %>')" >审核拒绝</a>
				</td>
				<%
				}
				%>	
				<td>
				<a href="javascript:void(0)" onclick="funcClienterRecharge('<%=data.get(i).getId() %>','<%=data.get(i).getTrueName() %>', '<%=data.get(i).getPhoneNo() %>')">余额变更</a>
				</td>
				
			</tr>
		 <%}
		%> 	 	
			</tbody>
		</table>
	</div>
	
<script type="text/javascript">
   function clientOk(clientId, idCard, trueName, picUrl, picWithHandUrl) {
        if (!window.confirm("是否审核通过？")) {
            return;
        }
        if (idCard == 0 || trueName == 0 || picUrl == 0 || picWithHandUrl == 0) {
            alert("该骑士未上传个人资料，不能通过审核。")
            return;
        }
        var paramaters = { "id": clientId };        
        var url = "<%=basePath%>/clienter/auditok";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {            
                    window.location.href = "<%=basePath%>/clienter/list";               
            }
        });
    }
   function clientCancel(clientId) {
     
       var paramaters = { "id": clientId };        
       var url = "<%=basePath%>/clienter/auditcancel";
       $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (result) {            
                   window.location.href = "<%=basePath%>/clienter/list";               
           }
       });
   }

   //骑士余额变更
   function funcClienterRecharge(id, name, phone) {
       $('#clienterId').val(0);
       $('#clienterName').val('');
       $('#clienterPhone').val('');
       $('#clienterRechargeAmount').val('');
       $('#rechargeLog').val('');
       $('#clienterId').val(id);
       $('#clienterName').val(name);
       $('#clienterPhone').val(phone);
       adminjs.openwinbox('#ClienterRechargeShow');
   }
  </script>
	
	
