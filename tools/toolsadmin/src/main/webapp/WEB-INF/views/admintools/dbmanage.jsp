<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.Account"%>
<%@page import="com.edaisong.toolsentity.AuthorityRole"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
	    <div class="col-lg-12">
	        <div class="input-group" style="margin-bottom:5px;">
	            <button type="button" class="btn btn-w-m btn-primary" style="margin-left:3px;" data-toggle="modal" data-target="#myModal" id="addapp">添加APP</button>
	        </div>
	    </div>
	</div>
</div>
 
<div id="content">
	
</div>

<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-lg">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">用户操作</h4>
	</div>
<div class="modal-body">
系统名称：<input type="text" id="appname"/><br/><br/>
db连接串：<input type="text" id="dburl"/><br/><br/>
redis连接串：<input type="text" id="redisurl"/><br/><br/>
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
			$.post("<%=basePath%>/admintools/dbmanagedo",{currentPage:currentPage},function(d){
				$("#content").html(d);
			});
		}
	}
	
jss.search(1);
// $("#btnSearch").click(function(){
// 	jss.search(1);
// });
function reset(){
	$("#appname").val("");
	$("#dburl").val("");
	$("#redisurl").val("");
}
var oldvalue="";
function modifyApp(id,name,dburl,redisurl) {
	oldvalue=name+dburl+redisurl;
	$("#appid").val(id);
	$("#appname").val(name);
	$("#dburl").val(dburl);
	$("#redisurl").val(redisurl);
	$("#optype").val(0);//0表示修改，1表示新增
	$('#myModal').modal('show');
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
	if($("#appname").val()==""){
		alert("系统名称不能为空");
		return;
	}
	if($("#dburl").val()==""){
		alert("db连接串不能为空");
		return;
	}
	if($("#redisurl").val()==""){
		alert("redis连接串不能为空");
		return;
	}
	if($("#optype").val()=="0"){
		if(oldvalue==$("#appname").val()+$("#dburl").val()+$("#redisurl").val()){
			alert("没有任何修改，不需要保存");
			return;
		}
	}
	
	
	var paramaters = {
			"id":$("#appid").val(),
			"appname" :  $("#appname").val(),
			"dburl" : $("#dburl").val(),
			"redisurl":$("#redisurl").val(),
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
					alert("操作失败");
				}
			}
		});
});
$("#addapp").click(function(){
	reset();
	$("#optype").val(1);
});
</script>