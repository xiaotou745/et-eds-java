<%@page import="com.edaisong.core.util.Config"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@page import="com.edaisong.entity.TaskDistributionConfig"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
List<TaskDistributionConfig> list =(List<TaskDistributionConfig>) request.getAttribute("listData");
TaskDistributionConfig entityMaster=(TaskDistributionConfig)list.get(0);
TaskDistributionConfig entityOne=(TaskDistributionConfig)list.get(1);
TaskDistributionConfig entityTwo=(TaskDistributionConfig)list.get(2);
int masterKM=entityMaster.getkM();
int masterKG=entityMaster.getkG();
double masterPirce=entityMaster.getDistributionPrice();


%>
<input type="hidden" value="<%=masterKM %>" id="hidmasterKM"/>
<input type="hidden" value="<%=masterKG %>" id="hidmasterKG"/>
<input type="hidden" value="<%=masterPirce %>" id="hidmasterPirce"/>
<input type="hidden" value="<%=entityOne.getkM() %>" id="hidoneKM"/>
<input type="hidden" value="<%=entityOne.getDistributionPrice() %>" id="hidoneDistributionPrice"/>
<input type="hidden" value="<%=entityTwo.getkG() %>" id="hidtwoKG"/>
<input type="hidden" value="<%=entityTwo.getDistributionPrice() %>" id="hidtwogetDistributionPrice"/>


<div
	class="wrapper wrapper-content animated fadeInRight  form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				<table
					class="table table-striped table-bordered table-hover dataTables-example">
					<tbody>
						<tr>
							<td><input type="text" value=<%=masterKM %> id="masterKM" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/> 
							公里内 <input type="text" value="<%=masterKG %>" id="masterKG" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/> 公斤以下 <input
								type="text" value="<%=masterPirce%>" id="masterPrice" onkeyup="clearNoNum(this)"/> 元配送费</td>
						</tr>
						<tr>
							<td>超过<%=masterKM %>公里，每增加 <input type="text" value="<%=entityOne.getkM()%>" id="oneKM" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/> 
							公里，增加 <input type="text" value="<%=entityOne.getDistributionPrice() %>" id="oneDistributionPrice" onkeyup="clearNoNum(this)"/> 元配送费
							</td>
						</tr>
						<tr>
							<td>超过<%=masterKM %>公斤，每增加 <input type="text" value="<%=entityTwo.getkG() %>" id="twoKG" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/> 
							公斤，增加 <input type="text" value="<%=entityTwo.getDistributionPrice() %>" id="twoDistributionPrice" onkeyup="clearNoNum(this)"/> 元配送费
							</td>
						</tr>
					</tbody>
				</table>
				<button type="button" class="btn btn-w-m btn-primary"
					id="btnupdate" style="margin-left: 3px;">保存修改</button>

			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$("#btnupdate").click(function(){
		
			var masterKM=parseInt($("#masterKM").val().trim());
			var masterKG=parseInt($("#masterKG").val().trim());
			var masterPrice=parseFloat($("#masterPrice").val().trim());
			var oneKM=parseInt($("#oneKM").val().trim());
			var oneDistributionPrice=parseFloat($("#oneDistributionPrice").val().trim());
			var twoKG=parseInt($("#twoKG").val().trim());
			var twoDistributionPrice=parseFloat($("#twoDistributionPrice").val().trim());
			
			var hidmasterKM=$("#hidmasterKM").val().trim();
			var hidmasterKG=$("#hidmasterKG").val().trim();
			var hidmasterPirce=$("#hidmasterPirce").val().trim();
			var hidoneKM=$("#hidoneKM").val().trim();
			var hidoneDistributionPrice=$("#hidoneDistributionPrice").val().trim();
			var hidtwoKG= $("#hidtwoKG").val().trim();
			var hidtwogetDistributionPrice= $("#hidtwogetDistributionPrice").val().trim();
			if(masterKM<0 || masterKG<0 || masterPrice<0 || oneKM<0 || oneDistributionPrice<0 || twoKG<0 || twoDistributionPrice<0) {
				alert("保存值不能为空不能小于零");
				return;
			}
			var isUpdate ="0";
			var logMsg="";
			if(hidmasterKM==masterKM && 
			   hidmasterKG==masterKG && 
			   hidmasterPirce==masterPrice) {
				isUpdate+="1";
				}
				else{
					logMsg="将原值公里 "+hidmasterKM+" 改为"+masterKM+" ,原值公斤 "+hidmasterKG+" 改为 "+masterKG+" ,原值金额 "+hidmasterPirce+" 改为 "+masterPrice;
				}
			   if(hidoneKM==oneKM && hidoneDistributionPrice==oneDistributionPrice){
				   isUpdate+="2";
				  
			   }else{
				   logMsg+="将原值每增加"+hidoneKM+" 公里改为"+oneKM+" ,原值增加 "+hidoneDistributionPrice+" 元配送费,改为 "+oneDistributionPrice;
				}
			   if(hidtwoKG==twoKG && hidtwogetDistributionPrice==twoDistributionPrice){
				   isUpdate+="3";
			   }
			   else{
				   logMsg+="将原值每增加"+hidtwoKG+" 公斤改为"+twoKG+" ,原值增加 "+hidtwogetDistributionPrice+" 元配送费,改为 "+twoDistributionPrice;
				}
			   if(isUpdate=="0123"){
				   alert("该操作没有任何值可更改的!");return;
				}
			$.post("<%=Config.adminUrl%>/admintools/updatetaskdistributionconfig",
				{masterKM:masterKM,masterKG:masterKG,masterPrice:masterPrice,
				oneKM:oneKM,oneDistributionPrice:oneDistributionPrice,
				twoKG:twoKG,twoDistributionPrice:twoDistributionPrice,
				isUpdate:isUpdate,logMsg:logMsg},
				function(d){
					if(d==1){alert("更新配置成功!");return;}
					alert("更新配置异常!");
				});
		});
	});
	function clearNoNum(obj){   obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	 obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
	 obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
	 obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
</script>