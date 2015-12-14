<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.QuartzServiceModel"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>
<script src="<%=basePath%>/js/util.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
		<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">服务状态:</label>
							<div class="col-sm-8">
								<select name="status" class="form-control m-b" id="status">
									<option value="-1">全部</option>
									<option value="0">停止</option>
									<option value="1">启动</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
							style="margin-left: 3px;height:30px;">查询</button>
					   <button type="button" class="btn btn-w-m btn-primary" id="add"
							style="margin-left: 3px;height:30px;">新增服务</button>
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
<div tabindex="-1" class="modal inmodal" id="servicedetail"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增或修改服务</h4>
				<!-- <small class="font-bold">这里可以显示副标题。 </small> -->
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
			            <br>
			            <div class="control-group">
			                <label>服务名称：</label>
			                <input  id="serviceName" class="form-control"  type="text">
			            </div>
			            <div class="control-group">
			                <label>bean名称(实现类的名称)：</label>
			                <input  id="beanName" class="form-control"  type="text">
			            </div>
			            <div class="control-group">
			                <label>cron表达式：</label>
			                <input  id="cron" class="form-control"  type="text">
			            </div>
			            <div class="control-group">
			                <label>服务说明：</label>
			  				 <input  id="remark" class="form-control"  type="text">
			            </div>
        			</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="save">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<input type="hidden" id="appid"/>
<input type="hidden" id="optype"/>
<script>		
	var jss={
			search:function(currentPage){	
                $("#_hiddenCurrentPage").val(currentPage);
		 		var data=$("#searchForm").serialize();
				$.post("<%=basePath%>/service/listdo",data, function(d) {
					$("#content").html(d);
				});

			}
	};

	jss.search(1);
	$("#btnSearch").click(function(){
		jss.search(1);
	});
	function updateStatus(id,status){
		if(!window.confirm("是否确认更改?")) return;
		var realstatus= status==0?1:0;//这里取反，启动就禁用，禁用就更新启动
		
		$.ajax({
			type : 'get',
			url : "<%=basePath%>/service/updatestatus",
			data : {"id":id,"status":realstatus},
			success : function(d) {
				if(d==1){
		            window.location.href = "<%=basePath%>/service/list";   
				}else if(d==-1){
					alert("beanName有误，无法启动服务");
				}	
			}
		});
	}
	$("#add").click(function(){
		$("#optype").val("1");//0表示修改，1表示新增
		$("#appid").val("0");
		
		$("#beanName").val("");
		$("#serviceName").val("");
		$("#cron").val("");
		$("#remark").val("");
		$('#servicedetail').modal('show');
	});
	var oldvalue;
	function modify(id,name,beanName,execTime,remark) {
		$("#optype").val(0);//0表示修改，1表示新增
		$("#appid").val(id);
		
		$("#beanName").val(beanName);
		$("#serviceName").val(base64decode(name));
		$("#cron").val(base64decode(execTime));
		$("#remark").val(base64decode(remark));
		$("#beanName").attr("disabled","disabled");
// 		$("#serviceName").attr("disabled","disabled");
		oldvalue=getValue();
		$('#servicedetail').modal('show');
	};
	function getValue(){
		var paramaters = {
				"id":$("#appid").val(),
				"beanName" :  $("#beanName").val().trim(),
				"name" : $("#serviceName").val().trim(),
				"execTime" : $("#cron").val().trim(),
				"remark" : $("#remark").val().trim(),
				"optype":$("#optype").val()
			};
		return paramaters;
	}
	$("#save").click(function(){
		var paramaters=getValue();
		if(paramaters.name==""){
			alert("服务名称不能为空");
			return;
		}
		if(paramaters.beanName==""){
			alert("bean名称不能为空");
			return;
		}
		if(paramaters.execTime==""){
			alert("cron表达式不能为空");
			return;
		}
		if(paramaters.remark==""){
			alert("服务说明不能为空");
			return;
		}

		
		if(paramaters.optype=="0"){
			//修改时，需要判断是否真正的修改了数据
			if(paramaters.name==oldvalue.name&&
				paramaters.beanName==oldvalue.beanName&&
				paramaters.execTime==oldvalue.execTime&&
				paramaters.remark==oldvalue.remark){
				alert("没有任何修改，不需要保存");
				return;
			}
		}
		checkCron(paramaters);
		
	});
	function saveInfo(paramaters){
		var url = "<%=basePath%>/service/save";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				$("#tip").html("");
				if (result>0) {
					alert("操作成功");
				}else{
					if(paramaters.optype=="0"){
					   alert("操作失败:当前服务处于启动状态或修改后的服务名称或bean名称与现有的服务相同，不能修改");
					}else{
						 alert("操作失败:新增的服务名称或bean名称与现有的服务相同");
					}
				}
				window.location.href = window.location.href;
			}
		});
	}
	function checkCron(paramaters){
		var url = "<%=basePath%>/service/checkcron";
		$.ajax({
			type : 'POST',
			url : url,
			data : {"cron":paramaters.execTime},
			success : function(result) {
				$("#tip").html("");
				if (result>0) {
					saveInfo(paramaters);
				} else{
					alert("cron表达式校验失败，请检查");
				}
			}
		});
		
	}
</script>