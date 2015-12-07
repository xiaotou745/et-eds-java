package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.domain.Tasks;
import com.edaisong.toolsentity.req.TaskChangeStatusReq;
import com.edaisong.toolsentity.req.TasksStatus;

/**
 * 领域对象接口 ITasksDao
 * 
 * @author wangyuchuan
 * @date 2015-12-07 10:50:37
 *
 */
public interface ITasksDao {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	Integer insert(Tasks tasks);

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要更改的对象
	 */
	void update(Tasks tasks);

	/**
	 * 更改为待处理状态
	 * 
	 * @param taskId
	 *            任务ID
	 */
	void toToDo(Integer taskId);

	/**
	 * 任务被领取
	 * 
	 * @param statusReq
	 *            参数
	 */
	void toInProcess(TaskChangeStatusReq statusReq);

	/**
	 * 任务完成
	 * 
	 * @param taskId
	 *            任务ID
	 */
	void toCompleted(Integer taskId);

	/**
	 * 更改状态
	 * 
	 * @param taskId
	 *            任务ID
	 * @param targetStatus
	 *            目标状态
	 */
//	void updateStatus(Integer taskId, TasksStatus targetStatus);

	/**
	 * 更改状态
	 * 
	 * @param statusReq
	 *            状态参数
	 */
//	void updateStatus(TaskChangeStatusReq statusReq);

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param id
	 *            ID
	 */
	void delete(Integer id);

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param id
	 *            ID
	 */
	Tasks getById(Integer id);

	/**
	 * 获取指定用户的任务列表
	 * 
	 * @param userId
	 *            指定用户ID
	 * @return 返回指定用户的任务列表
	 */
	List<Tasks> getByUserId(Integer userId);
}