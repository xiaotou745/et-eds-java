<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.QuartzServiceModel"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String basePath = PropertyUtils.getProperty("java.admin.url");

	List<QuartzServiceModel> listData = (List<QuartzServiceModel>) request
	.getAttribute("listData");
%>


<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 60px;">编号</th>
			<th style="width: 150px;">服务名称</th>
			<th style="width: 200px;">状态</th>
		</tr>
	</thead>
	<tbody>
		<%for(QuartzServiceModel item:listData)
		{%>
		<tr>
			<td><%=item.getId()%></td>
			<td><%=item.getName()%></td>
			<td><a href="javascript:void(0)" onclick="jss.updateStatus(<%=item.getId()%>)"><span id="status_<%=item.getId()%>"><%=item.getIsStart()==1?"启动":"停止"%></span></a></td>
		</tr>
		<%}%>
		<tr>
	</tbody>
</table>


<script>		
	var jss={
			search:function(currentPage){	
                 //参数不能为""值
				 var paramaters = { 
						m:Math.roundm()
						 };        
			        var url = "<%=basePath%>/service/listdo";
			$.ajax({
				type : 'POST',
				url : url,
				data : paramaters,
				success : function(result) {
					$("#content").html(result);
				}
			});
		},
		updateStatus:function(id){
			if(!window.confirm("是否确认更改?")) return;
			var objStatus= $("#status_"+id);
			var status= objStatus.html()=="启动"?0:1;//这里取反，启动就禁用，禁用就更新启动
			
			$.ajax({
				type : 'get',
				url : "<%=basePath%>/service/updatestatus",
				data : {id:id,status:status},
				success : function(d) {
					if(d==1){
						var updateHtml=objStatus.html()=="启动"?"停止":"启动";
						objStatus.html(updateHtml);
					}	
				}
			});
		}
	}

	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
</script>