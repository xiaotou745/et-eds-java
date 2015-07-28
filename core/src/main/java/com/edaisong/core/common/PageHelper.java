package com.edaisong.core.common;

public class PageHelper {

	// /获取分页数据
	public static String GetPage(int PageSize, int CurrentPage,
			int TotalRecord, int TotalPage) {
		if (TotalRecord <= 0)
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-sm-6\">");
		sb.append("<div class=\"dataTables_info\" id=\"DataTables_Table_0_info\" role=\"alert\"");
		sb.append("aria-live=\"polite\" aria-relevant=\"all\">共 " + PageSize
				+ "页，找到 " + TotalRecord + " 个</div>");
		sb.append("</div>");

		sb.append("<div class=\"col-sm-6\">");
		sb.append("<div class=\"dataTables_paginate paging_simple_numbers\" id=\"DataTables_Table_0_paginate\">");
		sb.append("<ul class=\"pagination\">");

		// 首页
		sb.append(GetFirstPage(CurrentPage, TotalPage));
		// 上一页
		sb.append("<li class=\"paginate_button previous disabled\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\" id=\"DataTables_Table_0_previous\">");
		sb.append("<a href=\"#\">上一页</a></li>");

		// 分页
		sb.append("	<li class=\"paginate_button active\"");
		sb.append(" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">1</a></li>");

		sb.append("<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">2</a></li>");
		sb.append("<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">3</a></li>");
		sb.append("<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">4</a></li>");
		// 下一页
		sb.append("<li class=\"paginate_button next\" aria-controls=\"DataTables_Table_0\"");
		sb.append(" tabindex=\"0\" id=\"DataTables_Table_0_next\"><a href=\"#\">下一页</a></li>");

		// 尾页

		sb.append("</ul></div></div></div>");

		return sb.toString();
	}

	private static String GetFirstPage(int CurrentPage, int TotalPage) {
		StringBuilder sb = new StringBuilder();
		String Disabled = "";
		if (CurrentPage <= 1 || TotalPage <= 1) {
			Disabled = " previous disabled";
		}
		sb.append("<li class=\"paginate_button "
				+ Disabled
				+ "\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\" id=\"DataTables_Table_0_previous\">");
		sb.append("<a href=\"javascript:jss.search(1)\">首页</a></li>");
		return sb.toString();
	}
}
