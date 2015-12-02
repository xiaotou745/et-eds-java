<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="com.edaisong.toolscore.enums.ServerType"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");

List<String> appNameList = (List<String>) request.getAttribute("appNameList");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
							   <%=HtmlHelper.getSelect("selappname", appNameList, "", "",null,null,"全部")%>
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="addversion"
							style="margin-left: 3px;height:30px;">添加版本</button>
					</div>
			</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>
<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-sm">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">用户操作</h4>
	</div>
<div class="modal-body">
所属平台: <input name="rAppSource" id="rold" type="radio" value="0" checked="checked" /> 易代送商户版
        <input name="rAppSource" id="rnew" type="radio" value="1" /> 智能调度版
客户端类型:  <input name="rPlatForm" id="rAndroid" type="radio" value="1" checked="checked" /> Android
          <input name="rPlatForm" id="rIOS" type="radio" value="2" /> IOS
用户类型: <input name="rUserType" id="rClienter" type="radio" value="1" checked="checked" /> 骑士
        <input name="rUserType" id="rBusiness" type="radio" value="2" /> 门店
是否强制更新:  <input name="rIsMust" id="rIsMustN" type="radio" value="0" checked="checked" /> 否
                <input name="rIsMust" id="rIsMustY" type="radio" value="1" /> 是
发布类型:  <input name="rSendType" id="rTiming" type="radio" value="1" checked="checked" /> 定时发布
         <input name="rSendType" id="rRealtime" type="radio" value="0" /> 实时发布
设置发布时间:  <input type="text" name="txtSendTime" id="txtSendTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:{%m+5}:%s'})" class="Wdate" />
版本号:  <input name="version" id="version" type="text" value="" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="5">

下载地址:    <input name="updateUrl" id="updateUrl" type="text" value="">
提示更新内容: <textarea cols="70" rows="5" id="message"></textarea>

<span id="tip" style="color:red"></span>
</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" id="saveversion" class="btn btn-primary">保存</button>
	</div>
    </div>
</div>
<input type="hidden" id="versionid"/>
<input type="hidden" id="optype"/>
</div>
<script>
$(function(){
	  $(' .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });
});
	var jss = {
		search : function(currentPage) {
			if($("#selappname").val()==""){
				return;
			}
			var data={"appName":$("#selappname").val(),"currentPage":currentPage};
			$.post("<%=basePath%>/admintools/listdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#addversion").click(function(){
		reset();
		$("#versionid").val("0");
		$("#optype").val("3");
		$("#myModal").modal('show');
	});
	function cancelPublish(id){
		if(!confirm("确定要取消发布？")){
			return;
		}
		var paramaters = {
				"appName":$("#selappname").val(),
				"id" :  id
			};
		var url = "<%=basePath%>/admintools/cancelversionpublish";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败");
				}
			}
		});
	}
	function getdetail(id){
		var paramaters = {
				"appName":$("#selappname").val(),
				"id" :  id
			};
		var url = "<%=basePath%>/admintools/getversionbyid";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if(result.appsource==0){
					$("#rold").prop('checked',true); 
					$("#rnew").prop('checked',false); 
				}else{
					$("#rnew").prop('checked',true); 
					$("#rold").prop('checked',false); 
				}
				if(result.platform==1){
					$("#rAndroid").prop('checked',true); 
					$("#rIOS").prop('checked',false); 
				}else{
					$("#rIOS").prop('checked',true); 
					$("#rAndroid").prop('checked',false); 
				}
				if(result.usertype==1){
					$("#rClienter").prop('checked',true); 
					$("#rBusiness").prop('checked',false); 
				}else{
					$("#rBusiness").prop('checked',true); 
					$("#rClienter").prop('checked',false); 
				}
				if(result.ismust==1){
					$("#rIsMustY").prop('checked',true); 
					$("#rIsMustN").prop('checked',false); 
				}else{
					$("#rIsMustN").prop('checked',true); 
					$("#rIsMustY").prop('checked',false); 
				}
				if(result.istiming==1){
					$("#rTiming").prop('checked',true); 
					$("#rRealtime").prop('checked',false); 
					$("#txtSendTime").val(result.timingdate);
				}else{
					$("#rRealtime").prop('checked',true); 
					$("#rTiming").prop('checked',false); 
				}

				$("#version").val(result.version);
				$("#updateUrl").val(result.updateurl);
				$("#message").val(result.message);
			}
		});
	}
	function reset(){
		$("#rold").prop('checked',true); 
		$("#rnew").prop('checked',false); 

		$("#rAndroid").prop('checked',true); 
		$("#rIOS").prop('checked',false); 

		$("#rClienter").prop('checked',true); 
		$("#rBusiness").prop('checked',false); 

		$("#rIsMustN").prop('checked',true); 
		$("#rIsMustY").prop('checked',false); 

		$("#rTiming").prop('checked',true); 
		$("#rRealtime").prop('checked',false); 
		
		$("#txtSendTime").val("");
		$("#version").val("");
		$("#updateUrl").val("");
		$("#message").val("");
	}
	function getparam(){
		var paramaters = {
				"appName":$("#selappname").val(),
				"opType":$("#optype").val(),
				"id" :$("#versionid").val(),
				"appsource":$('input[type="radio"][name="rAppSource"]:checked').val(),
				"platform":$('input[type="radio"][name="rPlatForm"]:checked').val(),
				"usertype":$('input[type="radio"][name="rUserType"]:checked').val(),
				"ismust":$('input[type="radio"][name="rIsMust"]:checked').val(),
				"istiming":$('input[type="radio"][name="rSendType"]:checked').val(),
				"timingdate":$("#txtSendTime").val(),
				"version":$("#version").val(),
				"updateurl":$("#updateUrl").val(),
				"message":$("#message").val()
			};
	}
	//type，0是查看，1是修改，2是取消,3是新增
	function ViewDetail(id,type){
		if(type==2){
			cancelPublish(id);
		}else{
			$("#versionid").val(id);
			$("#optype").val(type);
			getdetail(id);
			if(type==0){
				$("input[type='text']").arrr("disable",true);
				$("input[type='radio']").arrr("disable",true);
				$("#saveversion").hide();
			}else{
				$("input[type='text']").arrr("disable",false);
				$("input[type='radio']").arrr("disable",false);
				$("#saveversion").show();
			}
			$("#myModal").modal('show');
		}
	}
	$("#saveversion").click(function(){
		var paramaters=getparam();
		var url = "<%=basePath%>/admintools/saveversion";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败");
				}
			}
		});
	});
</script>