package com.edaisong.core.util;

public class PageHelper {

	// /获取分页数据
	public static String GetPage(int PageSize, int CurrentPage,
			int TotalRecord, int TotalPage) {
		if (TotalPage <= 0)
			return "";
		// int pageCount = TotalRecord % PageSize != 0 ? TotalRecord / PageSize
		// + 1 : TotalRecord / PageSize;
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-sm-6\">");
		sb.append("<div class=\"dataTables_info\" id=\"DataTables_Table_0_info\" role=\"alert\"");
		sb.append("aria-live=\"polite\" aria-relevant=\"all\">共 " + TotalPage
				+ "页，找到 " + TotalRecord + " 个</div>");
		sb.append("</div>");

		sb.append("<div class=\"col-sm-6\">");
		sb.append("<div class=\"dataTables_paginate paging_simple_numbers\" id=\"DataTables_Table_0_paginate\">");
		sb.append("<ul class=\"pagination\">");

		// 首页
		sb.append(GetFirstPage(CurrentPage, TotalPage));
		// 上一页
		// sb.append("<li class=\"paginate_button previous disabled\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\" id=\"DataTables_Table_0_previous\">");
		// sb.append("<a href=\"#\">上一页</a></li>");
		sb.append(GetBackPage(CurrentPage, TotalPage));

		// 分页
		// sb.append("	<li class=\"paginate_button active\"");
		// sb.append(" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">1</a></li>");
		//
		// sb.append("<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">2</a></li>");
		// sb.append("<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">3</a></li>");
		// sb.append("<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"#\">4</a></li>");
		sb.append(GetPageInfo(CurrentPage, TotalPage));
		// 下一页
		// sb.append("<li class=\"paginate_button next\" aria-controls=\"DataTables_Table_0\"");
		// sb.append(" tabindex=\"0\" id=\"DataTables_Table_0_next\"><a href=\"#\">下一页</a></li>");
		sb.append(GetNextPage(CurrentPage, TotalPage));

		// 尾页
		sb.append(GetEndPage(CurrentPage, TotalPage));

		sb.append("</ul></div></div></div>");

		return sb.toString();
	}

	/*
	 * 首页信息 窦海超 2015年7月28日 12:58:48
	 */
	private static String GetFirstPage(int CurrentPage, int TotalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(1)";
		if (CurrentPage <= 1 || TotalPage == 1) {
			Disabled = " previous disabled";
			Href = "#";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<li class=\"paginate_button "
				+ Disabled
				+ "\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\" id=\"DataTables_Table_0_previous\">");
		sb.append("<a href=\"" + Href + "\">首页</a></li>");
		return sb.toString();
	}

	/*
	 * 上一页 窦海超 2015年7月28日 12:59:03
	 */
	private static String GetBackPage(int CurrentPage, int TotalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(" + (CurrentPage - 1) + ")";
		if (CurrentPage <= 1 || TotalPage <= 1) {
			Disabled = " previous disabled";
			Href = "#";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<li class=\"paginate_button "
				+ Disabled
				+ "\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\" id=\"DataTables_Table_0_previous\">");
		sb.append("<a href=\"" + Href + "\">上一页</a></li>");
		return sb.toString();
	}

	/*
	 * 分页信息 窦海超 2015年7月28日 12:59:15
	 */
	private static String GetPageInfo(int CurrentPage, int TotalPage) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < TotalPage; i++) {
			int myPage = i + 1;
			String Href = "javascript:jss.search(" + (myPage) + ")";
			String activeStr = myPage == CurrentPage ? " active" : "";
			sb.append("<li class=\"paginate_button"
					+ activeStr
					+ "\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\""
					+ Href + "\">" + myPage + "</a></li>");
		}
		return sb.toString();
	}

	/*
	 * 下一页 窦海超 2015年7月28日 13:03:43
	 */
	private static String GetNextPage(int CurrentPage, int TotalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(" + (CurrentPage + 1) + ")";
		if (CurrentPage >= TotalPage) {
			Disabled = " previous disabled";
			Href = "#";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<li class=\"paginate_button "
				+ Disabled
				+ "\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\" id=\"DataTables_Table_0_previous\">");
		sb.append("<a href=\"" + Href + "\">下一页</a></li>");
		return sb.toString();
	}

	/*
	 * 尾页信息 窦海超 2015年7月28日 12:58:48
	 */
	private static String GetEndPage(int CurrentPage, int TotalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(" + TotalPage + ")";
		if (CurrentPage == TotalPage) {
			Disabled = " previous disabled";
			Href = "#";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<li class=\"paginate_button "
				+ Disabled
				+ "\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\" id=\"DataTables_Table_0_previous\">");
		sb.append("<a href=\"" + Href + "\">尾页</a></li>");
		return sb.toString();
	}
}