<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.lang.Double"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("static.business.url");
%>
<table class="stripe" width="100%">
<!-- 	<thead> -->
<!-- 			<tr> -->
<!-- 			<th style="width:50px">编号</th> -->
<!-- 			<th style="width:10px">订单号</th> -->
<!-- 			<th style="width:150px">发单时间</th> -->
<!-- 			<th style="width:80px">订单金额</th> -->
<!-- 			<th>收货人信息</th> -->
<!-- 			<th style="width:150px">骑士信息</th> -->
<!-- 			<th style="width:150px">完成时间</th> -->
<!-- 			<th style="width:80px">订单状态</th> -->
<!-- 			<th style="width:80px">操作</th> -->
<!-- 		</tr> -->
<!-- 	</thead> -->
	<tbody>
			<tr>
			<td style="width:50px">编号</td>
			<td style="width:10px">订单号</td>
			<td style="width:150px">发单时间</td>
			<td style="width:80px">订单金额</td>
			<td>收货人信息</td>
			<td style="width:150px">骑士信息</td>
			<td style="width:150px">完成时间</td>
			<td style="width:80px">订单状态</td>
			<td style="width:80px">操作</td>
		</tr>
		<%
			PagedResponse<OrderListModel> responsePageList = (PagedResponse<OrderListModel>) request.getAttribute("listData");
			List<OrderListModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<OrderListModel>();
			}
			String customerName="";
			String customerPhone="";
			String customerAddress="";
			String clienterName="";
			String clienterPhone="";
			for (int i = 0; i < data.size(); i++) {
				if(data.get(i).getReceviceName()!=null&&!data.get(i).getReceviceName().isEmpty()){
					customerName="姓名:"+data.get(i).getReceviceName();
				}
				if(data.get(i).getRecevicePhoneNo()!=null&&!data.get(i).getRecevicePhoneNo().isEmpty()){
					customerPhone="电话:"+data.get(i).getRecevicePhoneNo();
				}
				if(data.get(i).getReceviceAddress()!=null&&!data.get(i).getReceviceAddress().isEmpty()){
					customerAddress="地址:"+data.get(i).getReceviceAddress();
				}
				if(data.get(i).getClienterName()!=null&&!data.get(i).getClienterName().isEmpty()){
					clienterName="姓名:"+data.get(i).getClienterName();
				}
				if(data.get(i).getClienterPhoneNo()!=null&&!data.get(i).getClienterPhoneNo().isEmpty()){
					clienterPhone="电话:"+data.get(i).getClienterPhoneNo();
				}
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><a class="blue2" href="<%=basePath%>/order/detail?orderno=<%=data.get(i).getOrderNo()%>"><%=data.get(i).getOrderNo()%></a></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getPubDate())%></td>
			<td><%=data.get(i).getAmount()%></td>
			<td style="text-align: left;">
			<%=customerName%> <br /> 
			<%=customerPhone%> <br /> 
			<%=customerAddress%></td>
			<td style="text-align: left;">
			<%=clienterName%> <br /> 
			<%=clienterPhone%>
			</td>
			<td><%=ParseHelper.ToDateString(data.get(i).getActualDoneDate())%></td>
			<td><%=OrderStatus.getEnum(data.get(i).getStatus()).desc()%></td>
			<td>
			<%
			if(data.get(i).getStatus()==(int)OrderStatus.New.value()){
				%>
					<a class="red2" href="javascript:void(0)" onclick="cancelOrder(<%=data.get(i).getId()%>,'<%=data.get(i).getOrderNo()%>')">取消订单</a>
			<%}%>
			<a class="blue2" href="<%=basePath%>/order/detail?orderno=<%=data.get(i).getOrderNo()%>">订单详情</a>
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
<script type="text/javascript">
function cancelOrder(orderId,orderNo){
	if (!window.confirm("是否确认取消订单？")) {
        return;
    }
    var paramaters = { "orderId": orderId,"orderNo":orderNo };
    var url = "<%=basePath%>/order/canelorder";
    $.ajax({
        type: 'POST',
        url: url,
        data: paramaters,
        success: function (result) {
        	if (result.responseCode==0) {
        		 alert("操作成功");
                window.location.href = "<%=basePath%>/order/list";
            } else {
                alert(result.message);
            }
        }
    });
}
</script>
					