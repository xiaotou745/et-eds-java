package com.edaisong.core.util;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

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