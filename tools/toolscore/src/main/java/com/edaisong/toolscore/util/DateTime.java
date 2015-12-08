package com.edaisong.toolscore.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.spi.CalendarDataProvider;

import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.lang.time.DateUtils;

public class DateTime {
	private Date dateTime;

	private final static String[] parseSeprators = new String[] { "-", "/", " ", ":" };
	private final static String[] formatPatterns = new String[] { "yyyyMMddHHmmss", "yyyyMMdd", "HHmmss" };

	public static Date Now = new Date();

	public DateTime() {
		this.dateTime = new Date();
	}
	
	public static DateTime parse(Date date){
		if(date == null){
			return getInstance();
		}
		DateTime result = new DateTime();
		result.dateTime = date;
		return result;
	}

	public static DateTime parse(String date) {
		for (String seprator : parseSeprators) {
			date = date.replace(seprator, "");
		}
		DateTime result = new DateTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		Date parseDate = null;
		for (String pattern : formatPatterns) {
			try {
				dateFormat.applyPattern(pattern);
				parseDate = dateFormat.parse(date);
				result.dateTime = parseDate;
				break;
			} catch (ParseException e) {
			}
		}
		if (parseDate == null) {
			return getInstance();
		}

		return result;
	}

	public static DateTime parse(String str, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			return parse(str);
		}
		DateTime result = new DateTime();
		SimpleDateFormat parser = new SimpleDateFormat(pattern);
		try {
			Date parseDate = parser.parse(str);
			result.dateTime = parseDate;
		} catch (ParseException e) {
			return getInstance();
		}
		return result;
	}

	public static DateTime getInstance() {
		return new DateTime();
	}

	public DateTime addYears(int amount) {
		return add(Calendar.YEAR, amount);
	}

	public DateTime addMonths(int amount) {
		return add(Calendar.DAY_OF_MONTH, amount);
	}

	public DateTime addDays(int amount) {
		return add(Calendar.DATE, amount);
	}

	public DateTime addHours(int amount) {
		return add(Calendar.HOUR_OF_DAY, amount);
	}

	public DateTime addMinutes(int amount) {
		return add(Calendar.MINUTE, amount);
	}

	public DateTime addSeconds(int amount) {
		return add(Calendar.SECOND, amount);
	}

	private DateTime add(int field, int amount) {
		if (this.dateTime == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(this.dateTime);
		c.add(field, amount);
		this.dateTime = c.getTime();
		return this;
	}

	public Date getTime() {
		return this.dateTime;
	}

	public DateTime getDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(this.dateTime);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		this.dateTime = c.getTime();
		return this;
	}

	public String toString() {
		return toString(null);
	}

	public String toString(String format) {
		if (this.dateTime == null) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat sdf = new SimpleDateFormat(format);
		String defaultDate = "";
		defaultDate = sdf.format(this.dateTime);
		return defaultDate;
	}
}
