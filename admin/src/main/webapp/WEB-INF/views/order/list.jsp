<%@page import="java.sql.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
	    <div class="col-lg-12">
	        <div class="input-group" style="margin-bottom:5px;">
	            <input type="text" placeholder="请输入城市" class="input-sm form-control" id="InputCity" style="width:250px;height:34px;" value="<%=request.getAttribute("cityname")==null?"":request.getAttribute("cityname")%>"/>
	            <button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" id="btnSave" style="margin-left:3px;">保存修改</button>
	        </div>
	    </div>
	   
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content" id="content">
			
				</div>
			</div>
		</div>
	</div>
</div>

<script>
var jss={
		search:function(currentPage){
			$.post("<%=basePath%>/order/listdo",{CurrentPage:currentPage,m:Math.random()},function(d){
				$("#content").html(d);
			});
		}
	}
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>