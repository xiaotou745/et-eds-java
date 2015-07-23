package com.edaisong.entity.common;

/**
 * 缓存Base Model,所有需要缓存的对象都继承此类
 * @author pengyi
 * @date 20150723
 */
public abstract class CacheBaseModel {
	private boolean isEmpty;
	public abstract String getKey();
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
}
