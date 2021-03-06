
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.TestUserRecord"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
%>

<div class="row">
	<div class="col-lg-12">
		<div class="wrapper wrapper-content animated fadeInUp">
			<div class="ibox">
				<div class="ibox-title">
					<h5>所有账号</h5>
					<div class="ibox-tools">
						<a href="javascript:showCreateNewLayer();"
							tppabs="http://www.zi-han.net/theme/hplus/projects.html"
							class="btn btn-primary btn-xs">创建新账号</a>
					</div>
				</div>
				<%
					if(request.getAttribute("testUsers") == null){
				%>
				<h3>当前没有测试账户</h3>
				<%
					}else{
				%>
				<div class="ibox-content">
					<div class="project-list">
						<table class="table table-hover" id="tableData">
							<tr>
								<th class="project-status">编号</th>
								<th class="project-title">测试手机号</th>
								<th class="project-completion">角色</th>
								<th class="project-actions">操作</th>
							</tr>
							<tbody>
								<%
									List<TestUserRecord> list = (List<TestUserRecord>)request.getAttribute("testUsers");
									for(TestUserRecord ts : list){
								%>
								<tr>
									<td class="project-status"><span
										class="label label-primary"><%=ts.getId()%></td>
									<td class="project-title"><%=ts.getPhoneNo()%></td>
									<td class="project-completion">-</td>
									<td class="project-actions"><a href="javascript:void(0)"
										onclick="showDeleteConfirmLayer(<%=ts.getPhoneNo()%>,this.parentNode.parentNode);"
										class="btn btn-white btn-sm" id="btnDelete"><i
											class="fa fa-folder"></i> 删除 </a> <a href="projects.html#"
										tppabs="http://www.zi-han.net/theme/hplus/projects.html#"
										class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>
											编辑 </a></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</div>
<!-- layer方法 -->
<script type="text/javascript">
	var html = "<div style='width: 250px;margin: 0 auto;padding:30px;'><label>测试手机号:</label><input name='txtPhone' id='txtPhone' type='text'></div>";
	html += "<p style='text-align:center;'><input value='确认' type='button' id='btnConfimAdd' onclick='addUserAjax()'></p>";
	
	function showCreateNewLayer(){
		//页面层
		layer.open({
		    type: 1,
		    skin: 'layui-layer-rim', //加上边框
		    area: ['420px', '240px'], //宽高
		    content: html
		});
	}
	
	function showDeleteConfirmLayer(phoneNo,tr){
		//询问框
		layer.confirm('确定要删除吗？', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
			deleteTestUserAjax(phoneNo,tr);
		}, function(){
		    layer.msg('删除失败', {shift: 6});
		});
	}
	
	function deleteTestUserAjax(phoneNo,tr){
		var paramaters = {"phoneNo": phoneNo};
        var url = "<%=basePath%>/testuser/delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result.responseCode==0) {
                	layer.msg('删除成功', {icon: 1});
                	tr.remove();
                } else {
                	layer.msg('删除失败', {shift: 6});
                }
            }
        });
	}
	
	function addUserAjax(){
		phoneNo = document.getElementById("txtPhone");
		var paramaters = { "phoneNo": phoneNo};
        var url = "<%=basePath%>/testuser/add";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	if (result.responseCode==0) {
                	layer.msg(result.message, {shift: 6});
                    window.reload();
                } else {
                	layer.msg(result.message, {shift: 6});
                }
            }
        });
        addUserTr(phoneNo);
	}
	
	function addUserTr(phoneNo){
/* 		var tr = document.createElement("tr");
		tr.innerHTML = "aaaaaaaaaaaaaaaaaaaaaaaaa";
		document.getElementById("tableData").appendChild(tr); */
		window.location.href = "/testuser/list";
	}
</script>