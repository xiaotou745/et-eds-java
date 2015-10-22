
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
	
	List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
	List<DeliveryCompany> dCListData=	(List<DeliveryCompany>)request.getAttribute("dCListData");
%>


   <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                 <span class="">骑士名称: </span>
                 <input id="txtClienterName" type="text" name="txtClienterName" />
                  <span class="">审核状态: </span>
                        <select name="status"  class="form-control m-b" id="superManStatus">
                            <option value="-1">全部</option>
                            <option value="1">审核通过</option>
                            <option value="0">被拒绝</option>
                            <option value="2">未审核</option>
                            <option value="3">审核中</option>
                        </select>
                        <span class="">骑士电话: </span>
                        <input id="txtClienterPhone" type="text" name="txtClienterPhone" value="" />
                        <span class="">推荐人手机: </span>
                        <input id="txtRecommonPhone" type="text" name="txtRecommonPhone" value="" />
                                
                </td>
            </tr>
            <tr>
            <td>
               <span class="">筛选城市: </span>
               		  <%=HtmlHelper.getSelect("businessCityId", areaListData, "name", "code") %>
               	                     
              <span class="">物流公司: </span>
              <%=HtmlHelper.getSelect("deliveryCompanyId", dCListData, "deliverycompanyname", "id") %>            
      
             <input type="submit" value="查询" class="searchBtn" id="btnSearch" />
            </td>
            </tr>
        </table>  
        
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
   
   
   <div tabindex="-1" class="modal inmodal" id="ClienterRechargeShow"
	role="dialog" aria-hidden="true" style="display: none;">		
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">骑士余额变更</h4>				
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						  <div class="control-group">
		                <label>骑士名称：</label>
		                <input name="clienterName" id="clienterName" disabled="disabled" type="text">
		                <input name="clienterId" id="clienterId" type="hidden">
		            	</div>
			            <div class="control-group">
			                <label>骑士电话：</label>
			                <input name="clienterPhone" id="clienterPhone" disabled="disabled" type="text">
			            </div>
			            <div class="control-group">
			                <label>余额增减：</label>
			                <input name="clienterRechargeAmount" id="clienterRechargeAmount" type="text">元
			            </div>
			            <div class="control-group">
			                <label>备注：</label>
			                <div class="controls">
			                    <textarea cols="45" rows="5" id="rechargeLog"></textarea>
			                </div>
			            </div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnRechargeCommit">确认</button>
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
                 var phoneNo = $("#txtClienterPhone").val();
                 var recommendPhone = $("#txtRecommonPhone").val(); 
                 var status=$("#superManStatus").val();           
                 var code=$("#businessCityId").val();       
                 var deliveryCompanyId=$("#deliveryCompanyId").val();   
                 if (code==null || code=="")
                	 {
                	  code=-1;             
                	 }
                 if (deliveryCompanyId==null || deliveryCompanyId=="")
            	 {
                	 deliveryCompanyId=-1;             
            	 }                
               
                 //参数不能为""值
				 var paramaters = { 
						 "currentPage":currentPage,
						 "trueName": trueName,
						 "phoneNo": phoneNo,
						 "recommendPhone": recommendPhone,
						 "status": status,
						 "code": code,
						 "deliveryCompanyId": deliveryCompanyId		
						 };        
			        var url = "<%=basePath%>/clienter/listdo";
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
	
	
	 //骑士充值
    $("#btnRechargeCommit").on('click', function () {
        var clienterId = $("#clienterId").val(); //骑士id
        var clienterName = $("#clienterName").val(); //骑士电话
        var clienterRechargeAmount = $("#clienterRechargeAmount").val(); //骑士调整金额
        var rechargeLog = $("#rechargeLog").val(); //充值描述
        if (rechargeLog.trim().length == 0) {
            alert("请输入备注!");
            return;
        }

        var decimalFormat = /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
        if (!decimalFormat.test(clienterRechargeAmount)) {
            alert("请输入正确的金额！");
            return;
        }
    
        if (confirm("确定要为骑士：" + clienterName + "   调整：" + clienterRechargeAmount + "元？")) {
            var paramaters = {
                "clienterId": clienterId, "rechargeAmount": clienterRechargeAmount, "remark": rechargeLog
            };
            var url = "<%=basePath%>/clienter/modifymoney";
            $.ajax({
                type: 'POST',
                url: url,
                data: paramaters,
                success: function (result) {                  
                        window.location.href = "<%=basePath%>/clienter/list";                    
                }
            });
        }
    });

	</script>		
	
