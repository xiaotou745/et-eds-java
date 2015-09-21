<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%	
String basePath =PropertyUtils.getProperty("static.business.url");
%>
<div class="top cb">
  <h3 class="cb">充值</h3>
</div>
<div class="box3">
	<div class="cb sBox">
		<span class="fl">您当前余额</span>
		<em class="fl" id="balance">￥500</em>
	</div>
	<div class="cb sBox">
		<b class="fl">累计充值</b>
		<s class="fl" id="total">￥500</s>
	</div>
</div>
<div class="box3">
		<form name="alipayment" id="alipayment" action="alipayapi" method="post" target="_blank">
			<div class="cb sBox" id="default">
				<span class="fl fz14">充值金额</span> <label class="fl"> <input
					class="fl" type="radio" name="WIDtotal_fee" value="500" checked="checked"> 500元
				</label> <label class="fl fz14"> <input class="fl" type="radio"
					name="WIDtotal_fee" value="1000"> 1000元
				</label> <label class="fl fz14"> <input class="fl" type="radio"
					name="WIDtotal_fee" value="2000"> 2000元
				</label> <label class="fl fz14"> <input class="fl" type="radio"
					name="WIDtotal_fee" value="5000"> 5000元
				</label> <label class="fl fz14"> <input class="fl" type="radio"
					name="WIDtotal_fee" value="10000"> 10000元
				</label>
			</div>
			<div class="cb sBox">
				<span class="fl">&nbsp;</span> <input class="fl" type="radio" id="customerfee"
					name="WIDtotal_fee"> <input class="fl" type="text"
					id="orderBox"> <i class="fl">元</i> <em class="fl tishiyu">请输入1-100000范围内整数</em>
			</div>
			<div class="cb sBox">
				<span class="fl fz14">支付方式</span>
				<p class="fl">
				<input type="hidden" name="WIDdefaultbank" id="WIDdefaultbank" value="alipay">
				<input type="hidden" name="WIDout_trade_no" id="WIDout_trade_no" value="">
					<a href="javascript:;" class="fl zhifu on" id="alipay">支付宝</a> 
				</p>
			</div>
		</form></div>
<input type="button" value="确认支付" id="pay">
<div class="wxtsBox" id="rechargeResult" style="display:none">
<div id="orderResult"></div>
<span id="timespan">10</span>秒后自动刷新页面
</div>
<div class="wxtsBox">
	<h2>温馨提示</h2>
	<p>1、充值金额会在当天到账。如遇问题，可联系客服：<span>4006-380-177</span></p>
</div>
<div class="popup" style="display: none;">
	<div class="bg"></div>
	<div class="top" id="failDiv">
		<span>!</span>
		充值还未成功，请确认是否完成支付！
	</div>
	<div class="popupBox popupBox2 popupBox6" id="confirmDiv">
		<s class="close" onclick="reload()">关闭</s>
		<h3>充值完成前请不要关闭此窗口</h3>
		<img alt="支付" src="<%=basePath%>/images/zhifu.png">
		<h2>请在新开的页面完成充值</h2>
		<a href="javascript:void(0)" onclick="showRechargeStatus()">已完成充值</a>
		<a class="wt" href="javascript:void(0)" onclick="failRecharge()">充值未成功</a>
	</div>
</div>


<script>
function reload(){
	window.location.href = window.location.href;
}
function showRechargeStatus(){
	var url = "<%=basePath%>/group/getrechargestatus";
	$.ajax({
		type : 'POST',
		url : url,
		data : {"orderNO":$("#WIDout_trade_no").val()},
		success : function(data) {
			if(data==null||data==""||data==undefined||data.paystatus==undefined){
				alert("没有找到订单号为："+$("#WIDout_trade_no").val()+"的充值订单信息");
				return;
			}
			var status="未知";
			switch(data.paystatus){
				case -1:status="支付失败";break;
				case 0:status="支付中";break;
				case 1:status="支付成功";break;
			}
			if(data.paystatus!=1){
				failRecharge();
			}else{
				$("#orderResult").html("<font style=\"color:red\">充值单号："+data.orderno+";充值金额："+data.payamount+";充值状态："+status+"</font>");
				$('.popup').hide();
				$("#rechargeResult").show();
				
				setTimeout(function (){
					$("#rechargeResult").hide();
					window.location.href = window.location.href;
					}, 10000);
				var intervalId=setInterval(function(){
					var timeSection=document.getElementById('timespan');
					var second=parseInt(timeSection.innerHTML);
					if(second<=0){
						clearInterval(intervalId);
					}else{
						timeSection.innerHTML=second-1;
					}
				},1000);
			}
		}
	});
}
function failRecharge(){
	$("#confirmDiv").hide();
	$("#failDiv").show();
}
function check(pay){
	var radio = $('.box3 input:radio:checked');
	var money = radio.val();
	var tip = "请选择充值金额";

	if(radio.parents('.sBox').find("#orderBox").length>0){
		money = radio.parents('.sBox').find("#orderBox").val();
		tip = "请输入1-100000范围内整数"
	}
	$('#orderBox').parents('.sBox').find('.tishiyu').html(tip);
	if(money>=1 && money<=100000){
		$('#orderBox').parents('.sBox').find('.tishiyu').hide();
		return true;
	}else{
		$('#orderBox').parents('.sBox').find('.tishiyu').show();
		return false;
	}
}
function getGroupBalance(){
	var url = "<%=basePath%>/group/getgroupbalance";
	var paramaters="";
	$.ajax({
		type : 'POST',
		url : url,
		//data : paramaters,
		success : function(data) {
			var info=data.split(";");
			$("#balance").html("￥"+info[0]);
			$("#total").html("￥"+info[1]);
		}
	});
}
$(document).ready(function() {
	getGroupBalance();
	//支付方式单选
	$('.zhifu').on('click',function(){
		$(this).siblings().removeClass('on');
		$(this).addClass('on')
		$("#WIDdefaultbank").val(this.id);
	});
	$('#orderBox').on('focus',function(){
		$("#customerfee").prop('checked','checked')
	})
	$('#orderBox').on('keydown',function(e){
		var obj=e.srcElement || e.target;
		var dot=obj.value.indexOf(".");//alert(e.which);
		var  key=e.keyCode|| e.which;
		console.log(key);
		if(key==8){
			return true;
		}
		if ((key<=57 && key>=48) || (key<=105 && key>=95)  ) { //数字
		   	return true;
		}
		return false;
	});
	$('#orderBox').on('blur',function(){
		var self = this;
		setTimeout(function(){
			if($(self).parents('.sBox').find('input:radio:checked').length>0){
				check()
			}
		},100)

	});
	$('#default').on('click',function(){
		$('#orderBox').parents('.sBox').find('.tishiyu').hide();
	})
	$('#pay').on('click',function(){
		if (!confirm("是否确定充值？")){
			return;
		}
		if(check(true)){
			
			var selectedfee=$("input[name='WIDtotal_fee']:checked");
	    	if(selectedfee.attr('id')=="customerfee"){
	    		$("#selectedfee").val($("#orderBox").val());
	    	}
	    	var url = "<%=basePath%>/group/createorderno";
			var paramaters="";
			$.ajax({
				type : 'POST',
				url : url,
				//data : paramaters,
				success : function(data) {
					if(data.length>50){
						alert("生成单号时出错了");
						return;
					}
					$('.popup').show();
					$("#failDiv").hide();
					$("#WIDout_trade_no").val(data);
					$("#alipayment").submit();
				}
			});
		}
	});
	
    $("input[type='radio'][name='WIDtotal_fee']").change(function() { 
    	var fee=$("input[name='WIDtotal_fee']:checked");
    	if($(fee).attr('id')!="customerfee"){
    		$("#orderBox").val("");
    	}
    }); 
});
</script>