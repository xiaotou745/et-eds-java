package com.edaisong.api_http.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.redis.NetRedisService;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IBusinessClienterRelationService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api_http.service.inter.IBusinessHttpService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.BSendCodeType;
import com.edaisong.core.enums.BusinessClienterRelationAuditStatus;
import com.edaisong.core.enums.BusinessOrderEnum;
import com.edaisong.core.enums.ClienterBindBusinessEnum;
import com.edaisong.core.enums.returnenums.BusinessModiyPhoneReturnEnum;
import com.edaisong.core.enums.returnenums.GetMyServiceClientersReturnEnum;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.enums.returnenums.GetPushOrderTypeReturnEnum;
import com.edaisong.core.enums.returnenums.OptBindClienterReturnEnum;
import com.edaisong.core.enums.returnenums.RemoveRelationReturnEnum;
import com.edaisong.core.enums.returnenums.SendSmsReturnType;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.RandomCodeStrGenerator;
import com.edaisong.core.util.SmsUtils;
import com.edaisong.core.validator.CommonValidator;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.domain.BusinessBasicInfoModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.req.BCheckCodeReq;
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
	NetRedisService redisService;

	/**
	 * 获取门店发单模式：0 普通模式（默认），1 快单模式 默认0
	 * 
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@Override
	public HttpResultModel<Integer> getPushOrderType(GetPushOrderTypeReq req) {
		HttpResultModel<Integer> result = new HttpResultModel<Integer>();
		Integer type = businessService.getPushOrderType(req);
		if (req.getBusinessId() == 0 || type == null) {
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
			return result.setStatus(GetMyServiceClientersReturnEnum.AuditStatusError.value()).setMessage(GetMyServiceClientersReturnEnum.AuditStatusError.desc());
		}
		result.setResult(businessClienterRelationService.getMyServiceClienters(req));
		return result;
	}

	/*
	 * 骑士绑定商户 wangchao
	 */
	@Override
	public HttpResultModel<Object> bindClienterBusiness(BindClienterBusiness bindClienterBusiness) {
		HttpResultModel<Object> result = new HttpResultModel<Object>();
		if (bindClienterBusiness.getBusinessId() <= 0 || bindClienterBusiness.getBusinessName() == null || bindClienterBusiness.getBusinessName().trim() == "") {
			result.setStatus(ClienterBindBusinessEnum.BusinessNameEmpty.value());
			result.setMessage(ClienterBindBusinessEnum.BusinessNameEmpty.desc());
			return result;
		}
		if (bindClienterBusiness.getClienterId() <= 0 || bindClienterBusiness.getClienterName() == null || bindClienterBusiness.getClienterName().trim() == "") {
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
		if (req.getAuditStatus() != BusinessClienterRelationAuditStatus.Pass.value() && req.getAuditStatus() != BusinessClienterRelationAuditStatus.Refuse.value()) {
			return res.setStatus(OptBindClienterReturnEnum.AuditStatusError.value()).setMessage(OptBindClienterReturnEnum.AuditStatusError.desc());
		}
		if (req.getRelationId() <= 0) {
			return res.setStatus(OptBindClienterReturnEnum.RelationIdError.value()).setMessage(OptBindClienterReturnEnum.RelationIdError.desc());
		}
		return businessClienterRelationService.optBindClienter(req) <= 0 ? res.setStatus(OptBindClienterReturnEnum.StatusError.value()).setMessage(OptBindClienterReturnEnum.StatusError.desc()) : res;
	}

	/**
	 * 商家解绑
	 * 
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<Object> removeRelation(ClienterBindOptionReq req) {
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		if (req.getBusinessId() <= 0) {
			return res.setStatus(RemoveRelationReturnEnum.BusinessIdError.value()).setMessage(RemoveRelationReturnEnum.BusinessIdError.desc());
		}
		if (req.getClienterId() <= 0) {
			return res.setStatus(RemoveRelationReturnEnum.ClienterIdError.value()).setMessage(RemoveRelationReturnEnum.ClienterIdError.desc());
		}
		if (req.getRemark() == null || req.getRemark().isEmpty() || req.getRemark().length() < 5 || req.getRemark().length() > 100) {
			return res.setStatus(RemoveRelationReturnEnum.RemarkError.value()).setMessage(RemoveRelationReturnEnum.RemarkError.desc());
		}
		req.setInsertTime(new Date());
		req.setOptName("门店");
		req.setOptId(req.getBusinessId());
		req.setIsBind(0); // 解除绑定
		businessClienterRelationService.modifyClienterBind(req);
		return res;
	}

	/*
	 * 商户获取 待取货 配送中 已完成 任务 wangchao
	 */
	@Override
	public HttpResultModel<MyOrderBResp> getMyOrdeB(MyOrderBReq myOrderBReq) {
		HttpResultModel<MyOrderBResp> result = new HttpResultModel<MyOrderBResp>();
		if (myOrderBReq.getBusinessId() <= 0) {
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
	 * 获取商户 任务详情 wangchao
	 */
	@Override
	public HttpResultModel<MyOrderDetailBResp> getMyOrderDetailB(OrderDetailBReq orderGrabBReq) {
		HttpResultModel<MyOrderDetailBResp> result = new HttpResultModel<MyOrderDetailBResp>();
		if (orderGrabBReq.getGrabOrderId() <= 0) {
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
	public HttpResultModel<BusinessBasicInfoModel> getBusinessInfo(BusinessReq businessReq) {
		HttpResultModel<BusinessBasicInfoModel> result = new HttpResultModel<BusinessBasicInfoModel>();
		if (businessReq.getBusinessId() <= 0) {
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
	 * B端发送短信验证码
	 * 
	 * @author CaoHeYang
	 * @date 201551110
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<Object> sendCode(BSendCodeReq req) {
		String key = "";
		String phoneNo = req.getPhoneNo();
		String Content = "";
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		if (phoneNo==null||phoneNo.isEmpty()||CommonValidator.validPhoneNumber( phoneNo)) {
			return res.setStatus(SendSmsReturnType.PhoneError.value()).setMessage(SendSmsReturnType.PhoneError.desc());
		}
		else if (req.getMessageType() !=0&&req.getMessageType() !=1) {  //修改绑定手机号验证新手机号
			return res.setStatus(SendSmsReturnType.MessageTypeError.value()).setMessage(SendSmsReturnType.MessageTypeError.desc());
		}
		try {
			if (req.getType() == BSendCodeType.Register.value()) {	
				key = String.format(RedissCacheKey.PostRegisterInfo_B, phoneNo);
				Content = "短信验证码：#验证码#（E代送商家版手机动态码，请完成注册），如非本人操作，请忽略本短信。";
			} else if (req.getType() == BSendCodeType.ForgetPwd.value()||req.getType() == BSendCodeType.ModifyPwd.value()) {	
				key = String.format(RedissCacheKey.CheckCodeFindPwd_B, phoneNo);
				Content = "短信验证码：#验证码#（E代送商家版手机动态码，请完成修改密码），如非本人操作，请忽略本短信。";
			} 
			else if (req.getType() == BSendCodeType.ModifyPhone.value()) {	// 修改绑定手机号验证当前手机号
				key = String.format(RedissCacheKey.Business_SendCode_ModifyPhone, phoneNo);
				Content = "短信验证码：#验证码#(E代送商家版手机动态码，请完成验证)，此验证码5分钟内有效，如非本人操作，请忽略本短信。";
			} else if (req.getType() == BSendCodeType.ModifyPhoneNewPhone.value()) {  //修改绑定手机号验证新手机号
				key = String.format(RedissCacheKey.Business_SendCode_ModifyPhoneNewPhone, phoneNo);
				Content = "短信验证码：#验证码#(E代送商家版手机动态码，请完成新账号验证)，此验证码5分钟内有效，如非本人操作，请忽略本短信。";
			}
			if (key == "") {
				return res.setStatus(SendSmsReturnType.Fail.value()).setMessage(SendSmsReturnType.Fail.desc());// 发送失败
			}
			String code;// 获取随机数
			if (req.getMessageType()==1) {  //语音验证码
				 Content = "您的验证码是：#验证码#";
				 String obj = redisService.get(key,String.class);
				 if (obj==null||obj.isEmpty()) {
					 return res.setStatus(SendSmsReturnType.CodeNotExists.value()).setMessage
							 (SendSmsReturnType.CodeNotExists.desc());
				}
			    	String keycheck = key + "_voice";
		            if (ParseHelper.ToInt(redisService.get(keycheck,String.class)) == 1)
		            {
		            	 return res.setStatus(SendSmsReturnType.Sending.value()).setMessage
								 (SendSmsReturnType.Sending.desc());
		            }
					redisService.set(keycheck, 1, 60 * 5);
					code=obj;
			}else {
				code= RandomCodeStrGenerator.generateCodeNum(6);
				redisService.set(key, code, 60 * 5);
			}
			Content = Content.replace("#验证码#", code);
			long resultValue = req.getMessageType()==0?SmsUtils.sendSMS(phoneNo, Content) : SmsUtils.sendVoiceSMS(phoneNo, Content);
			if (resultValue <= 0) {
				return res.setStatus(SendSmsReturnType.Fail.value()).setMessage(SendSmsReturnType.Fail.desc());// 发送失败
			}
			return res.setStatus(SendSmsReturnType.Success.value()).setMessage(SendSmsReturnType.Success.desc());// 设置成功
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 商家端修改绑定手机号第一步验证当前手机号
	 * 
	 * @author CaoHeYang
	 * @date 201551110
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<Object> businessModiyPhoneStep1(BCheckCodeReq req) {
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		String phoneNo = req.getPhoneNo();
		if (req.getPhoneNo()==null||req.getPhoneNo().isEmpty()||CommonValidator.validPhoneNumber(phoneNo)) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.PhoneError.value()).setMessage
					(BusinessModiyPhoneReturnEnum.PhoneError.desc());
		}
		if (req.getCode()==null||req.getCode().isEmpty()) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.VerCodeNull.value())
					.setMessage(BusinessModiyPhoneReturnEnum.VerCodeNull.desc());
		}
		if (redisService.get(String.format(RedissCacheKey.Business_SendCode_ModifyPhone, phoneNo),String.class)==null) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.CodeTimeOut.value())
					.setMessage(BusinessModiyPhoneReturnEnum.CodeTimeOut.desc());// 验证码不不能为空
		}
		if (!redisService.get(String.format(RedissCacheKey.Business_SendCode_ModifyPhone, phoneNo),String.class).equals(req.getCode())) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.CodeError.value()).setMessage(BusinessModiyPhoneReturnEnum.CodeError.desc());// 发送失败
		}
		return res;
	}

	/**
	 * 商家端修改绑定手机号第二步验证新手机号
	 * 
	 * @author CaoHeYang
	 * @date 201551110
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<Object> businessModiyPhoneStep2(BCheckCodeReq req) {
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		if (req.getBusinessId()==null||req.getBusinessId()==0) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.BusinessId.value()).
					setMessage(BusinessModiyPhoneReturnEnum.BusinessId.desc());
		}
		if (req.getPhoneNo()==null||req.getPhoneNo().isEmpty()||CommonValidator.validPhoneNumber(req.getPhoneNo())) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.PhoneError.value()).
					setMessage(BusinessModiyPhoneReturnEnum.PhoneError.desc());
		}
		if (req.getCode()==null||req.getCode().isEmpty()) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.VerCodeNull.value()).
					setMessage(BusinessModiyPhoneReturnEnum.VerCodeNull.desc());
		}
		String phoneNo = req.getPhoneNo();
		
		System.out.println(String.format(RedissCacheKey.Business_SendCode_ModifyPhoneNewPhone, phoneNo));
		if (redisService.get(String.format(RedissCacheKey.Business_SendCode_ModifyPhoneNewPhone, phoneNo),String.class)==null) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.CodeTimeOut.value())
					.setMessage(BusinessModiyPhoneReturnEnum.CodeTimeOut.desc());
		}
		if (!redisService.get(String.format(RedissCacheKey.Business_SendCode_ModifyPhoneNewPhone, phoneNo),String.class).equals(req.getCode())) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.CodeError.value()).
					setMessage(BusinessModiyPhoneReturnEnum.CodeError.desc());
		}
		if (businessService.isExist(req.getPhoneNo())) {
			return res.setStatus(BusinessModiyPhoneReturnEnum.PhoneExists.value()).
					setMessage(BusinessModiyPhoneReturnEnum.PhoneExists.desc());
		}
		return businessService.businessModiyPhone(req)?res:res.setStatus(BusinessModiyPhoneReturnEnum.ModifyError.value()).
				setMessage(BusinessModiyPhoneReturnEnum.ModifyError.desc());
	}
}