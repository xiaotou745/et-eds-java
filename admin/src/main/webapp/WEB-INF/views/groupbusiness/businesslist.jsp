<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessModel"%>
<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
	GroupBusinessModel detail = (GroupBusinessModel)request.getAttribute("detail");
%>

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
							<label class="col-sm-3 control-label">集团名称：</label>
							<div class="col-sm-8">
								<label class="col-sm-6 control-label"><%=ParseHelper.ToString(detail.getGroupbusiname(), "")%></label>
							</div>
						</div>
					</div>
					
						<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-3 control-label">门店名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="bizName" id="bizName" onkeydown="return disableEnter(event)"/>
								<input id="hiddenText" type="text" style="display:none" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				
					<div class="col-lg-3">
						<div class="form-group">
							<button type="button" class="btn btn-primary btn-lg" id="btnSearch">查询</button>
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
var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
		 $.post("<%=basePath%>/groupbusiness/businesslistdo", data,
					function(d) {
			 			console.log('填充数据钱');
						$("#content").html(d);
						console.log('填充数据后');
					});
		}
	}
$(function(){
	jss.search(1);
	
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	
});
//回车
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


	function funAddBusinessBind(businessId){
		var groupBusinessId = $("#groupBusinessId").val();
        if (!window.confirm("是否绑定？")) {
            return;
        }
        var paramaters = {"businessId":businessId,"groupId":groupBusinessId};
        var url = "<%=basePath%>/groupbusiness/addbusinessbind";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
            	alert(result.message);
                if (result.responseCode > 0) {
                	console.log('导航')
                    window.location.href = "<%=basePath%>/groupbusiness/businesslist?groupBusinessId=" + groupBusinessId;
                }
            }
        });
	}
</script>

