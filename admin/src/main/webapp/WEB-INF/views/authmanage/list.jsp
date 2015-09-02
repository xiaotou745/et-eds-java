<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.api.service.impl.PublicProvinceCityService"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.AuthorityMenuClass"%>
<%
	String basePath =PropertyUtils.getProperty("static.admin.url");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				角色<input type="radio" name="objtype" value="0" checked="checked">
				账号<input type="radio" name="objtype" value="1">
			</div>
			<div class="input-group" style="margin-bottom: 5px;">
				<input type="text" placeholder="请输入账号名称"
					class="input-sm form-control" id="txtKeyword"
					style="width: 250px; height: 34px;" value="" />
				<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
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

			<div class="modal-body" style="height: 500px; overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;">

				<div class="test treeview" id="treeview11"></div>
				<div class="control-group">
               <button class="btn btn-success" id="btnExpanAll" type="button">展开/折叠</button>    
              <button class="btn btn-success" id="btn-check-all" type="button">全选/全消</button>                      
            </div>
			</div>
			<div class="modal-footer">
			<input id="userid" type="hidden" name="userid" />
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
		jss.search(1);
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
</script>