<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.api.service.impl.PublicProvinceCityService"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.AuthorityRole"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
UserContext context=UserContext.getCurrentContext(request);
int groupId=context.getGroupId();
List<AuthorityRole> roleData = (List<AuthorityRole>) request.getAttribute("roleData");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
								<label class="col-sm-4 control-label">账号名称:</label>
							<div class="col-sm-8">
								 <input type="text" placeholder="请输入账号名称" class="input-sm form-control" id="txtKeyword" style="width:250px;height:34px;" value="<%=request.getAttribute("cityname")==null?"":request.getAttribute("cityname")%>"/>
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
				<button type="button" class="btn btn-w-m btn-primary" id=btnSearch style="margin-left:3px;">查询</button>
	            <button type="button" class="btn btn-w-m btn-primary" style="margin-left:3px;" data-toggle="modal" data-target="#myModal" id="addUser">添加用户</button>
					</div>
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
<input name="groupId" id="groupId" type="hidden" value="<%=groupId %>" />
  账号名称：<input id="txtUserName" class="form-control"/><br/><br/>
登录名称：<input id="txtLoginName" class="form-control"/><br/><br/>
登录密码：<input type="password" id="txtPwd" class="form-control"/><br/><br/>
确认密码：<input type="password" id="txtConfirmPwd" class="form-control"/><br/><br/>
城市选项：<select id="selAcountType"  class="form-control m-b">
<option value="1">全部城市权限</option>
<option value="2">部分城市权限</option></select>
<br/>
<div style="padding-left:65px;display:none" id="divcity">
<%
List<AreaModel> listArea = (List<AreaModel>) request.getAttribute("listArea");
for(AreaModel item:listArea)
{
	%>
	<input type="checkbox" name="checkMenus" value="<%=item.getCode()%>" id="chkcity<%=item.getCode()%>"/>
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
		<input type="checkbox" name="checkDeliveryCompany" value="<%=item.getId() %>" id="chkdc<%=item.getId()%>"/>
		<label for="chkdc<%=item.getId()%>"><%=item.getDeliverycompanyname() %></label>
		<%
	}
	%>

</div>
<br/>
是否启用：<input type="radio" value="1" name="radstatus" id="radyes" checked/>
	  <label for="radyes">启用</label>
	  <input type="radio" value="0" name="radstatus" id="radno"/>
	  <label for="radno">不启用</label>
</div>


	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" id="saveuser" class="btn btn-primary">保存</button>
	</div>
    </div>
</div>
</div>
<div class="modal inmodal fade" id="authModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">分配权限</h4>
			</div>

			<div class="modal-body"
				style="height: 500px; overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;">
				<fieldset>
					<div class="control-group">
						按照角色<input type="radio" id="roletype" name="objtype" value="0">
						单独分配<input type="radio" id="usertype" name="objtype" value="1">
					</div>
					<div class="control-group" id="rolediv">
						<%=HtmlHelper.getSelect("roleid", roleData, "rolename", "id",null,"-1","请选择")%>
					</div>
					<div class="control-group" id="userdiv" style="display: hidden;">
						<div class="controls">
							<button class="btn btn-success" id="btnExpanAll" type="button">展开/折叠</button>
							<button class="btn btn-success" id="btn-check-all" type="button">全选/全消</button>
						</div>
						<div class="controls">
							<div class="test treeview" id="treeview11"></div>
						</div>
					</div>
				</fieldset>
				<div class="control-group"></div>
			</div>
			<div class="modal-footer">
				<input id="userid" type="hidden" name="userid" /> <input
					id="userroleid" type="hidden" name="userroleid" />
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="saveauth">保存</button>

			</div>
		</div>
	</div>
</div>
<script>


var jss={
		search:function(currentPage){
			var keyword=$("#txtKeyword").val();
			$.post("<%=basePath%>/account/listdo",{currentPage:currentPage,Keyword:keyword,m:Math.random()},function(d){
				$("#content").html(d);
			});
		},
		reset:function(){
			userid=-1;
			optype=0;
		    oldcityrelations="";
		    olddeliveryrelations="";
			$("#txtUserName").val("");
			$("#txtLoginName").val("");
            $("#txtUserName").removeAttr("disabled");
            $("#txtLoginName").removeAttr("disabled");
			$("#txtPwd").val("");
			$("#txtConfirmPwd").val("");
			$("#selAcountType").val(1);
			$("#divcity").hide();
			$("#divdc").hide();
		    $("#radyes").attr("checked","checked");
		    $("input[name='checkDeliveryCompany']").each(function() {
		        $(this).prop("checked", false);
		    });
		    $("input[name='checkMenus']").each(function() {
		        $(this).prop("checked", false);
		    });
		},
		reloadInfo:function(){
			
		}
	}
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
function updateuser(cityCodeList,DCidList){
	if($("#txtUserName").val()==""){
		alert("用户名不能为空");
		return;
	}
	if($("#txtLoginName").val()==""){
		alert("登录名不能为空");
		return;
	}
	if($("#txtPwd").val()!=$("#txtConfirmPwd").val()){
		alert("密码和确认密码不一致");
		return;
	}
	var paramaters = {
			"id":userid,
			"password":$("#txtPwd").val(),
			"accounttype":$("#selAcountType").val(),
			"status":$('input[name="radstatus"]:checked').val(),
			"oldcityrelations":oldcityrelations,
			"olddeliveryrelations":olddeliveryrelations,
			"newcityrelations":cityCodeList,
			"newdeliveryrelations":DCidList
		};
		var url = "<%=basePath%>/account/updateuser";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败");
				}
			}
		});
}
var userid=-1;
var optype=0;
$("#saveuser").click(function(){
	var cityCodeList = "";
    $("input[name='checkMenus']").each(function () {
        if ($("#selAcountType").val() == 1) {
            cityCodeList = cityCodeList + $(this).val() + ",";
        }
        else {
            if ($(this).is(':checked')) {
                cityCodeList = cityCodeList + $(this).val() + ",";
            }
        }
    });
    var DCidList = "";
    $("input[name='checkDeliveryCompany']").each(function () {
        if ($(this).is(':checked')) {
            DCidList = DCidList + $(this).val() + ",";
        }
    });
    if (cityCodeList.length > 0)
        cityCodeList = cityCodeList.substring(0, cityCodeList.length - 1);
    if (DCidList.length > 0)
        DCidList = DCidList.substring(0, DCidList.length - 1);
	if(optype==1&&userid>0){
		return updateuser(cityCodeList,DCidList);
	}
	userid=-1;
	optype=0;
	if($("#txtUserName").val()==""){
		alert("用户名不能为空");
		return;
	}
	if($("#txtLoginName").val()==""){
		alert("登录名不能为空");
		return;
	}
	if($("#txtPwd").val()==""){
		alert("密码不能为空");
		return;
	}
	if($("#txtPwd").val()!=$("#txtConfirmPwd").val()){
		alert("密码和确认密码不一致");
		return;
	}
	var paramaters = {
			"username" :  $("#txtUserName").val(),
			"loginname" : $("#txtLoginName").val(),
			"password":$("#txtPwd").val(),
			"groupid":$("#groupId").val(),
			"accounttype":$("#selAcountType").val(),
			"status":$('input[name="radstatus"]:checked').val(),
			"cityrelations":cityCodeList,
			"deliveryrelations":DCidList
		};
		var url = "<%=basePath%>/account/adduser";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败:账号名称或登录名称已经存在！");
				}
			}
		});
});
$("#addUser").click(function(){
	jss.reset();
});
function modify(id) {
	var paramaters = {
			"userId" :  id
		};
		var url = "<%=basePath%>/account/getuserinfo";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
			    userid=id;
				optype=1;
				$("#txtUserName").val(result.UserName);
				$("#txtLoginName").val(result.LoginName);
		        $("#txtUserName").attr("disabled", "true");
		        $("#txtLoginName").attr("disabled", "true");
				$("#txtPwd").val("");
				$("#txtConfirmPwd").val("");
				if(result.Status==1){
					$("#radyes").prop('checked','checked')
				}
				else{
					$("#radno").prop('checked','checked')
				}
				queryauthralation(id);
		        $('#myModal').modal('show');
			}
		});

}
var oldcityrelations="";
var olddeliveryrelations="";
function queryauthralation(accountId) {
    //每次加载数据前先清除
    oldcityrelations="";
    olddeliveryrelations="";
    $("input[name='checkDeliveryCompany']").each(function() {
        $(this).prop("checked", false);
    });
    $("input[name='checkMenus']").each(function() {
        $(this).prop("checked", false);
    });
    var paramaters = { "userId": accountId };
    var strCityNameList = "";
    var url = "<%=basePath%>/account/getauthoritycityrelations";
    $.ajax({
        type: 'POST',
        async: false,
        url: url,
        data: paramaters,
        success: function (result) {
        	var authlength=$("input[name='checkMenus']").length;
        	for (var i = 0; i < result.length; i++) {
        		if(i==0){
        			oldcityrelations=result[i];
        		}else{
        			oldcityrelations=oldcityrelations+","+result[i];
        		}
        		if(result.length!=authlength){
                 	$("#chkcity" + result[i]).prop("checked", true);
        		}else{
        			$("#chkcity" + result[i]).prop("checked", false);
        		}
            }
        	if(result.length==authlength||result.length==0){
        		$("#selAcountType").val("1");
        		$("#divcity").hide();
        	}else{
        		$("#selAcountType").val("2");
        		$("#divcity").show(500);
        	}
        }
    });
    url = "<%=basePath%>/account/getauthoritydeliveryrelations";
    $.ajax({
        type: 'POST',
        async: false,
        url: url,
        data: paramaters,
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
        		if(i==0){
        			olddeliveryrelations=result[i];
        		}else{
        			olddeliveryrelations=olddeliveryrelations+","+result[i];
        		}
                $("#chkdc" + result[i]).prop("checked", true);
            }
            $("#divdc").show();
        }
    });
};
//绑定城市事件
$("#selAcountType").change(function(){
	var selCity=$("#selAcountType").val();
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
var oldAuth="";
var checkstatus=0;
var expandstatus=0;
var $checkableTree;
//全选全消
$('#btn-check-all').on('click', function (e) {
      if(checkstatus==0){
    	  checkstatus=1;
        $checkableTree.treeview('checkAll', { silent: true });
      }else{
    	  checkstatus=0;
    	  $checkableTree.treeview('uncheckAll', { silent: true }); 
      }
});

// 展开/折叠
$('#btnExpanAll').on('click', function (e) {
	if(expandstatus==0){
		expandstatus=1;
		 $checkableTree.treeview('expandAll', { levels: 10, silent: true });
      }else{
    	  expandstatus=0;
    	  $checkableTree.treeview('collapseAll', { silent: true });
      }
  
});

$("input[type='radio'][name='objtype']").change(function() { 
	var typeid=$("input[name='objtype']:checked").val();
	if(typeid=="0"){
  		$("#userdiv").hide();
          $("#rolediv").show();
      }else{
    		$("#userdiv").show();
            $("#rolediv").hide();
      }
}); 

//保存权限设置
$("#saveauth").click(function() {
	var typeid=$("input[name='objtype']:checked").val();
	 if(typeid=="0"){
		 if("-1"==$("#roleid").val()){
			 alert("请选择一个角色");
			 return;
		 }
		 if($("#userroleid").val()==$("#roleid").val()){
			 alert("没有变更，不需要保存");
			 return;
		 }
		 //原来用户没有角色，则此时需要删掉原来的权限
		 var needUpdateAuth=true;
		if(parseInt($("#userroleid").val())>0){
			needUpdateAuth=false;
		}
		updateRole($("#roleid").val(),needUpdateAuth);
	 }
	 else{
			var newAuth = "";
	        var newChecked=$checkableTree.treeview('getChecked');
			if(newChecked!=undefined&&newChecked.length>0){
				 for (var i = 0; i < newChecked.length; i++){
					 newAuth += (newChecked[i].id+ ",");
				}
				 newAuth=newAuth.substring(0,newAuth.length-1);
			 }
			if(newAuth==oldAuth){
				alert("没有任何修改，不需要保存");
				return;
			}
			if (!confirm("确定要提交保存吗？")){
				return;
			}
			 var needUpdateRole=false;
			//如果当前用户以前有自己的角色，现在要单独配置权限，则应该将原来的角色置为0
			if(parseInt($("#userroleid").val())>0){
				needUpdateRole=true;
			}
			updateAuth(newAuth,oldAuth,needUpdateRole);
	 }
});
function updateRole(roleid,updateauth){
  var paramaters = {
			"userID" :  $("#userid").val(),
			"newRoleID" : roleid
		};
		var url = "<%=basePath%>/authmanage/updateroleid";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					if(updateauth){
						//用户原来是单独配置的角色，现在改为了按照角色配置，则应该将原来的权限删掉
						updateAuth("",oldAuth);
					}else{
						alert("操作成功");
						window.location.href = window.location.href;
					}
				} else {
					alert("操作失败");
				}
			}
		});
}
function updateAuth(newauths,oldauths,updaterole){
  var paramaters = {
			"userID" :  $("#userid").val(),
			"newAuth" : newauths,
			"oldAuth" : oldauths,
		};
		var url = "<%=basePath%>/authmanage/saveauth";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result == "") {
					if(updaterole){
						updateRole(0,false);
					}else{
						alert("操作成功");
						window.location.href = window.location.href;
					}
				} else {
					alert(result);
				}
			}
		});
}
//分配权限
function setauth(id) {
	var paramaters = { "userID": id};
  var url = "<%=basePath%>/authmanage/getroleid";
  $.ajax({
      type: 'POST',
      url: url,
      data: paramaters,
      success: function (result) {
      	if (result>0) {//当前用户已经有所属角色
      		$("#usertype").prop('checked',false); 
      		$("#userdiv").hide();
       		$("#roletype").prop('checked',true); 
            $("#rolediv").show();
            $("#roleid").val(result);
          }else{
          	$("#roletype").prop('checked',false); 
      		$("#rolediv").hide();
            $("#usertype").prop('checked',true); 
            $("#userdiv").show();
          }
        $("#userroleid").val(result);
        createAuthTree(id);
		$("#userid").val(id+"");
		$('#authModal').modal('show');
      }
  });
}
function createAuthTree(id) {
	var paramaters = { "userID": id};
  var url = "<%=basePath%>/authmanage/authlist";
	$.ajax({
		type : 'POST',
		url : url,
		data : paramaters,
		success : function(result) {
			if (result != "") {
				//var obj = $.parseJSON(result); 
				$checkableTree = $('#treeview11').treeview({
					data : result,
					levels : 1,
					showIcon : false,
					showCheckbox : true,
					onNodeChecked : function(event, node) {
						checkNode(0, event, node);
					},
					onNodeUnchecked : function(event, node) {
						checkNode(1, event, node);
					}
				});
				var oldChecked = $checkableTree.treeview('getChecked');
				if (oldChecked != undefined && oldChecked.length > 0) {
					for (var i = 0; i < oldChecked.length; i++) {
						oldAuth += (oldChecked[i].id + ",");
					}
					oldAuth = oldAuth.substring(0, oldAuth.length - 1);
				}
			}
		}
	});
}
function checkNode(type, event, node) {
	var parent = $checkableTree.treeview('getParent', node);
	if (type == 0) {//选中一个节点时，所有父节点选中，选中所有子节点选中
		checkChild(node);
		while (parent != undefined && parent.nodes != undefined
				&& parent.nodes.length > 0) {
			$checkableTree.treeview('checkNode', [ parent.nodeId, {silent : true} ]);
			parent = $checkableTree.treeview('getParent', parent);
		}
	} else {
		uncheckChild(node);
		//取消时，如果当前节点的兄弟节点都已经被取消选中了，则当前节点的父节点也应该取消选中
		if (node.isbutton=="0" &&
			parent != undefined && 
			parent.nodes != undefined&& 
			parent.state.checked) {
			var siblingNodes = $checkableTree.treeview('getSiblings', node);
			var needUncheckParent = true;
			if (siblingNodes.length > 0) {
				for (var i = 0; i < siblingNodes.length; i++) {
					if (siblingNodes[i].state.checked) {
						needUncheckParent = false;
						break;
					}
				}
			}
			if (needUncheckParent) {
				$checkableTree.treeview('uncheckNode', [ parent.nodeId, {silent : false} ]);
			}
		}
	}
}
//选中当前节点的所有子节点和孙子节点
function checkChild(node) {
	if (node.nodes != undefined && node.nodes.length > 0) {
		var childs = $checkableTree.treeview('findNodes', ['^' + node.id + '$', 'g', 'parentid' ]);
		if (childs != undefined && childs.length > 0) {
			$checkableTree.treeview('checkNode', [ childs, {silent : true} ]);
			for (var i = 0; i < node.nodes.length; i++) {
				checkChild(node.nodes[i]);
			}
		}
	}
}
//取消选中当前节点的所有子节点和孙子节点
function uncheckChild(node) {
	if (node.nodes != undefined && node.nodes.length > 0) {
		var childs = $checkableTree.treeview('findNodes', ['^' + node.id + '$', 'g', 'parentid' ]);
		if (childs != undefined && childs.length > 0) {
			$checkableTree.treeview('uncheckNode', [ childs, {silent : true} ]);
			for (var i = 0; i < node.nodes.length; i++) {
				uncheckChild(node.nodes[i]);
			}
		}
	}
}
</script>