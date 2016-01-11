
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.toolsentity.common.PagedResponse"%>
<%@page import="com.edaisong.toolscore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolsentity.domain.ActionLog"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>查看详情</th>
			<th width="5%">编号</th>
			<th width="5%">用户ID</th>
			<th width="5%">用户名</th>
		    <th>请求地址</th>
		    <th width="8%">执行时间(ms)</th>
			<th width="8%">请求时间</th>
			<th width="8%">请求结束时间</th>
			<th>服务器地址</th>
			<th width="8%">请求类型</th>
			<th width="8%">客户端IP</th>
			<th width="8%">请求方式</th>
			<th>内容类型</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<ActionLog> data = (PagedResponse<ActionLog>) request.getAttribute("listData");
			List<ActionLog> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<ActionLog>();
			}
			String param="";
			String decryptMsg="";
			String exception="";
			String stackTrace="";
			String header="";
			String resultJson="";
			for (int i = 0; i < list.size(); i++) { 
				if(list.get(i).getParam()!=null){
					param=new String(Base64.encodeBase64(list.get(i).getParam().getBytes("UTF-8")));  
				}
				if(list.get(i).getDecryptMsg()!=null){
					decryptMsg=new String(Base64.encodeBase64(list.get(i).getDecryptMsg().getBytes("UTF-8")));  
				} 
				if(list.get(i).getException()!=null){
					exception=new String(Base64.encodeBase64(list.get(i).getException().getBytes("UTF-8")));  
				}
				if(list.get(i).getException()!=null){
					exception=new String(Base64.encodeBase64(list.get(i).getException().getBytes("UTF-8")));  
				}
				if(list.get(i).getStackTrace()!=null){
					stackTrace=new String(Base64.encodeBase64(list.get(i).getStackTrace().getBytes("UTF-8")));  
				}
				if(list.get(i).getHeader()!=null){
					header=new String(Base64.encodeBase64(list.get(i).getHeader().getBytes("UTF-8")));  
				}
				if(list.get(i).getResultJson()!=null){
					resultJson=new String(Base64.encodeBase64(list.get(i).getResultJson().getBytes("UTF-8")));  
				}
		%>
		<tr>
		<td>
			<input type="hidden" id="<%="param"+(i+1)%>" value="<%=param%>"/>
			<input type="hidden" id="<%="decryptMsg"+(i+1)%>" value="<%=decryptMsg%>"/>
			<input type="hidden" id="<%="exception"+(i+1)%>" value="<%=exception%>"/>
			<input type="hidden" id="<%="stackTrace"+(i+1)%>" value="<%=stackTrace%>"/>
			<input type="hidden" id="<%="header"+(i+1)%>" value="<%=header%>"/>
			<input type="hidden" id="<%="resultJson"+(i+1)%>" value="<%=resultJson%>"/>										
			<a href="javascript:void(0)" onclick="showdetail(<%=(i+1)%>)">详情</a></td>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getUserID()%></td>
			<td><%=list.get(i).getUserName()%></td>
		    <td>   
		    <div style="width:500px;text-align:left;" class="breakline">
		    	请求地址:<br/><%=list.get(i).getRequestUrl()%><br/>
		    	方法名称:<br/><%=list.get(i).getMethodName()%></div>
		   </td>
		    <td><%=list.get(i).getExecuteTime()%></td>
			<td><%=list.get(i).getRequestTime()%></td>
			<td><%=list.get(i).getRequestEndTime()%></td>
			<td><%=list.get(i).getAppServer()%></td>
			<td><%=list.get(i).getRequestType()==0?"页面请求":"ajax请求"%></td>
			<td><%=list.get(i).getClientIp()%></td>
			<td><%=list.get(i).getRequestMethod()%></td>
			<td><%=list.get(i).getContentType()%></td>
			
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>