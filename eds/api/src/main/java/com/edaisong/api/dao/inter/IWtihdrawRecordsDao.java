package com.edaisong.api.dao.inter;

import com.edaisong.entity.WtihdrawRecords;

public interface IWtihdrawRecordsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WtihdrawRecords record);

    int insertSelective(WtihdrawRecords record);

    WtihdrawRecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WtihdrawRecords record);

    int updateByPrimaryKey(WtihdrawRecords record);
}