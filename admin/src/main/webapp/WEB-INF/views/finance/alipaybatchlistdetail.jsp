<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.entity.AlipayBatch"%>
<%@page
	import="com.edaisong.entity.domain.AlipayBatchClienterWithdrawForm"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.AlipayBatchStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.core.enums.ClienterWithdrawFormStatus"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
    String netUrl = PropertyUtils.getProperty("net.admin.url");
	AlipayBatch alipayBatch = (AlipayBatch) request.getAttribute("alipayBatch");
	List<AlipayBatchClienterWithdrawForm> list = (List<AlipayBatchClienterWithdrawForm>) request.getAttribute("withdrawForms");
	String callTimeStr = alipayBatch.getStatus() == AlipayBatchStatus.PlayGame.value() ? "- -" : ParseHelper.ToDateString(alipayBatch.getLastOptTime());
%>
<style type="text/css">
#map_contain {
	height: 90%;
	width: 100%;
	max-width: none;
}

label {
	max-width: none;
}

#control {
	width: 100%;
}
</style>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-6 control-label">批次号:</label> <label
							class="col-sm-6 control-label"><%=alipayBatch.getBatchNo()%></label>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-6 control-label">打款状态:</label> <label
							class="col-sm-6 control-label">	<%
					if (alipayBatch.getStatus() == AlipayBatchStatus.PlayGame.value()) {
				%>  <font style="color:red"><%=AlipayBatchStatus.getEnum(alipayBatch.getStatus()).desc()%> </font>
				<%
					} else {
				%> <%=AlipayBatchStatus.getEnum(alipayBatch.getStatus()).desc()%>
				<%
					}
				%></label>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-6 control-label">操作人:</label> <label
							class="col-sm-6 control-label"><%=alipayBatch.getLastOptUser()%></label>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-6 control-label">总提现金额:</label> <label
							class="col-sm-6 control-label"><%=alipayBatch.getTotalWithdraw()%>元</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-6 control-label">总提现单数:</label> <label
							class="col-sm-6 control-label"><%=alipayBatch.getOptTimes()%>单</label>
					</div>
							</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-6 control-label">打款时间:</label> <label
								class="col-sm-6 control-label"><%=ParseHelper.ToDateString(alipayBatch.getLastOptTime())%></label>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-6 control-label">回调时间:</label> <label
								class="col-sm-6 control-label"><%=callTimeStr%></label>
						</div>
					</div>
					<div class="col-lg-3">
					<%if(alipayBatch.getStatus() == AlipayBatchStatus.PlayGame.value()&&ParseHelper.plusDate(alipayBatch.getLastOptTime(), 4, 10)
							.compareTo(new Date())<0
					&&(UserContext.getCurrentContext(request).getLoginName().trim().equals("admin")||
							UserContext.getCurrentContext(request).getLoginName().trim().equals("douhaichao"))) {%>
					<div class="form-group">
								<button type="button" class="btn btn-w-m btn-primary" id="btnDosure" style="margin-left: 3px;height:30px;">打款</button>
						</div>
						<%} %>
					</div>
		
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content">
			<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="%5">提现单号</th>
			<th width="%5">支付宝账号</th>
			<th width="%5">支付宝账户名</th>
			<th width="%5">提现金额</th>
			<th width="%5">提现状态</th>
			<th width="%5">操作人</th>
			<th width="%5">备注原因</th>
		</tr>
	</thead>
	<tbody>
		<%
				for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><a href="<%=netUrl%>/ClienterWithdraw/ClienterWithdrawDetail?withwardId=<%=list.get(i).getId()%>"><%=list.get(i).getWithwardNo()%></a></td>
			<td><%=ParseHelper.toDecrypt(list.get(i).getAccountNo())%></td>
			<td><%=list.get(i).getTrueName()%></td>
			<td>
				<%=list.get(i).getAmount()%>
			</td>
			<td><%=ClienterWithdrawFormStatus.getEnum(list.get(i).getStatus()).desc()%></td>
			<td>
					<%=list.get(i).getLastOptUser()%>
			</td>
			<td><%=list.get(i).getPayFailedReason()==null?"--": list.get(i).getPayFailedReason()%></td>
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

<script >
/*确认打款功能*/
$(function(){
	$("#btnDosure").click(function (){
		//询问框
		layer.confirm('您确认要提交修改吗？？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		}, function(){
			window.open("<%=netUrl%>/ClienterWithdraw/AlipayBatchTransfer?type=2&data=<%=alipayBatch.getBatchNo()+""%>");
			 var index= layer.alert('请在新打开的页面完成打款！', {
				btn:["已完成打款"],
			    skin: 'layui-layer-molv', //样式类名
			    closeBtn: false
			},function(){
				window.location.reload();
				layer.close(index);  //关闭弹层
			}); 
		}, function(){
		    
		});
	})
});


</script>
