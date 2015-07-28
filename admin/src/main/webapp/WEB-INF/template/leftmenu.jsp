
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java"
	import="com.edaisong.api.service.inter.IAuthorityMenuClassService"%>
<%@ page language="java"
	import="com.edaisong.api.business.SqlSessionFactoryPool"%>
<%@ page language="java"
	import="com.edaisong.entity.resp.AuthorityMenuResp"%>
<%@ page language="java"
	import="com.edaisong.entity.req.AuthorityMenuReq"%>
<%@ page language="java" import="com.edaisong.entity.MenuEntity"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%
	String basePath = request.getContextPath();
	IAuthorityMenuClassService menuService = SqlSessionFactoryPool
			.getCustomBeanByType(IAuthorityMenuClassService.class);

	AuthorityMenuReq req = new AuthorityMenuReq();
	req.setAccountId("1");
	AuthorityMenuResp resp = menuService.getMenuListByUserID(req);
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
						<li><a href="login.html">注销</a></li>
					</ul>
				</div>
				<div class="logo-element">IN+</div>
			</li>

			<%
				if (resp != null) {
					for (MenuEntity menu : resp.getMenuList()) {
						if (menu.getParid() == 0) {
							List<MenuEntity> data=new ArrayList<>();
							for (MenuEntity submenu : resp.getMenuList()) {
								if (submenu.getParid() == menu.getMenuid()
										&& submenu.getIsbutton() == false
										) {
									data.add(submenu);
								}
							}
							if(data.size()>0){
								%>
								<li class=""><a href="#"><i class="fa fa-th-large"></i>
										<span class="nav-label"><%=menu.getMenuname()%></span> <span
										class="fa arrow"></span></a>
									<ul class="nav nav-second-level">
										<%
								for (MenuEntity submenu : data) {
									%>
									<li class=""><a href="<%=basePath+submenu.getJavaUrl()%>"><%=submenu.getMenuname()%></a></li>

									<%	
								}
								%>
								</ul></li>
							<%
							}
							
					}
				}	
			}
			%>
		</ul>
	</div>
</nav>
