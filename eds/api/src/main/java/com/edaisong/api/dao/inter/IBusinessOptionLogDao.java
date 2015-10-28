package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessOptionLog;

public interface IBusinessOptionLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessOptionLog record);

    int insertSelective(BusinessOptionLog record);

    BusinessOptionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessOptionLog record);

    int updateByPrimaryKey(BusinessOptionLog record);
}