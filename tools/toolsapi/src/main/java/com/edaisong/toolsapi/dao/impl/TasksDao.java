package com.edaisong.toolsapi.dao.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsentity.common.RequestBase;
import com.edaisong.toolsentity.domain.Tasks;
import com.edaisong.toolsentity.req.TaskChangeStatusReq;
import com.edaisong.toolsentity.req.TasksQueryReq;
import com.edaisong.toolsentity.req.TasksStatus;
import com.edaisong.toolsapi.dao.inter.ITasksDao;

/**
 * 数据访问对象 TasksDao
 * 
 * @author wangyuchuan
 * @date 2015-12-07 10:50:37
 *
 */
@Repository
public class TasksDao extends DaoBase implements ITasksDao {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	@Override
	public Integer insert(Tasks tasks) {
		getMasterSqlSessionUtil().insert("com.edaisong.toolsapi.dao.inter.ITasksDao.insert", tasks);
		return tasks.getId();
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要更改的对象
	 */
	@Override
	public void update(Tasks tasks) {
		getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.ITasksDao.update", tasks);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param id
	 *            ID
	 */
	@Override
	public void delete(Integer id) {
		getMasterSqlSessionUtil().delete("com.edaisong.toolsapi.dao.inter.ITasksDao.delete", id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param id
	 *            ID
	 */
	@Override
	public Tasks getById(Integer id) {
		return getMasterSqlSessionUtil().selectOne("com.edaisong.toolsapi.dao.inter.ITasksDao.getById", id);
	}

	@Override
	public List<Tasks> getByUserId(Integer userId) {
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.ITasksDao.getByUserId", userId);
	}

	/*
	 * @Override public void updateStatus(Integer taskId, TasksStatus
	 * targetStatus) { Map<String, Object> params = new HashMap<String,
	 * Object>(); params.put("id", taskId); params.put("status",
	 * targetStatus.value()); getMasterSqlSessionUtil().delete(
	 * "com.edaisong.toolsapi.dao.inter.ITasksDao.updateStatus", params); }
	 */
	@Override
	public void toToDo(Integer taskId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", taskId);
		params.put("status", TasksStatus.toDo.value());
		params.put("whoId", 0);
		params.put("who", "");
		params.put("startTime", null);
		params.put("taskTime", 0);

		getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.ITasksDao.toToDoInProcess", params);
	}

	@Override
	public void toInProcess(TaskChangeStatusReq statusReq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", statusReq.getTaskId());
		params.put("status", TasksStatus.InProcess.value());
		params.put("whoId", statusReq.getWhoId());
		params.put("who", statusReq.getWho());
		params.put("startTime", statusReq.getStartTime());
		params.put("taskTime", statusReq.getTaskTime());
		getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.ITasksDao.toToDoInProcess", params);
	}

	@Override
	public void toCompleted(Integer taskId) {
		getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.ITasksDao.toCompleted", taskId);
	}

	@Override
	public List<Tasks> select(RequestBase queryReq) {
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.ITasksDao.select", queryReq);
	}
}