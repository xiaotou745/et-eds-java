<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="com.edaisong.toolscore.enums.ServerType"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");

List<String> appNameList = (List<String>) request.getAttribute("appNameList");
%>
<script src="<%=basePath%>/js/util.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
							   <%=HtmlHelper.getSelect("selappname", appNameList, "", "",null,"","全部")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">服务器类型:</label>
							<div class="col-sm-8">
							   <%=HtmlHelper.getSelect("selconfigtype", EnumHelper.GetEnumItems(ServerType.class), "desc", "value",null,"-1","全部") %>
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" style="margin-left:3px;" data-toggle="modal" data-target="#myModal" id="addapp">添加配置项</button>
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
系统名称:<input type="text" id="appname" class="form-control"/> 
服务器类型: <%=HtmlHelper.getSelect("configtype", EnumHelper.GetEnumItems(ServerType.class), "desc", "value",null,null,"") %>
机器ip(或域名):<input type="text" id="host" class="form-control"/>
端口号:<input type="text" id="port" class="form-control"/>
<div id="dbdiv">数据库名称:<input type="text" id="dataBase" class="form-control"/></div>
用户名:<input type="text" id="userName" class="form-control"/>
密码:<input type="text" id="passWord" class="form-control"/>
</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" id="saveapp" class="btn btn-primary">保存</button>
	</div>
    </div>
</div>
</div>
<input type="hidden" id="appid"/>
<input type="hidden" id="optype"/>
<script>


var jss={
		search:function(currentPage){
			var param={"currentPage":currentPage,
					"appname":$("#selappname").val(),
					"configtype":$("#selconfigtype").val()
					};
			$.post("<%=basePath%>/admintools/serverconfigdo",param,function(d){
				$("#content").html(d);
			});
		}
	};
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
$("#configtype").change(function(){
	$("#dataBase").val("");
	if(isNeedDataBase()){
		$("#dbdiv").show();
	}else{
		$("#dbdiv").hide();
	}
});
function isNeedDataBase(){
	var selType=$("#configtype").val();
	if(selType==0||selType==1||selType==6){//sql server,mysql,mongo
		return true;
	}else{
		return false;
	}
};
function reset(){
	$("#appname").val("");
	$("#host").val("");
	$("#port").val("");
	$("#dataBase").val("");
	$("#userName").val("");
	$("#passWord").val("");
};
var oldvalue=null;
function modifyApp(id,name,configtype,configvalue) {
	$("#appid").val(id);
	$("#appname").val(name);
	$("#configtype").val(configtype);
	var realValue=base64decode(configvalue);
	var obj = eval('('+realValue+')');
	$("#host").val(obj.host);
	$("#port").val(obj.port);
	if(isNeedDataBase()){
		$("#dataBase").val(obj.dataBase);
		$("#dbdiv").show();
	}else{
		$("#dataBase").val("");
		$("#dbdiv").hide();
	}
	
	$("#userName").val(obj.userName);
	$("#passWord").val(obj.passWord);
	
	$("#optype").val(0);//0表示修改，1表示新增
	
	oldvalue=getValue();
	$('#myModal').modal('show');
	$("#appname").attr("disabled","disabled");
	$("#configtype").attr("disabled","disabled");
};
function deleteApp(id) {
	if(!confirm("确定要删除？")){
		return;
	}
	var paramaters = {
			"id" :  id
		};
	var url = "<%=basePath%>/admintools/deleteApp";
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
};
function getValue(){
	var dbName="";
	if(isNeedDataBase()){
		dbName=$("#dataBase").val().trim();
	}
	var paramaters = {
			"id":$("#appid").val(),
			"appname" :  $("#appname").val().trim(),
			"configtype" : $("#configtype").val().trim(),
			"host" : $("#host").val().trim(),
			"port" : $("#port").val().trim(),
			"dataBase" : dbName,
			"userName" : $("#userName").val().trim(),
			"passWord" : $("#passWord").val().trim(),
			"optype":$("#optype").val()
		};
	return paramaters;
};
$("#saveapp").click(function(){
	var paramaters=getValue();
	if(paramaters.appname==""){
		alert("系统名称不能为空");
		return;
	}
	if(paramaters.configtype==""){
		alert("服务器类型不能为空");
		return;
	}
	if(paramaters.host==""){
		alert("机器ip(或域名)不能为空");
		return;
	}
	if(paramaters.port==""){
		alert("端口号不能为空");
		return;
	}
	//只有数据库和mongo才需要填写数据库名称
	if(isNeedDataBase() && paramaters.dataBase==""){
		alert("数据库名称不能为空");
		return;
	}
	if(paramaters.configtype==0){
		if(paramaters.userName==""){
			alert("用户名不能为空");
			return;
		}
		if(paramaters.passWord==""){
			alert("密码不能为空");
			return;
		}
	}
	
	if(paramaters.optype=="0"){
		//修改时，需要判断是否真正的修改了数据
		if(paramaters.host==oldvalue.host&&
			paramaters.port==oldvalue.port&&
			paramaters.dataBase==oldvalue.dataBase&&
			paramaters.userName==oldvalue.userName&&
			paramaters.passWord==oldvalue.passWord){
			alert("没有任何修改，不需要保存");
			return;
		}
	}
	
		var url = "<%=basePath%>/admintools/saveapp";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else if(result==0){
					alert("操作失败:已经存在系统名称为"+paramaters.appname+",服务器类型为"+$("#configtype option:selected").html()+"的配置项");
				}else{
					alert("配置有误，服务器无法连接，请重试");
				}
			}
		});
});
$("#addapp").click(function(){
	reset();
	$("#optype").val("1");
	$("#configtype").val("0");
	$("#appname").removeAttr("disabled");
	$("#configtype").removeAttr("disabled");
});
</script>