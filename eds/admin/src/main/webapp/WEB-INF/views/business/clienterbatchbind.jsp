<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.entity.domain.BusinessDetailModel"%>
<%@page import="com.edaisong.core.util.EnumHelper"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="com.edaisong.core.enums.BusinessBalanceRecordRecordType"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
	BusinessDetailModel detail = (BusinessDetailModel) request
			.getAttribute("detail");
%>

<script src="<%=basePath%>/js/ajaxfileupload.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="businessId" id="businessId"
					value="<%=detail.getId()%>" />
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户名称:</label> <label
								class="col-sm-4 control-label"> <%=ParseHelper.ShowString(detail.getName())%>
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">选择excel:</label>
							<div class="col-sm-8">
								<input id="file1" type="file" name="file1">

							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="importData()">导入</button>
						<button type="button" class="btn btn-primary btn-lg"
							onclick="save()">保存绑定</button>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<span style="color: red;">
							(导入必须为excel，字段：骑士姓名、手机号，每次最多导入50条数据)&nbsp;&nbsp; </span>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<div class="col-sm-8">
								<a onclick="window.location.href='<%=basePath%>/business/addclienterbindlist?businessId=<%=detail.getId()%>'">手动绑定</a>
								<a onclick="window.location.href='<%=basePath%>/business/clienterbatchbind?businessId=<%=detail.getId()%>'">批量绑定</a>
								<a href="<%=basePath%>/template/BatchImport.xls" target="_blank">下载模板</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content">
				<table
					class="table table-striped table-bordered table-hover dataTables-example">
					<thead>
						<tr>
							<th>行号</th>
							<th>骑士姓名</th>
							<th>骑士手机号</th>
							<th>是否绑定</th>
							<th>状态</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody id="bodyData">
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var OverallS;
//导入
function importData(){
    var businessId = $('#businessId').val();        
    var url = "<%=basePath%>/business/clienterimport?businessId=" + businessId;
    $.ajaxFileUpload({
        type: 'POST',
        secureuri: false, //一般设置为false
        fileElementId: 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
        url: url,
        dataType: 'text',   
        data: {"businessId":businessId}, //此参数非常严谨，写错一个引号都不行  
        success: function (data){  //服务器成功响应处理函数
        	if(data=="上传文件不能为空"){
        		alert(data);
        	}else{
        		json = eval("(" + data + ")");
	      		 OverallS = json;
	      		 $("#bodyData").html("");
	      		 for (var i = 0; i < json.length; i++) {
	           		var isBindText, isEnableText, clienterRemarksText;
	                   if (json[i].bind)
	                   		isBindText = " <input id='Checkbox1' name='"+i+"' type='checkbox' onchange='setCheck(this)'   checked='checked' />"
	                   else
	                       isBindText = "";
	                   if (json[i].enable)
	                       isEnableText = "成功"
	                   else
	                       isEnableText = "<font color='red'>失败</font>"
	                   clienterRemarksText = "<font color='red'>"+json[i].clienterRemarks+"</font>";
	                   $("#bodyData").append("<tr><td>" + json[i].rowCount
	                       + "</td><td>" + json[i].clienterName
	                       + "</td><td>" + json[i].clienterPhoneNo
	                       + "</td><td>" + isBindText
	                       + "</td><td>" + isEnableText
	                       + "</td><td>" + clienterRemarksText
	                       + "</td></tr>");             
	           	}
        	}
        }
    });
}

function setCheck(row){
    if (row.checked){        
        OverallS[row.name].IsBind = true;          
    }
    else{    
        OverallS[row.name].IsBind = false;           
    }        
}

//保存
function save(){
	if (OverallS.length < 1){ 
	alert("没有需要保存从数据");
		return;
	}
	for(var i=0;i<OverallS.length;i++){
	   if (!json[i].bind){
			alert("文件中的数据有误，请修改后重试");
			return;
	   }
	}
    var businessId = $('#businessId').val();
    var paramaters = {
        "businessId": businessId,
        "overallS": JSON.stringify(OverallS)
    };

    var url = "<%=basePath%>/business/clienterbatchsave";
    $.ajax({
        type: 'POST',
        url: url,
        data: paramaters,
        success: function (result) {
        	alert(result.message);
            if (result.responseCode > 0) {
                window.location.href = "<%=basePath%>/business/addclienterbindlist?businessId=" + businessId;
            }
        }
    });
}
</script>

