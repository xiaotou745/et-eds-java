<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
	    <div class="col-lg-12">
	        <div class="input-group" style="margin-bottom:5px;">
	            <input type="text" placeholder="请输入城市" class="input-sm form-control" id="InputCity" style="width:250px;height:34px;" value="<%=request.getAttribute("cityname")==null?"":request.getAttribute("cityname")%>"/>
	            <button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" id="btnSave" style="margin-left:3px;">保存修改</button>
	        </div>
	    </div>
	   
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								  <th>订单号</th>
						            <th>原平台订单号</th>
						            <th>商户信息</th>
						            <th>超人信息</th>
						            <th>发布时间</th>
						            <th>取货地址</th>
						            <th>送货地址</th>
						            <th>实际完成时间</th>
						            <th>订单数量</th>
						            <th>订单金额</th>
						            <th>订单佣金</th>
						            <th>扣除补贴</th>
						            <th>外送费</th>
						            <th>每单补贴</th>
						            <th>任务补贴</th>
						            <th>商家结算比例</th>
						            <th>订单来源</th>
						            <th>订单状态</th>
						            <th>已上传/共需上传</th>
						            <th>抢单-完成</th>
						            <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%
								List<OrderListModel> data=(List<OrderListModel>)request.getAttribute("listData");
								 if(data==null){
									 data=new ArrayList<OrderListModel>();
								 }
							    for (int i = 0; i < data.size(); i++) {
					        
							%>
							<tr>
								<td><%=i+1%></td>
								<td><%=data.get(i).getOrderNo()%></td>
								<td><%=data.get(i).getOriginalOrderNo()%></td>
								<td><%=data.get(i).getBusinessName()%>  <br/> <%=data.get(i).getBusinessPhoneNo()%> </td>
								<td><%=data.get(i).getClienterName()%>  <br/> <%=data.get(i).getClienterPhoneNo()%> </td>
							    <td><%=data.get(i).getPubDate()%></td>
							    <td><%=data.get(i).getPickUpAddress()%></td>
		                        <td><%=data.get(i).getReceviceAddress()%></td>
		                        <td><%=data.get(i).getActualDoneDate()%></td>
		                        <td><%=data.get(i).getOrderCount()%></td>
		                        <td style="color:red;font-weight:600"><%=data.get(i).getAmount()%></td>
		                        <td><%=data.get(i).getOrderCommission()%></td>
		                        <%
		                            BigDecimal butie =BigDecimal.valueOf(0.00);
		                            if (1 == data.get(i).getIsNotRealOrder())
		                            {
		                                butie = data.get(i).getOrderCommission().subtract(data.get(i).getRealOrderCommission()) ;
		                            }
		                            
		                            if (butie.compareTo(BigDecimal.valueOf(0.00)) == 1)  // BigDecimal 比较 返回1  代表大于
		                            {
		                            %>
		                                <td style="color:red;font-weight:600"><%=butie%></td>
		                            <%
		                            }
		                            else
		                            {
		                             %>
		                                <td><%=butie%></td>
		                            <%}
		                        %>
		                        
		                        <td><%=data.get(i).getDistribSubsidy()%></td>
		                        <td><%=data.get(i).getWebsiteSubsidy()%></td>
		                        <td><%=data.get(i).getAdjustment()%></td>
		                        <td><%=data.get(i).getBusinessCommission()%></td>
		                        <td><%=data.get(i).getGroupName()%></td>
				                <td><%=data.get(i).getGroupName()%></td>
                     		    <td><%=data.get(i).getHadUploadCount()%>/<%=data.get(i).getOrderCount()%></td>
         					    <td><%=data.get(i).getGroupName()%></td>
                          	    <td><%=data.get(i).getGroupName()%></td>
                        <td><a  href="javascript:showMapData('@item.Id')">查看地图</a></td>
							</tr>
							<%
								}
							%>
						</tbody>	
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="<%=basePath%>/js/plugins/jeditable/jquery.jeditable.js"></script>
<!-- Data Tables -->
<script src="<%=basePath%>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="<%=basePath%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=basePath%>/js/hplus.js"></script>
