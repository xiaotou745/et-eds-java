
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.TaskDistribution"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>

<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
TaskDistribution detail = (TaskDistribution) request
		.getAttribute("detail");
%>

<style type="text/css">
#map_contain {
    height: 90%;
    max-width: none;
}
label {
    max-width: none;
}

#control {
width: 100%;
}
</style>

<div class="SearchMd">
<form method="POST" action="#" class="form-horizontal" id="searchForm">
<table class="tbstyle222" border="0"
	style="font-size: 14px; font-weight: bold; line-height: 300%; width: 900px">
	<tr class="trclass">
		<td>规则名称:<%=ParseHelper.ShowString(detail.getName())%></td>		
	</tr>	
</table>	
	<input type="hidden" name="taskDistributionId" id="taskDistributionId" value="<%=detail.getId()%>"/>
</form>
</div>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row" >
		<div class="col-lg-12" style="display:none">			
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">距离:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" value="" name="txtKM" id="txtKM" />
							</div>
						</div>
					</div>					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">重量:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" value="" name="txtKG" id="txtKG" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
								
							</div>
						</div>
					</div>
				</div>				
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">							
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
							  
							</div>
						</div>
					</div>
				</div>

			    <div class="row">
						<div class="col-lg-3">
<!-- 						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch -->
<!-- 							style="margin-left: 3px;height:30px;">查询</button> -->
								<button type="button" class="btn btn-w-m btn-primary" id="add"  onclick="showAdd()"
					style="margin-left: 3px;height:30px;">新增		</button>	
					<button type="button" class="btn btn-w-m btn-primary" id="btnCalculator"  onclick="showCalculator()"
					style="margin-left: 3px;height:30px;">计算器
					</button>
					</div>
	

		</div>
	</div>	
</div>
<div id="content"></div>

<div tabindex="-1" class="modal inmodal" id="addConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加规则</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>距离(千米)：</label><input  name="txtUKM" id="txtUKM" type="text" >
            </div>
            <div class="control-group">  
                <label>重量(公斤)：</label><input  name="txtUKG" id="txtUKG" type="text" >
            </div>
            <div class="control-group">  
                <label>阶梯增量(千米/公斤)：</label><input  name="txtUSteps" id="txtUSteps" type="text" >
            </div>            
              <div class="control-group">  
                <label>配送费(元)：</label><input  name="txtUDistributionPrice" id="txtUDistributionPrice" type="text" >
            </div>
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAdd" onclick="saveAdd()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
   
 <div tabindex="-1" class="modal inmodal" id="modifyConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">修改规则</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>距离(千米)：</label><input  name="txtEKM" id="txtEKM" type="text" >
                <input  name="txtEId" id="txtEId" type="hidden">
                <input  name="txtEIsMaster" id="txtEIsMaster" type="hidden">
            </div>
            <div class="control-group">  
                <label>重量(公斤)：</label><input  name="txtEKG" id="txtEKG" type="text" >
            </div>
                    <div class="control-group">  
                <label>阶梯增量(千米/公斤)：</label><input  name="txtESteps" id="txtESteps" type="text" >
            </div>     
              <div class="control-group">  
                <label>配送费(元)：</label><input  name="txtEDistributionPrice" id="txtEDistributionPrice" type="text" >
            </div>
            
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAdd" onclick="saveModify()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>

<div tabindex="-1" class="modal inmodal" id="CalculatorConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">配送费计算器</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>距离：</label><input  name="txtCKM" id="txtCKM" type="text" >
                <input type="hidden" name="jstaskDistributionId" id="jstaskDistributionId" value="<%=detail.getId()%>"/>
            </div>
            <div class="control-group">  
                <label>重量：</label><input  name="txtCKG" id="txtCKG" type="text" >
            </div>      
              <div class="control-group">  
                <label>配送费：</label><input  name="txtPSF" id="txtPSF" type="text"  disabled="disabled">
            </div>       
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnCCalculator" onclick="CalculatorFun()">计算配送费</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
	<script>		
	var jss={
			search:function(currentPage){	
			   var KM = $("#txtKM").val();
				   var KG=$("#txtKG").val();
				 var taskDistributionId=$("#taskDistributionId").val();	 	
				 var paramaters = { 
						 "currentPage":currentPage,						
						 "KM": KM,
						 "KG": KG,		
						 "taskDistributionId":taskDistributionId,
						 };        
			        var url = "<%=basePath%>/taskdistribution/taskdistributionbindlistdo";
			        $.ajax({
			            type: 'POST',
			            url: url,
			            data: paramaters,
			            success: function (result) {   			            
			            	$("#content").html(result);               
			            }
			        });
			}
		}	
		
	jss.search(1);
	$("#btnSearch").click(function(){
		jss.search(1);
	});		

	function showAdd(){ 
		//判断当前是否超过10条
		var len=$('#content').find('tbody').find('tr').length;
		if(len>10)
		{
			alert('新增规则不能超过10条!');
			return;
		}
        $('#txtUKM').val('0');
        $('#txtUKG').val('0');
        $('#txtUSteps').val('1');
        $('#txtUDistributionPrice').val('0');
        $('#addConfig').modal('show');
}
	//新增 
	function saveAdd(){
		
		var txtKM= $('#txtUKM').val().trim();
		var txtKG= $('#txtUKG').val().trim();	
		var txtSteps= $('#txtUSteps').val().trim();
		var txtDistributionPrice= $('#txtUDistributionPrice').val().trim();
		var taskDistributionId= $('#taskDistributionId').val().trim();

		 if(txtKM == "")
		 {
			 alert("距离不能为空");
		    return;
		 }
		 if(txtKG == "")
		 {
			 alert("重量不能为空");
		    return;
		 }
		 if(!isInt(txtKM))
		    {
		    	 alert("距离只能填整数");
				    return;
		    }
		 if(!isInt(txtKG))
		 {
		    	 alert("重量只能填整数");
				    return;
		 }
		 if(txtKM>0 && txtKG>0)
    	 {
			 alert("距离， 重量只能配置1个值");
			return;		 
		 }
		 if(txtKM<=0 && txtKG<=0)
    	 {
			 alert("距离， 重量必须有1个值不能为0");
			return;		 
		 }
		 if(txtUSteps == "")
		 {
			 alert("阶梯增量不能为空");
		    return;
		 }
		 if(txtUSteps == "0")
		 {
			 alert("阶梯增量不能为0");
		    return;
		 }
		 
		 if(txtDistributionPrice == "")
		 {
			 alert("配送费");
		    return;
		 }
		if(txtDistributionPrice<=0){
		    	alert("配送费必须大于零");
		    	return;
		   }		    
	    //配置必须大于基础配置 //公里
	    if(parseInt(txtKM)!=0&&parseInt(txtKG)==0)
	    {
	    	var flag=true;
	    	var basekm=parseInt($('.xiaokm1').html());
	    	if(parseInt(txtKM)<basekm)
	    	{
	    		alert('新建规则公里必须大于基础配置公里');
	    		return;
	    	}
	    	$('.xiaokm0').each(function(e){
	    		var ha=parseInt($(this).html());
	    		if(ha==parseInt(txtKM))
	    		{
	    			alert('不能添加相同的配置!');
	    			flag=false;
	    			return false;
	    		}
	    	});
	    	if(!flag)
	    	{
	    		return;
	    	}
	    }  //配置必须大于基础配置 //公里
	    if(parseInt(txtKM)==0&&parseInt(txtKG)!=0)
	    {
	    	var flag=true;
	    	var basekm=parseInt($('.xiaokg1').html());
	    	if(parseInt(txtKG)<basekm)
	    	{
	    		alert('新建规则重量必须大于基础配置重量');
	    		return;
	    	}
	    	$('.xiaokg0').each(function(e){
	    		var ha=parseInt($(this).html());
	    		if(ha==parseInt(txtKG))
	    		{
	    			alert('不能添加相同的配置!');
	    			flag=false;
	    			return false;
	    		}
	    	});
	    	if(!flag)
	    	{
	    		return;
	    	}
	    }
	    var paramaters = {
                "KM": txtKM.trim(),
                "KG": txtKG.trim(),
                "Steps": txtSteps.trim(),
                "DistributionPrice": txtDistributionPrice.trim(),
                "taskDistributionId":taskDistributionId,
            };
	    
       var url = "<%=basePath%>/taskdistribution/addconfig";
       var la= layer.confirm('是否确认创建规则？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.message);		        	
		               if (result.status == 1) {
		            	   window.location.href = "<%=basePath%>/taskdistribution/taskdistributionbindlist?taskDistributionId="+taskDistributionId;		
		               }
		                              
		        	  
		           }
		       });
		});  
	}
	
	function saveModify()
	{
		var txtId= $('#txtEId').val().trim();
		var txtKM= $('#txtEKM').val().trim();
		var txtKG= $('#txtEKG').val().trim();
		var txtESteps= $('#txtESteps').val().trim();
		var txtERemark= '';	
		var txtDistributionPrice= $('#txtEDistributionPrice').val().trim();		
		var txtEIsMaster= $('#txtEIsMaster').val().trim();		
		var taskDistributionId= $('#taskDistributionId').val().trim();

		 if(txtKM == "")
		 {
			 alert("距离不能为空");
		    return;
		 }
		 if(txtKG == "")
		 {
			 alert("重量不能为空");
		    return;
		 }
		 if(!isInt(txtKM))
		    {
		    	 alert("距离只能填整数");
				    return;
		    }
		    if(!isInt(txtKG))
		    {
		    	 alert("重量只能填整数");
				    return;
		    }
		 if (txtEIsMaster==0)//非基础
		 {
				 if(txtKM>0 && txtKG>0)
		    	 {
					 alert("距离， 重量只能配置1个值");
					return;		 
				 }
				 if(txtKM<=0 && txtKG<=0)
		    	 {
					 alert("距离， 重量必须有1个值不能为0");
					return;		 
				 }
				 if(txtDistributionPrice == "")
				 {
					 alert("配送费");
				    return;
				 }
				if(txtDistributionPrice<=0){
				    	alert("配送费必须大于零");
				    	return;
				   }	
				 if(txtESteps == "")
				 {
					 alert("阶梯增量不能为空");
				    return;
				 }
				 if(txtESteps == "0")
				 {
					 alert("阶梯增量不能为0");
				    return;
				 }
				//配置必须大于基础配置 //公里
				    if(parseInt(txtKM)!=0&&parseInt(txtKG)==0)
				    {
				    	var flag=true;
				    	var basekm=parseInt($('.xiaokm1').html());
				    	if(parseInt(txtKM)<basekm)
				    	{
				    		alert('修改规则公里必须大于基础配置公里')
				    	}
				    	$('.xiaokm0').each(function(e){
				    		var ha=parseInt($(this).html());
				    		if(ha==parseInt(txtKM))
				    		{
				    			alert('不能添加相同的配置!');
				    			flag=false;
				    			return false;
				    		}
				    	});
				    	if(!flag)
				    	{
				    		return;
				    	}
				    }  //配置必须大于基础配置 //公里
				    if(parseInt(txtKM)==0&&parseInt(txtKG)!=0)
				    {
				    	var flag=true;
				    	var basekm=parseInt($('.xiaokg1').html());
				    	if(parseInt(txtKG)<basekm)
				    	{
				    		alert('修改规则重量必须大于基础配置重量')
				    	}
				    	$('.xiaokg0').each(function(e){
				    		var ha=parseInt($(this).html());
				    		if(ha==parseInt(txtKG))
				    		{
				    			alert('不能添加相同的配置!');
				    			flag=false;
				    			return false;
				    		}
				    	});
				    	if(!flag)
				    	{
				    		return;
				    	}
				    }
		 }
		 else//基础
		 {
			 var basekm=parseInt(txtKM);
			 var basekg=parseInt(txtKG);
			 var kmarr=new Array();
			 $('.xiaokm0').each(function(index){
				 var temp=$(this).html();
				 if(temp!='--')
				 {
					 kmarr.push(parseInt(temp));
				 }
			 });
			 //排序
			 kmarr.sort(compare);
//			 kmarr.reverse();
			 console.log(kmarr);
			 if(basekm>=kmarr[0])
			 {
				alert('基础配置公里必须小于现有所有配置!');
				return false;
			 }
			 var kgarr=new Array();
			 $('.xiaokg0').each(function(index){
				 var temp=$(this).html();
				 if(temp!='--')
				 {
					 kgarr.push(parseInt(temp));
				 }
			 });
			 //排序
			 kgarr.sort(compare);
			 //kgarr.reverse();
			 console.log(kgarr);
			 if(basekg>=kgarr[0])
			 {
				alert('基础配置重量必须小于现有所有重量!');
				return false;
			 }
		 }
	    var paramaters = {	    		
	    		"Id": txtId.trim(),
                "KM": txtKM.trim(),
                "KG": txtKG.trim(),
                "Steps": txtESteps.trim(),                
                "DistributionPrice": txtDistributionPrice.trim(),
                "IsMaster": txtEIsMaster.trim(),
                "Remark": '',
            };
       var url = "<%=basePath%>/taskdistribution/modifyconfig";
	   var la= layer.confirm('是否确认修改规则？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.message);		        	
		               if (result.status == 1) {
		            	   window.location.href = "<%=basePath%>/taskdistribution/taskdistributionbindlist?taskDistributionId="+taskDistributionId;		
		               }
		                              
		        	  
		           }
		       });
		});       	    
	}
	function showCalculator(){ 	
		 $('#txtCKM').val('0');
	     $('#txtCKG').val('0');
	     $('#txtPSF').val('0');
        $('#CalculatorConfig').modal('show');
}
	
	function CalculatorFun(){	        
	        
		var txtKM= $('#txtCKM').val().trim();
		var txtKG= $('#txtCKG').val().trim();		
		

		 if(txtKM == "")
		 {
			 alert("距离不能为空");
		    return;
		 }
		 if(txtKG == "")
		 {
			 alert("重量不能为空");
		    return;
		 }	
	    if(!isInt(txtKM))
	    {
	    	 alert("距离只能填整数");
			    return;
	    }
	    if(!isInt(txtKG))
	    {
	    	 alert("重量只能填整数");
			    return;
	    }
	    var paramaters = {
                "KM": txtKM.trim(),
                "KG": txtKG.trim(),
                "taskDistributionId": <%=detail.getId()%>,
            };
        var url = "<%=basePath%>/taskdistribution/calculatorconfig"; 
	
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		   
		        	   
		        	   $('#txtPSF').val(result);

		           }
		       });	       	    
	}
	
	function isInt(n)
	{
		if(n.indexOf('.')>=0)
		{
			return false;
		}
	     return n == Math.abs( parseInt( n ) );
	}
	
	 function compare(value1, value2) {

		   if (value1 < value2) {

		       return -1;

		   } else if (value1 > value2) {

		       return 1;

		   } else {

		       return 0;

		   }

		} 
	</script>		
	
