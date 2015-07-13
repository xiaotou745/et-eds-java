package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.Subsidy;

public interface ISubsidyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Subsidy record);

    int insertSelective(Subsidy record);

    Subsidy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subsidy record);

    int updateByPrimaryKey(Subsidy record);
}