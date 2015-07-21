<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%> 	
<form method="get" class="form-horizontal">

		<%
		List<GlobalConfigModel> data=	(List<GlobalConfigModel>)request.getAttribute("DataList");
		 for (int i = 0; i < data.size(); i++) {
			 %>  
			<div class="form-group">
				<label class="col-sm-2 control-label"><%=data.get(i).getKeyName() %>:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="<%=data.get(i).getValue() %>">
				</div>
			</div>
			<div class="hr-line-dashed"></div>
		 <%}%> 
</form>
