<%@page import="com.edaisong.toolscore.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
<div class="row">
		<div class="col-lg-12">
				<div class="row">
							<label class="col-sm-4 control-label" style="color:red">加密时将需加密的字符串串填写至加密串区域,点击加密.<br/>解密时将串填写至解密串区域,点击解密</label>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">加密串:</label>
							<div class="col-sm-8">
							    <textarea rows="5" cols="50" id="enstr"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">解密串:</label>
							<div class="col-sm-8">
							   <textarea rows="5" cols="50" id="destr"></textarea>
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnenstr" style="margin-left:3px;">加密</button>
						</div>
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btndestr" style="margin-left:3px;">解密</button>
						</div>
				</div>
		</div>
	</div>
</div>
<script>
function execAES(str,type){
	var url='<%=basePath%>/admintools/aesdo';
	var par={
			"str":str,
			"type":type,
	};
	$.post(url,par,function(data){
		if(type==1){
			$('#destr').val(data);
		}else{
			$('#enstr').val(data);
		}
	});
}
//加密
$('#btnenstr').click(function(){
	if($('#enstr').val()=='')
		{alert('加密字符串不能为空!');return}
	execAES($('#enstr').val(),1);
});
$('#btndestr').click(function(){
	if($('#destr').val()=='')
	{alert('解密字符串不能为空!');return}
	execAES($('#destr').val(),2);
});
</script>