<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessModel"%> 
<%@page import="java.text.SimpleDateFormat"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
	GroupBusinessModel detail = (GroupBusinessModel)request.getAttribute("detail");
%>

<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script
	src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<table class="tbstyle222" border="0"
	style="font-size: 14px; font-weight: bold; line-height: 300%; width: 900px">
	<tr class="trclass">
		<td>集团名称：<%=ParseHelper.ToString(detail.getGroupbusiname(), "")%></td>
		<td>联系电话：<%=ParseHelper.ToString(detail.getLoginname(), "")%></td>
		<td><a href="<%=basePath%>/groupbusiness/balancerecordlist?groupBusinessId=<%=detail.getId() %>">集团余额：<%=detail.getAmount() %></a></td>
		<td><a href="<%=basePath%>/groupbusiness/businessbindloglist?groupBusinessId=<%=detail.getId() %>">绑定记录查询</a></td>
	</tr>
</table>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="groupBusinessId" id="groupBusinessId" value="<%=detail.getId() %>" />
				<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-6">
								<input placeholder="门店名称"  type="text" class="form-control" name="bizName" id="bizName" onkeydown="return disableEnter(event)" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-6">
								<input placeholder="门店电话"  type="text" class="form-control" name="bizPhone" id="bizPhone" onkeydown="return disableEnter(event)"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-6">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> 
										<input class="form-control" style="width:180px" type="text" name="startDate" id="startDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> 
										<input class="form-control" style="width:180px" type="text"  name="endDate" id="endDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-8">
								<button type="button" class="btn btn-primary btn-lg" id="btnSearch">查询</button>
								<button type="button" class="btn btn-primary btn-lg" onclick="window.location.href='<%=basePath%>/groupbusiness/businesslist?groupBusinessId=<%=detail.getId()%>'">绑定门店</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>

<script>
$(function(){
	
});
Date.prototype.Format = function(fmt) 
{  
  var o = { 
    "M+" : this.getMonth()+1,                  
    "d+" : this.getDate(),                     
    "h+" : this.getHours(),                    
    "m+" : this.getMinutes(),                  
    "s+" : this.getSeconds(),                  
    "q+" : Math.floor((this.getMonth()+3)/3), 
    "S"  : this.getMilliseconds()              
  }; 
  if(/(y+)/.test(fmt)) 
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  for(var k in o) 
    if(new RegExp("("+ k +")").test(fmt)) 
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
  return fmt; 
}
	var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize(); 
		 console.log(data);
		 $.post("<%=basePath%>/groupbusiness/businessbindlistdo", data,
					function(d) {
						$("#content").html(d);
					});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
	function funRemoveBusinessBind(businessId){
		var groupBusinessId = $("#groupBusinessId").val();
        if (!window.confirm("是否解除绑定？")) {
            return;
        }
        var paramaters = {"businessId":businessId,"groupId":groupBusinessId};
        var url = "<%=basePath%>/groupbusiness/removebusinessbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	alert(result.message);
                if (result.responseCode > 0) {
                    window.location.href = "<%=basePath%>/groupbusiness/businessbindlist?groupBusinessId=" + groupBusinessId;
                }
            }
        });
	}
	
	function disableEnter(event){
		 var e = event || window.event || arguments.callee.caller.arguments[0];
	       if(e && e.keyCode==27){ // 按 Esc 
	           //要做的事情
	         }
	       if(e && e.keyCode==113){ // 按 F2 
	            //要做的事情
	          }            
	        if(e && e.keyCode==13){ // enter 键
	            //要做的事情
	     	  $('#btnSearch').click();
	         // jss.search(1);
	       }
	};
</script>

