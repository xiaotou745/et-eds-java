<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OpenCityModel"%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						基本 <small>分类，查找</small>
					</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown"
							href="table_data_tables.html#"
							tppabs="http://www.zi-han.net/theme/hplus/table_data_tables.html#">
							<i class="fa fa-wrench"></i>
						</a> <a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="dataTables_length" id="DataTables_Table_0_length">
							 <input type="button" value="保存修改" class="searchBtn" id="btnSave" />
						</div>
					</div>
				</div>
				<div class="ibox-content">
					<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th width="%5">序号</th>
								<th>省编号</th>
								<th>省名称</th>
								<th>市编号</th>
								<th>市编名称</th>
								<th>区县编号</th>
								<th>区县名称</th>
								<th>是否开放<input type="checkbox" name="checkAll"
									id="selectAll" onclick="checkAll()" />全选/取消
								</th>
							</tr>
						</thead>
						<tbody>
							<%
								List<OpenCityModel> data=(List<OpenCityModel>)request.getAttribute("listData");
																			 for (int i = 0; i < data.size(); i++) {
							%>
							<tr>
								<td><%=i%></td>
								<td><%=data.get(i).getProvinceCode()%></td>
								<td><%=data.get(i).getProvinceName()%></td>
								<td><%=data.get(i).getCityCode()%></td>
								<td><%=data.get(i).getCityName()%></td>
								<td><%=data.get(i).getDistrictCode()%></td>
								<td><%=data.get(i).getDistrictName()%></td>
								<td>
									<%
										String checkstr=data.get(i).getIsPublic()==1?"checked=\"checked\"":"";
									%> <input type="checkbox" name="checkMenus"
									id="<%=data.get(i).getDistrictCode()%>" <%=checkstr%>
									value=<%=data.get(i).getDistrictCode()%> />
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
</div>
<script src="<%=basePath%>/js/plugins/jeditable/jquery.jeditable.js"></script>
<!-- Data Tables -->
<script src="<%=basePath%>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="<%=basePath%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<!-- Custom and plugin javascript -->
<script src="<%=basePath%>/js/hplus.js"></script>
<!-- Page-Level Scripts -->
<script>
	function checkAll() {
		var checkedOfAll = $("#selectAll").prop("checked");
		$("input[name='checkMenus']").prop("checked", checkedOfAll);
	}
</script>

<script>
	$("#btnSave").click(
			function() {
				if (confirm("确定要提交更改吗？")) {
					var OpenCityCodeList = "";
					var CloseCityCodeList = "";
					$("input[name='checkMenus']").each(
							function() {
								if ($(this).is(':checked')) {
									OpenCityCodeList = OpenCityCodeList
											+ $(this).val() + ",";
								} else {
									CloseCityCodeList = CloseCityCodeList
											+ $(this).val() + ",";
								}
							});
					if (OpenCityCodeList.length > 0)
						OpenCityCodeList = OpenCityCodeList.substring(0,
								OpenCityCodeList.length - 1);
					if (CloseCityCodeList.length > 0) {
						CloseCityCodeList = CloseCityCodeList.substring(0,
								CloseCityCodeList.length - 1);
					}
					var paramaters = {
						"openCityCodeList" : OpenCityCodeList,
						"closeCityCodeList" : CloseCityCodeList
					};
					var url = "<%=basePath%>/opencitymanager/modifyopencity";
					$.ajax({
						type : 'POST',
						url : url,
						data : paramaters,
						success : function(result) {
							
						   alert(result);	
							if (result.IsSuccess) {
								alert("设置成功!");
								//window.location.href = "/OpenCityManager/OpenCityManager";
							} else {
								alert(result.Message);
							}
						},
						error:function(result){
							alert(result.responseText);
						}
					});
				}
			});
</script>
