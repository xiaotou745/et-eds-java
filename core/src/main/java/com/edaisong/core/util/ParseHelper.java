package com.edaisong.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseHelper {
	public static short ToShort(Object o, short defaultValue) {
		short result = defaultValue;
		try {
			result = Short.parseShort(o.toString());
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static short ToShort(Object o) {
		return ToShort(o, (short)0);
	}
	public static int ToInt(Object o, int defaultValue) {
		int result = defaultValue;
		try {
			result = Integer.parseInt(o.toString());
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static int ToInt(Object o) {
		return ToInt(o, 0);
	}

	/**
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static long ToLong(Object o, long defaultValue) {
		long result = defaultValue;
		try {
			result = Long.parseLong(o.toString());
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @param strFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date ToDate(String o, String strFormat) throws ParseException {
		if (strFormat == null || strFormat.isEmpty()) {
			strFormat = "yyyy-MM-dd hh:MM:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		Date defaultDate = sdf.parse(o);
		return defaultDate;
	}

	/**
	 * 
	 * @param o
	 * @param strFormat
	 * @return
	 */
	public static String ToDateString(Date o, String strFormat) {
		if (o==null) {
			return "";
		}
		if (strFormat == null || strFormat.isEmpty()) {
			strFormat = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat sdf = new SimpleDateFormat(strFormat);
		String defaultDate = "";
		defaultDate = sdf.format(o);
		return defaultDate;
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String ToDateString(Date o) {
		return ToDateString(o,null);
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String ShowString(Object o) {
		if (o==null) {
			return "";
		}else {
			return o.toString();
		}
	}
}
