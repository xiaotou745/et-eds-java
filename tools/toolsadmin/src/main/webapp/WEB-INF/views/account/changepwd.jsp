<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">旧密码:</label>
							<div class="col-sm-8">
								<input type="password" class="form-control" name="oldpwd"  id="oldpwd" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">新密码:</label>
							<div class="col-sm-8">
							<input type="password" class="form-control" name="newpwd"  id="newpwd" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">确认新密码: </label>
							<div class="col-sm-8">
								 <input type="password" class="form-control" name="confirmnewpwd"  id="confirmnewpwd" />
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-4">
						<button type="button" class="btn btn-w-m btn-primary" id="update"
							style="margin-left: 3px;height:30px;">修改密码</button>
					</div>
			</div>
		</div>
	</div>
</div>

<script>
$("#update").click(function(){
	if($("#oldpwd").val()==""){
		alert("老密码不能为空");
		return;
	}
	if($("#newpwd").val()==""){
		alert("新密码不能为空");
		return;
	}
	if($("#oldpwd").val()==$("#newpwd").val()){
		alert("新密码不能和老密码一致");
		return;
	}
	if($("#newpwd").val()!=$("#confirmnewpwd").val()){
		alert("新密码和确认新密码不一致");
		return;
	}
	var paramaters = {
			"oldPwd" :  $("#oldpwd").val(),
			"newPwd" : $("#newpwd").val()
		};
		var url = "<%=basePath%>/account/updatepwd";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = "<%=basePath %>/account/logoff";
				} else {
					alert("操作失败：旧密码不正确");
				}
			}
		});
});

</script>