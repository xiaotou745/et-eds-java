
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%	
String basePath =PropertyUtils.getProperty("static.admin.url");
%>
<div class="top cb">
			<h3 class="cb">
				门店任务审核
				<p class="fr">
					<input type="text" class="fl" id="customerInfo" placeholder="骑士姓名，手机号">
					<input type="button" class="fl" value="搜索按钮" id="customerSearch">
				</p>
			</h3>
			

		<form method="POST" action="#" class="form-horizontal" id="searchForm">
			<input type="hidden" name="currentPage" id="_hiddenCurrentPage" value="1"/>
			<div class="function">
				<span class="fl">状态</span>
				<label class="fl">
					<input type="radio" name="workStatus" value="2" checked="checked" onchange="change(this.value)">
					全部
				</label>
				<label class="fl">
					<input type="radio" name="workStatus" value="0" onchange="change(this.value)">
					上班
				</label>
				<label class="fl">
					<input type="radio" name="workStatus" value="1" onchange="change(this.value)">
					下班
				</label>
			</div>
		</form>
		</div>
		<div class="bottom bottom2 bottom3" id="content">
		</div>
<script type="text/javascript">

</script>