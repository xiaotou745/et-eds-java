<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.TaskDistributionConfig"%>
<%@page import="java.util.List"%>
<%
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
									<td><%=list.get(i).getDistributionPrice()%>	</td>
								<td><%=list.get(i).getIsMaster()%></td>							
						
								<td>								
								<a href="javascript:void(0)" onclick="modify('<%=list.get(i).getId() %>','<%=list.get(i).getkM() %>','<%=list.get(i).getkG() %>','<%=list.get(i).getDistributionPrice() %>')">修改</a>	
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
<script type="text/javascript">				
    function modify(id,km,kg,distributionPrice) {
    	$('#txtEId').val(id);
        $('#txtEKM').val(km);
        $('#txtEKG').val(kg);
        $("#txtEDistributionPrice").val(distributionPrice);
        $('#modifyConfig').modal('show');
    }
    </script>
