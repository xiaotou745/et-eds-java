<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.AuthorityMenuClass"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				<input type="text" placeholder="新增的角色名称"
					class="input-sm form-control" id="newRole"
					style="width: 250px; height: 34px;" value="" />
				<button type="button" class="btn btn-w-m btn-primary" id="add"
					style="margin-left: 3px;">新增角色</button>
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
				<h4 class="modal-title">分配权限</h4>
			</div>

			<div class="modal-body"
				style="height: 500px; overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;">

				<fieldset>
					<div class="control-group">
						<div class="controls">
							<button class="btn btn-success" id="btnExpanAll" type="button">展开/折叠</button>
							<button class="btn btn-success" id="btn-check-all" type="button">全选/全消</button>
						</div>
						<div class="controls">
							<div class="test treeview" id="treeview11"></div>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="modal-footer">
				<input id="roleid" type="hidden" name="roleid" />
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="saveauth">保存</button>

			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	var data=null;
	$.post("<%=basePath%>/role/listdo", data, function(d) {
		$("#content").html(d);
	});
});

	$("#add").click(function() {
		if($("#newRole").val()==""){
			alert("角色名称不能为空");
			return;
		}
		var paramaters = {
				"roleName" :  $("#newRole").val()
			};
			var url = "<%=basePath%>/role/add";
			$.ajax({
				type : 'POST',
				url : url,
				data : paramaters,
				success : function(result) {
					if (result>0) {
						alert("操作成功");
						window.location.href = window.location.href;
					} else {
						alert("操作失败");
					}
				}
			});		
	});
	//全选全消
    $('#btn-check-all').on('click', function (e) {
	      if(checkstatus==0){
	    	  checkstatus=1;
	        $checkableTree.treeview('checkAll', { silent: true });
	      }else{
	    	  checkstatus=0;
	    	  $checkableTree.treeview('uncheckAll', { silent: true }); 
	      }
    });

    // 展开/折叠
    $('#btnExpanAll').on('click', function (e) {
    	if(expandstatus==0){
    		expandstatus=1;
    		 $checkableTree.treeview('expandAll', { levels: 10, silent: true });
	      }else{
	    	  expandstatus=0;
	    	  $checkableTree.treeview('collapseAll', { silent: true });
	      }
      
    });
    var oldAuth="";
	var checkstatus=0;
	var expandstatus=0;
	var $checkableTree;
  //保存权限设置
	$("#saveauth").click(function() {
		var newAuth = "";
        var newChecked=$checkableTree.treeview('getChecked');
		if(newChecked!=undefined&&newChecked.length>0){
			 for (var i = 0; i < newChecked.length; i++){
				 newAuth += (newChecked[i].id+ ",");
			}
			 newAuth=newAuth.substring(0,newAuth.length-1);
		 }
		if(newAuth==oldAuth){
			alert("没有任何修改，不需要保存");
			return;
		}
		if (!confirm("确定要提交保存吗？")){
			return;
		}
		var paramaters = {
			"roleID" :  $("#roleid").val(),
			"newAuth" : newAuth,
			"oldAuth" : oldAuth,
		};
		var url = "<%=basePath%>/role/saveauth";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result == "") {
					alert("操作成功");
					window.location.href = window.location.href;
					//$('#myModal').modal('hidden');
				} else {
					alert(result);
				}
			}
		});
	});
</script>