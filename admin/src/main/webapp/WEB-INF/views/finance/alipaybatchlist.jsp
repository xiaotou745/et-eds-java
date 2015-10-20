
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.AlipayBatchStatus"%>
<%@page import="java.util.ArrayList"%>
<%	
String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dAeaG6HwIFGlkbqtyKkyFGEC"></script>
<style type="text/css">
#map_contain {
    height: 90%;
    width: 100%;
    max-width: none;
}
label {
    max-width: none;
}

#control {
width: 100%;
}
</style>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">批次号:</label>
							<div class="col-sm-8">
									<input   class="form-control" type="text" name="batchNo" id="batchNo">
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">打款状态:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("status", EnumHelper.GetEnumItems(AlipayBatchStatus.class), "desc", "value",null,"-1","全部") %>
							</div>
						</div>
					</div> 
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">打款日期:</label>
							<div class="col-sm-8">		
								<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input style="width:120px" placeholder="开始日期" type="text" class="form-control" value="" name="lastOptTimeStart" 
                                        id="lastOptTimeStart" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lastOptTimeStart\')||\'2020-10-01\'}'})"/>
                                    </div>                                   					
							</div>
						</div>
					</div>					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到</label>
							<div class="col-sm-8">		
								<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input style="width:120px" placeholder="结束日期" type="text" class="form-control" value="" 
                                        name="lastOptTimeEnd" id="lastOptTimeEnd" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lastOptTimeEnd\')||\'2020-10-01\'}'})"/>
                                    </div>                                   					
							</div>
						</div>
					</div>					
				</div>	
				<div class="row">
					<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 3px;height:30px;">查询</button>
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
<script type="text/javascript">
var jss={
		search:function(currentPage){
			 var data={
					  	currentPage:currentPage,
					  	batchNo:$('#batchNo').val(),
					 	status:$('#status').val(),
					 	lastOptTimeStart:$('#lastOptTimeStart').val(),
					 	lastOptTimeEnd:$('#lastOptTimeEnd').val() 
					 };
			$.post("<%=basePath%>/finance/alipaybatchlistdo",
					data,
					function(d){
				$("#content").html(d);
			});
		}
	}
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>