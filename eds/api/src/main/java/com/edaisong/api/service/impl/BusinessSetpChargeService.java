package com.edaisong.api.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBusinessSetpChargeDao;
import com.edaisong.api.service.inter.IBusinessSetpChargeService;
import com.edaisong.entity.BusinessSetpCharge;
import com.edaisong.entity.BusinessSetpChargeChild;
import com.edaisong.entity.req.BusinessSetpChargeReq;

/**
 * 服务提供对象 BusinessSetpChargeService
 * @author wangyuchuan
 * @date 2016-02-18 12:36:32
 *
 */
@Service
public class BusinessSetpChargeService implements IBusinessSetpChargeService {
	@Autowired
	private IBusinessSetpChargeDao businessSetpChargeDao;
	/**
	 * 添加一个阶梯策略
	 * 茹化肖
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public int create(BusinessSetpChargeReq req) {
		BusinessSetpCharge businessSetpCharge=req.getSetp();
		List<BusinessSetpChargeChild> childs=req.getChilds();
		int result= businessSetpChargeDao.insert(businessSetpCharge);
		if(result>0)//插入成功
		{
			int x=0;
			for (int i=0;i<childs.size();i++) 
			{
				childs.get(i).setSetpChargeId(businessSetpCharge.getId());
				x+=businessSetpChargeDao.insertChild(childs.get(i));
			}
			if (x!=childs.size()) {
				throw new TransactionalRuntimeException("插入阶梯策略子项未成功");
			}
		}
		else {
			throw new TransactionalRuntimeException("插入阶梯策略未成功");
		}
		return 1;
	}

	@Override
	public void modify(BusinessSetpCharge businessSetpCharge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BusinessSetpCharge getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessSetpCharge> query(
			BusinessSetpCharge businessSetpChargeQueryInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}