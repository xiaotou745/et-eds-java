package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IBusinessClienterRelationDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IClienterBindOptionLogDao;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.api.service.inter.IBusinessClienterRelationService;
import com.edaisong.core.enums.BusinessClienterRelationAuditStatus;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.ClienterBindOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.domain.BusinessClienterRelationModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.OptBindClienterReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;
import com.edaisong.entity.resp.GetMyServiceClientersResp;

@Service
public class BusinessClienterRelationService implements IBusinessClienterRelationService {

	@Autowired
	private IBusinessClienterRelationDao businessClienterRelationDao;
	@Autowired
	private IBusinessDao businessDao;
	@Autowired
	private IClienterDao clienterDao;

	@Override
	public int getBusinessBindClienterQty(int businessId) {
		return businessClienterRelationDao.getBusinessBindClienterQty(businessId);
	}

	@Override
	public PagedResponse<BusinessClienterRelationModel> getBusinessClienterRelationList(PagedCustomerSearchReq req) {
		return businessClienterRelationDao.getBusinessClienterRelationList(req);
	}
	/**
	 * 修改骑士绑定
	 * 
	 * @author pengyi
	 * @date 20150901
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public boolean modifyClienterBind(ClienterBindOptionReq req) {
		if(businessClienterRelationDao.modifyClienterBind(req)){
			if (req.getIsBind() == 1) {// 绑定
				businessDao.updateBusinessIsBind(req.getBusinessId(), 1);
			    clienterDao.updateClienterIsBind(req.getClienterId(), 1);
			} else {// 解绑
				removeBusinessAndClienterBind(req);
			}
		}
		return true;
	}
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean removeBusinessAndClienterBind(ClienterBindOptionReq req){
		boolean clienterHasBind=businessClienterRelationDao.getClienterBindBusinessQty(req.getClienterId()) > 0;
		boolean businessHasBind=businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0;
	
		if (!clienterHasBind) {
			clienterDao.updateClienterIsBind(req.getClienterId(), 0);
		}
		if (!businessHasBind) {
			businessDao.updateBusinessIsBind(req.getBusinessId(), 0);
		} 
		return true;
	}
	/**
	 * 删除骑士绑定
	 * 
	 * @author pengyi
	 * @date 20150901
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public boolean removeclienterbind(ClienterBindOptionReq req) {
		if(businessClienterRelationDao.removeclienterbind(req)){
			removeBusinessAndClienterBind(req);
		}
		return true;
	}

	/**
	 * 确实骑士是否已绑定商家
	 * 
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public boolean checkHaveBind(ClienterBindOptionReq req) {
		return businessClienterRelationDao.checkHaveBind(req);
	}

	/**
	 * 添加骑士绑定
	 * 
	 * @author pengyi
	 * @date 20150901
	 * @param req
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean addClienterBind(ClienterBindOptionReq req) {
		businessClienterRelationDao.addClienterBind(req);
		if (req.getIsBind()==1) {
			businessDao.updateBusinessIsBind(req.getBusinessId(), 1);
			clienterDao.updateClienterIsBind(req.getClienterId(), 1);
		}
		return true;
	}

	@Override
	public BusinessClienterRelation getDetails(int businessId, int clienterId) {
		return businessClienterRelationDao.getDetails(businessId, clienterId);
	}

	/**
	 * 商戶端 我的骑士
	 * 
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@Override
	public GetMyServiceClientersResp  getMyServiceClienters(PagedGetMyServiceClientersReq req) {
		GetMyServiceClientersResp result=businessClienterRelationDao.getMyServiceClientersCountInfo(req);
		result.setList( businessClienterRelationDao.getMyServiceClienters(req));
		result.getList().forEach(action -> action.setHeadPhoto(PropertyUtils.getProperty("ImageServicePath") + action.getHeadPhoto()));
		return result;
	}

	/**
	 * 商戶端 我的骑士 申请中 同意/拒绝功能
	 * 
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int optBindClienter(OptBindClienterReq req) {
		if (req.getAuditStatus() == BusinessClienterRelationAuditStatus.Pass.value()) {
			req.setRemark("门店审核通过骑士申请");
			req.setIsEnable(1);
			req.setIsBind(1);
		} else if (req.getAuditStatus() == BusinessClienterRelationAuditStatus.Refuse.value()) {
			req.setRemark("门店拒绝通过骑士申请");
			req.setIsEnable(1);
			req.setIsBind(0);
		}
		req.setOptName("门店");
		int result= businessClienterRelationDao.optBindClienter(req);
		if (result>0&&req.getAuditStatus() == BusinessClienterRelationAuditStatus.Pass.value()) {
			BusinessClienterRelation relationData=businessClienterRelationDao.getByRelationId(req.getRelationId());
			businessDao.updateBusinessIsBind(req.getBusinessId(), 1);
			clienterDao.updateClienterIsBind(relationData.getClienterid(), 1);
		}
		return result;
	}

	/*
	 * 骑士申请绑定时候，若绑定关系已经存在且审核不是审核通过的情况下，修改状态值为待审核
	 * wangchao
	 */
	@Override
	public boolean updateClienterBindRelation(ClienterBindOptionReq bindClienterBusiness) {
		return businessClienterRelationDao.updateClienterBindRelation(bindClienterBusiness);
	}

}
