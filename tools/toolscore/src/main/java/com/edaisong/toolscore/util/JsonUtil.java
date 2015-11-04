package com.edaisong.toolscore.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;

public class JsonUtil {
	private final static ExtandObjectMapper mapper = new ExtandObjectMapper();
	/** 
     * 将对象转换为json字符串 
     *  
     * @param obj 
     * @return 
     * @throws Exception 
     */  
    public static String obj2string(Object obj) {  
    	if (obj==null) {
			return "";
		}
        StringWriter sw = new StringWriter();  
        try {  
        	mapper.writeValue(sw, obj);  
        } catch (Exception e) {  
        	throw new RuntimeException("序列化时出错:"+e.getMessage());
        }  
        return sw.toString();  
    }  
  
    /** 
     * 将字符串转list对象 
     *  
     * @param <T> 
     * @param jsonStr 
     * @param cls 
     * @return 
     */  
    public static <T> List<T> str2list(String jsonStr, Class<T> cls) {  
        List<T> objList = null;  
        try {  
            JavaType t = mapper.getTypeFactory().constructParametricType(  
                    List.class, cls);  
            objList = mapper.readValue(jsonStr, t);  
        } catch (Exception e) {  
        	throw new RuntimeException("反序列化为List对象时出错:"+e.getMessage());
        }  
        return objList;  
    }  
  
    /** 
     * 将字符串转为对象 
     *  
     * @param <T> 
     * @param jsonStr 
     * @param cls 
     * @return 
     */  
    public static <T> T str2obj(String jsonStr, Class<T> cls) {  
        T obj = null;  
        try {  
            obj = mapper.readValue(jsonStr, cls);  
        } catch (Exception e) {  
        	throw new RuntimeException("反序列化时出错:"+e.getMessage());
        }  
        return obj;  
    }   
}
