package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessGroup;

public interface IBusinessGroupDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessGroup record);

    int insertSelective(BusinessGroup record);

    BusinessGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessGroup record);

    int updateByPrimaryKey(BusinessGroup record);
    
    List<BusinessGroup> getBusinessGroupList();
}