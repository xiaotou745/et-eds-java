<%@page import="com.edaisong.entity.domain.GroupOrderDaystatistics"%>
<%@page import="com.edaisong.entity.domain.GroupOrderstatistics"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
GroupOrderstatistics g=(GroupOrderstatistics)request.getAttribute("g");
GroupOrderstatistics g1=(GroupOrderstatistics)request.getAttribute("g1");
%>
<div class="menu">
	<ul>
		<li class="active" idx="0">
			<div class="num"><%=g.getCompliteCount() %></div>
			<div class="des">订单数量（单）</div>
		</li>
		<li idx="1">
			<div class="num"><%=g.getTotalSettleMoney() %></div>
			<div class="des">配送费支出（元）</div>
		</li>
		<li idx="2">
			<div class="num"><%=g.getTotalAmount() %></div>
			<div class="des">菜品金额（元）</div>
		</li>
		<li idx="3">
			<div class="num"><%=g.getCanelCount() %></div>
			<div class="des">拒单数量（单）</div>
		</li>
	</ul>
</div>
<div class="chart"></div>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery.autocomplete.js"></script>
<script>
<%
StringBuilder complite=new StringBuilder("[");
StringBuilder settleMoney=new StringBuilder("[");
StringBuilder monthDate=new StringBuilder("[");
StringBuilder amount=new StringBuilder("[");
StringBuilder canel=new StringBuilder("[");
for(int i=0;i< g.getDays().size();i++){
	complite.append(g.getDays().get(i).getOrderCount());
	settleMoney.append(g.getDays().get(i).getSettleMoney());
	monthDate.append("\""+g.getDays().get(i).getMonthDate()+"\"");
	amount.append(g.getDays().get(i).getAmount());
	if(i<g.getDays().size()-1){
		complite.append(",");
		settleMoney.append(",");
		monthDate.append(",");
		amount.append(",");
	}
}
for(int i=0;i< g1.getDays().size();i++){
	canel.append(g.getDays().get(i).getOrderCount());
	if(i<g.getDays().size()-1){
		canel.append(",");
	}
}
%>
        var statistics = [{
            subtitle: '2016.2.1-2006.2.29',
            name: '订单数量',
            data: <%=(complite+"]")%>
        }, {
            name: '配送费支出',
            subtitle: '2016.2.1-2006.2.29',
            data: <%=(settleMoney+"]")%>
        }, {
            name: '菜品金额',
            subtitle: '2016.2.1-2006.2.29',
            data: <%=(amount+"]")%>
        }, {
            name: '拒单数量',
            subtitle: '2016.2.1-2006.2.29',
            data:  <%=(canel+"]")%>
        }];
        
        $(function() {
        	$(".store_filter input").AutoComplete(
        			{
        				data : "",
        				ajaxDataType : "json",
        				width : 240,
        				listStyle : "custom",
        				matchHandler : function() {
        					return !0
        				},
        				afterSelectedHandler : function() {
        				},
        				createItemHandler : function(t, e) {
        					var i = $("<div " + (e.unlink ? 'class="disabled"' : "")
        							+ ">" + e.label + (e.unlink ? "[已解绑]" : "")
        							+ "</div>");
        					return i
        				}
        			});
        	var t = {
        		title : {
        			text : "今日订单统计"
        		},
        		exporting : {
        			enabled : !1
        		},
        		subtitle : {
        			text : ""
        		},
        		xAxis : {
        			categories :  <%=(monthDate+"]")%>
        		},
        		yAxis : {
        			title : {
        				text : "订单量"
        			},
        			plotLines : [ {
        				value : 0,
        				width : 1,
        				color : "#808080"
        			} ]
        		},
        		tooltip : {
        			valueSuffix : "单"
        		},
        		legend : {
        			layout : "vertical",
        			align : "right",
        			verticalAlign : "middle",
        			borderWidth : 0
        		},
        		series : [ statistics[0] ]
        	};
        	$(".menu li").on("click", function() {
        		var e = $(this);
        		if (!e.hasClass("active")) {
        			$(".menu .active").removeClass("active"), e.addClass("active");
        			var i = e.attr("idx") - 0;
        			$(".chart").highcharts($.extend({}, t, {
        				title : {
        					text : statistics[i].name
        				},
        				subtitle : {
        					text : statistics[i].subtitle
        				},
        				series : [ {
        					name : statistics[i].name,
        					data : statistics[i].data
        				} ],
        				yAxis : {
        					title : {
        						text : statistics[i].name
        					}
        				}
        			}))
        		}
        	}), $(".chart").highcharts($.extend({}, t, {
        		title : {
        			text : statistics[0].name
        		},
        		subtitle : {
        			text : statistics[0].subtitle
        		},
        		series : [ {
        			name : statistics[0].name,
        			data : statistics[0].data
        		} ],
        		yAxis : {
        			title : {
        				text : statistics[0].name
        			}
        		}
        	}))
        });
    </script>