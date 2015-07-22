<%@page import="java.util.ArrayList"%>
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
	        <div class="input-group" style="margin-bottom:5px;">
	            <input type="text" placeholder="请输入城市" class="input-sm form-control" id="InputCity" style="width:250px;height:34px;" value="<%=request.getAttribute("cityname")==null?"":request.getAttribute("cityname")%>"/>
	            <button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" id="btnSave" style="margin-left:3px;">保存修改</button>
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
								<th width="%5">序号</th>
								<th>省编号</th>
								<th>省名称</th>
								<th>市编号</th>
								<th>市编名称</th>
								<th>区县编号</th>
								<th>区县名称</th>
								<th>是否开放<input type="checkbox" name="checkAll" style="margin-top:2px;"
									id="selectAll" onclick="checkAll()" />全选/取消
								</th>
							</tr>
						</thead>
						<tbody>
							<%
								List<OpenCityModel> data=(List<OpenCityModel>)request.getAttribute("listData");
								 if(data==null){
									 data=new ArrayList<OpenCityModel>();
								 }
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
<script src="<%=basePath%>/js/hplus.js"></script>
<!-- Page-Level Scripts -->
<script>
	function checkAll() {
		var checkedOfAll = $("#selectAll").prop("checked");
		$("input[name='checkMenus']").prop("checked", checkedOfAll);
	}
</script>

<script>
  $("#btnSearch").click(function(){
	  var cityname=$("#InputCity").val();
	  window.location="<%=basePath%>/opencitymanager/opencitymanager?cityname=" +cityname;
  });
    //提交绑定
	$("#btnSave").click(
			function() {
				
				//询问框
				layer.confirm('您确认要提交修改吗？？', {
				    btn: ['确认','取消'], //按钮
				    shade: false //显示遮罩
				}, function(){
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
							if(result.responseCode==0){
								layer.alert('操作成功！', {
								    skin: 'layui-layer-molv' //样式类名
								});
								var cityname=$("#InputCity").val();
								window.location="<%=basePath%>/opencitymanager/opencitymanager?cityname=" +cityname;s
							}else
							{
								layer.alert('操作失败，请联系管理员！', {
								    skin: 'layui-layer-lan' //样式类名
								});
						     }
							
						},
						error:function(result){
							layer.alert('操作失败！', {
							    skin: 'layui-layer-lan' //样式类名
							});
						}
					});
				}, function(){
				    
				});
	    });
</script>
