package com.edaisong.core.common;

import java.lang.reflect.Field;
import java.util.List;

/**
 * html页面帮助类
 * 
 * @author CaoHeYang
 * @Date 20150730
 */
public class HtmlHelper {

	/**
	 * 
	 * @param name
	 * @param list
	 * @param textName
	 * @param valueName
	 * @return
	 */
	public static <T> String getSelect(String name, List<T> list,
			String textName, String valueName) {
		StringBuffer htmlStrBuffer = new StringBuffer();
		htmlStrBuffer.append(" <select class=\"form-control\" name=\""+name+"\">");
		try {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Field fieldText = list.get(i).getClass()
							.getDeclaredField(textName);    //获取name
					Field fieldText1 = list.get(i).getClass()
							.getDeclaredField(valueName);
					fieldText.setAccessible(true);
					fieldText1.setAccessible(true);
					htmlStrBuffer.append(" <option value=\""+fieldText1.get(list.get(i)).toString()+"\">"+fieldText.get(list.get(i)).toString()+"</option>");
				}
				return htmlStrBuffer.append("</select>").toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}
}
