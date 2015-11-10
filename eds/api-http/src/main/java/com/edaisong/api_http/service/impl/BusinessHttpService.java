package com.edaisong.api_http.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IBusinessClienterRelationService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api_http.service.inter.IBusinessHttpService; 
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.BSendCodeType;
import com.edaisong.core.enums.BusinessClienterRelationAuditStatus;
import com.edaisong.core.enums.BusinessOrderEnum;
import com.edaisong.core.enums.ClienterBindBusinessEnum;
import com.edaisong.core.enums.SendSmsType;
import com.edaisong.core.enums.returnenums.GetMyServiceClientersReturnEnum;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.enums.returnenums.GetPushOrderTypeReturnEnum;
import com.edaisong.core.enums.returnenums.OptBindClienterReturnEnum;
import com.edaisong.core.enums.returnenums.RemoveRelationReturnEnum;
import com.edaisong.core.util.RandomCodeStrGenerator;
import com.edaisong.core.util.SmsUtils;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.domain.BusinessBasicInfoModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.req.BSendCodeReq;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.MyOrderBReq;
import com.edaisong.entity.req.OptBindClienterReq;
import com.edaisong.entity.req.OrderDetailBReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;
import com.edaisong.entity.req.GetPushOrderTypeReq;
import com.edaisong.entity.resp.GetMyServiceClientersResp;
import com.edaisong.entity.resp.MyOrderBResp;
import com.edaisong.entity.resp.MyOrderDetailBResp;

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
	@Autowired
	RedisService redisService;

	/**
	 * 获取门店发单模式：0 普通模式（默认），1 快单模式   默认0
	 * 
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@Override
	public HttpResultModel<Integer> getPushOrderType(GetPushOrderTypeReq req) {
		HttpResultModel<Integer> result = new HttpResultModel<Integer>();
		Integer type=businessService.getPushOrderType(req);
		if (req.getBusinessId() == 0||type==null) {
			return result.setStatus(GetPushOrderTypeReturnEnum.BusinessIdError.value()).setMessage(GetPushOrderTypeReturnEnum.BusinessIdError.desc());
		}
		result.setResult(type);
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
	public HttpResultModel<GetMyServiceClientersResp> getMyServiceClienters(PagedGetMyServiceClientersReq req) {
		HttpResultModel<GetMyServiceClientersResp> result = new HttpResultModel<GetMyServiceClientersResp>();
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
	/*
	 * 骑士绑定商户
	 * wangchao
	 */
	@Override
	public HttpResultModel<Object> bindClienterBusiness(BindClienterBusiness bindClienterBusiness) {
		HttpResultModel<Object> result = new HttpResultModel<Object>();
		if(bindClienterBusiness.getBusinessId() <=0 || bindClienterBusiness.getBusinessName() == null || bindClienterBusiness.getBusinessName().trim() == ""){
			result.setStatus(ClienterBindBusinessEnum.BusinessNameEmpty.value());
			result.setMessage(ClienterBindBusinessEnum.BusinessNameEmpty.desc()); 
			return result;
		}
		if(bindClienterBusiness.getClienterId() <=0 || bindClienterBusiness.getClienterName() == null || bindClienterBusiness.getClienterName().trim() == ""){
			result.setStatus(ClienterBindBusinessEnum.ClienterNameEmpty.value());
			result.setMessage(ClienterBindBusinessEnum.ClienterNameEmpty.desc()); 
			return result;
		} 
		
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
	/*
	 * 商户获取 待取货 配送中 已完成 任务
	 * wangchao
	 */
	@Override
	public HttpResultModel<MyOrderBResp> getMyOrdeB(
			MyOrderBReq myOrderBReq) {
		HttpResultModel<MyOrderBResp> result = new HttpResultModel<MyOrderBResp>();
		if(myOrderBReq.getBusinessId() <=0){
			result.setStatus(BusinessOrderEnum.BusinessIdEmpty.value());
			result.setMessage(BusinessOrderEnum.BusinessIdEmpty.desc());
			return result;
		} 
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		MyOrderBResp orderBResps = businessService.getMyOrdeB(myOrderBReq);
		
		result.setResult(orderBResps);
		return result;
	}
	/*
	 * 获取商户 任务详情
	 * wangchao
	 */
	@Override
	public HttpResultModel<MyOrderDetailBResp> getMyOrderDetailB(
			OrderDetailBReq orderGrabBReq) {
		HttpResultModel<MyOrderDetailBResp> result = new HttpResultModel<MyOrderDetailBResp>();
		if(orderGrabBReq.getGrabOrderId() <=0){
			result.setStatus(BusinessOrderEnum.GrabOrderIdEmpty.value());
			result.setMessage(BusinessOrderEnum.GrabOrderIdEmpty.desc());
			return result;
		} 
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		MyOrderDetailBResp orderDetailBResp = businessService.getMyOrderDetailB(orderGrabBReq);
		
		result.setResult(orderDetailBResp);
		return result;
	}

	@Override
	public HttpResultModel<BusinessBasicInfoModel> getBusinessInfo(
			BusinessReq businessReq) {
		HttpResultModel<BusinessBasicInfoModel> result = new HttpResultModel<BusinessBasicInfoModel>();
		if(businessReq.getBusinessId() <=0){
			result.setStatus(BusinessOrderEnum.BusinessIdEmpty.value());
			result.setMessage(BusinessOrderEnum.BusinessIdEmpty.desc());
		}
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		BusinessBasicInfoModel businessBasicInfoModel = businessService.getBusinessInfo(businessReq);
		
		result.setResult(businessBasicInfoModel);
		return result;
	}
	
	/**
	 *  B端发送短信验证码
	 * @author CaoHeYang
	 * @date 201551110
	 * @param req
	 * @return
	 */
	public HttpResultModel<Object> sendCode(BSendCodeReq req){
		try {
			String key = "";
			String phoneNo = req.getPhoneNo();
			String Content = "";// "欢迎您的使用，验证码：#验证码#，请妥善保管相关信息。若非您本人操作，请忽略。";
			HttpResultModel<Object> res = new HttpResultModel<Object>();
			// 修改绑定手机号验证当前手机号
			if (req.getsType() == BSendCodeType.ModifyPhone.value()) {
				key = String.format(RedissCacheKey.Business_SendCode_ModifyPhone, phoneNo);
				Content = "您的验证码：#验证码#，请在5分钟内填写。此验证码只用于修改密码，如非本人操作，请不要理会";
			} else if (req.getsType() == BSendCodeType.ModifyPhoneNewPhone.value()) {

			}
			if (key == "") {
				return res.setStatus(SendSmsType.Fail.value()).setMessage(SendSmsType.Fail.desc());// 发送失败
			}
			String code = RandomCodeStrGenerator.generateCodeNum(6);// 获取随机数
			Content = Content.replace("#验证码#", code);
			redisService.set(key, code, 60 * 5);
			long resultValue = SmsUtils.sendSMS(phoneNo, Content);
			if (resultValue <= 0) {
				return res.setStatus(SendSmsType.Fail.value()).setMessage(SendSmsType.Fail.desc());// 发送失败
			}
			return res.setStatus(SendSmsType.Success.value()).setMessage(SendSmsType.Success.desc());// 设置成功
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
