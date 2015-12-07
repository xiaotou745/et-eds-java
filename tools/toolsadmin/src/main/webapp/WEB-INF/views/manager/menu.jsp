<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.domain.Menu" %>
<%@page import="com.edaisong.toolsadmin.common.MenuUtils" %>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
	List<Menu> rootMenus = MenuUtils.getTopMenus();
%>


<div class="row">
    <div class="col-lg-12">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>菜单列表</h5>
                    <div class="ibox-tools">
                        <a href="#modalMenus" data-toggle="modal" data-type="1" class="btn btn-primary btn-xs">新建菜单</a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div id="tblContainer">
                        <jsp:include page="./_menulist.jsp"></jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="modalMenus" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="lblMenus">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="lblMenus">菜单编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="form1">
                    <input type="hidden" name="Id" value="0" />
                    <div class="form-group">
                        <label class="control-label col-sm-2">菜单名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtName" class="form-control input-sm" placeholder="菜单名称" required="required" />
                        </div>
                    </div>
                    
                    <div class="form-group J_Second">
                        <label class="control-label col-sm-2">菜单Url</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtUrl" class="form-control input-sm" placeholder="Url" />
                        </div>
                    </div>
					<div class="form-group J_Second">
                        <label class="control-label col-sm-2">ViewPath</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtViewPath" class="form-control input-sm" placeholder="JavaViewPath" required="required" />
                        </div>
                    </div>
                    <div class="form-group J_First">
                        <label class="control-label col-sm-2">Icon</label>
                        <div class="col-sm-10">
                            <div class="input-group">
                                <input type="text" name="txtIcon" class="form-control input-sm" placeholder="输入Icon">
                                <span class="input-group-addon input-sm"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtOrderBy" class="form-control input-sm" placeholder="排序" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">父菜单</label>
                        <div class="col-sm-10">
                            <select class="form-control input-sm" name="selParentId">
                                <option value="0">请选择</option>
                                <%for(Menu menu:rootMenus) {%>
                                	<option value="<%=menu.getId()%>"><%=menu.getName() %></option>
                                <%}
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group J_Second">
                    	<div class="col-sm-offset-2 col-sm-10">
                            <label class="checkbox-inline i-checks"> <input name="chkOpenNewWindow" type="checkbox" class="form-control"> 打开新窗口 </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSave" class="btn btn-primary">保存菜单</button>
            </div>
        </div>
    </div>
</div>



