<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%
PagedResponse<GlobalConfigModel> responsePageList=	(PagedResponse<GlobalConfigModel>)request.getAttribute("listData");
List<GlobalConfigModel> data = responsePageList.getResultList();
if(data == null){
	data = new ArrayList<GlobalConfigModel>();
}

							%>
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
							<%for (int i = 0; i < data.size(); i++) { %>
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
										onclick="SaveConfig('<%=data.get(i).getId()%>','<%=data.get(i).getKeyName()%>')">保存</button>
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
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
		
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
	 $('#boxcancle').click(function(){
		ClaenBox();
	}); 
	//添加新的配置
	$('#btnaddconfig').click(function(){
		 Addconfig();
	});
});
</script>