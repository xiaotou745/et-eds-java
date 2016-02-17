package com.edaisong.toolscore.enums;

import java.util.ArrayList;
import java.util.List;
/*
 * 管理后台
	E代送管理后台
E代送
	E代送骑士
	商家中心
	E代送（里程计费）
	E代送商户
智能调度
	E代送智能调度
	E代送轻骑士
 * 
 * 
 * */
public enum OnLineProductType {
	/**
	 * E代送管理后台
	 */
	EdsAdmin(1, "E代送管理后台",1),
	/**
	 * E代送骑士
	 */
	EdsClienter(2, "E代送骑士",2),
	/**
	 * 商家中心
	 */
	EdsBusinessCenter(3, "商家中心",2),
	/**
	 * E代送（里程计费）
	 */
	EdsSs(4, "E代送（里程计费）",2),
	/**
	 * E代送商户
	 */
	EdsBusiness(5, "E代送商户",2),
	/**
	 * E代送智能调度
	 */
	EdsIntelligentScheduling (6, "E代送智能调度",3),
	/**
	 * E代送轻骑士
	 */
	EdsIntelligentSchedulingCilenter(7, "E代送轻骑士",3),
	/**
	 * 人人推管理后台
	 */
	RrtAdmin (11, "人人推管理后台",11),
	/**
	 * 人人推App
	 */
	RrtAppC(12, "人人推App",12);


	
	private int value = 0;
	private String desc;
	private int from=0;
	private OnLineProductType(int value, String desc,int DevPlatformType) { 
		this.value = value;
		this.desc = desc;
		this.from=DevPlatformType;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}
	public int platform()
	{
		return this.from;
	}
	public static OnLineProductType getEnum(int index) {
		for (OnLineProductType c : OnLineProductType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
	public static List<OnLineProductType> getListbyplatform(int par)
	{
		List<OnLineProductType> resulTypes= new ArrayList<OnLineProductType>();
		for (OnLineProductType c : OnLineProductType.values()) {
			if (c.platform() == par) {
				resulTypes.add(c);
			}
		}
		return resulTypes;
	}
}
