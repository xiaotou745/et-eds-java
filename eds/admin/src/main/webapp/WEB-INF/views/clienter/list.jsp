
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.ClienterStatusEnum"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.entity.Mark"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
	
	List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
	List<DeliveryCompany> dCListData=	(List<DeliveryCompany>)request.getAttribute("dCListData");
	List<Mark> marklist=	(List<Mark>)request.getAttribute("marklistList");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">骑士名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="txtClienterName"  id="txtClienterName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">审核状态:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("status", EnumHelper.GetEnumItems(ClienterStatusEnum.class), "desc", "value",null,"-1","全部") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">骑士电话:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="txtSuperManPhone"  id="txtSuperManPhone"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">推荐人:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="recommonPhone"  id="recommonPhone"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">筛选城市:</label>
							<div class="col-sm-8">
							 <%=HtmlHelper.getSelect("businessCityId", areaListData, "name", "code") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">物流公司:</label>
							<div class="col-sm-8">
                               <%=HtmlHelper.getSelect("deliveryCompanyId", dCListData, "deliverycompanyname", "id") %> 
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">标签:</label>
							<div class="col-sm-8">
						<%=HtmlHelper.getSelect("markId", marklist, "tagName", "id") %>
   							</div>
						</div>
					</div>

				</div>
					
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 3px;height:30px;">查询</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 内容列表 --> 
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
   <!-- 内容列表 --> 
</div>
   <!-- 弹窗 --> 
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
<!-- 弹窗 --> 
   <!-- 弹窗 --> 
<div tabindex="-1" class="modal inmodal" id="PicShow"
	role="dialog" aria-hidden="true" style="display: none;">		
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">查看图片</h4>				
			</div>
			<small class="font-bold">
				<div class="modal-body">
			 	<fieldset>
                <img width="160" height="160" id="showPicHandWithUrl" />
                <a id="showBigPicHWU" href="" target="_blank">查看大图</a>
                <img width="160" height="160" id="showPicUrl" />
                <a id="showBigPicUrl" href="" target="_blank">查看大图</a>
           		</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<!-- 弹窗 --> 
	<script>		
	var jss={
			search:function(currentPage){	
                 var trueName = $("#txtClienterName").val();
                 var phoneNo = $("#txtSuperManPhone").val();
                 var recommendPhone = $("#recommonPhone").val(); 
                 var status=$("#status").val();           
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
						 "deliveryCompanyId": deliveryCompanyId	,
						 "markId":$('#markId').val()
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
                       if(result>0)
                    	{
                    	   jss.search(1);
                    	   alert('操作成功!');
                    	   $('#ClienterRechargeShow').modal('hide'); 
                    	 }
                       else
                    	   {
                    	   alert('操作失败!');
                    	   }
                }
            });
        }
    });
	 //查看图片
	function ShowPic(PicWithHandUrl,BigPWH,PicUrl,BigPU)
	{
			$('#showPicHandWithUrl').attr('src', PicWithHandUrl);
	        $('#showPicUrl').attr('src', PicUrl);
	        $('#showBigPicHWU').attr('href',BigPWH);
	        $('#showBigPicUrl').attr('href',BigPU);
	        $('#PicShow').modal('show');
	}
	</script>		
	
