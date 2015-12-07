<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="java.util.List" %>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.req.TasksStatus"%>
<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@page import="com.edaisong.toolsentity.view.TasksViewModel" %>
<%@page import="com.edaisong.toolsentity.domain.Tasks" %>
<%
	TasksViewModel model = (TasksViewModel)request.getAttribute("dataOfModel");
%>

<div class="col-lg-4">
    <div class="ibox">
        <div class="ibox-content">
            <h3>未开始</h3>
            <hr/>
            <!-- <p class="small"><i class="fa fa-hand-o-up"></i> 可以拖拉哦</p>

            <div class="input-group">
                <input type="text" id="txtMyJobTitle" placeholder="添加新任务. " class="input input-sm form-control">
                <span class="input-group-btn">
                    <a class="btn btn-sm btn-white" href="#modalTask" data-toggle="modal" data-type="1" data-whatever="创建任务"><i class="fa fa-plus"></i>创建任务</a>
                </span>
            </div> -->

            <ul id="toDoTasks" data-status="1" class="sortable-list connectList agile-list ui-sortable">
            	<%for(Tasks task : model.getByStatus(TasksStatus.toDo.value())){%>
            		<li data-id="<%=task.getId() %>" data-status="<%=task.getStatus() %>" data-type="<%=task.getType() %>" class="danger-element">
	                    <h3><%=task.getTitle() %></h3>
	                    <span><%=task.getContent() %></span>
	                    <div class="agile-detail">
	                        <a href="javascript:;" class="pull-right J_Remove"><i class="fa fa-trash-o"></i></a>
	                        <a href="#modalTask" data-toggle="modal" data-type="2" data-whatever="修改任务" class="pull-right J_Edit m-r-sm"><i class="fa fa-edit"></i></a>
	                        <i class="fa fa-clock-o"></i> <%=ParseHelper.ToDateString(task.getPubTime()) %>
	                    </div>
                	</li>
            	<%} %>
            </ul>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="ibox">
        <div class="ibox-content">
            <h3>进行中</h3>
            <!-- <p class="small"><i class="fa fa-hand-o-up"></i> 真的可以拖拽啊.</p> -->
            <hr/>
            <ul data-status="2" class="sortable-list connectList agile-list ui-sortable">
            	<%for(Tasks task : model.getByStatus(TasksStatus.InProcess.value())){%>
            		<li data-id="<%=task.getId() %>" data-status="<%=task.getStatus() %>" data-type="<%=task.getType() %>" class="warning-element">
	                    <h3><%=task.getTitle() %></h3>
	                    <span><%=task.getContent() %></span>
	                    <div class="agile-detail">
	                        <a href="javascript:;" class="pull-right J_Remove"><i class="fa fa-trash-o"></i></a>
	                        <a href="#modalTask" data-toggle="modal" data-type="2" data-whatever="修改任务" class="pull-right J_Edit m-r-sm"><i class="fa fa-edit"></i></a>
	                        <i class="fa fa-clock-o"></i> <%=ParseHelper.ToDateString(task.getPubTime()) %>
	                    </div>
                	</li>
            	<%} %>
            </ul>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="ibox">
        <div class="ibox-content">
            <h3>已完成</h3>
            <!-- <p class="small"><i class="fa fa-hand-o-up"></i> 确实可以拖拽.</p> -->
            <hr/>
            <ul data-status="3" class="sortable-list connectList agile-list ui-sortable">
            	<%for(Tasks task : model.getByStatus(TasksStatus.Completed.value())){%>
            		<li data-id="<%=task.getId() %>" data-status="<%=task.getStatus() %>" data-type="<%=task.getType() %>" class="success-element">
	                    <h3><%=task.getTitle() %></h3>
	                    <span><%=task.getContent() %></span>
	                    <div class="agile-detail">
                            <a href="javascript:;" class="pull-right btn btn-xs btn-white J_Hide">Hide</a>
	                        <i class="fa fa-clock-o"></i> <%=ParseHelper.ToDateString(task.getPubTime()) %>
	                    </div>
                	</li>
            	<%} %>
            </ul>
        </div>
    </div>
</div>