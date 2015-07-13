package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.BusinessMessage;

public interface IBusinessMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessMessage record);

    int insertSelective(BusinessMessage record);

    BusinessMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessMessage record);

    int updateByPrimaryKey(BusinessMessage record);
}