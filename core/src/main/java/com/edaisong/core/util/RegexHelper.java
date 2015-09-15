package com.edaisong.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern; 
/**
 * 正则相关的工具类
 * @author 茹化肖
 *
 */
public class RegexHelper {
	/**
	 * @param regStr正则表达式字符串
	 * @param str 需要匹配的字符串
	 * @return 录入字符串中有符合表达式的返回true 否则返回false
	 */
	public static Boolean regexBool(String regStr,String str)
	{
		  	Pattern p = Pattern.compile(regStr);
	        Matcher m = p.matcher(str);
	        boolean flg = m.matches();
	        return flg; 
	}
	/**
	 * 验证字符串格式是否类似 于 2010-09
	 * 范围1900-2100
	 * @param str 需要匹配的字符串
	 * @return 录入字符串中有符合表达式的返回true 否则返回false
	 */
	public static Boolean yearMotnReg(String str)
	{
			String regStr="^(19\\d{2}|2(0\\d{2}|100))-(0[1-9]|1[0-2])$";
		  	return regexBool(regStr, str);
	}
	
	/**
	 * 验证字符串格式是否类似 于 2010-09-01
	 * 范围1900-01-01到 2100-12-31
	 * @param str 需要匹配的字符串
	 * @return 录入字符串中有符合表达式的返回true 否则返回false
	 */
	public static Boolean yearMotnDayReg(String str)
	{
			String regStr="^(19\\d{2}|2(0\\d{2}|100))-(0[1-9]|1[0-2])-([012][1-9]|3[01])$";
		  	return regexBool(regStr, str);
	}
}
