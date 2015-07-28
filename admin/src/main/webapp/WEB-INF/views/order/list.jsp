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
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />

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
				<div class="ibox-content">
			
				</div>
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
