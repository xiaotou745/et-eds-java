<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.TaskDistributionConfig"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");	 
PagedResponse<TaskDistributionConfig> data=	(PagedResponse<TaskDistributionConfig>)request.getAttribute("listData");
List<TaskDistributionConfig> list = data.getResultList();
if(list == null){
	list = new ArrayList<TaskDistributionConfig>();
}

							%>
<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
							<th>距离(km)</th>
								<th>重量(kg)</th>
								<th>计价阶梯</th>
								<th>变量值</th>
								<th>是否基础值</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < list.size(); i++) { %>
							<tr>
								<td><%=list.get(i).getkM()%></td>
								<td><%=list.get(i).getkG()%></td>
								<td><%=list.get(i).getSteps()%></td>
									<td><%=list.get(i).getDistributionPrice()%>	</td>
								<td><%=list.get(i).getIsMaster()%></td>							
						
								<td>						
								<a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId() %>,<%=list.get(i).getIsMaster() %>)">修改</a>
								
								<%		
								
					if ( list.get(i).getIsMaster()==0)
					{			%>
								<a href="javascript:void(0)" onclick="del('<%=list.get(i).getId() %>')">删除</a>
							<%}	%>
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
    function modify(id,isMaster)
    {    	
    	 var paramaters = {
                   "id": id
               };
    	 var url = "<%=basePath%>/taskdistributionconfig/selectbyprimarykey";
    	 $.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {		    
	        	             	
	        	 	$('#txtEId').val(result.id);    	
	                $('#txtEKM').val(result.kM);
	                $('#txtEKG').val(result.kG);
	                $('#txtESteps').val(result.steps);   
	                $("#txtEDistributionPrice").val(result.distributionPrice);       
	                $("#txtEIsMaster").val(result.isMaster); 
	                $("#txtERemark").val(result.remark);    		
	              	$('#txtERemark').attr("disabled",false);     
	            	if(isMaster==0) 
	            		$('#txtERemark').attr("disabled",true)    	
	            		
	                $('#modifyConfig').modal('show');
	           }
	       });   	
		
    }
function del(id){		
	    
	    var paramaters = {
                "id": id
            };
       var url = "<%=basePath%>/taskdistributionconfig/del";
	   var la= layer.confirm('是否确认删除配置费？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	      alert("删除成功!");	      	
		             
		            	   window.location.href = "<%=basePath%>/taskdistributionconfig/list";		
		                              
		        	  
		           }
		       });
		});       	    
	}
	
    </script>
