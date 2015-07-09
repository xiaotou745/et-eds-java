package com.edaisong.core.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;

public class JsonUtils {
	private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

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
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			mapper.writeValue(sw, obj);
		} catch (Exception e) {
			logger.error("obj2string error", e);
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
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		List<T> objList = null;
		try {
			JavaType t = mapper.getTypeFactory().constructParametricType(List.class, cls);
			objList = mapper.readValue(jsonStr, t);
		} catch (Exception e) {
			logger.error("str2list error", e);
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
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		T obj = null;
		try {
			obj = mapper.readValue(jsonStr, cls);
		} catch (Exception e) {
			logger.error("str2obj error", e);
		}
		return obj;
	}

	/**
	 * 将字符串转为json节点
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static JsonNode str2node(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(jsonStr);
		} catch (Exception e) {
			logger.error("str2node error", e);
		}
		return null;
	}
}
