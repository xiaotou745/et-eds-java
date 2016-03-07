<%@page import="com.edaisong.core.enums.OrderStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.OrderListModel"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<style type="text/css">
.inputcss{
width: 220px;
height: 32px;
color: #888;
font-size: 14px;
padding-left: 7px;
border: 1px solid #c0c2cb;
border-radius: 3px 0 0 3px;
float: left;
margin-top: 6px;
line-height: 30px;
}

</style>
<%	
String basePath =PropertyUtils.getProperty("java.business.url");
String BusList=(String)request.getAttribute("BusList");
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/autocomplete.css">
<script src="<%=basePath%>/js/jquery.autocomplete.js"></script>
<div class="top cb">

			<h3 class="cb">
				交易明细
			</h3>
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
	        <input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
			<div class="function" style="height:100px;">		
				<span class="fl">交易方式</span>
				<select class="fl"  name="groupid"  id="groupid">
					<option value="-1">全部</option>
					<option value="1">消费集团</option>
					<option value="0">门店自费</option>								
				</select>
				<span class="fl">交易类型</span>
				<select class="fl"  name="recordtype"  id="recordtype">
					<option value="-1">全部</option>
					<option value="1">发布订单</option>
					<option value="2">取消订单</option>
					<option value="8">订单菜品费</option>
					<option value="13">返还配送费</option>					
				</select>
				<span class="fl">交易时间</span>
				<label class="fl">
					<input type="radio" name="timeType" value="0" checked="checked">
					今日
				</label>
				<label class="fl">
					<input type="radio" name="timeType"  value="1">
					7天
				</label>
				<label class="fl">
					<input type="radio" name="timeType" value="2">
					30天
				</label>
				<label class="fl">
					<input type="radio" name="timeType" value="3">
					区间
				</label>
				<span class="intime">
				<input type="text" disabled="disabled" class="dinput" id="operateTimeStart" name="operateTimeStart" />
				<s onClick="WdatePicker({el:'operateTimeStart',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'operateTimeEnd\')||\'new Date()\'}'});"></s></span>
				<span class="inblock">至</span>
				<span class="intime"><input type="text" class="dinput" disabled="disabled" id="operateTimeEnd" name="operateTimeEnd">
				<s onClick="WdatePicker({el:'operateTimeEnd',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'operateTimeStart\')}',maxDate:new Date()});"></s></span>
					 <br/>
				<div style="clear:both"></div>
				<span class="fl">订单号</span>
				<input type="text" class="inputcss" placeholder="订单号, 流水号" id="orderNo" name="orderNo">
			
			    <span class="fl">门&nbsp;&nbsp;&nbsp;&nbsp;店</span>
				<input type="text" class="inputcss"  id="businessName" style="margin-right:10px;" />
				<input type="hidden"  id="businessid" name="businessid"/>

				<input type="button" class="fr" value="搜索" id="btnSearch" style="line-height:30px;float:left">
						    	<input type="button" class="fr" value="导出报表" id="btnExport" style="line-height:30px;float:left">
			</div>
			</form>
</div>
		<div class="bottom bottom2 bottom3" id="content">
		</div>
<script>
$(function(){
	var businessNameDate=<%=BusList%>;
	 $("#businessName").AutoComplete({
	        data: businessNameDate,
	        ajaxDataType: "json",
	        width: 240,
	        listStyle: "custom",
	        matchHandler: function() {
	            return ! 0
	        },
	        afterSelectedHandler: function(d) {
	        	$('#businessName').val(d.label);////1.文本框ID
	        	$('#businessid').val(d.value);////2.隐藏域文本框ID
	        },
	        createItemHandler: function(t, i) {
	            var s = $("<div " + (i.unlink ? 'class="disabled"': "") + ">" + i.label + (i.unlink ? "[已解绑]": "") + "</div>");
	            return s
	        }
	    })
	   // <!-- 下拉框 END-->

	})
	
var searchType=0;
var jss = {
	search : function(currentPage) {
		
		var date1=$("#operateTimeStart").val();
		var date2=$("#operateTimeEnd").val();
		if(date1!=null&&date2!=null&&date1!=""&&date2!=""){
		    var date11=new Date(date1);
		 	var date22=new Date(date2);
		 	var date1Seconds=new Date(date11.getFullYear(),date11.getMonth()+2,date11.getDate());
		 	var date2Seconds=new Date(date22.getFullYear(),date22.getMonth(),date22.getDate());
		    if(date1Seconds<date2Seconds)
		    {
		    	alert("查询开始日期与结束日期最大间隔为两个月！")
		    	return;
		    }
		} 		
		  
		if(searchType==0){//点击了查询按钮
			$("#_hiddenCurrentPage").val(currentPage);
			 var data=$("#searchForm").serialize();
				$.post("<%=basePath%>/businessbalancerecord/grouplistdo",data, function(d) {
					$("#content").html(d);
				});
		}else{//点击了右上角的放大镜查询
			 var data={currentPage:currentPage,search:$("#customerInfo").val()};
				$.post("<%=basePath%>/businessbalancerecord/customerlistdo",data, function(d) {
					$("#content").html(d);
				});
		}

	}
}
jss.search(1);
$("#btnSearch").click(function() {
	searchType=0;
	$("#customerInfo").val("");
	var startDate = $('#operateTimeStart').val();
    var endDate = $('#operateTimeEnd').val();
    if (startDate != "" && endDate != "") {
        var intStartDate = startDate.replace(/-/g, "");
        var intEndDate = endDate.replace(/-/g, "");
        if (intStartDate > intEndDate) {
            alert('开始日期不能大于结束日期');
            $('#operateTimeStart').val("");
            return;
        }
    }
	jss.search(1);
});
$("#customerSearch").click(function() {
	searchType=1;
	jss.search(1);
});
$("input[type='radio']").click(function() {
	var selected=$('input[name="timeType"]:checked').val();
	if(selected!="3"){
		$("#operateTimeStart").attr("disabled","disabled");
		$("#operateTimeEnd").attr("disabled","disabled");
		$("#operateTimeStart").val("");
		$("#operateTimeEnd").val("");
	}else{
		$("#operateTimeStart").removeAttr("disabled");
		$("#operateTimeEnd").removeAttr("disabled");
	}
});

//导出功能
$("#btnExport").click(function() {	
	    var groupid = $("#groupid").val();
	    var recordtype = $("#recordtype").val();
	    var timeType=$('input[name="timeType"]:checked').val();
	    var operateTimeStart = $("#operateTimeStart").val();
	    var operateTimeEnd = $("#operateTimeEnd").val();	
        var url = "<%=basePath%>/businessbalancerecord/exportgrouplist?groupid=" + groupid 
        		+  "&recordtype=" + recordtype +"&timeType=" + timeType + "&operateTimeStart=" + operateTimeStart
        		+ "&operateTimeEnd=" + operateTimeEnd;     
        window.location.href = url;
        return true;
});
</script>