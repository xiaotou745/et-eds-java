package com.edaisong.toolsentity.req;

public enum TaskPrivilege {
	normal(0), middle(1), senior(2);
	private int value;

	TaskPrivilege(int value) {
		this.value = value;
	}

	public static TaskPrivilege valueOf(int value) {
		switch (value) {
		case 0:
			return TaskPrivilege.normal;
		case 1:
			return TaskPrivilege.middle;
		case 2:
			return TaskPrivilege.senior;
		default:
			return TaskPrivilege.normal;
		}
	}

	public int value() {
		return this.value;
	}

	public String getDesc() {
		switch (value) {
		case 0:
			return "普通";
		case 1:
			return "中级";
		case 2:
			return "高级";
		default:
			return "";
		}
	}
}
