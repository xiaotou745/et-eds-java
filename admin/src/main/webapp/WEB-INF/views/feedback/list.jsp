
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.FeedbackType"%>
<%@page import="com.edaisong.core.enums.UserType"%>

<%
	String basePath =PropertyUtils.getProperty("static.admin.url");	
 	List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
%>

   <table border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td>
                 <span class="">反馈类型: </span>                
                   <%=HtmlHelper.getSelect("sltFeedbackType", EnumHelper.GetEnumItems(FeedbackType.class), "desc", "value",null,"-1","全部","","selectw") %>
                   <span class="">筛选城市: </span>
               		  <%=HtmlHelper.getSelect("sltCityId", areaListData, "name", "name") %>
                <span class="">用户类型: </span>
                <%=HtmlHelper.getSelect("sltUserType", EnumHelper.GetEnumItems(UserType.class), "desc", "value",null,"-1","全部","","selectw") %>
                
                </td>
            </tr>
              <tr>
            <td>
               <span class="">日期: </span>                   
               <input name="txtStatrTime" id="txtStatrTime" type="text">到
               <input name="txtEndTime" id="txtEndTime" type="text">
               <input type="submit" value="查询" class="searchBtn" id="btnSearch" />
            </td>
            </tr>
        </table>  
        
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>   
   

	<script>		
	var jss={
			search:function(currentPage){	
				   var feedbacktype = $("#sltFeedbackType").val();
				   var city=$("#sltCityId").val();
				   var usertype=$("#sltUserType").val();
				   var statrTime=$("#txtStatrTime").val();				   
				   var endTime=$("#txtEndTime").val();				   
				 var paramaters = { 
						 "currentPage":currentPage,
						 "feedbacktype": feedbacktype,
						 "city": city,
						 "usertype": usertype,
						 "statrTime": statrTime,
						 "endTime": endTime,
						 };        
			        var url = "<%=basePath%>/feedback/listdo";
			        $.ajax({
			            type: 'POST',
			            url: url,
			            data: paramaters,
			            success: function (result) {   			            
			            	$("#content").html(result);               
			            }
			        });
			}
		}	
		
	jss.search(1);
	$("#btnSearch").click(function(){
		jss.search(1);
	});		

	</script>		
	
