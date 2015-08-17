<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("static.business.url");
 %>
 <script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
 
<form>
  <input value="发单" type="button" id="btnAdd" onclick="funOk()"/>     
 
</form>  
<script> 
   
   //审核通过
   function funOk() {
     
        var paramaters = { "id": 0 };        
        var url = "<%=basePath%>/order/add";
        alert(url);
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {            
             	alert(result);         
            },
            error:function(err)
            {
            	alert(err);
            }
        });
    }
</script>