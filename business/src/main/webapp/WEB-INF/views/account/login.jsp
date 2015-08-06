<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath(); %>
<form method="POST" action="<%=basePath %>/account/login">
	手机号:<input type="text" name="phoneNo" /><br/>
	密码:<input type="password" name="password" /><br/>
	<input type="submit" value="登录" />
</form>