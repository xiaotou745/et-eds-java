<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div
	class="wrapper wrapper-content animated fadeInRight form-horizontal">

	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">角色名称:</label>
						<div class="col-sm-8">
							<input type="text" placeholder="新增的角色名称"
					class="input-sm form-control" id="newRole"
					style="width: 250px; height: 34px;" value="" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="add"
					style="margin-left: 3px;">新增角色</button>
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
<div class="modal inmodal fade" id="modifyModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-sm">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">修改角色</h4>
	</div>
<div class="modal-body">
  角色名称：<input id="roleName" class="form-control"/><br/>
是否启用：<input type="radio" value="0" name="radstatus" id="radyes"/>
	  <label for="radyes">启用</label>
	  <input type="radio" value="1" name="radstatus" id="radno"/>
	  <label for="radno">不启用</label>
</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" class="btn btn-primary" id="saverole">保存</button>
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
    var oldAuth="";
	var checkstatus=0;
	var expandstatus=0;
	var $checkableTree;
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
	//分配权限
    function setauth(id) {
    	var paramaters = { "roleID": id};
        var url = "<%=basePath%>/role/authlist";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	if (result!="") {
                     //var obj = $.parseJSON(result); 
            		 $checkableTree=$('#treeview11').treeview({
            	        data: result,
            	        levels: 1,
            	        showIcon: false,
            	        showCheckbox: true,
            	        onNodeChecked: function(event, node) {
            	        	checkNode(0,event, node);
            	        },
            	        onNodeUnchecked: function (event, node) {
            	        	checkNode(1,event, node);
            	        }
            	      });
            		 var oldChecked=$checkableTree.treeview('getChecked');
            		 if(oldChecked!=undefined&&oldChecked.length>0){
            			 for (var i = 0; i < oldChecked.length; i++){
            				 oldAuth+=(oldChecked[i].id+",");
            			}
            			 oldAuth=oldAuth.substring(0,oldAuth.length-1);
            		 }
            		 $("#roleid").val(id);
                    $('#myModal').modal('show');
                } 
            }
        });
    }

	function checkNode(type,event, node){
		var parent=$checkableTree.treeview('getParent', node);
		if(type==0){//选中一个节点时，所有父节点选中，选中所有子节点选中
			checkChild(node);
			while(parent!=undefined&&parent.nodes!=undefined&&parent.nodes.length>0){
				$checkableTree.treeview('checkNode', [ parent.nodeId, { silent: true } ]);
				parent=$checkableTree.treeview('getParent', parent);
			}
		}else{
			uncheckChild(node);
			//取消时，如果当前节点的兄弟节点都已经被取消选中了，则当前节点的父节点也应该取消选中
			if(node.isbutton=="0" &&
				parent!=undefined &&
				parent.nodes!=undefined &&
				parent.state.checked){
				var siblingNodes=$checkableTree.treeview('getSiblings', node);
				var needUncheckParent=true;
				if(siblingNodes.length>0){
					for (var i = 0; i < siblingNodes.length; i++){
						if(siblingNodes[i].state.checked){
							needUncheckParent=false;
							break;
						}
					}
				}
				if(needUncheckParent){
					$checkableTree.treeview('uncheckNode', [ parent.nodeId, { silent: false } ]);
				}
			}
		}
	}
	//选中当前节点的所有子节点和孙子节点
	function checkChild(node){
		if(node.nodes!=undefined&&node.nodes.length>0){
	          var childs = $checkableTree.treeview('findNodes', ['^'+node.id+'$','g','parentid']);
	          if(childs!=undefined&&childs.length>0){
	        	  $checkableTree.treeview('checkNode', [ childs, { silent: true } ]);
	   			for (var i = 0; i < node.nodes.length; i++){
	 				 checkChild(node.nodes[i]);
	 			}
	          }
		}
	}
	//取消选中当前节点的所有子节点和孙子节点
	function uncheckChild(node){
		if(node.nodes!=undefined&&node.nodes.length>0){
			 var childs = $checkableTree.treeview('findNodes', ['^'+node.id+'$','g','parentid']);
	         if(childs!=undefined&&childs.length>0){
		        	$checkableTree.treeview('uncheckNode', [ childs, { silent: true } ]);
					for (var i = 0; i < node.nodes.length; i++){
						 uncheckChild(node.nodes[i]);
					}
			  }
		}
	}
	var oldRole="";
	$("#saverole").click(function(){
		var belock=$("input[name='radstatus']:checked").val();
		var newRole=$("#roleid").val()+";"+belock+";"+$('#roleName').val();
		if(oldRole==newRole){
			alert("没有修改，不需要保存");
			return;
		}

		var paramaters = {
				"roleID" :  $("#roleid").val(),
				"belock" : belock,
				"newName" : $('#roleName').val(),
			};
			var url = "<%=basePath%>/role/saverole";
			$.ajax({
				type : 'POST',
				url : url,
				data : paramaters,
				success : function(result) {
					if (result>0) {
						alert("操作成功");
						window.location.href = window.location.href;
						//$('#myModal').modal('hidden');
					} else {
						alert("操作失败");
					}
				}
			});
	});
	
    function modify(id,belock,rolename) {
    	oldRole=id+";"+belock+";"+rolename;
		if(belock==0){
			$("#radyes").prop('checked',true); 
			$("#radno").prop('checked',false); 
		}else{
			$("#radyes").prop('checked',false); 
			$("#radno").prop('checked',true); 
		}
		$("#roleid").val(id);
    	$('#roleName').val(rolename);
        $('#modifyModal').modal('show');
    }
</script>