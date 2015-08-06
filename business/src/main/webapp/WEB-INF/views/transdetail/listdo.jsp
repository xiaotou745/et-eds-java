<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.Date"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.edaisong.core.common.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.BusinessBalanceRecord"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>

<%
PagedResponse<BusinessBalanceRecord> responsePageList = (PagedResponse<BusinessBalanceRecord>) request.getAttribute("result");
%>
=================================================
<%
	for(int i=0;i<responsePageList.getResultList().size();i++)
	{%>
		
		<%=responsePageList.getResultList().get(i).getRecordtype() %>
		<%=responsePageList.getResultList().get(i).getRelationno() %>
		<%=responsePageList.getResultList().get(i).getAmount() %>
		<%=responsePageList.getResultList().get(i).getBalance() %>
		<%=responsePageList.getResultList().get(i).getStatus() %>
		<%=responsePageList.getResultList().get(i).getOperatetime() %>
		<br/>
	<%}
%>