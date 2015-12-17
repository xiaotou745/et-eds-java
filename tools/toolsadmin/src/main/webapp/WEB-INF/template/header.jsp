<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");
%>
<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
    </div>
    <ul class="nav navbar-top-links navbar-right">
        <li>
            <span class="m-r-sm text-muted welcome-message">欢迎您使用e代送工具后台管理系统.</span>
        </li>
        <li>
            <a href="<%=basePath %>/account/logoff">
                <i class="fa fa-sign-out"></i> 注销
            </a>
        </li>
    </ul>
</nav>