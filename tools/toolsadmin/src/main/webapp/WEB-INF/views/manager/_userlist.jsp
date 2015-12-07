<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.edaisong.toolsentity.domain.User"%>
<%@page import="com.edaisong.toolscore.util.ParseHelper" %>
<%@page import="com.edaisong.toolscore.util.StringUtils" %>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");

	List<User> allUsers = (List<User>) request.getAttribute("dataOfuserlist");
%>

<table class="table table-condensed table-bordered">
    <thead>
        <tr>
        	<th>登录名</th>
            <th>用户名</th>
            <th>注册时间</th>
            <th>禁用</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <% if (allUsers!=null && allUsers.size()>0)
        {
            for (User user : allUsers)
            { 
            	String createTime=ParseHelper.ToDateString(user.getCreateTime());
            	String roleIds = StringUtils.isEmpty(user.getRoleIds())?"":user.getRoleIds();
            %>
                <tr data-id="<%=user.getId() %>" data-roles="<%=roleIds %>" 
                	data-password="<%=user.getLoginPwd()%>">
                	<td name="loginname"><%=user.getLoginName() %></td>
                    <td name="username"><%=user.getUserName() %>
                    <%-- @foreach (var role in user.Roles)
                    {
                        <br /><code>@role</code>
                    } --%>
                    </td>
                    <td><%=createTime %></td>
                    <td name="disable"><%=(user.getIsDisable()?"√":"") %></td>
                    <td>
                        <a class="J_Remove m-r-sm">删除</a>
                        <a class="J_Disable m-r-sm" data-disable="<%=user.getIsDisable()%>"><%=(user.getIsDisable()==true?"解禁":"禁用") %></a>
                        <a class="" data-toggle="modal" href="#modalRoles">角色</a>
                    </td>
                </tr>
            <%}
        }
        else
        {%>
            <tr>
                <td colspan="5">
                    暂时没有用户明细
                </td>
            </tr>
        <%}%>
    </tbody>
</table>