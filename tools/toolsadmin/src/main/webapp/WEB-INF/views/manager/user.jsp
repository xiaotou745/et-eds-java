<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.domain.User" %>
<%@page import="com.edaisong.toolsentity.domain.Role" %>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
	List<User> rootMenus = (List<User>) request.getAttribute("dataOfuserlist");
	List<Role> allRoles = (List<Role>) request.getAttribute("dataOfRoles");
%>

<div class="row">
    <div class="col-lg-12">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>所有用户</h5>
                    <div class="ibox-tools">
                        <a href="#modalUsers" data-toggle="modal" data-type="1" class="btn btn-primary btn-xs">新建用户</a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row m-b-sm m-t-sm">
                        <div class="col-md-12">
                            <div class="input-group">
                                <input type="text" placeholder="请输入用户名称" class="input-sm form-control">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-sm btn-primary">搜索</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div id="tblContainer">
                        <jsp:include page="./_userlist.jsp"></jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="modalUsers" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="lblUsers">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="lblUsers">用户编辑</h4>
            </div>
            <div class="modal-body" id="frmUser">
                <form class="form-horizontal" id="form1">
                    <input type="hidden" name="Id" value="0" />
                    <div class="form-group">
                        <label class="control-label col-sm-2">登录账号</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtLoginName" class="form-control input-sm" placeholder="请输入登录账号" required="required" />
                        </div>
                    </div>
                    <div class="form-group J_Second">
                        <label class="control-label col-sm-2">登录密码</label>
                        <div class="col-sm-10">
                            <input type="text" name="txtLoginPwd" class="form-control input-sm" placeholder="请输入密码" />
                        </div>
                    </div>

                    <div class="form-group J_First">
                        <label class="control-label col-sm-2">用户名</label>
                        <div class="col-sm-10">
                        	<input type="text" name="txtUserName" class="form-control input-sm" placeholder="输入真实姓名吧">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSaveUser" class="btn btn-primary">保存用户</button>
            </div>
        </div>
    </div>
</div>
<div id="modalRoles" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="lblRoles">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="lblRoles">用户所属角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" value="0" name="Id" />
                    <%for(int index=0;index<allRoles.size();index++){
                    	Role role = allRoles.get(index);
                    	if(index%3==0){%>
                    		<div class="row">
                    	<%}%>
                    	<div class="col-sm-4">
	                        <label class="checkbox-inline">
	                            <input type="checkbox" class="i-checks" value="<%=role.getId()%>"> <%=role.getName() %>
	                        </label>
                    	</div>
                    	<%if (index % 3 == 2 || index==allRoles.size()-1)
                        {%>
                            </div>
                        <%}
                    }%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSaveRole" class="btn btn-primary">保存用户角色</button>
            </div>
        </div>
    </div>
</div>


