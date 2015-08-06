package com.edaisong.api.dal.dao.inter;


import java.math.BigDecimal;
import java.util.List;

import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessLoginLog;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.req.PagedBusinessReq;

public interface IBusinessDao {
    PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
	BusinessDetailModel getBusinessDetailByID(int businessID);
	List<BusinessOptionLog> getOpLogByBusinessID(int businessID);
	int modifyBusiness(BusinessModifyModel detailModel);
	Business login(String phoneNo,String password);
	boolean addLoginLog(BusinessLoginLog log);
	

	/**
	 * 更新 商户 余额，可提现余额   
	 * @param money  金额
	 * @param businessId  商户id
	 * @Date 20150804
	 * @param business
	 * @return
	 */
	 int updateForWithdraw(BigDecimal money,int businessId);
	/**
	 * 根据商户Id获取商户信息
	 * @param businessId 商户Id
	 * @return Business
	 */
	Business getBusinessById(int businessId);
	/**
	 * 修改商家绑定的物流公司
	 * @param 
	 * @return 
	 */
	int modifyExpress(List<BusinessExpressRelation> listData);
}