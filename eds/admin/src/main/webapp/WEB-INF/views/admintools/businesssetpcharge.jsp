<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.admin.common.AuthCodeConst"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");
UserContext context=UserContext.getCurrentContext(request);
boolean canAdd=context.isHasAuthByCode(AuthCodeConst.Admin_BusinessSetpCharge_Add_Btn);
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="Title"  id="Title" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">添加日期:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="BeginDate"  id="BeginDate"/>
                                    </div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
							     <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="EndDate"  id="EndDate"/>
                                 </div>
   							</div>
						</div>
					</div>
			
				</div>

			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
							style="margin-left: 3px;height:30px;">查询</button>
					   <button type="button" class="btn btn-w-m btn-primary" id="btnAdd"
							style="margin-left: 3px;height:30px;">添加配置</button>
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
<div class="modal inmodal fade" id="AddBox" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 550px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">添加配置</h4>
			</div>
			<div class="modal-body form-horizontal">
				添加配置添加配置添加配置添加配置
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" id="btnSave" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<script>
//页面初始化
$(function(){
	//日期控件初始化
	 $(' .input-group.date').datepicker({
         todayBtn: "linked",
         keyboardNavigation: false,
         forceParse: false,
         calendarWeeks: true,
         autoclose: true
     });
	//弹窗
	$('#btnAdd').click(function(){
		$('#AddBox').modal('show');
	});
	
	jss.search(1);
});
	var jss = {
		search : function(currentPage) {
// 			var data={"currentPage":currentPage};
<%-- 			$.post("<%=basePath%>/admintools/appversiondo",data, function(d) { --%>
// 				$("#content").html(d);
// 			});
		}
	}
	jss.search(1);
</script>