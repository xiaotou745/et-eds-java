<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>


<table border="0" cellspacing="0" cellpModifying="0">
	<tr>
		<td><span class="">集团名称: </span> <input id="txtGroupBusinessName"
			type="tel" name="txtGroupBusinessName" />&nbsp;&nbsp; <input
			type="submit" value="查询" class="searchBtn" id="btnSearch" />&nbsp;&nbsp;
			<input type="button" value="添加集团" class="searchBtn"
			id="btnModifyGroupBusiness" /></td>
	</tr>
</table>


<div>
    <div class="add-openbox Modify-form" id="addGroupBusiness" style="width:500px">
        <h2>
            <p id="statusFin">添加集团</p>
        </h2>
     <fieldset>
            <div class="control-group">
                <label >集团名称：</label> 
                    <input  name="txtAddGroupBusinessName" id="txtAddGroupBusinessName" type="text">
            </div>  
            <div class="control-group">
                <label >登陆账号：</label> 
                    <input  name="txtAddLoginName" id="txtAddLoginName" type="text">
            </div> 
            <div class="control-group">
                <label >密码：</label> 
                    <input  name="txtAddPassword" id="txtAddPassword" type="text">
            </div> 
            <div class="control-group">
                <label >确认密码：</label> 
                    <input  name="txtAddConfirmPassword" id="txtAddConfirmPassword" type="text">
            </div> 
     </fieldset>
        <p class="btnbox">
                <input value="确认" type="button" id="btnAddGroupBusiness" class="yesBtn" />                
                <input value="关闭" type="button" class="J_closebox qxBtn" />
            </p>
    </div>
</div>

<div>
    <div class="add-openbox Modify-form" id="modifyGroupBusiness" style="width:500px">
        <h2>
            <p id="statusFin">修改集团</p>
        </h2>
     <fieldset>
            <div class="control-group">
                <label >集团名称：</label> 
                    <input  name="txtModifyGroupBusinessName" id="txtModifyGroupBusinessName" type="text">
            </div>  
            <div class="control-group">
                <label >登陆账号：</label> 
                    <input  name="txtModifyLoginName" id="txtModifyLoginName" type="text">
            </div> 
            <div class="control-group">
                <label >密码：</label> 
                    <input  name="txtModifyPassword" id="txtModifyPassword" type="text">
            </div> 
            <div class="control-group">
                <label >确认密码：</label> 
                    <input  name="txtModifyConfirmPassword" id="txtModifyConfirmPassword" type="text">
            </div> 
            <div>
            	<h3>操作记录</h3>
            	
            </div>
     </fieldset>
        <p class="btnbox">
                <input value="确认" type="button" id="btnModifyGroupBusiness" class="yesBtn" />                
                <input value="关闭" type="button" class="J_closebox qxBtn" />
            </p>
    </div>
</div>