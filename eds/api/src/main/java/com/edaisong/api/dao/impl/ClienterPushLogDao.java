package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterPushLogDao;
import com.edaisong.entity.ClienterPushLog;

/**
 * 数据访问对象 ClienterPushLogDao
 * @author wangyuchuan
 * @date 2016-01-04 09:48:19
 *
 */
@Repository
public class ClienterPushLogDao extends DaoBase implements IClienterPushLogDao {
	/**
	 * 新增一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param clienterPushLog 要新增的对象
	 * @return  返回新增对象的自增Id
	 */
	@Override
	public int insert(ClienterPushLog clienterPushLog) {
		return getMasterSqlSessionUtil().insert("com.edaisong.dao.inter.IClienterPushLogDao.insert", clienterPushLog);
	}

	/**
	 * 更新一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param clienterPushLog 要更改的对象
	 */
	@Override
	public void update(ClienterPushLog clienterPushLog) {
		getMasterSqlSessionUtil().update("com.edaisong.dao.inter.IClienterPushLogDao.update", clienterPushLog);
	}

	/**
	 * 删除一条记录
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param iD 主键 自增ID
	 */
	@Override
	public void delete(Long iD) {
		getMasterSqlSessionUtil().delete("com.edaisong.dao.inter.IClienterPushLogDao.delete", iD);
	}

	/**
	 * 根据Id得到一个对象实体
	 * @author wangyuchuan
	 * @date 2016-01-04 09:48:19
	 * @param iD 主键 自增ID
	 */
	@Override
	public ClienterPushLog getById(Long iD) {
		return getMasterSqlSessionUtil().selectOne("com.edaisong.dao.inter.IClienterPushLogDao.getById", iD);
	}


}