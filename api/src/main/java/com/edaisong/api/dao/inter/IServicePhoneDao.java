package com.edaisong.api.dao.inter;

import com.edaisong.entity.ServicePhone;

public interface IServicePhoneDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ServicePhone record);

    int insertSelective(ServicePhone record);

    ServicePhone selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServicePhone record);

    int updateByPrimaryKey(ServicePhone record);
}