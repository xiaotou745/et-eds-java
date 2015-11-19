package com.edaisong.core.util;

public class DistanceConvert {
	
	public static String ToString(Object o, String defaultValue) {
		
		String result = defaultValue;
		
		double d = ParseHelper.ToDouble(o, 0);
		if(0<d && d<1000){
		 return  ParseHelper.ToString(ParseHelper.ToLong(Math.round(d),0),"")+"m";
		}
		if(d>1000){
			return ParseHelper.ToString(ParseHelper.ToLong(Math.round(d/1000),0),"")+"km";
		} 
		 
		return result;
	}

}
