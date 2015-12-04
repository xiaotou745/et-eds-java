<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolscore.util.ParseHelper"%>
<%@page import="com.edaisong.toolsentity.AuthorityMenuClass"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
	List<String> appNameList = (List<String>) request.getAttribute("appNameList");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
				<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
							   <%=HtmlHelper.getSelect("appname", appNameList, "", "",null,null,"全部")%>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
</div>
</div>
<table style="width:80%">
<tr>
<td style="width:400px;">
		<div class="control-group" style="width:400px;">
			<div class="controls">
				<button class="btn btn-success" id="btnExpanAll" type="button">展开/折叠</button>
				<button class="btn btn-success" type="button" id="addNewMenu">新增菜单</button>
			</div>
			<div class="controls">
				<div class="test treeview" id="treeview11"></div>
			</div>
		</div>

</td>
<td >
<div style="margin-top:-45px;" id="detail">
	<div class="controls">
				<button class="btn btn-success" type="button">编辑菜单</button>
				<button class="btn btn-success" type="button" id="new2ji">新增二级菜单</button>
				<button class="btn btn-success" type="button" id="newbutton">新增按钮</button>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">编号:</label>
				<div class="col-sm-8">
				   <input id="Id" class="form-control" type="text" name="Id" readonly="readonly" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">父级编号:</label>
				<div class="col-sm-8">
				   <input id="ParId" class="form-control" type="text" name="ParId" readonly="readonly" />
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">菜单名称:</label>
				<div class="col-sm-8">
				   <input id="MenuName" class="form-control" type="text" name="MenuName"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">是否锁定:</label>
				<div class="col-sm-8">
				   <input id="BeLock" class="form-control" type="text" name="BeLock" readonly="readonly" />
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">页面URL:</label>
				<div class="col-sm-8">
				   <input id="Url" class="form-control" type="text" name="Url"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">是否按钮:</label>
				<div class="col-sm-8">
				   <input id="IsButton" class="form-control" type="text" name="IsButton"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">AuthCode:</label>
				<div class="col-sm-8">
				   <input id="AuthCode" class="form-control" type="text" name="AuthCode"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">父级名称:</label>
				<div class="col-sm-8">
				   <input id="ParMenuName" class="form-control" type="text" name="ParMenuName"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		

</div>
</td>
</tr>
</table>





<!-- 新增菜单 -->
<div tabindex="-1" class="modal inmodal" id="addNewMenubox" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增一级菜单</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>菜单名称：</label> <input name="menuName" id="1jimenuName" type="text"/>
						</div>
						<div class="control-group">
							<label>AuthCode：</label> <input name="menuName" id="1jiAuthCode" type="text"/>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="save1ji()">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<!-- 新增二级菜单 -->
<div tabindex="-1" class="modal inmodal" id="addNewMenubox2ji" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增二级菜单</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>菜单名称：</label> <input name="menuName" id="2jimenuName" type="text"/>
						</div>
						<div class="control-group">
							<label>AuthCode：</label> <input name="menuName" id="2jiAuthCode" type="text"/>
						</div>
						<div class="control-group">
							<label>Url：</label> <input name="menuName" id="2jiUrl" type="text"/>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="save2ji()">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<!-- 新增按钮 -->
<div tabindex="-1" class="modal inmodal" id="addNewMenubox3ji" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增按钮</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>按钮名称：</label> <input name="menuName" id="3jimenuName" type="text"/>
						</div>
						<div class="control-group">
							<label>AuthCode：</label> <input name="menuName" id="3jiAuthCode" type="text"/>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="save3ji()">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<script>
//构建空间树
$('#detail').hide();
var detail="";
var $checkableTree;
var jss={
		search:function(currentPage){
			var url="<%=basePath%>/admintools/menulistdo";
			var par={
					"appName":$('#appname').val(),
			};
			$.post(url,par,function(result){
				if (result!="")
				{
	       		 $checkableTree=$('#treeview11').treeview({
	       	        data: result,
	       	        levels: 1,
	       	        showIcon: false,
	       	        showCheckbox: true,
	       	        onNodeChecked: function(event, node) {
	       	        	$checkableTree.treeview('uncheckAll', { silent: true });
	       	        	$checkableTree.treeview('checkNode', [ node.nodeId, { silent: true } ]);
	       	        	ClickItem();
	       	        },
	       	        onNodeUnchecked: function (event, node) {
	       	        	$('#detail').hide();
	       	        	detail="";
	       	        	$checkableTree.treeview('uncheckNode', [ node.nodeId, { silent: true } ]);
	       	        }
	       	      });
	               $('#myModal').modal('show');
	           } 
			});
		}
	}
	var expandstatus=0;//展开状态
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
	//对勾选中事件
	function ClickItem(){
		$('#detail').show();
		var currId=$checkableTree.treeview('getChecked')[0].id;
		var IsButton=$checkableTree.treeview('getChecked')[0].isbutton;
		var par={
				"appName":$('#appname').val(),
				"parId":currId
		};
		$.post("<%=basePath%>/admintools/menudetail",par,function(da){
			console.log(da);
			var d=jQuery.parseJSON(da);
			detail=d;
			if(d.jibie==1)
			{
				$('#newbutton').hide();
				$('#new2ji').show();
			}
			if(d.jibie==2)
			{
				$('#newbutton').show();
				$('#new2ji').hide();
			}
			if(d.jibie==3)
			{
				$('#newbutton').hide();
				$('#new2ji').hide();
			}
			$('#Id').val(d.id);
			$('#ParId').val(d.parid);
			$('#MenuName').val(d.menuname);
			$('#BeLock').val(d.belock);
			$('#Url').val(d.url);
			$('#IsButton').val(d.isbutton);
			$('#AuthCode').val(d.authCode);
			$('#ParMenuName').val(d.parMenuName);
		});
	}
	//编辑菜单
	function editCaidan()
	{
		
	}
	
	//新增菜单
	$('#addNewMenu').click(function(){
		$('#addNewMenubox').modal('show');
	});
	//新增2级菜单
	$('#new2ji').click(function(){
		$('#addNewMenubox2ji').modal('show');
	});
	//新增按钮
	$('#newbutton').click(function(){
		$('#addNewMenubox3ji').modal('show');
	});
	//保存1级菜单
	function save1ji(){
		var menuName = $("#1jimenuName").val();
		var authcode=$("#1jiAuthCode").val();
		
		if(menuName == ''){
			alert("请输入菜单名称!");
			return;
		}
		if(authcode == ''){
			alert("请输入AuthCode!");
			return;
		}
		var par={"parid":0,
				"menuname":menuName,
				"authCode":authcode,
				"appName":$('#appname').val()};
		postsave(par);
	}
	//保存2级菜单
	function save2ji(){
		var menuName = $("#2jimenuName").val();
		var authcode=$("#2jiAuthCode").val();
		var url=$("#2jiAuthCode").val();
		if(menuName == ''){
			alert("请输入菜单名称!");
			return;
		}
		if(url==''){
			alert("请输入Url!");
			return;
		}
		if(authcode == ''){
			alert("请输入AuthCode!");
			return;
		}
		var par={"parid":detail.id,
				"menuname":menuName,
				"authCode":authcode,
				"url":url,
				"appName":$('#appname').val()};
		
		postsave(par);
	}
	//保存2级菜单
	function save3ji(){
		var menuName = $("#3jimenuName").val();
		var authcode=$("#3jiAuthCode").val();
		if(menuName == ''){
			alert("请输入按钮名称!");
			return;
		}
		if(authcode == ''){
			alert("请输入AuthCode!");
			return;
		}
		var par={"parid":detail.id,
				"menuname":menuName,
				"authCode":authcode,
				"isbutton":1,
				"appName":$('#appname').val()};
		
		postsave(par);
	}
	//保存菜单
	function postsave(par)
	{
		$.post("<%=basePath%>/admintools/addnewmenu",par,function(d){
			if(d>0){
				alert('添加菜单成功!');
				$('#addNewMenubox').modal('hide');
				$('#addNewMenubox2ji').modal('hide');
				$('#addNewMenubox3ji').modal('hide');
				detail="";
				$('#detail').hide();
				jss.search(0);
				}
			else{
				alert('添加菜单失败!');
				}
		});
	}
	jss.search(0);
</script>

