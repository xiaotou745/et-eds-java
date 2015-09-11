
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>

<%
String basePath =PropertyUtils.getProperty("static.admin.url");
	
	//List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
	//List<DeliveryCompany> dCListData=	(List<DeliveryCompany>)request.getAttribute("dCListData");
%>

   <table >
            <tr>
            	<td>
                 <span>骑士名称: </span>
                 <input id="txtClienterName" type="tel" name="ClienterName" />
                 <span class="">骑士电话: </span>
                 <input id="txtClienterPhone" type="tel" name="ClienterPhone" />
                 <span class="">冻结状态: </span>
                 <select name="status" class="selectw" id="forzenstatus" style="width:83px">
                      <option value="0">全部</option>
                      <option value="1">冻结中</option>
                      <option value="2">已解冻</option>
                 </select>   
                 <select name="datetype" class="selectw" id="selectdatetype" style="width:73px">
                      <option value="1">冻结时间</option>
                      <option value="2">解冻时间</option>
                  </select>
                  <input id="txtstartdate" type="text" name="startdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'txtenddate\')||\'2020-10-01\'}'})"/>
                       <span class="">到 </span>
                  <input id="txtenddate" type="text" name="enddate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'txtstartdate\')}',maxDate:'2020-10-01'})"/>
                          
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
                 var clienterName = $("#txtClienterName").val();
                 var phoneNo = $("#txtClienterPhone").val();
                 var forzenStatus = $("#forzenstatus").val(); 
                 var datetype=$("#selectdatetype").val();           
                 var startdate=$("#txtstartdate").val();       
                 var enddate=$("#txtenddate").val();   
				 var paramaters = { 
						 "currentPage":currentPage,
						 "clienterName": clienterName,
						 "clienterPhone": phoneNo,
						 "forzenStatus": forzenStatus,
						 "dateType": datetype,
						 "startDate": startdate,
						 "endDate": enddate,	
						 };        
			        var url = "<%=basePath%>/clienter/forzenlistdo";
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