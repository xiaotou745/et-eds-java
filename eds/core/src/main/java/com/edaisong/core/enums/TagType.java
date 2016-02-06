package com.edaisong.core.enums;

public enum TagType {
	/**
	 * 0门店
	 */
    Business(0,"门店标签"),
    /**
	 * 1骑士
	 */
    Clienter (1,"骑士标签");
    
	private int value = 0;
	private String desc;
	private TagType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TagType getEnum(int index) {
		for (TagType c : TagType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}

