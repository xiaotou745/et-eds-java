package com.edaisong.toolsentity.req;

public enum TasksStatus {
	toDo(1), InProcess(2), Completed(3);
	private int value;

	TasksStatus(int value) {
		this.value = value;
	}

	public static TasksStatus valueOf(int value) {
		switch (value) {
		case 1:
			return TasksStatus.toDo;
		case 2:
			return TasksStatus.InProcess;
		case 3:
			return TasksStatus.Completed;
		default:
			return TasksStatus.toDo;
		}
	}

	public int value() {
		return this.value;
	}

	public String getDesc() {
		switch (value) {
		case 1:
			return "未开始";
		case 2:
			return "进行中";
		case 3:
			return "已完成";
		default:
			return "";
		}
	}
}
