<%@page import="com.edaisong.business.common.ServerUtil"%>
<%@page import="com.edaisong.business.entity.UserContext"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
%>
<html>
<body>
	<h2>Hello World!</h2>
	<h3>
		欢迎您,
		<%
		UserContext context = UserContext.getCurrentContext(request);
		if (!context.isEmpty() && context.getBusiness() != null) {
			out.println(context.getBusiness().getPhoneno());
		} else {
			out.println("游客");
		}
	%>
	</h3>

	<%
		boolean isLogin = ServerUtil.checkIsLogin(request);
		if (isLogin) {
	%>
	<script type="text/javascript">
		var getCoordInDocument = function(e) {
			e = e || window.event;
			var x = e.pageX
					|| (e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
			var y = e.pageY
					|| (e.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
			return {
				'x' : x,
				'y' : y
			};
		};

		var isMove = false;
		var oldX = 0;
		var oldY = 0;
		var flag = false;
		document.onmousemove = function(e) {
			var pointer = getCoordInDocument(e);
			if (Math.abs(pointer.x - oldX) > 5
					|| Math.abs(pointer.y - oldY) > 5) {
				//鼠标已经移动,证明正在操作
				isMove = true;
			} else {
				isMove = false;
			}
			if (isMove) {
				var t=setTimeout("if(flag){window.location.href='<%=basePath%>/account/logoff';}flag=true;",3000);
			}
			oldX = pointer.x;
			oldY = pointer.y;
		}
	</script>
	<%
		}
	%>
</body>
</html>
