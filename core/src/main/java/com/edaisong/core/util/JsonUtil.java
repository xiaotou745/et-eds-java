package com.edaisong.core.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JsonUtil {
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");//指定日期格式,和.net平台json序列化日期一致
	/** 
     * 将对象转换为json字符串 
     *  
     * @param obj 
     * @return 
     * @throws Exception 
     */  
    public static String obj2string(Object obj) {  
        StringWriter sw = new StringWriter();  
        ObjectMapper mapper = new ObjectMapper(); 
        mapper.setDateFormat(dateFormat);
        try {  
            mapper.writeValue(sw, obj);  
        } catch (Exception e) {  
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
        ObjectMapper mapper = new ObjectMapper();  
        mapper.setDateFormat(dateFormat);
        List<T> objList = null;  
        try {  
            JavaType t = mapper.getTypeFactory().constructParametricType(  
                    List.class, cls);  
            objList = mapper.readValue(jsonStr, t);  
        } catch (Exception e) {  
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
        ObjectMapper mapper = new ObjectMapper();  
        mapper.setDateFormat(dateFormat);
        T obj = null;  
        try {  
            obj = mapper.readValue(jsonStr, cls);  
        } catch (Exception e) {  
        }  
        return obj;  
    }  
      
      
    /** 
     * 将字符串转为json节点 
     * @param jsonStr 
     * @return 
     */  
    public static JsonNode str2node(String jsonStr) {  
        ObjectMapper mapper = new ObjectMapper();  
        mapper.setDateFormat(dateFormat);
        try {  
            return mapper.readTree(jsonStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
}
