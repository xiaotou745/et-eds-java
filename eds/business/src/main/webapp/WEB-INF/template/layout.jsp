<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/base.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/commen.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/index.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/newProjects.css">

<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/dataTables.bootstrap.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/popup.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
 <script type="text/javascript" src="<%=basePath%>/js/date/WdatePicker.js"></script> 
<script>
$(document).ready(function() {
	$("#gloablShowError").click(function() {
		if ($("#gloablShowError").html() == "显示详细信息") {
			$("#gloablShowError").html("隐藏详细信息");
			var timeSet=2000;
			if($("#gloablErrorContent").html().length<500){
				timeSet=500;
			}
			$("#gloablErrorContent").slideDown(timeSet);
		} else {
			$("#gloablShowError").html("显示详细信息");
			$("#gloablErrorContent").slideUp(500);
		}
	});
	$("#closebox").click(function() {
		 $('#gloablErrorDiv').hide();
	});
	$("#closebox1").click(function() {
		 $('#gloablErrorDiv').hide();
	});
	//分页跳转按钮事件处理方法
	$(document).on("click", "#pagesearch", function(){
		var page=$("#pagesearchvalue").val();
		var maxpage=$("#pagesearchmax").val();
		var currentpage=$("#pagesearchcurrentpage").val();
		var s = new RegExp("^\\s*(\\d+)\\s*$");
		if(!s.test(page)||parseInt(page) < 1 || parseInt(page) > maxpage){
		  alert("页索引超出范围");
		  $("#pagesearchvalue").val(currentpage);
		  return;
		}
		jss.search(page);
	}); 
	$(document).on("keydown", "#pagesearchvalue", function(e){
	    var key = null;
	    if (e.which) {
	        key = e.which;
	    }
	    else if (e.keyCode) {
	        key = e.keyCode;
	    }
		if ((48<=key&&key<=57)||(96<=key&&key<=105)) {
		    return true;
		}else{
		    return false;
		}
	});
	setTimeout("regTrEvent()",50);  
});

function regTrEvent(){  
	$(".stripe tr").mouseover(function() {
		//鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");
	}).mouseout(function() {
		//给这行添加class值为over，并且当鼠标一出该行时执行函数
		$(this).removeClass("over");
	})
	//移除该行的class

	$(".stripe tr:even").addClass("alt");
	//给class为stripe的表格的偶数行添加class值为alt

	//注：$(':even') 为偶数语法；
	//注：$(':odd')  为奇数语法；
	countH();
	setTimeout("regTrEvent()",50);  
};  

	$(document).ajaxError(function(event, jqXHR, options, errorMsg) {
		//var info=jqXHR.responseText;
		//alert(options.url + "调用出错了！");
		//alert(info);
	var content="内部服务器错误";
   	 //var start=jqXHR.responseText.indexOf("<body>");

// 	 if(start>0){
//     	 var end=jqXHR.responseText.indexOf("</body>");
//     	 content=jqXHR.responseText.substring(start+6,end);
//     	 content=content.replace("h1","h4"); 
// 	 }else{
		 var start2=jqXHR.responseText.indexOf("<pre>");
		 var end2=jqXHR.responseText.indexOf("</pre>");
    	 content=jqXHR.responseText.substring(start2,end2+6);
	 //}
		if(content.indexOf("AjaxNotLoginRunTimeException")>0){
			alert("由于你长时间没操作，请重新登录");  
			window.location.href = "<%=basePath %>";
			return;
		}
	 $("#gloablErrorParam").html(options.url+"调用出错了！");
	 $("#gloablErrorContent").html(content);
	 $("#gloablShowError").html("显示详细信息");
	 $("#gloablErrorContent").hide();
	 $('#gloablErrorDiv').show();
	});
	function countH() {
		var WinHeight = $(window).height();
		$(".nav").css({
			"min-height" : WinHeight - 70
		})
	};
	
	//如果2小时内页面没有操作自动退出 
	var getCoordInDocument = function(e) {
		e = e || window.event;
		var x = e.pageX
				|| (e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
		var y = e.pageY
				|| (e.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
		return {
			'x' : x,
			'y' : y
		};
	};

	var isMove = false;
	var oldX = 0;
	var oldY = 0;
	var flag = false;
	var maxArea = 5;
	document.onmousemove = function(e) {
		var pointer = getCoordInDocument(e);
		if (Math.abs(pointer.x - oldX) > maxArea
				|| Math.abs(pointer.y - oldY) > maxArea) {
			//鼠标已经移动,证明正在操作
			isMove = true;
		} else {
			isMove = false;
		}
		if (isMove) {
			flag = true;
			var t=setTimeout("logoff()",2*60*60*1000);
		}
		oldX = pointer.x;
		oldY = pointer.y;
	};
	
	function logoff(){
		if(flag){
			window.location.href="<%=basePath%>/account/logoff";
			maxArea = 10000;
			flag = false;
		}
	};
</script>
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<div class="content content2  cb">
		<tiles:insertAttribute name="leftmenu"></tiles:insertAttribute>
			<div class="center recharge">
			<tiles:insertAttribute name="breadcrumbs"></tiles:insertAttribute>
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
			<tiles:insertAttribute name="footer" ignore="true"></tiles:insertAttribute>	
		</div>	
	</div>
	<div tabindex="-1" class="modal inmodal" id="gloablErrorDiv" role="dialog" style="display:none"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal" id="closebox1">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">服务器异常</h4>
					</div>
					<small class="font-bold">
						<div class="modal-body">
						<div id="gloablErrorParam"></div>
						<div><a id="gloablShowError"  href="javascript:void(0)">显示详细信息</a></div>
						<pre id="gloablErrorContent" style="width: 560px;display: none; "></pre>
						</div>
						<div class="modal-footer">
							<button class="btn btn-white" type="button" data-dismiss="modal" id="closebox">关闭</button>
						</div>
					</small>
				</div>
				<small class="font-bold"> </small>
			</div>
			<small class="font-bold"> </small>
		</div>
</body>
</html>
