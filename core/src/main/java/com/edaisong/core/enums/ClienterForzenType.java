package com.edaisong.core.enums;

public enum ClienterForzenType {
    /**
	 * 1冻结中
	 */
    Forzening(1,"冻结中"),
    /**
	 * 2已解冻
	 */
    HadForzen (2,"已解冻");
    
	private int value = 0;
	private String desc;
	private ClienterForzenType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ClienterForzenType getEnum(int index) {
		for (ClienterForzenType c : ClienterForzenType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
