//注册页面的提示文字
$(function() {
	// 手机号
	$(".ex_iphone").blur(iphone);
	$(".ex_iphone").focus(function(){$(".error1").html("");});
	// 密码
	$(".ex_pord").blur(password);
	$(".ex_pord").focus(function(){$(".error2").html("");});
	// 验证码
	$(".ex_get").blur(get);
	$(".ex_get").focus(function(){$(".error3").html("");});
	/*
	 * $(".ex_iphone").blur(abc); $(".ex_pord").blur(function(){
	 * 
	 * if( $(".ex_pord").val()==""|| $(".ex_pord").val()=="输入密码") {
	 * $(".error2").html("输入密码"); $(".error2").css("display","block"); } else {
	 * $(".error2").css("display","none"); } });
	 */
});
function testloginName(){
	var reg=/^[\u4E00-\u9FA5]$/;
	var loginName=$(".ex_iphone").val().trim();
	if (loginName == "" || loginName == "输入账号") {
		$(".error1").html("输入账号");
		$(".error1").css("display", "block");
		return false;
	} else if (reg.test(loginName)) {
		$(".error1").html("登陆账号不能为中文字符");
		$(".error1").css("display", "block");
		return false;
	} else if (loginName.length <6 || loginName.length>20) {
		$(".error1").html("登陆账号除中文外6-20位字符");
		$(".error1").css("display", "block");
		return false;
	}
	$(".error1").css("display", "none");
	return true;
}
function iphone() {
	var usertype=$("#userType").val();
	if(usertype==1){
		return testloginName();
	}
	
	var reg = /^1[0-9]{10}$/i;// 验证手机正则(输入前7位至11位)
	
	if ($(".ex_iphone").val() == "" || $(".ex_iphone").val() == "输入您的手机号") {
		$(".error1").html("请输入手机号");
		$(".error1").css("display", "block");
		return false;
	} else if ($(".ex_iphone").val().length < 11) {
		$(".error1").html("输入手机格式错误！");
		$(".error1").css("display", "block");
		return false;
	} else if (!reg.test($(".ex_iphone").val())) {
		$(".error1").html("输入手机格式错误!!");
		$(".error1").css("display", "block");
		return false;
	}
	$(".error1").css("display", "none");
	return true;
};
function password() {

	if ($(".ex_pord").val() == "" || $(".ex_pord").val() == "输入密码") {
		$(".error2").html("请输入密码");
		$(".error2").css("display", "block");
		return false;
	}
	if ($(".ex_pord").val().length < 6) {
		$(".error2").html("密码格式错误！");
		$(".error2").css("display", "block");
		return false;
	}
	$(".error1").css("display", "none");
	return true;
}
function get() {
	var reg = /^[A-Z,a-z,0-9]{4}$/;
	if ($(".ex_get").val() == "" || $(".ex_get").val() == "输入验证码") {
		$(".error3").html("请填写验证码");
		$(".error3").css("display", "block");
		return false;
	}
	if (!reg.test($(".ex_get").val())) {
		$(".error3").html("验证码错误！");
		$(".error3").css("display", "block");
		return false;
	}
	$(".error3").css("display", "none");
	return true;
};

/**
 * 定时确定是否操作,如果在指定时间内没有操作,则退出登录
 * @param 时间间隔(单位:秒)
 */
function timingCheckOperate(seconds){
	window.setInterval("logoff()",seconds/1000);
}
