//注册页面的提示文字
$(function(){
   //手机号
    $(".ex_iphone").blur(iphone);
	//密码
	$(".ex_pord").blur(password);
	//验证码
	$(".ex_get").blur(get);
//	$(".ex_iphone").blur(abc);
//	 $(".ex_pord").blur(function(){
//
//        if( $(".ex_pord").val()==""|| $(".ex_pord").val()=="输入密码")
//        {
//            $(".error2").html("输入密码");
//            $(".error2").css("display","block");
//        }
//        else
//        {
//            $(".error2").css("display","none");
//        }
//    });
    //验证码
    
	
	$('form').submit(function(){
		var flag =  iphone();
		flag = flag || password();
		flag = flag || get();
		alert("aa");
		if(flag){
			//请求接口
			$.ajax({
				
			});
		}		
		return false;

	})

	function iphone(){
       var reg=/^1[3|4|5|7|8][0-9]\d{4,8}$/i;//验证手机正则(输入前7位至11位)

        if( $(".ex_iphone").val()==""|| $(".ex_iphone").val()=="输入您的手机号")
        {
            $(".error1").html("输入手机格式错误");
            $(".error1").css("display","block");
			return false;
        }
        else if($(".ex_iphone").val().length<11)
        { 
            $(".error1").html("输入手机格式错误！");
            $(".error1").css("display","block");
			return false;
        }
        else if(!reg.test($(".ex_iphone").val()))
        {   
            $(".error1").html("输入手机格式错误!!");
            $(".error1").css("display","block");
			return false;
        }
            $(".error1").css("display","none");
			return true;
    };
	function password(){

        if( $(".ex_pord").val()==""|| $(".ex_pord").val()=="输入密码")
        {
            $(".error2").html("密码输入错误");
            $(".error2").css("display","block");
			return false;
        }
        if($(".ex_pord").val().length>6)
        { 
            $(".error2").html("密码输入错误！");
            $(".error2").css("display","block");
			return false;
        }
            $(".error1").css("display","none");
			return true;
    }
	function get(){
        var reg=/^[A-Z,a-z,0-9]{4}$/;
        if( $(".ex_get").val()=="" || $(".ex_get").val()=="输入验证码")
        {
            $(".error3").html("请填写验证码");
            $(".error3").css("display","block");
			return false;
        }
        if(!reg.test($(".ex_get").val()))
        {
            $(".error3").html("验证码里无中文、特殊符号！");
            $(".error3").css("display","block");
              	return false;
		}
        $(".error3").css("display","none");
      	return true;

    };
/*	$('.img').click(function(){
		$('.img').attr("src",'images/ex_over.png?x='+Math.random())
		
	})*/
});	