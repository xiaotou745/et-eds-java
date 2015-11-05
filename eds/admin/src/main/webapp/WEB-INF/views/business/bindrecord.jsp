<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="com.edaisong.entity.ClienterBindOptionLog"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
List<ClienterBindOptionLog> data = (List<ClienterBindOptionLog>) request.getAttribute("listData");
%>

<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>操作人</th>
								<th>操作时间</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr class="info">
								<td><%=(i+1)%></td>
								<td><%=data.get(i).getOptname()%></td>
								<td><%=ParseHelper.ToDateString(data.get(i).getInserttime())%></td>
		                        <td><%=data.get(i).getRemark()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>