
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%	
String basePath =PropertyUtils.getProperty("static.admin.url");
List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
%>
   <table border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td>               
				<label >发单日期:</label>
<input type="text" value="" name="startDate" id="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
<span>到</span>
<input type="text" value="" name="endDate" id="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
<select id=selecttype>
<option value=1>门店名称</option>
<option value=2>注册电话</option>
</select>
<input type="text" name="selectvalue" id="selectvalue">
 <span class="">筛选城市: </span>
               		  <%=HtmlHelper.getSelect("businessCityId", areaListData, "name", "name") %>
               		   <input type="submit" value="查询" class="searchBtn" id="btnSearch" />
                </td>
            </tr>

              
        </table>  
        
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>   
<script type="text/javascript">
var jss={
		search:function(currentPage){
			 var data={
					 	currentPage:currentPage,
					 	startDate:$('#startDate').val(),
					 	endDate:$('#endDate').val(),
					 	selectType:$('#selecttype').val(),
					 	selectValue:$('#selectvalue').val(),
					 	cityName:$('#businessCityId').val()
					 };
			 console.log(data);
			$.post("<%=basePath%>/finance/bustasklistdo",
					data,
					function(d){
				$("#content").html(d);
			});
		}
	}
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>