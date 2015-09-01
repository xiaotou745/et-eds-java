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
			<td><a href="javascript:void(0)" onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a></td>
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
                    $('#myModal').modal('show');
                } 
            }
        });
    }
	var $checkableTree;
	var hascall=false;
	function checkNode(type,event, node){
		//if(hascall){
			//hascall=false;
			//return;
		//}
		//hascall=true;
		if(type==0){//选中一个节点时，所有父节点选中，选中所有子节点选中
			if(node.nodes!=undefined&&node.nodes.length>0){
			   $checkableTree.treeview('checkNode', [ node.nodes, { silent: true } ]);
			}
			var parent=$checkableTree.treeview('getParent', node);
			while(parent!=undefined&&parent.nodes!=undefined&&parent.nodes.length>0){
				$checkableTree.treeview('checkNode', [ parent, { silent: true } ]);
				parent=$checkableTree.treeview('getParent', parent);
			}
		}else{
			if(node.nodes!=undefined&&node.nodes.length>0){
				$checkableTree.treeview('uncheckNode', [ node.nodes, { silent: true } ]);
			}
		}
	}
    </script>