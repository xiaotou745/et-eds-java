
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.enums.TagType"%>
<%String basePath =PropertyUtils.getProperty("static.admin.url");%>

<div class="row" style="margin-top: 5px;">
	<div class="col-lg-3">
		<input type="button" value="添加标签" class="btn btn-w-m btn-primary" id="addMark"  onclick="addMark() "/>    
	</div>
</div> 
<div class="row"  style="margin-top: 15px;margin-bottom: 15px;margin-left: 5px;">
<table >
	 <tr>
	 	<td >
	      <span>标签名称: </span>
	      <input id="txtTagName" type="tel" name="txtTagName" />
	      <span class="">标签类型: </span>
	      <%=HtmlHelper.getSelect("tagtype", EnumHelper.GetEnumItems(TagType.class),"desc", "value", null, "-1", "全部", "", "")%> 
	      <span>创建时间: </span>
	        <input id="txtstartdate" type="text" name="startdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'txtenddate\')||\'2120-10-01\'}'})"/>
                       <span class="">到 </span>
            <input id="txtenddate" type="text" name="enddate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'txtstartdate\')}',maxDate:'2120-10-01'})"/>
	<input type="submit" value="查询" class="btn btn-w-m btn-primary" id="btnSearch" />      
	       </td>
	   </tr>
</table>  
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
</div>
<div tabindex="-1" class="modal inmodal" id="showEditTag" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog" style="width:361px">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header" style="width:360px;height:30px;margin-top:-25px;background:#F8F8FF;">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<div style="margin-top:-15px;"><h6 class="modal-title"  id="editTip">添加标签</h6></div> 
			</div>
			<small class="font-bold" >
				<div class="modal-body" >
					<fieldset>
			             <div class="control-group" style="width:260px;">
			                 <div style="font-size: 14px;float: left;margin-left: 5px;">标签名称: </div>
				      		 <div style="float:left;margin-left: 5px">
				      		 	<input id="editTagName" type="tel" name="editTagName" />
				      		 </div>
			                 <div style="font-size: 14px;float: left;margin-left: 5px;margin-top:10px">标签类型: </div>
				             <div style="float:left;margin-top:10px;margin-left: 5px">
				             	<%=HtmlHelper.getSelect("editTagType", EnumHelper.GetEnumItems(TagType.class),"desc", "value", null, null, null, "width:161px", "")%>
				             </div> 
			                 <div style="font-size: 14px;float: left;margin-left: 28px;clear:both;margin-top:10px"> 状 态:
				                 <input id="rIsEnableY" name="rIsEnable" type="radio" value="1" >启用
				                 <input id="rIsEnableN" name="rIsEnable" type="radio" value="0" style="margin-left: 30px" >禁止
                			 </div>
			                 <div style="font-size: 14px;float: left;margin-left: 28px;clear:both;margin-top:10px">备    注:</div>
				      		 <div style="float:left;margin-top:10px;margin-left: 5px">
				      		 	<textarea name="editRemark" id="editRemark" style="width:165px;height:60px;max-width:165px;max-height:60px;"> </textarea>
				      		 </div>
			                 <input type="hidden" id="hdTagId" value="0"/>
			                 <input type="hidden" id="hdOperateType" value="0"/>
			                 <input type="hidden" id="oldTagName" />
			                 <input type="hidden" id="oldTagType" />
			                 <input type="hidden" id="oldIsEnable" />
			                 <input type="hidden" id="oldRemark" />
			            </div>  
			        </fieldset>
				</div>
				<div class="modal-footer">
				    <button class="btn btn-primary" type="button" id="btnEditTag" onclick="confirmEidt()">保存</button>
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
				</div>
			</small>
		</div> 
	</div>
</div>
<script>
	var jss={
			search:function(currentPage){	
                 var tagName = $("#txtTagName").val();
                 var tagType = $("#tagtype").val();          
                 var startdate=$("#txtstartdate").val();       
                 var enddate=$("#txtenddate").val();   
				 var paramaters = { 
						 "currentPage":currentPage,
						 "tagName": tagName,
						 "tagType": tagType,
						 "startDate": startdate,
						 "endDate": enddate,	
						 };        
			        var url = "<%=basePath%>/mark/listdo";
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
	//提交
   function confirmEidt(){
	   var id=$("#hdTagId").val();
	   var operateType=$("#hdOperateType").val();
	   var tagName = $("#editTagName").val();
	   var tagType = $("#editTagType").val();
	   var isEnable = $('input[name="rIsEnable"]:checked').val();
	   var remark = $("#editRemark").val();
	   var oldTagName = $("#oldTagName").val();
	   var oldTagType = $("#oldTagType").val();
	   var oldIsEnable = $("#oldIsEnable").val();
	   var oldRemark = $("#oldRemark").val();
	   if(tagName.trim().length == 0){
		   alert("请输入标签名称!");
		   return; 
	   }
	   if(tagName.trim().length <2 || tagName.trim().length>20){
			alert("标签名称必须输入2-20个字符!");
			return;
		}
	   if(remark.trim().length>50){
			alert("备注不能超过50个字符!");
			return;
		}
	   if(operateType==1)
	   {
		 if(tagName.trim()==oldTagName&&tagType==oldTagType&&isEnable==oldIsEnable&&remark.trim()==oldRemark)
			 {
			 	alert("无修改内容!");
				return;
			 }
		 if(tagName.trim()==oldTagName)
			 {
			    operateType=2;
			 }
	   }
	   var paramaters = {
	    	   "id":id,
               "tagName":tagName.trim(),
               "tagType":tagType,
	    	   "isenable":isEnable,
               "remark":remark.trim(),
               "operateType":operateType
           };
      var url = "<%=basePath%>/mark/editmark";
	   $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (result) {   			            
        	   alert(result.message);
               if (result.responseCode > 0) {
                   window.location.href = "<%=basePath%>/mark/list";
               }               
           }
       });
   }
   function addMark(){
	    $("#hdTagId").val(0);
		$("#editTagName").val(''); 
		$("#editTagType").val(0);
		$("#editRemark").val('');
        $("#rIsEnableY").attr("checked", "checked");
		$("#hdOperateType").val(0);
		$("#editTip").html("添加标签");
		$('#showEditTag').modal('show');
	}
</script>