<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Redis工具</title>
</head>
<body>
	<form action="redistoolsdo" method="post">
		键：<input type="text" id="key" name="key"> 
		查询类型：<input
			type="radio" value="1" name="sType" />模糊查询 <input type="radio"
			value="2" name="sType" />精准查询 
			<input type="submit" value="查询" />
	</form>
</body>
</html>