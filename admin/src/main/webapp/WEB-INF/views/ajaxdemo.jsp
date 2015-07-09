<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
	Hello   ${data}<br/>
	    <c:forEach items="${listdata}" var="node">  
        姓名：<c:out value="${node.key}"></c:out>  
        住址：<c:out value="${node.value}"></c:out>  
        <br/>  
   </c:forEach>  
