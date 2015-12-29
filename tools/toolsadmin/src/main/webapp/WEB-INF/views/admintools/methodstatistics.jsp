<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%@page import="com.edaisong.toolscore.util.HtmlHelper"%>
<%@page import="com.edaisong.toolscore.util.EnumHelper"%>
<%@page import="java.util.Calendar"%>
<%
	String basePath =PropertyUtils.getProperty("java.toolsadmin.url");

List<String> appNameList = new ArrayList<String>();
appNameList.add("admin");
appNameList.add("apihttp");
appNameList.add("business");
appNameList.add("taobaoopenapi");
appNameList.add("renrenadmin");
appNameList.add("renrenapihttp");
String apihttpVersion = request.getAttribute("apihttpVersion").toString();
String renrenapihttpVersion = request.getAttribute("renrenapihttpVersion").toString();
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="<%=basePath%>/js/highcharts/js/highcharts.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="currentPage" id="_hiddenCurrentPage"
					value="1" />
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">系统名称:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("sourceSys", appNameList, "", "",
					null, null, null)%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">版本:</label>
							<div class="col-sm-8">
								<select name="appversion" class="form-control m-b" id="appversion">
								<option value='' selected='selected'>全部版本</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">请求类型:</label>
							<div class="col-sm-8">
								<input type="radio" value="-1" name="requestType"
									checked="checked" id="allreq" />全部 <input type="radio"
									value="0" name="requestType" id="pagereq" />页面 <input
									type="radio" value="1" name="requestType" id="ajaxreq" />ajax
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">异常:</label>
							<div class="col-sm-8">
								<input type="radio" value="-1" name="exceptionShowType" checked="checked"
									id="alltype" />查看全部 <input type="radio" value="1" name="exceptionShowType"
									id="normalreq" />正常 <input type="radio" value="2"
									name="exceptionShowType" id="seljz" />异常
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">请求时间:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" id="begin"/>
                                        <input type="hidden" value="" name="begin"  id="beginHidden"/>

                             </div>						
<!--                               <input type="text" name="begin" id="begin" -->
<!-- 									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'end\')||\'2020-10-01\'}'})" -->
<!-- 									class="form-control" />  -->
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value=""  id="end"/>
                                        <input type="hidden" value="" name="end"  id="endHidden"/>
                             </div>
<!-- 								<input type="text" name="end" id="end" -->
<!-- 									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'begin\')}',maxDate:'2020-10-01'})" -->
<!-- 									class="form-control" /> -->
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">排序方式:</label>
							<div class="col-sm-8">
								<input type="radio" checked="checked" 
									value="0" name="orderBy" />调用次数 <input
									type="radio" value="1" name="orderBy"/>平均耗时
									<input
									type="radio" value="2" name="orderBy"/>异常率
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">排序方式:</label>
							<div class="col-sm-8">
								<input type="radio"
									value="1" name="orderType" id="ASC" />升序 <input checked="checked" 
									type="radio" value="-1" name="orderType" id="DESC" />降序
							</div>
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">请求地址:</label>
							<div class="col-sm-8">
								<input type="text" placeholder="请输入请求地址" style="width:400px;"
					class="form-control" id="requestUrl" name="requestUrl"  value="" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary"
							id="btnSearch" style="margin-left: 3px;">查询</button>
							<span id="tip" style="color:red"></span>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
	<div class="col-lg-12">
		<div class="ibox-content" id="content2"></div>
	</div>
</div>
<input type="hidden" value="" id="apihttpversion"/>
<input type="hidden" value="" id="renrenapihttpversion"/>
<script>
$(function(){
	  $(' .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });
	  Highcharts.setOptions({                                                     
          global: {                                                               
              useUTC: false                                                       
          }                                                                       
      }); 
		$("#apihttpversion").val("<%=apihttpVersion%>");
		$("#renrenapihttpversion").val("<%=renrenapihttpVersion%>");
		$("#sourceSys").on("change",function(e){
			$("#appversion").html("<option value='' selected='selected'>全部版本</option>");
			if($("#sourceSys").val()=="apihttp"||$("#sourceSys").val()=="renrenapihttp"){
				var versions=$("#"+$("#sourceSys").val()+"version").val();
				if(versions!=""){
					var result=versions.split(",");
					for(j=0;j<result.length;j++){  
						$("#appversion").append("<option value='"+result[j]+"'>"+result[j]+"</option>");   
					}
				}
			}
		});
});

function showChart(result){                                                                  
	  Highcharts.setOptions({                                                     
          global: {                                                               
              useUTC: false                                                       
          }                                                                       
      });   
	$('#content').html("");    

	    $('#content').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '接口调用统计'
	        },
	        subtitle: {
	            text: '调用次数，平均执行时间，异常率'
	        },
	        xAxis: {
	            categories: result.urls
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '次数/异常率/平均耗时'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.2f} </b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '调用次数',
	            data:result.numData
	        }, {
	            name: '平均耗时',
	            data: result.timeData
	        }, {
	            name: '异常率',
	            data: result.rateData
	        }]
	    });			
}
function showtable(result){
	var contentHtml="<table  class='table table-striped table-bordered table-hover dataTables-example'><thead><tr><th>RequestUrl</th><th>请求次数</th><th>异常率(%)</th><th>平均耗时(ms)</th><th>最小耗时(ms)</th><th>最大耗时(ms)</th></tr></thead><tbody>";
	for(var i=0;i<result.urls.length;i++){
		contentHtml+="<tr><td>"+result.urls[i]+"</td>"+
		"<td>"+result.numData[i]+"</td>"+
		"<td>"+result.rateData[i].toFixed(2)+"</td>"+
		"<td>"+result.timeData[i].toFixed(2)+"</td>"+
		"<td>"+result.minTimeData[i]+"</td>"+
		"<td>"+result.maxTimeData[i]+"</td></tr>";
	}
	contentHtml+="</tbody></table>";
	$("#content2").html(contentHtml);
}
var jss={
		search:function(currentPage){
			if($("#begin").val()==""){
				alert("开始日期不能为空");
				return;
			}
			if($("#end").val()==""){
				alert("结束日期不能为空");
				return;
			}
			  var intStartDate = $("#begin").val().replace(/-/g, "");
		      var intEndDate = $("#end").val().replace(/-/g, "");
		      if (intStartDate > intEndDate) {
		          alert('开始日期不能大于结束日期');
		          $('#end').val("");
		          return;
		      }
			$("#beginHidden").val($("#begin").val()+" 00:00:00");
			$("#endHidden").val($("#end").val()+" 23:59:59");

			var paramaters=$("#searchForm").serialize();
			var url = "<%=basePath%>/admintools/methodstatisticsdo";
			$("#tip").html("正在查询。。。");
			$("#btnSearch").attr("disabled",true);
			$.ajax({
				type : 'POST',
				url : url,
				data : paramaters,
				success : function(result) {
					$("#tip").html("");
					$("#btnSearch").attr("disabled",false);
					//alert(result);
					showChart(result);
					showtable(result);
				},error:function(data){
					$("#tip").html("");
					$("#btnSearch").attr("disabled",false);
				}
			});
		}
};
$("#btnSearch").click(function(){
	jss.search(1);
});

</script>