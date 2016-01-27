<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="java.util.Calendar"%>
<%
Calendar a=Calendar.getInstance();
int month=a.get(Calendar.MONTH)+1;
int dateOfMonth = a.getActualMaximum(Calendar.DATE); 

String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
List<String> appNameList = (List<String>)request.getAttribute("appNameList");
List<String> appVersionList = (List<String>)request.getAttribute("appVersionList");
%>

<script src="<%=basePath%>/js/util.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="" class="form-horizontal"
				id="searchForm" onsubmit="return false;">
				<input type="hidden" name="currentPage" id="_hiddenCurrentPage"
					value="1" />
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("sourceSys", appNameList, "", "",null, null, null)%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">版本:</label>
							<div class="col-sm-8">
								<select name="appversion" class="form-control m-b" id="appversion">
								<option value='' selected='selected'>全部版本</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">年份:</label>
							<div class="col-sm-8">
							<select name="yearInfo" class="form-control m-b" id="yearInfo">
										<%
										Calendar cal = Calendar.getInstance();
										int nowYear = cal.get(Calendar.YEAR);
										for (int i = nowYear; i >=2015; i--) {
											%>
											<option value="<%=i %>" <%=i==nowYear?"selected='selected'":"" %>><%=i %></option>
											<%
										}
										%>
								</select>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">月份:</label>
							<div class="col-sm-8">
								<select name="monthInfo" class="form-control m-b" id="monthInfo">
									<option value="01" <%=month==1?"selected='selected'":"" %>>1</option>
									<option value="02" <%=month==2?"selected='selected'":"" %>>2</option>
									<option value="03" <%=month==3?"selected='selected'":"" %>>3</option>
									<option value="04" <%=month==4?"selected='selected'":"" %>>4</option>
									<option value="05" <%=month==5?"selected='selected'":"" %>>5</option>
									<option value="06" <%=month==6?"selected='selected'":"" %>>6</option>
									<option value="07" <%=month==7?"selected='selected'":"" %>>7</option>
									<option value="08" <%=month==8?"selected='selected'":"" %>>8</option>
									<option value="09" <%=month==9?"selected='selected'":"" %>>9</option>
									<option value="10" <%=month==10?"selected='selected'":"" %>>10</option>
									<option value="11" <%=month==11?"selected='selected'":"" %>>11</option>
									<option value="12" <%=month==12?"selected='selected'":"" %>>12</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
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
							<label class="col-sm-4 control-label">排序字段:</label>
							<div class="col-sm-8">
								<input type="radio" checked="checked" 
									value="requestTime" name="orderBy" id="requestTime" />请求时间 <input
									type="radio" value="executeTime" name="orderBy" id="executeTime" />执行时间
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">排序方式:</label>
							<div class="col-sm-8">
								<input type="radio"
									value="1" name="orderType" id="ASC" />升序 <input checked="checked" 
									type="radio" value="-1" name="orderType" id="DESC" />降序
							</div>
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">请求地址:</label>
							<div class="col-sm-8">
								<input type="text" placeholder="请输入请求地址" style="width:400px;"
					class="form-control" id="requestUrl" name="requestUrl"  value="" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">开始日期:</label>
							<div class="col-sm-8">
							<select name="beginDay" class="form-control m-b" id="beginDay">
							 <option value='' selected='selected'></option>
										<%
										for (int i = 1; i<=dateOfMonth; i++) {
											%>
											<option value="<%=i<10?("0"+i):i %>"><%=i %></option>
											<%
										}
										%>
								</select>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">结束日期:</label>
							<div class="col-sm-8">
							<select name="endDay" class="form-control m-b" id="endDay">
							 <option value='' selected='selected'></option>
										<%
										for (int i = 1; i<=dateOfMonth; i++) {
											%>
											<option value="<%=i<10?("0"+i):i %>"><%=i %></option>
											<%
										}
										%>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary"
							id="btnSearch" style="margin-left: 3px;">查询</button>
						<span id="tip" style="color:red"></span>
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

<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-lg">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">日志详情</h4>
	</div>
<div class="modal-body">
入参:<span id="param" class="breakline"></span><br/>
解密后:<span id="decryptMsg" class="breakline"></span><br/>
请求头:<span id="header" class="breakline"></span><br/>
返回值:<span id="resultJson" class="breakline"></span><br/>
异常信息:<span id="exception" class="breakline"></span><br/>
堆栈:<span id="stackTrace" class="breakline"></span><br/>
</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	</div>
    </div>
</div>
</div>
<input type="hidden" value="" id="appversions"/>
<script>
$(function(){
	$("#appversions").val("<%=String.join(";",appVersionList)%>");
	//列表页radio改变时，自动查询
	$("input[type='radio']").on("change",function(e){
		jss.search(1);
	});
});
var jss={
		search:function(currentPage){
			    $("#_hiddenCurrentPage").val(currentPage);
				$("#tip").html("正在查询。。。");
				$("#btnSearch").attr("disabled",true);
			    var data=$("#searchForm").serialize();
				$.post("<%=basePath%>/admintools/logdo",data, function(d) {
					$("#tip").html("");
					$("#btnSearch").attr("disabled",false);
					$("#content").html(d);
				});
			}
	};
	function chanageversion(){
		$("#appversion").html("<option value='' selected='selected'>全部版本</option>");
		if($("#sourceSys").val().indexOf("http")>0||$("#sourceSys").val().indexOf("api")>0){
			var versions=$("#appversions").val();
			if(versions!=""){
				var result=versions.split(";");
				for(j=0;j<result.length;j++){  
					var versionresult=result[j].split(":");
					if(versionresult[0]==$("#sourceSys").val()){
						if(versionresult[1]!=""){
							var version=versionresult[1].split(",");
							for(k=0;k<version.length;k++){ 
								$("#appversion").append("<option value='"+version[k]+"'>"+version[k]+"</option>");   
							}
							break;
						}
					}
				}
			}
		}	
	}
	function changeBeginDay(){
	    $("#beginDay").html("<option value=''></option>");
	    $("#endDay").html("<option value=''></option>");
		var year=$("#yearInfo").val();
		var month=$("#monthInfo").val();
		if(month.indexOf("0")==0){
			month=month.substr(1);
		}
	    var d = new Date(year,month,0);
	    var temp="";
	    var sel="selected='selected'"
		for(k=1;k<=d.getDate();k++){ 
			if(k<10){
				temp="0"+k;
			}else{
				temp=k;
			}
			$("#beginDay").append("<option value='"+temp+"'>"+k+"</option>"); 
			$("#endDay").append("<option value='"+temp+"'>"+k+"</option>");   
		}
	}
	function changeEndDay(id){
		if(id=="beginDay"){
			if($("#endDay").val()!=""&&$("#endDay").val()<$("#beginDay").val()){
				$("#endDay").val($("#beginDay").val());
			}
		}else{
			if($("#beginDay").val()!=""&&$("#beginDay").val()>$("#endDay").val()){
				$("#beginDay").val($("#endDay").val());
			}
		}
	}
function beforeselectchange(e){
	if(e.target.id=="sourceSys"){
		chanageversion();
	}else if(e.target.id=="yearInfo"||e.target.id=="monthInfo"){
		changeBeginDay();
	}else if(e.target.id=="beginDay"||e.target.id=="endDay"){
		changeEndDay(e.target.id);
	}
}	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});

function showdetail(id){
	$("#param").html(base64decode($("#param"+id).val()));
	$("#decryptMsg").html(base64decode($("#decryptMsg"+id).val()));
	$("#exception").html(base64decode($("#exception"+id).val()));
	$("#stackTrace").html(base64decode($("#stackTrace"+id).val()));
	$("#header").html(base64decode($("#header"+id).val()));
	$("#resultJson").html(base64decode($("#resultJson"+id).val()));
	$('#myModal').modal('show');
}
</script>