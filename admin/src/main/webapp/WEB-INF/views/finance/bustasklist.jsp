
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.domain.AreaModel"%>
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
							<label class="col-sm-4 control-label">开始日期:</label>
							<div class="col-sm-8">		
								<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="startDate" id="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
                                    </div>                                   					

							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">:</label>
							<div class="col-sm-8">		
								<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input class="form-control" type="text" value="结束日期" name="endDate" id="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
                                    </div>                                   					

							</div>
						</div>
					</div>
					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">用户类型:</label>
							<div class="col-sm-8">
								<select id=selecttype class="form-control m-b">
									<option value='1'>门店名称</option>
									<option value='2'>注册电话</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">门店名称/注册电话:</label>
							<div class="col-sm-8">
									<input  class="form-control" type="text" name="selectvalue" id="selectvalue">
							</div>
						</div>
					</div>
				</div>
<div class="row">				
						<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选城市:</label>
							<div class="col-sm-8">								
								<%=HtmlHelper.getSelect("businessCityId", areaListData, "name", "name","-1","-1","全部城市","","form-control m-b") %>
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
<script type="text/javascript">
var jss={
		search:function(currentPage){
			 var data={
					 	currentPage:currentPage,
					 	startDate:$('#startDate').val(),
					 	endDate:$('#endDate').val(),
					 	selectType:$('#selecttype').val(),
					 	selectValue:$('#selectvalue').val(),
					 	cityName:$('#businessCityId').val()
					 };
			 console.log(data);
			$.post("<%=basePath%>/finance/bustasklistdo",
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