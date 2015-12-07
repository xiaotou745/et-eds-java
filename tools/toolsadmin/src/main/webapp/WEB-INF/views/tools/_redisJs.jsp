<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.toolscore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.toolsadmin.url");
%>
<script type="text/javascript">
	$(function(){
		$(document).delegate(".J_Remove","click",function(){
			var key = $(this).parents("tr").find("td:first a").text();
			var $this = $(this);
			if(confirm("确定要删除此缓存吗?")){
				$.ajax({
					url:"<%=basePath%>/tools/redis/remove",
					method:"post",
					data:{key:key},
					dataType:"json",
					success:function(resp){
						if(!resp.iserror){
							$this.parents("tr").remove();
						}
					}
				})
			}
		})
		$("#btnSearch").bind("click",function(){
			var redisKey = $("#txtQuery").val();
			
			$.get("<%=basePath%>/tools/redis/query",{key:redisKey},function(resp){
				console.log(resp);
					$("#contents").html(resp);
			})
		})
		$("#btnRefresh").bind("click",function(){
			location.reload(true);
		})
		$('#modalRedisValue').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); // Button that triggered the modal
			var modal = $(this);
			var key = button.parents("tr").find("td:first a").text();
			console.log(key);
			$.ajax({
				url:"<%=basePath%>/tools/redis/get",
				data:{key:key},
				type:"get",
				dataType:"json",
				success:function(resp){
					console.log(resp);
					if(!resp.iserror){
						$("#lblValue").text(resp.message);
					}
				}
			})
		});
	})
</script>