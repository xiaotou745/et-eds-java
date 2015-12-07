package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.domain.Tasks;
import com.edaisong.toolsentity.req.TaskChangeStatusReq;
import com.edaisong.toolsentity.req.TasksStatus;

/**
 * 服务接口 ITasksService
 * 
 * @author wangyuchuan
 * @date 2015-12-07 10:50:37
 *
 */
public interface ITasksService {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	Integer create(Tasks tasks);

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param tasks
	 *            要更改的对象
	 */
	void modify(Tasks tasks);

	/**
	 * 更改状态
	 * 
	 * @param taskId
	 *            任务ID
	 * @param targetStatus
	 *            目标状态
	 */
//	void changeStatus(Integer taskId, TasksStatus targetStatus);

	/**
	 * 更改状态
	 * 
	 * @param statusReq
	 *            状态参数
	 */
	void modifyStatus(TaskChangeStatusReq statusReq);

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-07 10:50:37
	 * @param id
	 *            ID
	 */
	void remove(Integer id);

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