<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.admin.common.AuthCodeConst"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");
UserContext context=UserContext.getCurrentContext(request);
boolean canAdd=context.isHasAuthByCode(AuthCodeConst.Admin_BusinessSetpCharge_Add_Btn);
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="Title"  id="Title" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">添加日期:</label>
							<div class="col-sm-8">
							<div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="beginDate"  id="beginDate"/>
                                    </div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
							     <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input type="text" class="form-control" value="" name="endDate"  id="endDate"/>
                                 </div>
   							</div>
						</div>
					</div>
			
				</div>

			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
							style="margin-left: 3px;height:30px;">查询</button>
					   <button type="button" class="btn btn-w-m btn-primary" id="btnAdd"
							style="margin-left: 3px;height:30px;">添加配置</button>
					</div>
			</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>
<div class="modal inmodal fade" id="AddBox" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 550px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="boxtitle">添加配置</h4>
			</div>
			<div class="modal-body form-horizontal">
				<!-- 弹窗BODY  KAISHI -->
				 <div class="ibox-content">
                            <form class="form-horizontal">
                            	<div class="form-group" >
                                	<label class="col-lg-3 control-label ">名称</label>
									<div class="col-lg-6">
										<input maxlength="50" type="text" placeholder="" class="form-control brokerage" id="Addtitle">
										<input type="hidden" placeholder="" id="AddItemCount" value="0">
										<input type="hidden" placeholder="" id="modifyID" value="0">
                                    </div>
                                </div>
                                <div class="form-group" >
                                	<label class="col-lg-3 control-label ">阶梯计费规则</label>
									<div class="col-lg-6">
										<input type="text" placeholder="最大菜品金额" class="form-control brokerage" id="MaxLimitValue">
                                    </div>
                                    <label class="col-lg-0 control-label">元</label>
                                </div>
                                <div class="form-group" >
                                <label class="col-lg-3 control-label "></label>
									<div class="col-lg-6">
										<input type="text" placeholder="金额区间" class="form-control brokerage" id="setpcharge">
                                    </div>
                                    <label class="col-lg-0 control-label">元</label>
									<button class="btn btn-sm btn-white control-label total" type="button" id="shengcheng">生成</button>
                                </div>
                            </form>
                            
                            <table class="table">
	                            <thead>
	                            <tr>
	                                <th>菜品金额(元)</th>
	                                <th>配送费(元)</th>
	                            </tr>
	                            </thead>
	                            <tbody id="SetpBody">
	 
	                            </tbody>                       
	                        </table>
                        	 <div class="form-group" >
                                	<label class="col-lg-3 control-label ">描述</label>
									<div class="col-lg-6">
										<textarea maxlength="100" rows=6 cols=40 id="Remark"></textarea>
                                    </div>
                             </div>
                        </div>
				<!-- 弹窗BODY  END  -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" id="btnSave" class="btn btn-primary">保存</button>
				<button type="button" id="btnSaveEdit" class="btn btn-primary">修改保存</button>
			</div>
		</div>
	</div>
</div>

<script>
//页面初始化


$(function(){
	//日期控件初始化
	 $(' .input-group.date').datepicker({
         todayBtn: "linked",
         keyboardNavigation: false,
         forceParse: false,
         calendarWeeks: true,
         autoclose: true
     });
	//弹窗
	$('#btnAdd').click(function(){
		$('#btnSaveEdit').hide();
		$('#btnSave').show();
		$('#AddItemCount').val('0');
		$('#Addtitle').val('');
		$('#modifyID').val('0');
		$('#MaxLimitValue').val('');
		$('#setpcharge').val('');
		$('#Remark').val('');
		$('#SetpBody').html('');
		$('#boxtitle').html('添加配置')
		$('#AddBox').modal('show');
	});
	//点击生成
	$('#shengcheng').click(function(){
		CreateTr();
	});
	//保存
	$('#btnSave').click(function(){
		Save(1);
	});
	$('#btnSaveEdit').click(function(){
		editSave();
	});
	//查询
	$('#btnSearch').click(function(){
		jss.search(1);
	});
	jss.search(1);
});
	var jss = {
		search : function(currentPage) {
			var data={"currentPage":currentPage,
					"title":$('#Title').val(),
					"beginDate":$('#beginDate').val(),
					"endDate":$('#endDate').val()};
			$.post("<%=basePath%>/admintools/businesssetpchargedo",
					data,
					function(d) {
				$("#content").html(d);
			});
		}
	}
	//生成阶梯规则
	function CreateTr()
	{
		$('#AddItemCount').val(0);
		
		//判断
		var title =$.trim($('#Addtitle').val());
		if(title.length>50||title.length<2)
		{
			alert('计算规则名称2-50个字符!');
			return;
		}
		if (parseFloat($('#MaxLimitValue').val()) < 0.01
				|| isNaN(parseFloat($('#MaxLimitValue').val()))||
				parseInt($('#MaxLimitValue').val())>2147483648) {
			alert('菜品最大金额必须大于等于0.01元');
			$('#MaxLimitValue').val('');
			return;
		}
		if (parseFloat($('#setpcharge').val()) < 0.01
				|| isNaN(parseFloat($('#setpcharge').val()))||
				parseInt($('#setpcharge').val())>2147483648) {
			alert('金额区间必须大于等于0.01元');
			$('#setpcharge').val('');
			return;
		}
		var baseValue=0;//基数0
		var setpvale=parseFloat($('#setpcharge').val());//步长
		var maxlimit=parseFloat($('#MaxLimitValue').val());//上限
		var maxvalue=0;//最大值
		var htmStr='';
		for(var i=0;maxvalue<maxlimit;i++)
		{
			console.log(i);
			if(i<20)
			{
				var te=maxvalue;
				//步长+上次下限>最大上限,上限为最大上限,否则为步长+上次下限
				maxvalue=(setpvale+maxvalue)>maxlimit?maxlimit:(setpvale+maxvalue);
				htmStr+=getTRtemp(te,maxvalue,i,'');
				$('#AddItemCount').val(i+1);
			}
			else
				{
				break;
				}
			
		}
		if(htmStr!='')
		{
			$('#SetpBody').html(htmStr);
		}
	}
//获取TR标签	
function getTRtemp(minvalue,maxvalue,index,charge)
{
	var trtemp='<tr><td>'+minvalue+'＜X≤'+maxvalue+'</td><td>'
			+'<input style="width:120px" class="form-control brokerage" type="text" id="SetpValue'+index+'" value="'+charge+'"/>'
			+'<input  type="hidden" id="SetpValuemin'+index+'" value="'+minvalue+'"/><input  type="hidden" id="SetpValuemax'+index+'" value="'+maxvalue+'"/>'
			+'</td></tr>'
			+'</td></tr>';
	 return trtemp;
}
//保存检查
function SaveCheck()
{
	//判断
	
	var title =$.trim($('#Addtitle').val());
	if(title.length>50||title.length<2)
	{
		alert('计算规则名称2-50个字符!');
		return;
	}
	if (parseFloat($('#MaxLimitValue').val()) < 0.01
			|| isNaN(parseFloat($('#MaxLimitValue').val()))||
			parseInt($('#MaxLimitValue').val())>2147483648) {
		alert('菜品最大金额必须大于等于0.01元');
		$('#MaxLimitValue').val('');
		return false;
	}
	if (parseFloat($('#setpcharge').val()) < 0.01
			|| isNaN(parseFloat($('#setpcharge').val()))||
			parseInt($('#setpcharge').val())>2147483648) {
		alert('金额区间必须大于等于0.01元');
		$('#setpcharge').val('');
		return false;
	}
	var max=parseInt($('#AddItemCount').val());
	if(max==0)//没有生成
	{
		alert('请生成区间!');
		return false;
	}
	for(var i=0;i<max;i++)
	{
		var te=$('#SetpValue'+i).val();
		if (parseFloat(te) < 0.01
				|| isNaN(parseFloat(te))
				||parseInt(te)>2147483648) {
			alert('请正确填写各区间金额!');
			return false;
		}
	}
	return true;
}
//保存阶梯步骤
function Save(flag)
{
	//flag  1 添加 2 修改
	
	var tipstr='添加';
	
	if(flag==2)
	{
		tipstr='修改';
	}
	if(!SaveCheck())
	{
		return ;
	}
	//alert('验证通过');
	//构建策略对象
	var SetpCharge=new Object();
	SetpCharge.id=$('#modifyID').val();
	SetpCharge.title=$('#Addtitle').val();
	SetpCharge.remark=$('#Remark').val();
	SetpCharge.setpLength=parseFloat($('#setpcharge').val());
	SetpCharge.minLimit=0;
	SetpCharge.maxLimit=parseFloat($('#MaxLimitValue').val());
	var count=parseInt($('#AddItemCount').val());
	//构建子集合对象
	var ChildList=new Array(); 
	for(var i=0;i<count;i++)
	{
		var chid=new Object();
		chid.minValue=parseFloat($('#SetpValuemin'+i).val());
		chid.maxValue=parseFloat($('#SetpValuemax'+i).val());
		chid.chargeValue=parseFloat($('#SetpValue'+i).val());
		ChildList.push(chid);
	}
	var Jsdata=new Object();
	Jsdata.setp=SetpCharge;
	Jsdata.childs=ChildList;
	var JsonStr=JSON.stringify(Jsdata);
	var url='<%=basePath%>/admintools/businesssetpchargeadd';
	$.post(url,{"data":JsonStr},function(d){
		if(d==1|d=='1')
		{
			alert(tipstr+'成功!');
			window.location.href = "<%=basePath%>/admintools/businesssetpcharge";
		}
		else
		{
			alert(tipstr+'失败!');
		}
	});
}
//修改
function Edit(id)
{
	var url='<%=basePath%>/admintools/getsetpinfo';
	$.post(url,{"id":id},function(d){
		
		var obj=JSON.parse(d);
		createBox(obj);
	});
}
function createBox(obj)
{
	$('#btnSaveEdit').show();
	$('#btnSave').hide();
	$('#Addtitle').val(obj.setp.title);
	$('#modifyID').val(obj.setp.id);
	$('#MaxLimitValue').val(obj.setp.maxLimit);
	$('#setpcharge').val(obj.setp.setpLength);
	$('#Remark').val(obj.setp.remark);
	var list=obj.childs;
	$('#AddItemCount').val(list.length);
	$('#boxtitle').html('修改配置')
	var html='';
	for(var i=0;i<list.length;i++)
	{
		html+=getTRtemp(list[i].minValue,list[i].maxValue,i,list[i].chargeValue);
	}
	$('#SetpBody').html(html);
	$('#AddBox').modal('show');
}
//修改保存
function editSave()
{
	Save(2);
}
</script>