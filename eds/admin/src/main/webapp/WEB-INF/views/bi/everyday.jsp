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
StringBuilder orderPrice=new StringBuilder();
StringBuilder misstionCount=new StringBuilder();
StringBuilder orderCount=new StringBuilder();
StringBuilder kdj=new StringBuilder();
StringBuilder rwdj=new StringBuilder();
StringBuilder businessavgMission=new StringBuilder();
StringBuilder businessPubAvgMissionCount=new StringBuilder();
StringBuilder clienterAvgOrderCommission=new StringBuilder();
StringBuilder clienterAvgFinishOrderCount=new StringBuilder();
StringBuilder orderAvgCommission=new StringBuilder();

StringBuilder businessAverageOrderCount=new StringBuilder();
StringBuilder missionAverageOrderCount=new StringBuilder();
StringBuilder clienterAverageOrderCount=new StringBuilder();
StringBuilder ysPrice=new StringBuilder();
StringBuilder yfPrice=new StringBuilder();
StringBuilder ykPrice=new StringBuilder();
StringBuilder activeBusiness=new StringBuilder();
StringBuilder activeClienter=new StringBuilder();
StringBuilder rechargeTotal=new StringBuilder();
StringBuilder systemRecharge=new StringBuilder();
StringBuilder systemPresented=new StringBuilder();
StringBuilder clientRecharge=new StringBuilder();
StringBuilder incomeTotal=new StringBuilder();
StringBuilder zhsr=new StringBuilder();

for(Everyday item:list)
{
	time.append("'").append(item.getInsertTime()).append("',");
	businessCount.append(item.getBusinessCount()).append(",");//商家数量
	rzqsCount.append(item.getRzqsCount()).append(",");//认证骑士数量
	activeBusiness.append(item.getActiveBusiness()).append(",");//活跃商家
	activeClienter.append(item.getActiveClienter()).append(",");//活跃骑士
	ddrzqsCount.append(item.getDdrzqsCount()).append(",");//等待认证骑士数量
	orderPrice.append(item.getOrderPrice()).append(",");//订单金额
	misstionCount.append(item.getMisstionCount()).append(",");//任务量
	orderCount.append(item.getOrderCount()).append(",");//订单量
	kdj.append(item.getOrderPrice()/item.getOrderCount()).append(",");//客单价
	rwdj.append(item.getOrderPrice()/item.getMisstionCount()).append(",");//任务单价
	businessAverageOrderCount.append(item.getBusinessAverageOrderCount()).append(",");//商户平均发布订单
	businessPubAvgMissionCount.append(item.getMisstionCount()/item.getActiveBusiness()).append(",");//商户平均发布任务
	missionAverageOrderCount.append(item.getMissionAverageOrderCount()).append(",");//任务平均订单
	//clienterAvgFinishOrderCount.append(item.getOrderCount()/item.getActiveClienter()).append(","); //骑士平均完成订单量
	clienterAverageOrderCount.append(item.getClienterAverageOrderCount()).append(",");//骑士平均完成订单量
	clienterAvgOrderCommission.append( item.getYfPrice()/item.getActiveClienter()).append(","); //任务平均佣金
	orderAvgCommission.append(item.getYfPrice()/item.getOrderCount()).append(","); //订单平均佣金	
	incomeTotal.append(item.getIncomeTotal()).append(","); //在线支付(扫码/代付)总计
	rechargeTotal.append(item.getRechargeTotal()).append(","); //商户充值总计
	clientRecharge.append(item.getClientRecharge()).append(",");//客户端充值金额
	systemRecharge.append(item.getSystemRecharge()).append(",");//系统充值金额
	systemPresented.append(item.getSystemPresented()).append(",");//系统赠送金额
	zhsr.append(item.getIncomeTotal()+item.getRechargeTotal()).append(","); //账户收入总计
	ysPrice.append(item.getYsPrice()).append(",");//商户结算金额（应收）
	yfPrice.append(item.getYfPrice()).append(",");//骑士佣金总计（应付）
	ykPrice.append(item.getYkPrice()).append(",");//盈亏总计
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
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y;
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
        series: [
        {
            name: '商家数量',
            visible:false,
            data: [<%=businessCount%>]
        }, {
            name: '认证骑士数量',
            visible:false,
            data: [<%=rzqsCount%>]
        }, {
            name: '活跃商家数',
            visible:false,
            data: [<%=activeBusiness%>]
        },{
            name: '活跃骑士数',
            visible:false,
            data: [<%=activeClienter%>]
        },{
            name: '待认证骑士数量',
            visible:false,
            data: [<%=ddrzqsCount%>]
        },{
            name: '订单金额',
            visible:false,
            data: [<%=orderPrice%>]
        },{
            name: '任务量',
            visible:false,
            data: [<%=misstionCount%>]
        },{
            name: '订单量',
            visible:false,
            data: [<%=orderCount%>]
        },{
            name: '客单价',
            visible:false,
            data: [<%=kdj%>]
        },{
            name: '任务单价',
            visible:false,
            data: [<%=rwdj%>]
        },{
            name: '商户平均发布订单',
            visible:false,
            data: [<%=businessAverageOrderCount%>]
        },{
            name: '商户平均发布任务',
            visible:false,
            data: [<%=businessPubAvgMissionCount%>]
        },{
            name: '任务平均订单量',
            visible:false,
            data: [<%=missionAverageOrderCount%>]
        },{
            name: '骑士平均完成订单量',
            visible:false,
            data: [<%=clienterAverageOrderCount%>]
        },{
            name: '任务平均佣金',
            visible:false,
            data: [<%=clienterAvgOrderCommission%>]
        }, {
            name: '订单平均佣金',
            visible:false,
            data: [<%=orderAvgCommission%>]
        },{
            name: '在线支付(扫码/代付)总计',
            visible:false,
            data: [<%=incomeTotal%>]
        },{
            name: '商户充值总计',
            visible:false,
            data: [<%=rechargeTotal%>]
        },{
            name: '客户端充值金额',
            visible:false,
            data: [<%=clientRecharge%>]
        },{
            name: '系统充值金额',
            visible:false,
            data: [<%=systemRecharge%>]
        },{
            name: '系统赠送金额',
            visible:false,
            data: [<%=systemPresented%>]
        },{
            name: '账户收入总计',
            visible:false,
            data: [<%=zhsr%>]
        },{
            name: '商户结算金额（应收）',
            visible:false,
            data: [<%=ysPrice%>]
        },{
            name: '骑士佣金总计（应付）',
            visible:false,
            data: [<%=yfPrice%>]
        },{
            name: '盈亏总计',
            visible:false,
            data: [<%=ykPrice%>]
        }
        ]
    });
});		

</script>
<div class="wrapper wrapper-content animated fadeInRight">
<div class="row">
<div class="col-lg-12">
<div class="inquire">
   <div class="period">
       <!--表单查询-->
        <div class="item_row">
            <span class="dtext">查询周期</span>
            <div class="dbox">
                <span class="intime">
                    <input type="text" name="d" class="dinput" id="time1" style="width: 80px" value="2015-08-18"><s onclick="WdatePicker({el:'time1',dateFmt:'yyyy-MM-dd'});"></s></span>
                <span class="inblock">至</span>
                <span class="intime">
                    <input type="text" name="d2" class="dinput" id="time2" style="width: 80px" value="2015-09-17"><s onclick="WdatePicker({el:'time2',dateFmt:'yyyy-MM-dd'});"></s></span>
                <div class="dbox"><a href="javascript:;" class="sbutton" id="findDelivery">查询</a></div>
            </div>
        </div>
        <div class="refresh">
            <dl>
                <dt>数据更新时间</dt>
                <dd id="updateDate">2015/9/18 0:00:00</dd>
            </dl>
        </div>
    </div>
    
    <div class="array" id="summaryData"><dl class="red"><dt>54,098</dt><dd>餐厅数量（个）</dd> </dl><dl class="green"><dt>1,184,132</dt><dd>注册会员（人）</dd> </dl> <dl class="blue_dd"><dt>1,137,396</dt><dd>外卖订单（单）</dd></dl><dl class="blue_je"><dt>7,406.36万</dt><dd>外卖金额（元）</dd></dl><dl class="orange_dd"><dt>581</dt><dd>订台订单（个）</dd></dl><dl class="orange_je"><dt>88,997.55</dt><dd>订台金额（元）</dd></dl></div>
</div>
</div>
</div>
</div>
<div id="container" style="min-width:700px;height:400px"></div>
</body>
