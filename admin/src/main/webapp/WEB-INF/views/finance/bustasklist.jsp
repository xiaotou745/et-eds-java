
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
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-sm-1">
								<input class="form-control" type="text" value="开始日期" name="startDate" id="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
							</div>
							<div class="col-sm-1">
								<input class="form-control" type="text" value="结束日期" name="endDate" id="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
							</div>
							<div class="col-sm-1">
								<select id=selecttype class="form-control m-b">
									<option value='1'>门店名称</option>
									<option value='2'>注册电话</option>
								</select>
							</div>
							<div class="col-sm-1">
								<input value='门店名称/注册电话' class="form-control" type="text" name="selectvalue" id="selectvalue">
							</div>
							<div class="col-sm-1">
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