
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.FeedbackType"%>
<%@page import="com.edaisong.core.enums.UserType"%>

<%
	String basePath =PropertyUtils.getProperty("static.admin.url");	
 	List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
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
							<label class="col-sm-4 control-label">反馈类型:</label>
							<div class="col-sm-8">						
								<%=HtmlHelper.getSelect("sltFeedbackType", EnumHelper.GetEnumItems(FeedbackType.class), "desc", "value",null,"-1","全部","","form-control m-b") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选城市:</label>
							<div class="col-sm-8">								
								<%=HtmlHelper.getSelect("sltCityId", areaListData, "name", "name",null,"-1","全部","","form-control m-b") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">用户类型:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("sltUserType", EnumHelper.GetEnumItems(UserType.class), "desc", "value",null,"-1","全部","","form-control m-b") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
								
							</div>
						</div>
					</div>
				</div>
				<div class="row">				
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">日期:</label>
							<div class="col-sm-8">		
								<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="txtStatrTime" id="txtStatrTime" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'txtEndTime\')||\'2020-10-01\'}'})"/>
                                    </div>                                   					

							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="txtEndTime" id="txtEndTime" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'txtStatrTime\')}',maxDate:'2020-10-01'})"/>
                                    </div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">							
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
							  
							</div>
						</div>
					</div>
				</div>

			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
					 
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
   

	<script>		
	var jss={
			search:function(currentPage){	
				   var feedbacktype = $("#sltFeedbackType").val();
				   var city=$("#sltCityId").val();
				   var usertype=$("#sltUserType").val();
				   var statrTime=$("#txtStatrTime").val();				   
				   var endTime=$("#txtEndTime").val();				   
				 var paramaters = { 
						 "currentPage":currentPage,
						 "feedbacktype": feedbacktype,
						 "city": city,
						 "usertype": usertype,
						 "statrTime": statrTime,
						 "endTime": endTime,
						 };        
			        var url = "<%=basePath%>/feedback/listdo";
			        $.ajax({
			            type: 'POST',
			            url: url,
			            data: paramaters,
			            success: function (result) {   			            
			            	$("#content").html(result);               
			            }
			        });
			}
		}	
		
	jss.search(1);
	$("#btnSearch").click(function(){
		jss.search(1);
	});		

	</script>		
	
