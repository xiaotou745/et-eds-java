package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.ReceviceAddress;

public interface IReceviceAddressDao {
    int deleteByPrimaryKey(Long id);

    int insert(ReceviceAddress record);

    int insertSelective(ReceviceAddress record);

    ReceviceAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceviceAddress record);

    int updateByPrimaryKey(ReceviceAddress record);
}