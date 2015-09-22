<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							
							<div class="col-sm-8">							
								<input id="groupBusinessName"
								placeholder="集团名称,登录名称"
								 type="tel" class="form-control"  name="groupBusinessName" onkeydown="return disableEnter(event)"/>								
							</div>
						</div>
					</div>
					<div class="col-lg-3">
<button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left: 3px;height:30px;">查询</button>
<input type="button" value="添加集团" class="btn btn-w-m btn-primary" id="btnModifyGroupBusiness" onclick="showAddGroupBusiness()" style="margin-left: 3px;height:30px;" />
<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1" />
<input id="hiddenText" type="text" style="display:none" />
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
								
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
								
							</div>
						</div>
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


<div tabindex="-1" class="modal inmodal" id="addGroupBusiness"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加集团</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>集团名称：</label><input  name="txtAddGroupBusinessName" id="txtAddGroupBusinessName" type="text">
            </div>
            <div class="control-group">
                <label>是否允许透支：</label> 
                <input id="rAddIsAllowOverdraftY" name="rAddIsAllowOverdraft" type="radio" value="1"><label for="rAddIsAllowOverdraftY">是</label>
				<input id="rAddIsAllowOverdraftN" name="rAddIsAllowOverdraft" type="radio" checked="checked" value="0"><label for="rAddIsAllowOverdraftN">否</label>
            </div>
            <div class="control-group">
                <label >登陆账号：</label> 
                    <input  name="txtAddLoginName" id="txtAddLoginName" type="text">
            </div> 
            <div class="control-group">
                <label >集团密码：</label> 
                    <input  name="txtAddPassword" id="txtAddPassword" type="password">
            </div> 
            <div class="control-group">
                <label >确认密码：</label> 
                    <input  name="txtAddConfirmPassword" id="txtAddConfirmPassword" type="password">
            </div> 
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAddGroupBusiness" onclick="saveAddGroupBusiness()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
<div tabindex="-1" class="modal inmodal" id="modifyGroupBusiness"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">修改集团</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
			            <br>
			             <div class="control-group">
			                <label >集团名称：</label> 
			                    <input  name="txtModifyGroupBusinessName" id="txtModifyGroupBusinessName" type="text">
			                    <input  name="txtModifyGroupBusinessId" id="txtModifyGroupBusinessId" type="hidden">
			                    
			            </div>
			            <div class="control-group">
			                <label>是否允许透支：</label> 
			                <input id="rModifyIsAllowOverdraftY" name="rModifyIsAllowOverdraft" type="radio" value="1"><label for="rModifyIsAllowOverdraftY">是</label>
							<input id="rModifyIsAllowOverdraftN" name="rModifyIsAllowOverdraft" type="radio" value="0"><label for="rModifyIsAllowOverdraftN">否</label>
			            </div>
			            <div class="control-group">
			                <label >登录账号：</label> 
			                    <input name="txtModifyLoginName" id="txtModifyLoginName" type="text" disabled="disabled">
			            </div> 
			            <div class="control-group">
			                <label >密&nbsp;&nbsp;码：</label> 
			                    <input  name="txtModifyPassword" id="txtModifyPassword" type="password">
			            </div> 
			            <div class="control-group">
			                <label >确认密码：</label> 
			                    <input  name="txtModifyConfirmPassword" id="txtModifyConfirmPassword" type="password">
			            </div> 
			            <hr/>
			            <div>
			            	<h4>操作记录</h4> 
			            	<div id="modifyGroupBusinessLog"></div>
			            </div>
			        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id=btnModifyGroupBusiness onclick="saveModifyGroupBusiness()">保存</button>
				</div> 
			</small>
		</div>  
	</div>
</div>
<script>
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
		 $.post("<%=basePath%>/groupbusiness/listdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	//回车
	function disableEnter(event){
		 var e = event || window.event || arguments.callee.caller.arguments[0];
	        if(e && e.keyCode==27){ // 按 Esc 
	            //要做的事情
	          }
	        if(e && e.keyCode==113){ // 按 F2 
	             //要做的事情
	           }            
	         if(e && e.keyCode==13){ // enter 键
	             //要做的事情
	      	  $('#btnSearch').click();
	          // jss.search(1);
	        }
	};
	function showAddGroupBusiness(){ 
	        $('#txtAddGroupBusinessName').val('');
	        $('#txtAddLoginName').val('');
	        $('#txtAddPassword').val('');
	        $('#txtAddConfirmPassword').val(''); 
	        $("input:radio[name='rModifyIsAllowOverdraft'][value='0']").attr("checked",true);
	        $('#addGroupBusiness').modal('show');
	}
	//保存集团商户
	function saveAddGroupBusiness(){
		var groupBusiName= $('#txtAddGroupBusinessName').val().trim();
	    var loginName = $('#txtAddLoginName').val().trim();
	    var pw = $('#txtAddPassword').val().trim();
	    var confirmPw = $('#txtAddConfirmPassword').val().trim();
	    var reg=/^[\u4e00-\u9fa5]+$/;
	    
	    if(groupBusiName.trim().length <=4 || groupBusiName.trim().length>30){
	    	alert("集团名称必须在5-30个字符");
	    	return;
	    }
	    if (reg.test(loginName)){
	    	alert("登录账号不能为中文字符");
	    	return;
	    }
	    if(loginName.trim().length <6 || loginName.trim().length>20){
	    	alert("登录账号除中文外6-20位字符");
	    	return;
	    }
	    if(pw.trim().length <6 || pw.trim().length>20){
	    	alert("密码长度必须在6-20位字符之间");
	    	return;
	    }
	    if(confirmPw.trim().length ==0 || confirmPw.trim().length>20){
	    	alert("密码长度必须在6-20位字符之间");
	    	return;
	    }
	    if(pw.trim() != confirmPw.trim()){
	    	alert("两次输入的密码不一致");
	    	return;
	    }
	    var paramaters = {
                "groupBusinessName": groupBusiName.trim(),
                "loginName": loginName.trim(),
                "passWord": pw.trim(),
                "isAllowOverdraft":$('input[name="rAddIsAllowOverdraft"]:checked').val()
            };
       var url = "<%=basePath%>/groupbusiness/addgroupbusiness";
	   var la= layer.confirm('是否确认创建集团？', {
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
		               if (result.responseCode > 0) {
		                   window.location.href = "<%=basePath%>/groupbusiness/list";
		               }
		        	  
		           }
		       });
		});
       	    
	}
	//显示修改集团信息弹框
	function saveModifyGroupBusiness(){
		var groupBusiName= $('#txtModifyGroupBusinessName').val().trim();
	    var loginName = $('#txtModifyLoginName').val().trim();
	    var pw = $('#txtModifyPassword').val().trim();
	    var confirmPw = $('#txtModifyConfirmPassword').val().trim();
	    var reg=/^[\u4e00-\u9fa5]+$/;
	    if(groupBusiName.trim().length <=4 || groupBusiName.trim().length>30){
	    	alert("集团名称必须在5-30个字符");
	    	return;
	    }
	    if (reg.test(loginName)){
	    	alert("登录账号不能为中文字符");
	    	return;
	    }
	    if(loginName.trim().length <6 || loginName.trim().length>20){
	    	alert("登录账号除中文外6-20位字符");
	    	return;
	    }
	    if( pw.trim().length == 0 &&  confirmPw.trim().length == 0 && pw.trim() == confirmPw.trim()){
	    	alert("不填写时密码保持不变"); 
	    }else{
		    if(pw.trim().length <6 || pw.trim().length>20){
		    	alert("密码长度必须在6-20位字符之间");
		    	return;
		    }
		    if(confirmPw.trim().length ==0 || confirmPw.trim().length>20){
		    	alert("密码长度必须在6-20位字符之间");
		    	return;
		    }
		    if(pw.trim() != confirmPw.trim()){
		    	alert("两次输入的密码不一致");
		    	return;
		    }
	    }
	    var paramaters = {
	    		"id":$("#txtModifyGroupBusinessId").val(),
                "groupBusinessName": groupBusiName.trim(),
                "loginName": loginName.trim(),
                "passWord": pw.trim(),
                "isAllowOverdraft":$('input[name="rModifyIsAllowOverdraft"]:checked').val()
            };
       var url = "<%=basePath%>/groupbusiness/modifygroupbusiness";
       $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (result) {
        	   alert(result.message);
               if (result.responseCode > 0) {
                   window.location.href = "<%=basePath%>/groupbusiness/list";
               }
        	  
           }
       });	
	}
	
</script>