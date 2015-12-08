<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsadmin.common.MenuUtils"%>
<%@page import="com.edaisong.toolsentity.domain.Menu" %>
<%@page import="com.edaisong.toolsentity.domain.Role" %>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
	List<Role> allRoles = (List<Role>) request.getAttribute("dataOfRoles");
%>
<div class="row">
    <div class="col-lg-12">
        <div class="wrapper wrapper-content animated fadeInUp">

            <div class="ibox">
                <div class="ibox-title">
                    <h5>角色管理</h5>
                    <div class="ibox-tools">
                        <a href="#modalRoles" data-toggle="modal" data-type="1" class="btn btn-primary btn-xs">新建角色</a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                    角色列表
                                </div>
                                <div class="panel-body">
                                    <div id="rolesContainer">
                                    	<jsp:include page="./_rolelist.jsp"></jsp:include>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-7">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <a id="btnSaveRolePrivilege" class="btn btn-xs btn-primary pull-right" style="padding:0px 10px 0px 10px;">保存权限</a>
                                    <div>角色权限(<small><strong id="lblRoleName" class="text-primary">管理员</strong></small>)</div>
                                </div>
                                <div class="panel-body">
                                    <div id="menusContainer">
                                    	<%if(MenuUtils.hasMenus()){
                                    		for(Menu rootMenu:MenuUtils.getTopMenus()){%>
                                    			<ul>
                                    				<li id="<%=rootMenu.getId() %>" class="jstree-open"><%=rootMenu.getName() %>
                                    				<ul>
                                    				<%for(Menu child:MenuUtils.getChildMenus(rootMenu.getId())){%>
                                    					<li id="<%=child.getId()%>"><%=child.getName()%></li>
                                    				<%} %>
                                    				</ul>
                                    			</ul>
                                    		<%}
                                    	}else{%>
                                            <a href="<%=basePath %>/menu">去建立菜单吧，少年!</a>
                                        <%}%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="modalRoles" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="lblRoles">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="lblRoles">权限编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="Id" value="0" />
                    <div class="form-group">
                        <label class="control-label col-sm-2">权限名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtName" class="form-control input-sm" placeholder="权限名称" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSaveRole" class="btn btn-primary">保存权限</button>
            </div>
        </div>
    </div>
</div>