<%@page import="com.edaisong.core.enums.TagType"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@page import="com.edaisong.core.enums.OrderAuditStatus"%>


<%	
String basePath =PropertyUtils.getProperty("java.admin.url");
%>

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
							<label class="col-sm-4 control-label">名称:</label>
							<div class="col-sm-8">
							<input type="text" class="form-control" value="" name="txtName" id="txtName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">日期:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="txtDataStart"  id="txtDataStart"/>
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
                                        <input type="text" class="form-control" value="" name="txtDataEnd"  id="txtDataEnd"/>
                                    </div>
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
					
					</div>
				</div>
				  <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
					  	<button type="button" class="btn btn-w-m btn-primary" id="add"  onclick="showAdd()"
					style="margin-left: 3px;height:30px;">新增</button>	
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
<div tabindex="-1" class="modal inmodal" id="addConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加配置</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>名称：</label><input  name="txtAName" id="txtAName" type="text" >
            </div>
            <div class="control-group">  
                <label>描述：</label>
                <textarea name="txtARemark" id="txtARemark" placeholder="此描述会显示在app价格表中" class="input-sm form-control" style="width:300px;height:100px;max-width:300px;max-height:100px;">
                </textarea>
            </div>       
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAdd" onclick="saveAdd()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
<div tabindex="-1" class="modal inmodal" id="modifyConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">修改配送费</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
       <div class="control-group">  
                <label>名称：</label>
                        <input  name="txtEId" id="txtEId" type="hidden">
                <input  name="txtEName" id="txtEName" type="text" >
            </div>
            <div class="control-group">  
                <label>描述：</label>
                <textarea name="txtERemark" id="txtERemark" style="width:300px;height:100px;max-width:300px;max-height:100px;">
                </textarea>
            </div>    
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnModify" onclick="saveModify()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
 
<script>
 
 var jss={
			search:function(currentPage){	
			   var name = $("#txtName").val();
				   var dataStart=$("#txtDataStart").val();
				   var dataEnd=$("#txtDataEnd").val();	 	
				 var paramaters = { 
						 "currentPage":currentPage,						
						 "name": name,
						 "dataStart": dataStart,
						 "dataEnd": dataEnd,
						 };        
			        var url = "<%=basePath%>/taskdistribution/listdo";
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
	$("#btnSearch").click(function() {
		jss.search(1);
	});


	function showAdd(){ 
        $('#txtAName').val('');
        $('#txtARemark').val('');     
        $('#addConfig').modal('show');
}
	function saveAdd()
	{
		var txtAName= $('#txtAName').val().trim();
		var txtARemark= $('#txtARemark').val().trim();	


		 if(txtAName == "")
		 {
			 alert("名称不能为空");
		    return;
		 }
		 if(txtARemark == "")
		 {
			 alert("描述不能为空");
		    return;
		 }		 
		
		 if(txtAName.length<2 || txtAName.length>50  )
		 {
			 alert("名称长度应为2-50个字符");
		    return;
		 }
		 
		 if(txtARemark.length<30 || txtARemark.length>200  )
		 {
			 alert("描述长度应为30-200个字符");
		    return;
		 }
		 
	    var paramaters = {
                "name": txtAName.trim(),
                "remark": txtARemark.trim(),      
            };
	    
       var url = "<%=basePath%>/taskdistribution/add";
       var la= layer.confirm('是否确认创建配置？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.message);		        	
		               if (result.status == 1) {
		            	   window.location.href = "<%=basePath%>/taskdistribution/list";		
		               }
		                              
		        	  
		           }
		       });
		});  
	}
	function saveModify()
	{
		var txtId= $('#txtEId').val().trim();
		var txtEName= $('#txtEName').val().trim();
		var txtERemark= $('#txtERemark').val().trim();	


		 if(txtEName == "")
		 {
			 alert("名称不能为空");
		    return;
		 }
		 if(txtERemark == "")
		 {
			 alert("描述不能为空");
		    return;
		 }		 
		
	    var paramaters = {
	    		"Id": txtId.trim(),
                "name": txtEName.trim(),
                "remark": txtERemark.trim(),      
            };		
	  
       var url = "<%=basePath%>/taskdistribution/modify";
	   var la= layer.confirm('是否确认修改配置？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.message);		        	
		               if (result.status == 1) {
		            	   window.location.href = "<%=basePath%>/taskdistribution/list";		
		               }
		                              
		        	  
		           }
		       });
		});       	    
	}
	 
</script>