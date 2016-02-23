package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessSetpCharge;
import com.edaisong.entity.BusinessSetpChargeChild;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessSetpReq;

/**
 * 领域对象接口 IBusinessSetpChargeDao
 * @author wangyuchuan
 * @date 2016-02-18 12:36:32
 *
 */
public interface IBusinessSetpChargeDao {
	/**
	 * 新增一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpCharge 要新增的对象
	 * @return  返回新增对象的自增Id
	 */
	int insert(BusinessSetpCharge businessSetpCharge);
	/**
	 * 新增策略子项
	 * 茹化肖
	 * @param child
	 * @return
	 */
	int insertChild(BusinessSetpChargeChild child);

	/**
	 * 更新一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param businessSetpCharge 要更改的对象
	 */
	int update(BusinessSetpCharge businessSetpCharge);

	/**
	 * 删除一条记录
	 * @author wangyuchuan
	 * @date 2016-02-18 12:36:32
	 * @param id 
	 */
	void delete(Long id);

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
	PagedResponse<BusinessSetpCharge> select(PagedBusinessSetpReq businessSetpChargeQueryInfo);
	/**
	 * 查询子项
	 * @param id
	 * @return
	 */
	List<BusinessSetpChargeChild> getListBySetpChargeId(Long id);
	/**
	 * 清除子项数据
	 * @param setpChargeId
	 * @return
	 */
	int clearSetpChargeChild(Long setpChargeId);

}
