<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
List<Account> data=(List<Account>)request.getAttribute("listData");
		 if(data==null){
			 data=new ArrayList<Account>();
		 }
	    for (int i = 0; i < data.size(); i++) {
	%>
	<tr>
		<td><%=data.get(i).getId()%></td>
		<td><%=data.get(i).getUsername()%></td>
		<td><%=data.get(i).getLoginname()%></td>
		<td>编辑</td>
	</tr>
	<%}
	%>  