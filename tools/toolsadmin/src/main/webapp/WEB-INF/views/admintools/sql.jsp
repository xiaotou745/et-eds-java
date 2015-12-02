<%@page import="com.edaisong.toolscore.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%
List<String> appNameList = (List<String>) request.getAttribute("appNameList");
String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<script src="<%=Config.adminurl%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
<div class="row">
		<div class="col-lg-12">
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
						<div class="form-group">
							<label class="col-sm-4 control-label">SQL:</label>
							<div class="col-sm-8">
							   <textarea rows="5" cols="50" id="sql"></textarea>
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left:3px;">查询</button>
						</div>
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnExec" style="margin-left:3px;">执行</button>
						</div>
				</div>
		</div>
	</div>
</div>
<div id="content" style="overflow:scroll;height:580px;width:1300px;">
</div>
<script>
function execSQL(type){
	var url='<%=basePath%>/admintools/execsql';
	var par={
			"sql":$('#sql').val(),
			"type":type,
			"appName":$('#selappname').val()
	};
	$.post(url,par,function(data){
		$('#content').html(data);
	});
}
$('#btnSearch').click(function(){
	execSQL(1);
});
$('#btnExec').click(function(){
	execSQL(2);
});
</script>