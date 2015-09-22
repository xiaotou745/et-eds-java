<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.domain.BusinessModel"%>

<%
	String basePath = PropertyUtils.getProperty("java.business.url");
    BusinessModel businessModel=(BusinessModel) request.getAttribute("businessModel");//商家基本信息
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
			</span> <input class="fl" type="text" id="telphone" name="" value=""  max-length="20" /> <em
				class="fl" style="display:none" >请输入正确手机号码或固定电话号码</em>
		<ul id="list" class="vh">
			<!-- 加入 "vh" 选择器,为隐藏 -->
		</ul>
		</p>
		<p class="cb">
			<span class="fl"> <em class="fl">*</em> 收货人地址
			</span> <input class="fl" type="text" id="address" name="" value=""  max-length="20"/>
			<em class="fl" style="display:none"><%  // 一键发单客户端校验逻辑  不允许一键发单时
	    	if(businessModel.getOnekeypuborder()==0)
	    	{
		%>
		       收货地址不能为空且不能超过20个字符
		<% }else{%>
		 收货地址不能超过20个字符
		<% }%>
		</em>
		</p>
	   <p class="cb">
			<span class="fl">
				<!-- <em class="fl">*</em> -->
				收货人姓名
			</span>
			<input class="fl" type="text" id="name"   max-length="20"/>
			<em class="fl" style="display:none">收货人姓名不能超过20个字符</em>
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
			</span> 
		    <label class="fl"> <input type="radio" class="fl"
				name="fukuan" checked value="0"> 未付款
			</label>
			<label class="fl"> <input type="radio" class="fl"
				name="fukuan" value="1"> 已付款
			</label>
		</p>
		<div class="orderBox dn">
			<p class="cb">
				<span class="fl"> 订单数量 </span> <s class="fl">1单</s> <a
					href="javascript:;" class="fl add">添加</a>
			</p>
			<p class="cb copy">
				<em class="fl impo">*</em>
				<span class="fl"> 订单1 </span> <b class="fl"> <input type="text" class="price">
					元
				</b> <a href="javascript:;" class="fl del">删除</a> <em class="fl ts">订单金额必须在5-1000元之间</em>
			</p>
		</div>
		<p class="cb">
			<span class="fl"> 订单金额 </span> <em class="fl" id="allPrice">￥0</em>
		</p>
		<p class="cb">
			<span class="fl lh15"> 备注 </span>
			<textarea class="fl" id="remark"  max-length="50" style="margin-right:20px;"></textarea>
			<em class="fl" style="display:none">备注最多50字</em>
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
			<span class="fl">订单金额</span> <em class="fl">￥0</em>
		</p>
		<p class="cb">
			<span class="fl">订单数量</span> <em class="fl">1</em>
		</p>
		<p class="cb">
			<span class="fl">配送费</span> <em class="fl">￥0.00</em>
		</p>
		<p class="cb">
			<span class="fl">订单总金额</span> <em class="fl">￥0</em>
			<input type="hidden" name="amount" value="" id="amount"/>
		</p>
		<i>当前任务结算<s>0</s>元，剩余余额<s>0</s>元！
		</i> <a class="qx" href="javascript:;">取消</a>
		<a class="qr" href="javascript:;">确认</a>	
		<!-- class=“qr“ 加入选择器”ok"呼出任务发布成功弹层 -->
		<!-- class=“qr“ 加入选择器”no"呼出任务发布失败弹层 -->
	</div>
</div>
<!-- 任务发布失败弹层 -->
<div class="popup  popup6" style="display:none;">
	<div class="bg">蒙层</div>
	<div class="popupBox popupBox2 popup6">
		<img src="<%=basePath%>/images/no.png" alt="失败">
		<h2>任务发布失败！</h2>
		<a class="qr3" href="javascript:;">确认</a>
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
			var all = 0;
			var priceList = $('.price');
			for(var i=0;i<priceList.length;i++){
				var tempMoney=0;
				if(!parseFloat($(priceList[i]).val())){
					tempMoney=0;
				}else{
					tempMoney=parseFloat($(priceList[i]).val());
				}
				all = tempMoney+all;
			}
			all = Math.round(all*100)/100;
			all = all.toFixed(2);
			$('#amount').val(all);  // 真实订单金额
			$('#allPrice').html('¥'+all);  
		});

		//确认发布任务弹窗呼出 And 关闭
		$('.fabu').on('click',function() {
			var validate = true;
			var remark = $("#remark");
			if(remark.val().length>remark.attr("max-length")){
				validate= false;
				remark.next().show();
			}else{
				remark.next().hide();
			}
			//手机号非空判断
			var inputList = $("input");
			for(var i = 0;i<inputList.length;i++){
				var temp = vinput(inputList[i]);
				if(!temp){
					validate = temp;
				}
			}			

			if(validate){
				$(".popup1").show();
				$(".popup1 .cb:eq(0) em").html($("#allPrice").html()); //订单金额
				$(".popup1 .cb:eq(1) em").html($(".price").length);  //订单数量
				var totaldistribsubsidy=<%=businessModel.getDistribsubsidy()%>*$(".price").length;
				$(".popup1 .cb:eq(2) em").html('￥'+Math.round(totaldistribsubsidy).toFixed(2));  //外送费
				var amont=parseFloat($('#amount').val());
				var total=parseFloat(totaldistribsubsidy);
				$(".popup1 .cb:eq(3) em").html('￥'+(amont+total).toFixed(2));  //总金额
				var paramaters=getDatas();
				var url = "<%=basePath%>/order/getbalanceinfo";
				$.ajax({
					type : 'POST',
					url : url,
					data :paramaters,
					success : function(result) {
						$(".popup1 i s:eq(0)").html(result.settleMoney); //任务结算
						$(".popup1 i s:eq(1)").html(result.balanceprice); //余额
						if(result.responseCode!=0){
								$('.popup6').find('h2').html(result.message);
							    $('.popup6').show();
						}
					}
				});
			}
		});
		

		//任务发布 弹出层 的确认按钮 触发 ajax 请求  caoheyang 20150819
		$('.qr').on('click', function() {
			$(this).parents('.popup1').hide();
			var paramaters=getDatas();
			var url = "<%=basePath%>/order/add";
			$.ajax({
				type : 'POST',
				url : url,
				data :paramaters,
				success : function(result) {
                	if(result.responseCode==0){  
					    //异步请求成功 呼出 成功层
						$('.popup2').show();
					} else if(result.responseCode==-8){  
						$('.popup3').show();  //余额不足弹层
					} else
					{
					  $('.popup6').find('h2').html(result.message);
					  $('.popup6').show();
					}
				}
			});

		});
		
		//整个form表单内的数据  add by caoheyang 20150824
		function getDatas()
		{
			var recevicename=$("#name").val();  //收货人姓名
			var recevicephoneno=$("#telphone ").val(); //收货人电话
			var receviceaddress=$("#address").val(); //收货人地址
			var ispay=$("input[name='fukuan']:checked").val()==0?false:true;//是否已付款
			var amount=$("#amount").val(); //金额
			var remark=$("#remark").val();  //备注
			var ordercount=$('.copy').length; //订单数量
			var listOrderChild = new Array();
			$('.copy').each(function(index, domEle) {	
				var temp={"goodprice":$(this).find('input[type=text]').val()};
				listOrderChild.push(temp);
			});

			var paramaters={"recevicename":recevicename,"recevicephoneno":recevicephoneno,"receviceaddress":receviceaddress,
 					"ispay":ispay,"amount":amount,"remark":remark,"ordercount":ordercount,"childstr":JSON.stringify(listOrderChild)};
			return paramaters;
		}
		
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
			 window.location.reload();
		});

		//任务发布失败弹层呼出 And 关闭
		$('.no2').on('click', function() {
			$(this).parents('.popup1').hide();
			$('.popup6').show();
		});
		
		$('.qr3').on('click', function() {
			$(this).parents('.popup6').hide();
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

		//表单验证配置
		var teg = {};
		<%  // 一键发单客户端校验逻辑  不允许一键发单时
	    	if(businessModel.getOnekeypuborder()==0)
	    	{
		%>
			teg.telphone = /(?=^1\d{10}$)|(?=^[^1]\d{6,11}$)/;  //匹配电话号码
			teg.address = 'empty';
		<% }
	    	else{
			%>
			teg.telphone = /(?=^1\d{10}$)|(?=^[^1]\d{6,11}$)|(^\s*$)/;  //匹配电话号码 或者为空
			<%
			}%>

		$('.box2').on('blur','input',function(){
			vinput(this);
		});
	
		
		//客户端安全校验
		function vinput(input){
			if($(input).attr('type')!='text'){
				return true;
			}
			var id = $(input).attr('id');
			var reg = teg[id];
			var val = $(input).val();

			if($(input).attr('max-length')){
				if($(input).val().length>$(input).attr('max-length')){
					$(input).next().show();
					return false;
				}else{
					$(input).next().hide();
				}
			}

			if(reg){

				if(reg == 'empty'){
					if(val != ''){
						$(input).next().hide();
						return true;
					}
				}else{
					if(reg.test(val)){
						$(input).next().hide();
						return true;
					}
				}
				
				$(input).next().show();
				return false;
			}else if($(input).hasClass('price')){
				//金额操作
				if(val<5 || val>1000){
					$(input).parent().next().next().attr('style','display:block!important');
					return false;
				}
				$(input).parent().next().next().removeAttr('style');
				var all = 0;
				var priceList = $('.price');
				var tempresult=true;
				for(var i=0;i<priceList.length;i++){
					if(!parseFloat($(priceList[i]).val())||parseFloat($(priceList[i]).val())<5||parseFloat($(priceList[i]).val())>1000){
						tempresult= false;
					}else{
						all = parseFloat($(priceList[i]).val())+all;
					}
				}
				
				all = Math.round(all*100)/100;
				all = all.toFixed(2);
				$('#amount').val(all);  // 真实订单金额
				$('#allPrice').html('¥'+all);  

				return tempresult;
			}
			return true;
		}
		
		//键盘事件
		$('.orderBox').on('keydown','.price',function(e){
			var obj=e.srcElement || e.target;
			var dot=obj.value.indexOf(".");//alert(e.which);
			var  key=e.keyCode|| e.which;

			if(key==8 || key==9 || key==46 || (key>=37  && key<=40))
				return true;
			if ((key<=57 && key>=48) || (key<=105 && key>=95)  ) { //数字
				if(dot==-1)//没有小数点
			    	return true;
			  	else if(obj.value.length<=dot+2)//两位小数
			  		return true;
			}else if((key==46 || key==190 || key==110 ) && dot==-1){//小数点
			  	return true;
			}        
			return false;
		})
	
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



