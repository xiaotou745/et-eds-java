<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.AuthorityRole"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%
	String basePath =PropertyUtils.getProperty("static.admin.url");
List<AuthorityRole> roleData = (List<AuthorityRole>) request.getAttribute("roleData");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				<input type="text" placeholder="请输入账号名称"
					class="input-sm form-control" id="txtKeyword"
					style="width: 250px; height: 34px;" value="" />
				<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
					style="margin-left: 3px;">查询</button>
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
						按照角色<input type="radio" id="roletype" name="objtype" value="0">
						单独分配<input type="radio" id="usertype" name="objtype" value="1">
					</div>
					<div class="control-group" id="rolediv">
						<%=HtmlHelper.getSelect("roleid", roleData, "rolename", "id",null,null,"全部","width:100px")%>
					</div>
					<div class="control-group" id="userdiv" style="display:hidden;">
						<div class="controls">
							<button class="btn btn-success" id="btnExpanAll" type="button">展开/折叠</button>
							<button class="btn btn-success" id="btn-check-all" type="button">全选/全消</button>
						</div>
						<div class="controls">
							<div class="test treeview" id="treeview11"></div>
						</div>
					</div>
				</fieldset>
				<div class="control-group"></div>
			</div>
			<div class="modal-footer">
				<input id="userid" type="hidden" name="userid" />
				<input id="userroleid" type="hidden" name="userroleid" />
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="saveauth">保存</button>

			</div>
		</div>
	</div>
</div>

<script>
var jss={search:function(currentPage){
			var keywordvalue=$("#txtKeyword").val();
			var data={Keyword:keywordvalue,CurrentPage:currentPage};
			$.post("<%=basePath%>/authmanage/listdo", data, function(d) {
				$("#content").html(d);
			});
		}
	}

	jss.search(1);
	$("#btnSearch").click(function() {
		if($("#txtKeyword").val()!=""){
		   jss.search(1);
		}
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

    $("input[type='radio'][name='objtype']").change(function() { 
    	var typeid=$("input[name='objtype']:checked").val();
    	if(typeid=="0"){
      		$("#userdiv").hide();
              $("#rolediv").show();
          }else{
        		$("#userdiv").show();
                $("#rolediv").hide();
          }
    }); 
    
    var oldAuth="";
	var checkstatus=0;
	var expandstatus=0;
	var $checkableTree;
  //保存权限设置
	$("#saveauth").click(function() {
		var typeid=$("input[name='objtype']:checked").val();
		 if(typeid=="0"){
			 if($("#userroleid").val()==$("#roleid").val()){
				 alert("没有变更，不需要保存");
				 return;
			 }
			 //原来用户没有角色，则此时需要删掉原来的权限
			 var needUpdateAuth=true;
			if(parseInt($("#userroleid").val())>0){
				needUpdateAuth=false;
			}
			 updateRole($("#roleid").val(),needUpdateAuth);
		 }
		 else{
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
				updateAuth(newAuth,oldAuth);
				//如果当前用户以前有自己的角色，现在要单独配置权限，则应该将原来的角色置为0
				if(parseInt($("#userroleid").val())>0){
				   updateRole(0,false);
				}
		 }
	});
  function updateRole(roleid,updateauth){
	  var paramaters = {
				"userID" :  $("#userid").val(),
				"newRoleID" : roleid
			};
			var url = "<%=basePath%>/authmanage/updateroleid";
			$.ajax({
				type : 'POST',
				url : url,
				data : paramaters,
				success : function(result) {
					if (result>0) {
						if(updateauth){
							//用户原来是单独配置的角色，现在改为了按照角色配置，则应该将原来的权限删掉
							updateAuth("",oldAuth);
						}else{
							alert("操作成功");
							window.location.href = window.location.href;
						}
					} else {
						alert("操作失败");
					}
				}
			});
  }
  function updateAuth(newauths,oldauths){
	  var paramaters = {
				"userID" :  $("#userid").val(),
				"newAuth" : newauths,
				"oldAuth" : oldauths,
			};
			var url = "<%=basePath%>/authmanage/saveauth";
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
  }
//分配权限
  function setauth(id) {
  	var paramaters = { "userID": id};
      var url = "<%=basePath%>/authmanage/getroleid";
      $.ajax({
          type: 'POST',
          url: url,
          data: paramaters,
          success: function (result) {
              $("#userroleid").val(result);
              createAuthTree(id);
          	if (result>0) {//当前用户已经有所属角色
          		$("#usertype").removeAttr("checked");
          		$("#userdiv").hide();
           		$("#roletype").attr("checked","checked");
                  $("#rolediv").show();
                  $("#roleid").val(result);
              }else{
              	$("#roletype").removeAttr("checked");
          		$("#rolediv").hide();
                  $("#usertype").attr("checked","checked");
                  $("#userdiv").show();
              }

      		$("#userid").val(id+"");
              $('#myModal').modal('show');
          }
      });
  }
  function createAuthTree(id) {
  	var paramaters = { "userID": id};
      var url = "<%=basePath%>/authmanage/authlist";
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
			if(parent!=undefined&&parent.nodes!=undefined&&parent.state.checked){
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
</script>