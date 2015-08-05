<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.api.service.impl.PublicProvinceCityService"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("static.admin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
	    <div class="col-lg-12">
	        <div class="input-group" style="margin-bottom:5px;">
	            <input type="text" placeholder="请输入账号名称" class="input-sm form-control" id="txtKeyword" style="width:250px;height:34px;" value="<%=request.getAttribute("cityname")==null?"":request.getAttribute("cityname")%>"/>
	            <button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" style="margin-left:3px;" data-toggle="modal" data-target="#myModal" id="addUser">添加用户</button>
	        </div>
	    </div>
	</div>
</div>
 
<div id="content">
	
</div>



<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-lg">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">用户操作</h4>
	</div>
<div class="modal-body">

  账号名称：<input id="txtUserName"/><br/><br/>
登录名称：<input id="txtLoginName"/><br/><br/>
登录密码：<input type="password" id="txtPwd"/><br/><br/>
确认密码：<input type="password" id="txtConfirmPwd"/><br/><br/>
城市选项：<select id="selCity"><option value="1">全部城市权限</option><option value="2">部分城市权限</option></select>
<br/>
<div style="padding-left:65px;display:none" id="divcity">
<%
List<AreaModel> listArea = (List<AreaModel>) request.getAttribute("listArea");
for(AreaModel item:listArea)
{
	%>
	<input type="checkbox" value="<%=item.getCode()%>" id="chkcity<%=item.getCode()%>"/>
	<label for="chkcity<%=item.getCode()%>"><%=item.getName() %></label> &nbsp;
	<%	
}
%>
</div>
<br/>
物流公司：<a href="javascript:void(0)" id="hrefdc">请选择</a><br/>
<div style="padding-left:65px;" id="divdc">
	<%
	List<DeliveryCompany> listDc=(List<DeliveryCompany>)request.getAttribute("listDc");
	for(DeliveryCompany item:listDc)
	{
		%>
		<input type="checkbox" value="<%=item.getId() %>" id="chkdc<%=item.getId()%>"/>
		<label for="chkdc<%=item.getId() %>"><%=item.getDeliverycompanyname() %></label>
		<%
	}
	%>

</div>
<br/>
是否启用：<input type="radio" value="1" name="radstatus" id="radyes"/>
	  <label for="radyes">启用</label>
	  <input type="radio" value="0" name="radstatus" id="radno"/>
	  <label for="radno">不启用</label>
</div>


	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" class="btn btn-primary">保存</button>
	</div>
    </div>
</div>
</div>

<script>


var jss={
		search:function(currentPage){
			var keyword=$("#txtKeyword").val();
			$.post("<%=basePath%>/account/listdo",{CurrentPage:currentPage,Keyword:keyword,m:Math.random()},function(d){
				$("#content").html(d);
			});
		},
		reset:function(){
			$("#txtUserName").val("");
			$("#txtLoginName").val("");
			$("#txtPwd").val("");
			$("#txtConfirmPwd").val("");
			$("#selCity").val(1);
			$("#divcity").hide();
			$("#divdc").hide();
		    $("#radyes").attr("checked","checked");
		},
		reloadInfo:function(){
			
		}
	}
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
$("#addUser").click(function(){
	jss.reset();
});
//绑定城市事件
$("#selCity").change(function(){
	var selCity=$("#selCity").val();
	if(selCity==1) $("#divcity").hide();
	else $("#divcity").show(500);
});
//绑定物流公司
$("#hrefdc").click(function(){
		if($("#divdc").is(":hidden")){
			$("#divdc").show(500);
		}
		else $("#divdc").hide(500);
});
</script>