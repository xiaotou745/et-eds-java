<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%
	String basePath = request.getContextPath();
%>


    <link href="<%=basePath%>/css/admin.css" rel="stylesheet" />
 

    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/admin.js""></script>

<div style="height:500%"></div>
  
   <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <span class="">集团名称: </span>
                    <input id="txtGroupName" type="text" name="GroupName" />
                    <span class="">集团AppKey: </span> 
                    <input id="txtAppkey" type="text" name="Appkey" />
                    <input type="button" value="查询" class="searchBtn" id="btnSearch"  />
                    <input type="button" value="新增集团" class="searchBtn" id="btnAdd" /> 
                </td>
            </tr>
        </table>  
        
        <div id="groupList">     
        <jsp:include page="GroupManagerList.jsp"/>
        </div>

<div class="GroupAddDish">
    <div class="add-openbox add-form" id="GroupAddDivShow" style="width:500px">
        <h2>
            <p id="statusFin">新增集团</p>
        </h2>
     <fieldset>
            <div class="control-group">
                <label >集团名称</label> 
                    <input  name="txtaddGroupName" id="txtaddGroupName" type="text">
            </div>  
     </fieldset>
        <p class="btnbox">
                <input value="确认" type="button" id="btnAddGroup" class="yesBtn" />                
                <input value="关闭" type="button" class="J_closebox qxBtn" />
            </p>
    </div>
</div>
<div class="GroupUpdateDish">
    <div class="add-openbox add-form" id="GroupUpdateDivShow" style="width:500px">
        <h2>
            <p id="statusFin">修改集团</p>
        </h2>
        <fieldset>
            <input type="hidden" id="hiduGroupID" value="0"/>
            <div class="control-group">
                <label >集团名称</label> 
                <input  name="txtuGroupName" id="txtuGroupName" type="text">
            </div>  
        </fieldset>
        <p class="btnbox">
                <input value="确认" type="button" id="btnuGroup" class="yesBtn" />                
                <input value="关闭" type="button" class="J_closebox qxBtn" />
            </p>
    </div>
</div>	
	 
	<script>		
	var adminjs = new adminglass(); //实例化后台类	
	
	//查询集团
	 $(document).ready(function() {    	
	        //$("#btnSearch").on('click', Search())
	        
	        $("#btnAdd").on('click', function () {        	
	        	adminjs.openwinbox('#GroupAddDivShow');
	        });
	        //window.location.hash = '';
	    });
	    
	 $("#btnSearch").on('click', function () {
	        var txtGroupName = $("#txtGroupName").val(); //集团名称	        
	        var pars = { "groupname": txtGroupName };
	        alert(txtGroupName);
	        var url = "<%=basePath%>/group/selectlist";
	        $.ajax({
	            type: 'POST',
	            url: url,
	            data: pars,
	            success: function (result) {    
	            	
	            	$("#groupList").html(result);	            	
	                	
	            },
	            error:function(err)
	            {
	            	alert(err.responseText);
	            }
	            });
	        });    
    //添加集团
    $("#btnAddGroup").on('click', function () {
        var txtGroupName = $("#txtaddGroupName").val(); //集团名称
        if (txtGroupName == "") {
            alert("集团名称不能为空!");
            return;
        }
        var pars = { "groupname": txtGroupName };
        var url = "<%=basePath%>/group/addgroup";
        $.ajax({
            type: 'POST',
            url: url,
            data: pars,
            success: function (result) {         
            	if(result=="ok")
            	window.location.href = "<%=basePath%>/group/list";            	
            },
        error:function(err)
        {
        	alert(err.responseText);
        }
        });
    });    
   
    //关闭弹层
    $('.J_closebox').click(function () {
        adminjs.closewinbox('.add-openbox');
        return false;
    });   

    
    //修改集团
    $("#btnuGroup").on('click', function () {
        var txtGroupName = $("#txtuGroupName").val(); //集团名称
        var hiduGroupId = $("#hiduGroupID").val(); //集团id 
        if (txtGroupName == "") {
            alert("集团名称不能为空!");
            return;
        }    
        var pars = { "id":hiduGroupId,"groupname": txtGroupName };
        var url = "<%=basePath%>/group/updategroup";
        $.ajax({
            type: 'POST',
            url: url,
            data: pars,
            success: function (result) {
            	if(result=="ok")
                	window.location.href = "<%=basePath%>/group/list";  
            },
            error:function(err)
            {
            	alert(err.responseText);
            }
        });
    });
    </script>
    
	
