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
		<em class="fl"><i>￥</i>500</em>
	</div>
	<div class="cb sBox">
		<b class="fl">累计充值</b>
		<s class="fl"><i>￥</i>500</s>
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
				<input type="hidden" name="WIDdefaultbank" value="alipay">
					<a href="javascript:;" class="fl zhifu on" id="alipay">支付宝</a> 
					<a href="javascript:;" class="fl zhifu" id="ICBC">财付通</a>
				</p>
			</div>
		</form></div>
<input type="button" value="确认支付" id="pay">
<div class="wxtsBox">
	<h2>温馨提示</h2>
	<p>1、充值金额会在当天到账。如遇问题，可联系客服：<span>4006-380-177</span></p>
</div>
<script>
$(document).ready(function() {
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
		if(check(true)){
			var selectedfee=$("input[name='WIDtotal_fee']:checked");
	    	if(selectedfee.attr('id')=="customerfee"){
	    		$("#selectedfee").val($("#orderBox").val());
	    	}
			$("#alipayment").submit();
		}
	});
    $("input[type='radio'][name='WIDtotal_fee']").change(function() { 
    	var fee=$("input[name='WIDtotal_fee']:checked");
    	if($(fee).attr('id')!="customerfee"){
    		$("#orderBox").val("");
    	}
    }); 
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

});
</script>