package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsentity.common.RequestBase;
import com.edaisong.toolsentity.domain.Tasks;
import com.edaisong.toolsentity.req.TaskChangeStatusReq;
import com.edaisong.toolsentity.req.TasksStatus;
import com.edaisong.toolsapi.service.inter.ITasksService;
import com.edaisong.toolsapi.dao.inter.ITasksDao;

/**
 * 服务提供对象 TasksService
 * 
 * @author wangyuchuan
 *
 */
@Service
public class TasksService implements ITasksService {
	@Autowired
	private ITasksDao tasksDao;

	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	public Integer create(Tasks tasks) {
		return tasksDao.insert(tasks);
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要更改的对象
	 */
	public void modify(Tasks tasks) {
		tasksDao.update(tasks);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param id
	 *            ID
	 */
	public void remove(Integer id) {
		tasksDao.delete(id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param id
	 *            ID
	 */
	public Tasks getById(Integer id) {
		return tasksDao.getById(id);
	}

	@Override
	public List<Tasks> getByUserId(Integer userId) {
		return tasksDao.getByUserId(userId);
	}

	//
	// @Override
	// public void changeStatus(Integer taskId, TasksStatus targetStatus) {
	// tasksDao.updateStatus(taskId, targetStatus);
	// }

	@Override
	public void modifyStatus(TaskChangeStatusReq statusReq) {
		if (statusReq.getTargetStatus().equals(TasksStatus.toDo.value())) {
			tasksDao.toToDo(statusReq.getTaskId());
		} else if (statusReq.getTargetStatus().equals(TasksStatus.InProcess.value())) {
			tasksDao.toInProcess(statusReq);
		} else if (statusReq.getTargetStatus().equals(TasksStatus.Completed.value())) {
			tasksDao.toCompleted(statusReq.getTaskId());
		}
	}

	@Override
	public List<Tasks> query(RequestBase queryReq) {
		return tasksDao.select(queryReq);
	}
}