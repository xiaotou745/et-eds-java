package com.edaisong.api.dao.impl;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessSetpChargeDao;
import com.edaisong.entity.BusinessSetpCharge;
import com.edaisong.entity.BusinessSetpChargeChild;


/**
 * 数据访问对象 BusinessSetpChargeDao
 * @author wangyuchuan
 * @date 2016-02-18 12:36:32
 *
 */
@Repository
public class BusinessSetpChargeDao extends DaoBase implements IBusinessSetpChargeDao {
	/**
	 * 新增一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpCharge 要新增的对象
	 * @return  返回新增对象的自增Id
	 */
	@Override
	public int insert(BusinessSetpCharge businessSetpCharge) {
		return getMasterSqlSessionUtil().insert("IBusinessSetpChargeDao.insert", businessSetpCharge);
	}

	/**
	 * 更新一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpCharge 要更改的对象
	 */
	@Override
	public void update(BusinessSetpCharge businessSetpCharge) {
		getMasterSqlSessionUtil().update("IBusinessSetpChargeDao.update", businessSetpCharge);
	}

	/**
	 * 删除一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param id 
	 */
	@Override
	public void delete(Long id) {
		getMasterSqlSessionUtil().delete("IBusinessSetpChargeDao.delete", id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param id 
	 */
	@Override
	public BusinessSetpCharge getById(Long id) {
		return getMasterSqlSessionUtil().selectOne("IBusinessSetpChargeDao.getById", id);
	}

	/**
	 * 查询方法
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpChargeQueryInfo 查询条件
	 */
	@Override
	public List<BusinessSetpCharge> select(BusinessSetpCharge businessSetpChargeQueryInfo) {
		return null;
	}
	/**
	 * 添加一个策略子项
	 * 茹化肖
	 * 2016年2月18日17:12:08
	 */
	@Override
	public int insertChild(BusinessSetpChargeChild child) {
		return getMasterSqlSessionUtil().insert("IBusinessSetpChargeDao.insertChild", child);
	}

}
