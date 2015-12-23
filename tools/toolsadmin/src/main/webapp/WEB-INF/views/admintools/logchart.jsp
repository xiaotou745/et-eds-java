<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="java.util.Calendar"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");

List<String> appNameList = new ArrayList<String>();
appNameList.add("admin");
appNameList.add("apihttp");
appNameList.add("business");
appNameList.add("taobaoopenapi");
appNameList.add("renrenadmin");
appNameList.add("renrenapihttp");

%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="currentPage" id="_hiddenCurrentPage"
					value="1" />
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("sourceSys", appNameList, "", "",
					null, null, null)%>
							</div>
						</div>
					</div>

					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">请求类型:</label>
							<div class="col-sm-8">
								<input type="radio" value="-1" name="requestType"
									checked="checked" id="allreq" />全部 <input type="radio"
									value="0" name="requestType" id="pagereq" />页面 <input
									type="radio" value="1" name="requestType" id="ajaxreq" />ajax
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">异常:</label>
							<div class="col-sm-8">
								<input type="radio" value="-1" name="exceptionShowType" checked="checked"
									id="alltype" />查看全部 <input type="radio" value="1" name="exceptionShowType"
									id="normalreq" />正常 <input type="radio" value="2"
									name="exceptionShowType" id="seljz" />异常
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">步长(分钟):</label>
							<div class="col-sm-8">
								<input type="text" placeholder="步长" style="width:100px;"
					class="form-control" id="minStep" name="minStep"  value="5" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">请求时间:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                              <input type="text" name="begin" id="begin"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'end\')||\'2020-10-01\'}'})"
									class="form-control" />   </div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
								<input type="text" name="end" id="end"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'begin\')}',maxDate:'2020-10-01'})"
									class="form-control" />
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">请求地址:</label>
							<div class="col-sm-8">
								<input type="text" placeholder="请输入请求地址" style="width:400px;"
					class="form-control" id="requestUrl" name="requestUrl"  value="" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary"
							id="btnSearch" style="margin-left: 3px;">查询</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
</div>


<script>

$("#btnSearch").click(function(){
	if($("#begin").val()==""){
		alert("开始日期不能为空");
		return;
	}
	if($("#end").val()==""){
		alert("结束日期不能为空");
		return;
	}
	if($("#minStep").val()==""){
		alert("步长不能为空");
		return;
	}
	var paramaters=$("#searchForm").serialize();
	var url = "<%=basePath%>/admintools/requestnum";
	$.ajax({
		type : 'POST',
		url : url,
		data : paramaters,
		success : function(result) {
			alert(result);
		}
	});
});

</script>