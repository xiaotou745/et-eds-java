package com.edaisong.toolsentity.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.edaisong.toolsentity.domain.Tasks;
import com.edaisong.toolsentity.domain.User;

/**
 * 我的任务视图模型对象
 * 
 * @author wangyuchuan
 *
 */
public class TasksViewModel {
	private List<Tasks> tasks;

	private List<User> users;
	
	private Integer curUserId;

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * 根据状态获取列表
	 * 
	 * @param status
	 *            状态
	 * @return 返回指定状态的任务列表
	 */
	public List<Tasks> getByStatus(Integer status) {
		List<Tasks> myTasks = getTasks();
		if (myTasks == null) {
			return new ArrayList<Tasks>();
		}
		if (status.equals(1) || status.equals(2)) {// 待领取、进行中
			return myTasks.stream().filter(t -> t.getStatus().equals(status))
					.sorted((t1, t2) -> t2.getPriorityLevel() - t1.getPriorityLevel()).collect(Collectors.toList());
		} else {
			return myTasks.stream().filter(t -> t.getStatus().equals(status))
					.sorted((t1, t2) -> t2.getCompleteTime().compareTo(t1.getCompleteTime()))
					.collect(Collectors.toList());
		}
	}

	public Integer getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(Integer curUserId) {
		this.curUserId = curUserId;
	}
}
