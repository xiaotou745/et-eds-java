package com.edaisong.toolsentity.req;

public class TaskChangeStatusReq {
	private Integer taskId;

	private Integer targetStatus;

	private Integer taskTime;

	private String startTime;
	
	private Integer whoId;
	
	private String who;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getTargetStatus() {
		return targetStatus;
	}

	public void setTargetStatus(Integer targetStatus) {
		this.targetStatus = targetStatus;
	}

	public Integer getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Integer taskTime) {
		this.taskTime = taskTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getWhoId() {
		return whoId;
	}

	public void setWhoId(Integer whoId) {
		this.whoId = whoId;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}
}
