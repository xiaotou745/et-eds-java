package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalConfig;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.security.MD5Util;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessLoginLog;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.req.BusinessLoginReq;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;

@Service
public class BusinessService implements IBusinessService {

	@Autowired
	private IBusinessDao iBusinessDao;
	@Autowired
	private RedisService redisService;

	private static final int MAX_LOING_COUNT = 5;

	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		return iBusinessDao.getBusinessList(req);
	}

	/**
	 * 商家登录
	 * 
	 * @author pengyi
	 * @param req
	 * @return
	 */
	public BusinessLoginResp login(BusinessLoginReq req) {
		BusinessLoginResp resp = new BusinessLoginResp();
		BusinessLoginLog log = new BusinessLoginLog();
		// 参数验证
		if (req.getPhoneNo() == null || req.getPhoneNo().isEmpty()
				|| req.getPassword() == null || req.getPassword().isEmpty()) {
			resp.setLoginSuccess(false);
			resp.setMessage("用户名或密码不能为空");
			addLoginLog(req.getPhoneNo(), "用户名或密码为空", false);
			return resp;
		}
		// 验证登录次数
		String loginCountCacheKey = RedissCacheKey.LOGIN_COUNT_B
				+ req.getPhoneNo();
		Integer loginCount = redisService
				.get(loginCountCacheKey, Integer.class);
		loginCount = loginCount == null ? 0 : loginCount;
		/*
		 * if (loginCount >= MAX_LOING_COUNT) { resp.setLoginSuccess(false);
		 * resp.setMessage("您当前登录的次数大于10，请5分钟后重试");
		 * addLoginLog(req.getPhoneNo(),"5分钟内登录次数超过5次",false); return resp; }
		 */
		// 验证用户名密码是否正确
		String pwd = MD5Util.MD5(GlobalConfig.PWD_SALT + req.getPassword());
		Business b = iBusinessDao.login(req.getPhoneNo(), pwd);
		if (b == null) {
			resp.setLoginSuccess(false);
			resp.setMessage("您输入的用户名或密码错误");
			addLoginLog(req.getPhoneNo(), "用户名或密码错误", false);
			return resp;
		}
		// 审核未通过
		if (b.getStatus() != 1) {
			resp.setLoginSuccess(false);
			resp.setMessage("您的商铺尚未验证通过");
			addLoginLog(req.getPhoneNo(), "账号未审核通过", false);
			return resp;
		}
		resp.setLoginSuccess(true);
		resp.setBid(b.getId());
		resp.setMessage("登录成功");
		// 登录成功
		// 设置登录次数+1
		redisService.set(loginCountCacheKey, loginCount + 1, 5 * 60);
		// 记录登录日志
		addLoginLog(req.getPhoneNo(), "登录成功", true);
		// web层设置登录cookie
		return resp;
	}
	
	@Override
	public Business login(String phoneNo,String password){
		String pwd = MD5Util.MD5(GlobalConfig.PWD_SALT+password);
		Business b = iBusinessDao.login(phoneNo, pwd);
		return b;
	}

	@Override
	public BusinessDetailModel getBusinessDetailByID(int businessID) {
		return iBusinessDao.getBusinessDetailByID(businessID);
	}

	@Override
	public List<BusinessOptionLog> getOpLogByBusinessID(int businessID) {
		return iBusinessDao.getOpLogByBusinessID(businessID);
	}

	@Override
	public int modifyBusiness(BusinessModifyModel detailModel) {
		BusinessDetailModel oldModel = iBusinessDao
				.getBusinessDetailByID(detailModel.getId());
		String remark = GetRemark(oldModel, detailModel);
		if (remark.isEmpty()) {
			return -1;
		}
		detailModel.setRemark(remark);
		return iBusinessDao.modifyBusiness(detailModel);
	}

	private String GetRemark(BusinessDetailModel brm, BusinessModifyModel model) {
		StringBuffer remark = new StringBuffer();
		if (brm != null && brm.getId() > 0) {
			if (brm.getAddress() == null
					|| !brm.getAddress().equals(model.getAddress())) {
				remark.append(String.format("商户地址原值:%s,修改为%s;",
						brm.getAddress(), model.getAddress()));
			}
			if (brm.getGroupid() != model.getGroupid()) {
				remark.append(String.format("第三方id原值:%s,修改为%s;",
						brm.getGroupid(), model.getGroupid()));
			}
			if (brm.getName() == null || !brm.getName().equals(model.getName())) {
				remark.append(String.format("商户名原值:%s,修改为%s;", brm.getName(),
						model.getName()));
			}
			if (brm.getPhoneno2() == null
					|| !brm.getPhoneno2().equals(model.getPhoneno2())) {
				remark.append(String.format("联系电话原值:%s,修改为%s;",
						brm.getPhoneno2(), model.getPhoneno2()));
			}
			// 座机
			if (brm.getLandline() == null
					|| !brm.getLandline().equals(model.getLandline())) {
				remark.append(String.format("联系座机原值:%s,修改为%s;",
						brm.getLandline(), model.getLandline()));
			}
			if (brm.getDistribsubsidy().compareTo(model.getDistribsubsidy()) != 0) {
				remark.append(String.format("配送费原值:%s,修改为%s;",
						brm.getDistribsubsidy(), model.getDistribsubsidy()));
			}
			if (brm.getCity() == null || !brm.getCity().equals(model.getCity())) {
				remark.append(String.format("城市原值:%s,修改为%s;", brm.getCity(),
						model.getCity()));
			}
			if (brm.getDistrict() == null
					|| !brm.getDistrict().equals(model.getDistrict())) {
				remark.append(String.format("区域原值:%s,修改为%s;",
						brm.getDistrict(), model.getDistrict()));
			}
			if (brm.getLongitude().compareTo(model.getLongitude()) != 0) {
				remark.append(String.format("经度原值:%s,修改为%s;",
						brm.getLongitude(), model.getLongitude()));
			}
			if (brm.getLatitude().compareTo(model.getLatitude()) != 0) {
				remark.append(String.format("纬度原值:%s,修改为%s;",
						brm.getLatitude(), model.getLatitude()));
			}
			if (brm.getCommissiontype() != model.getCommissiontype()) {
				remark.append(String.format("结算类型原值:%s,修改为%s;",
						brm.getCommissiontype(), model.getCommissiontype()));
			}
			if (model.getCommissiontype() == 1
					&& brm.getBusinesscommission().compareTo(
							model.getBusinesscommission()) != 0) {
				remark.append(String.format("固定比例原值:%s,修改为%s;",
						brm.getBusinesscommission(),
						model.getBusinesscommission()));
			}
			if (model.getCommissiontype() == 2
					&& brm.getCommissionfixvalue().compareTo(
							model.getCommissionfixvalue()) != 0) {
				remark.append(String.format("固定金额原值:%s,修改为%s;",
						brm.getCommissionfixvalue(),
						model.getCommissionfixvalue()));
			}

			// 补贴策略 BusinessGroupId
			if (brm.getBusinessgroupid() != model.getBusinessgroupid()) {
				remark.append(String.format("补贴策略原值:%s,修改为%s;",
						brm.getBusinessgroupid(), model.getBusinessgroupid()));
			}
			// 餐费结算方式
			if (brm.getMealssettlemode() != model.getMealssettlemode()) {
				remark.append(String.format("餐费结算方式原值:%s,修改为%s;",
						brm.getMealssettlemode(), model.getMealssettlemode()));
			}
			// 一键发单
			if (brm.getOnekeypuborder() != model.getOnekeypuborder()) {
				remark.append(String.format("一键发单原值:%s,修改为%s;",
						brm.getOnekeypuborder(), model.getOnekeypuborder()));
			}
			// 余额可以透支
			if (brm.getIsallowoverdraft() != model.getIsallowoverdraft()) {
				remark.append(String.format("余额透支原值:%s,修改为%s;",
						brm.getIsallowoverdraft(), model.getIsallowoverdraft()));
			}
			// 雇主任务时间限制
			if (brm.getIsemployertask() != model.getIsemployertask()) {
				remark.append(String.format("余额透支原值:%s,修改为%s;",
						brm.getIsemployertask(), model.getIsemployertask()));
			}
			// 第三方Id
			if (brm.getOriginalbusiid() != model.getOriginalbusiid()) {
				remark.append(String.format("第三方ID原值:%s,修改为%s;",
						brm.getOriginalbusiid(), model.getOriginalbusiid()));
			}
		}
		if (remark.length() > 0) {
			String tipString = model.getOptName() + "通过后台管理系统修改商户信息:";
			return tipString + remark.toString();
		}
		return "";
	}

	public void addLoginLog(String phoneNo, String description,
			boolean isSuccess) {
		BusinessLoginLog log = new BusinessLoginLog();
		log.setDescription(description);
		log.setIsSuccess(isSuccess ? (short) 1 : (short) 0);
		log.setPhoneNo(phoneNo);
		iBusinessDao.addLoginLog(log);
	}

	@Override
	/**
	 * 根据商户Id获取商户信息
	 * @param businessId 商户Id
	 * @return Business
	 */
	public Business getBusinessById(int businessId) {
		return iBusinessDao.getBusinessById(businessId);
	}

	@Override
	public int modifyExpress(List<BusinessExpressRelation> listData) {
	    return	iBusinessDao.modifyExpress(listData);
	}

}
