<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.TestUserRecord"%>

<%String basePath = request.getContextPath(); %>

<%-- <script src="<%=basePath%>/js/jquery-2.1.1.js"></script> --%>
<script src="http://res.layui.com/lay/lib/layer/src/layer.js?v=1.93"></script>
<div class="row">
	<div class="col-lg-12">
		<div class="wrapper wrapper-content animated fadeInUp">
			<div class="ibox">
				<div class="ibox-title">
					<h5>所有账号</h5>
					<div class="ibox-tools">
						<a href="javascript:;"
							tppabs="http://www.zi-han.net/theme/hplus/projects.html"
							class="btn btn-primary btn-xs" id="showCreateNewLayer">创建新账号</a>
					</div>
				</div>
				<div class="ibox-content">
					<!-- <div class="">
                                    <a onclick="fnClickAddRow();" href="javascript:void(0);" class="btn btn-primary ">添加行</a>
                                </div> -->
					<div class="project-list">
						<table class="table table-hover">
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
									<td class="project-actions"><a href="javascript:void(0);"
										class="btn btn-white btn-sm" id="btnDelete" onclick="showDeleteConfirmLayer();" tid="<%=ts.getId()%>"><i class="fa fa-folder"></i>
											删除 </a> <a href="projects.html#"
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
			</div>
		</div>
	</div>
</div>
<!-- layer方法 -->
<script type="text/javascript">
	var html = "<div style='width: 250px;margin: 0 auto;padding:30px;'><label>测试手机号:</label><input name='txtPhone' id='txtPhone' type='text'></div>";
	html += "<p style='text-align:center;'><input value='确认' type='button' id='btnConfimAdd'></p>";
	$("#showCreateNewLayer").on('click', function() {
		//页面层
		layer.open({
		    type: 1,
		    skin: 'layui-layer-rim', //加上边框
		    area: ['420px', '240px'], //宽高
		    content: html
		});
	});
	
	function showDeleteConfirmLayer(){
		//询问框
		layer.confirm('确定要删除吗？', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.msg('删除成功', {icon: 1});
		}, function(){
		    layer.msg('删除失败', {shift: 6});
		});
	}
	
	function addUserAjax(phoneNo){
		var paramaters = { "phoneNo": phoneNo};
        var url = "<%=basePath%>/testUser/add";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result.IsSuccess) {
                	layer.msg(result.Message, {shift: 6});
                    window.reload();
                } else {
                    //alert(result.Message);
                	layer.msg(result.Message, {shift: 6});
                }
            }
        });
	}
</script>