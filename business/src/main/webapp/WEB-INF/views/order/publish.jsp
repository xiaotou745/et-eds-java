<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
	String basePath = PropertyUtils.getProperty("static.business.url");
%>

<div class="top cb">
	<h3 class="cb">发布任务</h3>
</div>
<div class="box2">
	<h4 class="cb">
		<span class="fl">收货人信息</span> <i class="fl">虚线</i>
	</h4>
	<div class="box2-ny">
		<p class="cb">
			<span class="fl"> <em class="fl">*</em> 收货人电话
			</span> <input class="fl" type="text" id="telphone" name="" value="" /> <em
				class="fl" style="display:none">请输入正确手机号码或固定电话号码</em>
		<ul id="list" class="vh">
			<!-- 加入 "vh" 选择器,为隐藏 -->
		</ul>
		</p>
		<p class="cb">
			<span class="fl"> <em class="fl">*</em> 收货人地址
			</span> <input class="fl" type="text" id="address" name="" value="">
			<em class="fl" style="display:none">收货地址不能为空</em>
		</p>
		<p class="cb">
			<span class="fl"> <em class="fl">*</em> 收货人姓名
			</span> <input class="fl" type="text" id="name" name="" value=""> <em
				class="fl" style="display:none">收货人姓名不能为空</em>
		</p>
	</div>
</div>
<div class="box2">
	<h4 class="cb">
		<span class="fl">订单信息</span> <i class="fl">虚线</i>
	</h4>
	<div class="box2-ny">
		<p class="cb">
			<span class="fl"> <em class="fl">*</em> 顾客是否付款
			</span> <label class="fl"> <input type="radio" class="fl"
				name="fukuan"> 已付款
			</label> <label class="fl"> <input type="radio" class="fl"
				name="fukuan" checked> 未付款
			</label>
		</p>
		<div class="orderBox dn">
			<p class="cb">
				<span class="fl"> 订单数量 </span> <s class="fl">1单</s> <a
					href="javascript:;" class="fl add">添加</a>
			</p>
			<p class="cb copy">
				<span class="fl"> 订单1 </span> <b class="fl"> <input type="text">
					元
				</b> <a href="javascript:;" class="fl del">删除</a> <em class="fl ts">订单金额必须大于等于5元且小于1000元</em>
			</p>
		</div>
		<p class="cb">
			<span class="fl"> 订单金额 </span> <em class="fl">￥45.00</em>
		</p>
		<p class="cb">
			<span class="fl lh15"> 备注 </span>
			<textarea class="fl"></textarea>
		</p>
	</div>
</div>
<div class="projects">
	<input type="button" value="发布任务" class="fabu">
</div>
<!-- 确认发布任务弹层 -->
<div class="popup popup1" style="display: none;">
	<div class="bg">蒙层</div>
	<div class="popupBox popupBox1">
		<h1>确认发布任务</h1>
		<p class="cb">
			<span class="fl">订单金额</span> <em class="fl">￥41.00</em>
		</p>
		<p class="cb">
			<span class="fl">订单数量</span> <em class="fl">3</em>
		</p>
		<p class="cb">
			<span class="fl">配送费</span> <em class="fl">￥0.00</em>
		</p>
		<p class="cb">
			<span class="fl">订单总金额</span> <em class="fl">￥41.00</em>
		</p>
		<i>当前任务结算<s>45</s>元，剩余余额<s>6</s>元！
		</i> <a class="qx" href="javascript:;">取消</a> <a class="qr"
			href="javascript:;">确认</a>
		<!-- class=“qr“ 加入选择器”ok"呼出任务发布成功弹层 -->
		<!-- class=“qr“ 加入选择器”no"呼出任务发布失败弹层 -->
	</div>
</div>

<!-- 任务发布成功弹层 -->
<div class="popup popup2" style="display: none;">
	<div class="bg">蒙层</div>
	<div class="popupBox popupBox2">
		<img src="<%=basePath%>/images/ok.png" alt="成功">
		<h2>任务发布成功！</h2>
		<a class="qr2" href="javascript:;">确认</a>
	</div>
</div>

<!-- 余额不足弹层 -->
<div class="popup popup3" style="display: none;">
	<div class="bg">蒙层</div>
	<div class="popupBox popupBox3">
		<a class="close" href="javascript:;">关闭</a> <img
			src="<%=basePath%>/images/yebz.png" alt="余额不足">
		<p>您的余额不足</p>
		<p>请通过客户端APP充值！</p>
	</div>
</div>

<!-- 添加订单超出15个提示弹层 -->
<div class="popup popup4" style="display: none;">
	<div class="bg">蒙层</div>
	<div class="popupBox popupBox4">
		<a class="close" href="javascript:;">关闭</a>
		<p>最多只能发布15个订单！</p>
	</div>
</div>

<script>
	function countH() {
		var WinHeight = $(window).height();
		$(".nav").css({
			"min-height" : WinHeight - 70
		});
	};
	$(function() {
		countH();

		//添加订单数量
		var add = $('.copy').clone(true);
		$(".add").click(function() {
			if ($('.copy').length >= 1) {
				$(this).parents('.orderBox').removeClass('dn');
			} else {
				$(this).parents('.orderBox').addClass('dn');
			}
			if ($('.copy').length >= 15) {
				$('.popup4').show();
				return;
			}
			var clone = add.clone();
			clone.find('span').html('订单' + ($('.copy').length + 1))
			$('.orderBox').append(clone);
			$('.orderBox').find('s').eq(0).html($('.copy').length +"单");  //重置单量
		});
		//删除订单数量
		$('.orderBox').on('click', '.del', function() {
			$(this).parent().remove();
			//对子订单序列号重新进行排序
            for(index=0;index<$('.copy').length;index++)
            {
            	$('.copy').eq(index).find('span').html('订单' +  (index+1));
            }
			if ($('.copy').length < 2) {
				$('.orderBox').addClass('dn');
			} else {
				$('.orderBox').removeClass('dn');
			}
			$('.orderBox').find('s').eq(0).html($('.copy').length +"单");  //重置单量
		});

		//确认发布任务弹窗呼出 And 关闭
		$('.fabu').on('click',function() {
							var validate = true;
							//手机号非空判断 
							validate=checkEmpty("telphone");					
							//收货地址
							validate=checkEmpty("address");								
							//姓名
							validate=checkEmpty("name");	
							validate=true;
							if(validate){
								$('.popup1').show();
								var url = "<%=basePath%>/order/add";
								var paramaters={};
								$.ajax({
									type : 'POST',
									url : url,
									data : paramaters,
									success : function(result) {
										if(result.responseCode==0){
											alert("操作成功");
										}
										else{
										alert(result.message);}
									}
								});
							}
						});
		//验证元素非空，为空显示提示语，不为空隐藏提示语  add by caoheyang 20150818
		function checkEmpty(id){
			if ($("#"+id).val().replace(/(^\s*)|(\s*$)/g,"").length == 0) { 
				$("#"+id).next().css("display","block");
				return false;
			}else{
				$("#"+id).next().css("display","none");
			}
		}
		
		//任务发布 弹出层 的确认按钮 触发 ajax 请求  caoheyang 20150819
		$('.qr').on('click', function() {
			$(this).parents('.popup1').hide();
		    //异步请求成功 呼出 成功层
			$('.popup2').show();
		});

		$('.qx').on('click', function() {
			$(this).parents('.popup1').hide();
		});

		//任务发布成功弹层呼出 And 关闭
		$('.ok').on('click', function() {
			$(this).parents('.popup1').hide();
			$('.popup2').show();
		});
		
		$('.qr2').on('click', function() {
			$(this).parents('.popup2').hide();
		});

		//余额不足弹层呼出 And 关闭
		$('.no').on('click', function() {
			$(this).parents('.popup1').hide();
			$('.popup3').show();
		});
		$('.popupBox3 .close').on('click', function() {
			$(this).parents('.popup3').hide();
		});

		//余额不足弹层关闭
		$('.popupBox4 .close').on('click', function() {
			$(this).parents('.popup4').hide();
		});

		//自动完成     暂时屏蔽
		/* var list = [];
		$("#telphone").on("keyup",function(){
			var num = $(this).val();
			if(num == ''){
				$("#list").addClass("vh");
				return;
			}
			$("#list").html('');
			//post回调
			list = [{"telphone":"13118077251","name":"张浩","address":"北京理想大厦1"},
						{"telphone":"13118077251","name":"张浩","address":"北京理想大厦1"},
						{"telphone":"13118077251","name":"张浩","address":"北京理想大厦1"},
						{"telphone":"13118077251","name":"张浩","address":"北京理想大厦1"}
						 ];
			for(var i=0;i<list.length;i++){
				var item = '<li>'+
								'<a href="javascriptscript:;">'+
									'<i>'+list[i].telphone+'</i>'+
									'<s>'+list[i].name+"&nbsp;&nbsp;"+list[i].address+'</s>'+
									'<b>删除</b>'+
								'</a>'+
							'</li>'
				$("#list").append(item);
				
			}
			$("#list").removeClass("vh");
			// $.post('',{},function(){
			// })
		}); */

		//点击自动完成触发   暂时屏蔽
		/* $("#list").on('click','li,b',function(e){
			//点击删除自动联想
			var index = $(this).index();
			if($(this).is("b")){
				//发送一个请求
				$(this).parents("li").remove();
				if($("#list li").length == 0){
					$("#list").addClass("vh");
				}
				return false;
			}else{
				
				var info = list[index];
				$("#list").addClass("vh");
				$("#list").html('');
				$("#address").val(info.address);
				$("#telphone").val(info.telphone);
				$("#name").val(info.name);
			}
			
		}) */

	});
</script>



