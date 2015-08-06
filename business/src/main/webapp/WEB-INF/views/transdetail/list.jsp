<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>

<%	
String basePath =PropertyUtils.getProperty("static.admin.url");
%>
<form>
日期:<input type="text" name="txtstartDate" id="startDate">到<input type="text" name="txtendDate" id="endDate"><br/>
交易类型:<select id="transTypeSelect">
<option value="0">全部</option>
<option value="1">发布订单</option>
<option value="2">取消订单</option>
<option value="8">订单菜品费</option>
<option value="9">充值</option>
<option value="11">手续费</option>
</select>
<select id="numTypeSelect">
<option value="0">单号类型</option>
<option value="1">订单号</option>
<option value="2">流水号</option>
</select>
<input type="text" name="txtnumString" id="numString" /> 
<button type="button" id="btnSerach" >查询</button>
</form>
<div id="dataList">
</div>