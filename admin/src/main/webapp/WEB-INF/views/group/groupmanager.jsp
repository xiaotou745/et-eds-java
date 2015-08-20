<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("static.admin.url");
%>
 <script type="text/javascript" src="<%=basePath%>/js/admin.js"></script>
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
        <jsp:include page="groupmanagerlist.jsp"/>
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
<div class="GroupAppConfig">
    <div class="add-openbox add-form" id="GroupAppConfig" style="width:500px">
        <h2>
            <p id="statusFinApp">设置集团AppConfig</p>
        </h2>
        <fieldset>
            <input type="hidden" id="HidGroupID" value="0"/>
            <div class="control-group">
                <label >AppKey</label> 
                <input  name="txtAppKeys" id="txtAppKeys" type="text"><label style="color: red">建议为"appkey_"+集团名称小写全拼</label>
            </div>  
            <div class="control-group">
                <label >App版本</label> 
                <input  name="txtAppVersion" value="1.0" title="1.0"  id="txtAppVersion" type="text"/>默认1.0
            </div> 
            <div class="control-group"> 
                <label style="color:red">AppSecret会自动生成</label>
            </div>
        </fieldset>
        <p class="btnbox">
            <input value="确认" type="button" id="btnAddGroupConfig" class="yesBtn" />                
            <input value="关闭" type="button" class="J_closebox qxBtn" />
        </p>
    </div>
</div>
	 
	<script>		
	var adminjs = new adminglass(); //实例化后台类		
	
	 $(document).ready(function() {    	
	        //$("#btnSearch").on('click', Search())
	        
	        $("#btnAdd").on('click', function () {        	
	        	adminjs.openwinbox('#GroupAddDivShow');
	        });
	        //window.location.hash = '';
	    });
	    
	//查询集团
	 $("#btnSearch").on('click', function () {
	        var txtGroupName = $("#txtGroupName").val(); //集团名称
	        var txtAppkey = $("#txtAppkey").val(); 	        
	        var pars = { "groupname": txtGroupName, "appkey":txtAppkey};	        
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
    
    
    //添加集团api配置
    $("#btnAddGroupConfig").on('click',function() {
        var groupid = $("#HidGroupID").val(); 
        var appkey=$('#txtAppKeys').val();
        var appversion=$('#txtAppVersion').val(); 
        if (appkey == "") {
            alert("集团AppKey不能为空!");
            return;
        }
        if (appversion == "") {
            alert("集团AppVersion不能为空!");
            return;
        }
        var pars = { "appkey":appkey,"appversion":appversion,"groupid":groupid};
        var url = "<%=basePath%>/group/addgroupapiconfig";
        $.ajax({
            type: 'POST',
            url: url,
            data: pars,
            success: function (result) {           
                    window.location.href = "<%=basePath%>/group/list";             
            }
        });
    });
    
    //关闭弹层
    $('.J_closebox').click(function () {
        adminjs.closewinbox('.add-openbox');
        return false;
    });   

    </script>
    
	
