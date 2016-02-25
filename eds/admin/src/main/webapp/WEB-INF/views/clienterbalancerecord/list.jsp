<%@page import="com.edaisong.core.security.DES"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.ClienterBalanceRecordRecordType"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.entity.domain.ClienterDetailModel"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");
String  clienterId=	request.getAttribute("clienterId").toString();
ClienterDetailModel  detail=(ClienterDetailModel)request.getAttribute("detail");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">骑士名称:</label>
							<div class="col-sm-8">
								<label class="col-sm-12 control-label"><%=detail.getTruename()%></label>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">电话:</label>
							<div class="col-sm-8">
								<label class="col-sm-12 control-label"><%=detail.getPhoneno()%></label>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">当前余额:</label>
							<div class="col-sm-8">
								<label class="col-sm-12 control-label">￥<%=ParseHelper.digitsNum(detail.getAccountbalance(), 2)%></label>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">累计提款:</label>
							<div class="col-sm-8">
								<label class="col-sm-12 control-label">￥<%=ParseHelper.digitsNum(detail.getHaswithdrawprice(), 2)%></label>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">开户行:</label>
							<div class="col-sm-8">
							<label class="col-sm-12 control-label"><%=detail.getOpenBank()%></label>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">开户支行:</label>
							<div class="col-sm-8">
                               <label class="col-sm-12 control-label"><%=detail.getOpenSubBank()%></label>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">账户名:</label>
							<div class="col-sm-8">
							<label class="col-sm-12 control-label"><%=detail.getAccountName()%></label>
   							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">银行账号:</label>
							<div class="col-sm-8">
							<label class="col-sm-12 control-label"><%=DES.decrypt(detail.getAccountNo())%></label>
   							</div>
						</div>
					</div>

				</div>
				<div class="row" style="margin-top: 30px;">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">交易类型:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("recordType", EnumHelper.GetEnumItems(ClienterBalanceRecordRecordType.class), "desc", "value",null,"-1","全部") %>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">提款单号:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="txtSuperManPhone"  id="txtSuperManPhone"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">开始时间:</label>
							<div class="col-sm-8">
									<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="beginDate"  id="beginDate"/>
                                    </div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">结束时间:</label>
							<div class="col-sm-8">
								 <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="endDate"  id="endDate"/>
                                    </div>
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


<script>	
	var jss={
			search:function(currentPage){	

                 var clienterId = <%=clienterId%>;            
                 //参数不能为""值
				 var paramaters = { 
						 "currentPage":currentPage,
						 "clienterId": clienterId						
						 };        
			        var url = "<%=basePath%>/clienter/clienterbalancerecordlistdo";
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
	
