package com.edaisong.api_http.service.impl;

import java.util.Date;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessClienterRelationService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api_http.service.inter.IBusinessHttpService;
import com.edaisong.core.enums.BusinessClienterRelationAuditStatus;
import com.edaisong.core.enums.ClienterBindBusinessEnum;
import com.edaisong.core.enums.returnenums.GetMyServiceClientersReturnEnum;
import com.edaisong.core.enums.returnenums.OptBindClienterReturnEnum;
import com.edaisong.core.enums.returnenums.RemoveRelationReturnEnum;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.OptBindClienterReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;
import com.edaisong.entity.req.IsAllowInputMoneyReq;

/**
 * 商家相关
 * 
 * @author CaoHeYang
 * @date 20151030
 */
@Service
public class BusinessHttpService implements IBusinessHttpService {
	/**
	 * 商家
	 */
	@Autowired
	private IBusinessService businessService;

	@Autowired
	private IBusinessClienterRelationService businessClienterRelationService;

	/**
	 * 获取商家是否需要录入金额才可以发单 0 需要 1 不需要 默认0
	 * 
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@Override
	public HttpResultModel<Integer> getIsAllowInputMoney(IsAllowInputMoneyReq par) {
		HttpResultModel<Integer> result = new HttpResultModel<Integer>();
		result.setResult(businessService.getIsAllowInputMoney(par));
		return result;
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
	public HttpResultModel<List<ServiceClienters>> getMyServiceClienters(PagedGetMyServiceClientersReq req) {
		HttpResultModel<List<ServiceClienters>> result = new HttpResultModel<List<ServiceClienters>>();
		if (req.getBusinessId() == 0) {
			return result.setStatus(GetMyServiceClientersReturnEnum.BusinessIdError.value()).setMessage(GetMyServiceClientersReturnEnum.BusinessIdError.desc());
		}
		if (req.getAuditStatus() == null || req.getAuditStatus() > 1) {
			return result.setStatus(GetMyServiceClientersReturnEnum.AuditStatusError.value()).setMessage(
					GetMyServiceClientersReturnEnum.AuditStatusError.desc());
		}
		result.setResult(businessClienterRelationService.getMyServiceClienters(req));
		return result;
	}

	@Override
	public HttpResultModel<Object> bindClienterBusiness(BindClienterBusiness bindClienterBusiness) {
		HttpResultModel<Object> result = new HttpResultModel<Object>();
		result.setStatus(ClienterBindBusinessEnum.Success.value());
		result.setMessage(ClienterBindBusinessEnum.Success.desc()); 
		boolean b = businessService.getClienterBind(bindClienterBusiness);
		if (!b) {
			int bindResult = businessService.bindClienter(bindClienterBusiness);
			if (bindResult <= 0) {
				result.setStatus(ClienterBindBusinessEnum.Fail.value());
				result.setMessage(ClienterBindBusinessEnum.Fail.desc());
			}
		} else {
			result.setStatus(ClienterBindBusinessEnum.HadBind.value());
			result.setMessage(ClienterBindBusinessEnum.HadBind.desc());
		}
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
	public HttpResultModel<Object> optBindClienter(OptBindClienterReq req) {
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		if (req.getBusinessId() <= 0) {
			return res.setStatus(OptBindClienterReturnEnum.BusinessIdError.value()).setMessage(OptBindClienterReturnEnum.BusinessIdError.desc());
		}
		if (req.getAuditStatus() != BusinessClienterRelationAuditStatus.Pass.value()
				&& req.getAuditStatus() != BusinessClienterRelationAuditStatus.Refuse.value()) {
			return res.setStatus(OptBindClienterReturnEnum.AuditStatusError.value()).setMessage(OptBindClienterReturnEnum.AuditStatusError.desc());
		}
		if (req.getRelationId() <= 0) {
			return res.setStatus(OptBindClienterReturnEnum.RelationIdError.value()).setMessage(OptBindClienterReturnEnum.RelationIdError.desc());
		}
		return businessClienterRelationService.optBindClienter(req) <= 0 ? res.setStatus(OptBindClienterReturnEnum.StatusError.value()).setMessage(
				OptBindClienterReturnEnum.StatusError.desc()) : res;
	}
	/**
	 * 商家解绑
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@Override
	 public HttpResultModel<Object> removeRelation(ClienterBindOptionReq  req){
		 HttpResultModel<Object> res = new HttpResultModel<Object>();
		 if (req.getBusinessId() <= 0) {
				return res.setStatus(RemoveRelationReturnEnum.BusinessIdError.value()).setMessage(RemoveRelationReturnEnum.BusinessIdError.desc());
		 }
		 if (req.getClienterId() <= 0) {
				return res.setStatus(RemoveRelationReturnEnum.ClienterIdError.value()).setMessage(RemoveRelationReturnEnum.ClienterIdError.desc());
	     }
		 if (req.getRemark()==null||req.getRemark().isEmpty()||req.getRemark().length()<5||req.getRemark().length()>100) {
				return res.setStatus(RemoveRelationReturnEnum.RemarkError.value()).setMessage(RemoveRelationReturnEnum.RemarkError.desc());
	     }
         req.setInsertTime(new Date());
         req.setOptName("门店");
         req.setOptId(req.getBusinessId());
         req.setIsBind(0);  //解除绑定
	     businessClienterRelationService.modifyClienterBind(req);
	     return res;
	 }
}
