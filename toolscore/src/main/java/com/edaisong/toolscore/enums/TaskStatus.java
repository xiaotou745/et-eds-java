package com.edaisong.toolscore.enums;

public enum TaskStatus {
	PublishOrder(0, "任务已发布"),
	OrderHadRush(1, "任务已被抢"),
	OrderFinish(2, "任务已完成"),
	CancelOrder(3, "取消任务");
	private int value = 0;
	private String desc;
	private TaskStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TaskStatus getEnum(int index) {
		for (TaskStatus c : TaskStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
