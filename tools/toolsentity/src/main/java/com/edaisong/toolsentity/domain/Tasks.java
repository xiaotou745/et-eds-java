package com.edaisong.toolsentity.domain;

import java.util.Date;

import com.edaisong.toolscore.enums.TaskStatus;
import com.edaisong.toolscore.util.DateTime;
import com.edaisong.toolsentity.req.TasksStatus;

/**
 * 实体类Tasks. (属性说明自动提取数据库字段的描述信息)
 * 
 * @author wangyuchuan
 * @date 2015-12-07 10:50:37
 *
 */
public class Tasks {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 优先级(0普通 1 中等 2 很急)
	 */
	private Integer priorityLevel;

	/**
	 * 类型（1个人，个人任务只自己看 2公司，公司任务所有人都可以看到）
	 */
	private Integer type;

	/**
	 * 录入人
	 */
	private Integer userId;

	/**
	 * 录入人
	 */
	private String user;

	/**
	 * 1、未接受 2、进行中 3、已完成
	 */
	private Integer status;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 负责人ID
	 */
	private Integer whoId;

	/**
	 * 责任人
	 */
	private String who;

	/**
	 * 发布时间
	 */
	private Date pubTime;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 任务时长
	 */
	private Integer taskTime;

	/**
	 * 完成时间
	 */
	private Date completeTime;

	/**
	 * 是否有效
	 */
	private Boolean isEnable;

	/**
	 * 获取ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取优先级(0普通 1 中等 2 很急)
	 */
	public Integer getPriorityLevel() {
		return priorityLevel;
	}

	/**
	 * 设置优先级(0普通 1 中等 2 很急)
	 * 
	 * @param priorityLevel
	 *            优先级(0普通 1 中等 2 很急)
	 */
	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	/**
	 * 获取类型（1个人，个人任务只自己看 2公司，公司任务所有人都可以看到）
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置类型（1个人，个人任务只自己看 2公司，公司任务所有人都可以看到）
	 * 
	 * @param type
	 *            类型（1个人，个人任务只自己看 2公司，公司任务所有人都可以看到）
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取录入人
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置录入人
	 * 
	 * @param userId
	 *            录入人
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取录入人
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 设置录入人
	 * 
	 * @param user
	 *            录入人
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 获取1、未接受 2、进行中 3、已完成
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置1、未接受 2、进行中 3、已完成
	 * 
	 * @param status
	 *            1、未接受 2、进行中 3、已完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取负责人ID
	 */
	public Integer getWhoId() {
		return whoId;
	}

	/**
	 * 设置负责人ID
	 * 
	 * @param whoId
	 *            负责人ID
	 */
	public void setWhoId(Integer whoId) {
		this.whoId = whoId;
	}

	/**
	 * 获取责任人
	 */
	public String getWho() {
		return who;
	}

	/**
	 * 设置责任人
	 * 
	 * @param who
	 *            责任人
	 */
	public void setWho(String who) {
		this.who = who;
	}

	/**
	 * 获取发布时间
	 */
	public Date getPubTime() {
		return pubTime;
	}

	/**
	 * 设置发布时间
	 * 
	 * @param pubTime
	 *            发布时间
	 */
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	/**
	 * 获取完成时间
	 */
	public Date getCompleteTime() {
		return completeTime;
	}

	/**
	 * 设置完成时间
	 * 
	 * @param completeTime
	 *            完成时间
	 */
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	/**
	 * 获取是否有效
	 */
	public Boolean getIsEnable() {
		return isEnable;
	}

	/**
	 * 设置是否有效
	 * 
	 * @param isEnable
	 *            是否有效
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Integer taskTime) {
		this.taskTime = taskTime;
	}

	/**
	 * 获取预计完成时间
	 * 
	 * @return 返回预计完成时间
	 */
	public Date getExpectCompleteTime() {
		if (getStartTime() == null) {
			return getStartTime();
		}
		return DateTime.parse(getStartTime()).addHours(getTaskTime()).getTime();
	}

	public boolean isCompleteTimeout() {
		if(getStatus().equals(TasksStatus.Completed.value())){
			return getCompleteTime().compareTo(getExpectCompleteTime()) > 0;
		}else if(getStatus().equals(TasksStatus.InProcess.value())){
			return DateTime.Now.compareTo(getExpectCompleteTime()) > 0;
		}else{
			return true;
		}
	}
}