<%@page import="com.edaisong.business.common.UserContext"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.resp.OrderDetailBusinessResp"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.enums.PayStatus"%>
<%@page import="com.edaisong.core.enums.PayType"%>
<%@page import="com.edaisong.core.enums.OrderFrom"%>
<%@page import="com.edaisong.core.enums.OrderIsPay"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
	OrderDetailBusinessResp modelDatas = (OrderDetailBusinessResp) request
			.getAttribute("modelDatas");
%>
<p class="mbx">
	<a class="fl" href="javascript:;">全部订单</a> <span class="fl">></span> <a
		class="fl on" href="javascript:;">订单详情</a>
</p>
<div class="top top2 cb">
	<div class="left2 fl">
		<h4>
			订单号：
			<%=modelDatas.getOrderModel().getOrderNo()%>
		</h4>
		<h4>
			状态：<span class="green2"><%=OrderStatus.getEnum(
					modelDatas.getOrderModel().getStatus()).desc()%> </span>
		</h4>
		<%
		if(modelDatas.getOrderModel().getOriginalOrderNo()!=null&&!modelDatas.getOrderModel().getOriginalOrderNo().isEmpty()){%>
			<h5 class="mt8">
			第三方订单号：<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getOriginalOrderNo())%></h5>
	    	<h5>
		<%} %>
			订单来源：
			<%=OrderFrom.getEnum(modelDatas.getOrderModel().getOrderFrom()).desc()%></h5>
		<h5 >
			备注：<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getRemark())%></h5>
	</div>
	<div class="right2 fl">
	<%
				String checkStyle="";
			    if (modelDatas.getOrderModel().getActualDoneDate() != null) {
					checkStyle = "w1 w2 w3 w4";
				} else if (modelDatas.getOrderModel().getTakeTime() != null) {
					checkStyle = "w1 w2 w3";
				} else if (modelDatas.getOrderModel().getGrabTime() != null) {
					checkStyle = "w1 w2";
				} else if (modelDatas.getOrderModel().getPubDate() != null) {
					checkStyle = "w1";
				}
			%>
		<div class="r-t cb" id="divCanel">
		    <% if(modelDatas.getOrderModel().getStatus()==OrderStatus.Cancel.value())
		  	  {
		    	if (modelDatas.getOrderModel().getGrabTime() != null) {
					 %>
	    			  <p class="fl" style="border:none">
	    			  </p>
	    	    <% 
			  }  if (modelDatas.getOrderModel().getTakeTime() != null) {
				 %>
	   			  <p class="fl" style="border:none">
	   			  </p>    	     	     	  
   			  <% 
				  } 
			   if (modelDatas.getOrderModel().getActualDoneDate() != null) {
		    	  %>
		    		 <p class="fl" style="border:none"></p>		    	     	     	     	  
		    	  <% 
					}
		    %>

			<p class="fl">
				<!-- 加入vh选择器隐藏该标签 -->
				<span>订单取消</span> <em><%=ParseHelper.ToDateString(modelDatas.getOrderModel()
					.getCancelTime(), "MM.dd HH:mm")%></em>
			</p>
			<% }
		    else{%>
		   		 <p class="fl" style="border:none"></p>		    	
		    <%} %>
		</div>
		<div class="liuc">
			<img src="<%=basePath%>/images/icon-13.png" width="420" height="35"
				alt="置灰状态">
			
			<!-- w1 w2 w3 w4 分别为四种状态的选中效果选择器 -->
			<span class="on <%=checkStyle%>"> <img
				src="<%=basePath%>/images/icon-14.png" width="420" height="35"
				alt="选中状态">
			</span>
		</div>
		<div class="r-t2 cb">
			<p class="fl" style="width:106px;">
				<span>发布</span> <em ><%=ParseHelper.ToDateString(modelDatas.getOrderModel()
					.getPubDate(), "MM.dd HH:mm")%>
				</em>
			</p>
			<p class="fl" style="width:106px;">
				<span>接单</span> <em ><%=ParseHelper.ToDateString(modelDatas.getOrderModel()
					.getGrabTime(), "MM.dd HH:mm")%></em>
			</p>
			<p class="fl" style="width:106px;">
				<span>取餐</span> <em><%=ParseHelper.ToDateString(modelDatas.getOrderModel()
					.getTakeTime(), "MM.dd HH:mm")%></em>
			</p>
			<p class="fl" style="width:106px;">
				<span>完成</span> <em><%=ParseHelper.ToDateString(modelDatas.getOrderModel()
					.getActualDoneDate(), "MM.dd HH:mm")%></em>
			</p>
		</div>
	</div>
</div>
<div class="bottom bottom2 bottom4 cb">
	<div class="left2 fl">
		<div class="box">
			<h4>收货人信息</h4>
			<h5>
				收货人电话：<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getRecevicePhoneNo())%></h5>
			<h5>
				收货人地址：<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getReceviceAddress())%></h5>
		</div>
		<div class="box">
			<h4>骑士信息</h4>
			<h5>
				骑士手机：<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getPhoneNo())%></h5>
			<h5>
				骑士姓名：<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getTrueName())%></h5>
		</div>
	</div>
	<div class="right2 fl">
		    <% if(modelDatas.getOrderModel().getStatus()==OrderStatus.New.value()
		    &&UserContext.getCurrentContext(request).getBusinessType()!=1){%>
	    <input type="button" value="取消订单"  class="qxOrder">
	    		<% }%>
		<h2>订单明细</h2>
		<p>
			总金额：<span class="red2">￥<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getAmount())%></span> 结算支出：<span class="red2">￥<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getSettleMoney())%></span> 订单佣金：<span class="red2">￥<%=ParseHelper.ShowString(modelDatas.getOrderModel()
					.getOrderCommission())%></span> 用户付款状态：<span> <%=ParseHelper.ShowString(OrderIsPay.getEnum(
					modelDatas.getOrderModel().getIsPay()).desc())%>
			</span>
		</p>
		<table class="stripe" width="100%">
			<tr>
				<td>订单金额</td>
				<td>配送费</td>
				<td>支付状态</td>
				<td>支付类型</td>
				<td>小票</td>
			</tr>
			<%
				for (int i = 0; i < modelDatas.getOrderChilds().size(); i++) {
			%>
			<tr>
				<td>￥<%=ParseHelper.ShowString(modelDatas.getOrderChilds()
						.get(i).getGoodprice())%>
				</td>
				<td>￥<%=ParseHelper.ShowString(modelDatas.getOrderChilds()
						.get(i).getDeliveryprice())%></td>
				<td><%=ParseHelper.ShowString(PayStatus.getEnum(
						modelDatas.getOrderChilds().get(i).getPaystatus())
						.desc())%></td>
				<td><%=ParseHelper
						.ShowString(modelDatas.getOrderChilds().get(i)
								.getPaytype() == null ? "" : PayType
								.getEnum(
										modelDatas.getOrderChilds().get(i)
												.getPaytype()).desc())%></td>
				<td>
				<% 
				 if(modelDatas.getOrderChilds().get(i).getTicketurl()!=null&&!modelDatas.getOrderChilds().get(i).getTicketurl().isEmpty())
				 {
				%>
				<a
					href="<%=PropertyUtils.getProperty("ImageServicePath")%><%=modelDatas.getOrderChilds().get(i).getTicketurl()%>"  target="_blank">查看</a>
				<%
				} else{%>
			      未上传
					<% }%>
				</td>
			</tr>

			<%
				}
			%>
		</table>
	</div>
</div>

<!-- 确认取消订单 -->
<div class="popup popup5" style="display:none;">
	<div class="bg">蒙层</div>
	<div class="popupBox popupBox5">
		<h1>确定要取消该订单吗？</h1>
		<a class="qx2" href="javascript:;">取消</a>
		<a class="qr2" href="javascript:;" >确认</a>
	</div>
</div>

<script>
$(function(){
	
	function countH(){
		var WinHeight = $(window).height();
		$(".nav").css({
		    "min-height":WinHeight-70
		})
	}
	$(function(){
		countH();

		//确认取消订单弹层呼出 And 关闭
		$('.qxOrder').on('click',function(){
			$('.popup5').show();
		});
		$('.qx2').on('click',function(){
			$(this).parents('.popup5').hide();
		});
	})
	//取消订单事件
	$(".qr2").click(function(){
		 var data={"orderNo":"<%=modelDatas.getOrderModel().getOrderNo()%>","orderId":<%=modelDatas.getOrderModel().getId() %>};
	     $.post("<%=basePath%>/order/canelorder", data,
                 function(result) {
				if(result.responseCode==0){
					alert("取消订单成功！");
					window.location.reload();
				}
	   	    	});
		});
	});
</script>