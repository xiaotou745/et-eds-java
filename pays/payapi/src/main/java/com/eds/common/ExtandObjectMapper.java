package com.eds.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ExtandObjectMapper  extends ObjectMapper{
	private static final long serialVersionUID = 4402127997078513582L;

	public ExtandObjectMapper() {
		super();
        //设置null值不参与序列化(字段不被显示)  
        //this.setSerializationInclusion(Include.NON_NULL);
        // 禁用空对象转换json校验
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setDateFormat(df);
        //驼峰命名法转换为小写加下划线
        // this.setPropertyNamingStrategy(new ExtandNameStrategy());
    }

}
