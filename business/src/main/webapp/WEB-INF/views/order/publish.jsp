<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@page import="com.edaisong.core.util.PropertyUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String basePath =PropertyUtils.getProperty("static.admin.url");
 %>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <input value="发单" type="button" id="btnAdd" onclick="funOk()"/>     
 
  
   //审核通过
   function funOk() {
 
        var url = "<%=basePath%>/order/publish";
        var paramaters = { "id": clientId };   
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {            
                    //window.location.href = "<%=basePath%>/clienter/list";               
            }
        });
    }

</body>
</html>