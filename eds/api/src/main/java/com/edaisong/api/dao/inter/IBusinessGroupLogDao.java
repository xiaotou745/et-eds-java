package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessGroupLog;

public interface IBusinessGroupLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessGroupLog record);

    int insertSelective(BusinessGroupLog record);

    BusinessGroupLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessGroupLog record);

    int updateByPrimaryKey(BusinessGroupLog record);
}