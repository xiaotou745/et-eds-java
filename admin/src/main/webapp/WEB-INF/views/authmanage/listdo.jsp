<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.resp.AccountResp"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("static.admin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>登录名称</th>
			<th>启用状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<Account> data = (PagedResponse<Account>) request.getAttribute("listData");
			List<Account> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Account>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getLoginname()%></td>
			<td><%=list.get(i).getStatus() > 0 ? "可用" : "锁定"%></td>
			<td><a href="javascript:void(0)"
				onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script type="text/javascript">
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
			"userID" :  $("#userid").val(),
			"newAuth" : newAuth,
			"oldAuth" : oldAuth,
		};
		var url = "<%=basePath%>/authmanage/saveauth";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result=="") {
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
            		 $("#userid").val(id+"");
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