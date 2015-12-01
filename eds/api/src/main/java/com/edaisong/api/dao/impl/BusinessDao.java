package com.edaisong.api.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessLoginLog;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.domain.BusinessBasicInfoModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.domain.BusinessRechargeDetailModel;
import com.edaisong.entity.domain.BusinessStatus;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.OrderRespModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.req.BCheckCodeReq;
import com.edaisong.entity.req.BusinessRegisterReq;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.req.GetPushOrderTypeReq;
import com.edaisong.entity.req.MyOrderBReq;
import com.edaisong.entity.req.OrderDetailBReq;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.MyOrderBResp;
import com.edaisong.entity.resp.MyOrderDetailBResp;

@Repository
public class BusinessDao extends DaoBase implements IBusinessDao {

	@Override
	public int insertSelective(Business record)
	{
		return getMasterSqlSessionUtil().insert(
				"IBusinessDao.insertSelective",
				record); 
	}
	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		PagedResponse<BusinessModel> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"IBusinessDao.getBusinessList",
						req);
		return model;
	}
	
	/**
	 * 获取商户、集团、策略 
	 * @param   商户id
	 * @return 
	 * @author 胡灵波
	 * @Date 2015年8月11日 17:48:47
	 */
	@Override
	public BusinessModel getBusiness(Long id) {
		BusinessModel model = getReadOnlySqlSessionUtil()
				.selectOne(
						"IBusinessDao.getBusiness",
						id);
		return model;
	}

	@Override
	public BusinessDetailModel getBusinessDetailByID(int businessID) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"IBusinessDao.getBusinessDetailByID",
						businessID);
	}

	@Override
	public List<BusinessOptionLog> getOpLogByBusinessID(int businessID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IBusinessDao.getOpLogByBusinessID",
						businessID);
	}

	@Override
	public int modifyBusiness(BusinessModifyModel detailModel) {
		return getMasterSqlSessionUtil().update(
				"IBusinessDao.modifyBusiness",
				detailModel);
	}

	@Override
	public Business login(String phoneNo, String password) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("phoneNo", phoneNo);
		paramMap.put("password", password);

		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getBusinessByPhoneNoAndPwd",paramMap);
	}

	@Override
	public boolean addLoginLog(BusinessLoginLog log) {
		return getMasterSqlSessionUtil().insert("IBusinessDao.addLogingLog",log) > 0;
	}
/**
	 * 更新 商户 余额，可提现余额
	 * @param money 金额
	 * @param businessId 商户id
	 * @Date 20150804
	 * @param business
	 * @return
	 */
	@Override
	public int updateForWithdraw(Double money, int businessId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", money);
		parasMap.put("Id", businessId);
		return getMasterSqlSessionUtil()
				.update("IBusinessDao.updateForWithdraw",
						parasMap);

	}
	
	/**
	 * 根据商户id获取商户基础数据
	 */
	@Override
	public Business getById(int businessId) { 
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getById",businessId);
}

	
	@Override
	public int modifyExpress(List<BusinessExpressRelation> listData) {
		return getMasterSqlSessionUtil()
				.update("IBusinessDao.modifyExpress",
						listData);
	}

	@Override
	public int updateAuditStatus(int businessID, int status) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("businessID", businessID);
		parasMap.put("status", status);
		return getMasterSqlSessionUtil()
				.update("IBusinessDao.updateAuditStatus",
						parasMap);
	}

	@Override
	public int updateLastLoginTime(int businessID, Date loginTime) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("businessID", businessID);
		parasMap.put("lastLoginTime", loginTime);
		return getMasterSqlSessionUtil()
				.update("IBusinessDao.updateLastLoginTime",
						parasMap);
	}

	@Override
	public BusinessRechargeDetailModel getRechargeDetail(String orderNo) {
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getRechargeDetail", orderNo);
	}

	@Override
	public boolean updateBusinessIsBind(int businessId, int isBind) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("businessId", businessId);
		parasMap.put("isBind", isBind);
		return getMasterSqlSessionUtil().update(
				"IBusinessDao.updateBusinessIsBind",parasMap) > 0;
	}

	/**
	 * 获取用户状态信息
	 * @author CaoHeYang
	 * @param userid
	 * @date 20150911
	 * @return
	 */
	@Override
	public BusinessStatus getUserStatus(int userid) {
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getUserStatus", userid);
	}
	
	/**
	 * 获取门店发单模式：0 普通模式（默认），1 智能调度模式   默认0
	 * @author CaoHeYang
	 * @date 20151030
	 * @param businessId
	 * @return
	 */
	@Override
	public  Integer getPushOrderType(Long businessId){
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getPushOrderType", businessId);
	}
	
	/**
	 * 骑士端获取店内任务  获取当前骑士的所有含有未接单订单的 雇主信息
	 * @version 3.0  
	 * @author CaoHeYang
	 * @date 20151102
	 * @param para
	 * @return
	 */
	@Override
	public List<InStoreTask> getInStoreTaskStroes(InStoreTaskReq para){
		return getReadOnlySqlSessionUtil().selectList("IBusinessDao.getInStoreTaskStroes", para);
	}

	@Override
	public int bindClienter(BindClienterBusiness bindClienterBusiness) {
		return getMasterSqlSessionUtil().insert("IBusinessDao.bindClienterBusiness",bindClienterBusiness);
	}

	@Override
	public BusinessClienterRelation getClienterBind(BindClienterBusiness bindClienterBusiness) {
		return getMasterSqlSessionUtil().selectOne("IBusinessDao.getClienterBind",bindClienterBusiness);
	}

	@Override
	public List<OrderRespModel> getMyOrdeB(MyOrderBReq myOrderBReq) {
		PagedResponse<OrderRespModel> lists= getMasterSqlSessionUtil().selectPageList(
				"IBusinessDao.getMyOrdeB", myOrderBReq);
		return lists.getResultList();
	}

	@Override
	public MyOrderBResp getOrderCountTotal(MyOrderBReq myOrderBReq) { 
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getOrderCountTotal",myOrderBReq);
	}

	@Override
	public MyOrderDetailBResp getMyOrderDetailB(OrderDetailBReq orderGrabBReq) {
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getMyOrderDetailB",orderGrabBReq);
	}

	@Override
	public BusinessBasicInfoModel getBusinessInfo(BusinessReq businessReq) {
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getBusinessInfo",businessReq);
	}
	
	  /**
	   * 商户是否已注册
	   * @param phone
	   * @return
	   */
	@Override
	public	boolean isExist(String phone){
		Integer count= getReadOnlySqlSessionUtil().selectOne("IBusinessDao.isExist",phone);
		return count>0;
	}
	
	  /**
	   * 商户是否已存在 存在返回id
	   * @param phone
	   * @return
	   */
	@Override
	public	int getId(String phone){
		Integer id= getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getId",phone);
		if(id==null) return 0;
		
		return Integer.valueOf(id);
	}
	/**
	 * 门店修改绑定手机号
	 * @author CaoHeYang
	 * @date 20151110
	 * @param model
	 * @return
	 */
	@Override
	public boolean businessModiyPhone(BCheckCodeReq model) {
		return getMasterSqlSessionUtil().update(
				"IBusinessDao.businessModiyPhone",model) > 0;
	}

	/**
	 * 获取当前商家下所有区域中是否存在未接单的订单数量
	 * @param businessId
	 * @return
	 */
	@Override
	public int getOrderCountInfoByBusinessId(int businessId) {
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getOrderCountInfoByBusinessId", businessId);
	}
	/**
	 * 通过分组ID获取商家策略ID
	 * 茹化肖
	 * 2015年11月11日14:48:11
	 */
	@Override
	public int getStrategyIdByGroupId(int groupId) {
		return getReadOnlySqlSessionUtil().selectOne("IBusinessDao.getStrategyIdByGroupId", groupId);
	}

	@Override
	public int register(BusinessRegisterReq req) { 
		return getMasterSqlSessionUtil().insert("IBusinessDao.register",req);
	}
}
