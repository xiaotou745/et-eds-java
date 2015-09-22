
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.ClienterForzenType"%>
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
                 
                 <%=HtmlHelper.getSelect("forzenstatus", EnumHelper.GetEnumItems(ClienterForzenType.class),
					"desc", "value", null, "0", "全部", "", "")%>                  
                 <select name="datetype" class="selectw" id="selectdatetype" style="width:73px">
                      <option value="1">冻结时间</option>
                      <option value="2">解冻时间</option>
                  </select>
                  <input id="txtstartdate" type="text" name="startdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'txtenddate\')||\'2020-10-01\'}'})"/>
                       <span class="">到 </span>
                  <input id="txtenddate" type="text" name="enddate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'txtstartdate\')}',maxDate:'2020-10-01'})"/>
                          
      			<input type="submit" value="查询" class="searchBtn" id="btnSearch" />  
      			<input type="button" value="骑士余额冻结" class="searchBtn" id="btnForzenClienter" onclick="showForzenClienterAmount()" />  
      			        
                </td>
            </tr>
        
        </table>  
        
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
<div tabindex="-1" class="modal inmodal" id="showUnfreezeClienter"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">解冻</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
		<fieldset>
            <br>
             <div class="control-group">
                <label >解冻原因：</label> 
                <textarea name="txtUnfreezeReason" id="txtUnfreezeReason" style="width:300px;height:60px;max-width:300px;max-height:60px;"></textarea>
                <input type="hidden" id="hdForzenId" />
                <input type="hidden" id="hdClienterId" />
                <input type="hidden" id="hdForzenAmount"/>
            </div>  
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnUnfreezeClienter" onclick="confirmUnfreezeClienter()">保存</button>
				</div>
			</small>
		</div> 
	</div>
</div>
<div tabindex="-1" class="modal inmodal" id="showForzenOrderDetail"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header"> 
				<h4 class="modal-title">余额冻结详情</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
		<fieldset>
            <br>
             <div class="control-group">
             <input type="hidden" id="hdForzenId"/>
                <label >骑士姓名：</label><label id="lblclienterTrueName"></label>
            </div>  
            <div class="control-group">
                <label >骑士电话：</label> 
                    <label id="lblclienterPhone"></label>
            </div> 
            <div class="control-group">
                <label>冻结金额：</label> <label id="lblforzenAmount"></label>
            </div>
            <div class="control-group">
                <label>冻结原因：</label> <label id="lblforzenReason"></label>
            </div>
            <div class="control-group">
                <label>解冻原因：</label> <label id="lblunfreezeReason"></label>
            </div><hr/>
            <div>
            	<h4>操作记录</h4> 
            	<div id="forzenRecord"></div>
            </div>
        </fieldset>
				</div>
				<div class="modal-footer" style="text-align:center">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
				</div>
			</small>
		</div> 
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
	$('#txtClienterName,#txtClienterPhone').keyup(function(event){ 
        if(event.keyCode == "13")    
        { 
            $("#btnSearch").click();
        }
    });
	//解冻
   function confirmUnfreezeClienter(){
	   var unfreezeReason = $("#txtUnfreezeReason").val();
	   if(unfreezeReason.trim().length == 0){
		   alert("请输入解冻原因");
		   return;
	   }
	   if(unfreezeReason.trim().length <5 || unfreezeReason.trim().length>50){
			alert("解冻原因必须输入5-50个字符");
			return;
		}
	   var paramaters = {
	    	   "id":$("#hdForzenId").val(),
	    	   
               "clienterId": $("#hdClienterId").val(),
               "forzenAmount": $("#hdForzenAmount").val(),
               "unfreezeReason":unfreezeReason.trim()
           };
      var url = "<%=basePath%>/clienter/unfreezeclienter";
	   $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (result) {   			            
        	   alert(result.message);
               if (result.responseCode > 0) {
                   window.location.href = "<%=basePath%>/clienter/forzenlist";
               }               
           }
       });
   }
   function showForzenClienterAmount(){
	   window.location.href = "<%=basePath%>/clienter/forzenbalancelist";
   }
   </script>