<%@page import="com.edaisong.entity.Everyday"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script> 
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script> 
<%
List<Everyday> list= (List<Everyday>) request.getAttribute("everyDayData");
StringBuilder time=new StringBuilder();
for(Everyday item:list)
{
	time.append(item.getInsertTime().replace(" '00:00:00.000',", ""));
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
            name: 'Tokyo',
            data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
        }, {
            name: 'London',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }]
    });
});		

</script> 
<title>每日数据统计</title>
</head>
<body>
<div id="container" style="min-width:700px;height:400px"></div>
</body>
</html>