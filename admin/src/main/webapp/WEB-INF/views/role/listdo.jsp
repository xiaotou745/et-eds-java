<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.AuthorityRole"%>
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
			<th>角色名称</th>
			<th>启用状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
		List<AuthorityRole> list = (List<AuthorityRole>) request.getAttribute("listData");
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getRolename()%></td>
			<td><%=list.get(i).getBelock()? "锁定" : "可用"%></td>
			<td><a href="javascript:void(0)"
				onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<script type="text/javascript">
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