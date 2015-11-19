<%@page import="com.edaisong.toolscore.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<script src="<%=Config.adminurl%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				<input type="text" placeholder="请输入Redis键"
					class="input-sm form-control" id="txtKey" name="key"
					style="width: 250px; height: 34px;" value="" />
查询类型：<input type="radio" value="1" name="sType" checked="checked" id="selmh" />模糊查询 
<input type="radio" value="2" name="sType" id="seljz"/>精准查询
				<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
					style="margin-left: 3px;">查询</button>
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
	$.post("<%=Config.adminurl%>/admintools/redisdo",{key:key,sType:sType},function(d){
		if(d==""){
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
	arr.push("</tr>");
	return arr;
}
$(function(){
	$("#btnSearch").click(function(){
		funGetRedis(0,"");
	});
});
</script>