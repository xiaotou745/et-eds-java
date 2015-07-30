<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         

<%
	String basePath = request.getContextPath();
    String  clienterid=	request.getAttribute("clienterid").toString();
%>

<div>
骑士Id
   <input id="txtClienterName" type="tel" name=<%=clienterid%> />
</div>



        
 <div id="content">
	
 </div>


	<script>		
	var jss={
			search:function(currentPage){	

                 var trueName = $("#txtClienterName").val();
                 var phoneNo = $("#txtClienterPhone").val();
                 var recommendPhone = $("#txtRecommonPhone").val(); 
                 var status=$("#superManStatus").val();           
                 var code=$("#businessCityId").val();       
                 var deliveryCompanyId=$("#deliveryCompanyId").val();   
                 if (code==null || code=="")
                	 {
                	  code=-1;             
                	 }
                 if (deliveryCompanyId==null || deliveryCompanyId=="")
            	 {
                	 deliveryCompanyId=-1;             
            	 }                
               
                 //参数不能为""值
				 var paramaters = { 
						 "CurrentPage":currentPage,
						 "trueName": trueName,
						 "phoneNo": phoneNo,
						 "recommendPhone": recommendPhone,
						 "status": status,
						 "code": code,
						 "deliveryCompanyId": deliveryCompanyId		
						 };        
			        var url = "<%=basePath%>/clienter/listdo";
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
	
	
	var adminjs = new adminglass(); //实例化后台类	
	 //骑士充值
    $("#btnRechargeCommit").on('click', function () {
        var clienterId = $("#clienterId").val(); //骑士id
        var clienterName = $("#clienterName").val(); //骑士电话
        var clienterRechargeAmount = $("#clienterRechargeAmount").val(); //骑士调整金额
        var rechargeLog = $("#rechargeLog").val(); //充值描述
        if (rechargeLog.trim().length == 0) {
            alert("请输入备注!");
            return;
        }

        var decimalFormat = /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
        if (!decimalFormat.test(clienterRechargeAmount)) {
            alert("请输入正确的金额！");
            return;
        }
    
        if (confirm("确定要为骑士：" + clienterName + "   调整：" + clienterRechargeAmount + "元？")) {
            var paramaters = {
                "ClienterId": clienterId, "RechargeAmount": clienterRechargeAmount, "Remark": rechargeLog
            };
            var url = "<%=basePath%>/clienter/modifymoney";
            $.ajax({
                type: 'POST',
                url: url,
                data: paramaters,
                success: function (result) {                  
                        window.location.href = "<%=basePath%>/clienter/list";                    
                }
            });
        }
    });
    //关闭弹层
    $('.J_closebox').click(function () {
        adminjs.closewinbox('.add-openbox');
        return false;
    });
	</script>		
	
