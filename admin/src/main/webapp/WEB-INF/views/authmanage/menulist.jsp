<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.AuthorityMenuClass"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
	List<AuthorityMenuClass> data = (List<AuthorityMenuClass>) request.getAttribute("listData");
	AuthorityMenuClass currentMenu = (AuthorityMenuClass) request.getAttribute("currentMenu");
%>

<div class="SearchMd">
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><%=currentMenu==null?"一级菜单":currentMenu.getMenuname()%></td>
			<td><input type="button" value="新增菜单" onclick="addNewMenu()" /></td>
		</tr>
	</table>
</div>


<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content">
				<table
					class="table table-striped table-bordered table-hover dataTables-example">
					<%
						if(currentMenu==null){//如果是一级菜单
					%>
					<thead>
						<tr>
							<th>编号</th>
							<th>菜单名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < data.size(); i++) {
						%>
						<tr class="info">
							<td><%=data.get(i).getId()%></td>
							<td><%=ParseHelper.ShowString(data.get(i).getMenuname())%></td>
							<td>
								<button type="button" class="btn btn-default btn-sm">修改</button>
								<button type="button" class="btn btn-default btn-sm">查看子菜单</button>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
					<%
						//如果是子菜单
					%>
					<%
						}else{
					%>
					<thead>
						<tr>
							<th>编号</th>
							<th>菜单名称</th>
							<th>旧版后台菜单地址</th>
							<th>新版后台菜单地址</th>
							<th>父级菜单</th>
							<th>是否按钮</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < data.size(); i++) {
						%>
						<tr class="info">
							<td><%=data.get(i).getId()%></td>
							<td><%=ParseHelper.ShowString(data.get(i).getMenuname())%></td>
							<td><%=data.get(i).getUrl()%></td>
							<td><%=data.get(i).getJavaUrl()%></td>
							<td><%=data.get(i).getParid()%></td>
							<td><%=data.get(i).getIsbutton()%></td>
							<td>
								<button type="button" class="btn btn-default btn-sm">修改</button>
								<button type="button" class="btn btn-default btn-sm">添加按钮</button>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>

					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- 新增菜单 -->
<form method="POST" action="#" class="form-horizontal" id="searchForm">
<input type="hidden" name="curId" value="<%=currentMenu == null ? 0 : currentMenu.getId() %>" />
<div tabindex="-1" class="modal inmodal" id="addNewMenu"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增菜单</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>菜单名称：</label> <input name="menuName" id="menuName"
								 type="text"/>
						</div>
					</fieldset>
				</div>
				<%
					if(currentMenu != null){
				%>
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>旧版后台菜单url：</label> <input name="url" id="url"
								disabled="disabled" type="text"/>
						</div>
					</fieldset>
				</div>
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>新版后台菜单url：</label> <input name="javaUrl" id="javaUrl"
								disabled="disabled" type="text"/>
						</div>
					</fieldset>
				</div>
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>是否是按钮：</label> <input type="radio" name="isButton" value="1" checked="checked" />是
							<input type="radio" name="isButton" value="0" checked="checked" />否
						</div>
					</fieldset>
				</div>
				<%} %>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="save()">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
</form>

<script>
	function addNewMenu(){
		$('#addNewMenu').modal('show');
	}
	
	function save(){
		var menuName = $("#menuName").val();
		if(menuName == ''){
			alert("请输入菜单名称!");
			return;
		}
		var data=$("#searchForm").serialize();
		$.post("<%=basePath%>/authmanage/addNewMenu",data,function(d){
			alert(d);
			location.reload();
		});
	}
</script>

