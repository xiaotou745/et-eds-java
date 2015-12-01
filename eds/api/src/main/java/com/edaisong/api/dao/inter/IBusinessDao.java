package com.edaisong.api.dao.inter;


import java.util.Date;
import java.util.List;

import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessLoginLog;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.HttpResultModel;
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
import com.edaisong.entity.Business;

public interface IBusinessDao {
    int insertSelective(Business record);
	/**
	 * 获取分页的商家列表
	 * @return
	 */
    PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
	/**
	 * 修改商家信息时，获取商家明细信息
	 * @return
	 */
	BusinessDetailModel getBusinessDetailByID(int businessID);
	/**
	 * 获取商家的修改记录
	 * @return
	 */
	List<BusinessOptionLog> getOpLogByBusinessID(int businessID);
	/**
	 * 修改商家信息
	 * @return
	 */
	int modifyBusiness(BusinessModifyModel detailModel);
	/**
	 * 商家登录
	 * @return
	 */
	Business login(String phoneNo,String password);
	/**
	 * 新增商家登录日志
	 * @return
	 */
	boolean addLoginLog(BusinessLoginLog log);
	
	/**
	 * 获取商户、集团、策略 
	 * @param   商户id
	 * @return 
	 * @author 胡灵波
	 * @Date 2015年8月11日 17:48:47
	 */
	public BusinessModel getBusiness(Long id);

	/**
	 * 更新 商户 余额，可提现余额   
	 * @param money  金额
	 * @param businessId  商户id
	 * @Date 20150804
	 * @param business
	 * @return
	 */
	 int updateForWithdraw(Double money,int businessId);
	/**
	 * 根据商户Id获取商户信息
	 * @param businessId 商户Id
	 * @return Business
	 */
	Business getById(int businessId);
	/**
	 * 修改商家绑定的物流公司
	 * @param 
	 * @return 
	 */
	int modifyExpress(List<BusinessExpressRelation> listData);
	
	/**
	 * 修改商家审核状态
	 * @param 
	 * @return 
	 */
	int updateAuditStatus(int businessID,int status);
	
	/**
	 * 更新商户最后登录时间
	 * @param businessID
	 * @param loginTime
	 * @author pengyi
	 * @date 20150818
	 * @return
	 */
	int updateLastLoginTime(int businessID,Date loginTime);
	
	/**
	 * 根据单号查询充值详情
	 * @author pengyi
	 * @param orderNo
	 * @return
	 */
	BusinessRechargeDetailModel getRechargeDetail(String orderNo);
	
	boolean updateBusinessIsBind(int businessId, int isBind);
	
	/**
	 * 获取用户状态信息
	 * @author CaoHeYang
	 * @param userid
	 * @date 20150911
	 * @return
	 */
	BusinessStatus getUserStatus(int userid);
	
	/**
	 * 获取门店发单模式：0 普通模式（默认），1 智能调度模式   默认0
	 * @author CaoHeYang
	 * @date 20151030
	 * @param businessId
	 * @return
	 */
	  Integer getPushOrderType(Long businessId);
	  
		/**
		 * 骑士端获取店内任务  获取当前骑士的所有含有未接单订单的 雇主信息
		 * @version 3.0  
		 * @author CaoHeYang
		 * @date 20151102
		 * @param para
		 * @return
		 */
	    List<InStoreTask> getInStoreTaskStroes(InStoreTaskReq para);
		/*
		 * 绑定骑士 建立商户和骑士关系
		 * wangchao
		 */
	    int bindClienter(BindClienterBusiness bindClienterBusiness);
	    BusinessClienterRelation getClienterBind(BindClienterBusiness bindClienterBusiness);
		List<OrderRespModel> getMyOrdeB(MyOrderBReq myOrderBReq);
		MyOrderBResp getOrderCountTotal(MyOrderBReq myOrderBReq);
		MyOrderDetailBResp getMyOrderDetailB(OrderDetailBReq orderGrabBReq);
		BusinessBasicInfoModel getBusinessInfo(BusinessReq businessReq);
	    
		  /**
		   * 商户是否已注册
		   * @param phone
		   * @return
		   */
		  boolean isExist(String phone);
		  
		  int getId(String phone);
			/**
			 * 门店修改绑定手机号
			 * @author CaoHeYang
			 * @date 20151110
			 * @param model
			 * @return
			 */
			 boolean businessModiyPhone(BCheckCodeReq model);

	/**
	 * 获取当前商家下所有区域中是否存在未接单的订单数量
	 * @param businessId
	 * @return
	 */
	int getOrderCountInfoByBusinessId(int businessId);
	/**
	 * 通过分组ID获取商家策略ID
	 * @param groupId
	 * @return
	 */
	int getStrategyIdByGroupId(int groupId);
	int register(BusinessRegisterReq req);

}