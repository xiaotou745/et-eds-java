<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
	    <div class="col-lg-12">
	        <div class="input-group" style="margin-bottom:5px;">
	            <input type="text" placeholder="请输入账号名称" class="input-sm form-control" id="txtKeyword" style="width:250px;height:34px;" value="<%=request.getAttribute("cityname")==null?"":request.getAttribute("cityname")%>"/>
	            <button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" style="margin-left:3px;" data-toggle="modal" data-target="#myModal">添加用户</button>
	        </div>
	    </div>
	</div>
</div>
<script src="<%=basePath%>/js/plugins/jeditable/jquery.jeditable.js"></script>
<!-- Data Tables -->
<script src="<%=basePath%>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="<%=basePath%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=basePath%>/js/hplus.js"></script>
<!-- Page-Level Scripts -->

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

  账号名称：<input id="txtUserName"/><br/><br/>
登录名称：<input id="txtLoginName"/><br/><br/>
登录密码：<input type="password" id="txtPwd"/><br/><br/>
确认密码：<input type="password" id="txtConfirmPwd"/><br/><br/>
城市选项：<select id="selCity"><option value="-1"></option></select>

<br/><br/>
物流公司：<select id="selLogistics"><option value="1">全部城市权限</option><option value="2">部分城市权限</option></select><br/><br/>
是否启用：<input type="radio" value="1"/>启用  <input type="radio" value="0"/>不启用
	   
</div>

	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" class="btn btn-primary">保存</button>
	</div>
    </div>
</div>
</div>

<script>


var jss={
		search:function(currentPage){
			var keyword=$("#txtKeyword").val();
			$.post("<%=basePath%>/account/listdo",{CurrentPage:currentPage,Keyword:keyword,m:Math.random()},function(d){
				$("#content").html(d);
			});
		}
	}
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>