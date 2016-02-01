
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.TestUserRecord"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号:</label>
						<div class="col-sm-8">
							<input type="text" placeholder="请输入测试手机号"
								class="input-sm form-control" id="phoneNo"
								style="width: 250px; height: 34px;" value="" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
						style="margin-left: 3px;">查询</button>
					<button type="button" class="btn btn-w-m btn-primary" id="add"
						style="margin-left: 3px;">创建新账号</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="content"></div>



<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">创建新账号</h4>
			</div>

			<div class="modal-body">
				  手机号：<input id="newphoneNo" class="form-control"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="save">保存</button>

			</div>
		</div>
	</div>
</div>
<!-- layer方法 -->
<script type="text/javascript">
var jss = {
		search : function(currentPage) {
			var data={"phoneNo":$("#phoneNo").val(),
					  "currentPage":currentPage
					 };
			$.post("<%=basePath%>/testuser/listdo", data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	$("#add").click(function() {
		$('#myModal').modal('show');
	});
	$("#save").click(function() {
		if($("#newphoneNo").val()==""){
			alert("手机号不能为空");
			return;
		}
		var pars = { "phoneNo": $("#newphoneNo").val() };
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>/testuser/add',
            data: pars,
            success: function (result) {
            	if(result>0){
    				alert("操作成功");
    				window.location.href = window.location.href;
            	}else{
    				alert("操作失败:手机号已经存在");
            	}
            }
        });
	});
	///删除骑士
    function deleteInfo(phone,infoType) {
		switch(infoType){
		case 1:
			if (!confirm("确定删除该骑士?")){
				return;
			}
			break;
		case 2:
			if (!confirm("确定删除订单数据?")){
				return;
			}
			break;
		case 3:
			if (!confirm("确定删除该门店?")){
				return;
			}
			break;
		}

        var pars = { "phoneNo": phone,"infoType":infoType };
        var url = "<%=basePath%>/testuser/deleteinfo";
        $.ajax({
            type: 'POST',
            url: url,
            data: pars,
            success: function (result) {
				alert("操作成功");
				window.location.href = window.location.href;
            }
        });
    }
</script>