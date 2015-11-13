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
		boolean flag = false;
		if (businessClienterRelationDao.modifyClienterBind(req)) { // 更新绑定状态
			if (req.getIsBind() == 1) {// 绑定
				if (businessDao.updateBusinessIsBind(req.getBusinessId(), 1)) {
					if (clienterDao.updateClienterIsBind(req.getClienterId(), 1)) {
						flag = true;
					}
				}
			} else {// 解绑
				if (businessClienterRelationDao.getClienterBindBusinessQty(req.getClienterId()) > 0) {
					if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
						flag = true;
					} else {
						if (businessDao.updateBusinessIsBind(req.getBusinessId(), 0)) {
							flag = true;
						}
					}
				} else {
					if (clienterDao.updateClienterIsBind(req.getClienterId(), 0)) {
						if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
							flag = true;
						} else {
							if (businessDao.updateBusinessIsBind(req.getBusinessId(), 0)) {
								flag = true;
							}
						}
					}
				}
			}
		}
		if (!flag) {
			throw new TransactionalRuntimeException("修改骑士绑定失败");
		}
		return flag;
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
		boolean flag = false;
		if (businessClienterRelationDao.removeclienterbind(req)) {
			if (businessClienterRelationDao.getClienterBindBusinessQty(req.getClienterId()) > 0) {
				if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
					flag = true;
				}
			} else {
				if (clienterDao.updateClienterIsBind(req.getClienterId(), 0)) {
					if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
						flag = true;
					} else {
						if (businessDao.updateBusinessIsBind(req.getBusinessId(), 0)) {
							flag = true;
						}
					}
				}
			}
		}
		if (!flag) {
			throw new TransactionalRuntimeException("删除骑士绑定失败");
		}
		return flag;
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
		boolean reg = false;
		if (businessClienterRelationDao.addClienterBind(req)) {
			if (businessDao.updateBusinessIsBind(req.getBusinessId(), 1)) {
				if (clienterDao.updateClienterIsBind(req.getClienterId(), 1)) {
					reg = true;
				}
			}
		}
		if (!reg) {
			throw new TransactionalRuntimeException("添加骑士绑定失败");
		}
		return reg;
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
		result.getList().forEach(action -> action.setHeadPhoto(PropertyUtils.getProperty("ImageClienterServicePath") + action.getHeadPhoto()));
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
		return businessClienterRelationDao.optBindClienter(req);
	}

	/*
	 * 骑士申请绑定时候，若绑定关系已经存在且审核不是审核通过的情况下，修改状态值为待审核
	 * wangchao
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public boolean updateClienterBindRelation(ClienterBindOptionReq bindClienterBusiness) {
		boolean reg = false;
		if (businessClienterRelationDao.updateClienterBindRelation(bindClienterBusiness)) {
			if (businessDao.updateBusinessIsBind(bindClienterBusiness.getBusinessId(), 1)) {
				if (clienterDao.updateClienterIsBind(bindClienterBusiness.getClienterId(), 1)) {
					reg = true;
				}
			}
		}
		if (!reg) {
			throw new TransactionalRuntimeException("骑士再次申请绑定商户失败");
		}
		return reg;
	}

}
