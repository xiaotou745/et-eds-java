package com.edaisong.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.edaisong.core.security.DES;

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
		return ToShort(o, (short) 0);
	}
	public static String ToString(Object o,String defaultValue) {
		if (o==null) {
		   return defaultValue;	
		}
		return o.toString();
	}
	public static int ToInt(Object o, int defaultValue) {
		int result = defaultValue;
		if (o==null) {
			return result;
		}
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
	 * @param defaultValue
	 * @return
	 */
	public static double ToDouble(Object o, double defaultValue) {
		double result = defaultValue;
		try {
			result = Double.parseDouble(o.toString());
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
	public static Date ToDate(String o) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		Date defaultDate = null;
		try {
			defaultDate = sdf.parse(o);
		} catch (ParseException e) {
		}
		return defaultDate;
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
		if (o == null) {
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
		return ToDateString(o, null);
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String ShowString(Object o) {
		if (o == null) {
			return "";
		} else {
			return o.toString();
		}
	}

	/**
	 * 日期加减帮助方法
	 * 
	 * @author 赵海龙
	 * @Date 20150817
	 * @param dt
	 *            要操作的日期对象
	 * @param type
	 *            0是年，1是月，2是日
	 * @param dif
	 *            需要加减的值，负数时，表示减
	 * @return
	 */
	public static Date plusDate(Date dt, int type, int dif) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		switch (type) {
		case 0:
			rightNow.add(Calendar.YEAR, dif);// 日期加n年
			break;
		case 1:
			rightNow.add(Calendar.MONTH, dif);// 日期加n月
			break;
		case 2:
			rightNow.add(Calendar.DAY_OF_YEAR, dif);// 日期加n天
			break;
		default:
			break;
		}

		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * DES解密
	 * @author pengyi
	 * @date 20150828
	 * @param text
	 * @return
	 */
	public static String toDecrypt(String text) {
		try {
			if (StringUtils.isEmpty(text))
				return "";
			return DES.decrypt(text);
		} catch (Exception ex) {
			return text;
		}
	}
	
	/**
	 * 判断当月有几天
	 * @author 茹化肖
	 * @date 20150828
	 * @param text
	 * @return
	 */
	public static Integer GetMixDay(String year,String month) {
		try {
			Calendar cal = Calendar.getInstance(); 
			cal.set(Calendar.YEAR,Integer.parseInt(year)); 
			cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);//Java月份从0开始算 
			int dateOfMonth = cal.getActualMaximum(Calendar.DATE); 
			return dateOfMonth;
			
		} catch (Exception ex) {
			return 0;
		}
		
	}
}
