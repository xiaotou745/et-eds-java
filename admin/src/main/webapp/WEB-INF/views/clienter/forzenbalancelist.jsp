
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
	List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
%>


   <table border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td>
                 <span class="">骑士名称: </span>
                 <input id="txtClienterName" type="text" name="txtClienterName" />
            </td>
            <td>
               <span class="">筛选城市: </span><%=HtmlHelper.getSelect("businessCityId", areaListData, "name", "code") %>
             <input type="submit" value="查询" class="searchBtn" id="btnSearch" />
            </td>
            </tr>
        </table>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
    <div tabindex="-1" class="modal inmodal" id="forzenClienterBalance"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">骑士余额冻结</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
              <div class="control-group">
              <input type="hidden" id="clienterId" />
                <label >骑士姓名：</label><label id="clienterTrueName"></label>
            </div>  
            <div class="control-group">
                <label >骑士电话：</label> 
                    <label id="clienterPhone"></label>
            </div> 
            <div class="control-group">
                <label >骑士余额：</label> 
                   <label id="clienterAmountBalance"></label>
            </div> 
            <div class="control-group">
                <label >骑士可提现余额：</label> 
                    <label id="clienterWithdrawPrice"></label>
            </div> 
             <div class="control-group">
                <label >冻结金额：</label> 
                    <input type="text" id="forzenAmount"/>
            </div> 
            <div class="control-group">
                <label >冻结原因：</label> 
                <textarea name="txtForzenReason" id="txtForzenReason" style="width:300px;height:60px;max-width:300px;max-height:60px;"></textarea>
            </div>  
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnForzenClienterBalance" onclick="confirmForzenClienterBalance()">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
	<script>		
	var jss={
			search:function(currentPage){	 
                 var trueName = $("#txtClienterName").val(); 
                 var code=$("#businessCityId").val();  
                 if (code==null || code=="")
                	 {
                	  code=-1;             
                	 } 
                 //参数不能为""值
				 var paramaters = { 
						 "currentPage":currentPage,
						 "trueName": trueName,
						 "code": code
						 };        
			        var url = "<%=basePath%>/clienter/forzenbalancelistdo";
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
	function confirmForzenClienterBalance(){
		var forzenAmount = $("#forzenAmount").val();
		var clienterId = $("#clienterId").val();
		var clienterAmountBalance = $("#clienterAmountBalance").text();
		var clienterWithdrawPrice = $("#clienterWithdrawPrice").text();
		var forzenReason = $("#txtForzenReason").val();
		if(isNaN(parseFloat(forzenAmount)) || parseFloat(forzenAmount)<1 || parseFloat(forzenAmount)>3000){
			alert("冻结金额必须大于1元小于3000元");
			return ;
		}
		if(parseFloat(forzenAmount)>parseFloat(clienterWithdrawPrice)){
			alert("冻结金额必须小于等于可提现金额");
			return;
		}
		if(forzenReason.trim().length <5 || forzenReason.trim().length>50){
			alert("冻结原因必须输入5-50个字符");
			return;
		}
		var paramaters = {
                "clienterId": clienterId,
                "forzenAmount": forzenAmount,
                "forzenReason": forzenReason.trim()              
            };
		//冻结骑士余额
       var url = "<%=basePath%>/clienter/forzenclienterbalance";
       $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (result) {
        	   alert(result.message);
               if (result.responseCode > 0) {
                   window.location.href = "<%=basePath%>/clienter/forzenbalancelist";
               }
        	  
           }
       });
	}
	</script>		
	
