<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
List<String> appNameList = (List<String>)request.getAttribute("appNameList");
%>

<script src="<%=basePath%>/js/util.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("sourceSys", appNameList, "", "",null, null, null)%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">模板名称:</label>
							<div class="col-sm-8">
						<input type="text" placeholder="请输入模板名称"
					class="form-control" id="templetName" name="templetName"  value="" />
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary"
							id="btnSearch" style="margin-left: 3px;">查询</button>
							<button type="button" class="btn btn-w-m btn-primary"
							id="btnAdd" style="margin-left: 3px;">新增模板</button>
						<span id="tip" style="color:red"></span>
					</div>
				</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
</div>

<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-sm">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">新增模板</h4>
	</div>
			<div class="modal-body">
				模板名称:<input type="text" class="form-control" id="addtempletName"
					name="addtempletName" value="" /><br /> 执行时间:<input type="text"
					class="form-control" id="executetime" name="executetime" value="" /><br />
				是否生效:<input type="radio" value="0" name="isenable" id="radyes"
					checked /> <label for="radyes">有效</label> <input type="radio"
					value="1" name="isenable" id="radno" /> <label for="radno">无效</label><br />
				收件人:<input type="text" class="form-control" id="receiveemail"
					name="receiveemail" value="" /> (多个收件人以";"分隔)<br /> sql语句:<textarea
					class="form-control" rows="10"  id="sqltext" name="sqltext"></textarea><br />
					<input type="hidden" id="templetid" value=""/>
			</div>
			<div class="modal-footer">
			 <button id="save" type="button" class="btn btn-primary">保存</button>
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	</div>
    </div>
</div>
</div>
<script>
var jss={
		search:function(currentPage){
				$("#tip").html("正在查询。。。");
				$("#btnSearch").attr("disabled",true);
			    var data={"currentPage":currentPage,
			    		"appName":$("#sourceSys").val(),
			    		"templetName":$("#templetName").val()};
				$.post("<%=basePath%>/admintools/exportquery",data, function(d) {
					$("#tip").html("");
					$("#btnSearch").attr("disabled",false);
					$("#content").html(d);
				});
			}
	};

jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
$("#btnAdd").click(function(){
	$("#radyes").prop("checked", true);
	$("#templetid").val("0");
	$("#addtempletName").val("");
	$("#executetime").val("");
	$("#receiveemail").val("");
	$("#sqltext").val("");
	$('#myModal').modal('show');
});
function modify(id,isenable){
	if(isenable==0){
		$("#radyes").prop("checked", true);
	}else{
		$("#radno").prop("checked", true);
	}
	$("#templetid").val(id);
	$("#addtempletName").val(base64decode($("#name"+id).val()));
	$("#executetime").val(base64decode($("#executetime"+id).val()));
	$("#receiveemail").val(base64decode($("#receiveemail"+id).val()));
	$("#sqltext").val(base64decode($("#sqltext"+id).val()));

	$('#myModal').modal('show');
}
function deleterecord(id){
	if(!confirm("确定要删除？")){
		return;
	}
	var paramaters = {
			"id" :  id,
			"appName":$("#sourceSys").val()
		};
	var url = "<%=basePath%>/admintools/exportdelete";
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
$("#save").click(function(){
	var paramaters = {
			"id" :$("#templetid").val(),
			"isenable":$('input[type="radio"][name="isenable"]:checked').val(),
			"name":$("#addtempletName").val(),
			"executetime":$("#executetime").val(),
			"receiveemail":$("#receiveemail").val(),
			"sqltext":$("#sqltext").val(),
			"appName":$("#sourceSys").val()
		};
	if(paramaters.templetName==""){
		alert("模板名称不能为空");
		return;
	}
	if(paramaters.executetime==""){
		alert("执行时间不能为空");
		return;
	}
	if(paramaters.receiveemail==""){
		alert("收件人不能为空");
		return;
	}
	if(paramaters.sqltext==""){
		alert("sql语句不能为空");
		return;
	}
	var url = "<%=basePath%>/admintools/exportupdate";
	if(paramaters.id=="0"){
		url = "<%=basePath%>/admintools/exportinsert";
	}
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