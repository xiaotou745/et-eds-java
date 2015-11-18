<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>



<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/font-awesome/css/font-awesome.min.css" />
<%-- <link rel="stylesheet" href="<%=basePath%>/css/plugins/toastr/toastr.min.css" /> --%>
<%-- <link rel="stylesheet" href="<%=basePath%>/js/plugins/gritter/jquery.gritter.css" /> --%>
<link rel="stylesheet" href="<%=basePath%>/css/animate.css" />
<link rel="stylesheet" href="<%=basePath%>/css/style.css" />
<link rel="stylesheet" href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />
<link href="<%=basePath%>/css/admin.css" rel="stylesheet" />

<!-- Mainly scripts -->
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=basePath%>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=basePath%>/My97DatePicker/WdatePicker.js"></script>
<!-- Flot -->
<%-- <script src="<%=basePath%>/js/plugins/flot/jquery.flot.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.pie.js"></script> --%>

<!-- Peity -->
<%-- <script src="<%=basePath%>/js/plugins/peity/jquery.peity.min.js"></script>
<script src="<%=basePath%>/js/demo/peity-demo.js"></script> --%>

<!-- Custom and plugin javascript -->
<script src="<%=basePath%>/js/inspinia.js"></script>
<%-- <script src="<%=basePath%>/js/plugins/pace/pace.min.js"></script> --%>

<!-- jQuery UI -->
<script src="<%=basePath%>/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- GITTER -->
<%-- <script src="<%=basePath%>/js/plugins/gritter/jquery.gritter.min.js"></script> --%>

<!-- Sparkline -->
<%-- <script src="<%=basePath%>/js/plugins/sparkline/jquery.sparkline.min.js"></script> --%>

<!-- Sparkline demo data  -->
<%-- <script src="<%=basePath%>/js/demo/sparkline-demo.js"></script> --%>

<!-- ChartJS-->
<%-- <script src="<%=basePath%>/js/plugins/chartJs/Chart.min.js"></script> --%>

<!-- Toastr -->
<%-- <script src="<%=basePath%>/js/plugins/toastr/toastr.min.js"></script> --%>


<!-- 第三方弹窗js -->
<script src="<%=basePath%>/js/layer.js"></script>

<!-- 分页相关js -->
<%--     <script type="text/javascript" src="<%=basePath%>/js/admin.js"></script> --%>
<%-- <script src="<%=basePath%>/js/plugins/jeditable/jquery.jeditable.js"></script>  --%>
<%-- <script src="<%=basePath%>/js/plugins/dataTables/jquery.dataTables.js"></script> --%>
<%-- <script src="<%=basePath%>/js/plugins/dataTables/dataTables.bootstrap.js"></script> --%>
<%-- <script src="<%=basePath%>/js/hplus.js"></script> --%>
<script>
    $(document).ajaxError( function(event, jqXHR, options, errorMsg){
   	 var content="内部服务器错误";
    	if(jqXHR.responseText==undefined){
    		content=jqXHR.statusText;
    	}else{
    	 var start=jqXHR.responseText.indexOf("<body>");

    	 if(start>0){
        	 var end=jqXHR.responseText.indexOf("</body>");
        	 content=jqXHR.responseText.substring(start+6,end);
        	 content=content.replace("h1","h4"); 
    	 }else{
    		 var start2=jqXHR.responseText.indexOf("<pre>");
    		 var end2=jqXHR.responseText.indexOf("</pre>");
        	 content=jqXHR.responseText.substring(start2,end2+6);
    	 }
    	 }
		if(content.indexOf("AjaxNotLoginRunTimeException")>0){
			alert("由于你长时间没操作，请重新登录");  
			window.location.href = "<%=basePath %>";
			return;
		}
    	 $("#gloablErrorParam").html(options.url+"调用出错了！");
    	 $("#gloablErrorContent").html(content);
    	 $("#gloablShowError").html("显示详细信息");
    	 $("#gloablErrorContent").hide();
    	 $('#gloablErrorDiv').modal('show');
    });
    
	$(document).ready(function() {
		$("#gloablShowError").click(function() {
			if ($("#gloablShowError").html() == "显示详细信息") {
				$("#gloablShowError").html("隐藏详细信息");
				var timeSet=2000;
				if($("#gloablErrorContent").html().length<500){
					timeSet=500;
				}
				$("#gloablErrorContent").slideDown(timeSet);
			} else {
				$("#gloablShowError").html("显示详细信息");
				$("#gloablErrorContent").slideUp(500);
			}
		});
		//分页跳转按钮事件处理方法
		$(document).on("click", "#pagesearch", function(){
			var page=$("#pagesearchvalue").val();
			var maxpage=$("#pagesearchmax").val();
			var currentpage=$("#pagesearchcurrentpage").val();
			var s = new RegExp("^\\s*(\\d+)\\s*$");
			if(!s.test(page)||parseInt(page) < 1 || parseInt(page) > maxpage){
			  alert("页索引超出范围");
			  $("#pagesearchvalue").val(currentpage);
			  return;
			}
			jss.search(page);
		}); 
		$(document).on("keydown", "#pagesearchvalue", function(e){
		    var key = null;
		    if (e.which) {
		        key = e.which;
		    }
		    else if (e.keyCode) {
		        key = e.keyCode;
		    }

			if ((48<=key&&key<=57)||(96<=key&&key<=105)) {
			    return true;
			}else{
			    return false;
			}
		});
		//列表页下拉框改变时，自动查询
		$("select").on("change",function(e){
			try{
				jss.search(1);
			}catch(e){
			}
		});
		//列表页点击回车时，自动查询
		$(document).on("keydown",function(event){
			try {
				var e = event || 
						window.event|| 
						arguments.callee.caller.arguments[0];
				if (e && e.keyCode == 13) { // enter 键
					jss.search(1);
				}
			} catch (e) {
			}
		});
	});
</script>
<tiles:insertAttribute name="header_js" ignore="true"></tiles:insertAttribute>
<tiles:insertAttribute name="header_css" ignore="true"></tiles:insertAttribute>
</head>
<body>

	<div id="wrapper">
		<tiles:insertAttribute name="leftmenu"></tiles:insertAttribute>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<tiles:insertAttribute name="header"></tiles:insertAttribute>
			</div>
			<tiles:insertAttribute name="breadcrumbs"></tiles:insertAttribute>
			<div class="row">
				<div class="col-lg-12">
					<tiles:insertAttribute name="body"></tiles:insertAttribute>

				</div>
			</div>
			<div class="row">
				<div class="">
					<tiles:insertAttribute name="footer" ignore="true"></tiles:insertAttribute>
				</div>
			</div>
		</div>
		<div tabindex="-1" class="modal inmodal" id="gloablErrorDiv" role="dialog"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">服务器异常</h4>
					</div>
					<small class="font-bold">
						<div class="modal-body">
						<div id="gloablErrorParam"></div>
						<div><a id="gloablShowError"  href="javascript:void(0)">显示详细信息</a></div>
						<pre id="gloablErrorContent" style="width: 560px;display: none; "></pre>
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
<!-- 		<div class="small-chat-box fadeInRight animated"> -->

<!-- 			<div class="heading" draggable="true"> -->
<!-- 				<small class="chat-date pull-right"> 02.19.2015 </small> Small chat -->
<!-- 			</div> -->

<!-- 			<div class="content"> -->

<!-- 				<div class="left"> -->
<!-- 					<div class="author-name"> -->
<!-- 						Monica Jackson <small class="chat-date"> 10:02 am </small> -->
<!-- 					</div> -->
<!-- 					<div class="chat-message active">Lorem Ipsum is simply dummy text input.</div> -->

<!-- 				</div> -->
<!-- 				<div class="right"> -->
<!-- 					<div class="author-name"> -->
<!-- 						Mick Smith <small class="chat-date"> 11:24 am </small> -->
<!-- 					</div> -->
<!-- 					<div class="chat-message">Lorem Ipsum is simpl.</div> -->
<!-- 				</div> -->
<!-- 				<div class="left"> -->
<!-- 					<div class="author-name"> -->
<!-- 						Alice Novak <small class="chat-date"> 08:45 pm </small> -->
<!-- 					</div> -->
<!-- 					<div class="chat-message active">Check this stock char.</div> -->
<!-- 				</div> -->
<!-- 				<div class="right"> -->
<!-- 					<div class="author-name"> -->
<!-- 						Anna Lamson <small class="chat-date"> 11:24 am </small> -->
<!-- 					</div> -->
<!-- 					<div class="chat-message">The standard chunk of Lorem Ipsum</div> -->
<!-- 				</div> -->
<!-- 				<div class="left"> -->
<!-- 					<div class="author-name"> -->
<!-- 						Mick Lane <small class="chat-date"> 08:45 pm </small> -->
<!-- 					</div> -->
<!-- 					<div class="chat-message active">I belive that. Lorem Ipsum is simply dummy text.</div> -->
<!-- 				</div> -->


<!-- 			</div> -->
<!-- 			<div class="form-chat"> -->
<!-- 				<div class="input-group input-group-sm"> -->
<!-- 					<input type="text" class="form-control"> <span class="input-group-btn"> -->
<!-- 						<button class="btn btn-primary" type="button">Send</button> -->
<!-- 					</span> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 		</div> -->


<!-- 		<div id="small-chat"> -->

<!-- 			<span class="badge badge-warning pull-right">5</span> <a class="open-small-chat"> <i class="fa fa-comments"></i> -->

<!-- 			</a> -->
<!-- 		</div> -->
<!-- 		<div id="right-sidebar"> -->
<!-- 			<div class="sidebar-container"> -->

<!-- 				<ul class="nav nav-tabs navs-3"> -->

<!-- 					<li class="active"><a data-toggle="tab" href="#tab-1"> Notes </a></li> -->
<!-- 					<li><a data-toggle="tab" href="#tab-2"> Projects </a></li> -->
<!-- 					<li class=""><a data-toggle="tab" href="#tab-3"> <i class="fa fa-gear"></i> -->
<!-- 					</a></li> -->
<!-- 				</ul> -->

<!-- 				<div class="tab-content"> -->


<!-- 					<div id="tab-1" class="tab-pane active"> -->

<!-- 						<div class="sidebar-title"> -->
<!-- 							<h3> -->
<!-- 								<i class="fa fa-comments-o"></i> Latest Notes -->
<!-- 							</h3> -->
<!-- 							<small><i class="fa fa-tim"></i> You have 10 new message.</small> -->
<!-- 						</div> -->

<!-- 						<div> -->

<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a1.jpg"> --%>

<!-- 										<div class="m-t-xs"> -->
<!-- 											<i class="fa fa-star text-warning"></i> <i class="fa fa-star text-warning"></i> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="media-body"> -->

<!-- 										There are many variations of passages of Lorem Ipsum available. <br> <small class="text-muted">Today -->
<!-- 											4:21 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a2.jpg"> --%>
<!-- 									</div> -->
<!-- 									<div class="media-body"> -->
<!-- 										The point of using Lorem Ipsum is that it has a more-or-less normal. <br> <small class="text-muted">Yesterday -->
<!-- 											2:45 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a3.jpg"> --%>

<!-- 										<div class="m-t-xs"> -->
<!-- 											<i class="fa fa-star text-warning"></i> <i class="fa fa-star text-warning"></i> <i -->
<!-- 												class="fa fa-star text-warning"></i> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="media-body"> -->
<!-- 										Mevolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). <br> -->
<!-- 										<small class="text-muted">Yesterday 1:10 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a4.jpg"> --%>
<!-- 									</div> -->

<!-- 									<div class="media-body"> -->
<!-- 										Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the <br> <small -->
<!-- 											class="text-muted">Monday 8:37 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a8.jpg"> --%>
<!-- 									</div> -->
<!-- 									<div class="media-body"> -->

<!-- 										All the Lorem Ipsum generators on the Internet tend to repeat. <br> <small class="text-muted">Today -->
<!-- 											4:21 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a7.jpg"> --%>
<!-- 									</div> -->
<!-- 									<div class="media-body"> -->
<!-- 										Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section -->
<!-- 										1.10.32. <br> <small class="text-muted">Yesterday 2:45 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a3.jpg"> --%>

<!-- 										<div class="m-t-xs"> -->
<!-- 											<i class="fa fa-star text-warning"></i> <i class="fa fa-star text-warning"></i> <i -->
<!-- 												class="fa fa-star text-warning"></i> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="media-body"> -->
<!-- 										The standard chunk of Lorem Ipsum used since the 1500s is reproduced below. <br> <small -->
<!-- 											class="text-muted">Yesterday 1:10 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<div class="sidebar-message"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="pull-left text-center"> -->
<%-- 										<img alt="image" class="img-circle message-avatar" src="<%=basePath%>/img/a4.jpg"> --%>
<!-- 									</div> -->
<!-- 									<div class="media-body"> -->
<!-- 										Uncover many web sites still in their infancy. Various versions have. <br> <small class="text-muted">Monday -->
<!-- 											8:37 pm</small> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 					</div> -->

<!-- 					<div id="tab-2" class="tab-pane"> -->

<!-- 						<div class="sidebar-title"> -->
<!-- 							<h3> -->
<!-- 								<i class="fa fa-cube"></i> Latest projects -->
<!-- 							</h3> -->
<!-- 							<small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small> -->
<!-- 						</div> -->

<!-- 						<ul class="sidebar-list"> -->
<!-- 							<li><a href="#"> -->
<!-- 									<div class="small pull-right m-t-xs">9 hours ago</div> -->
<!-- 									<h4>Business valuation</h4> It is a long established fact that a reader will be distracted. -->

<!-- 									<div class="small">Completion with: 22%</div> -->
<!-- 									<div class="progress progress-mini"> -->
<!-- 										<div style="width: 22%;" class="progress-bar progress-bar-warning"></div> -->
<!-- 									</div> -->
<!-- 									<div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div> -->
<!-- 							</a></li> -->
<!-- 							<li><a href="#"> -->
<!-- 									<div class="small pull-right m-t-xs">9 hours ago</div> -->
<!-- 									<h4>Contract with Company</h4> Many desktop publishing packages and web page editors. -->

<!-- 									<div class="small">Completion with: 48%</div> -->
<!-- 									<div class="progress progress-mini"> -->
<!-- 										<div style="width: 48%;" class="progress-bar"></div> -->
<!-- 									</div> -->
<!-- 							</a></li> -->
<!-- 							<li><a href="#"> -->
<!-- 									<div class="small pull-right m-t-xs">9 hours ago</div> -->
<!-- 									<h4>Meeting</h4> By the readable content of a page when looking at its layout. -->

<!-- 									<div class="small">Completion with: 14%</div> -->
<!-- 									<div class="progress progress-mini"> -->
<!-- 										<div style="width: 14%;" class="progress-bar progress-bar-info"></div> -->
<!-- 									</div> -->
<!-- 							</a></li> -->
<!-- 							<li><a href="#"> <span class="label label-primary pull-right">NEW</span> -->
<!-- 									<h4>The generated</h4> <div class="small pull-right m-t-xs">9 hours ago</div> There are many variations -->
<!-- 									of passages of Lorem Ipsum available. -->
<!-- 									<div class="small">Completion with: 22%</div> -->
<!-- 									<div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div> -->
<!-- 							</a></li> -->
<!-- 							<li><a href="#"> -->
<!-- 									<div class="small pull-right m-t-xs">9 hours ago</div> -->
<!-- 									<h4>Business valuation</h4> It is a long established fact that a reader will be distracted. -->

<!-- 									<div class="small">Completion with: 22%</div> -->
<!-- 									<div class="progress progress-mini"> -->
<!-- 										<div style="width: 22%;" class="progress-bar progress-bar-warning"></div> -->
<!-- 									</div> -->
<!-- 									<div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div> -->
<!-- 							</a></li> -->
<!-- 							<li><a href="#"> -->
<!-- 									<div class="small pull-right m-t-xs">9 hours ago</div> -->
<!-- 									<h4>Contract with Company</h4> Many desktop publishing packages and web page editors. -->

<!-- 									<div class="small">Completion with: 48%</div> -->
<!-- 									<div class="progress progress-mini"> -->
<!-- 										<div style="width: 48%;" class="progress-bar"></div> -->
<!-- 									</div> -->
<!-- 							</a></li> -->
<!-- 							<li><a href="#"> -->
<!-- 									<div class="small pull-right m-t-xs">9 hours ago</div> -->
<!-- 									<h4>Meeting</h4> By the readable content of a page when looking at its layout. -->

<!-- 									<div class="small">Completion with: 14%</div> -->
<!-- 									<div class="progress progress-mini"> -->
<!-- 										<div style="width: 14%;" class="progress-bar progress-bar-info"></div> -->
<!-- 									</div> -->
<!-- 							</a></li> -->
<!-- 							<li><a href="#"> <span class="label label-primary pull-right">NEW</span> -->
<!-- 									<h4>The generated</h4> <div class="small pull-right m-t-xs">9 hours ago</div> There are many variations -->
<!-- 									of passages of Lorem Ipsum available. -->
<!-- 									<div class="small">Completion with: 22%</div> -->
<!-- 									<div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div> -->
<!-- 							</a></li> -->

<!-- 						</ul> -->

<!-- 					</div> -->

<!-- 					<div id="tab-3" class="tab-pane"> -->

<!-- 						<div class="sidebar-title"> -->
<!-- 							<h3> -->
<!-- 								<i class="fa fa-gears"></i> Settings -->
<!-- 							</h3> -->
<!-- 							<small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small> -->
<!-- 						</div> -->

<!-- 						<div class="setings-item"> -->
<!-- 							<span> Show notifications </span> -->
<!-- 							<div class="switch"> -->
<!-- 								<div class="onoffswitch"> -->
<!-- 									<input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example"> <label -->
<!-- 										class="onoffswitch-label" for="example"> <span class="onoffswitch-inner"></span> <span -->
<!-- 										class="onoffswitch-switch"></span> -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="setings-item"> -->
<!-- 							<span> Disable Chat </span> -->
<!-- 							<div class="switch"> -->
<!-- 								<div class="onoffswitch"> -->
<!-- 									<input type="checkbox" name="collapsemenu" checked class="onoffswitch-checkbox" id="example2"> <label -->
<!-- 										class="onoffswitch-label" for="example2"> <span class="onoffswitch-inner"></span> <span -->
<!-- 										class="onoffswitch-switch"></span> -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="setings-item"> -->
<!-- 							<span> Enable history </span> -->
<!-- 							<div class="switch"> -->
<!-- 								<div class="onoffswitch"> -->
<!-- 									<input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example3"> <label -->
<!-- 										class="onoffswitch-label" for="example3"> <span class="onoffswitch-inner"></span> <span -->
<!-- 										class="onoffswitch-switch"></span> -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="setings-item"> -->
<!-- 							<span> Show charts </span> -->
<!-- 							<div class="switch"> -->
<!-- 								<div class="onoffswitch"> -->
<!-- 									<input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example4"> <label -->
<!-- 										class="onoffswitch-label" for="example4"> <span class="onoffswitch-inner"></span> <span -->
<!-- 										class="onoffswitch-switch"></span> -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="setings-item"> -->
<!-- 							<span> Offline users </span> -->
<!-- 							<div class="switch"> -->
<!-- 								<div class="onoffswitch"> -->
<!-- 									<input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example5"> <label -->
<!-- 										class="onoffswitch-label" for="example5"> <span class="onoffswitch-inner"></span> <span -->
<!-- 										class="onoffswitch-switch"></span> -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="setings-item"> -->
<!-- 							<span> Global search </span> -->
<!-- 							<div class="switch"> -->
<!-- 								<div class="onoffswitch"> -->
<!-- 									<input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example6"> <label -->
<!-- 										class="onoffswitch-label" for="example6"> <span class="onoffswitch-inner"></span> <span -->
<!-- 										class="onoffswitch-switch"></span> -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="setings-item"> -->
<!-- 							<span> Update everyday </span> -->
<!-- 							<div class="switch"> -->
<!-- 								<div class="onoffswitch"> -->
<!-- 									<input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example7"> <label -->
<!-- 										class="onoffswitch-label" for="example7"> <span class="onoffswitch-inner"></span> <span -->
<!-- 										class="onoffswitch-switch"></span> -->
<!-- 									</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 						<div class="sidebar-content"> -->
<!-- 							<h4>Settings</h4> -->
<!-- 							<div class="small">I belive that. Lorem Ipsum is simply dummy text of the printing and typesetting -->
<!-- 								industry. And typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the -->
<!-- 								1500s. Over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</div> -->
<!-- 						</div> -->

<!-- 					</div> -->
<!-- 				</div> -->

<!-- 			</div> -->



<!-- 		</div> -->
	</div>
</body>
</html>
