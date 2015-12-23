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
Calendar a=Calendar.getInstance();
int month=a.get(Calendar.MONTH)+1;

%>

<script src="<%=basePath%>/js/util.js"></script>
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
				</div>
				<div class="row">
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

<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-lg">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">日志详情</h4>
	</div>
<div class="modal-body">
入参:<span id="param"></span><br/>
解密后:<span id="decryptMsg"></span><br/>
请求头:<span id="header"></span><br/>
返回值:<span id="resultJson"></span><br/>
异常信息:<span id="exception"></span><br/>
堆栈:<span id="stackTrace"></span><br/>
</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	</div>
    </div>
</div>
</div>
<script>

var jss={
		search:function(currentPage){
			    $("#_hiddenCurrentPage").val(currentPage);
			    var data=$("#searchForm").serialize();
				$.post("<%=basePath%>/admintools/logdo",data, function(d) {
					$("#content").html(d);
				});
			}
	};
	
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