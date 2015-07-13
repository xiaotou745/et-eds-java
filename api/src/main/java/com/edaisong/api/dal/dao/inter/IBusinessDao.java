package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.Business;

public interface IBusinessDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
}