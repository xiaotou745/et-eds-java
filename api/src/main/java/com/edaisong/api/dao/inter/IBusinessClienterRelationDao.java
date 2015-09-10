package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClienterRelationModel;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;

public interface IBusinessClienterRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessClienterRelation record);

    int insertSelective(BusinessClienterRelation record);

    BusinessClienterRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessClienterRelation record);

    int updateByPrimaryKey(BusinessClienterRelation record);
    
    int getBusinessBindClienterQty(int businessId);
    
    int getClienterBindBusinessQty(int clienterId);
   
    PagedResponse<BusinessClienterRelationModel> getBusinessClienterRelationList(PagedCustomerSearchReq req);
    
    boolean modifyClienterBind(ClienterBindOptionReq req);
    
    boolean removeclienterbind(ClienterBindOptionReq req);
    
    /**
	 * 确实骑士是否已绑定商家
	 * @author pengyi
	 * @date 20150901
	 */
    boolean checkHaveBind(ClienterBindOptionReq req);
    
    /**
	 * 添加骑士绑定
	 * @author pengyi
	 * @date 20150901
	 * @param req
	 * @return
	 */
	boolean addClienterBind(ClienterBindOptionReq req);
	
	/**
	 * 获得骑士关系详情
	 * @author pengyi
	 * @date 2015年9月7日 下午3:00:15
	 * @version 1.0
	 * @param businessId
	 * @param clienterId
	 * @return
	 */
    BusinessClienterRelation getDetails(int businessId,int clienterId);
}