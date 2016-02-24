package com.edaisong.api.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBusinessSetpChargeDao;
import com.edaisong.api.service.inter.IBusinessSetpChargeService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.BusinessSetpCharge;
import com.edaisong.entity.BusinessSetpChargeChild;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.BusinessSetpChargeReq;
import com.edaisong.entity.req.PagedBusinessSetpReq;

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
		int result=0;
		if(businessSetpCharge.getId()!=null&&businessSetpCharge.getId()>0)
		{
			//修改,清空子项数据
			businessSetpChargeDao.clearSetpChargeChild(businessSetpCharge.getId());
			//更新数据
			result=businessSetpChargeDao.update(businessSetpCharge);
		}
		else{//新增 插入数据 
			result= businessSetpChargeDao.insert(businessSetpCharge);
		}
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
		
		return businessSetpChargeDao.getById(id);
	}
	/**
	 * 分页查询列表
	 * 茹化肖
	 */
	@Override
	public PagedResponse<BusinessSetpCharge> query(PagedBusinessSetpReq businessSetpChargeQueryInfo) {
		return businessSetpChargeDao.select(businessSetpChargeQueryInfo);
	}
	/**
	 * 获取信息
	 */
	@Override
	public String getSetpinfo(Long id) {
		BusinessSetpChargeReq result=new BusinessSetpChargeReq();
		BusinessSetpCharge re=businessSetpChargeDao.getById(id);
		result.setSetp(re);
		List<BusinessSetpChargeChild> chid= businessSetpChargeDao.getListBySetpChargeId(id);
		result.setChilds((ArrayList<BusinessSetpChargeChild>)chid);
		return JsonUtil.obj2string(result);
	}

}