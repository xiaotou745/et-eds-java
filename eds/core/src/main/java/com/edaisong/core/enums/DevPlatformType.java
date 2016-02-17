package com.edaisong.core.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 所属平台(包含易代送)
 * @author ofmyi_000
 *
 */
public enum DevPlatformType  {
	/**
	 * 易代送管理后台
	 */
	EdsAdmin(1, "管理后台",1),
	/**
	 * 易代送
	 */
	Eds(2, "E代送",1),
	/**
	 * 易代送智能调度
	 */
	EdsSs(3, "智能调度",1),
	/**
	 * 人人推管理后台
	 */
	RrtAdmin(11, "管理后台",2),
	/**
	 * 易代送
	 */
	RrtApp(12, "人人推App",2);

	
	private int value = 0;
	private String desc;
	private int dbNameId=0;
	private DevPlatformType(int value, String desc,int dbNameId) { 
		this.value = value;
		this.desc = desc;
		this.dbNameId=dbNameId;
	}
	public int value() {
		return this.value;
	}
	public int dbNameId() {
		return this.dbNameId;
	}
	public String desc() {
		return this.desc;
	}

	public static DevPlatformType getEnum(int index) {
		for (DevPlatformType c : DevPlatformType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
	
	public static List<DevPlatformType> getListbyId(int par)
	{
		List<DevPlatformType> resulTypes= new ArrayList<DevPlatformType>();
		for (DevPlatformType c : DevPlatformType.values()) {
			if (c.dbNameId() == par) {
				resulTypes.add(c);
			}
		}
		return resulTypes;
	}
}
