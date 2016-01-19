<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.Group"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">第三方平台名称:</label>
							<div class="col-sm-8">
							  <input type="text" class="form-control" value="" name="groupName"  id="txtGroupName"/>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">第三方平台AppKey:</label>
							<div class="col-sm-8">
							  <input type="text" class="form-control" value="" name="appKey"  id="txtAppkey"/>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
					   <button type="button" class="btn btn-w-m btn-primary" id=btnAdd
							style="margin-left: 3px;height:30px;">新增第三方平台</button>
					</div>
					<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
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
<!-- 新增第三方平台 弹窗 -->
<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 550px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">新增第三方平台</h4>
			</div>
			<div class="modal-body form-horizontal">
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">第三方平台名称:</label>
							<div class="col-sm-8">
								<input name="txtaddGroupName" class="form-control" id="txtaddGroupName"
									type="text" value="" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnAddGroup" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-white"  data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 修改第三方平台 弹窗 -->
<div class="modal inmodal fade" id="myModalEdit" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 550px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">修改第三方平台</h4>
			</div>
			<div class="modal-body form-horizontal">
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">第三方平台名称:</label>
							<div class="col-sm-8">
								<input name="txtuEditGroupName" class="form-control" id="txtuEditGroupName"
									type="text" value="" />
							</div>
						</div>
					</div>
				</div>
			</div>
			 <input type="hidden" id="hiduEditGroupID" value=""/>
			<div class="modal-footer">
				<button type="button" id="btnEditGroup" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-white"  data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 修改第三方平台 弹窗 -->
<div class="modal inmodal fade" id="myModalEdit" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 550px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">设置第三方平台AppConfig</h4>
			</div>
			<div class="modal-body form-horizontal">
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">AppKey:</label>
							<div class="col-sm-8">
								<input name="txtAppKeys" class="form-control" id="txtAppKeys"
									type="text" value="" />
							   <label style="color: red">建议为"appkey_"+第三方平台名称小写全拼</label>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">App版本:</label>
							<div class="col-sm-8">
								<input name="txtAppVersion" class="form-control" id="txtAppVersion"
									type="text" value="" />
									<label style="color: red">默认1.0</label>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-12 "  style="color: red">AppSecret会自动生成</label>
						</div>
					</div>
				</div>
				
			</div>
			 <input type="hidden" id="hiduEditGroupID" value=""/>
			<div class="modal-footer">
				<button type="button" id="btnEditGroup" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-white"  data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<script>
var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/group/selectlist",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	$("#btnAdd").click(function(){
		$("#myModal").modal('show');
	});

	  //添加第三方平台
    $("#btnAddGroup").on('click', function () {
        var txtGroupName = $("#txtaddGroupName").val(); //第三方平台名称
        if (txtGroupName == "") {
        	layer.alert("第三方平台名称不能为空!", {
			    icon: 2
	    	});
            return;
        }
        var pars = { "groupname": txtGroupName };
        var url = "<%=basePath%>/group/addgroup";
        $.ajax({
            type: 'POST',
            url: url,
            data: pars,
            success: function (result) {
            	if (result.responseCode==0) 
                {
                	layer.alert("添加成功!", {
        			    icon: 1
        	    	},function(){
        	    		window.location.reload();
        	    	});
                } else {
                	layer.alert(result.message, {
        			    icon: 2
        	    	});
                }
            }
        });
    });
	  
    //显示修改集团弹出层
    function funcGShowView(gid, gname) {    
    	   $("#hiduEditGroupID").val(gid);
           $("#txtuEditGroupName").val(gname);
        	$("#myModalEdit").modal('show');
    }

    //修改第三方平台
    $("#btnEditGroup").on('click', function () {
        var txtGroupName = $("#txtuEditGroupName").val(); //第三方平台名称
        var hiduGroupId = $("#hiduEditGroupID").val(); //第三方平台id 
        if (txtGroupName == "") {
        	layer.alert("第三方平台名称不能为空!", {
			    icon: 2
	    	});
            return;
        }
        var pars = { "id":hiduGroupId,"groupname": txtGroupName };
        var url = "<%=basePath%>/group/updategroup";
        $.ajax({
            type: 'POST',
            url: url,
            data: pars,
            success: function (result) {
            	if (result.responseCode==0) 
                {
                	layer.alert("修改成功!", {
        			    icon: 1
        	    	},function(){
        	    		window.location.reload();
        	    	});
                } else {
                	layer.alert(result.message, {
        			    icon: 2
        	    	});
                }
            }
        });
    });
</script>
