package com.edaisong.api.service.inter;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.domain.BusinessRechargeDetailModel;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;



public interface IBusinessService {
	/**
	 * 查询商家分页列表
	 * @return
	 */
	PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
	/**
	 * 修改时，获取商家详情
	 * @return
	 */
	BusinessDetailModel getBusinessDetailByID(int businessID);
	/**
	 * 获取商家修改记录
	 * @return
	 */
	List<BusinessOptionLog> getOpLogByBusinessID(int businessID);
	/**
	 * 修改商家信息
	 * @return
	 */
	int modifyBusiness(BusinessModifyModel detailModel);
	/**
	 * 新增商家登录日志
	 * @return
	 */
	void addLoginLog(String phoneNo, String description, boolean isSuccess);
	/**
	 * 商家登录
	 * @param 手机号
	 * @param 密码
	 * @return
	 */
	BusinessLoginResp login(String phoneNo,String password);
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
	 * 更新商户余额、可提现余额  
	 * @author hailongzhao
	 * @date 20150818
	 * @param businessID
	 * @param money
	 * @return
	 */
	int updateForWithdrawC(BusinessBalanceRecord record);
	
	/**
	 * 获取商户、集团、策略 
	 * @param   商户id
	 * @return 
	 * @author 胡灵波
	 * @Date 2015年8月11日 17:48:47
	 */
	BusinessModel getBusiness(int id);
	
	BusinessRechargeDetailModel getRechargeDetail(String orderNo);
}
