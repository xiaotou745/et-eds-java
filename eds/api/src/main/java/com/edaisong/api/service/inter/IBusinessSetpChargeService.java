package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.BusinessSetpCharge;
import com.edaisong.entity.req.BusinessSetpChargeReq;

/**
 * 服务接口 IBusinessSetpChargeService
 * @author wangyuchuan
 * @date 2016-02-18 12:36:32
 *
 */
public interface IBusinessSetpChargeService {
	/**
	 * 新增一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpCharge 要新增的对象
	 * @return  返回新增对象的自增Id
	 */
	int create(BusinessSetpChargeReq businessSetpCharge);

	/**
	 * 更新一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpCharge 要更改的对象
	 */
	void modify(BusinessSetpCharge businessSetpCharge);

	/**
	 * 删除一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param id 
	 */
	void remove(Long id);

	/**
	 * 根据Id得到一个对象实体
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param id 
	 */
	BusinessSetpCharge getById(Long id);

	/**
	 * 查询方法
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpChargeQueryInfo 查询条件
	 */
	List<BusinessSetpCharge> query(BusinessSetpCharge businessSetpChargeQueryInfo);

}
