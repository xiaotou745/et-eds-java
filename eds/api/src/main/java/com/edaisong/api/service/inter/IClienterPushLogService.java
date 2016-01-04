package com.edaisong.api.service.inter;

import com.edaisong.entity.ClienterPushLog;

public interface IClienterPushLogService {
	/**
	 * 新增一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param clienterPushLog 要新增的对象
	 * @return  返回新增对象的自增Id
	 */
	Long create(ClienterPushLog clienterPushLog);

	/**
	 * 更新一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param clienterPushLog 要更改的对象
	 */
	void modify(ClienterPushLog clienterPushLog);

	/**
	 * 删除一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param iD 主键 自增ID
	 */
	void remove(Long iD);

	/**
	 * 根据Id得到一个对象实体
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param iD 主键 自增ID
	 */
	ClienterPushLog getById(Long iD);

}
