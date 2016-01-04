package com.edaisong.api.dao.impl;

import java.util.List;
import com.edaisong.entity.ClienterPushLog;

/**
 * 领域对象接口 IClienterPushLogDao
 * @author wangyuchuan
 * @date 2016-01-04 09:48:19
 *
 */
public interface IClienterPushLogDao {
	/**
	 * 新增一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param clienterPushLog 要新增的对象
	 * @return  返回新增对象的自增Id
	 */
	int insert(ClienterPushLog clienterPushLog);

	/**
	 * 更新一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param clienterPushLog 要更改的对象
	 */
	void update(ClienterPushLog clienterPushLog);

	/**
	 * 删除一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param iD 主键 自增ID
	 */
	void delete(Long iD);

	/**
	 * 根据Id得到一个对象实体
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param iD 主键 自增ID
	 */
	ClienterPushLog getById(Long iD);

}