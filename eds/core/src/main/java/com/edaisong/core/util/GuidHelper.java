package com.edaisong.core.util;

import java.util.UUID;

/**
 * guid帮助类
 * @author CaoHeYang
 *@date 20160119
 */
public class GuidHelper {
	/**
	 * Create GUID
	 * 
	 * @author Administrator
	 *
	 */
	public static  String guid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * Create GUID
	 * 
	 * @author Administrator
	 *
	 */
	public static String guidNoSepToUpperCase() {
		String uidString=guid().replace("-", "");
		return uidString.toUpperCase();
	}
}
