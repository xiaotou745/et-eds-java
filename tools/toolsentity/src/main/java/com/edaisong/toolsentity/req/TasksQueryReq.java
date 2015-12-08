package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.RequestBase;

/**
 * 任务查询条件
 * 
 * @author wangyuchuan
 *
 */
public class TasksQueryReq extends RequestBase {
	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 创建开始时间
	 */
	private String startTime;

	/**
	 * 创建结束时间
	 */
	private String endTime;
	
	/**
	 * 获取或设置查询条件的日期类型
	 */
	private int timeType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}
}
