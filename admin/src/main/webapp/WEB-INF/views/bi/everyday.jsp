<%@page import="com.edaisong.entity.Everyday"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script> 
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script> 
<script> 
<%
List<Everyday> list= (List<Everyday>) request.getAttribute("everyDayData");
StringBuilder time=new StringBuilder();
StringBuilder businessCount=new StringBuilder();
StringBuilder rzqsCount=new StringBuilder();
StringBuilder ddrzqsCount=new StringBuilder();
for(Everyday item:list)
{
	time.append("'").append(item.getInsertTime()).append("',");
	businessCount.append(item.getBusinessCount()).append(",");
	rzqsCount.append(item.getRzqsCount()).append(",");
	ddrzqsCount.append(item.getDdrzqsCount()).append(",");
}
%>
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'line',
             zoomType: 'x'
        },
        title: {
            text: '每日数据统计'
        },
        subtitle: {
            text: 'Source: edaisong.com'
        },
        xAxis: {
            categories: [<%=time.toString()%>]
        },
        yAxis: {
            title: {
                text: '时间(天)'
            }
        },
        tooltip: {
            enabled: false,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '商家数量',
            data: [<%=businessCount%>]
        }, {
            name: '认证骑士数量',
            data: [<%=rzqsCount%>]
        },{
            name: '待认证骑士数量',
            data: [<%=ddrzqsCount%>]
        }]
    });
});		

</script> 

<div id="container" style="min-width:700px;height:400px"></div>
</body>
