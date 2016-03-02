<%@page import="com.edaisong.toolscore.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%
List<String> appNameList = (List<String>) request.getAttribute("appNameList");
%>
<script src="<%=Config.adminurl%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
							   <%=HtmlHelper.getSelect("selappname", appNameList, "", "",null,null,"全部")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">Redis键:</label>
							<div class="col-sm-8">
							  		<input type="text" placeholder="请输入Redis键"
					class="form-control" id="txtKey" name="key"  value="" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"> 查询类型:</label>
							<div class="col-sm-8">
							<input type="radio" value="1" name="sType" checked="checked" id="selmh" />模糊查询 
							<input type="radio" value="2" name="sType" id="seljz"/>精准查询
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left:3px;">查询</button>
					</div>
			</div>
		</div>
	</div>
</div>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>编号</th>
			<th>键</th>
			<th>值</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody id="Content">
	</tbody>
</table>

<script>
function funGetRedis(mtype,mkey){
	var key=mkey==""?$("#txtKey").val():mkey;
	
	if(key==""){alert("键不能为空!");return;}
	var sType;
	if(mtype>0){
		sType=mtype;
	}
	else{
		sType = $("input[name='sType']:checked").val();
	}
	var param={"appName":$("#selappname").val(),"key":key,"sType":sType};
	$.post("<%=Config.adminurl%>/admintools/redisdo",param,function(d){
		if(d==""){
			alert("结果为空");
			$("#Content").html("");
			return;
		}
		var arr=new Array();
		if(sType==1)
			{
			//列表
			var result=d.split(',');
			for(var i=0;i<=result.length;i++)
				{
					arr.push(
							detail(i+1,result[i],"<a href='javascript:void(0)' onclick=\"funGetRedis(2,'"+result[i]+"')\">查看详情</a>"));	
				}
			}
		else{
			//详情
			arr.push(detail(1,key,d));	
		}
		$("#Content").html(arr.join(''));
	});
}
function detail(id,key,val){
	if(key==undefined || key=="") return ;
	var arr=new Array();
	arr.push("<tr>");
	arr.push("<td>"+id+"</td>");
	arr.push("<td>"+key+"</td>");
	arr.push("<td>"+val+"</td>");
	arr.push("<td><a href='javascript:void(0)' onclick=\"deleteKey('"+key+"')\">删除</a></td>");
	arr.push("</tr>");
	return arr;
}
function deleteKey(key) {
	if(!confirm("确定要删除？")){
		return;
	}
	var paramaters = {
			"appName":$("#selappname").val(),
			"key" :  key
		};
	var url = "<%=Config.adminurl%>/admintools/redisremove";
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
};
$(function(){
	$("#btnSearch").click(function(){
		funGetRedis(0,"");
	});
});
</script>