<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  CACACACA 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						»ù±¾ <small>·ÖÀà£¬²éÕÒ</small>
					</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown"
							href="table_data_tables.html#"
							tppabs="http://www.zi-han.net/theme/hplus/table_data_tables.html#">
							<i class="fa fa-wrench"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="table_data_tables.html#"
								tppabs="http://www.zi-han.net/theme/hplus/table_data_tables.html#">Ñ¡Ïî1</a>
							</li>
							<li><a href="table_data_tables.html#"
								tppabs="http://www.zi-han.net/theme/hplus/table_data_tables.html#">Ñ¡Ïî2</a>
							</li>
						</ul>
						<a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">

					<div id="DataTables_Table_0_wrapper"
						class="dataTables_wrapper form-inline" role="grid">
						<div class="row">
							<div class="col-sm-6">
								<div class="dataTables_length" id="DataTables_Table_0_length">
									<label>Ã¿Ò³ <select name="DataTables_Table_0_length"
										aria-controls="DataTables_Table_0"
										class="form-control input-sm">
											<option value="10">10</option>
											<option value="25">25</option>
											<option value="50">50</option>
											<option value="100">100</option>
									</select> Ìõ¼ÇÂ¼
									</label>
								</div>
							</div>
							<div class="col-sm-6">
								<div id="DataTables_Table_0_filter" class="dataTables_filter">
									<label>²éÕÒ£º<input type="search"
										class="form-control input-sm"
										aria-controls="DataTables_Table_0"></label>
								</div>
							</div>
						</div>
						<table
							class="table table-striped table-bordered table-hover dataTables-example dataTable"
							id="DataTables_Table_0"
							aria-describedby="DataTables_Table_0_info">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										aria-sort="ascending" aria-label="äÖÈ¾ÒýÇæ£º¼¤»îÅÅÐòÁÐÉýÐò"
										style="width: 214px;">äÖÈ¾ÒýÇæ</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										aria-label="ä¯ÀÀÆ÷£º¼¤»îÅÅÐòÁÐÉýÐò" style="width: 382px;">ä¯ÀÀÆ÷</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										aria-label="Æ½Ì¨£º¼¤»îÅÅÐòÁÐÉýÐò" style="width: 353px;">Æ½Ì¨</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										aria-label="ÒýÇæ°æ±¾£º¼¤»îÅÅÐòÁÐÉýÐò" style="width: 156px;">ÒýÇæ°æ±¾</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										aria-label="CSSµÈ¼¶£º¼¤»îÅÅÐòÁÐÉýÐò" style="width: 157px;">CSSµÈ¼¶</th>
								</tr>
							</thead>
							<tbody>
								<tr class="gradeA odd">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Firefox 1.0</td>
									<td class=" ">Win 98+ / OSX.2+</td>
									<td class="center ">1.7</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA even">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Firefox 1.5</td>
									<td class=" ">Win 98+ / OSX.2+</td>
									<td class="center ">1.8</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA odd">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Firefox 2.0</td>
									<td class=" ">Win 98+ / OSX.2+</td>
									<td class="center ">1.8</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA even">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Firefox 3.0</td>
									<td class=" ">Win 2k+ / OSX.3+</td>
									<td class="center ">1.9</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA odd">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Camino 1.0</td>
									<td class=" ">OSX.2+</td>
									<td class="center ">1.8</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA even">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Camino 1.5</td>
									<td class=" ">OSX.3+</td>
									<td class="center ">1.8</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA odd">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Netscape 7.2</td>
									<td class=" ">Win 95+ / Mac OS 8.6-9.2</td>
									<td class="center ">1.7</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA even">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Netscape Browser 8</td>
									<td class=" ">Win 98SE+</td>
									<td class="center ">1.7</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA odd">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Netscape Navigator 9</td>
									<td class=" ">Win 98+ / OSX.2+</td>
									<td class="center ">1.8</td>
									<td class="center ">A</td>
								</tr>
								<tr class="gradeA even">
									<td class="sorting_1">Gecko</td>
									<td class=" ">Mozilla 1.0</td>
									<td class=" ">Win 95+ / OSX.1+</td>
									<td class="center ">1</td>
									<td class="center ">A</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<th rowspan="1" colspan="1">äÖÈ¾ÒýÇæ</th>
									<th rowspan="1" colspan="1">ä¯ÀÀÆ÷</th>
									<th rowspan="1" colspan="1">Æ½Ì¨</th>
									<th rowspan="1" colspan="1">ÒýÇæ°æ±¾</th>
									<th rowspan="1" colspan="1">CSSµÈ¼¶</th>
								</tr>
							</tfoot>
						</table>
						<div class="row">
							<div class="col-sm-6">
								<div class="dataTables_info" id="DataTables_Table_0_info"
									role="alert" aria-live="polite" aria-relevant="all">ÏÔÊ¾ 1
									µ½ 10 Ïî£¬¹² 57 Ïî</div>
							</div>
							<div class="col-sm-6">
								<div class="dataTables_paginate paging_simple_numbers"
									id="DataTables_Table_0_paginate">
									<ul class="pagination">
										<li class="paginate_button previous disabled"
											aria-controls="DataTables_Table_0" tabindex="0"
											id="DataTables_Table_0_previous"><a href="#">ÉÏÒ»Ò³</a></li>
										<li class="paginate_button active"
											aria-controls="DataTables_Table_0" tabindex="0"><a
											href="#">1</a></li>
										<li class="paginate_button "
											aria-controls="DataTables_Table_0" tabindex="0"><a
											href="#">2</a></li>
										<li class="paginate_button "
											aria-controls="DataTables_Table_0" tabindex="0"><a
											href="#">3</a></li>
										<li class="paginate_button "
											aria-controls="DataTables_Table_0" tabindex="0"><a
											href="#">4</a></li>
										<li class="paginate_button "
											aria-controls="DataTables_Table_0" tabindex="0"><a
											href="#">5</a></li>
										<li class="paginate_button "
											aria-controls="DataTables_Table_0" tabindex="0"><a
											href="#">6</a></li>
										<li class="paginate_button next"
											aria-controls="DataTables_Table_0" tabindex="0"
											id="DataTables_Table_0_next"><a href="#">ÏÂÒ»Ò³</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<script src="<%=basePath%>/js/plugins/jeditable/jquery.jeditable.js"></script>
<!-- Data Tables -->
<script src="<%=basePath%>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=basePath%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=basePath%>/js/plugins/pace/pace.min.js"></script>
<!-- Page-Level Scripts -->
<script>
	$(document).ready(function() {
		$('.dataTables-example').dataTable();
	});
	function fnClickAddRow() {
		$('#editable').dataTable().fnAddData(
				[ "Custom row", "New row", "New row", "New row", "New row" ]);
	}
</script>
