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
	UserIndentity currentUser = UserIndentity.getIndentity(request);
	if(!currentUser.isLogin()){
		response.sendRedirect(basePath +"/");
		return;
	}
	List<Integer> lstUserMenuIds = MenuUtils.getCurrentUserPrivileges(currentUser.getUserId());
	String url = request.getAttribute("servletPath").toString();
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
            	boolean hasChild = false;
            	List<Menu> childs = MenuUtils.getChildMenus(menu.getId());
            	for(Menu child: childs){
            		if(lstUserMenuIds.contains(child.getId())){
            			hasChild = true;
            		}
            		if(child.getUrl().toLowerCase().equals(url)){
            			topClass = " class='active'";
            		}
            	}
            	if(!hasChild){
            		continue;
            	}
            	if(childs.size()>0){%>
            		<li <%=topClass%>>
	                    <a href=""><i class="<%=menu.getIcon()%>"></i> <span class="nav-label"><%=menu.getName() %></span><span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                    	<%for(Menu childMenu:childs){
	                    		if(!lstUserMenuIds.contains(childMenu.getId())){
	                    			continue;
	                    		}
	                    		String childClass = url.toLowerCase().equals(childMenu.getUrl().toLowerCase())?" class='active'":"";
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
