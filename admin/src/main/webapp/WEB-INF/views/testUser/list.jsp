<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.testuser.TestUserRecord"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试用户列表</title>
</head>
<body>
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
									for(TestUserRecord ts : list){%>
										<tr>
										<td class="project-status"><span
											class="label label-primary"><%=ts.getId() %> </td>
										<td class="project-title"><%=ts.getPhoneNo() %></td>
										<td class="project-completion">-</td>
										<td class="project-actions"><a href="projects.html#"
											tppabs="http://www.zi-han.net/theme/hplus/projects.html#"
											class="btn btn-white btn-sm"><i class="fa fa-folder"></i>
												查看 </a> <a href="projects.html#"
											tppabs="http://www.zi-han.net/theme/hplus/projects.html#"
											class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>
												编辑 </a></td>
									</tr>
									<%}%>
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
		$("#showCreateNewLayer").on('click',function(){
			/*$.layer({
			    type: 2,
			    shadeClose: true,
			    title: false,
			    closeBtn: [0, false],
			    shade: [0.8, '#000'],
			    border: [0],
			    offset: ['20px',''],
			    area: ['1000px', ($(window).height() - 50) +'px'],
			    iframe: {src: 'http://f2e.sentsin.com/chat'}
			}); */
			console.log('aa');
		});
	</script>
</body>
</html>