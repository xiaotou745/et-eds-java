package com.edaisong.api.service.impl;

import java.util.Date;
import java.util.List;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IBusinessService; 
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.BusinessStatusEnum;
import com.edaisong.core.security.MD5Util;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessLoginLog;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.domain.BusinessBasicInfoModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.domain.BusinessRechargeDetailModel;
import com.edaisong.entity.domain.OrderRespModel;
import com.edaisong.entity.req.BusinessMoney;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.req.GetPushOrderTypeReq;
import com.edaisong.entity.req.MyOrderBReq;
import com.edaisong.entity.req.OrderDetailBReq;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;
import com.edaisong.entity.resp.MyOrderBResp;
import com.edaisong.entity.resp.MyOrderDetailBResp;

@Service
public class BusinessService implements IBusinessService {

	@Autowired
	private IBusinessDao iBusinessDao;
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;
	@Autowired
	private RedisService redisService;
	@Autowired
	private IGroupBusinessDao groupBusinessDao;

	private static final int MAX_LOING_COUNT = 5;

	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		return iBusinessDao.getBusinessList(req);
	}

	@Override
	public BusinessLoginResp login(String phoneNo, String password) {
		BusinessLoginResp resp = new BusinessLoginResp();
		// 登录次数验证
		String loginCountCacheKey = RedissCacheKey.LOGIN_COUNT_B + phoneNo;
		Integer loginCount = redisService
				.get(loginCountCacheKey, Integer.class);
		loginCount = loginCount == null ? 0 : loginCount;
		if (loginCount >= GlobalSettings.MAX_LOGIN_COUNT) {
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
			resp.setMessage("您当前登录的次数大于10，请5分钟后重试");
			addLoginLog(phoneNo == null ? "" : phoneNo, "5分钟内登录次数超过10次", false);
			return resp;
		}
		// 参数验证
		if (phoneNo == null || phoneNo.isEmpty() || password == null
				|| password.isEmpty()) {
			addLoginLog(phoneNo == null ? "" : phoneNo, "用户名或密码为空", false);
			resp.setMessage("用户名或密码为空");
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
			return resp;
		}
		String pwd = MD5Util.MD5(password);
		Business b = iBusinessDao.login(phoneNo, pwd);
		if (b == null) {
			resp.setMessage("用户名或密码错误");
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
			return resp;
		}
		// 审核未通过
		if (b.getStatus() != 1) {
			resp.setMessage("您的商铺尚未验证通过");
			resp.setResponseCode(ResponseCode.BUSINESS_FAILURE_ERROR);
			addLoginLog(phoneNo, "商铺尚未验证通过", false);
			return resp;
		}
		redisService.set(loginCountCacheKey, loginCount + 1, 5 * 60);
		resp.setBusiness(b);
		resp.setResponseCode(ResponseCode.SUCESS);
		return resp;
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
				remark.append(String.format("代收客配原值:%s,修改为%s;",
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

			if (brm.getBusinesscommission().compareTo(
					model.getBusinesscommission()) != 0) {
				remark.append(String.format("商配比例原值:%s,修改为%s;",
						brm.getBusinesscommission(),
						model.getBusinesscommission()));
			}
			if (brm.getCommissionfixvalue().compareTo(
					model.getCommissionfixvalue()) != 0) {
				remark.append(String.format("商配定额原值:%s,修改为%s;",
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
						brm.getMealssettlemode() == 0 ? "线下" : "线上",
						model.getMealssettlemode() == 0 ? "线下" : "线上"));
			}
			// 一键发单
			if (brm.getOnekeypuborder() != model.getOnekeypuborder()) {
				remark.append(String.format("一键发单原值:%s,修改为%s;",
						brm.getOnekeypuborder() == 1 ? "是" : "否",
						model.getOnekeypuborder() == 1 ? "是" : "否"));
			}
			// 余额可以透支
			if (brm.getIsallowoverdraft() != model.getIsallowoverdraft()) {
				remark.append(String.format("余额透支原值:%s,修改为%s;",
						brm.getIsallowoverdraft() == 1 ? "是" : "否",
						model.getIsallowoverdraft() == 1 ? "是" : "否"));
			}
			// 雇主任务时间限制
			if (brm.getIsemployertask() != model.getIsemployertask()) {
				remark.append(String.format("雇主任务时间限制原值:%s,修改为%s;",
						brm.getIsemployertask() == 1 ? "是" : "否",
						model.getIsemployertask() == 1 ? "是" : "否"));
			}
			// 第三方Id
			if (brm.getOriginalbusiid() != model.getOriginalbusiid()) {
				remark.append(String.format("第三方ID原值:%s,修改为%s;",
						brm.getOriginalbusiid(), model.getOriginalbusiid()));
			}
			// 是否需要审核
			if (brm.getIsOrderChecked() != model.getIsOrderChecked()) {
				remark.append(String.format("订单是否需要审核原值:%s,修改为%s;",
						brm.getIsOrderChecked() == 1 ? "是" : "否",
						model.getIsOrderChecked() == 1 ? "是" : "否"));
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
	public Business getById(int businessId) {
		return iBusinessDao.getById(businessId);
	}

	@Override
	public int modifyExpress(List<BusinessExpressRelation> listData) {
		return iBusinessDao.modifyExpress(listData);
	}

	@Override
	public int updateAuditStatus(int businessID, int status) {
		if (status != BusinessStatusEnum.AuditPass.value()
				&& status != BusinessStatusEnum.AuditRefuse.value()) {
			return -1;
		}
		int result = iBusinessDao.updateAuditStatus(businessID, status);
		if (result > 0) {
			Business business = iBusinessDao.getById(businessID);
			String juWangKeBusiAuditCallBack = PropertyUtils
					.getProperty("JuWangKeBusiAuditCallBack");
			// 调用第三方接口 ，聚网客商户审核通过后调用接口
			// 这里不建议使用 1 数字，而是根据 配置文件中的 appkey来获取 groupid
			if (business.getGroupid() != null && business.getGroupid() == 1
					&& business.getOriginalbusiid() > 0
					&& status == BusinessStatusEnum.AuditPass.value()) {
				String str = HttpUtil.sendPost(juWangKeBusiAuditCallBack,
						"supplier_id=" + business.getOriginalbusiid());
			}
		}
		return result;
	}

	/**
	 * 更新商户最后登录时间
	 * 
	 * @author pengyi
	 * @date 20150818
	 */
	@Override
	public int updateLastLoginTime(int businessID, Date loginTime) {
		return iBusinessDao.updateLastLoginTime(businessID, loginTime);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int updateForWithdrawC(Integer moneyType,BusinessBalanceRecord record) {
		int numfix = -1;
		if (moneyType > 0) {
			numfix = 1;
		}
	
		if (record.getGroupid() > 0) {
			record.setGroupamount(Math.abs(record.getGroupamount()) * numfix);
			groupBusinessDao.recharge(record.getGroupid(),record.getGroupamount());
			GroupBusiness groupBusiness = groupBusinessDao.select(record.getGroupid());
			record.setGroupafterbalance(groupBusiness.getAmount());
			businessBalanceRecordDao.groupInsert(record); // 记录
		} else {
			record.setAmount(Math.abs(record.getAmount()) * numfix);
			iBusinessDao.updateForWithdraw(record.getAmount(),record.getBusinessid());
			record.setGroupafterbalance(0d);
			return businessBalanceRecordDao.insert(record);
		}
		return 0;
	}

	/**
	 * 获取商户、集团、策略
	 * 
	 * @param 商户id
	 * @return
	 * @author 胡灵波
	 * @Date 2015年8月11日 17:48:47
	 */
	@Override
	public BusinessModel getBusiness(int id) {
		return iBusinessDao.getBusiness(id);
	}

	@Override
	public BusinessRechargeDetailModel getRechargeDetail(String orderNo) {
		return iBusinessDao.getRechargeDetail(orderNo);
	}

	/**
	 * 更新商家余额、可提现余额
	 * 
	 * @param businessMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	@Override
	public void updateBBalanceAndWithdraw(BusinessMoney businessMoney) {
		iBusinessDao.updateForWithdraw(businessMoney.getAmount(),
				businessMoney.getBusinessId());// 更新商户余额
		// 插入商家余额流水
		BusinessBalanceRecord businessBalanceRecord = new BusinessBalanceRecord();
		businessBalanceRecord.setBusinessid(businessMoney.getBusinessId());// 商户Id
		businessBalanceRecord.setAmount(businessMoney.getAmount());
		businessBalanceRecord.setStatus((short) businessMoney.getStatus()); // 流水状态
		businessBalanceRecord.setRecordtype((short) businessMoney
				.getRecordType());
		businessBalanceRecord.setOperator(businessMoney.getOperator()); // 商家id
		businessBalanceRecord.setWithwardid((long) businessMoney
				.getWithwardId()); // 关联单id
		businessBalanceRecord.setRelationno(businessMoney.getRelationNo()); // 关联单号
		businessBalanceRecord.setRemark(businessMoney.getRemark()); // 注释
		businessBalanceRecordDao.insert(businessBalanceRecord);
	}

	/**
	 * 获取门店发单模式：0 普通模式（默认），1 快单模式   默认0
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@Override
	public  Integer getPushOrderType(GetPushOrderTypeReq par){
	 return iBusinessDao.getPushOrderType(par.getBusinessId());
	}

	@Override
	public int bindClienter(BindClienterBusiness bindClienterBusiness) { 
		return iBusinessDao.bindClienter(bindClienterBusiness);
	}

	@Override
	public boolean getClienterBind(BindClienterBusiness bindClienterBusiness) {
		return iBusinessDao.getClienterBind(bindClienterBusiness);
	}

	@Override
	public MyOrderBResp getMyOrdeB(MyOrderBReq myOrderBReq) { 
		List<OrderRespModel> orderRespModels = iBusinessDao.getMyOrdeB(myOrderBReq);
		if(orderRespModels!=null &&orderRespModels.size() > 0){		
			orderRespModels.forEach(action -> action.setClienterHeadPhoto((PropertyUtils.getProperty("ImageServicePath") + action.getClienterHeadPhoto())));
		}
		MyOrderBResp myOrderBResp = new MyOrderBResp();
		myOrderBResp.setOrderRespModel(orderRespModels);
		
		MyOrderBResp orderCountBResp = iBusinessDao.getOrderCountTotal(myOrderBReq);
		myOrderBResp.setQuHuoOrderCountTotal(orderCountBResp.getQuHuoOrderCountTotal());
		myOrderBResp.setPeiSongOrderCountTotal(orderCountBResp.getPeiSongOrderCountTotal());
		myOrderBResp.setYiWanChenOrderCountTotal(orderCountBResp.getYiWanChenOrderCountTotal());
		
		return myOrderBResp;
	}

	@Override
	public MyOrderDetailBResp getMyOrderDetailB(OrderDetailBReq orderGrabBReq) { 
		return iBusinessDao.getMyOrderDetailB(orderGrabBReq);
	}

	@Override
	public BusinessBasicInfoModel getBusinessInfo(BusinessReq businessReq) { 
		return iBusinessDao.getBusinessInfo(businessReq);
	}
}
