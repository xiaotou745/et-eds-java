
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="com.edaisong.entity.ClienterForzen"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.Mark" %>
<%String basePath = PropertyUtils.getProperty("static.admin.url");%>
<%PagedResponse<Mark> data = (PagedResponse<Mark>) request.getAttribute("listData");%>
<% if(data.getResultList()==null||data.getResultList().size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">编号</th>
						<th width="%5">名称</th>
						<th width="%5">标签类型</th>
						<th width="%5">数量</th>
						<th width="%5">创建时间</th>
						<th width="%5">状态</th>
						<th width="%5">操作人</th>
						<th width="%5">操作时间</th>
						<th width="%5">备注</th>
						<th width="%5">操作</th>	
				</tr>
			</thead>
			<tbody>                           
		<%List<Mark> list = data.getResultList();
           for (int i = 0; i < list.size(); i++) {%>  
			 <tr> 
			    <td><%=i+1 %></td>
                <td><%=list.get(i).getTagName() %></td>
				<td><%=list.get(i).getTagType()==0?"门店":"骑士" %></td>
				<td><%=list.get(i).getBindquantity() %></td>
				<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime()) %></td>
                <td><%=list.get(i).getIsenable()==0?"禁止":"启动" %></td>
                <td><%=list.get(i).getModifyname() %></td>
				<td><%=ParseHelper.ToDateString(list.get(i).getModifytime()) %></td>
                <td><%=list.get(i).getRemark() %></td>
				<td>
					<a href="javascript:showEditTag(<%=list.get(i).getId()%>,<%=list.get(i).getIsenable()%>,'<%=list.get(i).getTagName()%>',<%=list.get(i).getTagType() %>,'<%=list.get(i).getRemark()%>')">修改</a>
					<%if(list.get(i).getIsenable()==1){%>
					<a href="javascript:modifyMarkStatus(<%=list.get(i).getId()%>,0)">禁止</a>
					<%}else{%> 
					<a href="javascript:modifyMarkStatus(<%=list.get(i).getId()%>,1)">启动</a>
					<%}%>
				</td>				
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script>
//标签编辑弹窗
	function showEditTag(id,isEnable,tagName,tagType,remark){
		$("#hdTagId").val(id);
		$("#editTagName").val(tagName); 
		$("#editTagType").val(tagType);
		$("#editRemark").val(remark);
		if (isEnable == 1) {
            
            $("#rIsEnableY").prop("checked",true);
        }
        else {
            
            $("#rIsEnableN").prop("checked",true);
        }
		$("#hdOperateType").val(1);
		$("#oldTagName").val(tagName); 
		$("#oldTagType").val(tagType);
		$("#oldIsEnable").val(isEnable);
		$("#oldRemark").val(remark);
		$('#showEditTag').modal('show');
		
	}
	//修改标签状态
	   function modifyMarkStatus(id,isEnable){
		   var confirmMsg="";
		   if(isEnable==0)
			   {
			   	confirmMsg="确认禁止此标签?";
			   }
		   else
			   {
			   	confirmMsg="确认启动此标签?";
			   }
		   if (!confirm(confirmMsg)) {
	            return;
	        }
		   var paramaters = {
		    	   "id":id,
		    	   "isenable":isEnable,
	           };
	      var url = "<%=basePath%>/mark/modifyMarkStatus";
		   $.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {   			            
	        	   alert(result.message);
	               if (result.responseCode > 0) {
	                   window.location.href = "<%=basePath%>/mark/list";
	               }               
	           }
	       });
	   }


</script>

