<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="com.edaisong.toolsentity.req.TaskPrivilege" %>
<%@page import="com.edaisong.toolsentity.view.TasksViewModel" %>
<%@page import="com.edaisong.toolsentity.domain.User" %>
<%
	TasksViewModel model = (TasksViewModel)request.getAttribute("dataOfModel");
	List<TaskPrivilege> lstTaskPrivileges = EnumHelper.GetEnumItems(TaskPrivilege.class);
%>
<div class="row">
    <div class="col-lg-12">
        <div class="ibox-content">
            <form class="form-inline">
            	<div class="form-group">
                    <label class="control-label">所属：</label>
                    <div class="radio i-checks">
                        <label><input type="radio" value="1" name="typeq" checked="checked"> <i></i> 个人</label>
                        <label><input type="radio" value="2" name="typeq"> <i></i> 公司</label>
                    </div>
                </div>
                <!-- <div class="divider"></div> -->
                <!-- <br/> -->
                <div class="form-group">
                    <label class="control-label">创建时间：</label>
                    <div class="input-daterange input-group" id="datepicker">
                        <input type="text" id="starttime" class="input-sm form-control" name="start" value="" />
                        <span class="input-group-addon">to</span>
                        <input type="text" id="overtime" class="input-sm form-control" name="end" value="" />
                        <span class="input-group-btn">
                            <button type="button" id="btnQuery" class="btn btn-primary btn-sm "><i class="fa fa-search"></i></button>
                        </span>
                    </div>
                </div>

                <a class="btn btn-primary pull-right" type="button" href="#modalTask" data-toggle="modal" data-type="1" data-whatever="创建任务"><i class="fa fa-check"></i>&nbsp;新增任务</a>
            </form>
        </div>
    </div>
</div>
<input type="hidden" id="hidUserId" value="<%=model.getCurUserId() %>"/>
<div class="row" id="jobs">
    <jsp:include page="./_taskslist.jsp"></jsp:include>
</div>

<!--账户对话框-->
<div class="modal fade" id="modalTask" tabindex="-1" role="dialog" aria-labelledby="modalTaskLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalTaskLabel">创建任务</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="frmTask">
                    <input type="hidden" value="0" name="id" />
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="title">标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="title" placeholder="请输入标题"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">优先级</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="selPriorityLevel">
                            	<%for(TaskPrivilege p:lstTaskPrivileges){%>
                            		<option value="<%=p.value()%>"><%=p.getDesc() %></option>
                            	<%} %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="control-label col-sm-2">所属</label>
                    	<div class="col-sm-10">
                    	 	<div class="radio i-checks">
                                <label><input type="radio" value="1" name="type" checked="checked"> <i></i> 个人</label>
                                <label><input type="radio" value="2" name="type"> <i></i> 公司</label>
                            </div>
                    	</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="content">任务内容</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" name="content" placeholder="请填写任务信息" required></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSave" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalTaskStatus" tabindex="-1" role="dialog" aria-labelledby="modalTaskStatusLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalTaskStatusLabel">任务领取</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="frmTaskStatus">
                    <input type="hidden" value="0" name="id" />
                    <input type="hidden" value="0" name="status" />
                    <div class="form-group">
                        <label class="control-label col-sm-2">指派给</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="selWho">
                            	<%for(User user:model.getUsers()){ %>
                            		<option value="<%=user.getId() %>"><%=user.getLoginName() %></option>
                            	<%} %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="control-label col-sm-2">开始时间</label>
                    	<div class="col-sm-10">
                    		<input type="text" name="startTime" class="form-control datetimepicker"/>
                    	</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="content">工时(小时)</label>
                        <div class="col-sm-10">
                            <input type="text" name="taskTime" class="form-control"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnSaveStatus" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>