package com.edaisong.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * html页面帮助类
 * 
 * @author zhaohl
 * @Date 20151119
 */
public class HtmlHelper {

	/**
	 * 
	 * @param selectName
	 *            下拉框名称
	 * @param list
	 *            下拉框数据集
	 * @param textName
	 *            文本对应的item的属性名称
	 * @param valueName
	 *            value对应的item的属性名称
	 * @param selectedValue
	 *            选中项的值
	 * @param defaultValue
	 *            下拉框第一项的value
	 * @param defaultText
	 *            下拉框第一项的text
	 * @param styleString
	 *            下拉框style
	 * @return
	 */
	public static <T> String getSelect(String selectName, List<T> list,
			String textName, String valueName, Object selectedValue,
			Object defaultValue, String defaultText, String styleString,String classString,Boolean auto) {
		StringBuffer htmlStrBuffer = new StringBuffer();
		String selected = "";
		String style = "";
		if (styleString != null&&!styleString.isEmpty()) {
			style = "style=\"" + styleString + "\"";
		}
		String strClass = "form-control m-b";
		if (classString != null&&!classString.isEmpty()) {
			strClass = classString;
		}
		String autooptString="";
		String divjsStringHead=" ";
		String divjsStringFoot=" ";
		if(auto){//zidong
			strClass+=" chosen-select";
			autooptString=" hassubinfo=\"true\" ";
		    divjsStringHead=" <div class=\"input-group\" style=\"width:100%\"> ";
		   // divjsStringFoot= "  </div> ";
		    divjsStringFoot= "  </div>  <script>"
		    		+"var configAutoCity = { '.chosen-select': {},'.chosen-select-deselect': { allow_single_deselect: true},'.chosen-select-no-single': {  disable_search_threshold: 10}, '.chosen-select-no-results': {no_results_text: '未找到结果!'},'.chosen-select-width': {width: \"50%\"}};for (var selector in configAutoCity) {$(selector).chosen(configAutoCity[selector]);}"
		    		+ "</script>";
		}
		htmlStrBuffer.append(" <select class=\""+strClass+"\" " + style + " id=\""
				+ selectName + "\" name=\"" + selectName + "\"> ");
		if (defaultValue != null) {
			if (selectedValue != null
					&& selectedValue.toString().equals(defaultValue.toString())) {
				selected = " selected=\"selected\" ";
			}
			htmlStrBuffer.append("<option " + selected + " value=\""
					+ defaultValue + "\">" + defaultText + "</option>");
			selected = "";
		}

		try {

			if (list != null && list.size() > 0) {
				List<Field> fields = getFinalFields(list.get(0).getClass(),textName, valueName);
				boolean isSimple=false;
				if (fields == null) {//此时表示List中的值是简单类型，则不用反射取值
					isSimple=true;
				}
				String NameText="";
				String ValueText="";
				for (int i = 0; i < list.size(); i++) {
					if (isSimple) {
						NameText=list.get(i).toString();
						ValueText=list.get(i).toString();
					}else {
						NameText=fields.get(0).get(list.get(i)).toString();
						ValueText=fields.get(1).get(list.get(i)).toString();
					}
					if (selectedValue != null
						&& selectedValue.toString().equals(ValueText)) {
						selected = " selected=\"selected\" ";
					}
					htmlStrBuffer.append(" <option "+autooptString + selected + " value=\""
										+ ValueText + "\">"+ NameText+ "</option>");
					selected = "";
				}
			}
			htmlStrBuffer.append("</select>");
			
			return divjsStringHead+ htmlStrBuffer.toString() +divjsStringFoot;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param type
	 * @param textName
	 * @param valueName
	 * @return
	 */
	private static List<Field> getFinalFields(Class type, String textName,String valueName) {
		if (textName==null||textName.isEmpty()||
			valueName==null||valueName.isEmpty()) {
			return null;
		}
		List<Field> resultList = new ArrayList<>();
		Field fieldText = null;
		Field fieldValue = null;
		// type.getDeclaredField("");//这种方式只能获取当前类的字段，不能访问基类中的属性
		// type.getField("");//这种方式只能获取类的所有public属性，包括当前类和基类
		try {
			try {
				fieldText = type.getDeclaredField(textName); // 获取name
			} catch (Exception e) {
				fieldText = type.getSuperclass().getDeclaredField(textName); // 获取name
			}
			try {
				fieldValue = type.getDeclaredField(valueName); // 获取valueName

			} catch (Exception e) {
				fieldValue = type.getSuperclass().getDeclaredField(valueName); // 获取valueName
			}
		} catch (Exception e) {
			return null;
		}
		fieldText.setAccessible(true);
		fieldValue.setAccessible(true);
		resultList.add(fieldText);
		resultList.add(fieldValue);
		return resultList;
	}

	/**
	 * 
	 * @param selectName
	 *            下拉框名称
	 * @param list
	 *            下拉框数据集
	 * @param textName
	 *            文本对应的item的属性名称
	 * @param valueName
	 *            value对应的item的属性名称
	 * @return
	 */
	public static <T> String getSelect(String selectName, List<T> list,
			String textName, String valueName) {
		return getSelect(selectName, list, textName, valueName, null, "-1",
				"全部", null,null,false);
	}

	/**
	 * 
	 * @param selectName
	 *            下拉框名称
	 * @param list
	 *            下拉框数据集
	 * @param textName
	 *            文本对应的item的属性名称
	 * @param valueName
	 *            value对应的item的属性名称
	 * @return
	 */
	public static <T> String getSelect(String selectName, List<T> list,
			String textName, String valueName, String styleString) {
		return getSelect(selectName, list, textName, valueName, null, "-1",
				"全部", styleString,null,false);
	}
	
	/**
	 * 
	 * @param selectName
	 * @param list
	 * @param textName
	 * @param valueName
	 * @param selectedValue
	 * @param defaultValue
	 * @param defaultText
	 * @param styleString
	 * @return
	 */
	public static <T> String getSelect(String selectName, List<T> list,
			String textName, String valueName, Object selectedValue,
			Object defaultValue, String defaultText, String styleString) {
		return getSelect(selectName, list, textName, valueName, selectedValue, defaultValue,
				defaultText, styleString,null,false);
	}
	/**
	 * 
	 * @param selectName
	 * @param list
	 * @param textName
	 * @param valueName
	 * @param selectedValue
	 * @param defaultValue
	 * @param defaultText
	 * @param styleString
	 * @return
	 */
	public static <T> String getSelect(String selectName, List<T> list,
			String textName, String valueName, Object selectedValue,
			Object defaultValue, String defaultText) {
		return getSelect(selectName, list, textName, valueName, selectedValue, defaultValue,
				defaultText, null,null,false);
	}
	/*******************************************************/
	/**
	 * 
	 * @param selectName
	 *            下拉框名称
	 * @param list
	 *            下拉框数据集
	 * @param textName
	 *            文本对应的item的属性名称
	 * @param valueName
	 *            value对应的item的属性名称
	 * @return
	 */
	public static <T> String getSelectAuto(String selectName, List<T> list,
			String textName, String valueName) {
		return getSelect(selectName, list, textName, valueName, null, "-1",
				"全部", null,null,true);
	}

	/**
	 * 
	 * @param selectName
	 *            下拉框名称
	 * @param list
	 *            下拉框数据集
	 * @param textName
	 *            文本对应的item的属性名称
	 * @param valueName
	 *            value对应的item的属性名称
	 * @return
	 */
	public static <T> String getSelectAuto(String selectName, List<T> list,
			String textName, String valueName, String styleString) {
		return getSelect(selectName, list, textName, valueName, null, "-1",
				"全部", styleString,null,true);
	}
	
	/**
	 * 
	 * @param selectName
	 * @param list
	 * @param textName
	 * @param valueName
	 * @param selectedValue
	 * @param defaultValue
	 * @param defaultText
	 * @param styleString
	 * @return
	 */
	public static <T> String getSelectAuto(String selectName, List<T> list,
			String textName, String valueName, Object selectedValue,
			Object defaultValue, String defaultText, String styleString) {
		return getSelect(selectName, list, textName, valueName, selectedValue, defaultValue,
				defaultText, styleString,null,true);
	}
	/**
	 * 
	 * @param selectName
	 * @param list
	 * @param textName
	 * @param valueName
	 * @param selectedValue
	 * @param defaultValue
	 * @param defaultText
	 * @param styleString
	 * @return
	 */
	public static <T> String getSelectAuto(String selectName, List<T> list,
			String textName, String valueName, Object selectedValue,
			Object defaultValue, String defaultText) {
		return getSelect(selectName, list, textName, valueName, selectedValue, defaultValue,
				defaultText, null,null,true);
	}

}
