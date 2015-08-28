
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java"
	import="com.edaisong.api.service.inter.IAuthorityMenuClassService"%>
<%@ page language="java"
	import="com.edaisong.core.util.SpringBeanHelper"%>
<%@ page language="java" import="com.edaisong.entity.MenuEntity"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%
	String basePath =PropertyUtils.getProperty("static.admin.url");
	IAuthorityMenuClassService menuService = SpringBeanHelper.getCustomBeanByType(IAuthorityMenuClassService.class);

	List<MenuEntity> menuList = menuService.getMenuListByUserID(UserContext.getCurrentContext(request).getAccount().getId());
    String viewPath =request.getAttribute("viewPath").toString();
    		
%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span> <img alt="image" class="img-circle"
						src="<%=basePath%>/img/profile_small.jpg" />
					</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
						class="clear"> <span class="block m-t-xs"> <strong
								class="font-bold">admin</strong>
						</span> <span class="text-muted text-xs block">管理员 <b
								class="caret"></b></span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="profile.html">个人信息</a></li>
						<li><a href="contacts.html">联系方式</a></li>
						<li><a href="mailbox.html">消息</a></li>
						<li class="divider"></li>
						<li><a href="<%=basePath %>/account/logoff">注销</a></li>
					</ul>
				</div>
				<div class="logo-element">IN+</div>
			</li>

			<%
			        String parentClass="";
					for (MenuEntity menu : menuList) {
						if (menu.getParid() == 0) {
							parentClass="";
							List<MenuEntity> data=new ArrayList<>();
							for (MenuEntity itemMenu : menuList) {
								if (itemMenu.getParid() == menu.getMenuid()
										&& itemMenu.getIsbutton() == false) {
									if(viewPath.equals(itemMenu.getJavaUrl().substring(1))){
										parentClass=" class='active' ";
									}
									data.add(itemMenu);
								}
							}
							if(data.size()>0){
								%>
								<li <%=parentClass%>><a href="#"><i class="fa fa-th-large"></i>
										<span class="nav-label"><%=menu.getMenuname()%></span> <span
										class="fa arrow"></span></a>
									<ul class="nav nav-second-level">
										<%
								for (MenuEntity submenu : data) {
									%>
									<li <%=viewPath.equals(submenu.getJavaUrl().substring(1))?"class='active'":""%>><a href="<%=basePath+submenu.getJavaUrl()%>"><%=submenu.getMenuname()%></a></li>

									<%	
								}
								%>
								</ul></li>
							<%
							}
							
					}
				}	
			%>
		</ul>
	</div>
</nav>
