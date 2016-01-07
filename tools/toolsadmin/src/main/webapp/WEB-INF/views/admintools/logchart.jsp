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

List<String> appNameList = (List<String>)request.getAttribute("appNameList");
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
							<label class="col-sm-4 control-label">步长(分钟):</label>
							<div class="col-sm-8">
								<input type="text" placeholder="步长" style="width:100px;"
					class="form-control" id="minStep" name="minStep"  value="5" />
							</div>
						</div>
					</div>
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
							<label class="col-sm-4 control-label">分析指标:</label>
							<div class="col-sm-8">
								<input type="radio" value="requestnum" name="showType" checked="checked"/>请求次数
								<input type="radio" value="averagetime" name="showType"/>平均耗时
								<input type="radio" value="errorrate" name="showType"/>异常率
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
					<div class="col-lg-3">
						<div class="form-group">
						</div>
					</div>
					
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

function showChart(points){    
	var title="";
	var showType=$('input:radio[name="showType"]:checked').val();
	switch(showType){
	case "requestnum":
		title="请求次数";
		break;
	case "averagetime":
		title="平均耗时(ms)";
		break;
	case "errorrate":
		title="异常率(%)";
		break;
	}
      var resultPoints = [];
      var start=points[0].key;
      var step=parseInt($("#minStep").val())*60*1000;
      for (var i = 0; i < points.length; i++) {   
    	  resultPoints.push(points[i].value);
      }                                                               
	  Highcharts.setOptions({                                                     
          global: {                                                               
              useUTC: false                                                       
          }                                                                       
      });   
	$('#content').html("");    
	$('#content').highcharts({
        chart: {
            zoomType: 'x',
            spacingRight: 20
        },
        title: {
            text: '系统'+title+'分析'
        },
        subtitle: {
            text: '拖动鼠标放大'
        },
        xAxis: {
            type: 'datetime',
        labels: {  
            formatter: function() {  
            	return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.value);
            	}  
        } 
        },
        yAxis: {
            title: {
                text: title
            }
        },
        tooltip: {
        	formatter: function() {                                             
                return '<b>'+ this.series.name +'</b><br/>'+                
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                Highcharts.numberFormat(this.y, 2);                         
        }     
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                lineWidth: 1,
                marker: {
                    enabled: false
                },
                shadow: false,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },

        series: [{
            type: 'area',
            name: title,
            pointInterval: step,
            pointStart: start,
            data: resultPoints
        }]
    });
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
			if($("#minStep").val()==""){
				alert("步长不能为空");
				return;
			}
			var t=parseInt($("#minStep").val());
			if(isNaN(t)||t<=0){
				alert("步长必须大于0");
				return;
			}
			$("#beginHidden").val($("#begin").val()+" 00:00:00");
			$("#endHidden").val($("#end").val()+" 23:59:59");
			//sourceSys下拉框改变时，会自动触发查询，此时appversion中的值还没有发生变更，因此需要特殊处理
			if($("#sourceSys").val()!="apihttp"&&$("#sourceSys").val()!="renrenapihttp"){
			    $("#appversion").val("");
			}else if($("#appversion").val()!=""){
				if($("#"+$("#sourceSys").val()+"version").val().indexOf($("#appversion").val())==-1){
					$("#appversion").val("");
				}
			}
			var paramaters=$("#searchForm").serialize();
			var showType=$('input:radio[name="showType"]:checked').val();
			var url = "<%=basePath%>/admintools/";
			url=url+showType
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