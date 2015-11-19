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
	            <button type="button" class="btn btn-w-m btn-primary" style="margin-left:3px;" data-toggle="modal" data-target="#myModal" id="addapp">添加APP</button>
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
系统名称：<input type="text" id="appname" class="form-control"/><br/><br/>
服务器类型： <%=HtmlHelper.getSelect("configtype", EnumHelper.GetEnumItems(ServerType.class), "desc", "value",null,null,"") %><br/><br/>
连接串：<input type="text" id="configvalue" class="form-control"/><br/><br/>
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
	}
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
function reset(){
	$("#appname").val("");
	//$("#configtype").val("");
	$("#configvalue").val("");
}
var oldvalue="";
function modifyApp(id,name,configtype,configvalue) {
	oldvalue=name.trim()+configtype.trim()+configvalue.trim();
	$("#appid").val(id);
	$("#appname").val(name);
	$("#configtype").val(configtype);
	$("#configvalue").val(configvalue);
	$("#optype").val(0);//0表示修改，1表示新增
	$('#myModal').modal('show');
	$("#appname").attr("disabled","disabled");
	$("#configtype").attr("disabled","disabled");
}
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

}

$("#saveapp").click(function(){
	if($("#appname").val().trim()==""){
		alert("系统名称不能为空");
		return;
	}
	if($("#configtype").val().trim()==""){
		alert("服务器类型不能为空");
		return;
	}
	if($("#configvalue").val().trim()==""){
		alert("连接串不能为空");
		return;
	}
	if($("#optype").val()=="0"){
		if(oldvalue==$("#appname").val().trim()+$("#configtype").val().trim()+$("#configvalue").val().trim()){
			alert("没有任何修改，不需要保存");
			return;
		}
	}
	
	
	var paramaters = {
			"id":$("#appid").val(),
			"appname" :  $("#appname").val().trim(),
			"configtype" : $("#configtype").val().trim(),
			"configvalue":$("#configvalue").val().trim(),
			"optype":$("#optype").val()
		};
		var url = "<%=basePath%>/admintools/saveapp";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败:已经存在系统名称为"+$("#appname").val().trim()+",服务器类型为"+$("#configtype option:selected").html()+"的配置项");
				}
			}
		});
});
$("#addapp").click(function(){
	reset();
	$("#optype").val(1);
	$("#appname").removeAttr("disabled");
	$("#configtype").removeAttr("disabled");
});
</script>