<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="com.edaisong.toolsentity.LineHistory"%>

<%@page import="com.edaisong.toolscore.enums.DevPlatformType"%>
<%@page import="com.edaisong.toolsentity.domain.SelectAppModel"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
	List<SelectAppModel> appNameList = (List<SelectAppModel>) request.getAttribute("appNameList");
%>
<script src="<%=basePath%>/js/util.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal"> 
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
							   <%=HtmlHelper.getSelect("selappname", appNameList, "", "",null,"","全部")%>
							 <%=HtmlHelper.getSelect("selappname", appNameList, "appName", "dbName",null,null,"全部")%>
							</div>
						</div>
					</div>
				<div class="col-lg-3">
					<div class="form-group"> 
						<div class="col-sm-8">
							<input type="button" data-toggle="modal" class="btn btn-primary" id="btnCrtLineHistory" value="新建里程" />
						</div>
					</div>
				</div> 
			</div> 
			<div class="row"> 
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">项目：</label>
						<div class="col-sm-8">
						  <select id="selDevPlatform" class="form-control m-b">
							<option value="">请选择</option>
				          	<option value="glht">管理后台</option>
				          	<option value="eds">E代送</option>
				          	<option value="zndd">智能调度</option>
						</select> 
							<select class="form-control m-b" id="selDevPlatform" name="selappname">
							<option value="-1">请选择</option> 
							</select>




						</div> 
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
					<label class="col-sm-4 control-label">产品：</label>
							<div class="col-sm-8">
							  <select id="selOnlineProduct" class="form-control m-b"> 
							  <option value="">请选择</option>
							  <option value="-1">请选择</option>
							</select> 
					</div>
				</div>
				</div> 
				<div class="col-lg-3">
					<div class="form-group"> 
							<div class="col-sm-8"> 
							<input type="button" class="btn btn-primary" id="btnSelLineHistory" value="查询" />
				</div>
				</div> 
			</div>
				</div>
		    </div>
	</div>  </div> 
<div id="content">
</div> 
<!-- 添加弹窗 -->
<div class="modal inmodal"  id="addLineHistoryModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">添加里程</h4>
			</div> 
			<div class="modal-body" style="overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;">
				<fieldset> 
					<div class="control-group">
						项目：<select id="devPlatform_sel" class="form-control m-b">
						          <option value="" selected="selected">请选择</option>
						          <option value="glht">管理后台</option>
						          <option value="eds">E代送</option>
						          <option value="zndd">智能调度</option>
						      </select> 
						项目：
						<select class="form-control m-b" id="devPlatform_sel" name="selappname">
							<option value="-1">请选择</option> 
						</select>



					</div>
					<div class="control-group"> 
						产品：<select id="onlineProduct_sel" class="form-control m-b">
						<option value="" selected="selected">请选择</option>
						<option value="-1" selected="selected">请选择</option>
						</select>						 
					</div>
					<div class="control-group"> 
						版本号：<input type="text" placeholder="请输入版本号" class="input-sm form-control" id="txtDevVersion" value="" />
					</div>
					<div class="control-group"> 
						上线时间：<input class="form-control" style="width:190px" type="text" name="onlineDate" id="onlineDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"/>
					</div>
					<div class="control-group"> 
						上线内容：<textarea id="txtOnLineContent" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
						上线内容：('@'分隔换行)<textarea maxlength="400" id="txtOnLineContent" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
					<div class="control-group"> 
						备注：<textarea id="txtRemark" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
						备注：<textarea maxlength="400" id="txtRemark" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
				</fieldset> 
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="saveLineHistory">保存</button> 
			</div>
		</div>
	</div>
</div>
<!-- 添加弹窗 结束-->

<!-- 修改弹窗 -->
<div class="modal inmodal fade" id="modifyLineHistoryModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">修改里程</h4>
			</div>
			<div class="modal-body" style="height: 500px; overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;">
				<fieldset> 
					<div class="control-group">
					<input type="hidden" id="modifyId" value="" />
						项目：<input type="text" class="input-sm form-control" id="modifyDevPlatform" disabled="disabled" value="" />
					</div>
					<div class="control-group"> 
						产品：<input type="text" class="input-sm form-control" id="modifyOnlineProduct" disabled="disabled" value="" />
					</div>
					<div class="control-group"> 
						版本号：<input type="text" class="input-sm form-control" id="txtModifyDevVersion" value="" />
					</div>
					<div class="control-group"> 
						上线时间：<input class="form-control" style="width:190px" type="text" name="modifyonlineDate" id="modifyonlineDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"/>
					</div>
					<div class="control-group"> 
						上线内容：<textarea id="txtModifyOnLineContent" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
						上线内容：('@'分隔换行)<textarea maxlength="400" id="txtModifyOnLineContent" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
					<div class="control-group"> 
						备注：<textarea id="txtModifyRemark" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
						备注：<textarea maxlength="400" id="txtModifyRemark" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
				</fieldset> 
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="saveModifyLineHistory">保存</button> 
			</div>
		</div>
	</div>
</div>
<!-- 修改弹窗结束 -->

<!-- 详情弹窗 -->
<div class="modal inmodal fade" id="showLineHistoryModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">里程详情</h4>
			</div>
			<div class="modal-body" style="height: 500px; overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;">
				<fieldset> 
					<div class="control-group">
						项目：<input type="text" class="input-sm form-control" id="showDevPlatform" disabled="disabled" value="" />
					</div>
					<div class="control-group"> 
						产品：<input type="text" class="input-sm form-control" id="showOnlineProduct" disabled="disabled" value="" />
					</div>
					<div class="control-group"> 
						版本号：<input type="text" class="input-sm form-control" id="txtshowDevVersion" value="" disabled="disabled"/>
					</div>
					<div class="control-group"> 
						上线时间：<input class="form-control" style="width:190px" type="text" name="showonlineDate" id="showonlineDate" disabled="disabled"/>
					</div>
					<div class="control-group"> 
						上线内容：<textarea id="txtshowOnLineContent" disabled="disabled" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
						上线内容：<textarea maxlength="400" id="txtshowOnLineContent" disabled="disabled" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
					<div class="control-group"> 
						备注：<textarea id="txtshowRemark" disabled="disabled" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
						备注：<textarea maxlength="400" id="txtshowRemark" disabled="disabled" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
				</fieldset> 
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 详情弹窗 结束-->
<script>
var jss1 = {
//初始化事件.
$(function(){
	//1.解除所有下拉框的改变事件
	$('select').unbind('change');
	//2.数据库切换事件
	$('#selappname').change(function(){
		GetDevPlatform($('#selappname').val());
		$('#selOnlineProduct').html('<option value="-1">请选择</option>');
		$('#onlineProduct_sel').html('<option value="-1">请选择</option>');
	});
	//3.项目切换事件
	$('#selDevPlatform').change(function(){
		GetOnLineProduct($('#selDevPlatform').val());
	});
	//弹窗项目切换事件
	$('#devPlatform_sel').change(function(){
		GetOnLineProductSel($('#devPlatform_sel').val());
	});
	//4.默认掉一下change
	$('#selappname').change();
	jss.search(1);
});
//根据选择的数据名称获取项目(平台)名称
function GetDevPlatform(dbname)
{
	var data={"dbname":dbname}
	$.post("<%=basePath%>/linehistory/getdevplatform",
			data,
			function(d){
			console.log(d);
			$('#selDevPlatform').html(d);
			$('#devPlatform_sel').html(d);
	});
}
//根据数据库名称以及项目ID获取上线产品
function GetOnLineProduct(devplatformId)
{
	var data={"typeid":devplatformId}
	if(devplatformId==-1||devplatformId=='-1')
	{
		$('#selOnlineProduct').html('<option value="-1">请选择</option>');
		return;
	}
	$.post("<%=basePath%>/linehistory/getonlineproduct",
			data,
			function(d){
			$('#selOnlineProduct').html(d);
	});
}
//上线产品(弹窗)
function GetOnLineProductSel(devplatformId)
{
	var data={"typeid":devplatformId}
	$.post("<%=basePath%>/linehistory/getonlineproduct",
			data,
			function(d){
			$('#onlineProduct_sel').html(d);
	});
}
//分页查询事件
var jss = {
		search : function(currentPage) { 
		 var data={
			"currentPage":currentPage,
			"m":Math.random(),
			"devPlatform":$('#selDevPlatform').find("option:selected").text(),
			"onLineProduct":$('#selOnlineProduct').find("option:selected").text(),
			"appName":$("#selappname").val()
			"devPlatform":$('#selDevPlatform').val(),
			"onLineProduct":$('#selOnlineProduct').val(),
			"appName":$("#selappname").find("option:selected").text()
		 };
		 $.post("<%=basePath%>/linehistory/listdo", data,
		 $.post("<%=basePath%>/linehistory/listdo",
				 	data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	$("#btnSelLineHistory").click(function() {
		if($("#selappname").val()==""){
			alert("请选择系统名称");
			return;
		}		
		jss1.search(1);
	});
var jsonEdsDevPlatform=[{'name':'glht','value':"[{'name':'edsglht','value':'E代送管理后台'}]"},{'name':'eds','value':"[{'name':'edssh','value':'E代送商户'},{'name':'edsqs','value':'E代送骑士'},{'name':'edslcjf','value':'E代送（里程计费）'},{'name':'edssjzx','value':'商家中心'}]"},{'name':'zndd','value':"[{'name':'edszndd','value':'E代送智能调度'},{'name':'edsqqs','value':'E代送轻骑士'}]"}];
var jsonRenrenDevPlatform=[{'name': 'rrtglht','value': "[{'name':'edsglht','value':'人人推管理后台'}]"},{'name': 'rrtqs','value': "[{'name':'rrtqsapp','value':'人人推骑士'}]"}];
var myjson=[];
//点击查询事件
$("#btnSelLineHistory").click(function() {
	if($("#selappname").val()==""){
		alert("请选择系统名称");
		return;
	}		
	jss.search(1);
});



$(function(){
	selChange("devPlatform_sel","onlineProduct_sel");
	selChange("selDevPlatform","selOnlineProduct");
});
$("#selappname").change(function(){
	$("#content").html("");
	if($(this).val() !=''){
		$("#btnSelLineHistory").click();
	}
});
function selChange(a,b){
	$("#"+a).change(function(){
		var selval= $(this).val(); 
		if(selval == ""){
			$("#"+b).html("<option value='' selected='selected'>请选择</option>");
			if($("#selappname").val() !=''){
				$("#btnSelLineHistory").click();
			}
			return;
		}		
		$.each(jsonEdsDevPlatform, function (n, ojson) { 
			if(selval == ojson.name){ 
		 		var dataObj=eval("("+ojson.value+")");
		 		var temp_html;
				$.each(dataObj, function (nn, kkjson) {  
					temp_html+="<option value='"+kkjson.name+"'>"+kkjson.value+"</option>";
				});
		 		$("#"+b).html(temp_html);
			}
		});
		if($("#selappname").val() !=''){
			$("#btnSelLineHistory").click();
		}
	});
}




































//显示新建里程弹窗
$("#btnCrtLineHistory").click(function(){
	var syst=$("#selappname").val();
	if(syst == ""){
		alert("请选择系统名称");
		return;
	}
	$("#devPlatform_sel")[0].selectedIndex=0;
	$("#onlineProduct_sel").html("<option value=''>请选择</option>");
	$("#onlineProduct_sel").html("<option value='-1'>请选择</option>");
	$("#addLineHistoryModal").modal("show");
});
function showLineHistory(devPlatform,onlineProduct,devVersion,onlineTime,onlineContent,remark){
	$("#showDevPlatform").val(base64decode(devPlatform));
	$("#showOnlineProduct").val(base64decode(onlineProduct));
	$("#txtshowDevVersion").val(base64decode(devVersion));
	$("#showonlineDate").val(onlineTime);
	$("#txtshowOnLineContent").val(base64decode(onlineContent));
	$("#txtshowRemark").val(base64decode(remark)); 
	$("#showLineHistoryModal").modal("show");
}
//修改
function modifyLineHistory(id,devPlatform,onlineProduct,devVersion,onlineTime,onlineContent,remark) {
	$("#modifyDevPlatform").val(base64decode(devPlatform));
	$("#modifyOnlineProduct").val(base64decode(onlineProduct));
	$("#txtModifyDevVersion").val(base64decode(devVersion));
	$("#modifyonlineDate").val(onlineTime);
	$("#txtModifyOnLineContent").val(base64decode(onlineContent));
	$("#txtModifyRemark").val(base64decode(remark));
	$("#modifyId").val(id);
	$("#saveModifyLineHistory").show();
	$("#modifyLineHistoryModal").modal("show");
}
//修改保存
$("#saveModifyLineHistory").click(function(){ 
    var devVersion = $('#txtModifyDevVersion').val().trim();
    var onlineDate = $('#modifyonlineDate').val();
    if(onlineDate=='')
    {
    	alert('请输入上线时间!');
    	return;
    }
    var onLineContent = $('#txtModifyOnLineContent').val().trim();
    var remark = $('#txtModifyRemark').val().trim(); 
    var modifyId=$('#modifyId').val();
    var paramaters = {
    		"appName":$("#selappname").val(),
    		"appName":$("#selappname").find("option:selected").text(),
            "devVersion":devVersion, 
            "onLineTime":onlineDate,
            "onLineContent":onLineContent,
            "remark":remark,
            "id":modifyId
        };
   var url = "<%=basePath%>/linehistory/modifylinehistory";
   var la= layer.confirm('是否确认修改里程？', {
	    btn: ['确认','取消'], //按钮
	    shade: false //显示遮罩
	},function(){
		layer.close(la);
		$.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {
	        	   $("#saveModifyLineHistory").hide();
	               if (result > 0) {
	            	   alert("修改成功");
	                   window.location.href = "<%=basePath%>/linehistory/list";
	               }
	           }
	    });
	});
});
//删除
function deleteLineHistory(id){
	if (!window.confirm("确认要删除？")) {
        return;
    }
    var paramaters = {
        "id": id
    };
    var url = "<%=basePath%>/linehistory/deletelinehistory";
    $.ajax({
        type: 'POST',
        url: url,
        data: paramaters,
        success: function (result) {
     	   if (result>0) {
					alert("操作成功");
					window.location.href = "<%=basePath%>/linehistory/list";
				} else {
					alert("操作失败");
				}
        }
    });
}
//添加保存
$("#saveLineHistory").click(function(){
	if (!window.confirm("确认要保存？")) {
        return;
    }
	var devPlatformValue= $('#devPlatform_sel').val();
	var devPlatformText=$('#devPlatform_sel').find("option:selected").text();
    var onlineProductValue = $('#onlineProduct_sel').val();
    var onlineProductText=$('#onlineProduct_sel').find("option:selected").text();
    var devVersion = $('#txtDevVersion').val().trim();
    var onlineDate = $('#onlineDate').val();
    if(onlineDate=='')
    {
    	alert('请输入上线时间!');
    	return;
    }
    var onLineContent = $('#txtOnLineContent').val().trim();
    var remark = $('#txtRemark').val().trim(); 
    var paramaters = {
    		"appName":$("#selappname").val(),
    		"appName":$("#selappname").find("option:selected").text(),
            "devPlatform": devPlatformText,
            "devPlatformValue":devPlatformValue,
            "devPlatformCode":devPlatformValue,
            "onLineProduct": onlineProductText,
            "onLineProductValue": onlineProductValue,
            "onLineProductCode": onlineProductValue,
            "devVersion":devVersion, 
            "onLineTime":onlineDate,
            "onLineContent":onLineContent,
            "remark":remark
        };
   var url = "<%=basePath%>/linehistory/addlinehistory";
   var la= layer.confirm('是否确认添加里程？', {
	    btn: ['确认','取消'], //按钮
	    shade: false //显示遮罩
	},function(){
		layer.close(la);
		$.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) { 
	               if (result > 0) {
	            	   alert("添加成功");
	                   window.location.href = "<%=basePath%>/linehistory/list";
	               }
	               else{
	            	   alert("添加失败");
	               }
	           }
	    });
	});
});
</script>