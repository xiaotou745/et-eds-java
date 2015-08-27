package com.edaisong.core.util;

public class PageHelper {

	/**
	 * 生成分页相关的html
	 * @author hailongzhao
	 * @date 20150827
	 * @param pageSize
	 * @param currentPage
	 * @param totalRecord
	 * @param totalPage
	 * @return
	 */
	public static String getPage(int pageSize, int currentPage,
			int totalRecord, int totalPage) {
		if (totalPage <= 0)
			return "";
		//共 39 页 572 条记录，当前为第 1 页,本页 15 条
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-sm-6\">");
		sb.append("<div class=\"dataTables_info\" id=\"DataTables_Table_0_info\" role=\"alert\"");
		sb.append("aria-live=\"polite\" aria-relevant=\"all\">共 " + totalPage
				+ "页," + totalRecord + "条记录,当前为第"+currentPage+"页,每页"+pageSize+"条</div>");
		sb.append("</div>");

		sb.append("<div class=\"col-sm-6\">");
		sb.append("<div class=\"dataTables_paginate paging_simple_numbers\" id=\"DataTables_Table_0_paginate\">");
		sb.append("<ul class=\"pagination\">");

		// 首页
		sb.append(getFirstPage(currentPage, totalPage));
		sb.append(getBackPage(currentPage, totalPage));
		sb.append(getPageInfo(currentPage, totalPage));
		sb.append(getNextPage(currentPage, totalPage));

		// 尾页
		sb.append(getEndPage(currentPage, totalPage));

		//跳转到指定页
		sb.append("<input type=\"hidden\" id=\"pagesearchmax\" value=\""+totalPage+"\">");
		sb.append("<input type=\"text\" id=\"pagesearchvalue\"  value=\"1\" style=\"width:30px;height:28px;\">");
		sb.append("<input type=\"button\" id=\"pagesearch\" value=\"跳转\" data-submitbutton=\"true\">");
		sb.append("</ul></div></div></div>");

		return sb.toString();
	}

/**
 * 生成首页的html
 * @author hailongzhao
 * @date 20050827
 * @param currentPage
 * @param totalPage
 * @return
 */
	private static String getFirstPage(int currentPage, int totalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(1)";
		if (currentPage <= 1 || totalPage == 1) {
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

/**
 * 生成上一页的html
 * @author hailongzhao
 * @date 20150827
 * @param currentPage
 * @param totalPage
 * @return
 */
	private static String getBackPage(int currentPage, int totalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(" + (currentPage - 1) + ")";
		if (currentPage <= 1 || totalPage <= 1) {
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

	/**
	 * 生成页码的html
	 * @author hailongzhao
	 * @date 20150827
	 * @param currentPage
	 * @param totalPage
	 * @return
	 */
	private static String getPageInfo(int currentPage, int totalPage) {
		StringBuilder sb = new StringBuilder();
		String headMoreString="";
		String endMoreString="";
		int showPage=5;//一次显示10个分页的按钮
		int headPage=1;
		int endPage=totalPage;
		if (totalPage>showPage) {
			if (currentPage>showPage) {
				headPage=currentPage-showPage/2;
				if (currentPage==totalPage) {
					headPage=totalPage-showPage+1;
				}
				endPage=headPage+showPage-1;
				if (endPage>totalPage) {
					endPage=totalPage;
				}
				headMoreString="<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"javascript:jss.search("
						+ (headPage-1) + ")\">...</a></li>";

				if (endPage<totalPage) {
					endMoreString="<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"javascript:jss.search("
							+ (endPage+1) + ")\">...</a></li>";
				}
			}else {
				endPage=showPage;
				endMoreString="<li class=\"paginate_button\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\"javascript:jss.search("
						+ (endPage+1) + ")\">...</a></li>";
			}
		}

		for (int i = headPage; i <= endPage; i++) {
			int myPage = i;
			String Href = "javascript:jss.search(" + (myPage) + ")";
			String activeStr = myPage == currentPage ? " active" : "";
			sb.append("<li class=\"paginate_button"
					+ activeStr
					+ "\" aria-controls=\"DataTables_Table_0\" tabindex=\"0\"><a href=\""
					+ Href + "\">" + myPage + "</a></li>");
		}
		return headMoreString+sb.toString()+endMoreString;
	}

/**
 * 生成下一页的html
 * @author hailongzhao
 * @date 20150827
 * @param currentPage
 * @param totalPage
 * @return
 */
	private static String getNextPage(int currentPage, int totalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(" + (currentPage + 1) + ")";
		if (currentPage >= totalPage) {
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

/**
 * 生成尾页的html
 * @author hailongzhao
 * @date 20150827
 * @param currentPage
 * @param totalPage
 * @return
 */
	private static String getEndPage(int currentPage, int totalPage) {
		String Disabled = "";
		String Href = "javascript:jss.search(" + totalPage + ")";
		if (currentPage == totalPage) {
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
