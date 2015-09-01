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
}