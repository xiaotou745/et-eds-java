<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.TaskDistribution"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");	 
PagedResponse<TaskDistribution> data=	(PagedResponse<TaskDistribution>)request.getAttribute("listData");
List<TaskDistribution> list = data.getResultList();
if(list == null){
	list = new ArrayList<TaskDistribution>();
}

							%>
<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
							<th>编号</th>
								<th>名称</th>
								<th>描述</th>
								<th>使用账户(个)</th>
								<th>修改时间</th>
								<th>操作人</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < list.size(); i++) { %>
							<tr>
								<td><%=list.get(i).getId()%></td>
								<td><%=list.get(i).getName()%></td>
								<td><%=list.get(i).getRemark()%></td>
								<td><%=list.get(i).getUserCount()%></td>
									<td><%=ParseHelper.ToDateString(list.get(i).getUpdatetime())%>	</td>
								<td><%=list.get(i).getUpdatename()%></td>							
						
								<td>						
								<a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId() %>)">修改</a>
   							   <a href="<%=basePath%>/taskdistribution/taskdistributionbindlist?taskDistributionId=<%=list.get(i).getId()%>">配置规则</a>
								
					
								</td>
							</tr>

							<%
								}
							%>
						</tbody>
					</table>
		<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
					
<script>				
function modify(id)
{    		
//	alert(id);
// 	alert(name);
// 	alert(remark);
// 	 	$('#txtEId').val(id);    	
//         $('#txtEName').val(name);
//         $('#txtERemark').val(remark);      
//     	alert(id);
//     	alert(name);
//     	alert(remark);
//         $('#modifyConfig').modal('show');
	
	 var paramaters = {
             "id": id
         };
	 var url = "<%=basePath%>/taskdistribution/selectbyprimarykey";
	 $.ajax({
         type: 'POST',
         url: url,
         data: paramaters,
         success: function (result) {		    
      	             	
      	 	  $('#txtEId').val(result.id);    	
              $('#txtEName').val(result.name);              
              $('#txtERemark').val(result.remark);              
              $('#modifyConfig').modal('show');
         }
     });  
}

    </script>
