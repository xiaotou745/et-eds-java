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
	List<AuthorityMenuClass> menuList = (List<AuthorityMenuClass>) request.getAttribute("menuList");
%>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
	    <div class="col-lg-12">
	    	        <div class="input-group" style="margin-bottom:5px;">
	                                                角色<input type="radio" name="objtype" value="0" checked="checked">
					账号<input type="radio" name="objtype" value="1">
	        </div>
	        <div class="input-group" style="margin-bottom:5px;">
	            <input type="text" placeholder="请输入账号名称" class="input-sm form-control" id="txtKeyword" style="width:250px;height:34px;" value=""/>
	            <button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	        </div>
	    </div>
	</div>
</div>
 
<div id="content">
	
</div>



<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-lg">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">分配权限</h4>
	</div>
<div class="modal-body">
<% 
for (AuthorityMenuClass menu : menuList) {
	if (menu.getParid() == 0) {
		for (AuthorityMenuClass itemMenu : menuList) {
			if (itemMenu.getParid() == menu.getId()) {

			}
		}
	}
}
%>
</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" class="btn btn-primary">保存</button>
	</div>
    </div>
</div>
</div>

<script>


var jss={search:function(currentPage){
			var keywordvalue=$("#txtKeyword").val();
			var data={Keyword:keywordvalue,CurrentPage:currentPage};
			$.post("<%=basePath%>/authmanage/listdo",data,function(d){
				$("#content").html(d);
			});
		}
	}
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>