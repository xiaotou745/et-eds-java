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
	            <button type="button" class="btn btn-w-m btn-primary" id="intSave" style="margin-left:3px;">添加用户</button>
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

<script>


var jss={
		search:function(currentPage){
			var keyword=$("#txtKeyword").val();
			$.post("<%=basePath%>/account/listdo",{CurrentPage:currentPage,Keyword:keyword,m:Math.random()},function(d){
				alert(d);
				$("#content").html(d);
			});
		}
	}
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>