package com.edaisong.core.common;

import java.lang.reflect.Field;
import java.time.Year;
import java.util.List;

/**
 * html页面帮助类
 * @author CaoHeYang
 * @Date 20150730
 */
public class HtmlHelper {

	/**
	 * 
	 * @param name
	 * @param list
	 * @param textName
	 * @param ValueName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> String getSelect(String name,List<T> list,String textName,String ValueName) throws IllegalArgumentException, IllegalAccessException {
		if (list!=null&& list.size()>0) {
			Field fields[]=list.get(0).getClass().getDeclaredFields();//获得对象所有属性
			Field field=null;
		    String[]attr={"name","address"};
		    for (int i = 0; i < list.size(); i++) {	
		    	   for (int j = 0; j < fields.length; i++) {
					   field=fields[j];
					   field.setAccessible(true);//修改访问权限
					   for (int m = 0; j < attr.length; j++)
					   {
						   if (attr[j].equals(field.getName())) {
							     System.out.println(field.getName()+":"+field.get(list.get(i)));//读取属性值
						   }
					   }
			    }
			}
		    return "1";
		}else {
			return "";
		}

	}
}
