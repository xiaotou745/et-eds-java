<%@page import="com.edaisong.toolscore.util.JsonUtil"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" import="com.edaisong.toolscore.util.SpringBeanHelper"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%@page import="com.edaisong.toolsadmin.common.UserIndentity" %>
<%@page import="com.edaisong.toolsadmin.common.MenuUtils" %>
<%@page import="com.edaisong.toolsentity.domain.Menu" %>
<%@page import="java.util.stream.Collectors" %>

<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
	if(!UserIndentity.getIndentity(request).isLogin()){
		response.sendRedirect(basePath +"/");
		return;
	}
	String viewPath = request.getAttribute("viewPath").toString();
	List<Menu> topMenus = MenuUtils.getTopMenus();
%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span> <img alt="image" class="img-circle" src="<%=basePath%>/img/profile_small.jpg" />
					</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span class="clear"> <span
							class="block m-t-xs"> <strong class="font-bold">wangyuchuan</strong>
						</span> <span class="text-muted text-xs block">test<b class="caret"></b></span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="<%=basePath%>/account/changepwd">修改密码</a></li>
						<li class="divider"></li>
						<li><a href="<%=basePath%>/account/logoff">注销</a></li>
					</ul>
				</div>
				<div class="logo-element">IN+</div>
			</li>
            <%for(Menu menu:topMenus){
            	String topClass="";
            	List<Menu> childs = MenuUtils.getChildMenus(menu.getId());
            	for(Menu child: childs){
            		if(child.getViewPath().toLowerCase().equals(viewPath)){
            			topClass = " class='active'";
            		}
            	}
            	if(childs.size()>0){%>
            		<li <%=topClass%>>
	                    <a href=""><i class="<%=menu.getIcon()%>"></i> <span class="nav-label"><%=menu.getName() %></span><span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                    	<%for(Menu childMenu:childs){
	                    		String childClass = viewPath.toLowerCase().equals(childMenu.getViewPath().toLowerCase())?" class='active'":"";
	                    	%>
	                    		<li <%=childClass%>><a href="<%=basePath + childMenu.getUrl()%>"><%=childMenu.getName() %></a></li>
	                    	<%}%>
	                    </ul>
                	</li>
            	<%}
            }%>
		</ul>
	</div>
</nav>
