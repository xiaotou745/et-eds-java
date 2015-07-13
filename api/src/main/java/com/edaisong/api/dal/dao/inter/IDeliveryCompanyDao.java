package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.DeliveryCompany;

public interface IDeliveryCompanyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryCompany record);

    int insertSelective(DeliveryCompany record);

    DeliveryCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryCompany record);

    int updateByPrimaryKey(DeliveryCompany record);
}