<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolsentity.LineHistory"%>

<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
	List<String> appNameList = (List<String>) request.getAttribute("appNameList");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal"> 
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
							   <%=HtmlHelper.getSelect("selappname", appNameList, "", "",null,"","全部")%>
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
							<option value="请选择">请选择</option>
				          	<option value="glht">管理后台</option>
				          	<option value="eds">E代送</option>
				          	<option value="zndd">智能调度</option>
						</select> 
						</div> 
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
					<label class="col-sm-4 control-label">产品：</label>
							<div class="col-sm-8">
							  <select id="selOnlineProduct" class="form-control m-b"> 
							  <option value="请选择">请选择</option>
							</select> 
					</div>
				</div>
				</div> 
				<div class="col-lg-3">
					<div class="form-group"> 
							<div class="col-sm-8"> 
							<input type="button"  class="btn btn-primary" id="btnSelLineHistory" value="查询" />
				</div>
				</div> 
			</div>
				</div>
		    </div>
	</div>  </div> 
<div id="content">
	
</div> 
<div class="modal inmodal"  id="addLineHistoryModal" tabindex="-1" role="dialog"
	aria-hidden="true">
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
						          <option value="请选择" selected="selected">请选择</option>
						          <option value="glht">管理后台</option>
						          <option value="eds">E代送</option>
						          <option value="zndd">智能调度</option>
						      </select> 
					</div>
					<div class="control-group"> 
						产品：<select id="onlineProduct_sel" class="form-control m-b">
						<option value="请选择" selected="selected">请选择</option>
						</select>						 
					</div>
					<div class="control-group"> 
						版本号：<input type="text" placeholder="请输入版本号" class="input-sm form-control" id="txtDevVersion" value="" />
					</div>
					<div class="control-group"> 
						上线时间：<input class="form-control" style="width:190px" type="text" name="onlineDate" id="onlineDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00'})"/>
					</div>
					<div class="control-group"> 
						上线内容：<textarea id="txtOnLineContent" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
					<div class="control-group"> 
						备注：<textarea id="txtRemark" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
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
						项目：<input type="text" class="input-sm form-control" id="modifyDevPlatform" readonly="readonly" value="" />
					</div>
					<div class="control-group"> 
						产品：<input type="text" class="input-sm form-control" id="modifyOnlineProduct" readonly="readonly" value="" />
					</div>
					<div class="control-group"> 
						版本号：<input type="text" class="input-sm form-control" id="txtModifyDevVersion" value="" />
					</div>
					<div class="control-group"> 
						上线时间：<input class="form-control" style="width:190px" type="text" name="modifyonlineDate" id="onlineDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00'})"/>
					</div>
					<div class="control-group"> 
						上线内容：<textarea id="txtModifyOnLineContent" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
					</div>
					<div class="control-group"> 
						备注：<textarea id="txtModifyRemark" style="width:220px;height:120px;max-width:220px;max-height:120px;"></textarea>
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
<script>
var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data={
			 
		 };
		 $.post("<%=basePath%>/linehistory/listdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	$("#btnSearch").click(function() {
		if($("#selappname").val()==""){
			alert("请选择系统名称");
			return;
		}		
		jss.search(1);
	});
var jsonDevPlatform=[{'name':'glht','value':"[{'name':'edsglht','value':'E代送管理后台'}]"},{'name':'eds','value':"[{'name':'edssh','value':'E代送商户'},{'name':'edsqs','value':'E代送骑士'},{'name':'edslcjf','value':'E代送（里程计费）'},{'name':'edssjzx','value':'商家中心'}]"},{'name':'zndd','value':"[{'name':'edszndd','value':'E代送智能调度'},{'name':'edsqqs','value':'E代送轻骑士'}]"}];
 
$("#devPlatform_sel").change(function(){
	var selval= $(this).val(); 
	if(selval == "请选择"){
		$("#onlineProduct_sel").html("<option value='请选择' selected='selected'>请选择</option>");
		return;
	}
	$.each(jsonDevPlatform, function (n, ojson) { 
		if(selval == ojson.name){ 
	 		var dataObj=eval("("+ojson.value+")");
	 		var temp_html;
			$.each(dataObj, function (nn, kkjson) {  
				temp_html+="<option value='"+kkjson.name+"'>"+kkjson.value+"</option>";
			});
	 		$("#onlineProduct_sel").html(temp_html);
		}
	});
}); 
//显示新建里程弹窗
$("#btnCrtLineHistory").click(function(){
	var syst=$("#selappname").val();
	if(syst == ""){
		alert("请选择系统名称");
		return;
	}
	$("#devPlatform_sel")[0].selectedIndex=0;
	$("#onlineProduct_sel").html("<option value='请选择'>请选择</option>");
	$("#addLineHistoryModal").modal("show");
});
//保存
$("#saveLineHistory").click(function(){
	var devPlatformValue= $('#devPlatform_sel').val();
	var devPlatformText=$('#devPlatform_sel').find("option:selected").text();
    var onlineProductValue = $('#onlineProduct_sel').val();
    var onlineProductText=$('#onlineProduct_sel').find("option:selected").text();
    var devVersion = $('#txtDevVersion').val().trim();
    var onlineDate = $('#onlineDate').val();
    var onLineContent = $('#txtOnLineContent').val().trim();
    var remark = $('#txtRemark').val().trim(); 
    var paramaters = {
    		"appName":$("#selappname").val(),
            "devPlatform": devPlatformText,
            "devPlatformValue":devPlatformValue,
            "onlineProduct": onlineProductText,
            "onlineProductValue": onlineProductValue,
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
	           }
	       });
	});
});
</script>