package com.edaisong.toolscore.util;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List; 
public class EnumHelper {
	public static <T extends Enum<T>> List<T> GetEnumItems(Class<T> enumType) {
		EnumSet<T> stateSet = EnumSet.allOf(enumType);
		List<T> result = new ArrayList<T>();

		for (T s : stateSet) {
			result.add(s);
		}
		return result;
	}
}