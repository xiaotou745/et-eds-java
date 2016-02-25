
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>

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
			<div class="input-group" style="margin-bottom: 5px;">
				<input type="text" placeholder="请输入金额"
					class="input-sm form-control" id="txtSelectAmount"
					style="width: 250px; height: 34px;" value="" />
				<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
					style="margin-left: 3px;">查询</button>
						            <button type="button" class="btn btn-w-m btn-primary" onclick="showAdd()" style="margin-left:3px;" data-toggle="modal" data-target="#myModal" id="add">添加小费</button>
			</div>
		</div>
	</div>
</div>

<div id="content"></div>

<div tabindex="-1" class="modal inmodal" id="addOrderTip"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加小费</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>金额：</label><input  name="txtAmount" id="txtAmount" type="text" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
            </div>
   
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAddOrderTip" onclick="saveAddOrderTip()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
   
    <div tabindex="-1" class="modal inmodal" id="modifyOrderTip"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">修改小费</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>金额：</label>
                <input  name="txtEAmount" id="txtEAmount"  name="txtEAmount"  type="text" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
                <input  name="txtEId" id="txtEId" type="hidden">             
            </div>            
            
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAdd" onclick="saveModify()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>

	<script>		
	var jss={
			search:function(currentPage){	
				   var txtAmount = $("#txtSelectAmount").val();				 		 
				 var paramaters = { 
						 "currentPage":currentPage,
						 "amount": txtAmount				
						 };        
			        var url = "<%=basePath%>/ordertip/listdo";
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
	$("#btnSearch").click(function(){
		jss.search(1);
	});		

	
	function showAdd(){ 
        $('#txtAmount').val('');
        $('#addOrderTip').modal('show');
}	

	function saveAddOrderTip(){	
		var txtAmount= $('#txtAmount').val().trim();	
	 
		 if(txtAmount == "")
		 {
			 	alert("金额不能为空");
		    	return;
		 }
		
	    if(txtAmount<0){
	    	alert("金额必须大于0");
	    	return;
	    }
	    if(txtAmount>100){
	    	alert("金额不能大于100");
	    	return;
	    }
	    
	    var paramaters = {	    	
                "amount": txtAmount.trim()        
            };
       var url = "<%=basePath%>/ordertip/add";
	   var la= layer.confirm('是否确认创建小费？', {
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
		            	   window.location.href = "<%=basePath%>/ordertip/list";		
		               }		                   		            
		        	  
		           }
		       });
		});
       	    
	}
	
	function saveModify(){
		var txtId= $('#txtEId').val().trim();	
		var txtAmount= $('#txtEAmount').val().trim();	
	 
		 if(txtAmount == "")
		 {
			 	alert("金额不能为空");
		    	return;
		 }		
	    if(txtAmount<0){
	    	alert("金额必须大于0");
	    	return;
	    }
	    if(txtAmount>100){
	    	alert("金额不能大于100");
	    	return;
	    }
	    var paramaters = {
	    		"Id": txtId.trim(),
                "amount": txtAmount.trim(),        
            };
       var url = "<%=basePath%>/ordertip/modify";
	   var la= layer.confirm('是否确认修改小费？', {
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
		            	   window.location.href = "<%=basePath%>/ordertip/list";		
		               }		                   		            
		        	  
		           }
		       });
		});
       	    
	}
	</script>		
	
