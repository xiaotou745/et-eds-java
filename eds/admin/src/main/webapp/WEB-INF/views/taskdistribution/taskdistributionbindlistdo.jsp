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
int taskDistributionId = (int) request
.getAttribute("taskDistributionId");

PagedResponse<TaskDistributionConfig> data=	(PagedResponse<TaskDistributionConfig>)request.getAttribute("listData");
List<TaskDistributionConfig> list = data.getResultList();
if(list == null){
	list = new ArrayList<TaskDistributionConfig>();
}

							%>
<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th></th>
							<th>距离(千米)</th>
								<th>重量(公斤)</th>
								<th>阶梯增量(距离/重量)</th>
								<th>配送费(元)</th>							
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < list.size(); i++) {
								int IsMaster=  list.get(i).getIsMaster();
								double km=list.get(i).getkM();
								double kg=list.get(i).getkG();
								double steps=list.get(i).getSteps();
								
								 String strMaster ="";
								 if(IsMaster==1)
									 strMaster="基础";
								 else
									 strMaster="超过";
								 
								 String strKm=String.valueOf(km);	
								 if(strKm.equals("") ||strKm.equals(" ") || strKm.equals("0") ||strKm.equals("0.0"))
									 strKm="--";		
								 
								 String strKg=String.valueOf(kg);	
								 if(strKg.equals("") ||strKg.equals(" ") || strKg.equals("0") ||strKg.equals("0.0"))
									 strKg="--";	
								 
								 String strSteps=String.valueOf(steps);	
								 if(strSteps.equals("") ||strSteps.equals(" ") || strSteps.equals("0") ||strSteps.equals("0.0"))
									 strSteps="";
								%>
					
							<tr>
							<td><%=strMaster%></td>	
								<td><%=strKm%></td>
									<td><%=strKg%></td>		
										<td><%=strSteps%></td>					
							
									<td><%=list.get(i).getDistributionPrice()%>	</td>
														
						
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
    	 var url = "<%=basePath%>/taskdistribution/selectbyprimarykeyconfig";
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
       var url = "<%=basePath%>/taskdistribution/delconfig";
	   var la= layer.confirm('是否确认删除规则？', {
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
		            
		        	      window.location.href = "<%=basePath%>/taskdistribution/taskdistributionbindlist?taskDistributionId="+<%=taskDistributionId%>;
		                              
		        	  
		           }
		       });
		});       	    
	}
	
    </script>
