
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%	
String basePath =PropertyUtils.getProperty("java.admin.url");
List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
%>
<!-- 下拉框的样式以及JS -->
<link href="<%=basePath%>/css/plugins/chosen/chosen.css"  rel="stylesheet">
<script src="<%=basePath%>/js/plugins/chosen/chosen.jquery.js" ></script>
<!-- 下拉框的样式以及JS -->
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
							<label class="col-sm-4 control-label">用户类型:</label>
							<div class="col-sm-8">
								<select id=selecttype class="form-control m-b">
									<option value='1'>门店名称</option>
									<option value='2'>注册电话</option>
								</select>
							</div>
						</div>
					</div> 
					<div class="col-lg-2">
						<div class="form-group">
							<div class="col-sm-8">
									<input style="width:160px" placeholder="门店名称/注册电话" class="form-control" type="text" name="selectvalue" id="selectvalue">
							</div>
						</div>
					</div>
					<div class="col-lg-2">
						<div class="form-group">
							<div class="col-sm-8">		
								<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input style="width:120px" placeholder="开始日期" type="text" class="form-control" value="<%=ParseHelper.ToDateString(ParseHelper.plusDate(new Date(),2,-1),"yyyy-MM-dd") %>" name="startDate" id="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
                                    </div>                                   					

							</div>
						</div>
					</div>
					<div class="col-lg-2">
						<div class="form-group">
							
							<div class="col-sm-8">		
								<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input style="width:120px" class="form-control" type="text" placeholder="结束日期" value="<%=ParseHelper.ToDateString(new Date(),"yyyy-MM-dd") %>" name="endDate" id="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
                                    </div>                                   					

							</div>
						</div>
					</div> 
					
					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选城市:</label>
							<div class="col-sm-8">								
								<%=HtmlHelper.getSelectAuto("businessCityId", areaListData, "name", "name","-1","-1","全部城市") %>
							</div>
						</div>
					</div>	
				</div>	
				<div class="row">
					
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