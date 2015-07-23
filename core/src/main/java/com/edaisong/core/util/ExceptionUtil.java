package com.edaisong.core.util;

import java.io.ByteArrayOutputStream;

public class ExceptionUtil {
	public static String getStackTrace(Throwable ex) {
		ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
		try {
			ex.printStackTrace(new java.io.PrintWriter(buf, true));
			return buf.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				buf.close();
			} catch (Exception a) {
			}
		}
	}
}
