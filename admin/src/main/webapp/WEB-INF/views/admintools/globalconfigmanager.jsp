<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%
	String basePath = request.getContextPath();
%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				<!-- <button type="button" class="btn btn-w-m btn-primary" id="btnaddboxshow"
					style="margin-left: 3px;">添加</button> -->
				<a id="modal-477982" href="#addconfig" role="button" class="btn" data-toggle="modal">添加</a>
			</div>
		</div>

	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>KeyMark</th>
								<th>KeyName</th>
								<th>KeyValue</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%
								List<GlobalConfigModel> data=	(List<GlobalConfigModel>)request.getAttribute("DataList");
														 for (int i = 0; i < data.size(); i++) {
							%>
							<tr class="info">
								<td><%=data.get(i).getRemark()%></td>
								<td><%=data.get(i).getKeyName()%></td>
								<td><input type="text" id="show<%=data.get(i).getId()%>"
									value="<%=data.get(i).getValue()%>" disabled="true" /> <inupt
										type="hidden" id="hid<%=data.get(i).getId()%>"
										value="<%=data.get(i).getValue()%>" /></td>
								<td>
									<button class="btn" type="button"
										id="btne<%=data.get(i).getId()%>"
										onclick="EditConfig('<%=data.get(i).getId()%>')">编辑</button>
									<button class="btn" type="button"
										id="btns<%=data.get(i).getId()%>" name="save"
										onclick="SaveConfig('<%=data.get(i).getId()%>')">保存</button>
									<button class="btn" type="button"
										id="btnc<%=data.get(i).getId()%>" name="cancle"
										onclick="CancleConfig('<%=data.get(i).getId()%>')">取消</button>
								</td>
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

	<div id="addconfig" class="modal hide fade" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">添加全局公共配置</h3>
		</div>
		<div class="modal-body">
			<p>
				键名:<input type="text" value="" id="keyname" /><span style="color: red">键名一旦保存不可修改<span /><br /> 
				描述:<input type="text" value="" id="keyremark" /><span style="color: red">描述一旦保存不可修改<span /><br />
				值:<input type="text" value="" id="keyvalue" /><br /> 
				正则:<input type="text" value="" id="keyregx" /><br />
			</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true" id="boxcancle">取消</button>
			<button class="btn btn-primary">保存</button>
		</div>
	</div>
</div>
<script>
	$(function() {
		//隐藏保存 取消按钮
		$("[name='save']").each(function() {
			$(this).hide();
		});
		$("[name='cancle']").each(function() {
			$(this).hide();
		});
		$('#btnaddboxshow').click(function(){
			$('#addconfig').show();
			$('#mengban').show();
		});
		//添加框取消事件
		/* $('#boxcancle').click(function(){
			ClaenBox();
		}); */
	});
	//编辑事件
	function EditConfig(id) {
		$('#show' + id).removeAttr('disabled');
		var old = $('#hid' + id).attr('value');
		$('#show' + id).val(old);
		$('#btne' + id).hide();
		$('#btns' + id).show();
		$('#btnc' + id).show();
	};
	//保存事件
	function SaveConfig(id) {
		var newvalue = $('#show' + id).val();
		var oldvalue = $('#hid' + id).attr('value');
		console.log(newvalue);
		console.log(oldvalue);
		if (newvalue == oldvalue) {
			alert('未修改');
		} else {
			//ajax
			$.ajax({
				type : 'Post',
				url : '/admin/admintools/saveconfig',
				data : {
					id : id,
					configValue : newvalue
				},
				success : function(data) {
					if (data) {
						alert('保存成功');
						$('#show' + id).attr('disabled', 'true');
						$('#show' + id).attr('value', newvalue);
						$('#hid' + id).attr('value', newvalue);
						$('#btns' + id).hide();
						$('#btnc' + id).hide();
						$('#btne' + id).show();
					} else {
						alert('保存失败');
					}
				},
				error : function(myErrorData) {
					alert(myErrorData.responseText);
				}
			});
			$('#show' + id).attr('disabled', 'true');
			$('#btns' + id).hide();
			$('#btnc' + id).hide();
			$('#btne' + id).show();
		}

	};
	//取消事件
	function CancleConfig(id) {
		var old = $('#hid' + id).attr('value');
		$('#show' + id).val(old);
		$('#show' + id).attr('disabled', 'true');
		$('#btns' + id).hide();
		$('#btnc' + id).hide();
		$('#btne' + id).show();
	};
	
	function Addconfig(){
		//获取参数
		//ajax保存
		//清空box数据
		ClaenBox();
		
	};
	function ClaenBox(){
		$('#addconfig [type="text"]').each(function(){
			$(this).val('');
			$(this).attr('value','');
		});
		$('#addconfig').hide();
		$('#mengban').hide();
	}

</script>
<!-- <div class="modal-backdrop fade in" id="mengban" style="display:none;"></div> -->