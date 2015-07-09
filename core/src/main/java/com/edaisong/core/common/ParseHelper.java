package com.edaisong.core.common;


public class ParseHelper {
	public static int ToInt(Object o, int defaultValue){
		int result = defaultValue;
		try	{
		    result = Integer.parseInt(o.toString());
		}catch(Exception e){
			
		}
		return result;
    }
	
	public static long ToLong(Object o,long defaultValue){
		long result = defaultValue;
		try{
			result = Long.parseLong(o.toString());
		}catch(Exception e){
			
		}
		return result;
	}
}
