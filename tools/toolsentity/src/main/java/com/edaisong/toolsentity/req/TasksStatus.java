package com.edaisong.toolsentity.req;

public enum TasksStatus {
	toDo(1,"未开始"), InProcess(2,"进行中"), Completed(3,"已完成");
	private int value;
	private String desc;

	TasksStatus(int value,String desc) {
		this.value = value;
		this.desc = desc;
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
	
	public String desc(){
		return this.desc;
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
