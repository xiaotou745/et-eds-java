<%@page import="com.edaisong.business.entity.UserContext" pageEncoding="utf-8"%>
<html>
<body>
<h2>Hello World!</h2>
<h3>
	欢迎您,
	<%
		UserContext context = UserContext.getCurrentContext(request);
		if(!context.isEmpty() && context.getBusiness()!=null){
			out.println(context.getBusiness().getPhoneno());
		}else{
			out.println("游客");
		}
	%>
</h3>
</body>
</html>
