
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
	String basePath =PropertyUtils.getProperty("java.admin.url");
	String adminUrl =PropertyUtils.getProperty("net.admin.url");
	IAuthorityMenuClassService menuService = SpringBeanHelper.getCustomBeanByType(IAuthorityMenuClassService.class);
	
	UserContext context = UserContext.getCurrentContext(request);
	if(context == null){
		response.sendRedirect(basePath);
		return;
	}
	List<MenuEntity> menuList = menuService.getMenuListByUserID(context.getId());
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
								class="font-bold"><%=context.getLoginName() %></strong>
						</span> <span class="text-muted text-xs block"><%=context.getUserName() %> <b
								class="caret"></b></span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="<%=basePath%>/account/changepwd">修改密码</a></li>
<!-- 						<li><a href="contacts.html">联系方式</a></li> -->
<!-- 						<li><a href="mailbox.html">消息</a></li> -->
						<li class="divider"></li>
						<li><a href="<%=basePath%>/account/logoff">注销</a></li>
					</ul>
				</div>
				<div class="logo-element">IN+</div>
			</li>
			<li><a href="<%=adminUrl%>/HomeCount/Index">			
				<i class="fa fa-th-large"></i>
				<span class="nav-label">后台首页</span> <span class="fa arrow"></span></a>
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
									
									//此时表示这个页面需要跳转到老后台
									if(submenu.getUrl()!=null&&
									!submenu.getUrl().isEmpty()&&
									context.getLoginFrom()==0){
										%>
										<li><a href="<%=adminUrl+submenu.getUrl()%>"><%=submenu.getMenuname()%></a></li>
										<%
									}else{
									%>
									<li <%=viewPath.equals(submenu.getJavaUrl().substring(1))?"class='active'":""%>><a href="<%=basePath+submenu.getJavaUrl()%>"><%=submenu.getMenuname()%></a></li>

									<%	}
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
